// Imports here
import java.io.File;

/**
 * Runs our program. Creates a Graph object and calls its go method as well as
 * handles exceptions if they occur
 *
 * @author Evert Ball
 * @version 01 November 2019
 *
 */
public class Driver {

    private static final int FIRST = 0;
    private static final int FAILURE = 1;
    private static final int SUCCESS = 0;

    public static void main(String[] args) {
        
        if(args.length != 1) {
            System.out.println("Usage is: java Driver <graphFile>");
            System.exit(FAILURE);
        }

        try {

            Graph graph = new Graph(new File(args[FIRST]));
            graph.go();

        }catch(NullPointerException NPE) {
            System.err.println("File does not exist. Try again!");
            System.exit(FAILURE);
        } // end try-catch

        // If we made it here, no errors
        System.exit(SUCCESS);
    } // end main method

} // end Driver class
