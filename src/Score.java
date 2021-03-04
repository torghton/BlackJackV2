import AlexsGameEnhancers.Drawable;
import AlexsGameEnhancers.Vector;

import java.awt.*;

public class Score implements Drawable {

    private int playerPoints;
    private int dealerPoints;

    private Vector location;

    public Score() {
        playerPoints = 0;
        dealerPoints = 0;

        location = new Vector(100, 100);
    }

    public void setPosition(Vector location) {
        this.location = location;
    }

    public void setPosition(int x, int y) {
        setPosition(new Vector(x, y));
    }

    public void incrementPlayerPoints() {
        playerPoints++;
    }

    public void incrementDealerPoints() {
        dealerPoints++;
    }


    @Override
    public void drawSelf(Graphics g) {
        g.setFont(new Font("text", 3, 50));
        g.drawString("You: " + playerPoints, (int) location.getXDirection(), (int) location.getYDirection());
        g.drawString("Dealer: " + dealerPoints, (int) location.getXDirection(), (int) location.getYDirection() + 60);
    }
}
