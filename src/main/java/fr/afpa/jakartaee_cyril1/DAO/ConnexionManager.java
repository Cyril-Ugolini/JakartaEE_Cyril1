package fr.afpa.jakartaee_cyril1.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Gestionnaire de connexion à la base de données MySQL.
 *
 * <p>Utilise une DataSource JNDI configurée dans Tomcat
 * pour obtenir les connexions depuis un pool.</p>
 *
 * @author Cyril
 * @version 1.0
 */
public final class ConnexionManager {

    /** Logger du ConnexionManager. */
    private static final Logger LOG =
            Logger.getLogger(ConnexionManager.class.getName());

    /** Nom JNDI de la DataSource. */
    private static final String JNDI_NAME =
            "java:comp/env/jdbc/clientsProspects";

    /** Instance unique du ConnexionManager. */
    private static ConnexionManager instance;

    /** Connexion à la base de données. */
    private Connection connection;

    /**
     * Constructeur privé — obtient une connexion via la DataSource JNDI.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    private ConnexionManager() throws SQLException {
        LOG.info("Connexion via DataSource JNDI...");
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/clientsProspects");
            this.connection = ds.getConnection();
            LOG.info("Connexion établie avec succès.");
        } catch (NamingException e) {
            LOG.severe("Erreur JNDI : " + e.getMessage());
            throw new SQLException("Erreur DataSource JNDI", e);
        } catch (SQLException e) {
            LOG.severe("ERREUR CONNEXION : " + e.getMessage());
            throw e;
        }
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
