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

/**Scene for 2D graphics*/
public class Scene extends JPanel {
    public static int WIDTH = 0;//Scene width
    public static int HEIGHT = 0;//Scene height
    public static ArrayList<ShapeObject> objects = new ArrayList<>();//set objects for painting
    private boolean Vaxis = false;//flag show axis XOY on scene
    private boolean Vcenter = false;//flag show objects centers
    public static Camera camera = new Camera();//camera
    public static ShapeObject[][] O_BUFFER;//TODO
    public int MaxX;//Max possible x
    public int MaxY;//Max possible y
    public int MinX;//Min possible x
    public int MinY;//Min possible y
    private final ShapeObject border = new ShapeObject("Border", 78);//Scene border
    private boolean Vborder = false;//flag show scene border
    /**Scene constructor
     * ini width, height, maxX, maxY, minX, minY, */
    public Scene(int w, int h){
        super();//invoke JPanel constructor
        WIDTH = w;//ini width
        HEIGHT = h;//ini height

        var tmp = new Vector2(w, h);
        toScreenDimension(tmp);//get w, h in scene dimension
        MaxX = (int)tmp.x;//ini MaxX
        MinY = (int)tmp.y;//ini MaxY

        tmp = new Vector2(0,0);
        toScreenDimension(tmp);//get w, h in scene dimension
        MinX = (int)tmp.x;//ini MinX
        MaxY = (int)tmp.y;//ini MinY
        //TODO
            O_BUFFER = new ShapeObject[WIDTH][HEIGHT];
            for(var i: O_BUFFER){
                Arrays.fill(i, new ShapeObject());
            }
        //TODO
        this.setSize(new Dimension(WIDTH, HEIGHT));//ini scene size
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));//ini scene preferred size
        this.setVisible(true);//set scene visible
        this.addComponentListener(new ComponentAdapter() {//add action on scene size change
            public void componentResized(ComponentEvent componentEvent) {
                WIDTH = getWidth();//ini new Scene width
                HEIGHT = getHeight();//ini new scene height
                //TODO
                    O_BUFFER = new ShapeObject[WIDTH][HEIGHT];
                    for(var i: O_BUFFER){
                        Arrays.fill(i, new ShapeObject());
                    }
                //TODO
            }
        });
    }
    /**Method set Border
     * set border size and color*/
    public void setBorder(int size, Color c){
        this.border.add(new Rectangle(size, MaxX-1, new Vector2(0, MaxY-size), c));//ini border top
        this.border.add(new Rectangle(size, MaxX-1, new Vector2(0, MinY+size+1), c));//ini border bot
        this.border.add(new Rectangle(MaxY-1, size, new Vector2(MaxX-size-1, 0), c));//ini border right
        this.border.add(new Rectangle(MaxY-1, size, new Vector2(MinX+size, 0), c));//ini border left
    }
    /**Show scene border*/
    public void setBorderVisible(boolean t){
        if(t && !Vborder) {
            this.Vborder = true;//set border show flag true
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
        this.Vaxis = b;//set axis show flag true
    }
    /**Show XOY center objects*/
    public void setCenterVisible(boolean b){
        this.Vcenter = b;//set center show flag true
    }
    /**Find object in radius of object size*/
    protected static ShapeObject findObject(Vector2 onPoint){//todo need testing
        for(var object: objects) {
            for(var shape: object.body) {
                int radius = Math.max(shape.width,shape.height);//ini search radius
                Vector2 positionCenter = shape.getVertices(shape.center);//get shape center in after transform
                if (Math.sqrt(Math.pow(onPoint.x - positionCenter.x, 2.0) + Math.pow(onPoint.y - positionCenter.y, 2.0)) <= Math.sqrt(radius * radius)) {
                    return object;//if shape radius into search radius we found according shape and return shape's object
                }
            }
        }
        return null;//if we don't find any according shape return null
    }
    /**Set object for moving*/
    @Deprecated
    public static void setActiveObject(ShapeObject o){
        //moveInput.setMovedObject(o);
    }
    /**Get point in scene dimension*/
    public static void toSceneDimension(Vector2 point){
        point.x = point.x + WIDTH/2;
        point.y = -(point.y - HEIGHT/2);
    }
    /**Get point in screen dimension*/
    public static void toScreenDimension(Vector2 point){
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
    /**Add all shape in collection on scene*/
    public void addAll(Collection<ShapeObject> o){
        objects.addAll(o);
    }
    /**Paint on the Image ande draw it*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Create an off-screen image
        Image offScreenImage = this.createImage(WIDTH, HEIGHT);
        Graphics imageGraphics = offScreenImage.getGraphics();//get image graphics
        draw(imageGraphics);//invoke draw method with image graphics
        // Draw the offscreen image on the JPanel
        g.drawImage(offScreenImage, 0, 0, null);
    }
    /**Draw method
     *  paint scene's objects on ImageGraphics*/
    private void draw(Graphics g){
        if(this.Vaxis) {//if flag axis true paint XOY axis
            g.setColor(Color.BLACK);//set color BLACK
            g.drawLine(0, HEIGHT / 2, WIDTH, HEIGHT / 2);//paint -XOX
            g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);//paint -YOY
            Vector2 zero = new Vector2(0, 0);
            toSceneDimension(zero);//get center 0,0 in scene dimension
            g.setColor(Color.RED);//set color RED
            g.fillRect((int) zero.x-1, (int) zero.y-1, 2, 2);//paint O(XOY) - center point
        }
        if(Vborder) {//if flag border true paint scene border
            for (var shape : this.border.body) {
                shape.paint(g, this.border);//paint border's shapes
            }
        }
        for (var it : objects.toArray(new ShapeObject[0])) {
            if(this.Vcenter) {//if flag center true paint shapes centers
                Vector2 tmp = new Vector2(it.center);
                toSceneDimension(tmp);//get shape's center in scene dimension
                g.setColor(Color.MAGENTA);//set color MAGENTA
                g.fillRect((int) tmp.x, (int) tmp.y, 3, 3);//paint shape's center
            }
            for(var shape: it.body.toArray(new AbstractShape[0])) {
                shape.paint(g, it);//paint shapes
            }
        }
    }
}