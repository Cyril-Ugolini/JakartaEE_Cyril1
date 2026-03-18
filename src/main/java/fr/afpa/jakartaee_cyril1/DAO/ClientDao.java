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
import models.Client;

/**
 * DAO pour la gestion des clients en base de données.
 *
 * @author Cyril
 * @version 1.0
 */
public final class ClientDao {

    /** Logger du ClientDao. */
    private static final Logger LOG =
            Logger.getLogger(ClientDao.class.getName());

    /** Gestionnaire de connexion. */
    private final fr.afpa.jakartaee_cyril1.DAO.ConnexionManager db;

    /**
     * Constructeur : initialise la connexion.
     *
     * @throws SQLException en cas d'erreur de connexion
     */
    public ClientDao() throws SQLException {
        this.db = fr.afpa.jakartaee_cyril1.DAO.ConnexionManager.getInstance();
        LOG.info("ClientDao initialisé.");
    }

    /**
     * Retourne tous les clients.
     *
     * @return liste de clients
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Client> findAll() throws SQLException {
        final List<Client> liste = new ArrayList<>();
        final String sql =
                "SELECT c.id_client, c.raison_sociale,"
                        + " c.telephone, c.adresse_mail,"
                        + " c.commentaires, c.chiffre_affaires,"
                        + " c.nombre_employes,"
                        + " a.id_adresse, a.numero_rue,"
                        + " a.nom_rue, a.code_postal, a.ville"
                        + " FROM client c"
                        + " INNER JOIN adresse a"
                        + " ON c.id_adresse = a.id_adresse";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }
            LOG.info("findAll() : " + liste.size()
                    + " client(s).");
        } catch (SQLException e) {
            LOG.severe("Erreur findAll() : " + e.getMessage());
            throw e;
        }
        return liste;
    }

    /**
     * Retourne un client par son identifiant.
     *
     * @param id identifiant du client
     * @return client ou null si non trouvé
     * @throws SQLException en cas d'erreur SQL
     */
    public Client findById(final int id) throws SQLException {
        final String sql =
                "SELECT c.id_client, c.raison_sociale,"
                        + " c.telephone, c.adresse_mail,"
                        + " c.commentaires, c.chiffre_affaires,"
                        + " c.nombre_employes,"
                        + " a.id_adresse, a.numero_rue,"
                        + " a.nom_rue, a.code_postal, a.ville"
                        + " FROM client c"
                        + " INNER JOIN adresse a"
                        + " ON c.id_adresse = a.id_adresse"
                        + " WHERE c.id_client = ?";

        try (PreparedStatement stmt =
                     db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LOG.info("findById(" + id + ") : trouvé.");
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            LOG.severe("Erreur findById() : " + e.getMessage());
            throw e;
        }
        LOG.warning("findById(" + id + ") : non trouvé.");
        return null;
    }

    /**
     * Crée un nouveau client en base de données.
     *
     * @param client client à créer
     * @return true si la création a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean create(final Client client) throws SQLException {
        final Connection conn = db.getConnection();
        try {
            conn.setAutoCommit(false);

            // 1. Créer l'adresse
            final int idAdresse = creerAdresse(
                    conn, client.getAdresse()
            );
            client.getAdresse().setIdAdresse(idAdresse);

            // 2. Créer le client
            final String sql =
                    "INSERT INTO client (raison_sociale,"
                            + " id_adresse, telephone, adresse_mail,"
                            + " commentaires, chiffre_affaires,"
                            + " nombre_employes)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt =
                         conn.prepareStatement(
                                 sql, Statement.RETURN_GENERATED_KEYS
                         )) {
                stmt.setString(1, client.getRaisonSociale());
                stmt.setInt(2, idAdresse);
                stmt.setString(3, client.getTelephone());
                stmt.setString(4, client.getAdresseMail());
                stmt.setString(5, client.getCommentaires());
                stmt.setLong(6, client.getChiffreAffaires());
                stmt.setInt(7, client.getNombreEmployes());

                final int rows = stmt.executeUpdate();
                if (rows > 0) {
                    try (ResultSet keys =
                                 stmt.getGeneratedKeys()) {
                        if (keys.next()) {
                            client.setIdClient(keys.getInt(1));
                        }
                    }
                    conn.commit();
                    LOG.info("Client créé : ID="
                            + client.getIdClient());
                    return true;
                }
            }
            conn.commit();
            return false;

        } catch (SQLException e) {
            conn.rollback();
            LOG.severe("Erreur create() : " + e.getMessage());
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * Met à jour un client existant.
     *
     * @param client client à mettre à jour
     * @return true si la mise à jour a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean update(final Client client) throws SQLException {
        final Connection conn = db.getConnection();
        try {
            conn.setAutoCommit(false);

            // 1. Mettre à jour l'adresse
            final String sqlAdr =
                    "UPDATE adresse SET numero_rue=?,"
                            + " nom_rue=?, code_postal=?, ville=?"
                            + " WHERE id_adresse=?";
            try (PreparedStatement stmt =
                         conn.prepareStatement(sqlAdr)) {
                stmt.setString(
                        1, client.getAdresse().getNumeroRue());
                stmt.setString(
                        2, client.getAdresse().getNomRue());
                stmt.setString(
                        3, client.getAdresse().getCodePostal());
                stmt.setString(
                        4, client.getAdresse().getVille());
                stmt.setInt(
                        5, client.getAdresse().getIdAdresse());
                stmt.executeUpdate();
            }

            // 2. Mettre à jour le client
            final String sql =
                    "UPDATE client SET raison_sociale=?,"
                            + " telephone=?, adresse_mail=?,"
                            + " commentaires=?, chiffre_affaires=?,"
                            + " nombre_employes=?"
                            + " WHERE id_client=?";
            try (PreparedStatement stmt =
                         conn.prepareStatement(sql)) {
                stmt.setString(1, client.getRaisonSociale());
                stmt.setString(2, client.getTelephone());
                stmt.setString(3, client.getAdresseMail());
                stmt.setString(4, client.getCommentaires());
                stmt.setLong(5, client.getChiffreAffaires());
                stmt.setInt(6, client.getNombreEmployes());
                stmt.setInt(7, client.getIdClient());
                final int rows = stmt.executeUpdate();
                conn.commit();
                LOG.info("Client modifié : ID="
                        + client.getIdClient());
                return rows > 0;
            }

        } catch (SQLException e) {
            conn.rollback();
            LOG.severe("Erreur update() : " + e.getMessage());
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * Supprime un client par son identifiant.
     *
     * @param id identifiant du client
     * @return true si la suppression a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean delete(final int id) throws SQLException {
        final Connection conn = db.getConnection();
        try {
            conn.setAutoCommit(false);

            final Client client = findById(id);
            if (client == null) {
                LOG.warning("delete() : client ID="
                        + id + " non trouvé.");
                return false;
            }

            final int idAdresse =
                    client.getAdresse().getIdAdresse();

            // 1. Supprimer le client
            final String sqlClient =
                    "DELETE FROM client WHERE id_client=?";
            try (PreparedStatement stmt =
                         conn.prepareStatement(sqlClient)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // 2. Supprimer l'adresse
            final String sqlAdr =
                    "DELETE FROM adresse WHERE id_adresse=?";
            try (PreparedStatement stmt =
                         conn.prepareStatement(sqlAdr)) {
                stmt.setInt(1, idAdresse);
                stmt.executeUpdate();
            }

            conn.commit();
            LOG.info("Client supprimé : ID=" + id);
            return true;

        } catch (SQLException e) {
            conn.rollback();
            LOG.severe("Erreur delete() : " + e.getMessage());
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    /**
     * Crée une adresse en base et retourne son identifiant.
     *
     * @param conn    connexion SQL
     * @param adresse adresse à créer
     * @return identifiant généré
     * @throws SQLException en cas d'erreur SQL
     */
    private int creerAdresse(
            final Connection conn,
            final Adresse adresse) throws SQLException {
        final String sql =
                "INSERT INTO adresse"
                        + " (numero_rue, nom_rue, code_postal, ville)"
                        + " VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt =
                     conn.prepareStatement(
                             sql, Statement.RETURN_GENERATED_KEYS
                     )) {
            stmt.setString(1, adresse.getNumeroRue());
            stmt.setString(2, adresse.getNomRue());
            stmt.setString(3, adresse.getCodePostal());
            stmt.setString(4, adresse.getVille());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }
        throw new SQLException(
                "Impossible de récupérer l'ID adresse."
        );
    }

    /**
     * Construit un objet Client depuis un ResultSet.
     *
     * @param rs résultat SQL
     * @return client construit
     * @throws SQLException en cas d'erreur SQL
     */
    private Client mapResultSet(
            final ResultSet rs) throws SQLException {
        final Adresse adresse = new Adresse();
        adresse.setIdAdresse(rs.getInt("id_adresse"));
        adresse.setNumeroRue(rs.getString("numero_rue"));
        adresse.setNomRue(rs.getString("nom_rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));

        final Client client = new Client();
        client.setIdClient(rs.getInt("id_client"));
        client.setRaisonSociale(rs.getString("raison_sociale"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresseMail(rs.getString("adresse_mail"));
        client.setCommentaires(rs.getString("commentaires"));
        client.setChiffreAffaires(
                rs.getLong("chiffre_affaires"));
        client.setNombreEmployes(
                rs.getInt("nombre_employes"));
        client.setAdresse(adresse);
        return client;
    }
}
