package fr.afpa.jakartaee_cyril1.DAO;

import fr.afpa.jakartaee_cyril1.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * DAO pour la gestion des utilisateurs.
 *
 * <p>Version refactorisée : utilise la DataSource JNDI via
 * {@link ConnexionManager} au lieu de DriverManager.</p>
 *
 * @author UGOLINI Cyril
 * @version 0.0.3
 * @since 24/03/2026
 */
public final class UserDao {

    /** Logger du DAO User. */
    private static final Logger LOG =
            Logger.getLogger(UserDao.class.getName());

    // ============================================================
    // CONSTANTES ERREURS SQL
    // ============================================================

    /** Code erreur SQL : doublon (UNIQUE). */
    private static final int ERR_DUPLICATE = 1062;

    /** Code erreur SQL : champ NOT NULL manquant. */
    private static final int ERR_NOT_NULL = 1048;

    // ============================================================
    // INITIALISATION TABLE
    // ============================================================

    static {
        final String sql =
                "CREATE TABLE IF NOT EXISTS user ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT, "
                        + "username VARCHAR(100) UNIQUE NOT NULL, "
                        + "password_hash VARCHAR(255) NOT NULL"
                        + ")";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();
            LOG.info("Table 'user' vérifiée/créée.");

        } catch (SQLException e) {
            LOG.severe("Erreur création table : " + e.getMessage());
            throw new RuntimeException(
                    "Impossible d'initialiser la table user", e);
        }
    }

    // ============================================================
    // CREATE
    // ============================================================

    /**
     * Insère un utilisateur dans la base de données.
     *
     * @param user utilisateur à insérer
     */
    public void create(final User user) {

        LOG.info("Tentative d'insertion : " + user.getUsername());

        final String sql =
                "INSERT INTO user (username, password_hash) "
                        + "VALUES (?, ?)";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.executeUpdate();

            LOG.info("Utilisateur inséré : " + user.getUsername());

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case ERR_DUPLICATE ->
                        "Erreur SQL 1062 : nom d'utilisateur déjà existant.";
                case ERR_NOT_NULL ->
                        "Erreur SQL 1048 : champ obligatoire manquant.";
                default ->
                        "Erreur SQL inconnue (code " + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new RuntimeException(message, e);
        }
    }

    // ============================================================
    // FIND BY USERNAME
    // ============================================================

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     *
     * @param username nom d'utilisateur
     * @return User ou null si non trouvé
     */
    public User findByUsername(final String username) {

        LOG.info("Recherche de l'utilisateur : " + username);

        final String sql =
                "SELECT id, username, password_hash "
                        + "FROM user WHERE username = ?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LOG.info("Utilisateur trouvé : " + username);
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"));
                }
                LOG.warning("Aucun utilisateur trouvé pour : " + username);
            }

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            LOG.severe("Erreur SQL recherche (code "
                    + code + ") : " + e.getMessage());

            throw new RuntimeException(
                    "Erreur SQL lors de la recherche (code "
                            + code + ")", e);
        }

        return null;
    }
}
