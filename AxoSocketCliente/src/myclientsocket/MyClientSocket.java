package myclientsocket;

import org.axocode.service.dao.protocolService;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axocode.dao.protocol;

public class MyClientSocket {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String ip = "";
        String ipConfig = "";
        int port = 0;
        String msgWrite = null;
        String msgRead = null;
        boolean cont = true;

        Scanner post = new Scanner(System.in);
        System.out.println("Ingresar el puerto de la persona con la que quieras chatear");
        System.out.println("Si quieres chatear de manera local escribe local");
        System.out.print("Ingresa: ");
        ipConfig = post.next();
        if (ipConfig.equals("local")) {
            ip = "127.0.0.1";
        } else {
            ip = ipConfig;
        }

        System.out.println("");
        System.out.print("Ingrese el puerto: ");
        port = post.nextInt();

        try {
            socket = new Socket(ip, port);
            System.out.println("Conectado");

            while (cont) {
                System.out.print("Ingrese: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                msgWrite = "Cliente: "+reader.readLine();
                String config = msgWrite;
                msgWrite = protocolService.eco(new protocol(msgWrite)).resultado1.toString();

                printWriter = new PrintWriter(socket.getOutputStream(), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                
                    printWriter.println(msgWrite);
                    msgRead = bufferedReader.readLine();

                    msgRead = protocolService.deco(new protocol(msgRead)).resultado1.toString();


                    if (msgRead != null) {
                        Logger.getLogger(MyClientSocket.class.getName()).log(Level.INFO, msgRead);
                    }
                
                if (config.equals("Cliente: adios")) {
                    cont = false;
                }
            }

            bufferedReader.close();
            printWriter.close();
            socket.close();
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.INFO, "End");
        } catch (IOException ex) {
            Logger.getLogger(MyClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
