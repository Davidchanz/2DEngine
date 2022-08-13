package Engine2D;

import UnityMath.Vector2;
import UnityMath.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**Class for Rectangles*/
public class Rectangle extends AbstractShape{
    public Triangle Top;
    public Triangle Bot;

    public Rectangle(int size, Vector2 pos, Color c){
        this(new Vector2(-1,-1), new Vector2(1,-1), new Vector2(1,1), new Vector2(-1,1), size, pos, c);
        this.height = 1;
        this.width = 1;
    }
    public Rectangle(int height, int width, Vector2 pos, Color c){
        this(new Vector2(-width,-height), new Vector2(width,-height), new Vector2(width,height), new Vector2(-width,height), 1, pos, c);
        this.height = height;
        this.width = width;
    }

    public Rectangle(Vector2 P0, Vector2 P1, Vector2 P2, Vector2 P3, int size, Vector2 pos, Color c){
        super(c);
        this.size = size;
        this.position = new Vector2(pos);
        this.Top = new Triangle(P0, P1, P2, size, pos, this.color);
        this.Bot = new Triangle(P0, P3, P2, size, pos, this.color);
        this.center = new Vector2((Top.center.x + Bot.center.x)/2,(Top.center.y + Bot.center.y)/2);
        this.vertices.addAll(this.Top.vertices);
        this.vertices.add(this.Bot.vertices.get(1));
    }

    /**Reload whith new coord or size*/
    public void resize(){
        Vector2 P0 = new Vector2(-width,-height);
        Vector2 P1 = new Vector2(width,-height);
        Vector2 P2 = new Vector2(width,height);
        Vector2 P3 = new Vector2(-width,height);
        this.Top = new Triangle(P0, P1, P2, this.size, this.position, this.color);
        this.Bot = new Triangle(P0, P3, P2, this.size, this.position, this.color);
        this.vertices.clear();
        this.vertices.addAll(this.Top.vertices);
        this.vertices.add(this.Bot.vertices.get(1));
        this.center = new Vector2((Top.center.x + Bot.center.x)/2,(Top.center.y + Bot.center.y)/2);
    }

    /**Paint Rectangle*/
    @Override
    public void paint(Graphics g, ShapesObject o) {
        ArrayList<Vector2> dots = getVertices(this.vertices);
        if(dots == null) return;
        if(colored) {
            g.setColor(this.color);
            DrawFilledTriangle(dots.get(0), dots.get(1), dots.get(2), g, o);
            DrawFilledTriangle(dots.get(0), dots.get(3), dots.get(2), g, o);
        }
        g.setColor(Color.BLACK);
        Brezenheim(dots.get(0), dots.get(1), g);
        Brezenheim(dots.get(1), dots.get(2), g);
        Brezenheim(dots.get(2), dots.get(3), g);
        Brezenheim(dots.get(3), dots.get(0), g);

        if(this.CENTER) {
            Vector2 zero = getVertices(this.center);
            g.setColor(Color.RED);
            g.fillRect((int) zero.x, (int) zero.y, 2, 2);
        }
    }
}
