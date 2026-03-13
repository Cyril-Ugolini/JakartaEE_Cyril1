import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

import models.Client;

public class TestClient {

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Client c = new Client();
        c.setIdClient(null);        // invalide
        c.setChiffreAffaires(100);  // invalide
        c.setNombreEmployes(0);     // invalide

        Set<ConstraintViolation<Client>> erreurs = validator.validate(c);

        if (erreurs.isEmpty()) {
            System.out.println("Le client est valide");
        } else {
            System.out.println("Le client est invalide :");
            erreurs.forEach(e ->
                    System.out.println(" - " + e.getPropertyPath() + " : " + e.getMessage())
            );
        }
    }
}