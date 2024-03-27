package com.example.ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class myServer extends Thread {
    private ServerSocket serverSocket;
    private boolean running = false;

    public myServer() {
        try {
            serverSocket = new ServerSocket(7800);
            System.out.println("Serverul este pornit si asculta pe portul 7800");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Eroare la pornirea serverului");
            return;
        }

        start();
    }

    @Override
    public void run() {
        running = true;
        System.out.println("Serverul este pornit si asculta pe portul 7800");

        while (running) {
            try {
                if (serverSocket != null) {
                    Socket clientSocket = serverSocket.accept(); //asteapta conexiuni de la clienti

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String messageFromClient = in.readLine(); //citirea mesajului trimis
                    System.out.println("Mesajul primit: " + messageFromClient);

                    clientSocket.close(); // s-a inchis conexiunea
                } else {
                    System.err.println("Eroare: serverSocket este null.");
                    // Adăugați aici orice alte acțiuni pe care doriți să le faceți în caz de eroare.
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
