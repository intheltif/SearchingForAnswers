import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        // Get the source and dest vertices
        Vertex[] sourceDest = findSourceDest();
        Vertex source = sourceDest[0];
        Vertex destination = sourceDest[1];

        //Perform DFS and print it to the screen.
        LinkedList<Vertex> dfs = depthFirstSearch(source, destination);
        //System.out.println("STACK: " + dfs.toString());
        System.out.print("[DFS path: " + source.getId() +
                ", " + destination.getId() + "]: ");
        int dfs_size = dfs.size() - 1;
        for(int i=dfs_size; i > 1; i--) {
            System.out.print("Vertex " + dfs.remove(i).getId());
            System.out.print(" -> ");
        }
        System.out.println("Vertex " + dfs.remove(0).getId());
        System.out.println();

        //Perform TC and print
        transitiveClosure();

        //Perform Cycle search and print result
        System.out.print("[Cycle]: ");
        if(cycleSearch(source)) {
            System.out.printf("%19s\n", "Cycle detected");
        } else {
            System.out.printf("%19s\n", "No cycle detected");
        }

    } // end go method
    
    /**
     * Finds the source and destination vertices. On valid input, asks user to
     * enter new vertices.
     *
     * @return An array containing the valid source and destination vertices.
     *
     * @throws IllegalArgumentException if the source and destination vertices
     *         are not found in the graph.
     */
    public Vertex[] findSourceDest() throws IllegalArgumentException {
        boolean validInput = false;
        Vertex[] sourceDestArr = new Vertex[2];
        int from = -1;
        int to = -1;
        System.out.print("Please enter valid source and destination vertices >> ");
        Scanner input = new Scanner(System.in);
        try {
            from = input.nextInt();
            to = input.nextInt();
        } catch (InputMismatchException ime) {
            System.err.println("Vertices must be integers. Try again.");
            System.exit(1);
        }
        input.close();

        try {
            sourceDestArr[0] = this.vertexList.get(from);
            sourceDestArr[1] = this.vertexList.get(to);
        } catch(IndexOutOfBoundsException aioobe) {
            throw new IllegalArgumentException("Invalid vertices supplied. " +
                    "Vertices must exist within Graph.");
        } // end try-catch

        return sourceDestArr;

    } // end findSourceDest method

    /**
     * Performs a depth-first search from source to destination or until the
     * search is exhausted.
     *
     * @param start The node to begin our BFS search from.
     * @param dest  The vertex we are searching for.
     */
    public LinkedList<Vertex> depthFirstSearch(Vertex start, Vertex dest) {
        // Perform BFS
        for(Vertex v : vertexList) {
            v.setColor("white");
        }
        LinkedList<Vertex> stack = new LinkedList<>();
        start.setColor("grey");
        stack.push(start);
        Vertex current = stack.peek();
        System.out.print("[DFS Discovered Vertices: " + start.getId() + ", " +
                dest.getId() + "] ");
        System.out.print("Vertex " + current.getId());
        while(!stack.isEmpty() && !current.equals(dest)) {
            System.out.print(", ");
            ArrayList<Vertex> adjacent = this.adjList.get(current.getId());
            //System.out.println("Adjacent: " + adjacent.toString());
            if(adjacent.size() == 0) {
                Vertex next = stack.pop();
                next.setColor("black");
            }
            for(Vertex neighbor : adjacent) {
                if (neighbor.getColor().equals("white")) {
                    neighbor.setColor("grey");
                    stack.push(neighbor);
                    //adjacent.remove(neighbor);
                    break;
                } else if(neighbor.getColor().equals("grey")) {
                    Vertex finished = stack.pop();
                    finished.setColor("black");
                } else if(neighbor.getColor().equals("black")) {
                    stack.pop();
                }
            }
            current = stack.peek();
            System.out.print("Vertex " + current.getId());
        }
        stack.push(current);
        System.out.println();
        return stack;
    } // end depthFirstSearch method
    
    /**
     * Performs a search for cycles.
     *
     * @param start The vertex to begin searching the graph for cycles from.
     * @return True if a cycle exists, false if no cycles are present.
     */
    public boolean cycleSearch(Vertex start) {
        boolean isCycle = false;
        // Perform BFS
        for(Vertex v : vertexList) {
            v.setColor("white");
        }
        LinkedList<Vertex> stack = new LinkedList<>();
        start.setColor("grey");
        stack.push(start);
        Vertex current = stack.peek();
        while(!stack.isEmpty()) {
            ArrayList<Vertex> adjacent = this.adjList.get(current.getId());
            if(adjacent.size() == 0) {
                Vertex next = stack.pop();
                next.setColor("black");
            }
            for(Vertex neighbor : adjacent) {
                if (neighbor.getColor().equals("white")) {
                    neighbor.setColor("grey");
                    stack.push(neighbor);
                    break;
                } else if(neighbor.getColor().equals("grey")) {
                    Vertex finished = stack.pop();
                    finished.setColor("black");
                    isCycle = true;
                } else if(neighbor.getColor().equals("black")) {
                    stack.pop();
                }
            }
            current = stack.peek();
        }
        return isCycle;
    } // end cycleSearch method
    
    /**
     * Uses Warshall's algorithm to find transitive closure of a graph and
     * prints each newly created edge to the screen.
     */
    public void transitiveClosure() {
        // Perform Warshall's algorithm
        System.out.println("[TC: New Edges] ");
        for(int i = 0; i < adjMatrix.length; i++) {
            for(int j = 0; j < adjMatrix.length; j++) {
                if(adjMatrix[j][i]) {
                    for(int k = 0; k < adjMatrix.length; k++) {
                        if(adjMatrix[j][i] && adjMatrix[i][k]) {
                            if(!adjMatrix[j][k]) {
                                System.out.printf("%17s %10s\n", j, k);
                            }
                            adjMatrix[j][k] = true;
                        }
                    }
                }
            }
        }

    } // end transitiveClosure method

    /**
     * Prints the information specified in the handout.
     */
    public void printGraphStats() {

        // Print information specified in the handout

    } // end printGraphStats method

    //TODO Delete the methods below this point

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
