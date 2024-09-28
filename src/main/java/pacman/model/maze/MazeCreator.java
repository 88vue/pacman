package pacman.model.maze;

import pacman.model.maze.MazeWallCreator;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanCreator;
import pacman.model.entity.staticentity.StaticEntityImpl;
import pacman.model.entity.staticentity.collectable.Pellet;
import pacman.model.entity.staticentity.collectable.PelletCreator;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostCreator;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Responsible for creating renderables and storing it in the Maze
 */
public class MazeCreator {

    private static Maze instance;
    public static final int RESIZING_FACTOR = 16;

    // Singleton implementation
    public static Maze getInstance(String fileName) {
        
        if (instance == null) {
            instance = createMaze(fileName);
        }
        return instance;
    }

    private static Maze createMaze(String fileName){
        File f = new File(fileName);
        Maze maze = new Maze();

        try {
            Scanner scanner = new Scanner(f);

            int y = 0;

            while (scanner.hasNextLine()){

                String line = scanner.nextLine();
                char[] row = line.toCharArray();

                for (int x = 0; x < row.length; x++){
                    int xCor = x * 16;
                    int yCor = y * 16;
                    
                    PacmanCreator pacmanCreator = new PacmanCreator();
                    Pacman pacman = pacmanCreator.pacmanBuilder(xCor+8, yCor-4);

                    if(row[x] == 'p') {
                        maze.addRenderable(pacman, row[x], xCor+8, yCor-4);
                    }else if(row[x] == '7'){
                        PelletCreator pelletCreator = new PelletCreator();
                        Pellet pellet = pelletCreator.pelletBuilder(xCor, yCor);
                        maze.addRenderable(pellet, row[x], xCor, yCor);
                    }else if(row[x] == 'g'){
                        GhostCreator ghostCreator = new GhostCreator();
                        GhostImpl ghost = ghostCreator.ghostBuilder(xCor+4, yCor-4, pacman);
                        System.out.println(ghost.getTargetLocation());
                        maze.addRenderable(ghost, row[x], xCor+4, yCor-4);
                    }else {
                        MazeWallCreator wallCreator = new MazeWallCreator();
                        StaticEntityImpl wall = wallCreator.MazeWallBuilder(row[x], xCor, yCor);
                        maze.addRenderable(wall, row[x], xCor, yCor);
                    }                    

                }

                y += 1;
            }

            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("No maze file was found.");
            exit(0);
        } catch (Exception e){
            System.out.println(e);
            exit(0);
        }

        return maze;
    }
}
