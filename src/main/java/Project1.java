import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // CASE 1: println("text")
        if (input.startsWith("println")) {
            String text = input.substring(input.indexOf("\"") + 1, input.lastIndexOf("\""));

            StringBuilder hex = new StringBuilder();

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                int ascii = (int) c;

                // Assembly
                System.out.println("LDBA 0x" + String.format("%04X", ascii) + ", i");
                System.out.println("STBA 0xFC16, d");

                // Hex
                hex.append("D0 00 ")
                        .append(String.format("%02X", ascii))
                        .append(" F1 FC 16 ");
            }

            System.out.println("STOP");
            System.out.println(".END");

            hex.append("00");
            System.out.println("\nMachine Code:");
            System.out.println(hex.toString());
        }

        // CASE 2: result = number + number
        else if (input.contains("+")) {
            String[] parts = input.split("[=+; ]+");

            int num1 = Integer.parseInt(parts[1]);
            int num2 = Integer.parseInt(parts[2]);

            // Assembly
            System.out.println("LDWA " + num1 + ", i");
            System.out.println("ADDA " + num2 + ", i");
            System.out.println("STBA 0xFC16, d");
            System.out.println("STOP");
            System.out.println(".END");

            // Hex (basic version based on Pep9 pattern)
            System.out.println("\nMachine Code:");
            System.out.println("C0 00 " + String.format("%02X", num1) +
                    " 60 00 " + String.format("%02X", num2) +
                    " F1 FC 16 00");
        }

        sc.close();
    }
}