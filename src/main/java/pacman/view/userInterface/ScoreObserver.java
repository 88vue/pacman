package pacman.view.userInterface;
import pacman.model.entity.Renderable;
import pacman.view.entity.*;
import javafx.scene.paint.Color;



public class ScoreObserver implements Observer<Integer, EntityView> {
    private Integer scoreNum = 0;
    private EntityView score;

    public void update(Integer scoreNum) {
        this.scoreNum = scoreNum;
        TextViewImpl tempScore = (TextViewImpl) this.score;
        tempScore.setText(String.valueOf(scoreNum));
    }

    public EntityView getRenderables() {
        score = new TextViewImpl("", Renderable.Layer.FOREGROUND, 5, 40, Color.WHITE);
        return score;
    }
}
