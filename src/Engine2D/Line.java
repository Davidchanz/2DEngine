package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Line extends AbstractShape{
    public enum TYPE{
        HORIZONTAL,
        VERTICAL,
        DIAGONAL,
        ADIAGONAL
    }
    public Vector2 start;
    public Vector2 end;

    public Line(TYPE HV, int size, Vector2 pos, Color c){
        super(c);
        this.size = size;
        if(HV == TYPE.VERTICAL){
            this.start = new Vector2(0, -size);
            this.end = new Vector2(0, size);
        } else if (HV == TYPE.HORIZONTAL) {
            this.start = new Vector2(-size, 0);
            this.end = new Vector2(size, 0);

        } else if (HV == TYPE.DIAGONAL) {
            this.start = new Vector2(-size/2, -size/2);
            this.end = new Vector2(size/2, size/2);

        } else if (HV == TYPE.ADIAGONAL) {
            this.start = new Vector2(-size/2, size/2);
            this.end = new Vector2(size/2, -size/2);

        } else {
            System.out.println("todo exeption and see how java build in class like Color behave when HV != 0 || 1");
            return;
        }
        this.height = (int)Math.abs(this.end.y - this.start.y)/2;
        this.width = (int)Math.abs(this.end.x - this.start.x)/2;
        this.vertices.add(new Vector2(this.start));
        this.vertices.add(new Vector2(this.end));
        this.center = new Vector2((this.start.x + this.end.x)/2,(this.start.y + this.end.y)/2);
        this.position = new Vector2(pos);
    }
    public Line(int height, int width, Vector2 pos, Color c){
        super(c);
        this.height = height;
        this.width = width;
        this.start = new Vector2(-width, height);
        this.end = new Vector2(width, -height);
        this.size = (int)Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
        this.vertices.add(new Vector2(this.start));
        this.vertices.add(new Vector2(this.end));
        this.center = new Vector2((this.start.x + this.end.x)/2,(this.start.y + this.end.y)/2);
        this.position = new Vector2(pos);
    }
    public Line(Vector2 start, Vector2 end, Vector2 pos, Color c){
        super(c);
        this.start = new Vector2(start);
        this.end = new Vector2(end);
        this.size = (int)Math.sqrt(((start.x - end.x) * (start.x - end.x)) + ((start.y - end.y) * (start.y - end.y)));
        this.center = new Vector2((this.start.x + this.end.x)/2,(this.start.y + this.end.y)/2);

        /*this.start = new Vector2(start.sub(this.center));
        this.end = new Vector2(end.sub(this.center));
        this.position = new Vector2(this.center);
        this.center = new Vector2((this.start.x + this.end.x)/2,(this.start.y + this.end.y)/2);*/

        this.vertices.add(new Vector2(this.start));
        this.vertices.add(new Vector2(this.end));

        this.position = new Vector2(pos);
    }
    @Override
    public void paint(Graphics g, ShapesObject o) {
        ArrayList<Vector2> dots = getVertices(this.vertices);
        if(dots == null) return;
        if(this.colored) g.setColor(this.color);
        else g.setColor(Color.BLACK);
        Brezenheim(dots.get(0), dots.get(1), g);

        if(this.CENTER) {
            Vector2 zero = getVertices(this.center);
            g.setColor(Color.RED);
            g.fillRect((int) zero.x, (int) zero.y, 2, 2);
        }
    }

    @Override
    public void resize() {
        this.vertices.clear();
        this.start = new Vector2(-width, height);
        this.end = new Vector2(width, -height);
        this.vertices.add(new Vector2(this.start));
        this.vertices.add(new Vector2(this.end));
        this.center = new Vector2((this.start.x + this.end.x)/2,(this.start.y + this.end.y)/2);
    }
}
