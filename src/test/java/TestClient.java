import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import fr.afpa.jakartaee_cyril1.models.Adresse;
import fr.afpa.jakartaee_cyril1.models.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires de la validation de l'entité Client.
 *
 * @author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public class TestClient {

    /** Validateur partagé entre tous les tests. */
    private static Validator validator;

    /** Initialise le validateur une seule fois. */
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /** Construit un client valide réutilisable. */
    private Client clientValide() {
        Adresse a = new Adresse();
        a.setNumeroRue("12");
        a.setNomRue("Rue de la Paix");
        a.setCodePostal("75001");
        a.setVille("Paris");

        Client c = new Client();
        c.setRaisonSociale("ACME Corp");
        c.setAdresse(a);
        c.setTelephone("0123456789");
        c.setAdresseMail("contact@acme.fr");
        c.setChiffreAffaires(500);
        c.setNombreEmployes(10);
        return c;
    }

    @Test
    void clientValide_aucuneViolation() {
        Set<ConstraintViolation<Client>> violations =
                validator.validate(clientValide());
        assertTrue(violations.isEmpty());
    }

    // ─── chiffreAffaires ─────────────────────────────────────

    @ParameterizedTest
    @ValueSource(longs = {0L, 1L, 100L, 199L})
    void chiffreAffaires_inferieurMin_violation(long montant) {
        Client c = clientValide();
        c.setChiffreAffaires(montant);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals(
                "Le chiffre d'affaires doit être supérieur à 200 €",
                violations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {200L, 201L, 1000L, 999999L})
    void chiffreAffaires_valide_aucuneViolation(long montant) {
        Client c = clientValide();
        c.setChiffreAffaires(montant);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertTrue(violations.isEmpty());
    }

    // ─── nombreEmployes ──────────────────────────────────────

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10, -100})
    void nombreEmployes_inferieurMin_violation(int nombre) {
        Client c = clientValide();
        c.setNombreEmployes(nombre);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals(
                "Le nombre d'employés doit être supérieur à 0",
                violations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 100, 1000})
    void nombreEmployes_valide_aucuneViolation(int nombre) {
        Client c = clientValide();
        c.setNombreEmployes(nombre);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertTrue(violations.isEmpty());
    }

    // ─── raisonSociale (hérité Societe) ──────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void raisonSociale_blank_violation(String raisonSociale) {
        Client c = clientValide();
        c.setRaisonSociale(raisonSociale);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
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
        Client c = clientValide();
        c.setTelephone(telephone);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
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
        Client c = clientValide();
        c.setAdresseMail(email);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertFalse(violations.isEmpty());
    }

    // ─── adresse (hérité Societe) ─────────────────────────────

    @Test
    void adresse_null_violation() {
        Client c = clientValide();
        c.setAdresse(null);
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals("L'adresse est obligatoire",
                violations.iterator().next().getMessage());
    }

    @Test
    void clientInvalide_multipleViolations() {
        Client c = new Client();
        Set<ConstraintViolation<Client>> violations =
                validator.validate(c);
        assertTrue(violations.size() >= 4);
    }
}
