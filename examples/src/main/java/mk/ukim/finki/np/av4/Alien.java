
public abstract class Alien {
	
	private int health;
	private String name;

	public Alien(){
		this.health = 100;
		this.name = "Anonymous";
	}
	
	public Alien(int health, String name) {
		this.health = health; // 0-dead, 100-full_strength
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getType();

	public abstract int getDamage();
	
}