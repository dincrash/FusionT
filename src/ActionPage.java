import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

public class ActionPage extends BasicGameState {

    public static final int ID = 1;


    Image background = null;
    Image img = null;
    Image hostile = null;
    Image bullet = null;
    private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

    /**
     * True if space is down
     */
    private boolean w;
    private boolean s;
    /**
     * True if left shift is down
     */
    private boolean a;
    /**
     * True if right shift is down
     */
    private boolean d;
    private AppGameContainer app;

    private float v;
    /**
     * The y position of our controlled stuff
     */
    private float b;
    private boolean g;
    /**
     * The input syste being polled
     */
    private Input input;
    int x;
    int y;
    int time=0;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("resources/background.png");
        img = new Image("resources/resize.png");
        hostile = new Image("resources/resize.png");
//        bullet = new Image("resources/bullet.png");
        if (gameContainer instanceof AppGameContainer) {
            app = (AppGameContainer) gameContainer;
        }

        input = gameContainer.getInput();
        v = 300;
        b = 300;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

//        g.drawLine(300, 300, 400 , 400);
//        g.setColor(Color.red);
//        g.drawString("Sabre!", 250, 10);
//        g.drawString("Шо", 250, 250);
        graphics.drawImage(background, 0, 0);
        img.draw(v, b, 128, 128);
        hostile.draw(500, 500, 128, 128);
        graphics.drawString("w: " + w, 100, 240);
        graphics.drawString("s: " + s, 100, 260);
        graphics.drawString("a: " + a, 100, 280);
        graphics.drawString("d: " + d, 100, 300);
//        bullet.draw(100,100);

        graphics.setColor(Color.red);
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);

            try {
                bullet.move();
                graphics.fillRect(bullet.location.x, bullet.location.y, 5, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        graphics.drawString("Time : " + time/1000, 100, 100);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        w = gameContainer.getInput().isKeyDown(Input.KEY_W);
        if (gameContainer.getInput().isKeyDown(Input.KEY_W)) {
            b = b - 5;
        }
        s = gameContainer.getInput().isKeyDown(Input.KEY_S);
        if (gameContainer.getInput().isKeyDown(Input.KEY_S)) {
            b = b + 5;
        }
        a = gameContainer.getInput().isKeyDown(Input.KEY_A);
        if (gameContainer.getInput().isKeyDown(Input.KEY_A)) {
            v = v - 5;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_D)) {
            v = v + 5;
        }

        if (input.isKeyPressed(input.KEY_F1)) {

            stateBasedGame.enterState(0);
        }
        if (input.isKeyPressed(input.KEY_F3)) {

            stateBasedGame.enterState(2);
        }
        time += i;

        if (gameContainer.getInput().isMouseButtonDown(input.MOUSE_LEFT_BUTTON)) {

            addNewBullet(input.getMouseX(), input.getMouseY());

        }

    }

    private void addNewBullet(Bullet bullet) {
    }

    private void addNewBullet(int x, int y) {
        try {
            bulletList.add(new Bullet((int) v + 60, (int) b + 20, x, y));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getID() {
        return ActionPage.ID;
    }
}
