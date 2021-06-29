/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmsgs1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sgmud
 */
public class ClientHandler implements Runnable{
    private Socket client;
    public BufferedReader in;
    public PrintWriter out;
    
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream());
    
    
    
    }
    
    @Override
    public void run(){
        
        try{
            while(true){
                System.out.println("dilan");
                String request = in.readLine();
                System.out.println(request);
            }
        }catch(IOException e){
            System.err.println(e.getStackTrace());
        }finally{
            out.close();
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
             
    }
    
    
    
    
    
    
}
