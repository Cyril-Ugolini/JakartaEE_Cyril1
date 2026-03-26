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
 * <p>Permet d'effectuer les opérations CRUD sur la table
 * {@code adresse}. Gère les erreurs SQL via les codes MySQL
 * afin de fournir des logs précis sans exposer ces détails
 * à l'utilisateur.</p>
 */
public final class AdresseDao {

    /** Logger du DAO. */
    private static final Logger LOG =
            Logger.getLogger(AdresseDao.class.getName());

    // ============================================================
    // CONSTRUCTEUR
    // ============================================================

    /**
     * Constructeur.
     *
     * @throws SQLException si la connexion échoue
     */
    public AdresseDao() throws SQLException {
        LOG.info("AdresseDao initialisé.");
    }

    // ============================================================
    // FIND ALL
    // ============================================================

    /**
     * Retourne toutes les adresses.
     *
     * @return liste d'adresses
     * @throws SQLException erreur SQL
     */
    public List<Adresse> findAll() throws SQLException {

        final List<Adresse> liste = new ArrayList<>();

        final String sql =
                "SELECT id_adresse, numero_rue, nom_rue, "
                        + "code_postal, ville "
                        + "FROM adresse";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }

            LOG.info("findAll() : " + liste.size()
                    + " adresse(s).");

        } catch (SQLException e) {

            LOG.severe("Erreur findAll() : "
                    + e.getMessage());
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
     * @param id identifiant
     * @return adresse ou null
     * @throws SQLException erreur SQL
     */
    public Adresse findById(final int id) throws SQLException {

        final String sql =
                "SELECT id_adresse, numero_rue, nom_rue, "
                        + "code_postal, ville "
                        + "FROM adresse WHERE id_adresse = ?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    LOG.info("findById(" + id
                            + ") : trouvé.");
                    return mapResultSet(rs);
                }
            }

        } catch (SQLException e) {

            LOG.severe("Erreur findById() : "
                    + e.getMessage());
            throw e;
        }

        LOG.warning("findById(" + id
                + ") : non trouvé.");
        return null;
    }

    // ============================================================
    // CREATE
    // ============================================================

    /**
     * Crée une adresse.
     *
     * @param adresse adresse à créer
     * @return id généré
     * @throws SQLException erreur SQL
     */
    public int create(final Adresse adresse)
            throws SQLException {

        final String sql =
                "INSERT INTO adresse (numero_rue, nom_rue, "
                        + "code_postal, ville) "
                        + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, adresse.getNumeroRue());
            stmt.setString(2, adresse.getNomRue());
            stmt.setString(3, adresse.getCodePostal());
            stmt.setString(4, adresse.getVille());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    final int id = keys.getInt(1);
                    LOG.info("Adresse créée : ID=" + id);
                    return id;
                }
            }

        } catch (SQLException e) {

            LOG.severe("Erreur create() : "
                    + e.getMessage());
            throw e;
        }

        throw new SQLException(
                "Impossible de récupérer l'ID généré.");
    }

    // ============================================================
    // UPDATE
    // ============================================================

    /**
     * Met à jour une adresse.
     *
     * @param adresse adresse à modifier
     * @return true si OK
     * @throws SQLException erreur SQL
     */
    public boolean update(final Adresse adresse)
            throws SQLException {

        final String sql =
                "UPDATE adresse SET numero_rue=?, nom_rue=?, "
                        + "code_postal=?, ville=? "
                        + "WHERE id_adresse=?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setString(1, adresse.getNumeroRue());
            stmt.setString(2, adresse.getNomRue());
            stmt.setString(3, adresse.getCodePostal());
            stmt.setString(4, adresse.getVille());
            stmt.setInt(5, adresse.getIdAdresse());

            final int rows = stmt.executeUpdate();

            LOG.info("Adresse modifiée : ID="
                    + adresse.getIdAdresse());

            return rows > 0;

        } catch (SQLException e) {

            LOG.severe("Erreur update() : "
                    + e.getMessage());
            throw e;
        }
    }

    // ============================================================
    // DELETE
    // ============================================================

    /**
     * Supprime une adresse.
     *
     * @param id identifiant
     * @return true si OK
     * @throws SQLException erreur SQL
     */
    public boolean delete(final int id)
            throws SQLException {

        final String sql =
                "DELETE FROM adresse WHERE id_adresse=?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            final int rows = stmt.executeUpdate();

            LOG.info("Adresse supprimée : ID=" + id);

            return rows > 0;

        } catch (SQLException e) {

            LOG.severe("Erreur delete() : "
                    + e.getMessage());
            throw e;
        }
    }

    // ============================================================
    // MAPPING
    // ============================================================

    /**
     * Convertit un ResultSet en objet Adresse.
     *
     * @param rs résultat SQL
     * @return adresse
     * @throws SQLException erreur SQL
     */
    private Adresse mapResultSet(final ResultSet rs)
            throws SQLException {

        final Adresse a = new Adresse();

        a.setIdAdresse(rs.getInt("id_adresse"));
        a.setNumeroRue(rs.getString("numero_rue"));
        a.setNomRue(rs.getString("nom_rue"));
        a.setCodePostal(rs.getString("code_postal"));
        a.setVille(rs.getString("ville"));

        return a;
    }
}
