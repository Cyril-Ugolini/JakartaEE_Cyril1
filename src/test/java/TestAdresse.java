import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import fr.afpa.jakartaee_cyril1.models.Adresse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires de la validation de l'entité Adresse.
 *
 * @author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public class TestAdresse {

    /** Validateur partagé entre tous les tests. */
    private static Validator validator;

    /** Initialise le validateur une seule fois. */
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /** Construit une adresse valide réutilisable. */
    private Adresse adresseValide() {
        Adresse a = new Adresse();
        a.setNumeroRue("12");
        a.setNomRue("Rue de la Paix");
        a.setCodePostal("75001");
        a.setVille("Paris");
        return a;
    }

    @Test
    void adresseValide_aucuneViolation() {
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(adresseValide());
        assertTrue(violations.isEmpty());
    }

    // ─── numeroRue ───────────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void numeroRue_blank_violation(String numeroRue) {
        Adresse a = adresseValide();
        a.setNumeroRue(numeroRue);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678901", "123456789012"})
    void numeroRue_tropLong_violation(String numeroRue) {
        Adresse a = adresseValide();
        a.setNumeroRue(numeroRue);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertEquals(1, violations.size());
        assertEquals(
                "Le numéro de rue ne doit pas dépasser 10 caractères",
                violations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "1234567890"})
    void numeroRue_valide_aucuneViolation(String numeroRue) {
        Adresse a = adresseValide();
        a.setNumeroRue(numeroRue);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertTrue(violations.isEmpty());
    }

    // ─── nomRue ──────────────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void nomRue_blank_violation(String nomRue) {
        Adresse a = adresseValide();
        a.setNomRue(nomRue);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertFalse(violations.isEmpty());
    }

    // ─── codePostal ──────────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"7500", "750011", "7500A", "abcde", "", " "})
    void codePostal_formatInvalide_violation(String codePostal) {
        Adresse a = adresseValide();
        a.setCodePostal(codePostal);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"75001", "13000", "06000", "00000", "99999"})
    void codePostal_valide_aucuneViolation(String codePostal) {
        Adresse a = adresseValide();
        a.setCodePostal(codePostal);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertTrue(violations.isEmpty());
    }

    // ─── ville ───────────────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void ville_blank_violation(String ville) {
        Adresse a = adresseValide();
        a.setVille(ville);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Paris", "Lyon", "Marseille"})
    void ville_valide_aucuneViolation(String ville) {
        Adresse a = adresseValide();
        a.setVille(ville);
        Set<ConstraintViolation<Adresse>> violations =
                validator.validate(a);
        assertTrue(violations.isEmpty());
    }
}
