package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FishTest {

    /* Note: FishTest is in the package fish tank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;

    @Before
    public void setUp() {
        fish = new Fish();
        FishTank.clear();
    }

    @Test
    public void testLeftBound() {

        for (int i = 0; i < 200; i++) {
            fish.setLocation(10, 10);
            fish.goingRight = false;
            fish.update();
        }

        assertTrue(fish.getX() >= 5);
    }

    @Test
    public void testRightBound() {

        for (int i = 0; i < 200; i++) {
            fish.setLocation(10, 10);
            fish.goingRight = true;
            fish.update();
        }

        assertTrue(fish.getX() < 640/6 - 5);
    }

    @Test
    public void testVerticalBounds() {

        for (int i = 0; i < 1000; i++) {
            fish.setLocation(10, 10);
            fish.update();
        }

        assertTrue(fish.getY() >= 5 && fish.getY() < 43);
    }

    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                false; //notice: I can edit package private attributes!
            fish.update();
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

        fish.setLocation(20, 32);
        FishTank.addEntity(20, 32, fish);

        fish.blowBubble();

        assertTrue(!(FishTank.getEntity(20, 32) instanceof Bubble));
    }

    @Test
    public void testBubbleCreated() {

        fish.setLocation(20, 32);

        fish.blowBubble();

        assertTrue(FishTank.getEntity(20, 32) instanceof Bubble);
    }

    @Test
    public void testBubbleMade() {

        fish.setLocation(100, 30);
        FishTank.addEntity(100, 30, fish);

        boolean value = false;

        int x = fish.getX();
        int y = fish.getY();

        for (int i = 0; i < 200; i++) {

            fish.goingRight = false;
            fish.update();

            if (FishTank.getEntity(x, y) instanceof Bubble || FishTank.getEntity(x+1, y) instanceof Bubble ||
                    FishTank.getEntity(x-1, y) instanceof Bubble ||
                    FishTank.getEntity(x, y+1) instanceof Bubble || FishTank.getEntity(x, y-1) instanceof Bubble ||
                    FishTank.getEntity(x+1, y+1) instanceof Bubble || FishTank.getEntity(x -1, y -1) instanceof Bubble ||
                    FishTank.getEntity(x+1, y-1) instanceof Bubble || FishTank.getEntity(x-1, y+1) instanceof Bubble) {
                value = true;
                break;
            }

            x = fish.getX();
            y = fish.getY();

        }
        assertTrue(value);
    }

    @Test
    public void testTenPercentBubble() {


        int sum = 0;

        for (int i = 0; i < 1000; i++) {

            fish.setLocation(30, 30);
            FishTank.addEntity(30, 30, fish);

            fish.update();
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

        boolean direction = fish.goingRight;
        int sum = 0;

        for (int i = 0; i < 1000; i++) {
            fish.update();

            if (fish.goingRight != direction) {
                sum++;
                direction = fish.goingRight;
            }

        }
        assertTrue(sum >= 50 && sum <= 150);
    }

    @Test
    public void testGoingRight() {

        fish.setLocation(20, 20);

        fish.setGoingRight(true);
        fish.update();

        assertEquals(fish.getX(), 21);
    }

    @Test
    public void testGoingRight2() {

        fish.setGoingRight(true);
        assertTrue(fish.goingRight);

    }

    @Test
    public void testGoingRight3() {

        fish.setGoingRight(false);
        assertTrue(!fish.goingRight);

    }

    @Test
    public void testVerticalMovement() {

        fish.setLocation(30, 24);

        int sum = 0;
        int y = fish.getY();

        for (int i = 0; i < 1000; i++) {
            fish.update();
            if (fish.getY() != y) {
                sum++;
            }
            y = fish.getY();
        }
        assertTrue(sum >= 50 && sum <= 150);
    }

    @Test
    public void testSetLocation() {

        fish.setLocation(25, 40);

        assertTrue(fish.getX() == 25 && fish.getY() == 40);
    }

    @Test
    public void testColliding() {

        fish.setLocation(20, 20);
        FishTank.addEntity(20, 20, fish);

        Fish fish1 = new Fish();
        fish1.setLocation(19, 19);
        FishTank.addEntity(19, 19, fish1);
        Fish fish2 = new Fish();
        fish2.setLocation(20, 19);
        FishTank.addEntity(20, 19, fish2);
        Fish fish3 = new Fish();
        fish3.setLocation(21, 19);
        FishTank.addEntity(21, 19, fish3);
        Fish fish4 = new Fish();
        fish4.setLocation(21, 20);
        FishTank.addEntity(21, 20, fish4);
        Fish fish5 = new Fish();
        fish5.setLocation(21, 21);
        FishTank.addEntity(21, 21, fish5);
        Fish fish6 = new Fish();
        fish6.setLocation(20, 21);
        FishTank.addEntity(20, 21, fish6);
        Fish fish7 = new Fish();
        fish7.setLocation(19, 21);
        FishTank.addEntity(19, 21, fish7);
        Fish fish8 = new Fish();
        fish8.setLocation(19, 20);
        FishTank.addEntity(19, 20, fish8);

        fish.update();

        assertTrue(fish.getX() == 20 && fish.getY() == 20);
    }

    @Test
    public void testColliding2() {

        fish.setLocation(20, 20);
        FishTank.addEntity(20, 20, fish);
        fish.goingRight = false;

        Fish fish1 = new Fish();
        fish1.setLocation(19, 19);
        FishTank.addEntity(19, 19, fish1);
        Fish fish7 = new Fish();
        fish7.setLocation(19, 21);
        FishTank.addEntity(19, 21, fish7);
        Fish fish8 = new Fish();
        fish8.setLocation(19, 20);
        FishTank.addEntity(19, 20, fish8);

        fish.update();

        assertTrue(fish.getX() == 20 && fish.getY() == 20);
    }


}
