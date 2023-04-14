package ToyShop;

public class main {

    public static void main(String[] args) {

        ToyMenu menu = new ToyMenu();
        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Машина", 3, 50));
        toyStore.addToy(new Toy(2, "Кукла", 2, 30));
        toyStore.addToy(new Toy(3, "Мяч", 4, 20));

        menu.run();
    }
}
