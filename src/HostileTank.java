import org.newdawn.slick.Image;
public class HostileTank {
    Image img;
    int x;
    int y;
    boolean hasCollision;

    public boolean isHasCollision() {
        return hasCollision;
    }

    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public HostileTank(Image img, int x, int y,boolean hasCollision) {

    }

}
