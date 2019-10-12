// Imports here

/**
 * Runs our program. Creates a Graph object and calls its go method as well as
 * handles exceptions if they occur
 *
 * @author Evert Ball
 * @version 01 November 2019
 *
 */
public class Driver {

    public static void main(String[] args) {
        try {

            Graph graph = new Graph();

        }catch(IllegalArgumentException iae) {
            System.err.println("Source and destination vertices " + 
                "not found in graph");
        } // end try-catch

    } // end main method

} // end Driver class
