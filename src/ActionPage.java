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
    List<Shape> tanksShapeList = new ArrayList<Shape>();
    List<HostileTank> hostileList = new ArrayList<HostileTank>();
    private boolean hasCollision = false;
    private float oldb;
    private float oldv;
    String tankname="1";
    boolean notcol = false;
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
        createHostileTanks();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, 800, 600);
        img.setRotation(rotation);
        restrictedArea();
        collisionTanks();
        graphics.drawString(tankname, 10, 30);
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
        for (HostileTank hostild : hostileList
        ) {
            hostild.setY(hostild.getY()-1);
        }
//        System.out.println(tanksShapeList.get(0).getBounds().getX());
//        tanksShapeList.get(0).getBounds().setLocation(100,100);
//        System.out.println(tanksShapeList.get(0).getBounds().getX());

//        System.out.println(tanksShapeList.get(0));
        //forward
        for (int i = 0; i < tanksShapeList.size(); i++) {
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
            if (!tanksShapeList.isEmpty()) {
                for (int c = 0; c < tanksShapeList.size(); c++) {

                    if ((tanksShapeList.get(c).getBounds().getX() < bullet.location.x) & (tanksShapeList.get(c).getBounds().getY() < bullet.location.y)) {
                        if ((bullet.location.x < (tanksShapeList.get(c).getBounds().getX() + tanksShapeList.get(c).getBounds().getWidth())) & (bullet.location.y < (tanksShapeList.get(c).getBounds().getY() + tanksShapeList.get(c).getBounds().getHeight()))) {
                            bulletList.remove(bullet);
                            hostileList.remove(c);
                            tankname = Integer.toString(c);
                            tanksShapeList.remove(c);
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
        for (int i = 0; i < tanksShapeList.size(); i++) {
            if (imgRectangle.getMinX() < tanksShapeList.get(i).getBounds().getMaxX() && imgRectangle.getMaxX() > tanksShapeList.get(i).getBounds().getMinX() &&
                    imgRectangle.getMinY() < tanksShapeList.get(i).getBounds().getMaxY() && imgRectangle.getMaxY() > tanksShapeList.get(i).getBounds().getMinY()) {
                hostileList.get(i).setHasCollision(true);
            } else {
                hostileList.get(i).setHasCollision(false);

            }
        }
    }

    private void createHostileTanks() {
        //int x,int y,int b,int d
        int max = 700;
        int max2 = 500;
        int min = 50;
        int min2 = 50;
        int range = max - min + 1;
        int range2 = max2 - min2 + 1;

        //quantity hostiletanks
        int quantity = 2;
        for (int i = 0; i < quantity; i++) {
            int rand = (int) (Math.random() * range) + min;
            int gang = (int) (Math.random() * range2) + min;
            HostileTank hostileTank = new HostileTank(img, rand, gang, false);
            hostileTank.setX(rand);
            hostileTank.setY(gang);
            hostileTank.setImg(img);
        checkOnCreateCollision(rand,gang);
//            System.out.println(notcol +" " + rand +" "+ gang);
        if(notcol==false){
            addTank(rand,gang);
        }
//            System.out.println(shapeList.size());
    }
    imgRectangle = new Rectangle((int) v, (int) b, 80, 80);
}
    public void addTank(int rand,int gang){
        Rectangle hostileRectangle = new Rectangle(rand, gang, 80, 80);
        HostileTank hostileTank = new HostileTank(img, rand, gang, false);
        tanksShapeList.add(hostileRectangle);

        hostileList.add(hostileTank);
        hostileTank.setX(rand);
        hostileTank.setY(gang);
        hostileTank.setImg(img);
    }

    public void checkOnCreateCollision(int rand,int gang){
        for (int b = 0; b < tanksShapeList.size(); b++) {
            if (rand < tanksShapeList.get(b).getBounds().getMaxX() && rand + 80 > tanksShapeList.get(b).getBounds().getMinX() &&
                    gang < tanksShapeList.get(b).getBounds().getMaxY() && gang + 80 > tanksShapeList.get(b).getBounds().getMinY()) {
                notcol = true;
                break;
            } else {
                notcol = false;
            }
        }
    }
    @Override
    public int getID() {
        return ActionPage.ID;
    }
}
