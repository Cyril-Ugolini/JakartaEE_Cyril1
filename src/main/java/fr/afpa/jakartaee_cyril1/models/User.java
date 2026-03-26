package fr.afpa.jakartaee_cyril1.models;

/**
 * Représente un utilisateur de l'application.
 *
 * @author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public class User {

    /** Identifiant unique de l'utilisateur. */
    private Integer id;

    /** Nom d'utilisateur (login). */
    private String username;

    /** Mot de passe hashé (Argon2). */
    private String passwordHash;

    /** Constructeur par défaut. */
    public User() { }

    /**
     * Constructeur complet.
     *
     * @param id           identifiant unique
     * @param username     nom d'utilisateur
     * @param passwordHash mot de passe hashé
     */
    public User(final Integer id,
                final String username,
                final String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Integer getId() { return id; }
    public void setId(final Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(final String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(final String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
