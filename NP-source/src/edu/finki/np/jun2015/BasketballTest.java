package edu.finki.np.jun2015;

import java.util.HashMap;

/**
 * Created by tdelev on 3.6.15.
 */
public class BasketballTest {
}

class BasketballPlayer {
  int number;
  HashMap<Integer, Integer> attempts;
  HashMap<Integer, Integer> baskets;

}
class Team {
  HashMap<Integer, BasketballPlayer> players;
  String name;
}