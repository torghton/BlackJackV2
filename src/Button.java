
import AlexsGameEnhancers.Clickable;
import AlexsGameEnhancers.Vector;
import AlexsGameEnhancers.VisualComponent;

import java.awt.*;

interface MouseData {
    Vector getMouseLocation();
}

public class Button extends VisualComponent implements Clickable {

    private Runnable onReleased;
    private MouseData mouseData;

    private Image image;
    private Image hoverImage;

    private boolean active;

    public Button(Image image, Vector location, Dimension size, Runnable onReleased, MouseData getMouseLoaction) {
        this(image, image, location, size, onReleased, getMouseLoaction);
    }

    public Button(Image image, Image hoverImage, Vector location, Dimension size, Runnable onReleased, MouseData getMouseLoaction) {
        super(location, size);
        this.image = image;
        this.hoverImage = hoverImage;
        this.onReleased = onReleased;

        this.mouseData = getMouseLoaction;

        active = false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOnReleased(Runnable onReleased) {
        this.onReleased = onReleased;
    }

    @Override
    public void drawSelf(Graphics g) {
        if(active) {
            if(mousePositionInBounds(mouseData.getMouseLocation())) {
                g.drawImage(hoverImage, (int) location.getXDirection(), (int) location.getYDirection(), (int) size.getWidth(), (int) size.getHeight(), null);
            } else {
                g.drawImage(image, (int) location.getXDirection(), (int) location.getYDirection(), (int) size.getWidth(), (int) size.getHeight(), null);
            }
        }
    }

    @Override
    public void mouseClicked(Vector position, int mouseButton) {}

    @Override
    public void mousePressed(Vector position, int mouseButton) {}

    @Override
    public void mouseReleased(Vector position, int mouseButton) {
        if(active) {
            if(mousePositionInBounds(position)) {
                onReleased.run();
            }
        }
    }

    private boolean mousePositionInBounds(Vector position) {
        if(position.getXDirection() > location.getXDirection() && position.getXDirection() < location.getXDirection() + size.getWidth()) {
            if(position.getYDirection() > location.getYDirection() && position.getYDirection() < location.getYDirection() + size.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
