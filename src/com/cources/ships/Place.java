package com.cources.ships;

import java.util.Random;
import java.util.Scanner;

public class Place {
    private static char[][] userBoard;
    public static char[][] opponentBoard;

    public Place() {
        userBoard = new char[16][16];
        opponentBoard = new char[16][16];
        initializeField();
    }

    public void initializeField() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                userBoard[i][j] = '□';
                opponentBoard[i][j] = '□';
            }
        }
    }

    public void placeOpponentShips() {
        int[] shipSizes = {6, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1};
        Random random = new Random();
        for (int shipSize : shipSizes) {
            int shipCount = shipSizes.length - shipSize;
            for (int i = 0; i < shipCount; i++) {
                boolean placed = false;
                while (!placed) {
                    int row = random.nextInt(16);
                    int col = random.nextInt(16);
                    boolean vertical = random.nextBoolean();
                    if (canPlaceShip(row, col, shipSize, vertical)) {
                        placeShip(row, col, shipSize, vertical);
                        placed = true;
                    }
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int shipSize, boolean vertical) {
        if (vertical && row + shipSize <= 16) {
            for (int i = row; i < row + shipSize; i++) {
                if (opponentBoard[i][col] != '□') {
                    return false;
                }
            }
            return true;
        } else if (!vertical && col + shipSize <= 16) {
            for (int j = col; j < col + shipSize; j++) {
                if (opponentBoard[row][j] != '□') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void placeShip(int row, int col, int shipSize, boolean vertical) {
        if (vertical) {
            for (int i = row; i < row + shipSize; i++) {
                opponentBoard[i][col] = '■';
            }
        } else {
            for (int j = col; j < col + shipSize; j++) {
                opponentBoard[row][j] = '■';
            }
        }
    }
    public static void drawSeaField() {
        for (int i = 0; i < userBoard.length; i++) {
            for (int j = 0; j < userBoard[i].length; j++) {
                userBoard[i][j] = '□'; // '□' пустая клетка
            }
        }

        for (int i = 0; i < userBoard.length; i++) {
            for (int j = 0; j < userBoard[i].length; j++) {
                System.out.print(userBoard[i][j] + "  ");
            }
            System.out.println(" " + (i + 1)); // Подпись строк
        }

        for (int i = 0; i < 16; i++) {
            System.out.print((char) ('A' + i) + "  "); // Подпись колонок
        }
    }

    public void fillShipUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nКорабли 6 типов: 6 клеток - 1 штука, 5 клеток - 2 штуки, 4 клетки - 3 штуки, 3 клетки - 4 штуки, 2 клетки - 5 штук, 1 клетка - 6 штук");
        for (int count = 0; count <= 20; count++) {
            System.out.println("\nВведите координаты корабля (A2, B2, B3): ");
            String input = scanner.nextLine().toUpperCase();
            String[] coordinates = input.split(", ");

            for (String coordinate : coordinates) {
                int row = Integer.parseInt(coordinate.substring(1)) - 1;
                int col = coordinate.charAt(0) - 'A';
                userBoard[row][col] = '■';  // '■' клетка с кораблем
            }
            // Вывод поля с новым добавленным кораблем
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    System.out.print(userBoard[i][j] + "  ");
                }
                System.out.println(" " + (i + 1));
            }
            for (int i = 0; i < 16; i++) {
                System.out.print((char) ('A' + i) + "  ");
            }
        }
        System.out.println();
        System.out.println("Поле соперника!");
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(opponentBoard[i][j] + "  ");
            }
            System.out.println();
        }
    }
}