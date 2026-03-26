package fr.afpa.jakartaee_cyril1.security;

/**
 * Configuration de sécurité de l'application.
 *
 * <p>Contient le "poivre" (pepper), une valeur secrète ajoutée
 * au mot de passe avant le hachage Argon2. Contrairement au sel,
 * généré automatiquement et stocké dans le hash, le poivre n'est
 * jamais enregistré en base de données.</p>
 *
 * <p>Le poivre renforce la sécurité en rendant les attaques par
 * dictionnaire ou rainbow tables beaucoup plus difficiles, même
 * en cas de fuite de la base de données.</p>
 *
 * <p>Cette valeur doit rester secrète et ne jamais être exposée
 * dans l'interface utilisateur ou dans la base.</p>
 *
 * @author UGOLINI Cyril
 * @version 1.0
 */
public final class SecurityConfig {

    /** Poivre secret ajouté au mot de passe avant hachage. */
    public static final String PEPPER = "X9f!kL#92@p";

    /** Constructeur privé pour empêcher l'instanciation. */
    private SecurityConfig() {
        // Classe utilitaire : pas d'instance
    }
}
