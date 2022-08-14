package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

/**Interface for Sahpes on scene*/
public interface ShapeMethod {
    /**pint object on scene*/
    void paint(Graphics g, ShapeObject o);
    /**get vertex after transform*/
    ArrayList<Vector2> getVertices(ArrayList<Vector2> vertices);
    /**get vertices after transform*/
    Vector2 getVertices(Vector2 vertices);
    /**resize object*/
    void resize();
}
