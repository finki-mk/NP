package edu.finki.np.jun2015;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by tdelev on 3.6.15.
 */
public class BasketballTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    String[] teams = line.split("\\s+");
    Game game = new Game(teams[0], teams[1]);
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();
      String[] parts = line.split("\\s+");
      int second = Integer.parseInt(parts[0]);
      String team = parts[1];
      int player = Integer.parseInt(parts[2]);
      boolean basket = Integer.parseInt(parts[3]) == 1;
      int points = Integer.parseInt(parts[4]);
      game.attempt(second, team, player, points, basket);
    }
    game.score();
    game.stats();
  }
}

class Game {
  Team home;
  Team guest;
  Set<Integer> time;

  public Game(String home, String guest) {
    this.home = new Team(home);
    this.guest = new Team(guest);
    time = new TreeSet<Integer>();
  }

  public void attempt(int second, String team, int player, int points, boolean basket) {
    time.add(second);
    if (team.equals(home.name)) {
      home.attempt(second, player, points, basket);
    } else {
      guest.attempt(second, player, points, basket);
    }
    if (basket) {
      home.update(second);
      guest.update(second);
    }
  }

  public void score() {
    for (Integer moment : time) {
      if (home.points.containsKey(moment)) {
        System.out.printf("%2d:%02d %3d - %-3d\n", moment / 60, moment % 60,
          home.points.get(moment), guest.points.get(moment));
      }
    }
  }

  public void stats() {
    System.out.println(home.name);
    home.stats();
    System.out.println(guest.name);
    guest.stats();
  }
}

class BasketballPlayer {
  int number;
  HashMap<Integer, Integer> attempts;
  HashMap<Integer, Integer> baskets;

  public BasketballPlayer(int number) {
    this.number = number;
    attempts = new HashMap<Integer, Integer>();
    baskets = new HashMap<Integer, Integer>();
  }

  public void attempt(int points, boolean basket) {
    Integer a = attempts.get(points);
    if (a == null) {
      a = 1;
    } else {
      ++a;
    }
    attempts.put(points, a);
    if (basket) {
      Integer b = baskets.get(points);
      if (b == null) {
        b = 1;
      } else {
        ++b;
      }
      baskets.put(points, b);
    }
  }

  public String stats(int points) {
    Integer b = baskets.get(points);
    Integer a = attempts.get(points);
    if (b == null) b = 0;
    if (a == null) a = 0;
    return String.format("%d/%d (%.1f%%)", b, a, a == 0 ? 0 : b * 100. / a);
  }

  public int points() {
    int p = 0;
    for (int i = 1; i <= 3; ++i) {
      Integer b = baskets.get(i);
      if (b == null) b = 0;
      p += b * i;
    }
    return p;
  }
}

class Team {
  HashMap<Integer, BasketballPlayer> players;
  HashMap<Integer, Integer> points;
  String name;
  int score;

  public Team(String name) {
    this.name = name;
    score = 0;
    players = new HashMap<Integer, BasketballPlayer>();
    for (int i = 4; i <= 15; ++i) {
      players.put(i, new BasketballPlayer(i));
    }
    points = new HashMap<Integer, Integer>();
  }

  public void attempt(int second, int player, int points, boolean basket) {
    BasketballPlayer basketballPlayer = players.get(player);
    basketballPlayer.attempt(points, basket);
    if (basket) {
      score += points;
    }
  }

  public void update(int second) {
    this.points.put(second, score);
  }

  public void stats() {
    System.out.printf("%2s. %-15s%-15s%-15s%3s\n",
      "NO", "1", "2", "3", "P");
    for (Integer p : players.keySet()) {
      BasketballPlayer player = players.get(p);
      System.out.printf("%2d. %-15s%-15s%-15s%3d\n",
        p, player.stats(1), player.stats(2), player.stats(3), player.points());
    }
  }
}