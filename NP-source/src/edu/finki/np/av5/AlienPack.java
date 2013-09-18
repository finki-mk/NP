package edu.finki.np.av5;

class Alien {
	public static final int SNAKE_ALIEN = 0;
	public static final int OGRE_ALIEN = 1;
	public static final int MARSHMALLOW_MAN_ALIEN = 2;
	public int type; // Stores one of the three above types
	public int health; // 0=dead, 100=full strength
	public String name;

	public Alien(int type, int health, String name) {
		this.type = type;
		this.health = health;
		this.name = name;
	}
}

public class AlienPack {
	private Allien[] aliens;

	public AlienPack(int numAliens) {
		aliens = new Allien[numAliens];
	}

	public void addAlien(Allien newAlien, int index) {
		aliens[index] = newAlien;
	}

	public Allien[] getAliens() {
		return aliens;
	}

	public int calculateDamage() {
		int damage = 0;
		for (int i = 0; i < aliens.length; i++) {
			damage += aliens[i].getDamage();
		}
		return damage;
	}
}
