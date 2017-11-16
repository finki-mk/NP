
public class Ogre extends Alien {

	public Ogre() {
		super();
	}

	public Ogre(int health, String name) {
		super(health, name);
	}
	
	@Override
	public String getType() {
		return "Ogre";
	}

	@Override
	public int getDamage() {
		return 6;
	}

}
