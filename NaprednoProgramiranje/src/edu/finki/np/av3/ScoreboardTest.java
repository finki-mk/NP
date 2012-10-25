package edu.finki.np.av3;

public class ScoreboardTest {
	public static void main(String[] args) {
		Scoreboard sb = new Scoreboard();
		//sb.addScore(new Score("Risto", 1302));
		//sb.addScore(new Score("Blazo", 1107));
		//sb.addScore(new Score("Cacko", 1708));
		//sb.addScore(new Score("Toso", 301));
		//sb.addScore(new Score("Mile", 301));
		sb.save();
		System.out.println(sb);
	}
}