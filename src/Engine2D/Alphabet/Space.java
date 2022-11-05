package Engine2D.Alphabet;

import UnityMath.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Space extends Letter{
    public Space(){
        super();
    }
    public Space(int size, Color color){
        super(size, color);
        lines = new ArrayList<>();
    }

    @Override
    public Letter get(int size, Color color) {
        return new Space(size, color);
    }
}