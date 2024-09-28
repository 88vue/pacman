package pacman.view.userInterface;
import pacman.model.entity.Renderable;

import java.util.*;

public interface Observer <T, U> {
    void update(T input);
    
    U getRenderables();
}
