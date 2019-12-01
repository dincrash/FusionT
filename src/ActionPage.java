import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ActionPage extends BasicGameState {

    public static final int ID = 1;


    Image background = null;
    Image img=null;
    Image hostile=null;
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
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("resources/background.png");
        img = new Image("resources/resize.png");
        hostile = new Image("resources/resize.png");
        if (gameContainer instanceof AppGameContainer) {
            app = (AppGameContainer) gameContainer;
        }

        input = gameContainer.getInput();
        x = 300;
        y = 300;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

//        g.drawLine(300, 300, 400 , 400);
//        g.setColor(Color.red);
//        g.drawString("Sabre!", 250, 10);
//        g.drawString("Шо", 250, 250);
        graphics.drawImage(background,0, 0);
        img.draw(x, y,128,128);
        hostile.draw(500, 500,128,128);
        graphics.drawString("w: "+w, 100, 240);
        graphics.drawString("s: "+s, 100, 260);
        graphics.drawString("a: "+a, 100, 280);
        graphics.drawString("d: "+d, 100, 300);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        w = gameContainer.getInput().isKeyDown(Input.KEY_W);
        if(gameContainer.getInput().isKeyDown(Input.KEY_W)){
            y=y-5;
        }
        s = gameContainer.getInput().isKeyDown(Input.KEY_S);
        if(gameContainer.getInput().isKeyDown(Input.KEY_S)){
            y=y+5;
        }
        a = gameContainer.getInput().isKeyDown(Input.KEY_A);
        if(gameContainer.getInput().isKeyDown(Input.KEY_A)){
            x=x-5;
        }
        d = gameContainer.getInput().isKeyDown(Input.KEY_D);
        if(gameContainer.getInput().isKeyDown(Input.KEY_D)){
            x=x+5;
        }

        if(input.isKeyPressed(input.KEY_F1)) {

            stateBasedGame.enterState(0);
        }
        if(input.isKeyPressed(input.KEY_F3)) {

            stateBasedGame.enterState(2);
        }
    }
    @Override
    public int getID() {
        return ActionPage.ID;
    }
}
