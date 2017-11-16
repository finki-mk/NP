
public class TestAliens {

	public static void main(String[] args) {
		AlienPack aliens = new AlienPack(7);
		int i = 0;
		aliens.addAlien(new Marshmallow(75, "Prv"), i++);
		aliens.addAlien(new Ogre(80, "Vtor"), i++);
		aliens.addAlien(new Marshmallow(10, "Tret"), i++);
		aliens.addAlien(new Snake(25, "Chetvrt"), i++);
		aliens.addAlien(new Snake(15, "Petti"), i++);
		aliens.addAlien(new Marshmallow(60, "Shesti"), i++);
		aliens.addAlien(new Ogre(5, "Sedmi"), i++);

		System.out.println(aliens.calculateDamage());
	}

}
