package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird {

    int x;
    int y;

    int width = 40;
    int height = 30;

    double velocity = 0;

    double gravity = 0.5;

    double jumpStrength = -9;

    public Bird() {

        x = 150;
        y = 350;
    }

    public void update() {

        velocity += gravity;

        y += (int) velocity;
    }

    public void jump() {

        velocity = jumpStrength;
    }

    public Rectangle getBounds() {

        return new Rectangle(
                x,
                y,
                width,
                height
        );
    }

    public void draw(Graphics g) {

        // Body
        g.setColor(Color.YELLOW);

        g.fillOval(
                x,
                y,
                width,
                height
        );

        // Eye
        g.setColor(Color.WHITE);

        g.fillOval(
                x + 25,
                y + 5,
                10,
                10
        );

        g.setColor(Color.BLACK);

        g.fillOval(
                x + 29,
                y + 8,
                5,
                5
        );

        // Beak
        g.setColor(Color.ORANGE);

        int[] beakX = {
                x + width,
                x + width + 15,
                x + width
        };

        int[] beakY = {
                y + 12,
                y + 18,
                y + 23
        };

        g.fillPolygon(
                beakX,
                beakY,
                3
        );

        // Wing
        g.setColor(Color.ORANGE);

        g.fillOval(
                x + 5,
                y + 18,
                20,
                10
        );
    }
}