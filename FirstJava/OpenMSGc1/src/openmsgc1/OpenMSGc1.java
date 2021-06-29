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
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    public BufferedReader input; // reads data coming into the socket
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        client.setup();
    }
    
    void setup(){
        username=menu.getUsername();
        IPAddress=menu.getIPAddress();
        port=menu.getPort();
        client.startConnection();
        
    }
    
    void endConnection(){
        System.out.println("ENDED");
    }
    
    void startConnection(){
        clientSocket = null;
        input = null;
        
        try{
            clientSocket = new Socket(IPAddress, port);
            
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            client.sendMessage();
            
        }catch(IOException e) {
            System.out.println("Connection failed!");
        }
    }

    void sendMessage() throws IOException{
        output = null;
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        ClientMenu menu = new ClientMenu();
        while(true){
            String message = menu.getMessage();
            output.println(message);
            System.out.println(message);
            System.out.println("Do you want to send another message (y/n)");
            String choice = scan.nextLine();
        if(choice=="n"){
            break;
        }
        }
    }
    
    //void sendData(){
    //    String data = ""
    //}
}

