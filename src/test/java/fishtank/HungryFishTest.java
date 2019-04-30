package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class HungryFishTest {

    /* Note: FishTest is in the package fish tank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    private HungryFish hungryfish;

    @Before
    public void setUp() {
        hungryfish = new HungryFish();
        FishTank.clear();
    }

    @Test
    public void testLeftBound() {

        for (int i = 0; i < 200; i++) {
            hungryfish.setLocation(10, 10);
            hungryfish.goingRight = false;
            hungryfish.update();
        }

        assertTrue(hungryfish.getX() >= 5);
    }

    @Test
    public void testRightBound() {

        for (int i = 0; i < 200; i++) {
            hungryfish.setLocation(10, 10);
            hungryfish.goingRight = true;
            hungryfish.update();
        }

        assertTrue(hungryfish.getX() < 640/6 - 5);
    }

    @Test
    public void testVerticalBounds() {

        for (int i = 0; i < 1000; i++) {
            hungryfish.setLocation(10, 10);
            hungryfish.update();
        }

        assertTrue(hungryfish.getY() >= 5 && hungryfish.getY() < 43);
    }

    @Test
    public void testHungryFishBubbles() {
        //Note: This test currently fails, but should pass once you've
        // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            hungryfish.setLocation(5, 10);
            hungryfish.goingRight =
                    false; //notice: I can edit package private attributes!
            hungryfish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(5, 10);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue(bubbleMade);
    }

    @Test
    public void testBubbleReplaceFish() {

        hungryfish.setLocation(20, 10);
        FishTank.addEntity(20, 10, hungryfish);

        hungryfish.blowBubble();

        assertTrue(!(FishTank.getEntity(20, 10) instanceof Bubble));
    }

    @Test
    public void testBubbleMade() {

        hungryfish.setLocation(100, 30);
        FishTank.addEntity(100, 30, hungryfish);

        boolean value = false;

        int x = hungryfish.getX();
        int y = hungryfish.getY();

        for (int i = 0; i < 200; i++) {

            hungryfish.goingRight = false;
            hungryfish.update();

            if (FishTank.getEntity(x, y) instanceof Bubble || FishTank.getEntity(x+1, y) instanceof Bubble ||
                    FishTank.getEntity(x-1, y) instanceof Bubble ||
                    FishTank.getEntity(x, y+1) instanceof Bubble || FishTank.getEntity(x, y-1) instanceof Bubble ||
                    FishTank.getEntity(x+1, y+1) instanceof Bubble || FishTank.getEntity(x -1, y -1) instanceof Bubble ||
                    FishTank.getEntity(x+1, y-1) instanceof Bubble || FishTank.getEntity(x-1, y+1) instanceof Bubble) {
                value = true;
                break;
            }

            x = hungryfish.getX();
            y = hungryfish.getY();

        }
        assertTrue(value);
    }

    @Test
    public void testTenPercentBubble() {


        int sum = 0;

        for (int i = 0; i < 1000; i++) {

            hungryfish.setLocation(30, 30);
            FishTank.addEntity(30, 30, hungryfish);

            hungryfish.update();
            if (FishTank.getEntity(29, 30) instanceof Bubble) {
                sum++;
            } else if (FishTank.getEntity(31, 30) instanceof Bubble) {
                sum++;
            }
            FishTank.clear();
        }
        assertTrue(sum >= 50 && sum <= 150);
    }

    @Test
    public void testTurningAround() {

        boolean direction = hungryfish.goingRight;
        int sum = 0;

        for (int i = 0; i < 1000; i++) {
            hungryfish.update();

            if (hungryfish.goingRight != direction) {
                sum++;
                direction = hungryfish.goingRight;
            }

        }
        assertTrue(sum >= 50 && sum <= 150);
    }

    @Test
    public void testGoingRight() {

        hungryfish.setLocation(67, 20);

        hungryfish.setGoingRight(true);
        hungryfish.update();

        assertEquals(hungryfish.getX(), 68);
    }

    @Test
    public void testGoingRight2() {

        hungryfish.setGoingRight(true);
        assertTrue(hungryfish.goingRight);

    }

    @Test
    public void testGoingRight3() {

        hungryfish.setGoingRight(false);
        assertTrue(!hungryfish.goingRight);

    }

    @Test
    public void testVerticalMovement() {

        hungryfish.setLocation(30, 24);

        int sum = 0;
        int y = hungryfish.getY();

        for (int i = 0; i < 1000; i++) {
            hungryfish.update();
            if (hungryfish.getY() != y) {
                sum++;
            }
            y = hungryfish.getY();
        }
        assertTrue(sum >= 50 && sum <= 150);
    }

    @Test
    public void testSetLocation() {

        hungryfish.setLocation(25, 40);

        assertTrue(hungryfish.getX() == 25 && hungryfish.getY() == 40);
    }

    @Test
    public void testColliding() {

        hungryfish.setLocation(20, 20);
        FishTank.addEntity(20, 20, hungryfish);

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

        hungryfish.update();

        assertTrue(hungryfish.getX() == 20 && hungryfish.getY() == 20);
    }

    @Test
    public void testColliding2() {

        hungryfish.setLocation(20, 20);
        FishTank.addEntity(20, 20, hungryfish);
        hungryfish.goingRight = false;

        Fish fish1 = mock(Fish.class);
        fish1.setLocation(19, 19);
        FishTank.addEntity(19, 19, fish1);
        Fish fish7 = mock(Fish.class);
        fish7.setLocation(19, 21);
        FishTank.addEntity(19, 21, fish7);
        Fish fish8 = mock(Fish.class);
        fish8.setLocation(19, 20);
        FishTank.addEntity(19, 20, fish8);

        hungryfish.update();

        assertTrue(hungryfish.getX() == 20 && hungryfish.getY() == 20);
    }


}
