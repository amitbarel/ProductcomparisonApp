package productComparison;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<Item> items;
    private ArrayList<Item> alt_1_items;
    private ArrayList<Item> alt_2_items;

    public DataManager(){
        items = new ArrayList<>();
        alt_1_items = new ArrayList<>();
        alt_2_items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public DataManager setItems(ArrayList<Item> items) {
        this.items = items;
        return this;
    }

    public ArrayList<Item> getAlt_1_items() {
        return alt_1_items;
    }

    public DataManager setAlt_1_items(ArrayList<Item> alt_1_items) {
        this.alt_1_items = alt_1_items;
        return this;
    }

    public ArrayList<Item> getAlt_2_items() {
        return alt_2_items;
    }

    public DataManager setAlt_2_items(ArrayList<Item> alt_2_items) {
        this.alt_2_items = alt_2_items;
        return this;
    }
}
