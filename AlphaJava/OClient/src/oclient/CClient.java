/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oclient;

import  java.net.*; // Import javas networking library
import  java.io.*; // Import javas input output librarys
import com.dosse.upnp.UPnP; // Inport WaifUPnP Library
import java.util.Scanner; // Import The scanner from the Util Library
import java.util.Timer; // Import the timer from the Util Library
import java.util.TimerTask; // import the TimerTask from the Util Library
/**
 *
 * @author sgmud
 */
public class CClient { // client class
    public Socket CSocket; // Socket will be created with a successfull conection between programs
    public PrintWriter output; // writes to the socket
    public BufferedReader in; // reads data coming into the socket
    public Scanner scan = new Scanner(System.in); // Creats new scanner object called scan that can read a users input
    
    public int GetPort(){ // Allows the user to enter a custom port
        
        System.out.println("please enter the port number"); 
        int PortNum = scan.nextInt(); // Will save the entered portnumber as PortNum
        return PortNum; // Will return the users port number
    }
    
    public String GetAddress(){ // Get the ip address of the host
        
        System.out.println("Please enter the IP address of the host");
        String Address = scan.nextLine(); // Saves the Users IP address as a string 
        return Address; // Returns the users entered address
    }
    
    public void StartConnection(String ip, int port){ // Main method responsible for connecting to a listening server
        CSocket = null; //---//
        output = null; // These are setting the variables inside the method so its within the scope of the try catch
        in = null; //---//
        try{
            CSocket = new Socket(ip, port); // This will creat a socket with a machine thats listening on the specifid port with that IP address
            output = new PrintWriter(CSocket.getOutputStream(), true); // // Setting the printWriter to the variable output
            in = new BufferedReader(new InputStreamReader(CSocket.getInputStream())); // Setting the Buffered Reader to the variable in
            Timer timer = new Timer(); // Creating a timer object
           
            
            

            timer.scheduleAtFixedRate( // Runs the sceduleAtFixedRate method which creats a thred that runs periodically
            new TimerTask() 
            {
                public void run() // The code within this runs periodically
                {
                    Listner();// runs Listner method periodically
                    
                }
            },
            0,      // run first occurrence immediately
            1000);  // run every 1 seconds
            
            // CClient server = new CClient();
            convosation(); // Main loop method 
        
        }catch(IOException e){ // catch any IOException errors 
                System.out.println("ERROR"); // Display error of an error is caught
        }
    
    }
    
    
    
    public void sendMessage(String msg){ // Sends a message to the socket with a message
        if(msg.equals("null")){ // If there is nothing entered
                
                sendMessage(Message());// Make the user re-enter a message to send
        }        
        else{
            output.println(msg); // send the message to the Server
        }       
  
    }  
        
    
        
    

    
    
    public void convosation(){  // method will keep letting you send a message untill you stop
        
        while (true){
            
            System.out.println("Type QUIT to end the convosation or press any key to send a message");
            String qit = scan.nextLine(); // Saves the users entered message as string qit
            if("QUIT".equals(qit)){ // IF they enter QUIT
                STOP(); // Will stop the program
            }
            else{
                sendMessage(Message()); // Runs the send message method with the output from the Message method
                
            }
        }       
    }

    public String Message(){   // gets the users entered message                 
             
            System.out.println("please enter a mesasge to be sent");
            String message = scan.nextLine(); // Saves the users entred message as string message
           
            return message; // returns the users entered message
        
    }
    
    public void STOP(){ // Halts the program by closing all the running proccesses apropriately
        try{
            in.close(); // Closes the BufferedReader
            output.close(); // Closes the PrintWriter
            CSocket.close(); // Closes the socket
        }catch(IOException e){ // catches IOException error
                System.out.println("ERROR"); // Prints Error if an error is caught
        }
    }
    
    public void Listner() { // Runs periodically listening for messages
        String message = ""; // Creating a local variable message
        try{
            message = in.readLine(); // Setting the message equal to the data coming in from the socket
                
            
        }catch(IOException e){
                
        }
        if(message.equals("")==false){System.out.println(message);} // If the message dosent equal nothing then it is displayed
    }
    
    
    
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        CClient client = new CClient(); // Making a new client class
        client.StartConnection(client.GetAddress(), client.GetPort()); // runs the startConnection method but runs the Get address and Get port method first so the Start connection method has the IP and Port number 
        
        // client.STOP(); // runs the stop method which will terminate the server
    }
    
    
    
}
