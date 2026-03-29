// Team Name: Gurleen-Oluwantoni Team
// Members: Gurleen Kaur, Oluwatoni Benson
// Course: CS 230
// Project: Compiler + Assembler Simulator
// Description: This program converts simple Java-like statements into
// Pep/9 assembly code and corresponding hexadecimal machine code.

import java.util.Scanner;

public class Project1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a statement:");
        System.out.println("Example: println(\"Hi\"); OR result = 3 + 5;");

        String input = sc.nextLine();

        // Decide which case to handle
        if (input.startsWith("println")) {
            handlePrint(input);
        }
        else if (input.contains("+")) {
            handleAddition(input);
        }
        else {
            System.out.println("Invalid input format.");
        }

        sc.close();
    }

    // ================= PRINTLN CASE =================
    public static void handlePrint(String input) {

        // Extract text inside quotes
        String text = input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\""));

        StringBuilder hex = new StringBuilder();

        System.out.println("\nAssembly Code:");

        // Loop through each character
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int ascii = (int) c;

            // Convert ASCII to hex
            String hexValue = String.format("%04X", ascii);

            // Print assembly instructions
            System.out.println("LDBA 0x" + hexValue + ", i");
            System.out.println("STBA 0xFC16, d");

            // Build machine code
            hex.append("D0 00 ")
                    .append(String.format("%02X", ascii))
                    .append(" F1 FC 16 ");
        }

        // Ending instructions
        System.out.println("STOP");
        System.out.println(".END");

        // Final machine code
        hex.append("00");

        System.out.println("\nMachine Code:");
        System.out.println(hex.toString());
    }

    // ================= ADDITION CASE =================
    public static void handleAddition(String input) {

        // Extract numbers from input
        String[] parts = input.split("[=+; ]+");

        int num1 = Integer.parseInt(parts[1]);
        int num2 = Integer.parseInt(parts[2]);

        System.out.println("\nAssembly Code:");

        // Print assembly instructions
        System.out.println("LDWA " + num1 + ", i");
        System.out.println("ADDA " + num2 + ", i");
        System.out.println("STBA 0xFC16, d");
        System.out.println("STOP");
        System.out.println(".END");

        // Print machine code
        System.out.println("\nMachine Code:");
        System.out.println("C0 00 " + String.format("%02X", num1) +
                " 60 00 " + String.format("%02X", num2) +
                " F1 FC 16 00");
    }
}