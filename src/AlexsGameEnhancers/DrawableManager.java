package AlexsGameEnhancers;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;

public class DrawableManager {
    private HashMap<Integer, LinkedList<Drawable>> drawables;

    public DrawableManager(int layers) {
        drawables = new HashMap<>();
		for(int i = 0; i < layers; i++) {
			drawables.put(i, new LinkedList<>());
		}

    }

    public void drawAll(Graphics graphics) {
        for(int i = 0; i < drawables.size();i++) {
            for(int g = 0; g < drawables.get(i).size(); g++) {
				drawables.get(i).get(g).drawSelf(graphics);
			}
        }
    }

    public void addDrawable(Drawable drawable, int layer) {
        drawables.get(layer).add(drawable);
	}
	
	public void removeDrawable(Drawable drawable) {
		for(Integer key: drawables.keySet()) {
			drawables.get(key).remove(drawable);
		}
	}
}
