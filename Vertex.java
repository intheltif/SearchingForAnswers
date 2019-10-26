/**
 * This class represents a Vertex in a Graph. Each vertex has a unique ID
 * number and a color that represents its current state (visited, unvisited,
 * and processed).
 *
 * @author Evert Ball
 * @version 1 November 2019
 */
public class Vertex {

    private static final String DEFAULT_COLOR = "white";

    /**
     * Unique integer corresponding to a vertex's id number. Vertices are
     * numbered from 0 to the number of vertices - 1
     */
    private int id;

    /** Color/Classification of the vertex */ //TODO Maybe make an enum?
    private String color;
    
    /**
     * Creates a Vertex based on a specified id and the default color. The
     * default color is white (unvisited).
     *
     * @param id The unique integer representing the vertex's id number.
     * @param color The color/classification of the vertex.
     */
    public Vertex(int id) {
        
        this.id = id;
        this.color = DEFAULT_COLOR;

    } // end Vertex constructor
    
    /**
     * Creates a Vertex based on a specified id and color.
     *
     * @param id The unique integer representing the vertex's id number.
     * @param color The color/classification of the vertex.
     */
    public Vertex(int id, String color) {
        
        this.id = id;
        this.color = color;

    } // end Vertex constructor
    
    /**
     * Changes the color of the vertex.
     *
     * @param color The new color of the vertex.
     */
    public void setColor(String color) {

        this.color = color.toLowerCase();

    } // end setColor method

    /**
     * Returns the color of a vertex.
     *
     * @return A String representing the color of a vertex.
     */
    public String getColor() {

        return this.color;

    } // end getColor method

    /**
     * Returns the unique id of a vertex.
     *
     * @return The unique id of a vertex.
     */
    public int getId() {

        return this.id;

    } // end getId method

    public boolean equals(Object other) {
        
        boolean isEqual = false;
        
        Vertex otherVertex = (Vertex)other;
        
        if(this.id == otherVertex.getId()) {
            isEqual = true;
        }
        
        return isEqual;

    } // end equals method

    public String toString() {

        String vertexStr = "ID: " + this.id + "\tCOLOR: " + this.color;

        return vertexStr;

    } // end toString method

} // end Vertex class
