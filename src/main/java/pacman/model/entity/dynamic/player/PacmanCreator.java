package pacman.model.entity.dynamic.player;

import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.level.LevelConfigurationReader;

public class PacmanCreator {
    private final Image closed = new Image(getClass().getResourceAsStream("/maze/pacman/playerClosed.png"));
    private final Image down = new Image(getClass().getResourceAsStream("/maze/pacman/playerDown.png"));
    private final Image left = new Image(getClass().getResourceAsStream("/maze/pacman/playerLeft.png"));
    private final Image right = new Image(getClass().getResourceAsStream("/maze/pacman/playerRight.png"));
    private final Image up = new Image(getClass().getResourceAsStream("/maze/pacman/playerUp.png"));

    private final Map<PacmanVisual, Image> images =  new HashMap<>();
        images.put(PacmanVisual.UP, up);
        images.put(PacmanVisual.DOWN, down);
        images.put(PacmanVisual.LEFT, left);
        images.put(PacmanVisual.RIGHT, right);
        images.put(PacmanVisual.CLOSED, closed);

    Vector2D topLeft;

    double width = 16;
    double height = 16; 

    BoundingBox boundingBox;

    KinematicState kinematicState;

    public Pacman pacmanBuilder(int x, int y) {
        levelReader = new LevelConfigurationReader()
        
        topLeft = new Vector2D(x, y);
        kinematicState; = new KinematicStateImpl.KinematicStateBuilder()
        .setPosition(new Vector2D(x, y)) 
        .setDirection(Direction.LEFT)
        .build();
        boundingBox = new BoundingBoxImpl(topLeft, this.height, this.width);

        

    }
}
