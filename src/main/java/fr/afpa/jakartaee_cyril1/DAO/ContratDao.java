package fr.afpa.jakartaee_cyril1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.afpa.jakartaee_cyril1.models.Contrat;

/**
 * DAO pour la gestion des contrats en base de données.
 *
 * <p>Cette classe permet d'effectuer les opérations CRUD sur la table
 * {@code contrat}. Elle intègre une gestion d'erreurs SQL basée sur les
 * codes d'erreurs MySQL afin de fournir des messages techniques précis
 * dans les logs, sans exposer ces détails à l'utilisateur.</p>
 *
 * <author>Cyril</author>
 * @version 2.0
 */
public final class ContratDao {

    /** Logger du DAO. */
    private static final Logger LOG =
            Logger.getLogger(ContratDao.class.getName());

    /** Connexion active. */
    private Connection connection;

    /**
     * Constructeur : initialise la connexion.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    public ContratDao() throws SQLException {
        this.connection = ConnexionManager.getConnection();
        LOG.info("ContratDao initialisé.");
    }
    // ============================================================
    // FIND ALL
    // ============================================================
    /**
     * Retourne tous les contrats présents en base.
     *
     * @return liste de contrats
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Contrat> findAll() throws SQLException {

        final List<Contrat> liste = new ArrayList<>();

        final String sql =
                "SELECT identifiant, id_client, nom_contrat, montant "
                        + "FROM contrat";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }

            LOG.info("findAll() : " + liste.size() + " contrat(s).");

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1064 -> "Erreur SQL dans findAll() : syntaxe incorrecte "
                        + "(code=1064)";
                default -> "Erreur SQL dans findAll() (code=" + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new SQLException(message, e);
        }

        return liste;
    }
    /**
     * Retourne un contrat par son identifiant.
     *
     * @param id identifiant du contrat
     * @return contrat ou {@code null} si non trouvé
     * @throws SQLException en cas d'erreur SQL
     */
    public Contrat findById(final int id) throws SQLException {

        final String sql =
                "SELECT identifiant, id_client, nom_contrat, montant "
                        + "FROM contrat WHERE identifiant = ?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1064 -> "Erreur SQL dans findById(" + id
                        + ") : syntaxe incorrecte (code=1064)";
                default -> "Erreur SQL dans findById(" + id + ") (code="
                        + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new SQLException(message, e);
        }
    }
    /**
     * Crée un nouveau contrat en base.
     *
     * @param c contrat à créer
     * @return {@code true} si la création a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean create(final Contrat c) throws SQLException {

        final String sql =
                "INSERT INTO contrat (id_client, nom_contrat, montant) "
                        + "VALUES (?, ?, ?)";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

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

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1048 -> "Champ obligatoire manquant lors de la création "
                        + "du contrat (code=1048)";
                case 1452 -> "Contrainte étrangère violée : client inexistant "
                        + "(code=1452)";
                case 1062 -> "Doublon détecté lors de la création du contrat "
                        + "(code=1062)";
                default -> "Erreur SQL dans create() (code=" + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new SQLException(message, e);
        }
    }
    /**
     * Met à jour un contrat existant.
     *
     * @param c contrat à mettre à jour
     * @return {@code true} si la mise à jour a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean update(final Contrat c) throws SQLException {

        final String sql =
                "UPDATE contrat SET id_client=?, nom_contrat=?, montant=? "
                        + "WHERE identifiant=?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1048 -> "Champ obligatoire manquant lors de la mise à "
                        + "jour (code=1048)";
                case 1452 -> "Contrainte étrangère violée : client inexistant "
                        + "(code=1452)";
                case 1062 -> "Doublon détecté lors de la mise à jour "
                        + "(code=1062)";
                default -> "Erreur SQL dans update() (code=" + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new SQLException(message, e);
        }
    }
    /**
     * Supprime un contrat par son identifiant.
     *
     * @param id identifiant du contrat
     * @return {@code true} si la suppression a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean delete(final int id) throws SQLException {

        final String sql = "DELETE FROM contrat WHERE identifiant=?";

        try (Connection conn = ConnexionManager.getConnection()) {

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

                final int code = e.getErrorCode();
                final String message = switch (code) {
                    case 1451 -> "Suppression impossible : contrainte "
                            + "étrangère (code=1451)";
                    default -> "Erreur SQL dans delete() (code=" + code + ")";
                };

                LOG.severe(message + " | " + e.getMessage());
                throw new SQLException(message, e);
            }

        } finally {
            // Toujours remettre l'autocommit à true
            try (Connection conn = ConnexionManager.getConnection()) {
                conn.setAutoCommit(true);
            }
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
