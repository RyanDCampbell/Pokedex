
run: 
	javac PokedexUI.java
	javac Database.java
	java -cp .:sqlite-jdbc-3.36.0.3.jar PokedexUI 
