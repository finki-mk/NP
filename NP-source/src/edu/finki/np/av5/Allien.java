package edu.finki.np.av5;

public abstract class Allien {
	private String name;
	private int health;
	
	public Allien(String name, int health) {
		this.name = name;
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}
	
	public abstract int getDamage();
	
}
