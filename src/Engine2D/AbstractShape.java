package Engine2D;

import MatrixTranspose.TransponeMatrix;
import UnityMath.Vector2;
import UnityMath.Vector3;

import java.awt.*;
import java.util.ArrayList;

/**Abstract class for Shapes on scene*/
public abstract class AbstractShape implements ShapeMethods{
    public int id;
    public Vector2 position;
    public Vector2 center;
    public ArrayList<Vector2> vertices;
    public int size;
    public Color color;
    public boolean CENTER;
    public boolean colored;
    public int width;
    public int height;
    public int angX = 0;
    public int angY = 0;
    public int angZ = 0;
    public ShapesObject parent;
    protected AbstractShape(Color c){
        this.vertices = new ArrayList<>();
        if(c != null) {this.color = c; this.colored = true;}
        else this.colored = false;
    }

    /**Get vertices in screen coord in camera projection after transformation*/
    @Override
    public Vector2 getVertices(Vector2 vertices) {
        Vector2 tmpPos = ShareRotate();
        Vector2 screen_coord = new Vector2((int) (vertices.x), (int) (vertices.y));
        Scene.toSceneCoord(screen_coord);
        Vector3 newPoint = new Vector3(screen_coord, 0);
        Vector2 sceneCenter = new Vector2(0,0);
        Scene.toSceneCoord(sceneCenter);

        TransponeMatrix.Offset(-(int)sceneCenter.x, -(int)sceneCenter.y, 0, newPoint);
        TransponeMatrix.RotationX(angX + parent.getAngX(), newPoint, 0, 0, 0);
        TransponeMatrix.RotationY(angY + parent.getAngY(), newPoint, 0, 0, 0);
        TransponeMatrix.RotationZ(angZ + parent.getAngZ(), newPoint, 0, 0, 0);
        TransponeMatrix.Offset((int)sceneCenter.x, (int)sceneCenter.y, 0, newPoint);
        //TransponeMatrix.Offset((int) this.position.x, (int) -(this.position.y), 0, newPoint);
        TransponeMatrix.Offset((int) tmpPos.x, (int) -(tmpPos.y), 0, newPoint);


        newPoint = new Vector3(Scene.camera.Projection(new Vector2(newPoint.x, newPoint.y)), 0);

        return new Vector2(newPoint.x, newPoint.y);
    }

    /**Get list of vertices in screen coord in camera projection after transformation*/
    @Override
    public ArrayList<Vector2> getVertices(ArrayList<Vector2> vertices) {
        Vector2 tmpPos = ShareRotate();
        ArrayList<Vector2> dots = new ArrayList<>();
        for (var i : vertices){
            Vector2 screen_coord = new Vector2((int) (i.x), (int) (i.y));
            Scene.toSceneCoord(screen_coord);
            Vector3 newPoint = new Vector3(screen_coord, 0);
            Vector2 sceneCenter = new Vector2(0,0);
            Scene.toSceneCoord(sceneCenter);

            TransponeMatrix.Offset(-(int)sceneCenter.x, -(int)sceneCenter.y, 0, newPoint);
            TransponeMatrix.RotationX(angX+parent.getAngX(), newPoint, 0, 0, 0);
            TransponeMatrix.RotationY(angY+parent.getAngY(), newPoint, 0, 0, 0);
            TransponeMatrix.RotationZ(angZ+parent.getAngZ(), newPoint, 0, 0, 0);
            TransponeMatrix.Offset((int)sceneCenter.x, (int)sceneCenter.y, 0, newPoint);
            //TransponeMatrix.Offset((int) this.position.x, (int) -(this.position.y), 0, newPoint);
            TransponeMatrix.Offset((int) tmpPos.x, (int) -(tmpPos.y), 0, newPoint);

            newPoint = new Vector3(Scene.camera.Projection(new Vector2(newPoint.x, newPoint.y)), 0);

            if(newPoint.x < 0 || newPoint.x >= Scene.WIDTH || newPoint.y < 0 || newPoint.y >= Scene.HEIGHT) return null;
            else dots.add(new Vector2(newPoint.x, newPoint.y));

        }
        return dots;
    }

    private Vector2 ShareRotate(){
        Vector2 screen_coord = new Vector2((int) (this.position.x), (int) (this.position.y));
        Scene.toSceneCoord(screen_coord);
        Vector3 newPoint = new Vector3(screen_coord, 0);
        Vector2 sceneCenter = new Vector2(parent.center);
        Scene.toSceneCoord(sceneCenter);

        TransponeMatrix.Offset(-(int)sceneCenter.x, -(int)sceneCenter.y, 0, newPoint);
        TransponeMatrix.RotationX(parent.getAngX(), newPoint, 0, 0, 0);
        TransponeMatrix.RotationY(parent.getAngY(), newPoint, 0, 0, 0);
        TransponeMatrix.RotationZ(parent.getAngZ(), newPoint, 0, 0, 0);
        TransponeMatrix.Offset((int)sceneCenter.x, (int)sceneCenter.y, 0, newPoint);

        var tmp = new Vector2(newPoint.x, newPoint.y);
        Scene.fromSceneCoord(tmp);
        newPoint.x = tmp.x;
        newPoint.y = tmp.y;

        //newPoint = new Vector3(Scene.camera.Projection(new Vector2(newPoint.x, newPoint.y)), 0);

        return new Vector2(newPoint.x, newPoint.y);
    }

    /**Interpolete to points*///todo explore this function
    public static ArrayList<Integer> Interpolate (float i0, float d0, float i1, float d1) {
        var values = new ArrayList<Integer>();
        if (i0 == i1) {
            values.add((int)d0);
            return values;
        }
        float a = (d1 - d0) / (i1 - i0);
        float d = d0;
        for (int i = (int)i0; i <= (int)i1; ++i) {
            values.add((int)d);
            d = d + a;
        }
        return values;
    }

    /**Draw filled triangle using interpolate*///todo exlpore this function
    public static void DrawFilledTriangle (Vector2 v0, Vector2 v1, Vector2 v2, Graphics g, ShapesObject o) {
        // Сортировка точек так, что y0 <= y1 <= y2
        if (v0.y > v1.y) {
            var tmp = v0;
            v0 = v1;
            v1 = tmp;
        }
        if (v0.y > v2.y) {
            var tmp = v0;
            v0 = v2;
            v2 = tmp;
        }
        if (v1.y > v2.y) {
            var tmp = v1;
            v1 = v2;
            v2 = tmp;
        }

        // Вычисление координат x рёбер треугольника
        var x01 = Interpolate(v0.y, v0.x, v1.y, v1.x);
        var x12 = Interpolate(v1.y, v1.x, v2.y, v2.x);
        var x02 = Interpolate(v0.y, v0.x, v2.y, v2.x);

        //# Конкатенация коротких сторон
        x01.remove(x01.size()-1);

        x01.addAll(x12);
        var x012 = x01;

        // Определяем, какая из сторон левая и правая
        ArrayList<Integer> x_left;
        ArrayList<Integer> x_right;
        var m = x012.size() / 2;
        if (x02.get(m) < x012.get(m)) {
            x_left = x02;
            x_right = x012;
        } else {
            x_left = x012;
            x_right = x02;
        }

        //# Отрисовка горизонтальных отрезков
        for (int y = (int)v0.y; y <= v2.y; ++y){
            for (int x = x_left.get(y - (int)v0.y); x <= x_right.get(y - (int)v0.y); ++x){
                g.drawRect(x, y, 1,1);
                O_BUFFER(x, y, o);
            }
        }
    }

    /**Draw line using intorpolation*///todo exlpore this function
    public static void Brezenheim(Vector2 v1, Vector2 v2, Graphics g) {
        float dx = v2.x - v1.x;
        float dy = v2.y - v1.y;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (v1.x > v2.x) {
                var tmp = v1;
                v1 = v2;
                v2 = tmp;
            }
            var ys = Interpolate(v1.x, v1.y, v2.x, v2.y);
            /*float invslope1 = (v2.y - v1.y) / (v2.x - v1.x);

            float curx1 = v1.y;*/

            for (int scanlineY = (int) v1.x; scanlineY <= v2.x; scanlineY++) {
                g.drawRect(scanlineY, ys.get(scanlineY - (int)v1.x), 1, 1);
                //curx1 += invslope1;
            }
        } else /*if (Math.abs(dx) > Math.abs(dy))*/{
            if (v1.y > v2.y) {
                var tmp = v1;
                v1 = v2;
                v2 = tmp;
            }
            /*float invslope1 = (v2.x - v1.x) / (v2.y - v1.y);

            float curx1 = v1.x;*/
            var xs = Interpolate(v1.y, v1.x, v2.y, v2.x);

            for (int scanlineY = (int) v1.y; scanlineY <= v2.y; scanlineY++) {
                g.drawRect(xs.get(scanlineY - (int)v1.y), scanlineY, 1, 1);
                //curx1 += invslope1;
            }
        } /*else{
            g.drawRect((int)v1.x, (int)v2.y, 1, 1);
        }*/
    }
    //TODO
    public static void O_BUFFER(int x, int y, ShapesObject o){
        if(x >= 0 & x < Scene.WIDTH && y >= 0 & y < Scene.HEIGHT)
            Scene.O_BUFFER[x][y] = o;
    }
}
