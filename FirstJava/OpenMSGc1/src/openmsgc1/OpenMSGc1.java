/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmsgc1;

import java.io.IOException;
import java.util.Scanner;
import java.net.*;
import com.dosse.upnp.UPnP; // Inport WaifUPnP Library
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JTextField;
/**
 *
 * @author sgmud
 */
public class OpenMSGc1 {
    Scanner scan = new Scanner(System.in);
    static OpenMSGc1 client = new OpenMSGc1();
    static ClientMenu menu = new ClientMenu();
    private String username="";
    private String IPAddress="";
    private Integer port;
    public Socket clientSocket;
    public PrintWriter output; // writes to the socket
    public OutputStream f;
    public BufferedReader input; // reads data coming into the socket
    private static ExecutorService Serv = Executors.newFixedThreadPool(10);
    public Encryption e = new Encryption();
    public int k = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, GeneralSecurityException { 
        client.setup();
    }
    
    void setup() throws IOException, FileNotFoundException, GeneralSecurityException{
        ClientGUI gui = new ClientGUI();     
        gui.setVisible(true);
    }
    
    void endConnection(){
        System.out.println("ENDED");
    }
    
    void startConnection(String user, String IP, int prt) throws FileNotFoundException, GeneralSecurityException{
        clientSocket = null;
        input = null;
        username = user;
        IPAddress = IP;
        port = prt;
        if(k==0){    
            try {
                e.KeyGeneration();
                e.writeToFile("RSA/publickey", e.publicKey.getEncoded());
                e.writeToFile("RSA/privatekey", e.privateKey.getEncoded());
                k++;
                
            } catch (IOException ex) {
            Logger.getLogger(OpenMSGc1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(OpenMSGc1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
        try{
            
            e.LoadPublicKey("RSA/publickey");
            clientSocket = new Socket(IPAddress, port);
            ClientReceiver receiverThread = new ClientReceiver(clientSocket);
            Serv.execute(receiverThread);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
        }catch(IOException e) {
            System.err.println(e);
        }
    }

    
     public void sendMessage(String msg) throws FileNotFoundException, GeneralSecurityException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException{ 
        String message = msg;
        byte[] encryptedMessage = e.Encrypt(message, Base64.getEncoder().encodeToString(Encryption.publicKey.getEncoded()));
        if(msg.equals("null")){
                
                
        }        
        else{
            try{
            
            System.out.print("Message After Encryption:"+Arrays.toString(encryptedMessage));
            f = clientSocket.getOutputStream();
            output = new PrintWriter(f, true);
            String encodedmsg = new String(encryptedMessage, "ISO-8859-1");
            System.out.print("Message After Encryption and encoding:"+ encodedmsg);
            output.println(encodedmsg);
            
            
            
            }catch (IOException e){
                System.out.println("ERROR");
            }
            
            
            
            
            
            
        }       
 
    }      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 /**   
    
    void sendMessage() throws IOException{
        output = null;
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        ClientMenu menu = new ClientMenu();
        while(true){
            String message = menu.getMessage();
            output.println(message);
            // System.out.println(message);
            
        }
    }
    
    //void sendData(){
    //    String data = ""
    //}
    **/
}

