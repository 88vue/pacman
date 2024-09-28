package pacman.view.userInterface;
import pacman.model.entity.Renderable;
import pacman.view.entity.*;
import java.util.*;
import javafx.scene.paint.Color;


public class StartEndObserver implements Observer<GameStage, EntityView>{
    private EntityView object;

    public void update(GameStage stage) {
        TextViewImpl tempMessage = (TextViewImpl) this.object;
        if(stage == GameStage.START) {
            tempMessage.setText("READY!");
            tempMessage.setColor(Color.YELLOW);
        }else if (stage == GameStage.MIDDLE) {
            tempMessage.setText("");
        }else if (stage == GameStage.LOSS) {
            tempMessage.setText("GAME OVER");
            tempMessage.setColor(Color.RED);
            tempMessage.setX(150);
        }else if (stage == GameStage.WIN) {
            tempMessage.setText("YOU WIN!");
            tempMessage.setColor(Color.WHITE);
            tempMessage.setX(160);
        }
        
    }

    public EntityView getRenderables() {
        object = new TextViewImpl("READY!", Renderable.Layer.FOREGROUND, 175, 340, Color.YELLOW);
        return object;
    }
}
