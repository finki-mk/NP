package edu.finki.np.mt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Transactions 2015/11/14 first midterm
 */

public class ViewTest {
  public static void main(String[] args) {
    View view = new View();
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for (int i = 0; i < n; ++i) {
      view.addButton(i + 1, String.format("BUTTON %d", i + 1));
    }
    n = scanner.nextInt();
    for (int i = 0; i < n; ++i) {
      int x = scanner.nextInt();
      view.setOnButtonClickListener(x, new LongButtonClickImpl());
    }
    for (int i = 0; i < n; ++i) {
      int x = scanner.nextInt();
      view.setOnButtonClickListener(x, new ShortButtonClickImpl());
    }
    for (int i = 0; i < n; ++i) {
      try {
        view.clickButton(i + 1);
      } catch (UnsupportedOperationException e) {
        System.out.println(e.getMessage());
      }
    }
    scanner.close();
  }

}

interface OnButtonClick {
  void onClick(Button button);
}

class ShortButtonClickImpl implements OnButtonClick {
  @Override
  public void onClick(Button button) {
    System.out.println(String.format("Button with id '%s' was clicked SHORT"));
  }
}

class LongButtonClickImpl implements OnButtonClick {
  @Override
  public void onClick(Button button) {
    System.out.println(String.format("Button with id '%s' was clicked SHORT"));
  }
}

class View {
  List<Button> buttons;

  public View() {
    buttons = new ArrayList<Button>();
  }

  public void addButton(int id, String name) {
    buttons.add(new Button(id, name));
  }

  public void setOnButtonClickListener(int id, OnButtonClick onButtonClick) {
    for (Button button : buttons) {
      if (button.id == id) {
        button.setOnButtonClick(onButtonClick);
      }
    }
  }

  public void clickButton(int id) {
    for (Button button : buttons) {
      if (button.id == id) {
        button.click();
      }
    }
  }
}

class Button {
  int id;
  String name;

  public Button(int id, String name) {
    this.id = id;
    this.name = name;
  }

  OnButtonClick onButtonClick;

  public void setOnButtonClick(OnButtonClick onButtonClick) {
    this.onButtonClick = onButtonClick;
  }

  public void click() {
    if (onButtonClick == null) throw new UnsupportedOperationException("Button click is not supported");
    onButtonClick.onClick(this);
  }
}
