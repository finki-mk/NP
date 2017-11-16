
public class Snake extends Alien {

	public Snake() {
		super();
	}

	public Snake(int health, String name) {
		super(health, name);
	}

	@Override
	public String getType() {
		return "Snake";
	}

	@Override
	public int getDamage() {
		return 10;
	}

}
