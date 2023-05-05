package calcul;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Calculatrice {
    // Variables pour les fichiers d'entrée et de sortie
    private String inputFile;
    private String outputFile;

    // Constructeur prenant le fichier d'entrée en paramètre
    public Calculatrice(String inputFile) {
        this.inputFile = inputFile;
        this.outputFile = "resultats.txt";
    }

    // Méthode pour lire les calculs depuis le fichier d'entrée
    public List<String> lireCalculs() {
        List<String> calculs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                calculs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calculs;
    }

    // Méthode pour écrire les résultats dans le fichier de sortie
    public void ecrireResultats(List<String> resultats) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String resultat : resultats) {
                bw.write(resultat);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour évaluer une expression mathématique
    public String evaluerExpression(String expression) {
        // Séparer l'expression en tokens (nombres et opérateurs)
        String[] tokens = expression.split(" ");
        double a = Double.parseDouble(tokens[0]);
        String operateur1 = tokens[1];
        double b = Double.parseDouble(tokens[2]);
        double resultat1 = 0;

        // Calculer le résultat en fonction du premier opérateur
        switch (operateur1) {
            case "+":
                resultat1 = a + b;
                break;
            case "-":
                resultat1 = a - b;
                break;
            case "*":
                resultat1 = a * b;
                break;
            case "/":
                resultat1 = a / b;
                break;
        }

        // Si l'expression contient un deuxième opérateur, calculer le résultat en fonction de celui-ci
        if (tokens.length > 3) {
            String operateur2 = tokens[3];
            double c = Double.parseDouble(tokens[4]);
            double resultat2 = 0;

            switch (operateur2) {
                case "+":
                    resultat2 = resultat1 + c;
                    break;
                case "-":
                    resultat2 = resultat1 - c;
                    break;
                case "*":
                    resultat2 = resultat1 * c;
                    break;
                case "/":
                    resultat2 = resultat1 / c;
                    break;
            }

            return expression + " = " + resultat2;
        }

        return expression + " = " + resultat1;
    }

    // Méthode pour exécuter le calcul des expressions
    public void executer() {
        List<String> calculs = lireCalculs();
        List<String> resultats = new ArrayList<>();

        for (String calcul : calculs) {
            String resultat = evaluerExpression(calcul);
            System.out.println(resultat); // Affiche le résultat dans la console
            resultats.add(resultat);
        }

        ecrireResultats(resultats);
    }
    
    // Méthode principale pour lancer la calculatrice
    public static void main(String[] args) {
        // Spécifiez le fichier d'entrée contenant les calculs
        String inputFile = "calculs.txt";
        
        // Créez une instance de la classe Calculatrice avec le fichier d'entrée
        Calculatrice calculatrice = new Calculatrice(inputFile);
        
        // Exécutez les calculs et sauvegardez les résultats dans le fichier de sortie
        calculatrice.executer();
    }
}
