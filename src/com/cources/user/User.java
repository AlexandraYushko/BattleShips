package com.cources.user;

import java.util.Scanner;

public class User {
    private static boolean checkShip(int row, int col, char[][] opponentBoard) {
        char cell = opponentBoard[row][col];
        if (cell != '✖') {
            return false;
        }
        int rowCount = opponentBoard.length;
        int colCount = opponentBoard[0].length;
        // Проверка вертикали
        int top = row;
        while (top >= 0 && opponentBoard[top][col] == '✖') {
            top--;
        }
        int bottom = row;
        while (bottom < rowCount && opponentBoard[bottom][col] == '✖') {
            bottom++;
        }
        if (bottom - top - 1 >= 6) {
            return true;
        }
        // Проверка горизонтали
        int left = col;
        while (left >= 0 && opponentBoard[row][left] == '✖') {
            left--;
        }
        int right = col;
        while (right < colCount && opponentBoard[row][right] == '✖') {
            right++;
        }
        if (right - left - 1 >= 6) {
            return true;
        }
        return false;
    }

    public static void stepUser(char[][] opponentBoard) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите координаты для удара (A5): ");
        String input = scanner.nextLine().toUpperCase();
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        if (checkShip(row, col, opponentBoard)) {
            System.out.println("Убил!");
            opponentBoard[row][col] = '✖';
        } else if (!checkShip(row, col, opponentBoard)) {
            System.out.println("Мимо!");
            opponentBoard[row][col] = '￭';
        } else {
            System.out.println("Ранил!");
            opponentBoard[row][col] = '✖';
        }
    }
}