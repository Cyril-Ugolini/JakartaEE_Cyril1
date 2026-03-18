package fr.afpa.jakartaee_cyril1.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Gestionnaire de connexion à la base de données MySQL.
 *
 * <p>Implémente le pattern Singleton pour garantir
 * une seule instance de connexion.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ConnexionManager {

    /** Logger du ConnexionManager. */
    private static final Logger LOG =
            Logger.getLogger(ConnexionManager.class.getName());

    /** URL de connexion à la base de données. */
    private static final String URL =
            "jdbc:mysql://127.0.0.1:3306/clients_prospects"
                    + "?useSSL=false&serverTimezone=Europe/Paris"
                    + "&characterEncoding=UTF-8";

    /** Utilisateur de la base de données. */
    private static final String USER = "root";

    /** Mot de passe de la base de données. */
    private static final String PASSWORD = "";

    /** Instance unique du ConnexionManager. */
    private static ConnexionManager instance;

    /** Connexion à la base de données. */
    private Connection connection;

    /**
     * Constructeur privé — initialise la connexion.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    private ConnexionManager() throws SQLException {
        LOG.info("Connexion à la base de données...");
        this.connection = DriverManager.getConnection(
                URL, USER, PASSWORD
        );
        LOG.info("Connexion établie avec succès.");
    }

    /**
     * Retourne l'instance unique du ConnexionManager.
     *
     * @return instance du ConnexionManager
     * @throws SQLException en cas d'erreur de connexion
     */
    public static ConnexionManager getInstance() throws SQLException {
        if (instance == null
                || instance.getConnection().isClosed()) {
            instance = new ConnexionManager();
        }
        return instance;
    }

    /**
     * Retourne la connexion active.
     *
     * @return connexion SQL
     */
    public Connection getConnection() {
        return connection;
    }
}
