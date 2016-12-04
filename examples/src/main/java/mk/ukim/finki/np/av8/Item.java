package mk.ukim.finki.np.av8;

import javax.xml.bind.annotation.XmlAttribute;

public class Item {

    private String name;
    private Integer price;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s -> %d", name, price);
    }
}
