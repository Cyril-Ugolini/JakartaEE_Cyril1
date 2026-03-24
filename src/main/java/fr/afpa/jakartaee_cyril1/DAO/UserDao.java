package fr.afpa.jakartaee_cyril1.DAO;

import models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * DAO pour la gestion des utilisateurs.
 *
 * @author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public class UserDao {

    /** Logger du DAO User. */
    private static final Logger LOG =
            Logger.getLogger(UserDao.class.getName());

    /** Code erreur SQL : doublon (UNIQUE). */
    private static final int ERR_DUPLICATE = 1062;

    /** Code erreur SQL : champ NOT NULL manquant. */
    private static final int ERR_NOT_NULL = 1048;

    /** URL de connexion JDBC. */
    private static final String URL =
            "jdbc:mysql://localhost:3306/clients_prospects";

    /** Nom d'utilisateur MySQL. */
    private static final String DB_USER = "root";

    /** Mot de passe MySQL. */
    private static final String PASSWORD = "";

    // Création de la table une seule fois au chargement de la classe
    static {
        final String sql = "CREATE TABLE IF NOT EXISTS user ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "username VARCHAR(100) UNIQUE NOT NULL, "
                + "password_hash VARCHAR(255) NOT NULL"
                + ")";

        try (Connection conn = DriverManager.getConnection(
                URL, DB_USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.executeUpdate();
            LOG.info("Table 'user' vérifiée/créée.");

        } catch (SQLException e) {
            LOG.severe("Erreur création table : " + e.getMessage());
            throw new RuntimeException(
                    "Impossible d'initialiser la table user", e);
        }
    }

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

        try (Connection conn = DriverManager.getConnection(
                URL, DB_USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.executeUpdate();

            LOG.info("Utilisateur inséré : " + user.getUsername());

        } catch (SQLException e) {

            int code = e.getErrorCode();
            String message;

            switch (code) {
                case ERR_DUPLICATE:
                    message = "Erreur SQL 1062 : nom d'utilisateur déjà existant.";
                    LOG.warning(message);
                    break;
                case ERR_NOT_NULL:
                    message = "Erreur SQL 1048 : champ obligatoire manquant.";
                    LOG.warning(message);
                    break;
                default:
                    message = "Erreur SQL inconnue (code " + code + ").";
                    LOG.severe(message + " - " + e.getMessage());
                    break;
            }

            throw new RuntimeException(message, e);
        }
    }

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

        try (Connection conn = DriverManager.getConnection(
                URL, DB_USER, PASSWORD);
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
            LOG.severe("Erreur SQL recherche (code "
                    + e.getErrorCode() + ") : " + e.getMessage());
            throw new RuntimeException(
                    "Erreur SQL lors de la recherche (code "
                            + e.getErrorCode() + ")", e);
        }

        return null;
    }
}
