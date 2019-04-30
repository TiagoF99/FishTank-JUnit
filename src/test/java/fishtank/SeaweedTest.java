package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeaweedTest {

    /* Note: FishTest is in the package fish tank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    private Seaweed seaweed;

    @Before
    public void setUp() {
        seaweed = new Seaweed(4);
        FishTank.clear();
    }

    @Test
    public void testHeightToContact() {

        Fish fish1 = new Fish();
        fish1.setLocation(41, 12);
        FishTank.addEntity(41, 12, fish1);

        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);


        for (int i = 0; i < 1000; i++) {

            fish1.goingRight = false;
            fish1.update();
            if (fish1.getY() == 12 && fish1.getX() == 40) {
                assertEquals(seaweed.getLength(), 1);
                break;
            }
            fish1.x = 41;
            fish1.y = 12;
        }
    }

    @Test
    public void testHeightToContact2() {

        Fish fish1 = new Fish();
        fish1.setLocation(41, 11);
        FishTank.addEntity(41, 11, fish1);

        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);

        for (int i = 0; i < 1000; i++) {

            fish1.goingRight = false;
            fish1.update();
            if (fish1.getY() == 11 && fish1.getX() == 40) {
                assertEquals(seaweed.getLength(), 2);
                break;
            }
            fish1.x = 41;
            fish1.y = 11;
        }

    }

    @Test
    public void testHeightToContact3() {

        Fish fish1 = new Fish();
        fish1.setLocation(41, 10);
        FishTank.addEntity(41, 10, fish1);

        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);


        for (int i = 0; i < 1000; i++) {

            fish1.goingRight = false;
            fish1.update();
            if (fish1.getY() == 10 && fish1.getX() == 40) {
                assertEquals(seaweed.getLength(), 3);
                break;
            }
            fish1.x = 41;
            fish1.y = 10;
        }
    }

    @Test
    public void testHeightToContactHungry() {

        HungryFish fish1 = new HungryFish();
        fish1.setLocation(41, 12);
        FishTank.addEntity(41, 12, fish1);

        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);


        for (int i = 0; i < 1000; i++) {

            fish1.goingRight = false;
            fish1.update();
            if (fish1.getY() == 12 && fish1.getX() == 40) {
                assertEquals(seaweed.getLength(), 1);
                break;
            }
            fish1.x = 41;
            fish1.y = 12;
        }
    }

    @Test
    public void testHeightToContact2Hungry() {

        HungryFish fish1 = new HungryFish();
        fish1.setLocation(41, 11);
        FishTank.addEntity(41, 11, fish1);

        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);

        for (int i = 0; i < 1000; i++) {

            fish1.goingRight = false;
            fish1.update();
            if (fish1.getY() == 11 && fish1.getX() == 40) {
                assertEquals(seaweed.getLength(), 2);
                break;
            }
            fish1.x = 41;
            fish1.y = 11;
        }

    }

    @Test
    public void testHeightToContact3Hungry() {

        HungryFish fish1 = new HungryFish();
        fish1.setLocation(41, 10);
        FishTank.addEntity(41, 10, fish1);

        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);


        for (int i = 0; i < 1000; i++) {

            fish1.goingRight = false;
            fish1.update();
            if (fish1.getY() == 10 && fish1.getX() == 40) {
                assertEquals(seaweed.getLength(), 3);
                break;
            }
            fish1.x = 41;
            fish1.y = 10;
        }
    }

    @Test
    public void testHeightAtOne() {

        Fish fish1 = new Fish();
        Fish fish2 = new Fish();

        fish1.setLocation(41, 14);
        fish2.setLocation(39, 17);
        seaweed.setLocation(40, 13);
        FishTank.addEntity(40, 13, seaweed);
        FishTank.addEntity(41, 14, fish1);
        FishTank.addEntity(39, 17, fish2);

        for (int i = 0; i < 2001; i++) {
            fish1.update();
            fish2.update();
            seaweed.update();
            assertTrue(seaweed.getLength() >= 1);
        }
    }

    @Test
    public void testUpdateRate() {

        seaweed.l = 1;

        for (int i = 1; i < 202; i++) {
            seaweed.update();
            if (i % 201 == 0) {
                assertEquals(seaweed.l, 2);
            } else {
                assertEquals(seaweed.l, 1);
            }
        }
    }

    @Test
    public void testGetLength() {

        assertEquals(seaweed.l, seaweed.getLength());
    }

    @Test
    public void testSetLocation() {

        seaweed.setLocation(25, 40);

        assertTrue(seaweed.getX() == 25 && seaweed.getY() == 40);
    }

}