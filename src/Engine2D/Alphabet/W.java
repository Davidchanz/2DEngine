package Engine2D.Alphabet;

import Engine2D.Rectangle;
import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class W extends Letter{
    public W(){
        super();
    }
    public W(int size, Color color){
        super(size, color);
        lines = new ArrayList<>();
        lines.add(new Rectangle(size*5,size*1, new Vector2(size*0, size*1), color));
        lines.add(new Rectangle(size*5,size*1, new Vector2(size*6, size*1), color));
        lines.add(new Rectangle(size*1,size*1, new Vector2(size*3, size*1), color));
        lines.add(new Rectangle(size*1,size*1, new Vector2(size*1, size*-1), color));
        lines.add(new Rectangle(size*1,size*1, new Vector2(size*5, size*-1), color));
    }

    @Override
    public Letter get(int size, Color color) {
        return new W(size, color);
    }
}