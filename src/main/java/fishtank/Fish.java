package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class Fish extends FishTankEntity {

    /** How this fish appears on the screen. */
    private String appearance;

    /** Indicates whether this fish is moving right. */
    boolean goingRight;

    /** This fish's first coordinate. */
    int x;
    /** This fish's second coordinate. */
    int y;
    /** The colour of this fish. */
    private final Color colour;


    /**
     * Constructs a new fish.
     */
    public Fish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><>";
        goingRight = true;
    }



    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
      x = a;
      y = b;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }


    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
		  Bubble b = new Bubble();
		  b.setLocation(x, y);
		  System.out.println(x + " " + y);

            if (FishTank.getEntity(x, y) == null || FishTank.getEntity(x, y) instanceof Bubble) {
                FishTank.addEntity(x, y, b);
            }

    }



    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
        StringBuilder reverse = new StringBuilder();
        for (int i=appearance.length()-1; i>=0; i--) {
            switch (appearance.charAt(i)) {
            case ')': reverse.append('('); break;
            case '(': reverse.append(')'); break;
            case '>': reverse.append('<'); break;
            case '<': reverse.append('>'); break;
            case '}': reverse.append('{'); break;
            case '{': reverse.append('}'); break;
            case '[': reverse.append(']'); break;
            case ']': reverse.append('['); break;
            default: reverse.append(appearance.charAt(i)); break;
            }
        }

        return reverse.toString();
    }



    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /** The font used to draw instances of this class. */
    static final Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param  g  the graphics context in which to draw the string.
     * @param  s  the string to draw.
     * @param  x  the x-coordinate of the string's cursor location.
     * @param  y  the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x*fm.charWidth('W'), y*fm.getAscent());
    }



    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    public void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }



    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {

        int c = x;
        int r = y;

        // Move one spot to the right or left.
        // Move one spot to the right or left.
        if (goingRight && x < (640/6 - 5)) {
            x += 1;
        } else {
            if (x > 5) {
                x -= 1;
            }
        }

        // Figure out whether I blow a bubble.
        double d = Math.random();
        if (d < 0.1) { blowBubble(); }

        // Figure out whether I turn around
        d = Math.random();
        if (x == 5 || x == (640/6 - 6)) {
            turnAround();
        } else if (d < 0.1) { turnAround(); }

        // Figure out whether to move up or down, or neither.
        d = Math.random();

        if (5 <= y && y < 43) {
            if (d < 0.05) {
                y += 1;
            } else if (d < 0.1) {
                y -= 1;
            }
        }
        if (y < 5) {
            y++;
        } else if (y >= 43) {
            y--;
        }

        if (FishTank.getEntity(x, y) != null) {
            setLocation(c,r);
        } else {
            for (int i = 0; i < 48; i++) {
                if (FishTank.getEntity(x, i) instanceof Seaweed) {
                    int length = ((Seaweed) FishTank.getEntity(x, i)).l;
                    if (i < y) {
                        break;
                    } else if (i - length >= y) {
                        break;
                    } else {
                        ((Seaweed) FishTank.getEntity(x, i)).l = i - y;
                    }
                }
            }
        }
    }
}
