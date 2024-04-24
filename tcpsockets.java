Creating a peer-to-peer chat application using TCP sockets in Java involves two main components: a server and a client. However, in a peer-to-peer setting, every participant can act as both server and client. Here, 
I'll guide you through creating a simple chat program where two peers on the same machine can communicate with each other using Java.


### Implementation Steps

1. **Server-Client Setup**: Both peers will have the capability to act as both a server to listen for messages and as a client to send messages.

2. **Multi-threading**: To allow each peer to perform listening and sending simultaneously, we'll use threads.



### Java Code for Peer-to-Peer Chat

We'll write a single Java class that includes both the client and server functionalities. Each peer will initiate a server thread to listen for incoming connections and messages, and use the main thread to send messages                                                                                                                                                                                                                                                                   

#### Java Program: `PeerChat.java`

/*
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PeerChat {
    private static final int PORT = 6789; // Port number for the chat
    private static Socket socket = null;
    private static ServerSocket serverSocket = null;
    private static BufferedReader in = null; // Input stream for socket
    private static PrintWriter out = null; // Output stream for socket
    private static BufferedReader inputKeyboard = null; // Keyboard input

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        inputKeyboard = new BufferedReader(new InputStreamReader(System.in));

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Chat Server started on port " + PORT);
            System.out.println("Waiting for another peer to connect...");
            
            Thread serverThread = new Thread(() -> {
                try {
                    socket = serverSocket.accept();
                    System.out.println("A peer has connected!");
                    
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    
                    String receivedMessage;
                    while ((receivedMessage = in.readLine()) != null) {
                        System.out.println("Peer says: " + receivedMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection Error: " + e.getMessage());
                } finally {
                    try {
                        if (socket != null) socket.close();
                        if (in != null) in.close();
                        if (out != null) out.close();
                        if (serverSocket != null) serverSocket.close();
                    } catch (IOException e) {
                        System.out.println("Failed to close resources: " + e.getMessage());
                    }
                }
            });

            serverThread.start();

            // Connecting to the peer server
            System.out.println("Enter the IP address of a peer to connect:");
            String host = scanner.nextLine();
            if (!host.isEmpty()) {
                socket = new Socket(host, PORT);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            }

            // Sending messages
            System.out.println("Connected. Type your messages (type 'exit' to end):");
            String messageToSend;
            while ((messageToSend = inputKeyboard.readLine()) != null) {
                if ("exit".equalsIgnoreCase(messageToSend)) {
                    break;
                }
                out.println(messageToSend);
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
            if (serverSocket != null) serverSocket.close();
        }
    }
}
*/

### Running the Chat Application

1. **Compile the Program**:
   Open the terminal or command prompt, navigate to the directory where your program is stored, and compile the Java program:
   ```sh
   javac PeerChat.java
   ```

2. **Run the Program on Two Terminals**:
   - Open two terminal windows (Terminal A and Terminal B).
   - Run the program in both terminals:
     ```sh
     java PeerChat
     ```
   - In one of the terminals (say Terminal A), wait for the peer to connect.
   - In the other terminal (Terminal B), when prompted, enter `localhost` to connect back to the first terminal.
   - Now you can start chatting from both terminals.

Each terminal represents a peer, and messages typed in one terminal should appear in the other. This setup demonstrates the basics of a peer-to-peer chat application using Java TCP sockets, where each instance can send and receive messages simultaneously.