Creating a peer-to-peer chat application using UDP sockets in Java involves handling both sending and receiving datagrams within a single application instance. Unlike TCP, UDP is connectionless, which means you do not need to establish a connection before sending or receiving data. Each peer in the UDP chat can freely send and receive messages without formal handshakes.

### Implementation Steps

1. **UDP Sockets**: Each peer will use a `DatagramSocket` for sending and receiving messages.
2. **Multi-threading**: We'll use threads to handle receiving messages asynchronously, allowing the user to type messages for sending simultaneously.

### Java Code for Peer-to-Peer Chat using UDP

We will write a single Java class that serves both sending and receiving functionalities. The class will use threading to handle receiving data continuously while the main thread will be used for sending data.

#### Java Program: `UDPPeerChat.java`

/*
import java.io.*;
import java.net.*;

public class UDPPeerChat {
    private static final int PORT = 6789; // Port number for the chat
    private static DatagramSocket socket = null;
    private static InetAddress address = null;

    public static void main(String[] args) throws IOException {
        try {
            socket = new DatagramSocket(PORT);
            System.out.println("UDP Chat Server is running on port " + PORT);
            address = InetAddress.getByName("localhost");

            // Thread to receive messages
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        byte[] receiveBuffer = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                        socket.receive(receivePacket);
                        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Peer says: " + receivedMessage);
                    }
                } catch (IOException e) {
                    System.out.println("IOException: " + e.getMessage());
                }
            });
            receiveThread.start();

            // Reading messages from keyboard and sending
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type your messages (type 'exit' to end):");

            String messageToSend;
            while ((messageToSend = reader.readLine()) != null) {
                if ("exit".equalsIgnoreCase(messageToSend)) {
                    break;
                }
                byte[] sendBuffer = messageToSend.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, PORT);
                socket.send(sendPacket);
            }

        } catch (SocketException se) {
            System.out.println("Socket Exception: " + se.getMessage());
        } catch (IOException ie) {
            System.out.println("IO Exception: " + ie.getMessage());
        } finally {
            if (socket != null) socket.close();
        }
    }
}
*/

### Running the UDP Chat Application

1. **Compile the Program**:
   Open the terminal or command prompt, navigate to the directory where your program is stored, and compile the Java program:

   javac UDPPeerChat.java


2. **Run the Program on Two Terminals**:
   - Open two terminal windows (Terminal A and Terminal B).
   - Run the program in both terminals:

     java UDPPeerChat

   - Since UDP is connectionless, you can start typing and sending messages in either terminal without establishing a connection.

In this setup, each instance can send and receive messages simultaneously using UDP. Messages typed in one terminal should appear in the other as long as both are using the same port and network. Adjust the `address` if you want to test across different machines on the same network by replacing `localhost` with the appropriate IP address.