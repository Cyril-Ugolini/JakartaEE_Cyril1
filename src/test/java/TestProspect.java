import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Adresse;
import models.Interesse;
import models.Prospect;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires de la validation de l'entité Prospect.
 *
 * @author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public class TestProspect {

    /** Validateur partagé entre tous les tests. */
    private static Validator validator;

    /** Initialise le validateur une seule fois. */
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /** Construit un prospect valide réutilisable. */
    private Prospect prospectValide() {
        Adresse a = new Adresse();
        a.setNumeroRue("12");
        a.setNomRue("Rue de la Paix");
        a.setCodePostal("75001");
        a.setVille("Paris");

        Prospect p = new Prospect();
        p.setRaisonSociale("ACME Corp");
        p.setAdresse(a);
        p.setTelephone("0123456789");
        p.setAdresseMail("contact@acme.fr");
        p.setDateProspection(LocalDate.now());
        p.setInteresse(Interesse.OUI);
        return p;
    }

    @Test
    void prospectValide_aucuneViolation() {
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(prospectValide());
        assertTrue(violations.isEmpty());
    }

    // ─── dateProspection ─────────────────────────────────────

    @Test
    void dateProspection_null_violation() {
        Prospect p = prospectValide();
        p.setDateProspection(null);
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertEquals(1, violations.size());
        assertEquals("La date de prospection est obligatoire",
                violations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -365, -3650})
    void dateProspection_valide_aucuneViolation(int joursOffset) {
        Prospect p = prospectValide();
        p.setDateProspection(LocalDate.now().plusDays(joursOffset));
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertTrue(violations.isEmpty());
    }

    // ─── interesse ───────────────────────────────────────────

    @Test
    void interesse_null_violation() {
        Prospect p = prospectValide();
        p.setInteresse(null);
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertEquals(1, violations.size());
        assertEquals("L'état d'intérêt est obligatoire",
                violations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"OUI", "NON"})
    void interesse_valide_aucuneViolation(String valeur) {
        Prospect p = prospectValide();
        p.setInteresse(Interesse.valueOf(valeur));
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertTrue(violations.isEmpty());
    }

    // ─── raisonSociale (hérité Societe) ──────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void raisonSociale_blank_violation(String raisonSociale) {
        Prospect p = prospectValide();
        p.setRaisonSociale(raisonSociale);
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    // ─── telephone (hérité Societe) ──────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {
            "012345",
            "01234567890",
            "012345678A",
            ""
    })
    void telephone_formatInvalide_violation(String telephone) {
        Prospect p = prospectValide();
        p.setTelephone(telephone);
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    // ─── email (hérité Societe) ───────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {
            "pasunemail",
            "manque@",
            "@manque.fr",
            "double@@test.fr",
            ""
    })
    void email_formatInvalide_violation(String email) {
        Prospect p = prospectValide();
        p.setAdresseMail(email);
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertFalse(violations.isEmpty());
    }

    // ─── adresse (hérité Societe) ─────────────────────────────

    @Test
    void adresse_null_violation() {
        Prospect p = prospectValide();
        p.setAdresse(null);
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertEquals(1, violations.size());
        assertEquals("L'adresse est obligatoire",
                violations.iterator().next().getMessage());
    }

    @Test
    void prospectInvalide_multipleViolations() {
        Prospect p = new Prospect();
        Set<ConstraintViolation<Prospect>> violations =
                validator.validate(p);
        assertTrue(violations.size() >= 4);
    }
}
