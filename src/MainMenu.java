import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

    // ID we return to class 'Application'
    public static final int ID = 0;
    Image background = null;
    private Input input;
    // init-method for initializing all resources
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("resources/background.png");
        input = gc.getInput();
    }

    // render-method for all the things happening on-screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background,0, 0);
        g.setColor(Color.green);
        g.drawString("Main Menu", 300, 100);
        g.drawString("Change Level", 300, 120);
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {

        if(input.isKeyPressed(input.KEY_F2)) {

            sbg.enterState(1);
        }

        if(input.isKeyPressed(input.KEY_F3)) {

            sbg.enterState(2);
        }
    }

    // Returning 'ID' from class 'MainMenu'
    @Override
    public int getID() {
        return MainMenu.ID;
    }
}