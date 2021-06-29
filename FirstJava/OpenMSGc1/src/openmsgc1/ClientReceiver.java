/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmsgc1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dylan
 */
public class ClientReceiver implements Runnable{
    private Socket client;
    public BufferedReader in;
    public PrintWriter out;
    
    public ClientReceiver(Socket clientSocket) throws IOException {
        this.client = clientSocket;
    }
    
    @Override
    public void run(){
        in = null;
        out = null;
        try {
            System.out.println("poo poo");
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream());
            while(true){
                String request = in.readLine();
                System.out.println(request);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
