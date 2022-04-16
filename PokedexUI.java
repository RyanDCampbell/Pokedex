
import java.util.Scanner;

public class PokedexUI {

    public static void main(String[] args)
    {
        Database pokedex = new Database();



       runPokedex(pokedex, args);

    }



    public static void runPokedex(Database pokedex, String[] args){

        System.out.println("\nWelcome to the Gen 1 Pokedex! \n\n Enter 'help' to access the list of commands, or enter 'exit' to exit the Pokedex.");

        boolean getUserInput = true;

        Scanner userInput = new Scanner(System.in);
        String inputString;

        String[] inputTokens;

        while(getUserInput){

            System.out.print(" > ");
            inputString = userInput.nextLine();
            inputTokens = inputString.split(" ");

            if(inputString.equals("help")){
                helpMenu();
            }
            else if(inputString.equals("all pokemon")){
                pokedex.allPokemon();
            }
            else if(inputString.equals("all advantages")){
                pokedex.allTypeAdvantages();
            }
            else if(inputString.equals("all moves")){
                pokedex.allMoves();
            }
            else if(inputString.equals("all types")){
                pokedex.allTypes();
            } else if(inputString.toLowerCase().equals("exit")){
                getUserInput = false;
                System.out.println("Shutting down the Pokedex...");
            }
            else if(inputTokens.length == 3 && (inputTokens[0].toLowerCase().equals("not-very-effective") || inputTokens[0].toLowerCase().equals("normal") ||
            inputTokens[0].toLowerCase().equals("no-effect") || inputTokens[0].toLowerCase().equals("super-effective")) &&
                    inputTokens[1].equalsIgnoreCase("type") && inputTokens[2].equalsIgnoreCase("advantages")){

                String advantage = inputTokens[0].substring(0, 1).toUpperCase() + inputTokens[0].substring(1);

                if(!advantage.equals("Super-effective")){
                    advantage = advantage.replace("-", " ");
                }
                pokedex.getAdvantageEffects(advantage);

            }
            else if(inputTokens.length == 3 && ((inputTokens[0].toLowerCase().equals("all") &&
                    inputTokens[1].toLowerCase().equals("pokemon") && inputTokens[2].toLowerCase().equals("types")))){
                pokedex.allPokemonAndTypes();
            }
            else if ((inputTokens.length == 4 && (inputTokens[0].toLowerCase().equals("all")) &&
                    (inputTokens[1].toLowerCase().equals("normal")|| inputTokens[1].toLowerCase().equals("fire")||
                     inputTokens[1].toLowerCase().equals("water")|| inputTokens[1].toLowerCase().equals("electric")||
                     inputTokens[1].toLowerCase().equals("grass")||inputTokens[1].toLowerCase().equals("ice")||
                     inputTokens[1].toLowerCase().equals("fighting")||inputTokens[1].toLowerCase().equals("poison")||
                     inputTokens[1].toLowerCase().equals("ground")||inputTokens[1].toLowerCase().equals("flying")||
                     inputTokens[1].toLowerCase().equals("psychic")||inputTokens[1].toLowerCase().equals("bug")||
                     inputTokens[1].toLowerCase().equals("rock")|| inputTokens[1].toLowerCase().equals("ghost")||
                     inputTokens[1].toLowerCase().equals("dragon")|| inputTokens[1].toLowerCase().equals("dark")||
                     inputTokens[1].toLowerCase().equals("steel")|| inputTokens[1].toLowerCase().equals("fairy")) &&
                     inputTokens[2].toLowerCase().equals("type") && inputTokens[3].toLowerCase().equals("pokemon"))){

                String type =  inputTokens[1].substring(0, 1).toUpperCase() + inputTokens[1].substring(1);
                pokedex.allPokemonOfType(type);
            }
            else if(inputTokens.length == 4 && inputTokens[0].equalsIgnoreCase("all") && (inputTokens[1].toLowerCase().equals("not-very-effective") || inputTokens[1].toLowerCase().equals("normal") ||
                    inputTokens[1].toLowerCase().equals("no-effect") || inputTokens[1].toLowerCase().equals("super-effective")) &&
                    inputTokens[2].equalsIgnoreCase("attack") && inputTokens[3].equalsIgnoreCase("types")){

                String advantage = inputTokens[1].substring(0, 1).toUpperCase() + inputTokens[1].substring(1);

                if(!advantage.equals("Super-effective")){
                    advantage = advantage.replace("-", " ");
                }
                pokedex.allMovesTypeAdvantage(advantage);

            }
            else if (inputTokens.length == 4 && inputTokens[0].equalsIgnoreCase("all") && inputTokens[1].equalsIgnoreCase("pokemon") &&
                    ((Integer.parseInt(inputTokens[2]) > 0 && Integer.parseInt(inputTokens[2]) < 152) && inputTokens[3].equalsIgnoreCase("moves"))){
                pokedex.allPokemonNoMoves(inputTokens[2]);
            }
            else if (inputTokens.length == 4 && inputTokens[0].equalsIgnoreCase("strongest") && inputTokens[1].equalsIgnoreCase("pokemon") &&
                    ((Integer.parseInt(inputTokens[2]) > 0 && Integer.parseInt(inputTokens[2]) < 152) && inputTokens[3].equalsIgnoreCase("moves"))){
                pokedex.strongestPokemonNoMoves(inputTokens[2]);
            }
            else if (inputTokens.length == 5 && inputTokens[0].equalsIgnoreCase("strongest") && inputTokens[1].equalsIgnoreCase("special") && inputTokens[2].equalsIgnoreCase("pokemon") &&
                    ((Integer.parseInt(inputTokens[3]) > 0 && Integer.parseInt(inputTokens[3]) < 152) && inputTokens[4].equalsIgnoreCase("moves"))){
                pokedex.strongestSpecialPokemonNoMoves(inputTokens[3]);
            }
            else if(inputTokens.length == 4 && inputTokens[0].equalsIgnoreCase("move") && inputTokens[1].equalsIgnoreCase("types") && inputTokens[2].equalsIgnoreCase("average") && inputTokens[3].equalsIgnoreCase("power")){
                pokedex.typesAVGPower();
            }
            else if (inputTokens.length == 2 && inputTokens[0].equalsIgnoreCase("all") && inputTokens[1].equalsIgnoreCase("trainers")){
                pokedex.allTrainers();
            }
            else if (inputTokens.length == 2 && inputTokens[0].equalsIgnoreCase("all") && inputTokens[1].equalsIgnoreCase("parties")){
                pokedex.allParties();
            }
            else if(inputTokens.length == 3 && inputTokens[0].equalsIgnoreCase("all") && inputTokens[1].equalsIgnoreCase("trainers") && inputTokens[2].equalsIgnoreCase("pokemon")){
                pokedex.allTrainersPokemon();
            }
            else if(inputTokens.length ==4 && inputTokens[0].equalsIgnoreCase("each") && inputTokens[1].equalsIgnoreCase("trainers") && inputTokens[2].equalsIgnoreCase("strongest") && inputTokens[3].equalsIgnoreCase("pokemon")){
                pokedex.eachTrainersStrongestPokemon();
            }
            else {
                System.out.println("Invalid command, enter 'h' for help!");
            }


        }






    }



    public static void helpMenu(){

        System.out.println("\nCommands: \n" +
                "Function 1) help - Displays the help menu \n" +
                "Function 2) exit - Stops the Pokedex from running, ends the program \n\n" +

                "Query 1) all pokemon types - Prints all Pokemon, along with their types\n" +
                "Query 2) all fire type pokemon - Prints all fire type Pokemon. (Fire can be replaced with any pokemon type)\n" +
                "Query 3) all pokemon 1 moves - Prints all the moves and move stats that Pokemon 1 can learn. (1 can be replaced by any number ranging from 1-151) \n" +
                "Query 4) strongest pokemon 1 moves - Prints all the moves and move stats that Pokemon 1 can learn ordered by power in descending order. (1 can be replaced by any number ranging from 1-151) \n" +
                "Query 5) strongest special pokemon 1 moves - Prints all the special moves and move stats that Pokemon 1 can learn ordered by power in descending order. (1 can be replaced by any number ranging from 1-151) \n" +
                "Query 6) super-effective type advantages - Prints all type advantages with the given effect (super-effective can be replaced with 'no-effect', 'not-very-effective', or 'normal') \n" +
                "Query 7) all super-effective attack types - Show all attacks that are super effective against a Pokemon type combination. (super-effective can be replaced with 'no-effect', 'not-very-effective', or 'normal') \n" +
                "Query 8) move types average power - Prints all the average power of all move types that have an attack move) \n" +
                "Query 9) all trainers pokemon - Prints each pokemon trainer and all the pokemon in their party\n" +
                "Query 10) each trainers strongest pokemon - Prints the strongest Pokemon of each trainer, based on highest attack power\n\n" +

                "Get Table 1) all pokemon - Prints the contents of the Pokemon table \n" +
                "Get Table 2) all moves - Prints the contents of the Move table \n" +
                "Get Table 3) all types - Prints the contents of the Type table \n" +
                "Get Table 4) all trainers - Prints the contents of the Trainer table\n" +
                "Get Table 5) all parties - Prints the contents of the Party table\n" +
                "Get Table 6) all advantages - Prints the contents of the typeAdvantages table \n");


    }



}
