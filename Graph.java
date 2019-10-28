import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a Graph made of Vertices.
 *
 * @author Everrt Ball
 * @version 1 November 2019
 *
 */
public class Graph {

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


        try {
            // Scanner to read from the file
            Scanner input = new Scanner(graphFile);

            int max = 0;
            int next = input.nextInt();
            
            // Find the largest vertex number in the file
            while(input.hasNext()) {
                if(max < next) {
                    max = next;
                }
                next = input.nextInt();
            } // end while loop
            
            // Create an ArrayList that is the size of the largest vertex id
            this.vertexList = new ArrayList<>(max);
            
            // Insert the vertices in a sorted order
            for(int i = 0; i <= max; i++) {
                this.vertexList.add(i, new Vertex(i));
            }
            
            // Close the scanner
            input.close();

            // ######## Then build the adjList ##########

            // Create a new scanner so we start at the beginning of the file
            input = new Scanner(graphFile);
            int num_vertices = this.vertexList.size();
            // Create outter ArrayList size # vertices
            this.adjList = new ArrayList<>(num_vertices);

            // Create an empty ArrayList at each index in our adjList
            for(int i = 0; i < vertexList.size(); i++) {
                ArrayList<Vertex> goesTo = new ArrayList<>();
                this.adjList.add(i, goesTo);

            }

            // While there is still items to read, add the relationship to
            // the adjList
            // TODO Simplify this if possible
            while(input.hasNext()) {
                int from = input.nextInt();
                int to = input.nextInt();
                Vertex toVertex = this.vertexList.get(to);
                ArrayList<Vertex> addVertexArrList = this.adjList.get(from);
                addVertexArrList.add(toVertex);
            } // end adjList while loop

            input.close();

            // Finally, build the adjMatrix

            // primative boolean arrays are set to false by default.
            this.adjMatrix = new boolean[num_vertices][num_vertices];

            for(int i=0; i < this.adjList.size(); i++) {
                ArrayList<Vertex> goesTo = this.adjList.get(i);
                for(int j=0; j < goesTo.size(); j++) {
                    adjMatrix[i][goesTo.get(j).getId()] = true;
                }
            }

            printMat(this.adjMatrix);

        } catch(FileNotFoundException fnfe) {
            System.err.println("File not found. Please enter an existing file.");
            System.exit(1);
        } catch(InputMismatchException ime) {
            System.err.println("Vertices must be integers. Exiting...");
            System.exit(2);
        } // end try-catch


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
    public int[] findSourceDest() throws IllegalArgumentException {

        int[] sourceDestArr = new int[2];

        return sourceDestArr;

    } // end findSourceDest method

    /**
     * Performs a depth-first search from source to destination or until the
     * search is exhausted.
     *
     * @param start The node to begin our BFS search from.
     * @param dest  The vertex we are searching for.
     */
    public void depthFirstSearch(Vertex start, Vertex dest) {

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
        //
        // TODO Change. This is just a placeholder
        return false;

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

    public void printVertexList(ArrayList<Vertex> list) {
        
        for(int i = 0; i < list.size(); i++) {
            Vertex element = list.get(i);
            System.out.println("Index " + i + ": " + element.toString());
        }

    } // end printList method

    private void printMat(boolean[][] mat) {
        for (boolean[] booleans : mat) {
            System.out.println(Arrays.toString(booleans));
        }
    }

} // end Graph class
