import java.util.Scanner;

public class SubnetCalculator {
    
    // Converts an integer to a binary string of a given length
    private static String toBinaryString(int number, int length) {
        StringBuilder binary = new StringBuilder(Integer.toBinaryString(number));
        while (binary.length() < length) {
            binary.insert(0, "0");
        }
        return binary.toString();
    }
    
    // Calculates the subnet mask from the number of bits for the network part
    private static String calculateSubnetMask(int bitsForNetwork) {
        int mask = 0xFFFFFFFF << (32 - bitsForNetwork);
        return String.format("%d.%d.%d.%d", 
            (mask >>> 24) & 0xFF, 
            (mask >>> 16) & 0xFF, 
            (mask >>> 8) & 0xFF, 
            mask & 0xFF);
    }
    
    // Calculates the required number of bits to represent a number
    private static int bitsRequired(int numSubnets) {
        int bits = 0;
        int value = 1;
        while (value < numSubnets) {
            value *= 2;
            bits++;
        }
        return bits;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the base IP address (e.g., 192.168.1.0): ");
        String ipAddress = scanner.nextLine();
        
        System.out.println("Enter the number of required subnets: ");
        int numberOfSubnets = scanner.nextInt();
        
        int bitsForSubnets = bitsRequired(numberOfSubnets);
        int baseBits = 0;
        
        // Assuming a default class C network for simplicity
        // Modify baseBits for other classes as needed
        baseBits = 24;
        
        int totalBits = baseBits + bitsForSubnets;
        
        // Calculate and print subnet mask
        String subnetMask = calculateSubnetMask(totalBits);
        System.out.println("Subnet mask required: " + subnetMask);
        
        scanner.close();
    }
}
/*

### How to Run This Program:
1. **Save the Code**: Save the above code to a file named `SubnetCalculator.java`.
2. **Compile the Java Program**:
   - Open a terminal or command prompt.
   - Navigate to the directory containing your `SubnetCalculator.java` file.

      cd Desktop


      cd 121B1B258

   - Compile the program by running the command:

     javac SubnetCalculator.java

3. **Run the Compiled Java Program**:
   - After compilation, run the program using:

     java SubnetCalculator

   - Follow the prompts to input the base IP address and the number of required subnets.

### Program Explanation:
- **Input**: The user enters a base IP address and the number of subnets they wish to create.
- **Processing**:
  - The program calculates how many bits are needed to create the required number of subnets.
  - It combines these bits with the default base bits for a Class C network (which is 24 bits; modify this for different classes).
  - It then calculates the subnet mask corresponding to the total number of network bits.
- **Output**: The program outputs the calculated subnet mask.

This simple approach shows subnet mask calculation and can be expanded or refined to include more features like detailed subnetting information or support for different IP classes.
*/