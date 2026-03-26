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
 * <p>Version refactorisée : classe utilitaire statique,
 * sans singleton ni connexion persistante.</p>
 *
 * @author Cyril
 * @version 2.0
 */
public final class ConnexionManager {

    /** Logger du ConnexionManager. */
    private static final Logger LOG =
            Logger.getLogger(ConnexionManager.class.getName());

    /** Nom JNDI de la DataSource. */
    private static final String JNDI_NAME =
            "java:comp/env/jdbc/clientsProspects";

    /** DataSource résolue une seule fois. */
    private static DataSource dataSource;

    // ============================================================
    // INITIALISATION STATIQUE
    // ============================================================

    static {
        try {
            LOG.info("Initialisation de la DataSource JNDI...");
            final Context initCtx = new InitialContext();
            dataSource = (DataSource) initCtx.lookup(JNDI_NAME);
            LOG.info("DataSource initialisée avec succès.");
        } catch (NamingException e) {
            LOG.severe("Erreur JNDI : " + e.getMessage());
            throw new ExceptionInInitializerError(
                    "Impossible d'initialiser la DataSource : "
                            + e.getMessage());
        }
    }

    /** Constructeur privé — empêche l'instanciation. */
    private ConnexionManager() {
        // Classe utilitaire : pas d'instance
    }

    // ============================================================
    // OBTENTION DE CONNEXION
    // ============================================================

    /**
     * Retourne une nouvelle connexion depuis le pool.
     *
     * @return connexion SQL
     * @throws SQLException en cas d'erreur d'obtention de connexion
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
