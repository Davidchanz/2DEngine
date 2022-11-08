package Engine2D.Alphabet;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class T extends Letter{
    public T(){
        super();
    }
    public T(int size, Color color){
        super(size, color);
        lines = new ArrayList<>();
        lines.add(new Engine2D.Rectangle(size*4,size*1, new Vector2(size*3, size*0), color));
        lines.add(new Engine2D.Rectangle(size*1,size*4, new Vector2(size*3, size*5), color));
    }

    @Override
    public Letter get(int size, Color color) {
        return new T(size, color);
    }
}