package pacman.view.userInterface;

import pacman.model.entity.Renderable;
import pacman.model.engine.GameEngine;
import pacman.view.entity.*;
import pacman.model.maze.MazeCreator;
import pacman.model.maze.Maze;
import java.util.*;

public class UiSubject {

    public static UiSubject instance;
    private Maze maze;
    private int points;
    private int score = 0;
    private int numLives;
    private GameStage stage;
    private List<Observer> observers = new ArrayList<>();
    private List<Renderable> renderables = new ArrayList<>();
    private List<EntityView> textRenderables = new ArrayList<>();

    public UiSubject() {
        this.maze = MazeCreator.getInstance(null);
        this.numLives = maze.getNumLives();
        this.points = 100;
    }

    public static UiSubject getInstance() {
        if (instance == null) {
            instance = new UiSubject();
        }
        return instance;
    }

    public void attach(Observer observer){
        observers.add(observer);
        if (observer instanceof LifeObserver){
            LifeObserver tempObserver = (LifeObserver) observer;
            this.renderables.addAll(tempObserver.getRenderables());
        }else if (observer instanceof ScoreObserver) {
            ScoreObserver tempObserver = (ScoreObserver) observer;
            this.textRenderables.add(tempObserver.getRenderables());
        }else if (observer instanceof StartEndObserver){
            StartEndObserver tempObserver = (StartEndObserver) observer;
            this.textRenderables.add(tempObserver.getRenderables());
        }
            
    }

    public void setState(GameEngine model, GameStage stage) {
        setLives(maze.getNumLives());
        setScore(model.getLevel().getScore());
        setStage(stage);
    }

    public List<Renderable> getRenderables() {
        return this.renderables;
    }

    public List<EntityView> getTextRenderables() {
        return this.textRenderables;
    } 

    private void setLives(int lives) {
        this.numLives = lives;
        
        for (Observer observer : observers){
            if(observer instanceof LifeObserver) {
                observer.update(lives);
            }
        }
    }

    private void setScore(int score) {
        this.score = score;
        for(Observer observer : observers) {
            if (observer instanceof ScoreObserver) {
                observer.update(score);
            }
        }
    }

    private void setStage(GameStage stage) {
        for(Observer observer : observers) {
            if (observer instanceof StartEndObserver) {
                observer.update(stage);
            }
        }
        
    }


    

}
