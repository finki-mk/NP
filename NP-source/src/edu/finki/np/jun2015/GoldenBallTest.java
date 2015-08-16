package edu.finki.np.jun2015;

import java.util.*;

/**
 * Created by tdelev on 3.6.15.
 */
public class GoldenBallTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    GoldenBall goldenBall = new GoldenBall();
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line == null || line.isEmpty()) break;
      String[] players = line.split(";");
      goldenBall.addVote(players);
    }
    scanner.close();
    System.out.println("===== TOP 10 ======");
    goldenBall.listTopN(10);
    System.out.println("===== MAX 1 PLACE =====");
    goldenBall.maxPlace(1);
    System.out.println("===== MAX 2 PLACE =====");
    goldenBall.maxPlace(2);
    System.out.println("===== MAX 3 PLACE =====");
    goldenBall.maxPlace(3);
  }
}

class GoldenBall {
  HashMap<Integer, HashMap<String, Integer>> votes;
  int total;

  public GoldenBall() {
    total = 0;
    votes = new HashMap<Integer, HashMap<String, Integer>>();
    votes.put(1, new HashMap<String, Integer>());
    votes.put(2, new HashMap<String, Integer>());
    votes.put(3, new HashMap<String, Integer>());
  }

  public void addVote(String[] players) {
    total += 9;
    for (int i = 0; i < 3; ++i) {
      HashMap<String, Integer> pv = votes.get(i + 1);
      Integer total = pv.get(players[i]);
      if (total == null) {
        total = 0;
      }
      ++total;
      pv.put(players[i], total);
    }
  }

  public void listTopN(int n) {
    HashMap<String, Player> players = new HashMap<String, Player>();
    for (int i = 1; i <= 3; ++i) {
      HashMap<String, Integer> pv = votes.get(i);
      for (Map.Entry<String, Integer> entry : pv.entrySet()) {
        Player player = players.get(entry.getKey());
        int points = 0;
        if (i == 1) points = 5 * entry.getValue();
        else if (i == 2) points = 3 * entry.getValue();
        else if (i == 3) points = 1 * entry.getValue();
        if (player == null) {
          player = new Player(entry.getKey(), points);
        } else {
          player.points += points;
        }
        players.put(entry.getKey(), player);
      }
    }
    List<Player> all = new ArrayList<Player>(players.values());
    Collections.sort(all);
    for (int i = 0; i < n; ++i) {
      System.out.println(all.get(i).toString(total));
    }
  }

  public void maxPlace(int x) {
    HashMap<String, Integer> players = votes.get(x);
    String maxPlayer = null;
    int maxVotes = 0;
    for (Map.Entry<String, Integer> p : players.entrySet()) {
      if (p.getValue() > maxVotes) {
        maxVotes = p.getValue();
        maxPlayer = p.getKey();
      }
    }
    System.out.println(maxPlayer);
  }
}

class Player implements Comparable<Player> {
  String name;
  int points;

  public Player(String name, int points) {
    this.name = name;
    this.points = points;
  }

  @Override
  public int compareTo(Player o) {
    return Integer.compare(o.points, points);
  }

  public String toString(int total) {
    return String.format("%-30s %d %.2f%%", name, points, points * 100.0 / total);
  }
}