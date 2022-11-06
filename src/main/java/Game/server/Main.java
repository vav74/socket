package Game.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String city = "";
        try (ServerSocket serverSocket = new ServerSocket(8989)) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    System.out.println("Connection accepted");
                    if (city.equals("")) {
                        out.println("???");
                        city = in.readLine().toLowerCase();
                        out.println("OK");
                    } else {
                        out.println(city);
                        String resp = in.readLine().toLowerCase();
                        if (resp.charAt(0) == city.charAt(city.length() - 1)) {
                            out.println("OK");
                            city = resp;
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}