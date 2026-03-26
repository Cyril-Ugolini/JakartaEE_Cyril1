package fr.afpa.jakartaee_cyril1.models;

/**
 * Représente un utilisateur de l'application.
 *
 * <p>Contient l'identifiant, le nom d'utilisateur et le mot de
 * passe hashé (Argon2).</p>
 *
 * <p>Utilisé pour l'authentification et la gestion des comptes.</p>
 *
 * author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public final class User {

    /** Identifiant unique de l'utilisateur. */
    private Integer id;

    /** Nom d'utilisateur (login). */
    private String username;

    /** Mot de passe hashé (Argon2). */
    private String passwordHash;

    // ============================================================
    // CONSTRUCTEURS
    // ============================================================

    /** Constructeur par défaut. */
    public User() {
        // Nécessaire pour le mapping JDBC
    }

    /**
     * Constructeur complet.
     *
     * @param newId identifiant unique
     * @param newUsername nom d'utilisateur
     * @param newPasswordHash mot de passe hashé
     */
    public User(final Integer newId,
                final String newUsername,
                final String newPasswordHash) {

        this.id = newId;
        this.username = newUsername;
        this.passwordHash = newPasswordHash;
    }

    // ============================================================
    // GETTERS / SETTERS
    // ============================================================

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return identifiant
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     *
     * @param newId nouvel identifiant
     */
    public void setId(final Integer newId) {
        this.id = newId;
    }

    /**
     * Retourne le nom d'utilisateur.
     *
     * @return nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Définit le nom d'utilisateur.
     *
     * @param newUsername nouveau nom d'utilisateur
     */
    public void setUsername(final String newUsername) {
        this.username = newUsername;
    }

    /**
     * Retourne le mot de passe hashé.
     *
     * @return hash du mot de passe
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Définit le mot de passe hashé.
     *
     * @param newPasswordHash nouveau hash
     */
    public void setPasswordHash(final String newPasswordHash) {
        this.passwordHash = newPasswordHash;
    }
}
