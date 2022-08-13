package Engine2D;

import MatrixTranspose.TransponeMatrix;
import UnityMath.Vector2;
import UnityMath.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

//todo
/**Class for scene object which especially is a list of AbstractShape elements*/
public class ShapesObject{
    public final ArrayList<AbstractShape> body;
    public String name;
    public int id;
    private int angX;
    private int angY;
    private int angZ;

    public Vector2 center;
    public Vector2 position;

    public ShapesObject(){
        this.id = 0;
        this.name = "Template";
        this.body = new ArrayList<>();
        this.angX = 0;
        this.angY = 0;
        this.angZ = 0;
        this.center = new Vector2(0,0);
        this.position = new Vector2(0,0);
    }

    public void move(Vector2 dir){
        this.position.add(dir);
        this.center.add(dir);
        for(var i: this.body){
            i.position.add(dir);
        }
    }

    public void setCenterVisible(boolean b){
        for(var shape: this.body) {
            //g.drawString(objects.indexOf(it) + it.body.indexOf(shape) + "", (int)shape.position.x+200, (int)shape.position.y+200);
            shape.CENTER = b;
        }
    }

    public ShapesObject(String name, int id){
        this.id = id;
        this.name = name;
        this.body = new ArrayList<>();
        this.center = new Vector2(0,0);
        this.position = new Vector2(0,0);
    }
    public ShapesObject(String name, int id, Vector2 pos){
        this.id = id;
        this.name = name;
        this.body = new ArrayList<>();
        this.center = new Vector2(0,0);
        this.position = new Vector2(pos);
    }

    /**Add new element on object*/
    public AbstractShape add(AbstractShape o){
        o.id = this.id;
        o.angX += this.angX;
        o.angY += this.angY;
        o.angZ += this.angZ;
        this.body.add(o);
        float sumX=0;
        float sumY=0;
        for(var i: this.body){
            sumX+= i.center.x + i.position.x;
            sumY+= i.center.y + i.position.y;
        }
        this.center = new Vector2(sumX/this.body.size(), sumY/this.body.size());//todo compute
        this.center.add(this.position);
        for(var i: this.body){
            i.parent = this;
        }
        return o;
    }

    /**Add new element collection on object*/
    public void addAll(Collection<AbstractShape> o){
        for(var i : o){
            add(i);
        }
    }

    public int getAngX() {
        return angX;
    }

    public void setAngX(int angX) {
        //int diff = angX - this.angX;
        this.angX = angX;
      //  for(var i: this.body){
       //     i.angX += diff;
      //  }
    }

    public int getAngY() {
        return angY;
    }

    public void setAngY(int angY) {
       // int diff = angY - this.angY;
        this.angY = angY;
       // for(var i: this.body){
       //     i.angY += diff;
       // }
    }

    public int getAngZ() {
        return angZ;
    }

    public void setAngZ(int angZ) {
        //int diff = angZ - this.angZ;
        this.angZ = angZ;
       // for(var i: this.body){
        //    i.angZ += diff;
       // }
    }
}
