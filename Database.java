


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;

    class Database {

        private Connection connection;

        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();

        public Database() {
            try {

                path = path.replace("\\", "/");
                path = "jdbc:sqlite:" + path + "/pokedex.db";

                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(path);

            } catch (ClassNotFoundException | SQLException var2) {
                var2.printStackTrace(System.out);
            }
        }


        public void allPokemon() {

            int evo;
            String no;
            String name;
            String evolves;
            String hp;
            String attack;
            String defense;
            String spAtk;
            String spDef;
            String speed;


            try {
                String query = "Select * from pokemon;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    no = String.format("%1$-5s", queryResult.getInt("pokemonNo"));
                    name = String.format("%1$-15s",queryResult.getString("pokemonName"));
                    evo = queryResult.getInt("pokemonEvolvesFrom");

                    if(evo == 0){
                        evolves = "null";
                        evolves = String.format("%1$-6s",evolves);
                    }
                    else {
                        evolves = String.format("%1$-6s", queryResult.getInt("pokemonEvolvesFrom"));
                    }

                    hp = String.format("%1$-6s",queryResult.getInt("HP") );
                    attack = String.format("%1$-6s",queryResult.getInt("Attack"));
                    defense = String.format("%1$-6s", queryResult.getInt("Defense"));
                    spAtk = String.format("%1$-6s", queryResult.getInt("SpAtk"));
                    spDef = String.format("%1$-6s", queryResult.getInt("SpDef"));
                    speed = String.format("%1$-6s", queryResult.getInt("Speed"));

                    System.out.println("No. " + no + name +
                            "Evolves From: " + evolves +
                            "HP: " + hp +
                            "Attack: " + attack +
                            "Defense: " + defense +
                            "SpAtk: " + spAtk +
                            "SpDef: " + spDef +
                            "Speed: "+ speed);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }


        public void allPokemonAndTypes() {

            int evo;
            String no;
            String name;
            String typeOne;
            String typeTwo;

            try {
                String query = "Select * from pokemon natural join pokemonType;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    no = String.format("%1$-5s", queryResult.getInt("pokemonNo"));
                    name = String.format("%1$-15s",queryResult.getString("pokemonName"));

                    typeOne = String.format("%1$-10s",queryResult.getString("typeOne") );
                    typeTwo = queryResult.getString("typeTwo");


                    System.out.println("No. " + no + name + " Type One: " + typeOne + " Type Two: " + typeTwo);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }


        public void allPokemonOfType(String type) {

            String no;
            String name;
            String typeOne;
            String typeTwo;


            try {
                String query = "select * from pokemon Natural join PokemonType where TypeOne=\"" + type + "\"" + " OR " + "TypeTwo=\"" + type + "\";";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    no = String.format("%1$-5s", queryResult.getInt("pokemonNo"));
                    name = String.format("%1$-15s",queryResult.getString("pokemonName"));
                    typeOne = String.format("%1$-10s",queryResult.getString("typeOne"));
                    typeTwo = String.format("%1$-10s", queryResult.getString("typeTwo"));


                    System.out.println("No. " + no + name +
                            "TypeOne: " + typeOne +
                            "TypeTwo: " + typeTwo);
                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }


        public void allTypeAdvantages(){

            String moveType;
            String pokemonTypeOne;
            String pokemonTypeTwo;
            String effect;

            int i = 1;

            try {
                String query = "Select * from TypeAdvantages;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    moveType = String.format("%1$-10s",queryResult.getString("moveType"));
                    pokemonTypeOne = String.format("%1$-10s",queryResult.getString("pokemonTypeOne"));
                    pokemonTypeTwo = String.format("%1$-10s", queryResult.getString("pokemonTypeTwo"));
                    effect = String.format("%1$-10s", queryResult.getString("Effect"));

                    System.out.println("Advantage " + i + ": MoveType: " + moveType +
                            "pokemonTypeOne: " + pokemonTypeOne +
                            "pokemonTypeTwo: " + pokemonTypeTwo +
                            "Effect: " + effect);
                    i++;
                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }


        public void allMoves(){

            String moveNo;
            String moveName;
            String typeName;
            String power;
            String accuracy;
            String pp;

            int pow;
            int acc;
            int p;

            try {
                String query = "Select * from move;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    moveNo = String.format("%1$-5s", queryResult.getInt("MoveNo"));
                    moveName = String.format("%1$-15s",queryResult.getString("MoveName"));

                    typeName = String.format("%1$-15s", queryResult.getString("TypeName"));

                    pow = queryResult.getInt("Power");
                    acc = queryResult.getInt("Accuracy");
                    p = queryResult.getInt("PP");

                    if(pow == 0){
                        power = "null";
                        power = String.format("%1$-6s",power);
                    }
                    else {
                        power = String.format("%1$-6s", queryResult.getInt("Power"));
                    }

                    if(acc == 0){
                        accuracy = "null";
                        accuracy = String.format("%1$-6s",accuracy);
                    }
                    else {
                        accuracy = String.format("%1$-6s", queryResult.getInt("Accuracy"));
                    }

                    if(p == 0){
                        pp = "null";
                        pp = String.format("%1$-6s",pp);
                    }
                    else {
                        pp = String.format("%1$-6s", queryResult.getInt("PP"));
                    }

                    System.out.println("Move No. " + moveNo +
                            "Move Name: " + moveName +
                            "Type Name: " + typeName +
                            "Power: " + power +
                            "Accuracy: " + accuracy +
                            "PP: " + pp);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }



        public void allPokemonNoMoves(String number){

            String pokemonName;
            String moveName;
            String typeName;
            String power;
            String accuracy;
            String pp;

            int pow;
            int acc;
            int p;

            try {
                String query = "select * from pokemon natural join PokemonType left join move where (PokemonNo=\"" +  number + "\") AND (TypeOne = TypeName OR TypeTwo = TypeName OR TypeName = \"Normal\");";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    pokemonName = String.format("%1$-15s",queryResult.getString("pokemonName"));
                    moveName = String.format("%1$-15s",queryResult.getString("MoveName"));

                    typeName = String.format("%1$-15s", queryResult.getString("TypeName"));

                    pow = queryResult.getInt("Power");
                    acc = queryResult.getInt("Accuracy");
                    p = queryResult.getInt("PP");

                    if(pow == 0){
                        power = "null";
                        power = String.format("%1$-6s",power);
                    }
                    else {
                        power = String.format("%1$-6s", queryResult.getInt("Power"));
                    }

                    if(acc == 0){
                        accuracy = "null";
                        accuracy = String.format("%1$-6s",accuracy);
                    }
                    else {
                        accuracy = String.format("%1$-6s", queryResult.getInt("Accuracy"));
                    }

                    if(p == 0){
                        pp = "null";
                        pp = String.format("%1$-6s",pp);
                    }
                    else {
                        pp = String.format("%1$-6s", queryResult.getInt("PP"));
                    }

                    System.out.println("Pokemon " + pokemonName + " can learn " +
                            "Move Name: " + moveName +
                            "Type Name: " + typeName +
                            "Power: " + power +
                            "Accuracy: " + accuracy +
                            "PP: " + pp);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }


        public void strongestPokemonNoMoves(String number){

            String pokemonName;
            String moveName;
            String typeName;
            String power;
            String accuracy;
            String pp;

            int pow;
            int acc;
            int p;

            try {
                String query = "select * from pokemon natural join PokemonType left join move where (PokemonNo=\"" +  number + "\") AND (TypeOne = TypeName OR TypeTwo = TypeName OR TypeName = \"Normal\") order by power desc;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    pokemonName = String.format("%1$-15s",queryResult.getString("pokemonName"));
                    moveName = String.format("%1$-15s",queryResult.getString("MoveName"));

                    typeName = String.format("%1$-15s", queryResult.getString("TypeName"));

                    pow = queryResult.getInt("Power");
                    acc = queryResult.getInt("Accuracy");
                    p = queryResult.getInt("PP");

                    if(pow == 0){
                        power = "null";
                        power = String.format("%1$-6s",power);
                    }
                    else {
                        power = String.format("%1$-6s", queryResult.getInt("Power"));
                    }

                    if(acc == 0){
                        accuracy = "null";
                        accuracy = String.format("%1$-6s",accuracy);
                    }
                    else {
                        accuracy = String.format("%1$-6s", queryResult.getInt("Accuracy"));
                    }

                    if(p == 0){
                        pp = "null";
                        pp = String.format("%1$-6s",pp);
                    }
                    else {
                        pp = String.format("%1$-6s", queryResult.getInt("PP"));
                    }

                    System.out.println("Pokemon " + pokemonName + " can learn " +
                            "Move Name: " + moveName +
                            "Type Name: " + typeName +
                            "Power: " + power +
                            "Accuracy: " + accuracy +
                            "PP: " + pp);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }

        public void strongestSpecialPokemonNoMoves(String number){

            String pokemonName;
            String moveName;
            String typeName;
            String power;
            String accuracy;
            String pp;

            int pow;
            int acc;
            int p;

            try {
                String query = "select * from pokemon natural join PokemonType left join move where (PokemonNo=\"" +  number + "\") AND (TypeOne = TypeName OR TypeTwo = TypeName) order by power desc;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    pokemonName = String.format("%1$-15s",queryResult.getString("pokemonName"));
                    moveName = String.format("%1$-15s",queryResult.getString("MoveName"));

                    typeName = String.format("%1$-15s", queryResult.getString("TypeName"));

                    pow = queryResult.getInt("Power");
                    acc = queryResult.getInt("Accuracy");
                    p = queryResult.getInt("PP");

                    if(pow == 0){
                        power = "null";
                        power = String.format("%1$-6s",power);
                    }
                    else {
                        power = String.format("%1$-6s", queryResult.getInt("Power"));
                    }

                    if(acc == 0){
                        accuracy = "null";
                        accuracy = String.format("%1$-6s",accuracy);
                    }
                    else {
                        accuracy = String.format("%1$-6s", queryResult.getInt("Accuracy"));
                    }

                    if(p == 0){
                        pp = "null";
                        pp = String.format("%1$-6s",pp);
                    }
                    else {
                        pp = String.format("%1$-6s", queryResult.getInt("PP"));
                    }

                    System.out.println("Pokemon " + pokemonName + " can learn " +
                            "Move Name: " + moveName +
                            "Type Name: " + typeName +
                            "Power: " + power +
                            "Accuracy: " + accuracy +
                            "PP: " + pp);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }




        public void allMovesTypeAdvantage(String effect){

            String moveName;
            String pokemonTypeOne;
            String pokemonTypeTwo;
            String effectResult;

            int i = 1;

            try {
                String query = "select MoveName, moveType, pokemonTypeOne, pokemonTypeTwo, Effect from move natural join TypeAdvantages where typeName=moveType AND Effect=\"" + effect + "\"" + " AND power is not null;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    moveName = queryResult.getString("MoveName");
                    pokemonTypeOne = queryResult.getString("pokemonTypeOne");
                    pokemonTypeTwo = queryResult.getString("pokemonTypeTwo");
                    effectResult = queryResult.getString("Effect");


                    if(pokemonTypeTwo == null){

                        System.out.println("Result " + i + ": " +
                                "Attack: " + moveName +
                                " is " + effectResult +
                                " against " + pokemonTypeOne +
                                " type Pokemon");
                    }
                    else
                    {
                        System.out.println("Result " + i + ": " +
                                "Attack: " + moveName +
                                " is " + effectResult +
                                " against " + pokemonTypeOne + " - " +
                                pokemonTypeTwo + " type Pokemon");

                    }



                    i++;

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }


        public void typesAVGPower(){

            String typeName;
            String power;

            try {
                String query = "select TypeName, AVG(power) from move where power is not null group by TypeName order by AVG(power) desc;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                int i = 1;
                while(queryResult.next()) {

                    typeName = String.format("%1$-15s",queryResult.getString("typeName"));
                    power = String.format("%1$-15s",queryResult.getFloat("AVG(power)"));

                    System.out.println("Type " + i + ": " + typeName + "AVG Power: " + power);

                    i++;

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }
        }



        public void allTypes(){

            String typeName;

            try {
                String query = "Select * from Type;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                int i = 1;
                while(queryResult.next()) {

                    typeName = String.format("%1$-15s",queryResult.getString("typeName"));

                    System.out.println("Type " + i + ": " + typeName );

                    i++;

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }
        }


        public void allTrainers(){

            String trainerName;
            String trainerID;

            try {
                String query = "Select * from Trainer;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    trainerName = queryResult.getString("trainerName");
                    trainerID = queryResult.getString("trainerID");


                    System.out.println("TrainerID " + trainerID + ": " + trainerName );

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }
        }


        public void eachTrainersStrongestPokemon(){

            String trainerName;
            String trainerID;
            String pokemonName;
            String attack;

            try {
                String query = "select * from Trainer natural join Party natural join pokemon group by trainerName order by TrainerID, Attack desc;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    trainerName = queryResult.getString("trainerName");
                    trainerID = queryResult.getString("trainerID");
                    pokemonName = queryResult.getString("pokemonName");
                    attack = queryResult.getString("Attack");



                    System.out.println("TrainerID " + trainerID + ": " + trainerName + "'s strongest Pokemon is " + pokemonName + " Attack: " + attack);

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }
        }


        public void allParties(){

            String pokemonNo;
            String trainerID;

            try {
                String query = "Select * from Party;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    pokemonNo = queryResult.getString("pokemonNo");
                    trainerID = queryResult.getString("trainerID");


                    System.out.println("TrainerID: " + trainerID + " pokemonNo: " + pokemonNo );

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }
        }


        public void allTrainersPokemon(){

            String trainerID;
            String trainerName;
            String pokemonNo;
            String pokemonName;

            try {
                String query = "select * from Trainer NATURAL join Party NATURAL join Pokemon;";
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                while(queryResult.next()) {

                    trainerID = queryResult.getString("trainerID");
                    trainerName = queryResult.getString("trainerName");
                    pokemonNo = queryResult.getString("pokemonNo");
                    pokemonName = queryResult.getString("pokemonName");



                    System.out.println("TrainerID " + trainerID +  ": " + trainerName +  " has all" + pokemonName );

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }
        }




        public void getAdvantageEffects(String effect){

            String moveType;
            String pokemonTypeOne;
            String pokemonTypeTwo;
            String effectResult;

            String query = "Select * from TypeAdvantages where Effect=\"" + effect + "\";";

            try {
                Statement queryStatement = this.connection.createStatement();
                ResultSet queryResult = queryStatement.executeQuery(query);

                int i = 1;
                while(queryResult.next()) {

                    moveType = queryResult.getString("moveType");
                    pokemonTypeOne = queryResult.getString("pokemonTypeOne");
                    pokemonTypeTwo = queryResult.getString("pokemonTypeTwo");
                    effectResult = queryResult.getString("Effect");


                    if(pokemonTypeTwo == null){
                        System.out.println("Result " + i + ": " + moveType + " is " + effectResult + " against " + pokemonTypeOne + " type Pokemon ");
                    }
                    else {
                        System.out.println("Result " + i + ": " + moveType + " is " + effectResult + " against " + pokemonTypeOne + " - " + pokemonTypeTwo + " type Pokemon ");
                    }

                    i++;

                }

                queryResult.close();
                queryStatement.close();
            } catch (SQLException close) {
                close.printStackTrace(System.out);
            }

        }

    }




