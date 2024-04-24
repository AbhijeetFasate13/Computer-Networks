Creating and sending TCP and UDP packets in Java involves using sockets provided by the Java Standard Library. Java offers robust support for both TCP and UDP communication via its `java.net.Socket` (for TCP) and `java.net.DatagramSocket` (for UDP) classes. I'll guide you through creating simple programs for both TCP and UDP to send packets between two peers in a peer-to-peer mode.

### TCP Communication

TCP (Transmission Control Protocol) is a connection-oriented protocol that ensures the reliable delivery of a stream of data between two points (peers). The steps involved in sending and receiving data using TCP are:

1. **Create a Server Socket**: This listens for incoming TCP connections.
2. **Create a Client Socket**: This initiates the connection to the server.
3. **Send/Receive Data**: Once the connection is established, data can be sent and received.

#### Example TCP Server (Java)

/*
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        int port = 6789;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started and listening on port " + port);

        while (true) {
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Client connected from " + connectionSocket.getInetAddress());

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            String clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);

            String capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
*/

#### Example TCP Client (Java)

/*
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        String serverIP = "localhost";
        int port = 6789;
        Socket clientSocket = new Socket(serverIP, port);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a sentence: ");
        String sentence = inFromUser.readLine();

        outToServer.writeBytes(sentence + '\n');
        String modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();
    }
}
*/

### UDP Communication

UDP (User Datagram Protocol) is a connectionless protocol that allows sending packets (datagrams) without establishing a connection. Here's how you can implement simple UDP communication:

1. **Create a Datagram Socket**: Unlike TCP, no connection needs to be established.
2. **Send/Receive Datagram Packets**: Packets are sent to and received from any peer.

#### Example UDP Server (Java)

/*
import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("UDP Server listening on port 9876");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Received: " + sentence);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
*/

#### Example UDP Client (Java)

/*
import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData;
        byte[] receiveData = new byte[1024];

        System.out.print("Enter a sentence: ");
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}
*/

### Running the Programs

To run these programs, compile each `.java` file and start the server first, then the client. For example:

1. Open two terminals.
2. In one, compile and run the server using:
   ```
   javac TCPServer.java
   java TCPServer
   ```
   (Or `javac UDPServer.java` and `java UDPServer` for UDP.)
3. In the other, compile and run the client using:
   ```
   javac TCPClient.java
   java TCPClient
   ```
   (Or `javac UDPClient.java` and `java UDPClient` for UDP.)

Ensure that the server is running before the client attempts to connect to it.