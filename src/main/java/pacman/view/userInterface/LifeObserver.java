package pacman.view.userInterface;

import pacman.model.entity.staticentity.StaticEntity;
import pacman.model.entity.staticentity.StaticEntityImpl;
import pacman.model.entity.Renderable;
import pacman.model.maze.RenderableType;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import javafx.scene.image.Image;


import java.util.*;

public class LifeObserver implements Observer<Integer, ArrayList<Renderable>>{
    private ArrayList<Renderable> lives = new ArrayList<>();
    private Integer numLives;

    public LifeObserver(Integer numLives) {
        this.numLives = numLives;
    }

    public void update(Integer numLives) {
        if(numLives < this.numLives) {
            StaticEntityImpl life = (StaticEntityImpl) lives.get(numLives);
            life.setLayer(Renderable.Layer.INVISIBLE);
            this.numLives -= 1;
        }
    }

    public ArrayList<Renderable> getRenderables() {
        for(int i = 0; i < numLives; i++) {
            Vector2D position = new Vector2D(5 + (24 * i), 550);
            BoundingBox boundingBox = new BoundingBoxImpl(position, 24, 24);
            Image pacman = new Image(getClass().getResourceAsStream("/maze/pacman/playerRight.png"));
            Renderable lifeRender = new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, pacman);
            lives.add(lifeRender);
        }
        return this.lives;
    }
}
