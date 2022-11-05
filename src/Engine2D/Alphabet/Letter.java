package Engine2D.Alphabet;

import Engine2D.Line;
import Engine2D.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public abstract class Letter{
    private int size;
    private Color color;
    public ArrayList<Rectangle> lines;
    Letter(){}
    Letter(int size, Color color){
        this.size = size;
        this.color = color;
    }
    public abstract Letter get(int size, Color color);
}