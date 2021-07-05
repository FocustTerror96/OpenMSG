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
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author sdadasd
 */
public class ClientReceiver implements Runnable{
    private Socket client;
    public BufferedReader in;
    public PrintWriter out;
    public Encryption e;
    
    public ClientReceiver(Socket clientSocket) throws IOException {
        this.client = clientSocket;
    }
    
    @Override
    public void run(){
        in = null;
        out = null;
        String request = null;
        try {
            
            this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.out = new PrintWriter(client.getOutputStream());
            
            while(true){
                request = in.readLine();
                String DecryptedMSG = "";
                
                
                if(request!=null){
                    System.out.println("Got to the if statement"); 
                    byte[] decodedmsg = request.getBytes(Charset.forName("ISO-8859-1")); // decodes message
                    try {
                        System.out.println(request);
                        DecryptedMSG = e.decrypt(decodedmsg, e.privateKey); // decrypts message with users private key
                        System.out.println("Past line 55 on thread");
                    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException ex) {
                        Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.out.println("No request");
                }
                if(DecryptedMSG.equals(null)==false){
                System.out.println(DecryptedMSG);
                // Outputs message to user
                }
                
                
                
                // System.out.println(request);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
