// Imports here

/**
 * This class represents a Vertex in a Graph. Each vertex has a unique ID
 * number and a color that represents its current state (visited, unvisited,
 * and processed).
 *
 * @author Evert Ball
 * @version 1 November 2019
 */
public class Vertex {

    /**
     * Unique integer corresponding to a vertex's id number. Vertices are
     * numbered from 0 to the number of vertices - 1
     */
    private int id;

    /** Color/Classification of the vertex */ //TODO Maybe make an enum?
    private String color;

    public Vertex(int id, String color) {
        
        this.id = id;
        this.color = color;

    } // end Vertex constructor

} // end Vertex class
