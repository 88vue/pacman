package pacman.model.entity.dynamic.player;

import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanVisual;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.KinematicStateImpl;

import javafx.scene.image.Image;
import java.util.*;

public class PacmanCreator {
    private final Image closed = new Image(getClass().getResourceAsStream("/maze/pacman/playerClosed.png"));
    private final Image down = new Image(getClass().getResourceAsStream("/maze/pacman/playerDown.png"));
    private final Image left = new Image(getClass().getResourceAsStream("/maze/pacman/playerLeft.png"));
    private final Image right = new Image(getClass().getResourceAsStream("/maze/pacman/playerRight.png"));
    private final Image up = new Image(getClass().getResourceAsStream("/maze/pacman/playerUp.png"));

    private final Map<PacmanVisual, Image> images =  new HashMap<>();

    private Vector2D topLeft;

    private final double width = 23;
    private final double height = 23; 

    private BoundingBox boundingBox;

    private KinematicState kinematicState;

    public Pacman pacmanBuilder(int x, int y) {
        images.put(PacmanVisual.UP, up);
        images.put(PacmanVisual.DOWN, down);
        images.put(PacmanVisual.LEFT, left);
        images.put(PacmanVisual.RIGHT, right);
        images.put(PacmanVisual.CLOSED, closed);
        
        topLeft = new Vector2D(x, y);
        kinematicState = new KinematicStateImpl.KinematicStateBuilder()
        .setPosition(new Vector2D(x, y)) 
        .setDirection(Direction.LEFT)
        .build();
        boundingBox = new BoundingBoxImpl(topLeft, this.height, this.width);

        return new Pacman(closed, this.images, boundingBox, kinematicState);

    }
}
