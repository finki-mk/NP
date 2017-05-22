package mk.ukim.finki.np.mt2;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */
public class WebBrowserTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int limit = scanner.nextInt();
        scanner.nextLine();
        WebBrowser webBrowser = new WebBrowser(limit);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String tab = scanner.nextLine();
            int memory = scanner.nextInt();
            scanner.nextLine();
            webBrowser.addTab(tab, memory);
        }
        System.out.println(webBrowser);
        scanner.close();
    }
}

class WebBrowser {

    private final int limit;
    TreeSet<Tab> tabs;

    public WebBrowser(int limit) {
        this.limit = limit;
        this.tabs = new TreeSet<>(Comparator.comparing(Tab::getMemory).reversed()
                .thenComparing(Tab::getTitle));
    }

    public void addTab(String title, int memory) {
        if (memory > limit) return;
        int total = totalMemory();
        if (memory + total > limit) {
            freeTabs(memory + total - limit);
        }
        tabs.add(new Tab(title, memory));
    }

    void freeTabs(int memory) {
        Tab tab = tabs.pollFirst();
        if (tab == null) return;
        if (tab.getMemory() < memory) {
            freeTabs(memory - tab.getMemory());
        }
    }

    public int totalMemory() {
        return tabs.stream().mapToInt(Tab::getMemory).sum();
    }

    @Override
    public String toString() {
        String tabsStr = tabs.stream()
                .map(Tab::toString)
                .collect(Collectors.joining("\n"));
        return String.format("%s\nTotal memory: %dMB\n", tabsStr, totalMemory());

    }
}

class Tab {
    final String title;
    final int memory;

    public Tab(String title, int memory) {
        this.title = title;
        this.memory = memory;
    }

    public int getMemory() {
        return memory;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("(%dMB) %s", memory, title);
    }
}
