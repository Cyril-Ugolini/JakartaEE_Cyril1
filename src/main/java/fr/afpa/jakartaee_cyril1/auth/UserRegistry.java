package fr.afpa.jakartaee_cyril1.auth;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Registre global des utilisateurs actuellement connectés.
 *
 * <p>Stocké en mémoire (singleton), thread-safe.</p>
 */
public final class UserRegistry {

    /** Ensemble synchronisé des usernames connectés. */
    private static final Set<String> CONNECTED =
            Collections.synchronizedSet(new HashSet<>());

    /** Constructeur privé pour empêcher l'instanciation. */
    private UserRegistry() { }

    /**
     * Ajoute un utilisateur dans la liste des connectés.
     *
     * @param username nom d'utilisateur à ajouter
     */
    public static void add(final String username) {
        CONNECTED.add(username);
    }

    /**
     * Retire un utilisateur de la liste des connectés.
     *
     * @param username nom d'utilisateur à retirer
     */
    public static void remove(final String username) {
        CONNECTED.remove(username);
    }

    /**
     * Vérifie si un utilisateur est connecté.
     *
     * @param username nom d'utilisateur à vérifier
     * @return true si l'utilisateur est connecté, false sinon
     */
    public static boolean isConnected(final String username) {
        return CONNECTED.contains(username);
    }

    /**
     * Retourne la liste des utilisateurs connectés.
     *
     * @return ensemble non modifiable des utilisateurs connectés
     */
    public static Set<String> getAll() {
        return Collections.unmodifiableSet(CONNECTED);
    }
}
