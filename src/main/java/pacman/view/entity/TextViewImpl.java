package pacman.view.entity;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import pacman.model.entity.Renderable;
import javafx.scene.paint.Color;

public class TextViewImpl implements EntityView{
    private boolean delete = false;
    private final Text node;
    private Renderable.Layer layer  = Renderable.Layer.FOREGROUND;
    private String text;
    private int xPos;
    private int yPos;
    private Color color;
    private int x;

    public TextViewImpl(String text, Renderable.Layer layer, int xPos, int yPos, Color color) {
        // Load the custom font from a TTF file
        String fontPath = getClass().getResource("/maze/PressStart2P-Regular.ttf").toExternalForm();
        Font customFont = Font.loadFont(fontPath, 17); // Size of the font
        this.xPos = xPos;
        this.yPos = yPos;
        this.layer = layer;
        this.text = text;
        this.color = color;
        this.x = xPos;
        node = new Text(xPos,yPos, text);
        node.setFont(customFont);
        node.setFill(color);
        update();
    }

    private static double getViewOrder(Renderable.Layer layer) {
        return switch (layer) {
            case BACKGROUND -> 100.0;
            case FOREGROUND -> 100.0;
            case EFFECT -> 25.0;
            case INVISIBLE -> 0.0;
        };
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void update() {
        node.setText(text);
        node.setFill(color);
        node.setX(x);
        node.setVisible(true);
    }

    @Override
    public boolean matchesEntity(Renderable entity) {
        return false;
    }

    @Override
    public void markForDelete() {
        delete = true;
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public boolean isMarkedForDelete() {
        return delete;
    }
}
