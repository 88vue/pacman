package pacman;

import javafx.application.Application;
import javafx.stage.Stage;
import pacman.model.engine.GameEngine;
import pacman.model.engine.GameEngineImpl;
import pacman.view.GameWindow;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameWindow window = new GameWindow(448, 576);

        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}