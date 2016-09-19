package mk.ukim.finki.np.mt1;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class FrontPageTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] parts = line.split(" ");
		Category[] categories = new Category[parts.length];
		for (int i = 0; i < categories.length; ++i) {
			categories[i] = new Category(parts[i]);
		}
		int n = scanner.nextInt();
		scanner.nextLine();
		FrontPage frontPage = new FrontPage(categories);
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
			cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, scanner.nextInt());
			Date date = cal.getTime();
			scanner.nextLine();
			String text = scanner.nextLine();
			TextNewsItem tni = new TextNewsItem(title, date, categories[i
					% categories.length], text);
			frontPage.addNewsItem(tni);
		}
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
			cal.add(Calendar.MINUTE, scanner.nextInt());
			scanner.nextLine();
			Date date = cal.getTime();
			String url = scanner.nextLine();
			int views = scanner.nextInt();
			scanner.nextLine();
			MediaNewsItem mni = new MediaNewsItem(title, date, categories[i
					% categories.length], url, views);
			frontPage.addNewsItem(mni);
		}
		System.out.println(frontPage);
	}
}
