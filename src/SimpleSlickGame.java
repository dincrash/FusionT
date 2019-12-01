import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleSlickGame  extends BasicGame {
    public SimpleSlickGame(String gamename)
    {
        super(gamename);
    }


    Image background = null;
    Image img=null;
    /** True if space is down */
    private boolean w;
    private boolean s;
    /** True if left shift is down */
    private boolean a;
    /** True if right shift is down */
    private boolean d;
    private AppGameContainer app;

    private float x;
    /** The y position of our controlled stuff */
    private float y;
    /** The input syste being polled */
    private Input input;

    @Override
    public void init(GameContainer gc) throws SlickException {
        background = new Image("resources/background.png");
        img = new Image("resources/resize.png");
        if (gc instanceof AppGameContainer) {
            app = (AppGameContainer) gc;
        }

        input = gc.getInput();
        x = 300;
        y = 300;

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        w = gc.getInput().isKeyDown(Input.KEY_W);
        if(gc.getInput().isKeyDown(Input.KEY_W)){
            y=y-1;
        }
        s = gc.getInput().isKeyDown(Input.KEY_S);
        if(gc.getInput().isKeyDown(Input.KEY_S)){
            y=y+1;
        }
        a = gc.getInput().isKeyDown(Input.KEY_A);
        if(gc.getInput().isKeyDown(Input.KEY_A)){
            x=x-1;
        }
        d = gc.getInput().isKeyDown(Input.KEY_D);
        if(gc.getInput().isKeyDown(Input.KEY_D)){
            x=x+1;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {

//        g.drawLine(300, 300, 400 , 400);
//        g.setColor(Color.red);
//        g.drawString("Sabre!", 250, 10);
//        g.drawString("Шо", 250, 250);
        g.drawImage(background,0, 0);
        img.draw(x, y,128,128);
        g.drawString("w: "+w, 100, 240);
        g.drawString("s: "+s, 100, 260);
        g.drawString("a: "+a, 100, 280);
        g.drawString("d: "+d, 100, 300);
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SimpleSlickGame ("Simple Slick Game"));
            appgc.setDisplayMode(800, 600, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
