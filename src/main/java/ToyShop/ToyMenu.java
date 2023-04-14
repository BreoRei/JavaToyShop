package ToyShop;

import java.util.Scanner;

public class ToyMenu {
    private Scanner scanner;
    private boolean isRunning;

    public ToyMenu() {
        scanner = new Scanner(System.in);
        isRunning = true;
    }

    public void run() {
        while (isRunning) {
            System.out.println("Меню:");
            System.out.println("1. Добавить новую игрушку");
            System.out.println("2. Изменить вес игрушки");
            System.out.println("3. Изменить количество игрушек");
            System.out.println("4. Выдать приз");
            System.out.println("5. Провести розыгрышь");
            System.out.println("6. Выйти из меню");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    ToyStore.addNewToy();
                    break;
                case 2:
                    ToyStore.changeWeight();
                    break;
                case 3:
                    ToyStore.changeQuantity();
                    break;
                case 4:
                    ToyStore.removeToPrize();
                    break;
                case 5:
                    ToyStore.playGame();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Выход из меню");
                    break;
                default:
                    System.out.println("Ошибка ввода");
                    break;
            }
            System.out.println();
        }
    }
}
