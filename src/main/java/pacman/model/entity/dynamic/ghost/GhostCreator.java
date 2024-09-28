package pacman.model.entity.dynamic.ghost;

import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.KinematicStateImpl;
import pacman.model.entity.dynamic.player.Pacman;

import javafx.scene.image.Image;
import java.util.*;

public class GhostCreator {
    private final Image ghostImage = new Image(getClass().getResourceAsStream("/maze/ghosts/ghost.png"));
    private Vector2D topLeft;
    private final double width = 28;
    private final double height = 28; 
    private BoundingBox boundingBox;
    private KinematicState kinematicState;
    private GhostMode ghostMode = GhostMode.SCATTER;
    private Vector2D[] targetCorner = new Vector2D[4];
    private Direction[] directions = new Direction[2];
    Direction currentDirection;
    
    public GhostImpl ghostBuilder(int x, int y, Pacman pacman) {
        targetCorner[0] = (new Vector2D(0,64));
        targetCorner[1] = (new Vector2D(448, 64));
        targetCorner[2] = (new Vector2D(0, 544));
        targetCorner[3] = (new Vector2D(448, 544));

        directions[0] = Direction.LEFT;
        directions[1] = Direction.RIGHT;

        Random random = new Random();
        Direction randomDirection = directions[random.nextInt(directions.length)];
        Vector2D randomTarget = targetCorner[random.nextInt(targetCorner.length)];

        topLeft = new Vector2D(x, y);
        kinematicState = new KinematicStateImpl.KinematicStateBuilder()
        .setPosition(new Vector2D(x, y)) 
        .setDirection(randomDirection)
        .build();
        boundingBox = new BoundingBoxImpl(topLeft, 28, 28);
        
        return new GhostImpl(this.ghostImage, this.boundingBox, this.kinematicState, this.ghostMode, randomTarget, randomDirection, pacman);
    }
}
