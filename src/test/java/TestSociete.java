import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Adresse;
import models.Societe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires de la validation de l'entité Societe.
 *
 * @author UGOLINI Cyril
 * @version 0.0.2
 * @since 24/03/2026
 */
public class TestSociete {

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

    /** Construit une société valide réutilisable. */
    private Societe societeValide() {
        Societe s = new Societe();
        s.setRaisonSociale("ACME Corp");
        s.setAdresse(adresseValide());
        s.setTelephone("0123456789");
        s.setAdresseMail("contact@acme.fr");
        return s;
    }

    @Test
    void societeValide_aucuneViolation() {
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(societeValide());
        assertTrue(violations.isEmpty());
    }

    // ─── raisonSociale ───────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void raisonSociale_blank_violation(String raisonSociale) {
        Societe s = societeValide();
        s.setRaisonSociale(raisonSociale);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ACME", "Société Dupont", "123 Corp"})
    void raisonSociale_valide_aucuneViolation(String raisonSociale) {
        Societe s = societeValide();
        s.setRaisonSociale(raisonSociale);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertTrue(violations.isEmpty());
    }

    // ─── adresse ─────────────────────────────────────────────

    @Test
    void adresse_null_violation() {
        Societe s = societeValide();
        s.setAdresse(null);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertEquals(1, violations.size());
        assertEquals("L'adresse est obligatoire",
                violations.iterator().next().getMessage());
    }

    @Test
    void adresse_invalide_cascadeValidation() {
        Societe s = societeValide();
        Adresse a = adresseValide();
        a.setCodePostal("invalide");
        s.setAdresse(a);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertEquals(1, violations.size());
    }

    // ─── telephone ───────────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {
            "012345",       // trop court
            "01234567890",  // trop long
            "012345678A",   // lettre
            "          ",   // espaces
            ""              // vide
    })
    void telephone_formatInvalide_violation(String telephone) {
        Societe s = societeValide();
        s.setTelephone(telephone);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0123456789",
            "0612345678",
            "0712345678"
    })
    void telephone_valide_aucuneViolation(String telephone) {
        Societe s = societeValide();
        s.setTelephone(telephone);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertTrue(violations.isEmpty());
    }

    // ─── email ───────────────────────────────────────────────

    @ParameterizedTest
    @ValueSource(strings = {
            "pasunemail",
            "manque@",
            "@manque.fr",
            "double@@test.fr",
            ""
    })
    void email_formatInvalide_violation(String email) {
        Societe s = societeValide();
        s.setAdresseMail(email);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "contact@acme.fr",
            "user.name@domain.com",
            "user+tag@sub.domain.org"
    })
    void email_valide_aucuneViolation(String email) {
        Societe s = societeValide();
        s.setAdresseMail(email);
        Set<ConstraintViolation<Societe>> violations =
                validator.validate(s);
        assertTrue(violations.isEmpty());
    }
}
