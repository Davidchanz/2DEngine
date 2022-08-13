package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

/**Interface for Sahpes on scene*/
public interface ShapeMethods {
    void paint(Graphics g, ShapesObject o);
    ArrayList<Vector2> getVertices(ArrayList<Vector2> vertices);
    Vector2 getVertices(Vector2 vertices);
    void resize();
}
