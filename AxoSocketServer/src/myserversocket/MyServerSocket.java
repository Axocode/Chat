package myserversocket;

import org.axocode.service.dao.protocolService;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axocode.dao.protocol;

public class MyServerSocket {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String msgWrite = null;
        int port = 8888;
        String msgRead = null;
        boolean cont = true;

        try {
            serverSocket = new ServerSocket(port);
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.INFO, "Waiting...");
            socket = serverSocket.accept();

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (cont) {
                System.out.print("Mensaje: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                msgWrite = "Servidor: "+reader.readLine();
                String config = msgWrite;
                msgWrite = protocolService.eco(new protocol(msgWrite)).resultado1.toString();

                
                    printWriter.println(msgWrite);
                    msgRead = bufferedReader.readLine();

                    
                    msgRead = protocolService.deco(new protocol(msgRead)).resultado1.toString();

                    if (msgRead != null) {
                        Logger.getLogger(MyServerSocket.class.getName()).log(Level.INFO, msgRead);
                    }
                
                if (config.equals("Servidor: adios")) {
                    cont = false;
                }
            }

            bufferedReader.close();
            printWriter.close();
            socket.close();
            serverSocket.close();
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.INFO, "End");
        } catch (IOException ex) {
            Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
