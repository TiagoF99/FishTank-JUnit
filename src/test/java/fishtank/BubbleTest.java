package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BubbleTest {

    /* Note: FishTest is in the package fish tank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    private Bubble bubble;

    @Before
    public void setUp() {
        bubble = new Bubble();
        FishTank.clear();
    }

    @Test
    public void testAlwaysGoesUp() {

        bubble.setLocation(10, 10);

        int y = bubble.getY();

        for (int i = 0; i < 5; i++) {
            bubble.update();
        }

        assertEquals(Math.abs(bubble.getY() - y), 5 );
    }

    @Test
    public void testLeftMovement() {

        int sum = 0;

        for (int i = 0; i < 1000; i++) {

            bubble.setLocation(20, 20);

            bubble.update();

            if (bubble.getX() == 19) {
                sum++;
            }
            FishTank.clear();
        }
        assertTrue(sum >= 280 && sum <= 380);
    }

    @Test
    public void testRightMovement() {


        int sum = 0;

        for (int i = 0; i < 1000; i++) {

            bubble.setLocation(20, 20);

            bubble.update();

            if (bubble.getX() == 21) {
                sum++;
            }
            FishTank.clear();
        }
        assertTrue(sum >= 280 && sum <= 380);
    }

    @Test
    public void testStraightUpMovement() {

        int sum = 0;

        for (int i = 0; i < 1000; i++) {

            bubble.setLocation(20, 20);

            bubble.update();

            if (bubble.getX() == 20) {
                sum++;
            }
            FishTank.clear();
        }
        assertTrue(sum >= 280 && sum <= 380);
    }


    @Test
    public void testOffTheMap() {

        bubble.setLocation(10, 10);

        for (int i = 0; i < 8; i++) {
            bubble.update();
        }

        assertTrue(!bubble.exists());
    }

    @Test
    public void testOffTheMap2() {

        bubble.setLocation(10, 40);

        for (int i = 0; i < 100; i++) {
            try {
                bubble.update();
            } catch (IndexOutOfBoundsException e) {
                assertTrue(!bubble.exists());
                break;
            }
        }
    }


    @Test
    public void testCollide() {

        bubble.setLocation(8, 11);
        FishTank.addEntity(8, 11, bubble);

        int x  = bubble.getX();
        int y = bubble.getY();

        Bubble three = new Bubble();
        Bubble four = new Bubble();

        three.setLocation(8, 10);
        four.setLocation(9, 10);

        Fish fish = mock(Fish.class);
        fish.setLocation(7, 10);

        FishTank.addEntity(8, 10, three);
        FishTank.addEntity(9, 10, four);
        FishTank.addEntity(7, 10, fish);

        bubble.update();

        assertTrue((bubble.getY() == y) && (bubble.getX() == x));
    }

    @Test
    public void testColliding() {

        bubble.setLocation(20, 20);
        FishTank.addEntity(20, 20, bubble);

        Fish fish1 = mock(Fish.class);
        fish1.setLocation(19, 19);
        FishTank.addEntity(19, 19, fish1);
        Fish fish2 = mock(Fish.class);
        fish2.setLocation(20, 19);
        FishTank.addEntity(20, 19, fish2);
        Fish fish3 = mock(Fish.class);
        fish3.setLocation(21, 19);
        FishTank.addEntity(21, 19, fish3);
        Fish fish4 = mock(Fish.class);
        fish4.setLocation(21, 20);
        FishTank.addEntity(21, 20, fish4);
        Fish fish5 = mock(Fish.class);
        fish5.setLocation(21, 21);
        FishTank.addEntity(21, 21, fish5);
        Fish fish6 = mock(Fish.class);
        fish6.setLocation(20, 21);
        FishTank.addEntity(20, 21, fish6);
        Fish fish7 = mock(Fish.class);
        fish7.setLocation(19, 21);
        FishTank.addEntity(19, 21, fish7);
        Fish fish8 = mock(Fish.class);
        fish8.setLocation(19, 20);
        FishTank.addEntity(19, 20, fish8);

        bubble.update();

        assertTrue(bubble.getX() == 20 && bubble.getY() == 20);
    }


    @Test
    public void testGet() {

        bubble.setLocation(12, 19);
        assertTrue(bubble.getX() == 12 && bubble.getY() == 19);
    }

    @Test
    public void testStraightUp() {
        bubble.setLocation(30, 30);
        bubble.floatStraightUp();
        assertTrue(bubble.getX() == 30 && bubble.getY() == 29);
    }

    @Test
    public void testLeftUp() {
        bubble.setLocation(30, 30);
        bubble.floatLeftUp();
        assertTrue(bubble.getX() == 29 && bubble.getY() == 29);
    }

    @Test
    public void testRightUp() {
        bubble.setLocation(30, 30);
        bubble.floatRightUp();
        assertTrue(bubble.getX() == 31 && bubble.getY() == 29);
    }
}
