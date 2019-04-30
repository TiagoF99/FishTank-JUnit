package fishtank;
import java.awt.*;

/**
 * Seaweed.
 */
public class Seaweed extends FishTankEntity {
  /** The font used to draw instances of this class. */
  private static final Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /** The number of weed segments. */
    int l;
    private final int old_length;
    int update = 0;

    /** Indicates whether the bottom segment is leaning right. */
    private boolean leanRight;

    /** My colour. Ah,the vagaries of British vs. US spelling. */
    private final Color colour;

    //static variables for number of updates.


    /**
     * Constructs a new seaweed item at the specified cursor
     * location (x,y),l segments tall.
     *
     * @param  l  the number of segments this seaweed is tall.
     */
    public Seaweed(int l) {
        this.l  = l;
        this.old_length = l;
        colour  =Color.green.darker().darker();
    }

    public int getLength() {
      return l;
    }



    /**
     * Draws this fish tank item.  Looks lovely waving in the current, doesn't
     * it?
     *
     * @param  g  the graphics context in which to draw this item.
     */
    void draw(Graphics g) {

     // WWhich way does the first segment lean?
      boolean lR=leanRight;

      for (int i=0;i<l;i++) {// Draw a "/" seaweed segment: even numbered and leaning to the right.
        if (i%2==0)
          if (lR)
            // Draw the string
            drawString(g,"/",my_curr_row,(-i+my_curr_col));
      if (i%2==1==true) // Draw a "/" seaweed segment: odd numbered and leaning to the right.
        if (lR)
          // Draw the string
          drawString(g,"\\",my_curr_row,(-i+my_curr_col));
      if (i%2==0) // Draw a "/" seaweed segment: even numbered and leaning to the left.
        if (!lR)
          // Draw the string
          drawString(g,"\\",my_curr_row,(-i+my_curr_col));
        else if (i%2==1){ // to make a point about comparing to true or false.
          // Draw the string for the last kind of leaning of the segment at location  my_curr_row,(-i+my_curr_col)
          drawString(g,"/",my_curr_row,(-i+my_curr_col));
        }
      }}
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
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a,int b) {
      this.my_curr_row  =a;
      this.my_curr_col  =b;
    }

  @Override
  int getX() {
    return my_curr_row;
  }

  @Override
  int getY() {
    return my_curr_col;
  }

  /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {

      leanRight  =!leanRight;

      if (update % 200 == 0 && update > 0) {
        if (this.l < old_length) {
            this.l++;
          }
      }
      update++;
    }


    /** This bubble's first coordinate. */
    private int my_curr_row;
    /** This bubble's second coordinate. */
    private int my_curr_col;

}
