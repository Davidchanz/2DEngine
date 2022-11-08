package Engine2D.Alphabet;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class F extends Letter{
    public F(){
        super();
    }
    public F(int size, Color color){
        super(size, color);
        lines = new ArrayList<>();
        lines.add(new Engine2D.Rectangle(size*5,size*1, new Vector2(size*0, size*1), color));
        lines.add(new Engine2D.Rectangle(size*1,size*3, new Vector2(size*4, size*5), color));
        lines.add(new Engine2D.Rectangle(size*1,size*3, new Vector2(size*3, size*1), color));
    }

    @Override
    public Letter get(int size, Color color) {
        return new F(size, color);
    }
}