package com.sailfish.ch8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author sailfish
 * @create 2017-05-10-下午3:45
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = null;
        PrintWriter printWriter = null;
        BufferedReader reader = null;

        client = new Socket();
        try {
            client.connect(new InetSocketAddress("localhost", 8000));
            printWriter = new PrintWriter(client.getOutputStream(), true);
            printWriter.write("hello");
            printWriter.flush();

            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(": from server" + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (client != null) {
                client.close();
            }
        }

    }
}
