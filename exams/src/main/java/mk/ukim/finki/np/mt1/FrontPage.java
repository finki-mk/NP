package mk.ukim.finki.np.mt1;

import java.util.ArrayList;
import java.util.Date;

public class FrontPage {
	private ArrayList<NewsItem> newsItems;
	private Category[] categories;

	public FrontPage(Category[] categories) {
		this.categories = categories;
		this.newsItems = new ArrayList<NewsItem>();
	}

	public void addNewsItem(NewsItem newsItem) {
		newsItems.add(newsItem);
	}

	public ArrayList<NewsItem> listByCategory(Category category) {
		ArrayList<NewsItem> result = new ArrayList<NewsItem>();
		for (NewsItem ni : newsItems) {
			if (ni.category.equals(category)) {
				result.add(ni);
			}
		}
		return result;
	}

	public ArrayList<NewsItem> listByCategoryName(String name)
			throws CategoryNotFoundException {
		int index = -1;
		for (int i = 0; i < categories.length; ++i) {
			if (categories[i].equals(name)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			throw new CategoryNotFoundException(name);
		}
		return listByCategory(categories[index]);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (NewsItem ni : newsItems) {
			sb.append(ni.getTeaser());
		}
		return sb.toString();
	}

}

class CategoryNotFoundException extends Exception {
	public CategoryNotFoundException(String category) {
		super(String.format("Category %s was not found", category));
	}
}

class Category {
	private String name;

	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		String s = (String) obj;
		return this.name.equals(s);
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

	public int when() {
		Date now = new Date();
		long ms = date.getTime() - now.getTime();
		return (int) (ms / 1000) / 60;
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
		return String.format("%s\n%d\n%s\n%d\n", title, when(), url, views);
	}

}

class TextNewsItem extends NewsItem {
	private static final int LEN = 80;
	private String text;

	public TextNewsItem(String title, Date date, Category category, String text) {
		super(title, date, category);
		this.text = text;
	}

	@Override
	public String getTeaser() {
		String teaser = text;
		if (text.length() > LEN) {
			teaser = text.substring(0, LEN);
		}
		return String.format("%s\n%d\n%s\n", title, when(), teaser);
	}
}
