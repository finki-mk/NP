
public class AlienPack {

	private Alien[] aliens;

	public AlienPack() {
		aliens = new Alien[0];
	}

	public AlienPack(int numAliens) {
		aliens = new Alien[numAliens];
	}
	
	public void addAlien(Alien newAlien, int index){
		aliens[index] = newAlien;
	}

	public Alien[] getAliens() {
		return aliens;
	}

	public int calculateDamage() {
		int damage = 0;
		for (Alien alien : aliens) {
			damage += alien.getDamage();
		}
		return damage;
	}

}
