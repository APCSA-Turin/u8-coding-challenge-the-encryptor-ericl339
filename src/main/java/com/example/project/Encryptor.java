package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        int col = 0;
        for (int i = 0; i <= messageLen; i ++) {
            if (rows * i >= messageLen) {
                col = i;
                break;
            }
        }
        if (messageLen == 0) {
            return 1;
        }
        return col;
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        String[][] outcome = new String[rows][determineColumns(message.length(), rows)];
        int count = 1;
        for (int row = 0; row < outcome.length; row ++) {
            for (int col = 0; col < outcome[0].length; col ++) {
                if (count != message.length() + 1) {
                    outcome[row][col] = message.substring(count - 1, count);
                    count ++;
                }
                else {
                    outcome[row][col] = "=";
                }
            }
        }
        return outcome;
    }

    public static String encryptMessage(String message, int rows){
        String[][] array = generateEncryptArray(message, rows);
        String outcome = "";
        for (int col = array[0].length - 1; col >= 0; col --) {
            for (int row = 0; row < array.length; row ++) {
                outcome += array[row][col];
            }
        }
        return outcome;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        String[][] array = generateEncryptArray(encryptedMessage, rows);
        String outcome = "";
        int count = 1;
        for (int col = array[0].length - 1; col >= 0; col --) {
            for (int row = 0; row < array.length; row ++) {
                if (count > encryptedMessage.length()) {
                    return null;
                }
                array[row][col] = encryptedMessage.substring(count - 1, count);
                count ++;
            }
        }
        for (int row = 0; row < array.length; row ++) {
            for (int col = 0; col < array[0].length; col ++) {
                if (!array[row][col].equals("=")) {
                    outcome += array[row][col];
                }
            }
        }
        return outcome;
    }

    public static void main(String[] args) {
        String message = "meet= g p=sasy=isar.hswcdTe ne";
        int rows = 5;
        // String[][] outcome = generateEncryptArray(message, rows);
        // int count = 1;
        // for (int col = outcome[0].length - 1; col >= 0; col --) {
        //     for (int row = 0; row < outcome.length; row ++) {
        //         if (count > message.length() - 1) {
        //             break;
        //         }
        //         outcome[row][col] = message.substring(count - 1, count);
        //         count ++;
        //         System.out.print(outcome[row][col]);
        //     }
        //     System.out.println();
        // }
        // System.out.println(decryptMessage(message, rows));
        // String[][] outcome = generateEncryptArray(message, rows);
        // for (int row = 0; row < outcome.length; row ++) {
        //     for (int col = 0; col < outcome[0].length; col ++) {
        //         System.out.print(outcome[row][col] + " ");
        //     }
        //     System.out.println();
        // }
        System.out.println(decryptMessage(message, rows));
        // System.out.println(encryptMessage(message, rows));
    }
}