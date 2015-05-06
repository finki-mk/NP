package edu.finki.np.av5;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FinkiNews {
	public static void main(String[] args) {
        // Reading
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
            int min = scanner.nextInt();
			cal.add(Calendar.MINUTE, -min);
			Date date = cal.getTime();
			scanner.nextLine();
			String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
			TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
			frontPage.addNewsItem(tni);
		}
        
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -min);
			scanner.nextLine();
			Date date = cal.getTime();
			String url = scanner.nextLine();
			int views = scanner.nextInt();
			scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
			MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
			frontPage.addNewsItem(mni);
		}
        // Execution
        String category = scanner.nextLine();
		System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
        	System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
	}
}

abstract class NewsItem {
	protected String title;
	protected Date date;
	protected Category category;

	public NewsItem(String title, Date date, Category category) {
		this.title = title;
		this.date = date;
		this.category = category;
	}

	public abstract String getTeaser();

	public int getMinPublished() {
		long timeSpan = new Date().getTime() - date.getTime();
		return (int) (timeSpan / 1000) / 60;
	}

}

class TextNewsItem extends NewsItem {
	private String text;

	public TextNewsItem(String title, Date date, Category category, String text) {
		super(title, date, category);
		this.text = text;
	}

	@Override
	public String getTeaser() {
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		sb.append("\n");
		sb.append(getMinPublished());
		sb.append("\n");
		if (text.length() > 80) {
			sb.append(text.substring(0, 80));
		} else {
			sb.append(text);
		}
		return sb.toString();
	}

}

class MediaNewsItem extends NewsItem {
	private String url;
	private int views;

	public MediaNewsItem(String title, Date date, Category category,
			String url, int views) {
		super(title, date, category);
		this.url = url;
		this.views = views;
	}

	@Override
	public String getTeaser() {
		return String.format("%s\n%d\n%s\n%d", title, getMinPublished(), url,
				views);
	}

}

class Category {
	private String name;

	public Category(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		Category c = (Category) obj;
		return this.name.equals(c.name);
	}
}

class FrontPage {
	private List<NewsItem> newsItems;
	private Category[] categories;

	public FrontPage(Category[] categories) {
		this.categories = categories;
		newsItems = new ArrayList<NewsItem>();
	}

	public void addNewsItem(NewsItem newsItem) {
		newsItems.add(newsItem);
	}

	public List<NewsItem> listByCategory(Category category) {
		List<NewsItem> result = new ArrayList<NewsItem>();
		for (NewsItem ni : newsItems) {
			if (ni.category.equals(category)) {
				result.add(ni);
			}
		}
		return result;
	}

	public List<NewsItem> listByCategoryName(String category)
			throws CategoryNotFoundException {
		Category c = new Category(category);
		boolean found = false;
		for (Category cc : categories) {
			if (cc.equals(c)) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new CategoryNotFoundException(category);
		}
		return listByCategory(c);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (NewsItem ni : newsItems) {
			result.append(ni.getTeaser());
			result.append("\n");
		}
		return result.toString();
	}
}

class CategoryNotFoundException extends Exception {

	public CategoryNotFoundException(String category) {
		super(category);
	}

}
