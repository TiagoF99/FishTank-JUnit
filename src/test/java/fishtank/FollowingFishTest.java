package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
  private Fish follow;
  private FollowingFish follower;

  @Before
  public void setUp() {
    follow = mock(Fish.class);
    //note: this is also why we use getters and setters so much in Java,
    //we wouldn't be able to mock the field itself if it were used instead
    //of the getter.
    when(follow.getX()).thenReturn(20);
    //This syntax is introduced by a library called mockito.
    //You can use it however you want, and it will be installed when we
    //run the grader.
    //See: http://www.vogella.com/tutorials/Mockito/article.html from 4 onwards
    when(follow.getY()).thenReturn(20);

    follower = new FollowingFish(follow);
  }

  @Before
  public void empty() {
    FishTank.clear();
  }

  @Test
  public void testApproachesFromBottomRight() {
    follower.setLocation(35, 30);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromBottomCenter() {

    follower.setLocation(20, 30);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 8; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromTopCenter() {

    follower.setLocation(20, 10);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 8; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromCenterRight() {

    follower.setLocation(25, 20);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 5; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromTopRight() {

    follower.setLocation(25, 15);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 6; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromTopLeft() {

    follower.setLocation(15, 15);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 6; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromCenterLeft() {

    follower.setLocation(15, 20);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 5; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }

  @Test
  public void testApproachesFromBottomLeft() {

    follower.setLocation(5, 30);

    //it should take exactly 15 updates to get from
    //(20, 20) to (5, 10)
    for(int i = 0; i < 15; i++) {
      follower.update();
    }
    int vertical_Dist = Math.abs(follower.getY() - follow.getY());
    int horizontal_Dist = Math.abs(follower.getX() - follow.getX());
    //Follower should be exactly 2 units below follow.
    assertEquals(2, vertical_Dist);
    assertEquals(0, horizontal_Dist);
  }


  @Test
  public void testGet() {

    follower.setLocation(12, 19);
    assertTrue(follower.getX() == 12 && follower.getY() == 19);
  }

  @Test
  public void colliding() {

    follower.setLocation(20, 20);
    FishTank.addEntity(20, 20, follower);

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

    for (int i = 0; i < 200; i++) {
      follow.update();
      follow.update();
      follow.update();
      follower.update();

      assertTrue(follower.getX() == 20 && follower.getY() == 20);
    }
  }

  @Test
  public void testLeftBoundWithVertical() {

    follower.setLocation(20, 18);

    for (int i = 0; i < 2000; i++) {
      follow.goingRight = false;
      follow.update();
      follower.update();
      assertTrue(follower.getX() >= 5 && follower.getY() >=5 && follower.getY() < 43);
    }
  }

  @Test
  public void testRightBoundWithVertical() {

    follower.setLocation(20, 18);

    for (int i = 0; i < 2000; i++) {
      follow.goingRight = true;
      follow.update();
      follower.update();
      assertTrue(follower.getX() < 43 && follower.getY() >=5 && follower.getY() < 43);
    }
  }

  @Test
  public void testCenterTopEdge() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(6);

    follower = new FollowingFish(fish);
    follower.setLocation(20, 5);

    follower.update();

    assertTrue(follower.getX() == 21 && follower.getY() == 5);
    assertTrue(follower.getY() >= 5);
  }

  @Test
  public void testCenterTopEdge2() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(6);

    follower = new FollowingFish(fish);
    follower.setLocation(18, 5);

    follower.update();

    assertTrue(follower.getX() == 19 && follower.getY() == 5);
    assertTrue(follower.getY() >= 5);
  }

  @Test
  public void testCenterTopEdge3() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(6);

    follower = new FollowingFish(fish);
    follower.setLocation(19, 5);

    follower.update();

    assertTrue(follower.getX() == 19 && follower.getY() == 5);
    assertTrue(follower.getY() >= 5);
  }

  @Test
  public void testCenterTopEdge4() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(5);

    follower = new FollowingFish(fish);
    follower.setLocation(19, 5);

    follower.update();
    follower.update();

    assertTrue(follower.getX() == 20 && follower.getY() == 7);
    assertTrue(follower.getY() >= 5);
  }

  @Test
  public void testCenterBottomEdge() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(41);

    follower = new FollowingFish(fish);
    follower.setLocation(20, 42);

    follower.update();

    assertTrue(follower.getX() == 21 && follower.getY() == 42);
    assertTrue(follower.getY() < 43);
  }

  @Test
  public void testCenterBottomEdge2() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(41);

    follower = new FollowingFish(fish);
    follower.setLocation(19, 42);

    follower.update();

    assertTrue(follower.getX() == 19 && follower.getY() == 42);
    assertTrue(follower.getY() < 43);
  }

  @Test
  public void testCenterBottomEdge3() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(41);

    follower = new FollowingFish(fish);
    follower.setLocation(18, 42);

    follower.update();

    assertTrue(follower.getX() == 19 && follower.getY() == 42);
    assertTrue(follower.getY() < 43);
  }

  @Test
  public void testCenterBottomEdge4() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(42);

    follower = new FollowingFish(fish);
    follower.setLocation(19, 42);

    follower.update();
    follower.update();

    assertTrue(follower.getX() == 20 && follower.getY() == 40);
    assertTrue(follower.getY() < 43);
  }

  @Test
  public void testCenterBottomEdge5() {

    Fish fish = mock(Fish.class);
    when(fish.getX()).thenReturn(20);
    when(fish.getY()).thenReturn(42);

    follower = new FollowingFish(fish);
    follower.setLocation(20, 41);

    follower.update();

    assertTrue(follower.getX() == 20 && follower.getY() == 40);
    assertTrue(follower.getY() < 43);
  }
}
