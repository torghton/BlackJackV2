import AlexsGameEnhancers.*;

import javax.swing.JPanel;

import java.awt.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;


// TODO: DISPLAY TOTAL POINTS AND POINTS EARNED
public class Table extends JPanel implements KeyListener, MouseListener {

    private final Dimension SCREENSIZE;

    private ImageLoader imageLoader;

    private Cards cards;

    private Updater updater;

    private DrawableManager drawableManager;

    private ClickManager clickManager;

    private KeyInteractor keyInteractor;

    public Table(Dimension SCREENSIZE) {
        setFocusable(true);

        setBackground(Color.WHITE);

        addKeyListener(this);
        addMouseListener(this);

        this.SCREENSIZE = SCREENSIZE;

        imageLoader = new ImageLoader();

        drawableManager = new DrawableManager(10);

        keyInteractor = new KeyInteractor();

        clickManager = new ClickManager();

        updater = new Updater();

        imageLoader.loadImage("BoardBackground", "../assets/Backgrounds/CasinoBoardBackground.jpg");
        imageLoader.loadImage("HitImage", "../assets/ExtraPlayingCards/HitImage.png");
        imageLoader.loadImage("HitImageHovered", "../assets/ExtraPlayingCards/HitImageHovered.png");
        imageLoader.loadImage("StandImage", "../assets/ExtraPlayingCards/StandImage.png");
        imageLoader.loadImage("StandImageHovered", "../assets/ExtraPlayingCards/StandImageHovered.png");
        imageLoader.loadImage("NewGameImage", "../assets/ExtraPlayingCards/NewGameImage.png");
        imageLoader.loadImage("NewGameImageHovered", "../assets/ExtraPlayingCards/NewGameImageHovered.png");
        imageLoader.loadImage("HintImage", "../assets/ExtraPlayingCards/HintImage.png");
        imageLoader.loadImage("HintImageHovered", "../assets/ExtraPlayingCards/HintImageHovered.png");
        imageLoader.loadImage("CardBack", "../assets/ExtraPlayingCards/PlayingCardBack.png");


        int[] cardValues = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        String[] cardValueNames = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        Image[] cardImages = new Image[52];
        File file = new File("../assets/CardValues");

        String[] pathNames = file.list();
        ArrayList<String> pathNamesAL = new ArrayList<>();
        Collections.addAll(pathNamesAL, pathNames);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++) {
                int index = getIndexOfStartStringInArrayList(pathNamesAL, cardValueNames[j]);

                cardImages[(i*13)+j] = imageLoader.loadImage(pathNamesAL.get(index), "../assets/CardValues/" + pathNamesAL.get(index));

                pathNamesAL.remove(index);
            }
        }

        MouseData getMousePos = () -> {
            Point mousePos = getMousePosition();

            if(mousePos != null) {
                return new Vector(mousePos);
            }

            return new Vector(-100, -100);
        };

        Score score = new Score();
        score.setPosition(550, 50);
        addManagers(score, 3);

        Button NewGameButton = new Button(imageLoader.getImage("NewGameImage"), imageLoader.getImage("NewGameImageHovered"), new Vector(400, 600), new Dimension(150, 150), () -> {}, getMousePos);
        addManagers(NewGameButton, 3);


        cards = new Cards(cardImages, imageLoader.getImage("CardBack"), cardValues, new GameStateEvent() {
            @Override
            public void onGameEnd(boolean playerWon, boolean dealerWon, int points) {
                if(playerWon) {
                    score.incrementPlayerPoints();
                }
                if(dealerWon) {
                    score.incrementDealerPoints();
                }
            }

            @Override
            public void onGamePreview(int points) {
                NewGameButton.setActive(true);
            }
        });
        addManagers(cards, 2);

        NewGameButton.setOnReleased(() -> {
            cards.reset();
            NewGameButton.setActive(false);
        });

        VisualText visualText = new VisualText();
        addManagers(visualText, 5);

        cards.setActionEvent(new ActionEvent() {
            @Override
            public void onHit() {
                visualText.setText("");
            }

            @Override
            public void onStand() {
                visualText.setText("");
            }

            @Override
            public void onReset() {
                visualText.setText("");
            }
        });

        Button HitButton = new Button(imageLoader.getImage("HitImage"), imageLoader.getImage("HitImageHovered"), new Vector(30, 600), new Dimension(130, 150), () -> cards.hit(), getMousePos);
        HitButton.setActive(true);
        addManagers(HitButton, 2);

        Button StandButton = new Button(imageLoader.getImage("StandImage"), imageLoader.getImage("StandImageHovered"), new Vector(200, 600), new Dimension(130, 150), () -> {
            cards.stand();
            }, getMousePos);
        StandButton.setActive(true);
        addManagers(StandButton, 2);

        Button HintButton = new Button(imageLoader.getImage("HintImage"), imageLoader.getImage("HintImageHovered"), new Vector(650, 600), new Dimension(130, 150), () -> {
            if(cards.getPlayer().getTotalPoints() < 16) {
                visualText.setText("Hit");
            } else {
                visualText.setText("Stand");
            }
        }, getMousePos);
        HintButton.setActive(true);
        addManagers(HintButton, 2);

        cards.restart(cardImages);

    }

    private <T>void addManagers(T obj, int drawableLayer) {
        if(obj instanceof Updateable) {
            updater.addUpdateable((Updateable) obj);
        }

        if(obj instanceof Drawable) {
            drawableManager.addDrawable((Drawable) obj, drawableLayer);
        }

        if(obj instanceof KeyInteractable) {
            keyInteractor.addKeyInteractable((KeyInteractable) obj);
        }

        if(obj instanceof Clickable) {
            clickManager.addClickable((Clickable) obj);
        }
    }

    private int getIndexOfStartStringInArrayList(ArrayList<String> strings, String startStringToFind) {
        for(int i = 0; i < strings.size(); i++) {
            if(strings.get(i).substring(0, 1).equals(startStringToFind)) {
                return i;
            } else if(strings.get(i).substring(0, 2).equals(startStringToFind)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SCREENSIZE);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawableManager.drawAll(g);


        repaint();
    }

    public void gameLoop(int delay) {
        while(true) {
            try {
                Thread.sleep(delay);
            } catch(Exception e) {
                e.printStackTrace();
            }

           updater.updateAll();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keyInteractor.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyInteractor.keyReleased(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickManager.clickAll(new Vector(e.getX(), e.getY()), e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickManager.pressAll(new Vector(e.getX(), e.getY()), e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clickManager.releasesAll(new Vector(e.getX(), e.getY()), e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}