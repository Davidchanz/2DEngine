package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

/**Interface for Sahpes on scene*/
public interface ShapeMethod {
    /**pint object on scene*/
    void paint(Graphics g, ShapeObject o);
    /**resize object*/
    void resize();
}
