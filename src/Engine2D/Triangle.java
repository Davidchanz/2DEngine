package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

/**Class for Triangle*/
public class Triangle extends AbstractShape{
    public Vector2 P0;//first triangle vertice
    public Vector2 P1;//second triangle vertice
    public Vector2 P2;//third triangle vertice
    /**Triangle constructor
     * for equilateral triangle*/
    public Triangle(int size, Vector2 pos, Color c){
        //invoke main triangle constructor
        this(new Vector2(-0.5f-size,-0.5f-size), new Vector2(0.5f+size,-0.5f-size), new Vector2(0.0f,0.5f+size), pos, c);
        this.height = size;//ini height
        this.width = size;//ini width
    }
    /**Triangle constructor
     * for width, height triangle*/
    public Triangle(int height, int width, Vector2 pos, Color c){
        //invoke main triangle constructor
        this(new Vector2(-0.5f-width,-0.5f-height), new Vector2(0.5f+width,-0.5f-height), new Vector2(0.0f,0.5f+height), pos, c);
        this.height = height;//ini height
        this.width = width;//ini width
    }
    /**Triangle main constructor*/
    public Triangle(Vector2 P0, Vector2 P1, Vector2 P2, Vector2 pos, Color c){
        super(c);//ini AbstractShape constructor
        this.P0 = new Vector2(P0);//ini P0
        this.P1 = new Vector2(P1);//ini P1
        this.P2 = new Vector2(P2);//ini P2
        this.vertices.add(this.P0);//add P0 in vertices
        this.vertices.add(this.P1);//add P1 in vertices
        this.vertices.add(this.P2);//add P2 in vertices
        this.center = new Vector2((this.P0.x + this.P1.x + this.P2.x)/3,(this.P0.y + this.P1.y + this.P2.y)/3);//ini center
        this.position = new Vector2(pos);//ini position
        //todo height and width
    }
    /**Method paint*/
    @Override
    public void paint(Graphics g, ShapesObject o) {
        ArrayList<Vector2> dots = getVertices(this.vertices);//get vertices for paint in screen dimension
        if(dots == null) return;//if no points for paint return
        if(this.colored) {//if color flag true fill object
            g.setColor(this.color);//set color object color
            DrawFilledTriangle(dots.get(0), dots.get(1), dots.get(2), g, o);//paint colored triangle
        }
        g.setColor(Color.BLACK);//set color BLACK
        Brezenheim(dots.get(0), dots.get(1), g);//paint triangle's line
        Brezenheim(dots.get(1), dots.get(2), g);//paint triangle's line
        Brezenheim(dots.get(2), dots.get(0), g);//paint triangle's line
        if(this.CENTER) {//if center flag true paint center point
            Vector2 zero = getVertices(this.center);//get object center in screen dimension
            g.setColor(Color.RED);//set color for center point RED
            g.fillRect((int) zero.x, (int) zero.y, 2, 2);//paint center point
        }
    }
    /**Method for resize object*/
    @Override
    public void resize() {
        //todo
        this.P0 = new Vector2(-width,-height);//ini new P0
        this.P1 = new Vector2(width,-height);//ini new P1
        this.P2 = new Vector2(0.0f,height);//ini new P2
        this.vertices.clear();//clear old vertices
        this.vertices.add(this.P0);//add new P in vertices
        this.vertices.add(this.P1);//add new P in vertices
        this.vertices.add(this.P2);//add new P in vertices
        this.center = new Vector2((this.P0.x + this.P1.x + this.P2.x)/3,(this.P0.y + this.P1.y + this.P2.y)/3);//ini new center
    }
}
