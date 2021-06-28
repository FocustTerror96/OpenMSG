/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmsgs1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.net.*; // Import javas networking library
import java.io.*; // Import javas input output librarys
import com.dosse.upnp.UPnP; // Inport WaifUPnP Library
/**
 *
 * @author sgmud
 */


public class OpenMSGs1{
    public Scanner scan = new Scanner(System.in);
    public ServerSocket SSocket; // Serversocket is used to listen for incoming connections
    public Socket CSocket; // Socket will be created with a successfull conection between programs
    public int PortNumber;
    public static OpenMSGs1 server = new OpenMSGs1();
    /**
     * @param args the command line arguments
     */
      
    
    public static void main(String[] args) {
     
     server.GetPort();
    }
          
    
    
    public void GetPort(){
        System.out.println("Please Enter the Port");
        PortNumber = scan.nextInt();
        server.setup();
    }

    
    private void setup(){
        System.out.println("Server Starting...");
        try{
        UPnP.openPortTCP(PortNumber); // WaifUPnP library auto port forwards this port on your router
        SSocket = new ServerSocket(PortNumber); // Takes the port the user enters and starts a server socket with it
        CSocket = SSocket.accept(); // When A client trys to connect to the server a socket is made 
        System.out.println("Cleint Connected");
        }catch(IOException e){ // Catches the error as variable e
            System.out.println("ERROR"); // Prints error if there is an error
            
        }
        
        
    }






}




