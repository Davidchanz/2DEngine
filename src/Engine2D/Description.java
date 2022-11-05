package Engine2D;

import Engine2D.Alphabet.*;
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
    private int space;
    private static Map<Character, Letter> alphabet = new HashMap<>();
    static {
        alphabet.put(' ', new Space());
        alphabet.put('A', new A());
        alphabet.put('B', new B());
        alphabet.put('C', new C());
        alphabet.put('D', new D());
    }
    private Letter[] string_o;

    /**
     * Inner constructor for ini vertices and color members.
     *
     * @param c
     */
    public Description(String str, Vector2 pos, Color c, int size, int space) {
        super(c);
        this.position = new Vector2(pos);
        this.string = str;
        this.size = size;
        this.center = new Vector2(pos);
        this.space = space;
        string_o = fill();
    }
    private Letter[] fill(){
        Letter[] tmp = new Letter[string.length()];

        int count = 0;
        for(var ch: this.string.toCharArray()){
            tmp[count++] = alphabet.get(ch).get(size, color);
        }
        for(int i = 0; i < tmp.length; i++){
            for(var line: tmp[i].lines){
                line.position.add(new Vector2(size*space*i, 0));
                line.resize();
            }
        }
        return tmp;
    }

    @Override
    public void paint(Graphics g, ShapeObject o) {
        for(var letter: string_o){
            for(var line: letter.lines){
                line.angX = this.angX;
                line.angY = this.angY;
                line.angZ = this.angZ;
                line.parent = o;
                line.paint(g, o);
            }
        }
    }

    @Override
    public void resize() {

    }
}
