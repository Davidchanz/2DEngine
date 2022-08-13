package Engine2D;

import UnityMath.Vector2;

import java.awt.*;

public class Dot extends AbstractShape{
    public Dot(Vector2 dot, int size, Color c){
        super(c);
        this.vertices.add(new Vector2(dot));
        this.center = new Vector2(dot);
        this.position = new Vector2(0,0);
        this.size = size;
    }
    @Override
    public void paint(Graphics g, ShapesObject o) {
        Vector2 tmp = getVertices(this.vertices.get(0));
        g.setColor(this.color);
        g.fillRect((int)tmp.x, (int)tmp.y, this.size, this.size);
    }

    @Override
    public void resize() {

    }
}
