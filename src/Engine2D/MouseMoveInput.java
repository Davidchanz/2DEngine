package Engine2D;

import UnityMath.Vector2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**Mouse listener for mouse click*/
@Deprecated
public class MouseMoveInput extends MouseAdapter {

    /**Find shape uder mouse cursor if exist and set it in active object for moe(set it player)*/
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(e.getButton() == 1){
            Scene.setActiveObject(Scene.findObject(new Vector2(e.getX(), e.getY())));
        }
    }
}
