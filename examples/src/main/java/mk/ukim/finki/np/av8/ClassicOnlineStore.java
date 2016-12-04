package mk.ukim.finki.np.av8;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ClassicOnlineStore {

    protected final OnlineShoppingMall mall =
            JAXB.unmarshal(new File("examples/data/shop.xml"), OnlineShoppingMall.class);

    public static void main(String[] args) {
        ClassicOnlineStore store = new ClassicOnlineStore();
        List<Customer> customerList = store.mall.getCustomerList();

        customerList.forEach(System.out::println);
    }

    List<String> iterateCustomers() {
        List<String> customerNames = new ArrayList<>();

        Consumer<Customer> customerName = customer -> customerNames.add(customer.getName());

        mall.getCustomerList().forEach(customerName);
        return customerNames;
    }
}
