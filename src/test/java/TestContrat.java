import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Contrat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires de la validation de l'entité Contrat.
 *
 * @author UGOLINI Cyril
 * @version 0.0.1
 * @since 24/03/2026
 */
public class TestContrat {

    /** Validateur partagé entre tous les tests. */
    private static Validator validator;

    /** Initialise le validateur une seule fois. */
    @BeforeAll
    static void setUp() {
        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /** Construit un contrat valide réutilisable. */
    private Contrat contratValide() {
        Contrat c = new Contrat();
        c.setIdentifiant(1);
        c.setIdClient(42);
        c.setNomContrat("Contrat de maintenance");
        c.setMontant(500.0);
        return c;
    }

    @Test
    void contratValide_aucuneViolation() {
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(contratValide());
        assertTrue(violations.isEmpty());
    }

    @Test
    void identifiant_null_violation() {
        Contrat c = contratValide();
        c.setIdentifiant(null);
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals("L'identifiant du contrat est obligatoire",
                violations.iterator().next().getMessage());
    }

    @Test
    void nomContrat_blank_violation() {
        Contrat c = contratValide();
        c.setNomContrat("");
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals("Le nom du contrat est obligatoire",
                violations.iterator().next().getMessage());
    }

    @Test
    void nomContrat_null_violation() {
        Contrat c = contratValide();
        c.setNomContrat(null);
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals("Le nom du contrat est obligatoire",
                violations.iterator().next().getMessage());
    }

    @Test
    void montant_zero_violation() {
        Contrat c = contratValide();
        c.setMontant(0);
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals("Le montant doit être strictement positif",
                violations.iterator().next().getMessage());
    }

    @Test
    void montant_negatif_violation() {
        Contrat c = contratValide();
        c.setMontant(-100.0);
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertEquals(1, violations.size());
        assertEquals("Le montant doit être strictement positif",
                violations.iterator().next().getMessage());
    }

    @Test
    void montant_exactementUn_aucuneViolation() {
        Contrat c = contratValide();
        c.setMontant(1.0);
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertTrue(violations.isEmpty());
    }

    @Test
    void contratInvalide_multipleViolations() {
        Contrat c = new Contrat();
        Set<ConstraintViolation<Contrat>> violations =
                validator.validate(c);
        assertTrue(violations.size() >= 2);
    }
}
