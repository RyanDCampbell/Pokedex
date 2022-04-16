# Pokedex
 A Gen 1 Pokedex

To run:

1) Open a terminal windows within the directory.
2) Type "make" without the quotations to compile and run.


## Project Report 

# Summary of the data:

Pokemon has always been close to my heart, as a child I have spend countless hundreds of hours playing various Pokemon games. Naturally, when the opportunity to design a database for this project was presented to me, building a Pokedex was a no-brainer.

1) Why was it chosen? 
I gathered the data from several sources, initially I planned to only use the data provided in CSV format from “veekun”’s github pokedex project https://github.com/veekun/pokedex. Veekun’s pokedex project data powers a large portion of the Pokemon databases available on the web. When I started working with building the database, I soon realized how difficult it was to sift through all the various data files. I opted to supplement my data source by also using pokemondb.net. PokemonDB made processing information such as the numerous type advantages much easier, as it was presented in a more legible format. 

2) What does it consist of? 
I limited the data I would be using to encompass the core mechanics of Pokemon. Pokemon, stats, types, and type advantages and the various moves each Pokemon can learn. Due to the huge number of Pokemon releases over the years, I decided to limit the project to the first generation of Pokemon.  This was done as I opted to complete this project solo (regrettably), and because the first generation is arguably my favorite generation (second gen is also excellent, and the data would have been expanded to include second gen if time permitted).

3) How large is it? 
The file size of the database file is 172KB. The database consists of a total of 1338 entries across all tables. 


## A Discussion of the Data Model

1) Why was it broken down into those tables? 
 
The data model was broken down into their tables by following the process of normalization described in class. Through the normalization process, the pokemonStats and moveStats attribute were separated into their own tables, but later remerged, as they shared the same primary key. The relationships: noEffect, normal, superEffective and notVeryEffective requires some explanation, which I will discuss in the proceeding section. 
 
2)	Difficulties deciding how to setup the data model 
 
There were a few difficulties when implementing the data model that I didn’t expect to run into.  The first was when merging the relationships noEffect, normal, superEffective and notVeryEffective, they are all merged into a single table as they share the same primary key. However, this caused loss of information, because we would lose the type-advantage information. To resolve this loss of information, an additional column was added to the typeAdvantages table.  This column was labeled “Effect” and contains the type-advantage information that otherwise would have been lost in the merging process. 
 
Secondly, another issue was encountered when adding entries to the typeAdvantages and move table. When attempting to populate the tables, I would run into a “foreign key mismatch error”. This stumped me for a while, but I discovered the issue was caused by each table referencing the other, and each being empty or not entirely populated, so the foreign key references were not valid.  To resolve this, I turned off the foreign key constraints for these tables until they were fully populated. Once I was finished adding the entries to each table, I reenabled the foreign key constraints and all was well. 
 
Lastly, I initially planned to have an evolvesInto column, which contains the pokemonID of the Pokemon the current Pokemon evolves into, however I did not account for Eevee. This was an issue, as Eevee may evolve into many different Pokemon. This caused me to have to change the column to EvolvesFrom instead. Making this change made creating a query to find the evolution chain of Pokemon rather difficult, so it ended up getting cut from the list of queries due to development difficulties.  The EvolvesFrom information remains in the Pokemon table for user reference. 

## A Summary of the Database 

The file size of the database file is 172KB. The database consists of a total of 1338 entries. 

|Table Name     | Cardinality | Arity |
|---------------|:-----------:|:-----:|
|Move 	    |      164 	|   6   | 
|Party 	    |       48 	|   2   |
|Pokemon 	    |      151 	|   9   |
|PokemonType    |      151 	|   3   |
|Trainer 	    |       14 	|   2   |
|Type 	    |       18 	|   1   |
|TypeAdvantages |      792 	|   4   |
 
## A list of the queries implemented in Part 2 (Commands are highlighted in yellow) 

1.	all pokemon types - Prints all Pokemon, along with their types 
2.	all fire type pokemon - Prints all fire type Pokemon. (Fire can be replaced with any pokemon type) 
3.	all pokemon 1 moves - Prints all the moves and move stats that Pokemon 1 can learn. (1 can be replaced by any number ranging from 1-151) 
4.	strongest pokemon 1 moves - Prints all the moves and move stats that Pokemon 1 can learn ordered by power in descending order. (1 can be replaced by any number ranging from 1-151) (Uses order by) 
5.	strongest special pokemon 1 moves - Prints all the special moves and move stats that Pokemon 1 can learn ordered by power in descending order. (1 can be replaced by any number ranging from 1-151) (Uses ORDER BY) 
6.	super-effective type advantages - Prints all type advantages with the given effect (supereffective can be replaced with 'no-effect', 'not-very-effective', or 'normal') 
7.	all super-effective attack types - Show all attacks that are super effective against a Pokemon type combination. (super-effective can be replaced with 'no-effect', 'not-very-effective', or 'normal') 
8.	move types average power - Prints all the average power of all move types that have an attack move) (Uses AVG aggregation, GROUP BY, and ORDER BY) 
all trainers pokemon	 - 
each trainers strongest pokemon
9.	Prints each pokemon trainer and all the pokemon in their party 
10.	- Prints the strongest Pokemon of each trainer, based on highest attack power (Uses GROUP BY, and ORDER BY) 
 
## Requesting Contents of any Table (Commands are highlighted in yellow) 

1.	all pokemon - Prints the contents of the Pokemon table 
2.	all moves - Prints the contents of the Move table 
3.	all types - Prints the contents of the Type table 
4.	all trainers - Prints the contents of the Trainer table 
5.	all parties - Prints the contents of the Party table 
6.	all advantages - Prints the contents of the typeAdvantages table 
 
## A Summary of the Interface 

The interface was created in Java. The libraries it uses are java.sql for handling sql connections, java.lang for string processing, and java.nio.file for use of getting the working directory of the interface so that the pokedex.db file can be loaded gracefully without user interaction. Additionally, the JDBC database access driver was used to interface between Java and SQLite.

## How to Use the Interface

The interface is very simplistic, utilizing a command-line interface. To compile and run the application, open a terminal window in the directory of my project. Next, simply type “make” and press enter.  This will compile all the need files, handle the JDBC driver dependencies and launch the application. At this point, you are free to make any available queries, as listed above.  Additionally, for your convenience, you may enter “help” into the interface to get a list of available commands. To exit the interface, simply type “exit”. 
