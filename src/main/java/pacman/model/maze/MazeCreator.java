package pacman.model.maze;

import pacman.model.maze.MazeWallCreator;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanCreator;
import pacman.model.entity.staticentity.StaticEntityImpl;
import pacman.model.entity.staticentity.collectable.Pellet;
import pacman.model.entity.staticentity.collectable.PelletCreator;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Responsible for creating renderables and storing it in the Maze
 */
public class MazeCreator {

    private final String fileName;
    public static final int RESIZING_FACTOR = 16;

    public MazeCreator(String fileName){
        this.fileName = fileName;
    }

    public Maze createMaze(){
        File f = new File(this.fileName);
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

                    MazeWallCreator wallCreator = new MazeWallCreator();
                    PacmanCreator pacmanCreator = new PacmanCreator();
                    PelletCreator pelletCreator = new PelletCreator();

                    if(row[x] == 'p') {
                        maze.addRenderable((Pacman) pacmanCreator.pacmanBuilder(xCor, yCor), row[x], xCor, yCor);
                    }else if(row[x] == '7'){
                        maze.addRenderable((Pellet) pelletCreator.pelletBuilder(xCor, yCor), row[x], xCor, yCor);
                    }else {
                        maze.addRenderable((StaticEntityImpl) wallCreator.MazeWallBuilder(row[x], x * 16, y * 16), row[x], x * 16, y * 16);
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
            System.out.println("Error");
            exit(0);
        }

        return maze;
    }
}
