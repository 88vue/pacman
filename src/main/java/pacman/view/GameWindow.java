package pacman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import pacman.model.engine.GameEngine;
import pacman.model.engine.GameEngineImpl;
import pacman.model.entity.Renderable;
import pacman.model.maze.MazeCreator;
import pacman.model.maze.Maze;
import pacman.view.background.BackgroundDrawer;
import pacman.view.background.StandardBackgroundDrawer;
import pacman.view.entity.EntityView;
import pacman.view.entity.EntityViewImpl;
import pacman.view.entity.TextViewImpl;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import pacman.view.keyboard.KeyboardInputHandler;
import javafx.scene.paint.Color;
import pacman.view.userInterface.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for managing the Pac-Man Game View
 */
public class GameWindow {

    public static final File FONT_FILE = new File("src/main/resources/maze/PressStart2P-Regular.ttf");

    private final Scene scene;
    private final Pane pane;
    private final GameEngine model;
    private final Maze maze;
    private final List<EntityView> entityViews;
    private final UiSubject UI;
    private int frameCount = 0; 
    private int secondCount = 0;
    private boolean initialPause1 = true;
    private boolean initialPause2 = false;
    private boolean gameOver1 = false;
    private boolean gameOver2 = false;
    private GameStage gameStage = GameStage.START;
    private int numLives;

    public GameWindow(int width, int height) {
        this.model = GameEngineImpl.getInstance("src/main/resources/config.json");
        this.UI = UiSubject.getInstance();
        this.maze = MazeCreator.getInstance(null);

        pane = new Pane();
        scene = new Scene(pane, width, height);

        entityViews = new ArrayList<>();
        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);
        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);

        BackgroundDrawer backgroundDrawer = new StandardBackgroundDrawer();
        backgroundDrawer.draw(model, pane);

        setUpUIObservers();
        numLives = maze.getNumLives();
    }

    public Scene getScene() {
        return scene;
    }

    public void setUpUIObservers() {
        Observer lives = new LifeObserver(maze.getNumLives());
        Observer score = new ScoreObserver();
        Observer stage = new StartEndObserver();

        UI.attach(lives);
        UI.attach(score);
        UI.attach(stage);
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(34),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        model.startGame();
    }

    private void FramePause() {
        frameCount++;
        if (frameCount >= 100) {
            initialPause1 = false;
            initialPause2 = false;
            frameCount = 0;
            gameStage = GameStage.MIDDLE;
        }
    }

    private void SecondPause() {
        secondCount++;
        if (secondCount >= 147) {
            System.exit(0);
        }
    }

    private void trackChanges() {
        // lost a life
        if (maze.getNumLives() < this.numLives) {
            gameStage = GameStage.START;
            initialPause1 = true;
            this.numLives = maze.getNumLives();
        }
        // lost all lives
        else if (model.getLevel().getNumLives() == 0){
            gameStage = GameStage.LOSS;
            gameOver1 = true;
            model.getLevel().handleGameEnd();
        }
        //next level
        else if (model.getLevel().isLevelFinished() == true) {
            
            model.nextLevel();

            // win
            if (model.getWinStatus() == true) {
                gameStage = GameStage.WIN;
                gameOver1 = true;
                model.getLevel().handleGameEnd();
                return;
            }
            gameStage = GameStage.START;
            initialPause1 = true;
        } 
        
        

    }


    private void draw() {
        // Handle game start
        if (initialPause2 == true) {
            FramePause();
            return;
        }

        //Handle game end
        if (gameOver2 == true) {
            SecondPause();
            return;
        }

        // Track level/life changes
        trackChanges();

        // Updating the UI Observers
        UI.setState(model, gameStage);

        // Update the level entities
        model.tick();

        // Get maze and UI renderables
        List<Renderable> entities = model.getRenderables();
        entities.addAll(UI.getRenderables());

        // Get UI text renderables
        List<EntityView> textEntities = UI.getTextRenderables();

   
        // Adding UI text entities to pane
        for (EntityView textEntity : textEntities) {
            if (pane.getChildren().contains(textEntity.getNode())){
                textEntity.update();
            }
            else{
                pane.getChildren().add((Text) textEntity.getNode());
            }            
        }

        // Adding UI and Maze entities to pane
        for (Renderable entity : entities) {
            boolean notFound = true;
            for (EntityView view : entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update();
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        
        // Handle game pause
        if (initialPause1 == true) {
            frameCount++;
            initialPause2 = true;
            return;
        }

        //Handle game end
        if (gameOver1 == true) {
            secondCount++;
            gameOver2 = true;
        }
    }

    
}
