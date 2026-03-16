import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import models.Contrat;

import java.util.Set;

public class TestContrat {

    public static void main(String[] args) { //  Plus de paramètre générique

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Contrat c = new Contrat();
        c.setIdentifiant(null);     // invalide
        c.setIdClient(null);        // invalide
        c.setNomContrat("");        // invalide
        c.setMontant(0);            // invalide (min 1)

        Set<ConstraintViolation<Contrat>> erreurs = validator.validate(c);

        if (erreurs.isEmpty()) {
            System.out.println(" Le contrat est valide");
        } else {
            System.out.println(" Contrat invalide :");
            erreurs.forEach(e ->
                    System.out.println(" - " + e.getPropertyPath() + " : " + e.getMessage())
            );
        }
    }
}