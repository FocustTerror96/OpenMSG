/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmsgc1;

import java.io.IOException;
import java.util.Scanner;
import java.net.*;

/**
 *
 * @author sgmud
 */
public class OpenMSGc1 {
    Scanner scan = new Scanner(System.in);
    static OpenMSGc1 client = new OpenMSGc1();
    private String username="";
    private String IPAddress="";
    public Socket CSocket;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        client.setup();
    }
    
    void setup(){
        System.out.println("Please enter your desired username: ");
        username = scan.nextLine();
        System.out.println("Please enter the ip of your target server: ");
        IPAddress = scan.nextLine();
        System.out.println(username+IPAddress);
        client.startConnection();
    }
    
    void endConnection(){
        System.out.println("ENDED");
    }
    void startConnection(){
        try{
            CSocket = new Socket(IPAddress, 6666);
        }catch(IOException e) {
            System.out.println("1");
        }
    }

    void sendMessage(){
        System.out.println("hello sam");        
    }
}

