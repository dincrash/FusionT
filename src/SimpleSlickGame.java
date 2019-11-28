import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleSlickGame  extends BasicGame {
    public SimpleSlickGame(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {}

    @Override
    public void update(GameContainer gc, int i) throws SlickException {}

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("Howdy!", 250, 10);
        g.drawString("lox", 250, 250);
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
