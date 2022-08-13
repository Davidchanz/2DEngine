package Engine2D;

import UnityMath.Vector2;

/**Class for 2D camera*/
public class Camera {
    public Vector2 position;

    Camera(){
        this.position = new Vector2(0,0);
    }

    /**Fake camera: move world cords instead camera projection*/
    public Vector2 Projection(Vector2 v){
        int divX = (int)this.position.x;
        int divY = (int)this.position.y;

        return new Vector2(v.x  - divX,v.y - divY);
    }
}
