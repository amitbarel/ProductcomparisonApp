package productComparison;

public class Item {
    private int id;
    private String name;
    private String brand;
    private double cost;
    private int weight;
    private int alternative;

    public Item() {
    }

    public Item(int id, String name, String brand, double cost, int weight, int alternative) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.cost = cost;
        this.weight = weight;
        this.alternative = alternative;
    }

    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Item setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Item setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public Item setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public int getAlternative() {
        return alternative;
    }

    public Item setAlternative(int alternative) {
        this.alternative = alternative;
        return this;
    }
}
