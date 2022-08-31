package Engine2D.Alphabet;

import Engine2D.Line;
import UnityMath.Vector2;

import java.awt.*;

public class A extends Letter{
    public A(){
        lines.add(new Line(new Vector2(1,1), new Vector2(1, 4), new Vector2(0, 0), Color.BLACK));
        lines.add(new Line(new Vector2(2,5), new Vector2(3, 5), new Vector2(0, 0), Color.BLACK));
        lines.add(new Line(new Vector2(4,4), new Vector2(4, 1), new Vector2(0, 0), Color.BLACK));
        lines.add(new Line(new Vector2(2,4), new Vector2(3, 4), new Vector2(0, 0), Color.BLACK));

        //lines.add(new Line(4,0, new Vector2(0, 0), Color.BLACK));
        //lines.add(new Line(4,0, new Vector2(4, 0), Color.BLACK));
        //lines.add(new Line(0,0, new Vector2(0, 0), Color.BLACK));
        //lines.add(new Line(0,0, new Vector2(0, 0), Color.BLACK));
    }
}