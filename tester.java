

public class tester{


// Run using 
// javac tester.java
// java -cp /eecs/home/harp95/3421/A2/postgresql-42.2.5.jar:. tester

public static void main(String[] args) {

    System.out.println("-------- PostgreSQL JDBC Connection Testing ------------\n");

    Assignment2 a2 = new Assignment2();

    //add your username and password below
    boolean connected = a2.connectDB("jdbc:postgresql://db:5432/<username>", "<username>", "<student number>");
    if (connected){
        System.out.println("Database connected");
    }

    boolean insertedPlayer = a2.insertPlayer(55, "Goraya", 28, 1);
    if (insertedPlayer){
        System.out.println("Player");
    }
    else {
        System.out.println("Cannot insert player!");
    }
    
    }

}
