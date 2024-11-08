package utils;

import java.util.Scanner;
import managers.ProductManager.InputType;

public class GetInput {
    public static String getInput(String message, InputType type, Integer min, Integer max) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String input = "";

        switch (type) {
            case INT:
                int intInput;
                while (true) {
                    while (!scanner.hasNextInt()) {
                        System.out.println("Lütfen geçerli bir tamsayı girin:");
                        scanner.next();
                    }
                    intInput = scanner.nextInt();
                    if ((min == null || intInput >= min) && (max == null || intInput <= max)) {
                        input = String.valueOf(intInput);
                        break;
                    } else {
                        System.out.println("Lütfen " + min + " ile " + max + " arasında bir değer girin.");
                    }
                }
                break;

            case FLOAT:
                double doubleInput;
                while (true) {
                    while (!scanner.hasNextDouble()) {
                        System.out.println("Lütfen geçerli bir ondalıklı sayı girin:");
                        scanner.next();
                    }
                    doubleInput = scanner.nextDouble();
                    if ((min == null || doubleInput >= min) && (max == null || doubleInput <= max)) {
                        input = String.valueOf(doubleInput);
                        break;
                    } else {
                        System.out.println("Lütfen " + min + " ile " + max + " arasında bir değer girin.");
                    }
                }
                break;

            case STRING:
                while (true) {
                    input = scanner.next();
                    if (input.matches("[a-zA-ZçÇğĞşŞüÜöÖıİ]+") && (max == null || input.length() <= max)) {
                        break;
                    } else {
                        System.out.println("Lütfen geçerli bir string girin (max " + max + " karakter):");
                    }
                }
                break;

            case LETTER:
                while (true) {
                    input = scanner.next();
                    if (input.matches("[a-zA-ZçÇğĞşŞüÜöÖıİ]") && input.length() == 1) {
                        break;
                    } else {
                        System.out.println("Lütfen geçerli bir harf girin:");
                    }
                }
                break;

            default:
                System.out.println("Geçersiz tür.");
                break;
        }
        return input;
    }
}
