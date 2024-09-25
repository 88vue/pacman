package pacman.view.keyboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pacman.model.engine.GameEngine;

/**
 * Responsible for handling keyboard input from player
 */
public class KeyboardInputHandler {
    private GameEngine model;

    public KeyboardInputHandler(GameEngine model) {
        this.model = model;
    }

    public void handlePressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        switch (keyCode) {
            case LEFT:
                this.model.moveLeft();
                break;
            case RIGHT:
                this.model.moveRight();
                break;
            case DOWN:
                this.model.moveDown();
                break;
            case UP:
                this.model.moveUp();
                break;
        };
    }
}
