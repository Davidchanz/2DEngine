package Engine2D;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Circle extends AbstractShape{

    public Circle(int height, int width, Vector2 pos, Color c){
        super(c);
        this.size = 1;
        this.height = height;
        this.width = width;
        this.position = new Vector2(pos);
        this.center = new Vector2(0,0);
        for(double angle = 0.0; angle <= Math.PI * 2; angle += 1.0/Math.min(this.width, this.height)) {
            double x = this.width * Math.sin(angle);
            double y = this.height * Math.cos(angle);
            this.vertices.add(new Vector2((float) x,(float) y));
        }

        /*double n = (2 * Math.PI) * Math.min(this.width, this.height);
        double radius = Math.min(this.width, this.height)/n;
        for (double angle = 0.0; angle < (2.0 * Math.PI); angle += (2 * Math.PI) / n)
        {
            double x = (((this.width) * Math.cos(angle + (Math.PI / n)/(n / 2))));
            double y = (((this.height) * Math.sin(angle + (Math.PI / n)/(n / 2))));

            this.vertices.add(new Vector2((float) x,(float) y));
        }*/

    }
    public Circle(int size, Vector2 pos, Color c){
        super(c);
        this.position = new Vector2(pos);
        this.center = new Vector2(0,0);
        this.size = size;
        this.height = size;
        this.width = size;
        for(double angle = 0.0; angle <= Math.PI * 2; angle += 1.0/ this.width) {
            double x = this.width * Math.sin(angle);
            double y = this.height * Math.cos(angle);
            this.vertices.add(new Vector2((float) x,(float) y));
        }

        /*double n = (2 * Math.PI) * size;
        double radius = size/n;
        for (double angle = 0.0; angle < (2.0 * Math.PI); angle += (2 * Math.PI) / n)
        {
            double x = (((radius * n) * Math.cos(angle + (Math.PI / n)/(n / 2))));
            double y = (((radius * n) * Math.sin(angle + (Math.PI / n)/(n / 2))));

            this.vertices.add(new Vector2((float) x,(float) y));
        }*/
    }

    @Override
    public void paint(Graphics g, ShapesObject o) {
        //todo
        ArrayList<Vector2> dots = getVertices(this.vertices);
        if(dots == null) return;
        if(colored) {
            g.setColor(this.color);
            int i = 0;
            do{
                Vector2 p1 = dots.get(0);
                Vector2 p2 = dots.get(i);
                Brezenheim(p1, p2, g);
                i++;
            }while(i < (dots.size()));

            /*int i = 1;
            do{
                Vector2 p1 = dots.get(i);
                Vector2 p2 = dots.get(dots.size()-i);
                Brezenheim(p1, p2, g);
                i++;
            }while (i < (dots.size()));*/


        }
        g.setColor(Color.BLACK);
        for(int i = 0; i < dots.size()-1; ++i){
            Brezenheim(dots.get(i), dots.get(i+1), g);
        }
        Brezenheim(dots.get(dots.size()-1), dots.get(0), g);

        if(this.CENTER) {
            Vector2 zero = getVertices(this.center);
            g.setColor(Color.RED);
            g.fillRect((int) zero.x, (int) zero.y, 2, 2);
        }
        //g.fillOval((int)this.position.x, (int)this.position.y, this.radius, this.radius);
    }

    @Override
    public void resize() {
        this.vertices.clear();
        this.center = new Vector2(this.position);
        for(double angle = 0.0; angle <= Math.PI * 2; angle += 1.0/Math.min(this.width, this.height)) {
            double x = this.width * Math.sin(angle);
            double y = this.height * Math.cos(angle);
            this.vertices.add(new Vector2((float) x,(float) y));
        }
    }
}
