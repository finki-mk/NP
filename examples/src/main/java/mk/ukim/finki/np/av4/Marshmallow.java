
public class Marshmallow extends Alien {

	public Marshmallow() {
		super();
	}

	public Marshmallow(int health, String name) {
		super(health, name);
	}

	@Override
	public String getType() {
		return "Marshmallow";
	}

	@Override
	public int getDamage() {
		return 1;
	}

}
