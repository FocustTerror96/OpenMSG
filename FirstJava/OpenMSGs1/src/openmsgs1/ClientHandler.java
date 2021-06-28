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
    private BufferedReader in;
    private PrintWriter out;
    
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
    
    
    
    
    }
    
    public void run(){
        try{
            while (true){
                String request = in.readLine();
                if (request.contains("name")){
                    out.println("Dilan");
                    
                }else{
                    out.println("Enter 'name' to be given one");
                }
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
