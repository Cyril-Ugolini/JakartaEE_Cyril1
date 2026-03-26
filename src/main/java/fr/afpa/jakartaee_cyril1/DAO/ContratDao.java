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
 * <p>Cette classe permet d'effectuer les opérations CRUD sur la
 * table {@code contrat}. Elle intègre une gestion d'erreurs SQL
 * basée sur les codes d'erreurs MySQL afin de fournir des
 * messages techniques précis dans les logs.</p>
 *
 * author Cyril
 * @version 2.0
 */
public final class ContratDao {

    /** Logger du DAO. */
    private static final Logger LOG =
            Logger.getLogger(
                    ContratDao.class.getName()
            );

    // ============================================================
// CONSTANTES ERREURS SQL
// ============================================================

    /** Code erreur MySQL : champ obligatoire manquant. */
    private static final int ERR_NOT_NULL = 1048;

    /** Code erreur MySQL : contrainte étrangère (insert/update). */
    private static final int ERR_FOREIGN_KEY_INSERT = 1452;

    /** Code erreur MySQL : contrainte étrangère (delete). */
    private static final int ERR_FOREIGN_KEY_DELETE = 1451;

    /** Code erreur MySQL : doublon. */
    private static final int ERR_DUPLICATE = 1062;

    /** Code erreur MySQL : erreur de syntaxe. */
    private static final int ERR_SYNTAX = 1064;

// ============================================================
// CONSTRUCTEUR
// ============================================================

    /**
     * Constructeur : initialise le DAO.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    public ContratDao() throws SQLException {
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

            LOG.info("findAll() : " + liste.size()
                    + " contrat(s).");

        } catch (SQLException e) {

            final int code = e.getErrorCode();

            final String message = switch (code) {
                case ERR_SYNTAX ->
                        "Erreur SQL dans findAll() : syntaxe incorrecte";
                default ->
                        "Erreur SQL dans findAll() (code="
                                + code + ")";
            };

            LOG.severe(message + " | "
                    + e.getMessage());

            throw new SQLException(message, e);
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
                case ERR_SYNTAX ->
                        "Erreur SQL dans findById(" + id
                                + ") : syntaxe incorrecte";
                default ->
                        "Erreur SQL dans findById(" + id
                                + ") (code=" + code + ")";
            };

            LOG.severe(message + " | "
                    + e.getMessage());

            throw new SQLException(message, e);
        }
    }

// ============================================================
// CREATE
// ============================================================

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
             PreparedStatement stmt =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

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

                LOG.info("Contrat créé : ID="
                        + c.getIdentifiant());

                return true;
            }

            return false;

        } catch (SQLException e) {

            final int code = e.getErrorCode();

            final String message = switch (code) {
                case ERR_NOT_NULL ->
                        "Champ obligatoire manquant lors de la "
                                + "création du contrat";
                case ERR_FOREIGN_KEY_INSERT ->
                        "Contrainte étrangère violée : "
                                + "client inexistant";
                case ERR_DUPLICATE ->
                        "Doublon détecté lors de la création "
                                + "du contrat";
                default ->
                        "Erreur SQL dans create() (code="
                                + code + ")";
            };

            LOG.severe(message + " | "
                    + e.getMessage());

            throw new SQLException(message, e);
        }
    }
    // ============================================================
// UPDATE
// ============================================================

    /**
     * Met à jour un contrat existant.
     *
     * @param c contrat à mettre à jour
     * @return {@code true} si la mise à jour a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean update(final Contrat c) throws SQLException {

        final String sql =
                "UPDATE contrat SET id_client=?, nom_contrat=?, "
                        + "montant=? WHERE identifiant=?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, c.getIdClient());
            stmt.setString(2, c.getNomContrat());
            stmt.setDouble(3, c.getMontant());
            stmt.setInt(4, c.getIdentifiant());

            final int rows = stmt.executeUpdate();

            if (rows > 0) {
                LOG.info("Contrat modifié : ID="
                        + c.getIdentifiant());
                return true;
            }

            LOG.warning("Contrat inexistant : ID="
                    + c.getIdentifiant());
            return false;

        } catch (SQLException e) {

            final int code = e.getErrorCode();

            final String message = switch (code) {
                case ERR_NOT_NULL ->
                        "Champ obligatoire manquant lors de la "
                                + "mise à jour";
                case ERR_FOREIGN_KEY_INSERT ->
                        "Contrainte étrangère violée : "
                                + "client inexistant";
                case ERR_DUPLICATE ->
                        "Doublon détecté lors de la mise à jour";
                default ->
                        "Erreur SQL dans update() (code="
                                + code + ")";
            };

            LOG.severe(message + " | "
                    + e.getMessage());

            throw new SQLException(message, e);
        }
    }

// ============================================================
// DELETE
// ============================================================

    /**
     * Supprime un contrat par son identifiant.
     *
     * @param id identifiant du contrat
     * @return {@code true} si la suppression a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean delete(final int id) throws SQLException {

        final String sql =
                "DELETE FROM contrat WHERE identifiant=?";

        try (Connection conn = ConnexionManager.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt =
                         conn.prepareStatement(sql)) {

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
                    case ERR_FOREIGN_KEY_DELETE ->
                            "Suppression impossible : "
                                    + "contrainte étrangère";
                    default ->
                            "Erreur SQL dans delete() (code="
                                    + code + ")";
                };

                LOG.severe(message + " | "
                        + e.getMessage());

                throw new SQLException(message, e);
            }

        } finally {
            try (Connection conn =
                         ConnexionManager.getConnection()) {
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
    private Contrat mapResultSet(final ResultSet rs)
            throws SQLException {

        final Contrat c = new Contrat();

        c.setIdentifiant(rs.getInt("identifiant"));
        c.setIdClient(rs.getInt("id_client"));
        c.setNomContrat(rs.getString("nom_contrat"));
        c.setMontant(rs.getDouble("montant"));

        return c;
    }
}
