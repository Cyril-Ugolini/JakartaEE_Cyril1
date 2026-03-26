package fr.afpa.jakartaee_cyril1.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.afpa.jakartaee_cyril1.models.Adresse;

/**
 * DAO pour la gestion des adresses en base de données.
 *
 * <p>Cette classe permet d'effectuer les opérations CRUD sur la table
 * {@code adresse}. Elle intègre une gestion d'erreurs SQL basée sur les
 * codes d'erreurs MySQL afin de fournir des messages techniques précis
 * dans les logs, sans exposer ces détails à l'utilisateur.</p>
 *
 * @author Cyril
 * @version 2.0
 */
public final class AdresseDao {

    /** Logger du DAO. */
    private static final Logger LOG =
            Logger.getLogger(AdresseDao.class.getName());

    /** Connexion active (obtenue à chaque opération). */
    private Connection connection;

    /**
     * Constructeur : initialise une connexion depuis le pool.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    public AdresseDao() throws SQLException {
        this.connection = ConnexionManager.getConnection();
        LOG.info("AdresseDao initialisé.");
    }

    // ============================================================
    // FIND ALL
    // ============================================================

    /**
     * Retourne toutes les adresses présentes en base.
     *
     * @return liste d'adresses
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Adresse> findAll() throws SQLException {

        final List<Adresse> liste = new ArrayList<>();
        final String sql =
                "SELECT id_adresse, numero_rue, nom_rue, code_postal, ville "
                        + "FROM adresse";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }

            LOG.info("findAll() : " + liste.size() + " adresse(s).");

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

    // ============================================================
    // FIND BY ID
    // ============================================================
    /**
     * Retourne une adresse par son identifiant.
     *
     * @param id identifiant de l'adresse
     * @return adresse ou {@code null} si non trouvée
     * @throws SQLException en cas d'erreur SQL
     */
    public Adresse findById(final int id) throws SQLException {

        final String sql =
                "SELECT id_adresse, numero_rue, nom_rue, code_postal, ville "
                        + "FROM adresse WHERE id_adresse = ?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // ============================================================
    // CREATE
    // ============================================================
/**
 * Crée une nouvelle adresse en base.
 *
 * @param a adresse à créer
 * @return {@code true} si la création a réussi
 * @throws SQLException en cas d'erreur SQL
 */
public boolean create(final Adresse a) throws SQLException {

    final String sql =
            "INSERT INTO adresse (numero_rue, nom_rue, code_postal, ville) "
                    + "VALUES (?, ?, ?, ?)";

    try (Connection conn = ConnexionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
                 sql, Statement.RETURN_GENERATED_KEYS)) {

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

        return false; // ← placé au bon endroit

    } catch (SQLException e) {

        final int code = e.getErrorCode();
        final String message = switch (code) {
            case 1048 -> "Champ obligatoire manquant lors de la création "
                    + "(code=1048)";
            case 1062 -> "Doublon détecté lors de la création "
                    + "(code=1062)";
            default -> "Erreur SQL dans create() (code=" + code + ")";
        };

        LOG.severe(message + " | " + e.getMessage());
        throw new SQLException(message, e);
    }
}

    // ============================================================
    // UPDATE
    // ============================================================

/**
 * Met à jour une adresse existante.
 *
 * @param a adresse à mettre à jour
 * @return {@code true} si la mise à jour a réussi
 * @throws SQLException en cas d'erreur SQL
 */
public boolean update(final Adresse a) throws SQLException {

    final String sql =
            "UPDATE adresse SET numero_rue=?, nom_rue=?, code_postal=?, "
                    + "ville=? WHERE id_adresse=?";

    try (Connection conn = ConnexionManager.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        final int code = e.getErrorCode();
        final String message = switch (code) {
            case 1048 -> "Champ obligatoire manquant lors de la mise à "
                    + "jour (code=1048)";
            case 1062 -> "Doublon détecté lors de la mise à jour "
                    + "(code=1062)";
            default -> "Erreur SQL dans update() (code=" + code + ")";
        };

        LOG.severe(message + " | " + e.getMessage());
        throw new SQLException(message, e);
    }
}

    // ============================================================
    // DELETE
    // ============================================================

/**
 * Supprime une adresse par son identifiant.
 *
 * @param id identifiant de l'adresse
 * @return {@code true} si la suppression a réussi
 * @throws SQLException en cas d'erreur SQL
 */
public boolean delete(final int id) throws SQLException {

    final String sql = "DELETE FROM adresse WHERE id_adresse=?";

    try (Connection conn = ConnexionManager.getConnection()) {

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
        // (la connexion retourne au pool dans un état propre)
        try (Connection conn = ConnexionManager.getConnection()) {
            conn.setAutoCommit(true);
        }
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
