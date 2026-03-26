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
import fr.afpa.jakartaee_cyril1.models.Interesse;
import fr.afpa.jakartaee_cyril1.models.Prospect;

/**
 * DAO pour la gestion des prospects en base de données.
 *
 * <p>Cette classe permet d'effectuer les opérations CRUD sur la table
 * {@code prospect}. Elle intègre une gestion d'erreurs SQL basée sur les
 * codes d'erreurs MySQL afin de fournir des messages techniques précis
 * dans les logs, sans exposer ces détails à l'utilisateur.</p>
 *
 * @author Cyril
 * @version 2.0
 */
public final class ProspectDao {

    /** Logger du DAO. */
    private static final Logger LOG =
            Logger.getLogger(ProspectDao.class.getName());

    public ProspectDao() {
        LOG.info("ProspectDao initialisé.");
    }


    // ============================================================
// FIND ALL
// ============================================================

    /**
     * Retourne tous les prospects présents en base.
     *
     * @return liste de prospects
     * @throws SQLException en cas d'erreur SQL
     */
    public List<Prospect> findAll() throws SQLException {

        final List<Prospect> liste = new ArrayList<>();

        final String sql =
                "SELECT p.id_prospect, p.raison_sociale, p.telephone, "
                        + "p.adresse_mail, p.commentaires, p.date_prospection, "
                        + "p.interesse, a.id_adresse, a.numero_rue, a.nom_rue, "
                        + "a.code_postal, a.ville "
                        + "FROM prospect p "
                        + "INNER JOIN adresse a ON p.id_adresse = a.id_adresse";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                liste.add(mapResultSet(rs));
            }

            LOG.info("findAll() : " + liste.size() + " prospect(s).");

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1064 -> "Erreur SQL dans findAll() : syntaxe incorrecte (code=1064)";
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
     * Retourne un prospect par son identifiant.
     *
     * @param id identifiant du prospect
     * @return prospect ou {@code null} si non trouvé
     * @throws SQLException en cas d'erreur SQL
     */
    public Prospect findById(final int id) throws SQLException {

        final String sql =
                "SELECT p.id_prospect, p.raison_sociale, p.telephone, "
                        + "p.adresse_mail, p.commentaires, p.date_prospection, "
                        + "p.interesse, a.id_adresse, a.numero_rue, a.nom_rue, "
                        + "a.code_postal, a.ville "
                        + "FROM prospect p "
                        + "INNER JOIN adresse a ON p.id_adresse = a.id_adresse "
                        + "WHERE p.id_prospect = ?";

        try (Connection conn = ConnexionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LOG.info("findById(" + id + ") : trouvé.");
                    return mapResultSet(rs);
                }
            }

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

        LOG.warning("findById(" + id + ") : non trouvé.");
        return null;
    }
    // ============================================================
// CREATE
// ============================================================

    /**
     * Crée un nouveau prospect en base.
     *
     * @param prospect prospect à créer
     * @return {@code true} si la création a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean create(final Prospect prospect) throws SQLException {

        try (Connection conn = ConnexionManager.getConnection()) {

            conn.setAutoCommit(false);

            // 1. Création de l'adresse
            final int idAdresse = creerAdresse(conn, prospect.getAdresse());
            prospect.getAdresse().setIdAdresse(idAdresse);

            // 2. Création du prospect
            final String sql =
                    "INSERT INTO prospect (raison_sociale, id_adresse, "
                            + "telephone, adresse_mail, commentaires, "
                            + "date_prospection, interesse) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, prospect.getRaisonSociale());
                stmt.setInt(2, idAdresse);
                stmt.setString(3, prospect.getTelephone());
                stmt.setString(4, prospect.getAdresseMail());
                stmt.setString(5, prospect.getCommentaires());
                stmt.setDate(6, java.sql.Date.valueOf(prospect.getDateProspection()));
                stmt.setInt(7, prospect.getInteresse() == Interesse.OUI ? 1 : 0);

                final int rows = stmt.executeUpdate();

                if (rows > 0) {
                    try (ResultSet keys = stmt.getGeneratedKeys()) {
                        if (keys.next()) {
                            prospect.setIdProspect(keys.getInt(1));
                        }
                    }
                    conn.commit();
                    LOG.info("Prospect créé : ID=" + prospect.getIdProspect());
                    return true;
                }
            }

            conn.commit();
            return false;

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1048 -> "Champ obligatoire manquant lors de la création "
                        + "du prospect (code=1048)";
                case 1452 -> "Contrainte étrangère violée lors de la création "
                        + "du prospect (code=1452)";
                case 1062 -> "Doublon détecté lors de la création du prospect "
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
     * Met à jour un prospect existant.
     *
     * @param prospect prospect à mettre à jour
     * @return {@code true} si la mise à jour a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean update(final Prospect prospect) throws SQLException {

        try (Connection conn = ConnexionManager.getConnection()) {

            conn.setAutoCommit(false);

            // 1. Mise à jour de l'adresse
            final String sqlAdr =
                    "UPDATE adresse SET numero_rue=?, nom_rue=?, "
                            + "code_postal=?, ville=? WHERE id_adresse=?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlAdr)) {

                stmt.setString(1, prospect.getAdresse().getNumeroRue());
                stmt.setString(2, prospect.getAdresse().getNomRue());
                stmt.setString(3, prospect.getAdresse().getCodePostal());
                stmt.setString(4, prospect.getAdresse().getVille());
                stmt.setInt(5, prospect.getAdresse().getIdAdresse());

                stmt.executeUpdate();
            }

            // 2. Mise à jour du prospect
            final String sql =
                    "UPDATE prospect SET raison_sociale=?, telephone=?, "
                            + "adresse_mail=?, commentaires=?, date_prospection=?, "
                            + "interesse=? WHERE id_prospect=?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, prospect.getRaisonSociale());
                stmt.setString(2, prospect.getTelephone());
                stmt.setString(3, prospect.getAdresseMail());
                stmt.setString(4, prospect.getCommentaires());
                stmt.setDate(5, java.sql.Date.valueOf(prospect.getDateProspection()));
                stmt.setInt(6, prospect.getInteresse() == Interesse.OUI ? 1 : 0);
                stmt.setInt(7, prospect.getIdProspect());

                final int rows = stmt.executeUpdate();
                conn.commit();

                LOG.info("Prospect modifié : ID=" + prospect.getIdProspect());
                return rows > 0;
            }

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1048 -> "Champ obligatoire manquant lors de la mise à jour (code=1048)";
                case 1452 -> "Contrainte étrangère violée lors de la mise à jour (code=1452)";
                case 1062 -> "Doublon détecté lors de la mise à jour (code=1062)";
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
     * Supprime un prospect par son identifiant.
     *
     * @param id identifiant du prospect
     * @return {@code true} si la suppression a réussi
     * @throws SQLException en cas d'erreur SQL
     */
    public boolean delete(final int id) throws SQLException {

        try (Connection conn = ConnexionManager.getConnection()) {

            conn.setAutoCommit(false);

            final Prospect prospect = findById(id);

            if (prospect == null) {
                LOG.warning("delete() : prospect ID=" + id + " non trouvé.");
                return false;
            }

            final int idAdresse = prospect.getAdresse().getIdAdresse();

            // 1. Suppression du prospect
            final String sqlProspect =
                    "DELETE FROM prospect WHERE id_prospect=?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlProspect)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            // 2. Suppression de l'adresse
            final String sqlAdr =
                    "DELETE FROM adresse WHERE id_adresse=?";

            try (PreparedStatement stmt = conn.prepareStatement(sqlAdr)) {
                stmt.setInt(1, idAdresse);
                stmt.executeUpdate();
            }

            conn.commit();
            LOG.info("Prospect supprimé : ID=" + id);
            return true;

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1451 -> "Suppression impossible : contrainte étrangère (code=1451)";
                default -> "Erreur SQL dans delete() (code=" + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new SQLException(message, e);
        }
    }
    // ============================================================
    // CREER ADRESSE
    // ============================================================

    /**
     * Crée une adresse en base et retourne son identifiant.
     *
     * @param conn connexion SQL
     * @param adresse adresse à créer
     * @return identifiant généré
     * @throws SQLException en cas d'erreur SQL
     */
    private int creerAdresse(final Connection conn,
                             final Adresse adresse) throws SQLException {

        final String sql =
                "INSERT INTO adresse (numero_rue, nom_rue, code_postal, ville) "
                        + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt =
                     conn.prepareStatement(sql,
                             Statement.RETURN_GENERATED_KEYS)) {

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

        } catch (SQLException e) {

            final int code = e.getErrorCode();
            final String message = switch (code) {
                case 1048 -> "Champ obligatoire manquant lors de la création "
                        + "d'adresse (code=1048)";
                default -> "Erreur SQL dans creerAdresse() (code=" + code + ")";
            };

            LOG.severe(message + " | " + e.getMessage());
            throw new SQLException(message, e);
        }

        throw new SQLException("Impossible de récupérer l'ID adresse.");
    }

    // ============================================================
    // MAPPING
    // ============================================================

    /**
     * Construit un objet Prospect depuis un ResultSet.
     *
     * @param rs résultat SQL
     * @return prospect construit
     * @throws SQLException en cas d'erreur SQL
     */
    private Prospect mapResultSet(final ResultSet rs) throws SQLException {

        final Adresse adresse = new Adresse();
        adresse.setIdAdresse(rs.getInt("id_adresse"));
        adresse.setNumeroRue(rs.getString("numero_rue"));
        adresse.setNomRue(rs.getString("nom_rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));

        final Prospect prospect = new Prospect();
        prospect.setIdProspect(rs.getInt("id_prospect"));
        prospect.setRaisonSociale(rs.getString("raison_sociale"));
        prospect.setTelephone(rs.getString("telephone"));
        prospect.setAdresseMail(rs.getString("adresse_mail"));
        prospect.setCommentaires(rs.getString("commentaires"));
        prospect.setDateProspection(
                rs.getDate("date_prospection").toLocalDate());
        prospect.setInteresse(
                rs.getInt("interesse") == 1 ? Interesse.OUI : Interesse.NON);
        prospect.setAdresse(adresse);

        return prospect;
    }
}
