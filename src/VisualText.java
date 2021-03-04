import AlexsGameEnhancers.Drawable;
import AlexsGameEnhancers.Vector;
import AlexsGameEnhancers.VisualComponent;

import java.awt.*;

public class VisualText implements Drawable {

    private String text;

    private Vector location;

    private Font font;

    public VisualText() {
        location = new Vector(100, 100);

        font = new Font("text", 1, 100);

        text = "";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    @Override
    public void drawSelf(Graphics g) {
        g.setFont(font);
        g.drawString(text, (int) location.getXDirection(), (int) location.getYDirection());
    }
}
