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

    @Override
    public void init(GameContainer gc) throws SlickException {
        background = new Image("resources/background.png");
        img = new Image("resources/2.png");

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {}

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {

//        g.drawLine(300, 300, 400 , 400);
//        g.setColor(Color.red);
//        g.drawString("Sabre!", 250, 10);
//        g.drawString("Шо", 250, 250);
        g.drawImage(background,0, 0);
        img.draw(400, 400);
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
