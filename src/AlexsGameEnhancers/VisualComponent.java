package AlexsGameEnhancers;

import java.awt.*;

public class VisualComponent implements Drawable {

    protected Vector location;
    protected Dimension size;

    public VisualComponent(Vector location, Dimension size) {
        this.location = location;
        this.size = size;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.fillRect((int) location.xDir, (int) location.yDir, size.width, size.height);
    }
}
