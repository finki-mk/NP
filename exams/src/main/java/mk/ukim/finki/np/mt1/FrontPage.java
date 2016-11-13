package mk.ukim.finki.np.mt1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FrontPage {
    private ArrayList<NewsItem> newsItems;
    private Category[] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        this.newsItems = new ArrayList<>();
    }

    public void addNewsItem(NewsItem newsItem) {
        newsItems.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category) {
        return newsItems.stream()
                .filter(each -> each.category.equals(category))
                .collect(Collectors.toList());
    }

    public List<NewsItem> listByCategoryName(String name)
            throws CategoryNotFoundException {
        Category category = Arrays.stream(categories)
                .filter(each -> each.equals(name))
                .findFirst().orElseThrow(() -> new CategoryNotFoundException(name));
        return listByCategory(category);
    }

    @Override
    public String toString() {
        return newsItems.stream()
                .map(NewsItem::getTeaser)
                .collect(Collectors.joining("\n"));
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
