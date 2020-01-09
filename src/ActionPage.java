import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActionPage extends BasicGameState {
    private int move;
    public static final int ID = 1;
    Image background = null;
    Image img = null;
    Image hostile = null;
    Rectangle imgRectangle = null;
    private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    private boolean w;
    private boolean s;
    private boolean a;
    private boolean d;
    private AppGameContainer app;
    private float v;
    private float b;
    private Input input;
    public static final int MINWIDTH = 0;
    public static final int WIDTH = 650;
    public static final int MINHEIGHT = 0;
    public static final int HEIGHT = 450;
    float rotation;
    private static int default_bullet_delay = 500;
    private static int time = 0;
    List<Shape> shapeList = new ArrayList<Shape>();
    List<HostileTank> hostileList = new ArrayList<HostileTank>();
    private boolean hasCollision = false;
    private float oldb;
    private float oldv;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("resources/background2.jpg");
        img = new Image("resources/resize.png");
        hostile = new Image("resources/tutle.png");
//        bullet = new Image("resources/bullet.png");
        if (gameContainer instanceof AppGameContainer) {
            app = (AppGameContainer) gameContainer;
        }
        input = gameContainer.getInput();
        v = 300;
        b = 300;
        img.setCenterOfRotation((img.getWidth() / 2) * 0.5f, (img.getHeight() / 2) * 0.5f);

        int max = 500;
        int min = 100;
        int range = max - min + 1;
        for (int i = 0; i < 5; i++) {
            int rand = (int) (Math.random() * range) + min;
            int gang = (int) (Math.random() * range) + min;
            HostileTank hostileTank = new HostileTank(img, rand, rand, false);
            hostileTank.setX(rand);
            hostileTank.setY(gang);
            hostileTank.setImg(img);
            Rectangle bulletRectangle = new Rectangle(rand, gang, 80, 80);
            bulletRectangle.setLocation(rand, gang);
            shapeList.add(bulletRectangle);
            hostileList.add(hostileTank);
        }
        imgRectangle = new Rectangle((int) v, (int) b, 80, 80);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, 800, 600);
        img.setRotation(rotation);
        restrictedArea();
        collisionTanks();

        imgRectangle = new Rectangle((int) v, (int) b, 80, 80);
        img.draw(v, b, 128, 128);
        for (HostileTank hostild : hostileList
        ) {
            hostile.draw(hostild.getX(), hostild.getY(), 128, 128);
        }

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
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        //forward
        for (int i = 0; i < shapeList.size(); i++) {
            move = 5;
            if (hostileList.get(i).isHasCollision()) {
                v = oldv;
                b = oldb;
            }
        }
        w = gameContainer.getInput().isKeyDown(Input.KEY_W);
        if (w) {
            oldb = b;
            b = b - move;
        }

        //bottom
        s = gameContainer.getInput().isKeyDown(Input.KEY_S);
        if (s) {
            oldb = b;
            b = b + move;
        }
        //left
        a = gameContainer.getInput().isKeyDown(Input.KEY_A);
        if (a) {
            oldv = v;
            v = v - move;
        }
        //right
        d = gameContainer.getInput().isKeyDown(Input.KEY_D);
        if (d) {
            oldv = v;
            v = v + move;
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
            if (!shapeList.isEmpty()) {
                for (int c = 0; c < shapeList.size(); c++) {

                    if ((shapeList.get(c).getBounds().getX() < bullet.location.x) & (shapeList.get(c).getBounds().getY() < bullet.location.y)) {
                        if ((bullet.location.x < (shapeList.get(c).getBounds().getX() + shapeList.get(c).getBounds().getWidth())) & (bullet.location.y < (shapeList.get(c).getBounds().getY() + shapeList.get(c).getBounds().getHeight()))) {
                            bulletList.remove(bullet);
                            hostileList.remove(c);
                            shapeList.remove(c);
                        } else {
                        }
                    }
                }
            }

        }

        //rotation
        if ((w && a) || (w && d)) {
            if (a) {
                rotation = 315;
            }
            if (d) {
                rotation = 45;
            }
        } else if (w) {
            rotation = 0;
        } else if (d) {
            rotation = 90;
        } else if (a) {
            rotation = 270;
        }

        if ((s && a) || (s && d)) {
            if (a) {
                rotation = 225;
            }
            if (d) {
                rotation = 135;
            }
        } else if (s) {
            rotation = 180;
        }

    }

    private void addNewBullet(int x, int y) {
        try {
            if (rotation == 0) {
                bulletList.add(new Bullet((int) v + 60, (int) b + 20, x, y));
            }
            if (rotation == 45) {
                bulletList.add(new Bullet((int) v + 90, (int) b + 30, x, y));
            }
            if (rotation == 90) {
                bulletList.add(new Bullet((int) v + 110, (int) b + 65, x, y));
            }
            if (rotation == 135) {
                bulletList.add(new Bullet((int) v + 105, (int) b + 100, x, y));
            }
            if (rotation == 180) {
                bulletList.add(new Bullet((int) v + 70, (int) b + 110, x, y));
            }
            if (rotation == 225) {
                bulletList.add(new Bullet((int) v + 30, (int) b + 110, x, y));
            }
            if (rotation == 270) {
                bulletList.add(new Bullet((int) v + 20, (int) b + 70, x, y));
            }
            if (rotation == 315) {
                bulletList.add(new Bullet((int) v + 30, (int) b + 35, x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restrictedArea() {
        if (v > WIDTH) {
            v = WIDTH;
        } else if (v < MINWIDTH) {
            v = MINWIDTH;
        }
        if (b > HEIGHT) {
            b = HEIGHT;
        } else if (b < MINHEIGHT) {
            b = MINHEIGHT;
        }
    }

    private void collisionTanks() {
        for (int i = 0; i < shapeList.size(); i++) {
            if (imgRectangle.getMinX() < shapeList.get(i).getBounds().getMaxX() && imgRectangle.getMaxX() > shapeList.get(i).getBounds().getMinX() &&
                    imgRectangle.getMinY() < shapeList.get(i).getBounds().getMaxY() && imgRectangle.getMaxY() > shapeList.get(i).getBounds().getMinY()) {
                hostileList.get(i).setHasCollision(true);
            } else {
                hostileList.get(i).setHasCollision(false);

            }
        }
    }

    @Override
    public int getID() {
        return ActionPage.ID;
    }
}
