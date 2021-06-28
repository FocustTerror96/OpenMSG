/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oserver;

import  java.net.*; // Import javas networking library
import  java.io.*; // Import javas input output librarys
import com.dosse.upnp.UPnP; // Inport WaifUPnP Library
import java.util.Scanner; // Import The scanner from the Util Library
import java.util.Timer; // Import the timer from the Util Library
import java.util.TimerTask;// import the TimerTask from the Util Library

/**
 *
 * @author sgmud
 */
public class SServer { // Server Class 
    public ServerSocket SSocket; // Serversocket is used to listen for incoming connections
    public Socket CSocket; // Socket will be created with a successfull conection between programs
    public PrintWriter out; // This will write to the other program
    public BufferedReader in; // This will listen for incoming data over the socket
    public Scanner scan = new Scanner(System.in); // This creats a new scanner object
    
    public int GetPort(){ // Gets port number for socket to be set listening to
        
        System.out.println("please enter the port number"); // Asks the user to enter the port
        int PortNum = scan.nextInt(); // Saves the users input as PortNum
        return PortNum; // Sets the GetPort Method to the port number
        
    }    
    
    public void start(int port) { // Starts the server with the collected port
        out = null; // ----------------------//
        in = null; // ----- These Three Lines Set The variables before the try Catch -----//
        CSocket = null; //------------------//
        try{
            System.out.println("Server Started"); // Lets the user know that this method is running
            UPnP.openPortTCP(port); // WaifUPnP library auto port forwards this port on your router
            SSocket = new ServerSocket(port); // Takes the port the user enters and starts a server socket with it
            CSocket = SSocket.accept(); // When A client trys to connect to the server a socket is made
            System.out.println("Server Connected"); // Lets the user know a successfull connection was made
            
            out = new PrintWriter(CSocket.getOutputStream(), true); // Setting the global variable out to a PrintWriter
            in = new BufferedReader(new InputStreamReader(CSocket.getInputStream())); // Setting the global variable in to a BufferedReader
            Timer timer = new Timer(); // Creating a timer object called timer
            //int seconds;
            //seconds = 1;
            
            

            timer.scheduleAtFixedRate( // Creats a thred that will run every specific time period
            new TimerTask() // 
            {
                public void run() // The code in this method will run periodically
                {
                    Listner(); // Runs a specific listner method I have created 
                    
                }
            },
            0,      // run first occurrence immediately
            1000);  // run every 1 seconds
            
            
            convosation(); // Runs Convosation Method
            

            }catch(IOException e){ // Catches the error as variable e
                System.out.println("ERROR"); // Prints error if there is an error
        }   
    }

    
        

  
    public String Message(){ // Askss the user for an input, then returns that input         
             
            System.out.println("please enter a mesasge to be sent");
            String message = scan.nextLine(); // Saves the users input as string message
           
            return message; // returns the Message the user entered
        
    }
    
    public void convosation(){  // method will keep letting you send a message untill you stop
        
        while (true){ // Keeps this in loop as its essentially the main menue of the program
            
            System.out.println("Type QUIT to end the convosation or press any key to send a message");
            String qit = scan.nextLine(); // Saves the users input as qit
            if("QUIT".equals(qit)){ // If the user types quit then the program will end otherwise they will be able to send a message
                STOP(); // Kills the program
            }
            else{
                sendMessage(Message()); // Runs the send message method with the output from the Message method
                
            }
        }       
    }
    
  

        public void sendMessage(String msg){ // Takes the users entered message and outputs it to the socket
        if(msg.equals("null")){
                
                sendMessage(Message());// Makes them enter something again if its nothing
        }        
        else{
            out.println(msg); // Will send there message to the other connected device
        }        
                
    }  
        
        
    public static void main(String[] args){ // Starts the server by running the Start Method with GetPort method as a paramater
        
        SServer server = new SServer(); // Creat new server class
        server.start(server.GetPort()); // Starts the server with the port number
        
        
    }

       public void STOP(){ // Will close all running processes properly
        try{
            in.close(); // Closes the BufferedWriter
            out.close(); // Closes the PrintWriter
            CSocket.close(); // Closes the socket
        }catch(IOException e){ // Catches any IOException Errors
                System.out.println("ERROR"); // Displays an error message
        }
    }
    
    
    
       
        
        public void Listner() { // Runs every secong listening for messages from the client
                String message = ""; // Sets the variable before the try catch
            try{
                message = in.readLine(); // Reads what ever has been sent through the socket 
                
            
            }catch(IOException e){
                
            }
            if(message.equals("")==false){System.out.println(message);} 
            // If the message is not nothing then it will display it
        }


    }
    
    
    