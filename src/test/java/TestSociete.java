import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

import models.Societe;
import models.Adresse;

public class TestSociete {

    public static void main(String[] args) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Societe s = new Societe();
        s.setIdentifiant(null);
        s.setRaisonSociale("");             // invalide (NotBlank)
        s.setAdresse(null);                 // invalide (NotNull)
        s.setTelephone("123");              // invalide (Pattern 10 chiffres)
        s.setAdresseMail("test");           // invalide (Email)
        s.setCommentaires("Test OK");

        Set<ConstraintViolation<Societe>> erreurs = validator.validate(s);

        if (erreurs.isEmpty()) {
            System.out.println(" La société est valide");
        } else {
            System.out.println(" Société invalide :");
            erreurs.forEach(e ->
                    System.out.println(" - " + e.getPropertyPath() + " : " + e.getMessage())
            );
        }
    }
}