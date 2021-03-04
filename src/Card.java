import AlexsGameEnhancers.Vector;

import java.awt.*;

public class Card {

    private int value;

    private Image cardValue;
    private Image hiddenCard;

    private boolean hidden;

    public Card(int value, Image cardValue, Image hiddenCard) {
        this.value = value;
        this.cardValue = cardValue;

        this.hiddenCard = hiddenCard;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean getHiden() {
        return hidden;
    }

    public int getValue() {
        return value;
    }

    public void drawSelf(Graphics g, Vector location, Dimension size) {
        if(hidden) {
            g.drawImage(hiddenCard, (int) location.getXDirection(), (int) location.getYDirection(), (int) size.getWidth(), (int) size.getHeight(), null);
        } else {
            g.drawImage(cardValue, (int) location.getXDirection(), (int) location.getYDirection(), (int) size.getWidth(), (int) size.getHeight(), null);
        }
    }
}
