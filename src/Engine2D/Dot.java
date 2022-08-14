package Engine2D;

import UnityMath.Vector2;

import java.awt.*;

/**Class Dot*/
public class Dot extends AbstractShape{
    protected int size;//dot's size
    /**Dot constructor
     * ini vertices, ini center, ini position, ini size*/
    public Dot(Vector2 pos, int size, Color c){
        super(c);//AbstractShape constructor
        this.vertices.add(new Vector2(0, 0));//ini vertices
        this.center = new Vector2(0, 0);//ini center
        this.position = new Vector2(pos);//ini position 0,0
        this.size = size;//ini size
    }
    /**Method for paint*/
    @Override
    public void paint(Graphics g, ShapeObject o) {
        Vector2 tmp = getVertices(this.vertices.get(0));//get vertices point in screen dimension
        g.setColor(this.color);//set color object color
        g.fillRect((int)tmp.x, (int)tmp.y, this.size, this.size);//paint dot dot's size
    }
    /**Method resize*/
    @Override
    public void resize() {

    }
}
