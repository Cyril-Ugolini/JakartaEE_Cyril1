package fr.afpa.jakartaee_cyril1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import models.Adresse;

/**
 * DAO pour la gestion des adresses en base de données.
 *
 * @author Cyril
 * @version 1.0
 */
public final class AdresseDao {

    /**
     * Logger du DAO.
     */
    private static final Logger LOG =
            Logger.getLogger(AdresseDao.class.getName());

    /**
     * Gestionnaire de connexion.
     */
    private final ConnexionManager db;

    /**
     * Constructeur : initialise la connexion.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    public AdresseDao() throws SQLException {
        this.db = ConnexionManager.getInstance();
        LOG.info("AdresseDao initialisé.");
    }

    // ============================================================
    // FIND ALL
    // ============================================================

    /**
     * Retourne toutes les adresses.
     *
     * @return liste d'adresses
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Adresse> findAll() throws SQLException {

        final List<Adresse> liste = new ArrayList<>();

        final String sql =
                "SELECT id_adresse, numero_rue, nom_rue, code_postal, ville "
                        + "FROM adresse";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }

            LOG.info("findAll() : " + liste.size() + " adresse(s).");

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
     * Retourne une adresse par son identifiant.
     *
     * @param id identifiant de l'adresse
     * @return adresse ou null si non trouvée
     * @throws SQLException en cas d'erreur SQL
     */
    public Adresse findById(final int id) throws SQLException {

        final String sql =
                "SELECT id_adresse, numero_rue, nom_rue, code_postal, ville "
                        + "FROM adresse "
                        + "WHERE id_adresse = ?";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    LOG.fine("findById(" + id + ") : trouvée");
                    return mapResultSet(rs);
                }
            }

            LOG.fine("findById(" + id + ") : non trouvée");
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
     * Crée une nouvelle adresse.
     *
     * @param a adresse à créer
     * @return true si création OK
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean create(final Adresse a) throws SQLException {

        final String sql =
                "INSERT INTO adresse (numero_rue, nom_rue, code_postal, ville) "
                        + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, a.getNumeroRue());
            stmt.setString(2, a.getNomRue());
            stmt.setString(3, a.getCodePostal());
            stmt.setString(4, a.getVille());

            final int rows = stmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        a.setIdAdresse(keys.getInt(1));
                    }
                }
                LOG.info("Adresse créée : ID=" + a.getIdAdresse());
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
     * Met à jour une adresse existante.
     *
     * @param a adresse à mettre à jour
     * @return true si mise à jour OK
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean update(final Adresse a) throws SQLException {

        final String sql =
                "UPDATE adresse SET numero_rue=?, nom_rue=?, code_postal=?, ville=? "
                        + "WHERE id_adresse=?";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql)) {

            stmt.setString(1, a.getNumeroRue());
            stmt.setString(2, a.getNomRue());
            stmt.setString(3, a.getCodePostal());
            stmt.setString(4, a.getVille());
            stmt.setInt(5, a.getIdAdresse());

            final int rows = stmt.executeUpdate();

            if (rows > 0) {
                LOG.info("Adresse modifiée : ID=" + a.getIdAdresse());
                return true;
            }

            LOG.warning("Adresse inexistante : ID=" + a.getIdAdresse());
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
     * Supprime une adresse par son identifiant.
     *
     * @param id identifiant de l'adresse
     * @return true si suppression OK
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean delete(final int id) throws SQLException {

        final String sql = "DELETE FROM adresse WHERE id_adresse=?";
        final Connection conn = db.getConnection();

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                final int rows = stmt.executeUpdate();
                conn.commit();

                if (rows > 0) {
                    LOG.info("Adresse supprimée : ID=" + id);
                    return true;
                }

                LOG.warning("Adresse inexistante : ID=" + id);
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
     * Convertit un ResultSet en objet Adresse.
     *
     * @param rs résultat SQL
     * @return adresse mappée
     * @throws SQLException en cas d'erreur SQL
     */
    private Adresse mapResultSet(final ResultSet rs) throws SQLException {

        final Adresse adresse = new Adresse();

        adresse.setIdAdresse(rs.getInt("id_adresse"));
        adresse.setNumeroRue(rs.getString("numero_rue"));
        adresse.setNomRue(rs.getString("nom_rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));

        return adresse;
    }
}
