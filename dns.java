To create a DNS lookup program in Java that can handle both IP address to URL resolution (reverse DNS lookup) and URL to IP address resolution (forward DNS lookup), you can use the `InetAddress` class provided by Java's `java.net` package. This class offers methods to easily retrieve the IP address of a host from its hostname and vice versa.

Here's a simple Java program that allows the user to perform both forward and reverse DNS lookups based on their input:

### Java Program for DNS Lookup

/*
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DNSLookup {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter URL or IP address (type 'exit' to quit):");
            String input = scanner.nextLine();

            // Exit condition
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            try {
                if (input.matches("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$")) {
                    // Input is an IP address
                    System.out.println("Performing reverse DNS lookup...");
                    InetAddress address = InetAddress.getByName(input);
                    String hostName = address.getHostName();
                    System.out.println("Hostname: " + hostName);
                } else {
                    // Input is a hostname
                    System.out.println("Performing forward DNS lookup...");
                    InetAddress[] addresses = InetAddress.getAllByName(input);
                    for (InetAddress addr : addresses) {
                        System.out.println("IP Address: " + addr.getHostAddress());
                    }
                }
            } catch (UnknownHostException e) {
                System.out.println("Error: Host not found for " + input);
            }
        }

        scanner.close();
    }
}
*/

### How to Compile and Run the Program:

1. **Save the code** in a file named `DNSLookup.java`.
2. **Open your terminal** and navigate to the directory containing your file.
3. **Compile the program** using the Java compiler:

   javac DNSLookup.java

4. **Run the compiled program** with the Java interpreter:

   java DNSLookup

5. **Use the program** by entering "localhost" when prompted. Type `exit` to quit the program.

### Explanation:
- **Forward DNS Lookup**: When the user inputs a hostname, the program uses `InetAddress.getAllByName(String host)` to get an array of `InetAddress` objects representing all of the IP addresses for the given hostname. It prints out all IP addresses associated with the hostname.
- **Reverse DNS Lookup**: When the user inputs an IP address, the program uses `InetAddress.getByName(String host)` to create an `InetAddress` object. Then, it retrieves the hostname associated with the given IP address using `getHostName()`. This works assuming the IP address has a reverse DNS record set up.

This simple tool handles basic DNS lookups but note that network access is required, and the results depend on the current DNS configurations and records available for the queried hosts.