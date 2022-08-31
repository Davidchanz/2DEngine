package Engine2D;

import Engine2D.Alphabet.A;
import Engine2D.Alphabet.Letter;
import UnityMath.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Description extends AbstractShape implements Resizing{
    private String string;
    private int size;
    public static Map<Character, Letter> alphabet = new HashMap<>();
    static {
        alphabet.put('A', new A());
    }
    /**
     * Inner constructor for ini vertices and color members.
     *
     * @param c
     */
    public Description(String str, Vector2 pos, Color c) {
        super(c);
        this.position = new Vector2(pos);
        this.string = str;
        this.size = 10;
        this.center = new Vector2(pos);
    }

    @Override
    public void paint(Graphics g, ShapeObject o) {
        for(var c: this.string.toCharArray()){
            for(var line: alphabet.get(c).lines){
                line.parent = o;
                line.size = this.size;
                line.position.add(new Vector2(5*size, 0));
                line.resize();
                line.paint(g, o);

                line.size = 1.0f/this.size;
                line.resize();
            }
        }
        for(var c: this.string.toCharArray()){
            for(var line: alphabet.get(c).lines){
                line.position.add(new Vector2(-5*size, 0));
            }
        }
    }

    @Override
    public void resize() {

    }
}
