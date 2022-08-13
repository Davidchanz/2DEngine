package Engine2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**Key listener for key press*/
@Deprecated
public class KeyMoveInput extends KeyAdapter {
    protected AbstractShape object;

    public void setMovedObject(AbstractShape object){
        this.object = object;
    }

    /**Move player*/
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(object != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> object.position.y++;
                case KeyEvent.VK_S -> object.position.y--;
                case KeyEvent.VK_D -> object.position.x++;
                case KeyEvent.VK_A -> object.position.x--;
            }
        }
    }
}
