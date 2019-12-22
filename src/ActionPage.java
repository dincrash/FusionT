import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ActionPage extends BasicGameState {

    public static final int ID = 1;
    Image background = null;
    Image img = null;
    Image hostile = null;
    Image bullet = null;
    private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    private boolean w;
    private boolean s;
    private boolean a;
    private boolean d;
    private AppGameContainer app;
    private float v;
    private float b;
    private boolean g;
    private Input input;
    int x;
    int y;
    private static int default_bullet_delay = 500;
    private static int time = 0;
    boolean destroy = false;
    List<Shape> shapeList = new ArrayList<Shape>();

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("resources/background.png");
        img = new Image("resources/resize.png");
        hostile = new Image("resources/tutle.png");
//        bullet = new Image("resources/bullet.png");
        if (gameContainer instanceof AppGameContainer) {
            app = (AppGameContainer) gameContainer;
        }
        input = gameContainer.getInput();
        v = 300;
        b = 300;

        Rectangle bulletRectangle = new Rectangle(x, y, 128, 128);
        shapeList.add(bulletRectangle);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        graphics.drawImage(background, 0, 0);
        img.draw(v, b, 128, 128);
        if (!destroy) {
            hostile.draw(x, y, 128, 128);
        }


        graphics.drawString("w: " + w, 100, 240);
        graphics.drawString("s: " + s, 100, 260);
        graphics.drawString("a: " + a, 100, 280);
        graphics.drawString("d: " + d, 100, 300);
//        bullet.draw(100,100);
        graphics.setColor(Color.red);

        graphics.drawString("Time : " + time / 1000, 100, 100);

        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            try {
                bullet.move();
                graphics.fillRect(bullet.location.x, bullet.location.y, 5, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
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

        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            time -= delta;

            if (time <= 0) {
                addNewBullet(input.getMouseX(), input.getMouseY());
                time = default_bullet_delay;
            }
        }

        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            if(!shapeList.isEmpty()) {
                for (int d = 0; d < shapeList.size(); d++) {
                    Shape rectangle = shapeList.get(d);

                if ((shapeList.get(d).getBounds().getX() < bullet.location.x) & (shapeList.get(d).getBounds().getY() < bullet.location.y)) {
                    if ((bullet.location.x < shapeList.get(d).getBounds().getWidth()) & (bullet.location.y < shapeList.get(d).getBounds().getHeight())) {
                        bulletList.remove(bullet);
                        destroy = true;
                        shapeList.remove(d);
                    } else {
                    }
                }}
            }

        }

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
