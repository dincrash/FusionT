import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class UpgradePage extends BasicGameState {

    public static final int ID = 2;
    Image background = null;
    private Input input;


    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("resources/background.png");
        input = gameContainer.getInput();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background,0, 0);
        graphics.setColor(Color.green);
        graphics.drawString("Upgrade Menu", 300, 100);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(input.isKeyPressed(input.KEY_F1)) {

            stateBasedGame.enterState(0);
        }

        if(input.isKeyPressed(input.KEY_F2)) {

            stateBasedGame.enterState(1);
        }
    }
}
