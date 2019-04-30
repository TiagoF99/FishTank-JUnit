package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

    /** How this fish appears on the screen. */
    private String appearance;

    /** Indicates whether this fish is moving right. */
    private boolean goingRight;

    /** This fish's first coordinate. */
    int x;
    /** This fish's second coordinate. */
    int y;
    /** The colour of this fish. */
    private final Color colour;

    /** The entity that our fish is following */
    private final Fish de;


    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
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
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private void reverseAppearance() {
      System.out.println("Turning around" + this.appearance);
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
        System.out.println("Turned around" + this.appearance);
        appearance = reverse.toString();
    }


    /**
     * Turns this fish to fc
     */
    protected void turnToFace() {
        if(de.getX() < this.getX() && goingRight) {
            goingRight = false;
            reverseAppearance();
        } else if(de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            reverseAppearance();
        }
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
    void draw(Graphics g) {
        drawString(g, appearance, x, y);
    }



    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        turnToFace();

        int c = x;
        int r = y;


        if (y >= 5 && y < 43 && x >= 5 && x < 640/6 - 5 ) {
            if (de.getY() == 5 && de.getY() >= y) {
                y++;
            } else if (de.getY() == 6 && de.getY() >= y) {
                if (de.getX() == x) {
                    if (x == 5) {
                        x++;
                    } else if (x == (640 / 6 - 6)) {
                        x--;
                    } else {
                        x++;
                    }
                } else {
                    if (Math.abs(de.getX() - x) != 1) {
                        if (de.getX() > x) {
                            x++;
                        } else {
                            x--;
                        }
                    }
                }
            } else if (de.getY() == 41 && de.getY() <= y) {

                if (de.getX() == x) {
                    if (x == 5) {
                        x++;
                    } else if (x == (640 / 6 - 6)) {
                        x--;
                    } else {
                        x++;
                    }
                } else {
                    if (Math.abs(de.getX() - x) != 1) {
                        if (de.getX() > x) {
                            x++;
                        } else {
                            x--;
                        }
                    }
                }

            } else if (de.getY() == 42 && de.getY() <= y) {
                y--;
            } else {
                if (Math.abs(de.getY() - y) > 2) {
                    if (de.getY() < y) {
                        y--;
                    } else {
                        y++;
                    }
                } else if (Math.abs(de.getY() - y) < 2) {
                    if (de.getY() < y) {
                        y++;
                    } else {
                        y--;
                    }
                }

                if (de.getX() < x) {
                    x--;
                } else if (x < de.getX()) {
                    x++;
                }
            }
        }



        if (FishTank.getEntity(x, y) != null) {
            setLocation(c, r);
        }

    }
}
