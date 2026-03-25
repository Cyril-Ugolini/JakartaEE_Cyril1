import models.Interesse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests unitaires de l'énumération Interesse.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 24/03/2026
 */
public class TestInteresse {

    @Test
    void oui_toString_retourneMinuscules() {
        assertEquals("oui", Interesse.OUI.toString());
    }

    @Test
    void non_toString_retourneMinuscules() {
        assertEquals("non", Interesse.NON.toString());
    }

    @Test
    void oui_name_retourneMajuscules() {
        assertEquals("OUI", Interesse.OUI.name());
    }

    @Test
    void non_name_retourneMajuscules() {
        assertEquals("NON", Interesse.NON.name());
    }

    @Test
    void valueOf_oui_retourneEnum() {
        assertEquals(Interesse.OUI, Interesse.valueOf("OUI"));
    }

    @Test
    void valueOf_non_retourneEnum() {
        assertEquals(Interesse.NON, Interesse.valueOf("NON"));
    }

    @Test
    void values_contientDeuxValeurs() {
        assertEquals(2, Interesse.values().length);
    }

    @Test
    void toutes_valeurs_nonNulles() {
        for (Interesse i : Interesse.values()) {
            assertNotNull(i);
        }
    }
}
