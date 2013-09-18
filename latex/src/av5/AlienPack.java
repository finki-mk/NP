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
	private Alien[] aliens;

	public AlienPack(int numAliens) {
		aliens = new Alien[numAliens];
	}

	public void addAlien(Alien newAlien, int index) {
		aliens[index] = newAlien;
	}

	public Alien[] getAliens() {
		return aliens;
	}

	public int calculateDamage() {
		int damage = 0;
		for (int i = 0; i < aliens.length; i++) {
			if (aliens[i].type == Alien.SNAKE_ALIEN) {
				damage += 10;// Snake does 10 damage
			} else if (aliens[i].type == Alien.OGRE_ALIEN) {
				damage += 6;// Ogre does 6 damage
			} else if (aliens[i].type == Alien.MARSHMALLOW_MAN_ALIEN) {
				damage += 1;
				// Marshmallow Man does 1 damage
			}
		}
		return damage;
	}
}
