package pacman.model.maze;

import pacman.model.maze.RenderableType;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.staticentity.StaticEntityImpl;
import pacman.model.entity.Renderable;

import javafx.scene.image.Image;

public class MazeWallCreator {
    public StaticEntityImpl MazeWallBuilder(char type, int x, int y) {
        Vector2D position = new Vector2D(x, y);
        BoundingBox boundingBox = new BoundingBoxImpl(position, 16, 16);
        if(type == RenderableType.HORIZONTAL_WALL){
            return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, new Image(getClass().getResourceAsStream("/maze/walls/horizontal.png")));
        }else if(type == RenderableType.VERTICAL_WALL){
            return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, new Image(getClass().getResourceAsStream("/maze/walls/vertical.png")));
        }else if(type == RenderableType.UP_LEFT_WALL){
            return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, new Image(getClass().getResourceAsStream("/maze/walls/upLeft.png")));
        }else if(type == RenderableType.UP_RIGHT_WALL){
            return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, new Image(getClass().getResourceAsStream("/maze/walls/upRight.png")));
        }else if(type == RenderableType.DOWN_LEFT_WALL){
            return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, new Image(getClass().getResourceAsStream("/maze/walls/downLeft.png")));
        }else if(type == RenderableType.DOWN_RIGHT_WALL){
            return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, new Image(getClass().getResourceAsStream("/maze/walls/downRight.png")));
        }
        return null;
    }
}
