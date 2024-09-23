package pacman.model.maze;

import pacman.model.maze.MazeWall;
import pacman.model.maze.RenderableType;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;

import javafx.scene.image.Image;

public class MazeWallCreator {
    public MazeWall MazeWallBuilder(char type, int x, int y) {
        Vector2D position = new Vector2D(x, y);
        BoundingBox boundingBox = new BoundingBoxImpl(position, 16, 16);
        if(type == RenderableType.HORIZONTAL_WALL){
            return new MazeWall(new Image(getClass().getResourceAsStream("/maze/walls/horizontal.png")), boundingBox ,position);
        }else if(type == RenderableType.VERTICAL_WALL){
            return new MazeWall(new Image(getClass().getResourceAsStream("/maze/walls/vertical.png")), boundingBox ,position);
        }else if(type == RenderableType.UP_LEFT_WALL){
            return new MazeWall(new Image(getClass().getResourceAsStream("/maze/walls/upLeft.png")), boundingBox ,position);
        }else if(type == RenderableType.UP_RIGHT_WALL){
            return new MazeWall(new Image(getClass().getResourceAsStream("/maze/walls/upRight.png")), boundingBox ,position);
        }else if(type == RenderableType.DOWN_LEFT_WALL){
            return new MazeWall(new Image(getClass().getResourceAsStream("/maze/walls/downLeft.png")), boundingBox ,position);
        }else if(type == RenderableType.DOWN_RIGHT_WALL){
            return new MazeWall(new Image(getClass().getResourceAsStream("/maze/walls/downRight.png")), boundingBox ,position);
        }
        return null;
    }
}
