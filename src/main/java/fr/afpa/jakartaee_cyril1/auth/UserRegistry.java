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

    private UserRegistry() { }

    /** Ajoute un utilisateur dans la liste des connectés. */
    public static void add(String username) {
        CONNECTED.add(username);
    }

    /** Retire un utilisateur de la liste des connectés. */
    public static void remove(String username) {
        CONNECTED.remove(username);
    }

    /** Vérifie si un utilisateur est connecté. */
    public static boolean isConnected(String username) {
        return CONNECTED.contains(username);
    }

    /** Retourne la liste des utilisateurs connectés. */
    public static Set<String> getAll() {
        return Collections.unmodifiableSet(CONNECTED);
    }
}
