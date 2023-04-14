package ToyShop;

public class Toy {
    protected int id;
    protected String name;
    protected int quantity;
    protected int weight;

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
