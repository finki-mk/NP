package mk.ukim.finki.np.av8;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Shop {

    private String name;
    private List<Item> itemList;

    @XmlAttribute
    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "item")
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
