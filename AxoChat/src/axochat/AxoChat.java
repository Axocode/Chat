/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package axochat;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.axocode.dao.protocol;
import org.axocode.service.dao.protocolService;

public class AxoChat {
    private static final String SERVER_IP = "192.168.20.71"; // Reemplaza con la dirección IP del servidor
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Conectado al servidor");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Hilo para enviar mensajes al servidor
            Thread sendMessageThread = new Thread(() -> {
                try {System.out.println("Yo: ");
                    String userInput;
                    while (true) {
                        userInput = consoleInput.readLine();
                        if ("exit".equalsIgnoreCase(userInput)) {
                            out.println(userInput);
                            break;
                        } else if (!userInput.isEmpty()) {
                            out.println(protocolService.eco(new protocol(userInput)).resultado1.toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            sendMessageThread.start();

            String response;
            while ((response = in.readLine()) != null) {
                
                System.out.println("Personita: " + protocolService.deco(new protocol(response)).resultado1.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
