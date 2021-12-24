import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client");
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            osw.write("HTTP/1.0 200 OK\n" +
                    "Content-type: text/hrml\n" +
                    "\n" +
                    "Edmon's message");
            osw.flush();
            clientSocket.close();
        }
    }
}