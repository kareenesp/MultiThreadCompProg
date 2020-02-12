/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author BlackMoon
 */
public class Server {

    public Server(int port) {
        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out
                = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));) 
        {

            String inputLine, outputLine;

            ServerProtocols sp = new ServerProtocols();
            outputLine = sp.proccessClientInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = sp.proccessClientInput(inputLine);
                out.println(outputLine);
//                if (outputLine.equals("Exit")) {
//                    break;
//                }
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
