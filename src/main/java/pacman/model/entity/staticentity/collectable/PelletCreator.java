package pacman.model.entity.staticentity.collectable;

import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.Renderable;

import javafx.scene.image.Image;

public class PelletCreator {
    private final Image pelletImage = new Image(getClass().getResourceAsStream("/maze/pellet.png"));

    private BoundingBox boundingBox;
    private final Renderable.Layer layer = Renderable.Layer.FOREGROUND;
    private Vector2D topLeft;

    private final double width = 16;
    private final double height = 16;

    public Pellet pelletBuilder(int x, int y){
        topLeft = new Vector2D(x, y);
        boundingBox = new BoundingBoxImpl(topLeft, this.height, this.width);
        
        return new Pellet(boundingBox, this.layer, this.pelletImage, 100);
    }   
}
