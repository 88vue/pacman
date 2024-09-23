package pacman.model.maze;

import pacman.model.entity.dynamic.DynamicEntity;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.Direction;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.maze.RenderableType;

import javafx.scene.image.Image;

import java.util.*;

public class MazeWall implements Renderable {
    private final BoundingBox boundingBox;
    private Image image;
    private final Vector2D position;
    private final Layer layer = Layer.FOREGROUND;

    public MazeWall(Image image, BoundingBox boundingBox, Vector2D position){
        this.boundingBox = boundingBox;
        this.image = image;
        this.position = position;
    }

    @Override
    public Image getImage(){
        return this.image;
    }

    @Override
    public BoundingBox getBoundingBox(){
        return this.boundingBox;
    }

    @Override
    public double getHeight() {
        return this.boundingBox.getHeight();
    }

    @Override
    public double getWidth() {
        return this.boundingBox.getWidth();
    }

    @Override
    public Vector2D getPosition(){
        return this.position;
    }

    @Override
    public Layer getLayer() {
        return this.layer;
    }

    @Override
    public void reset(){
    }


}
