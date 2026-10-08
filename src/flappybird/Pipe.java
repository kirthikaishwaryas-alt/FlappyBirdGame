package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pipe {

    int x;

    int width = 80;

    int gap = 180;

    int topHeight;

    int bottomY;

    int speed = 5;

    public Pipe(int x, int topHeight) {

        this.x = x;

        this.topHeight = topHeight;

        bottomY = topHeight + gap;
    }

    public void update() {

        x -= speed;
    }

    public boolean isOffScreen() {

        return x + width < 0;
    }

    public Rectangle getTopBounds() {

        return new Rectangle(
                x,
                0,
                width,
                topHeight
        );
    }

    public Rectangle getBottomBounds() {

        return new Rectangle(
                x,
                bottomY,
                width,
                800 - bottomY
        );
    }

    public void draw(Graphics g) {

        g.setColor(
                new Color(30, 180, 50)
        );

        // Top pipe
        g.fillRect(
                x,
                0,
                width,
                topHeight
        );

        // Top pipe cap
        g.fillRect(
                x - 5,
                topHeight - 20,
                width + 10,
                20
        );

        // Bottom pipe
        g.fillRect(
                x,
                bottomY,
                width,
                800 - bottomY
        );

        // Bottom pipe cap
        g.fillRect(
                x - 5,
                bottomY,
                width + 10,
                20
        );
    }
}