package pacman.model.engine;

import pacman.model.entity.Renderable;
import java.util.List;
import pacman.model.level.Level;
import pacman.model.maze.Maze;


/**
 * The base interface for interacting with the Pac-Man model
 */
public interface GameEngine {

    /**
     * Gets the list of renderables in the game
     *
     * @return The list of renderables
     */
    List<Renderable> getRenderables();

    /**
     * Starts the game
     */
    void startGame();

    /**
     * Gets the maze instance
     */
    Maze getMaze();

    /**
     * Gets the level instance
     */
    Level getLevel();

    /**
     * Gets the win status
     */
    boolean getWinStatus();

    /**
     * Moves game to next level
     */
    void nextLevel();

    /**
     * Move the player up
     */
    void moveUp();

    /**
     * Move the player down
     */
    void moveDown();

    /**
     * Move the player left
     */
    void moveLeft();

    /**
     * Move the player right
     */
    void moveRight();

    /**
     * Instruct the model to progress forward in time by one increment.
     */
    void tick();
}
