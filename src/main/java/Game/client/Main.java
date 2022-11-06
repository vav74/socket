package Game.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8989;
        Scanner scanner = new Scanner(System.in);
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String resp = in.readLine();
            System.out.println(resp);
            System.out.println("Назовите город:");
            String input = scanner.nextLine();
            out.println(input);
            resp = in.readLine();
            System.out.println(resp);
        }
        scanner.close();
    }
}