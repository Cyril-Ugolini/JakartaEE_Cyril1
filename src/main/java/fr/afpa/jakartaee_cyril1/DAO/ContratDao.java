package fr.afpa.jakartaee_cyril1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import models.Contrat;

/**
 * DAO pour la gestion des contrats en base de données.
 *
 * @author Cyril
 * @version 1.0
 */
public final class ContratDao {

    /** Logger du DAO. */
    private static final Logger LOG =
            Logger.getLogger(ContratDao.class.getName());

    /** Gestionnaire de connexion. */
    private final ConnexionManager db;

    /**
     * Constructeur : initialise la connexion.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    public ContratDao() throws SQLException {
        this.db = ConnexionManager.getInstance();
        LOG.info("ContratDao initialisé.");
    }

    // ============================================================
    // FIND ALL
    // ============================================================

    /**
     * Retourne tous les contrats.
     *
     * @return liste de contrats
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Contrat> findAll() throws SQLException {

        final List<Contrat> liste = new ArrayList<>();

        final String sql =
                "SELECT identifiant, id_client, nom_contrat, montant "
                        + "FROM contrat";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }

            LOG.info("findAll() : " + liste.size() + " contrat(s).");

        } catch (SQLException e) {
            LOG.severe("Erreur findAll() : " + e.getMessage());
            throw e;
        }

        return liste;
    }

    // ============================================================
    // FIND BY ID
    // ============================================================

    /**
     * Retourne un contrat par son identifiant.
     *
     * @param id identifiant du contrat
     * @return contrat ou null si non trouvé
     * @throws SQLException en cas d'erreur SQL
     */
    public Contrat findById(final int id) throws SQLException {

        final String sql =
                "SELECT identifiant, id_client, nom_contrat, montant "
                        + "FROM contrat "
                        + "WHERE identifiant = ?";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    LOG.fine("findById(" + id + ") : trouvé");
                    return mapResultSet(rs);
                }
            }

            LOG.fine("findById(" + id + ") : non trouvé");
            return null;

        } catch (SQLException e) {
            LOG.severe("Erreur findById(" + id + ") : " + e.getMessage());
            throw e;
        }
    }

    // ============================================================
    // CREATE
    // ============================================================

    /**
     * Crée un nouveau contrat.
     *
     * @param c contrat à créer
     * @return true si création OK
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean create(final Contrat c) throws SQLException {

        final String sql =
                "INSERT INTO contrat (id_client, nom_contrat, montant) "
                        + "VALUES (?, ?, ?)";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, c.getIdClient());
            stmt.setString(2, c.getNomContrat());
            stmt.setDouble(3, c.getMontant());

            final int rows = stmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        c.setIdentifiant(keys.getInt(1));
                    }
                }
                LOG.info("Contrat créé : ID=" + c.getIdentifiant());
                return true;
            }

            return false;

        } catch (SQLException e) {
            LOG.severe("Erreur create() : " + e.getMessage());
            throw e;
        }
    }

    // ============================================================
    // UPDATE
    // ============================================================

    /**
     * Met à jour un contrat existant.
     *
     * @param c contrat à mettre à jour
     * @return true si mise à jour OK
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean update(final Contrat c) throws SQLException {

        final String sql =
                "UPDATE contrat SET id_client=?, nom_contrat=?, montant=? "
                        + "WHERE identifiant=?";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, c.getIdClient());
            stmt.setString(2, c.getNomContrat());
            stmt.setDouble(3, c.getMontant());
            stmt.setInt(4, c.getIdentifiant());

            final int rows = stmt.executeUpdate();

            if (rows > 0) {
                LOG.info("Contrat modifié : ID=" + c.getIdentifiant());
                return true;
            }

            LOG.warning("Contrat inexistant : ID=" + c.getIdentifiant());
            return false;

        } catch (SQLException e) {
            LOG.severe("Erreur update() : " + e.getMessage());
            throw e;
        }
    }

    // ============================================================
    // DELETE
    // ============================================================

    /**
     * Supprime un contrat par son identifiant.
     *
     * @param id identifiant du contrat
     * @return true si suppression OK
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean delete(final int id) throws SQLException {

        final String sql = "DELETE FROM contrat WHERE identifiant=?";
        final Connection conn = db.getConnection();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                final int rows = stmt.executeUpdate();
                conn.commit();

                if (rows > 0) {
                    LOG.info("Contrat supprimé : ID=" + id);
                    return true;
                }

                LOG.warning("Contrat inexistant : ID=" + id);
                return false;

            } catch (SQLException e) {
                conn.rollback();
                LOG.severe("Erreur delete() : " + e.getMessage());
                throw e;
            }

        } finally {
            conn.setAutoCommit(true);
        }
    }

    // ============================================================
    // MAPPING
    // ============================================================

    /**
     * Convertit un ResultSet en objet Contrat.
     *
     * @param rs résultat SQL
     * @return contrat mappé
     * @throws SQLException en cas d'erreur SQL
     */
    private Contrat mapResultSet(final ResultSet rs) throws SQLException {

        final Contrat c = new Contrat();

        c.setIdentifiant(rs.getInt("identifiant"));
        c.setIdClient(rs.getInt("id_client"));
        c.setNomContrat(rs.getString("nom_contrat"));
        c.setMontant(rs.getDouble("montant"));

        return c;
    }
}
