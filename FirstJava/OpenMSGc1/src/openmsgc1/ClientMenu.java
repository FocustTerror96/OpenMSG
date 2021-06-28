/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openmsgc1;

import java.util.Scanner;

/**
 *
 * @author dylan
 */
public class ClientMenu {
    Scanner scan = new Scanner(System.in);
    private String username;
    private String IPAddress;
    private int port;
    private String message="";
    
    String getUsername(){
        System.out.println("Please enter your username: ");
        username = scan.nextLine();
        return username;
    }
    
    String getIPAddress(){
        System.out.println("Please enter the IP address you wish to connect: ");
        IPAddress = scan.nextLine();
        return IPAddress;
    }
    
    String getMessage(){
        while(true){
        System.out.println("Please enter the message you wish to send: ");
        message=scan.nextLine();
        if(message.isBlank()){
            continue;
        }else{
            break;
        }
        }
        return message;
    }
    
    int getPort(){
        System.out.println("Please enter the port you wish to connect to: ");
        port = scan.nextInt();
        return port;
    }
    
    
    
}
