package mk.ukim.finki.np.av8;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Customer {

    private String name;
    private Integer age;
    private Integer budget;
    private List<Item> wantToBuy;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @XmlAttribute
    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @XmlElement(name = "item")
    public List<Item> getWantToBuy() {
        return wantToBuy;
    }

    public void setWantToBuy(List<Item> wantToBuy) {
        this.wantToBuy = wantToBuy;
    }

    @Override
    public String toString() {
        return String.format("%-10s %2dy %5d", name, age, budget);
    }
}
