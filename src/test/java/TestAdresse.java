import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

import models.Adresse;

public class TestAdresse {

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Adresse a = new Adresse();
        a.setIdAdresse(null);          // invalide
        a.setNumeroRue("");            // invalide
        a.setNomRue(" ");              // invalide
        a.setCodePostal("5400");       // invalide (4 chiffres)
        a.setVille(null);              // invalide

        Set<ConstraintViolation<Adresse>> erreurs = validator.validate(a);

        if (erreurs.isEmpty()) {
            System.out.println(" L'adresse est valide");
        } else {
            System.out.println(" Adresse invalide :");
            erreurs.forEach(e ->
                    System.out.println(" - " + e.getPropertyPath() + " : " + e.getMessage())
            );
        }
    }
}