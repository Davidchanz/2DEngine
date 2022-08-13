package Engine2D;

import UnityMath.Vector2;
import UnityMath.Vector3;

import java.awt.*;
import java.util.ArrayList;

/**Class for Triangle*/
public class Triangle extends AbstractShape{
    public Vector2 P0;
    public Vector2 P1;
    public Vector2 P2;

    public Triangle(int size, Vector2 pos, Color c){
        this(new Vector2(-1,-1), new Vector2(1,-1), new Vector2(0,1), size, pos, c);
        this.height = 1;
        this.width = 1;
    }
    public Triangle(int height, int width, Vector2 pos, Color c){
        this(new Vector2(-0.5f-width,-0.5f-height), new Vector2(0.5f+width,-0.5f-height), new Vector2(0.0f,0.5f+height), 1, pos, c);
        this.height = height;
        this.width = width;
    }
    public Triangle(Vector2 P0, Vector2 P1, Vector2 P2, int size, Vector2 pos, Color c){
        super(c);
        this.P0 = new Vector2(P0);
        this.P1 = new Vector2(P1);
        this.P2 = new Vector2(P2);
        this.size = size;
        this.P0.mul(this.size);
        this.P1.mul(this.size);
        this.P2.mul(this.size);
        this.vertices.add(this.P0);
        this.vertices.add(this.P1);
        this.vertices.add(this.P2);
        this.center = new Vector2((this.P0.x + this.P1.x + this.P2.x)/3,(this.P0.y + this.P1.y + this.P2.y)/3);
        this.position = new Vector2(pos);
    }

    /**Paint Triangle*/
    @Override
    public void paint(Graphics g, ShapesObject o) {
        ArrayList<Vector2> dots = getVertices(this.vertices);
        if(dots == null) return;
        if(this.colored) {
            g.setColor(this.color);
            DrawFilledTriangle(dots.get(0), dots.get(1), dots.get(2), g, o);
        }
        g.setColor(Color.BLACK);
        Brezenheim(dots.get(0), dots.get(1), g);
        Brezenheim(dots.get(1), dots.get(2), g);
        Brezenheim(dots.get(2), dots.get(0), g);

        if(this.CENTER) {
            Vector2 zero = getVertices(this.center);
            g.setColor(Color.RED);
            g.fillRect((int) zero.x, (int) zero.y, 2, 2);
        }
    }

    @Override
    public void resize() {
        //todo
        Vector2 P0 = new Vector2(-width,-height);
        Vector2 P1 = new Vector2(width,-height);
        Vector2 P2 = new Vector2(0.0f,height);
        this.P0 = new Vector2(P0);
        this.P1 = new Vector2(P1);
        this.P2 = new Vector2(P2);
        this.P0.mul(this.size);
        this.P1.mul(this.size);
        this.P2.mul(this.size);
        this.vertices.clear();
        this.vertices.add(this.P0);
        this.vertices.add(this.P1);
        this.vertices.add(this.P2);
        this.center = new Vector2((this.P0.x + this.P1.x + this.P2.x)/3,(this.P0.y + this.P1.y + this.P2.y)/3);
    }
}
