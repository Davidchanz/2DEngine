package Engine2D;

import UnityMath.Vector2;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Scene extends JPanel {
    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    //add some cooment
    public static ArrayList<ShapeObject> objects = new ArrayList<>();
    private boolean Vaxis = false;
    private boolean Vcenter = false;
    public static Camera camera = new Camera();
    public static ShapeObject[][] O_BUFFER;
    public int MaxX;
    public int MaxY;
    public int MinX;
    public int MinY;
    private int bordersize = 0;
    private final ShapeObject border = new ShapeObject("Border", 78);
    private boolean Vborder = false;
    //do some
    public Scene(int w, int h){
        super();
        WIDTH = w;
        HEIGHT = h;

        var tmp = new Vector2(w, h);
        fromSceneCoord(tmp);
        MaxX = (int)tmp.x;
        MinY = (int)tmp.y;

        tmp = new Vector2(0,0);
        fromSceneCoord(tmp);
        MinX = (int)tmp.x;
        MaxY = (int)tmp.y;

        O_BUFFER = new ShapeObject[WIDTH][HEIGHT];
        for(var i: O_BUFFER){
            Arrays.fill(i, new ShapeObject());
        }
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                Scene.WIDTH = getWidth();
                Scene.HEIGHT = getHeight();
                O_BUFFER = new ShapeObject[WIDTH][HEIGHT];
                for(var i: O_BUFFER){
                    Arrays.fill(i, new ShapeObject());
                }
            }
        });
    }
    public void setBorder(int size, Color c){
        this.bordersize = size;
        this.border.add(new Rectangle(bordersize/4, MaxX-1, new Vector2(0, MaxY-bordersize/4), c));
        this.border.add(new Rectangle(bordersize/4, MaxX-1, new Vector2(0, MinY+bordersize/4+1), c));
        this.border.add(new Rectangle(MaxY-1, bordersize/4, new Vector2(MaxX-bordersize/4-1, 0), c));
        this.border.add(new Rectangle(MaxY-1, bordersize/4, new Vector2(MinX+bordersize/4, 0), c));
    }
    /**Show scene border*/
    public void setBorderVisible(boolean t){
        if(t && !Vborder) {
            this.Vborder = true;
        }
    }

    /**Load map from file. Version for block's games*/
    @Deprecated
    public void loadMap(String path){
        /*try{
            this.map = Map.loadMap(path);
            camera.position = moveInput.object.position;//todo this is lock camera to player
            useMap = true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            useMap = false;
        }*/
    }
    /**Show XOY coord axis*/
    public void setCoordVisible(boolean b){
        this.Vaxis = b;
    }

    /**Show XOY center objects*/
    public void setCenterVisible(boolean b){
        this.Vcenter = b;
    }

    /**find object in radius of object size*/
    protected static ShapeObject findObject(Vector2 onPoint){//todo need testing
        for(int i = objects.size()-1; i >= 0; --i) {
            for(var shape: objects.get(i).body) {
                int radius = shape.size;
                int positionX = (int) onPoint.x;
                int positionY = (int) onPoint.y;
                //System.out.println("X: " + positionX + " Y:" + positionY);

                Vector2 tmp = shape.getVertices(shape.center);
                float positionCenterX = tmp.x;
                float positionCenterY = tmp.y;
                if (Math.sqrt(Math.pow((double) (positionX - positionCenterX), 2.0) + Math.pow((double) (positionY - positionCenterY), 2.0)) <= Math.sqrt(radius * radius)) {
                    //System.out.println("X: " + positionCenterX + " Y:" + positionCenterY);
                    return objects.get(i);
                }
            }
        }
        return null;
    }

    /**Set object for moving*/
    @Deprecated
    public static void setActiveObject(ShapeObject o){
        //moveInput.setMovedObject(o);
    }

    /**Offset form XOY coords to Screen coords ([0,0] = [WIDTH/2, HEIGHT/2])*/
    public static void toSceneCoord(Vector2 point){
        point.x = point.x + WIDTH/2;
        point.y = -(point.y - HEIGHT/2);
    }

    public static void fromSceneCoord(Vector2 point){
        point.x = point.x - WIDTH/2;
        point.y = HEIGHT/2 - point.y;
    }

    /**Remove shape from scene*/
    public void remove(ShapeObject o){
        objects.remove(o);
    }

    /**Add new shape on scene*/
    public void add(ShapeObject o){
        objects.add(o);
    }

    public void addAll(Collection<ShapeObject> o){
        objects.addAll(o);
    }

    /**Paint on the Image ande draw it*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Create an off-screen image and fill a rectangle with red
        int w = this.getWidth();
        int h = this.getHeight();
        Image offScreenImage = this.createImage(w, h);
        Graphics imageGraphics = offScreenImage.getGraphics();
        draw(imageGraphics);
        // Draw the offscreen image on the JPanel
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**Draw function use g for reference on image graphics*/
    private void draw(Graphics g){
        if(this.Vaxis) {
            g.setColor(Color.BLACK);
            g.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
            g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
            Vector2 zero = new Vector2(0, 0);
            toSceneCoord(zero);
            g.setColor(Color.RED);
            g.fillRect((int) zero.x-1, (int) zero.y-1, 2, 2);
        }
        /*for (var it : objects) {
            if(this.Vcenter) {
                Vector2 tmp = new Vector2(it.center);
                toSceneCoord(tmp);
                g.setColor(Color.MAGENTA);
                g.fillRect((int) tmp.x, (int) tmp.y, 3, 3);
            }
            for(var shape: it.body) {
                shape.paint(g, it);
            }
        }*/
        if(Vborder) {
            for (var shape : this.border.body) {
                shape.paint(g, this.border);
            }
        }
        for (var it : objects.toArray(new ShapeObject[0])) {
            if(this.Vcenter) {
                Vector2 tmp = new Vector2(it.center);
                toSceneCoord(tmp);
                g.setColor(Color.MAGENTA);
                g.fillRect((int) tmp.x, (int) tmp.y, 3, 3);
            }
            for(var shape: it.body.toArray(new AbstractShape[0])) {
                shape.paint(g, it);
            }
        }
    }
}