package productComparison;

import java.util.List;

public interface ItemCallBack {

    void success(List<Item> items);

    void success(Item item);

    void error(String error);
}
