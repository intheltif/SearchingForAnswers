import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Graph made of Vertices.
 *
 * @author Everrt Ball
 * @version 1 November 2019
 *
 */
public class Graph {

    private static final int ADJ_SIZE = 2;
    
    /** 
     * List of vertices in the graph where the index corresponds to the 
     * vertex number.
     */
    private ArrayList<Vertex> vertexList;

    /** List at each vertex corresponds to the vertices adjacent to vertex */
    private ArrayList<ArrayList<Vertex>> adjList;

    /** Value of true represents an edge between two vertices */
    private boolean [][] adjMatrix;

    public Graph(File graphFile) {
        
        // Create two new empty ArrayLists of default capacity of ten elements.
        this.vertexList = new ArrayList<Vertex>();
        this.adjList    = new ArrayList<ArrayList<Vertex>>();
        
        try {
            Scanner in = new Scanner(graphFile);

            while(in.hasNextLine()) {

                int from = in.nextInt();
                int to   = in.nextInt();
                // TODO Figure out a better way to do this
                ArrayList<Vertex> pair = new ArrayList<>(ADJ_SIZE);
                pair.add(0, from);
                pair.add(1, to);

                this.vertexList.add(new Vertex(from));
                this.adjList

            } // end while loop

        } catch(FileNotFoundException fnfe) {
            System.err.println("File not found. Enter an existing file.");
            System.exit(1);
        } catch(InputMismatchException ime) {
            System.err.println("Vertices must be integers. Exiting..."
            System.exit(2);

    } // end Graph constructor
    
    /**
     * Performs the operations described in the handout.
     */
    public void go() {

        // do stuff

    } // end go method
    
    /**
     * Finds the source and destination vertices. On valid input, asks user to
     * enter new vertices.
     *
     * @param
     * @return
     *
     * @throws IllegalArgumentException if the source and destination vertices
     *         are not found in the graph.
     */
    public findSourceDest() throws IllegalArgumentException {

    } // end findSourceDest method

    /**
     * Performs a depth-first search from source to destination or until the
     * search is exhausted.
     *
     * @param start The node to begin our BFS search from.
     * @param dest  The vertex we are searching for.
     */
    public depthFirstSearch(Vertex start, Vertex dest) {

        // Perform BFS

    } // end depthFirstSearch method
    
    /**
     * Performs a search for cycles.
     *
     * @param start The vertex to begin searching the graph for cycles from.
     * @return True if a cycle exists, false if no cycles are present.
     */
    public boolean cycleSearch(Vertex start) {

        // Do a cycle search

    } // end cycleSearch method
    
    /**
     * Uses Warshall's algorithm to find transitive closure of a graph.
     *
     * @param
     * @return
     */
    public void transitiveClosure() {

        // Perform Warshall's algorithm

    } // end transitiveClosure method

    /**
     * Prints the information specified in the handout.
     */
    public void printGraphStats() {

        // Print information specified in the handout

    } // end printGraphStats method

} // end Graph class
