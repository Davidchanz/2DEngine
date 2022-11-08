package Engine2D.Alphabet;

import Engine2D.Rectangle;
import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class L extends Letter{
    public L(){
        super();
    }
    public L(int size, Color color){
        super(size, color);
        lines = new ArrayList<>();
        lines.add(new Rectangle(size*5,size*1, new Vector2(size*0, size*1), color));
        lines.add(new Rectangle(size*1,size*4, new Vector2(size*3, size*-3), color));
    }

    @Override
    public Letter get(int size, Color color) {
        return new L(size, color);
    }
}