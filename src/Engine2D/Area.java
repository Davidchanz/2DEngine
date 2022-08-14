package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Area extends AbstractShape{
    public double start;
    public double end;
    public Area(int radius, double start, double end, Vector2 pos, Color c){
        super(c);
        this.height = radius;//ini height
        this.width = radius;//ini width
        this.start = start;
        this.end = end;
        this.position = new Vector2(pos);//ini position
        this.center = new Vector2(0,0);//ini center 0,0
        for(double angle = start; angle <= end; angle += 1.0/Math.min(this.width, this.height)) {
            double x = this.width * Math.sin(angle);
            double y = this.height * Math.cos(angle);
            this.vertices.add(new Vector2((float) x,(float) y));//add point in vertices
        }
    }
    @Override
    public void paint(Graphics g, ShapeObject o) {
        ArrayList<Vector2> dots = getVertices(this.vertices);//get vertices for paint in screen dimension
        Vector2 zero = getVertices(this.center);//get object center in screen dimension
        if(dots == null) return;//if no points for paint return
        if(colored) {//if color flag true fill object
            g.setColor(this.color);//set color object color
            int i = 0;//ini count
            do{//get first vertices point
                Vector2 p2 = dots.get(i);//get i vertices point
                Brezenheim(zero, p2, g);//paint line from first point to i point
                i++;//inc i
            }while(i < (dots.size()));//when paint all points break
        }
        g.setColor(Color.BLACK);//set color BLACK
        for(int i = 0; i < dots.size()-1; ++i){
            Brezenheim(dots.get(i), dots.get(i+1), g);//paint line form i to i+1 point
        }
        Brezenheim(dots.get(dots.size()-1), zero, g);
        Brezenheim(dots.get(0), zero, g);//paint line from last to first point
        if(this.CENTER) {//if center flag true paint center point
            g.setColor(Color.RED);//set color for center point RED
            g.fillRect((int) zero.x, (int) zero.y, 2, 2);//paint center point
        }
    }

    @Override
    public void resize() {
        this.vertices.clear();//clear old vertices
        this.center = new Vector2(this.position);//ini new center TODO
        if(end < start){
            var tmp = start;
            start = end;
            end = tmp;
        }
        for(double angle = start; angle <= end; angle += 1.0/Math.min(this.width, this.height)) {
            double x = this.width * Math.sin(angle);
            double y = this.height * Math.cos(angle);
            this.vertices.add(new Vector2((float) x,(float) y));//add new vertices
        }
    }
}
