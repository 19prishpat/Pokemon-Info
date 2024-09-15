import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String[][] pokedex = Pokedex.getPokedex();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Pokémon name: ");
        String pokemonName = scanner.nextLine().trim();

        // Display all information about the Pokémon
        displayPokemonInfo(pokemonName);
    }

    /**
     * Displays all information about the specified Pokémon.
     */
    public static void displayPokemonInfo(String pokemonName) {
        System.out.println("Description: " + getDescription(pokemonName));
        System.out.println("Legendaries Info: " + getLegendaires(pokemonName));
        System.out.println("Pokémon of the same type (" + getPrimaryType(pokemonName) + "): " + getTypes(getPrimaryType(pokemonName)));
        System.out.println("Is weak against fire? " + checkIfWeak(pokemonName, "fire"));
        System.out.println("All strengths: " + getAllStrengths());
    }

    /**
     * Returns the primary type of the Pokémon.
     */
    public static String getPrimaryType(String engName) {
        for (int i = 1; i < pokedex.length; i++) {
            if (engName.equalsIgnoreCase(pokedex[i][2])) {
                return pokedex[i][4];  // Assuming column 4 contains the primary type
            }
        }
        return "Unknown";
    }

    /**
     * Returns the description of the Pokémon.
     */
    public static String getDescription(String engName) {
        for (int i = 1; i < pokedex.length; i++) {
            if (engName.equalsIgnoreCase(pokedex[i][2])) {
                return pokedex[i][54];  // Assuming column 54 contains the description
            }
        }
        return "No description available.";
    }

    /**
     * Returns legendaries information of the Pokémon.
     */
    public static String getLegendaires(String engName) {
        String lineOne = "";
        String lineTwo = "";
        String lineThree = "";
        String finalx = "";
        for (int i = 1; i < pokedex.length; i++) {
            if(engName.equalsIgnoreCase(pokedex[i][2])){
                lineOne = "Legendaries: " + pokedex[i][41];     // Column 41 for Legendaries
                lineTwo = "Sub-Legendaries: " + pokedex[i][42]; // Column 42 for Sub-Legendaries
                lineThree = "Mythical: " + pokedex[i][43];      // Column 43 for Mythical
                finalx = lineOne + " " + lineTwo + " " + lineThree;
                return finalx;
            }
        }
        return "No legendaries information available.";
    }

    /**
     * Returns a list of all Pokémon that share the same type.
     */
    public static ArrayList<String> getTypes(String primType) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i < pokedex.length; i++) {
            if (primType.equalsIgnoreCase(pokedex[i][4])) {
                list.add(pokedex[i][2]);  // Column 2 for Pokémon name
            }
        }
        return list;
    }

    /**
     * Checks if the Pokémon is weak against a certain type.
     */
    public static boolean checkIfWeak(String name, String typeVar) {
        String check = "against_" + typeVar;
        for (int i = 1; i < pokedex.length; i++) {   
            if (name.equalsIgnoreCase(pokedex[i][2])) {  // Column 2 for Pokémon name
                for (int j = 0; j < pokedex[0].length; j++) {
                    if (check.equalsIgnoreCase(pokedex[0][j])) {  // Column header matches the type
                        return !(pokedex[i][j].equals("2") || pokedex[i][j].equals("4"));  // Assuming 2x and 4x indicate weakness
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns a list of Pokémon with strengths (immune, resistant).
     */
    public static ArrayList<String> getAllStrengths() {
        String finals = "";
        String name = "";
        ArrayList<String> listTwo = new ArrayList<String>();

        for (int i = 1; i < pokedex.length; i++) {
            for (int j = 23; j < 41; j++) {  // Columns 23 to 41 for strengths
                if (pokedex[i][j].equals("0.5") || pokedex[i][j].equals("0.25")) {
                    name = pokedex[i][2];  // Column 2 for Pokémon name
                    finals = name + ": " + pokedex[i][j];
                    listTwo.add(finals);
                } else if (pokedex[i][j].equals("0")) {
                    name = pokedex[i][2];
                    finals = name + ": " + pokedex[i][j] + " (immune)";
                    listTwo.add(finals);
                }
            }
        }
        return listTwo;
    }
}
