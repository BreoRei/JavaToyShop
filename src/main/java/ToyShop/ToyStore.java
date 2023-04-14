package ToyShop;

import java.io.*;
import java.util.*;

public class ToyStore {

    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Toy> toys = new ArrayList<>();
    private static List<String> prizeToys = new ArrayList<>();

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    static void addNewToy() {
        System.out.println("Добавляем новую игрушку в магазин!");
        int toyCount = toys.get(toys.size()-1).getId()+1;
        System.out.print("Введите название игрушки: ");
        String name = scanner.nextLine();
        System.out.print("Введите количество игрушек: ");
        int quantity = scanner.nextInt();
        System.out.print("Введите шанс выпадения игрушки: ");
        int weight = scanner.nextInt();
        toys.add(new Toy(toyCount, name, quantity, weight));
        System.out.printf("Новая грушка %s: %dшт. Добавлена в магазин.\n", name, quantity);
        scanner.nextLine();
    }

    public static void changeWeight() {
        storeWarehouse();
        System.out.print("Введите id игрушки: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                System.out.printf("Шанс выпадения %s: %d%s\n", toy.getName(), toy.getWeight(), "%");
                System.out.print("Введите новое значение шанса 1-100: ");
                int weight = scanner.nextInt();
                toy.setWeight(weight);
                return;
            }
        }
    }

    public static void changeQuantity() {
        storeWarehouse();
        System.out.print("Введите id игрушеки: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                System.out.printf("Количество %s: %dшт\n", toy.getName(), toy.getQuantity());
                System.out.print("Введите новое количество игрушек: ");
                int quantity = scanner.nextInt();
                toy.setQuantity(quantity);
                return;
            }
        }
    }

    public static void storeWarehouse() {
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(4), "-".repeat(17),"-".repeat(8),"-".repeat(6));
        System.out.printf("|%3s | %-15s | %6s | %4s |%n", "Id", "Название", "Кол-во", "Шанс");
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(4), "-".repeat(17),"-".repeat(8),"-".repeat(6));
        for (Toy toy : toys) {
            System.out.printf("|%3s | %-15s | %6s | %4s |%n", toy.getId(), toy.getName(), toy.getQuantity(), toy.getWeight());
        }
        System.out.printf("+%s+%s+%s+%s+%n", "-".repeat(4), "-".repeat(17),"-".repeat(8),"-".repeat(6));
    }

    static void addToPrize(String prize) {
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("prize.txt", true));
            br.write(prize);
            br.write("\n");
            br.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void removeToPrize() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("prize.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lines.size() == 0) {
            System.out.println("Все призы выданы");
        } else {
            System.out.println("Ваш приз " + lines.get(0));
            lines.remove(0);
            try {
                BufferedWriter br = new BufferedWriter(new FileWriter("prize.txt"));
                for (String ln : lines) {
                    br.write(ln);
                    br.write("\n");
                }
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void playGame() {
        if (toys.size()==0){
            System.out.println("Кошмар! В магазине нет игрушек");
        } else {
            System.out.println("Игра началась!");
            String prize = selectWinner();
            System.out.println("Вы выиграли " + prize);
            addToPrize(prize);
        }
    }

    public static String selectWinner() {
        String prize = "";
        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }
        int randomWeight = new Random().nextInt(totalWeight);
        for (Toy toy : toys) {
            randomWeight -= toy.getWeight();
            if (randomWeight <= 0) {
                toy.setQuantity(toy.getQuantity() - 1);
                prizeToys.add(toy.getName());
                prize = toy.getName();
                if (toy.getQuantity() <= 0) {
                    toys.remove(toy);
                }
                return prize;
            }
        }
        return prize;
    }
}