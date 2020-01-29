import java.awt.*;

public class Bullet {
    int startX = 0;
    int startY = 0;
    int destX = 0;
    int destY = 0;
    Point location = new Point(0,0);
    float speed = 50; //how fast this moves.
    float dx;
    float dy;

    public Bullet(int startX, int startY, int destX, int destY)
    {
        this.startX = startX;
        this.startY = startY;
        location.setLocation(startX, startY);
        this.destX = destX;
        this.destY = destY;
        recalculateVector(destX, destY);

    }
    public void recalculateVector(int destX, int destY)
    {
        float rad = (float)(Math.atan2(destX - startX, startY - destY));

        speed = 10;

        this.dx = (float) Math.sin(rad) * speed;
        this.dy = -(float) Math.cos(rad) * speed;
    }

    public void recalculateVector()
        {
            recalculateVector(destX, destY);
        }

    public void move()
    {
        double x;
        x=destX;
        double y;
        y=destY;

        try {
            x = location.getX();
            y = location.getY();

        } catch (Exception e) {
            e.printStackTrace();
        }

        x += dx;
        y += dy;

        location.setLocation(x, y);
    }
}