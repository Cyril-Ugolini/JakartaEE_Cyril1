import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.time.LocalDate;
import java.util.Set;

import models.Prospect;
import models.Interesse;
import models.Adresse;

public class TestProspect {

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Prospect p = new Prospect();
        p.setIdentifiant(null);            // hérité de Societe
        p.setRaisonSociale(null);          // invalide (NotBlank dans Societe)
        p.setAdresse(null);                // invalide (NotNull dans Societe)
        p.setTelephone("123");             // invalide (Pattern 10 chiffres)
        p.setAdresseMail("test");          // invalide (Email)
        p.setCommentaires("Test");

        p.setDateProspection(null);        // invalide
        p.setInteresse(null);              // invalide

        Set<ConstraintViolation<Prospect>> erreurs = validator.validate(p);

        if (erreurs.isEmpty()) {
            System.out.println(" Le prospect est valide");
        } else {
            System.out.println(" Prospect invalide :");
            erreurs.forEach(e ->
                    System.out.println(" - " + e.getPropertyPath() + " : " + e.getMessage())
            );
        }
    }
}