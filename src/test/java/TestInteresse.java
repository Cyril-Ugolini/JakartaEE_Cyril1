import models.Interesse;

public class TestInteresse {

    public static void main(String[] args) {

        System.out.println("Test des valeurs de l'énumération Interesse :");

        for (Interesse i : Interesse.values()) {
            System.out.println(" - Enum : " + i.name() + " | toString() : " + i.toString());
        }

        // Test individuel
        Interesse oui = Interesse.OUI;
        Interesse non = Interesse.NON;

        System.out.println("\nVérification individuelle :");
        System.out.println("OUI → " + oui.toString());
        System.out.println("NON → " + non.toString());
    }
}