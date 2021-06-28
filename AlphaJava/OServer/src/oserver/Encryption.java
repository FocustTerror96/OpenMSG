/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oserver;

import java.security.*; // Imports all the security library
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException; // Imports a special part of the security library
import java.io.*; // imports all of the IO LIbrary
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.nio.file.Files; // Imports non blocking Input output library which offer featurs for intensive IO operations
import java.nio.file.Paths; // ^^ same as above just different method imported
//import java.security.GeneralSecurityException;
//import java.security.InvalidKeyException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec; // Library import Used to encode the private key into a usable form
import java.security.spec.X509EncodedKeySpec; // Library import Used to encode the public key into usable form
import java.util.Base64; // Library import used so I can convert my messages into a base64 encoded version for storage
import javax.crypto.*; // Imports the entire Crypto Library used with RSA
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;


/**
 *
 * @author sgmud
 */
public class Encryption {
    
    
    public static PrivateKey privateKey; // Initialises the private key
    public static PublicKey publicKey; // Initilises the public key
    
    public void KeyGeneration() throws NoSuchAlgorithmException{ // Generates the Private and Public Key Pair
        KeyPairGenerator Generator = KeyPairGenerator.getInstance("RSA"); // Creat the Generator object set to RSA key type
        Generator.initialize(1024); // Set the generator to make the 1024 bit key (The larger the key the longer it takes)
        KeyPair Pair = Generator.genKeyPair(); // Generate the key pair 
        publicKey = Pair.getPublic(); // Set the Public Key from the pair
        privateKey = Pair.getPrivate(); // Set the private Key from the pair
        
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded())); // Prints the public Key in console (debug feature)
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded())); // Prints the private key in console (debug feature)

              
    }

    
    
    
   
    public void LoadPublicKey(String FilePath) throws FileNotFoundException, IOException, GeneralSecurityException{ 
    // This method is responsible for Loading the public keys from the files for encryption
    byte[] publicKeyBytesLoad = Files.readAllBytes(Paths.get("RSA/publickey"));
    // Creats a byte array from the data in the specific file called "publickey"
    publicKey = getPublicKeyFromEncoded(publicKeyBytesLoad);
    // Runs my getPublicKeyFromencoded method which takes the key and converts it into X509 Format
        
    }
 
    
    
    
    public static PublicKey getPublicKeyFromEncoded(byte[] key) throws GeneralSecurityException {
        // This method is used to convert the public key from the file into X509 format
        KeyFactory kf = KeyFactory.getInstance("RSA");
        // Creats a key factory object that is set to RSA
        return (PublicKey) kf.generatePublic(new X509EncodedKeySpec(key));
        // key is converted into usable X509 form 
    }

    
    
 
    public void writeToFile(String filePath, byte[] key) throws IOException{ // Writes the public and private key to a file using a file path string and the key
        File fileToWriteto = new File(filePath); // Takes the file path string and creats the file
        fileToWriteto.getParentFile().mkdirs(); // Sets the file path to allways inside where ever the program is being run from
        FileOutputStream FoutputStream = new FileOutputStream(fileToWriteto); // Creat a new File out put stream object that writes to the file
        FoutputStream.write(key); // Writes to the file the entered key is 
        FoutputStream.flush(); // flushes the writer (removes any elements in the output stream)
        FoutputStream.close(); // Closes the writer 
        
    
    }
    
    
    
    
    public static PublicKey getPublicKey(String base64PublicKey){ 
        // gets the encoded public key from the Load public key method and turns it into its final usable form
        PublicKey publicKey = null; // initialise the public key object
        try{
            X509EncodedKeySpec KeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            // X509EncodedKeySpec class represents the ASN.1 encoding of the public key. 
            KeyFactory Keyfact = KeyFactory.getInstance("RSA"); // // creat a key factory object and set it to RSA form
            publicKey = Keyfact.generatePublic(KeySpec); // Using the X509 standard we convert the public key into a usable form
            return publicKey;   
        } catch (NoSuchAlgorithmException e) { // Catch any NoSuchAlgorithmException exeptions
            e.printStackTrace();// Displays the methods and classes used that lead to the exception
        } catch (InvalidKeySpecException e) { // Catch any InvalidKeySpecException exeptions
            e.printStackTrace(); // Displays the methods and classes used that lead to the exception
        }
        return publicKey; // returns the usable public key
    }

    public byte[] Encrypt(String data, String publicKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException{
        // This Method takes the encrypted data and the other persons public key and decrypts the message
        Cipher cipher = Cipher.getInstance("RSA"); // Creat cipher object set to RSA encryption
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey)); 
        // Cipher method init uses the first string to set it to encrypt 
        // and second string should be the public key
        // the data is converted to byte array form in this process as that is the best form for encryption and decryption
        return cipher.doFinal(data.getBytes()); // Data is then converted to byte array form
        
}

 
    
    public static PrivateKey getPrivateKey(String base64PrivateKey){ // Takes the encoded private key and decodes it
        PrivateKey privateKey = null; // initialising the private key object
        PKCS8EncodedKeySpec keySpec = new  PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        // PKCS8EncodedKeySpec class represents a type of encoding on a private key.
        // Code above creats a KeySpec object of the private key from the file in a usable ASN.1 format
        KeyFactory Kfact = null; // Initialising the key before the try catch
        try{
            
            Kfact = Kfact.getInstance("RSA"); // Creat a key factory object set to RSA
            
        } catch (NoSuchAlgorithmException e) { // Catch any NoSuchAlgorithmException exeptions
            e.printStackTrace(); // Displays the methods and classes used that lead to the exception
        }
        try{
            privateKey = Kfact.generatePrivate(keySpec); // generates the private key which can now be used for decryption
        }catch(InvalidKeySpecException e){ // Catch any InvalidKeySpecException exeptions
            e.printStackTrace(); // Displays the methods and classes used that lead to the exception
        }
        return privateKey;
    }
 
  
    
    
    public static String decrypt(byte[] data, PrivateKey privateKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException{
        // This decrypts the data and converts it into a readable form
        Cipher cipher = Cipher.getInstance("RSA"); // creating a Cipher object that will be set into RSA mode so it can use my RSA keys
        cipher.init(Cipher.DECRYPT_MODE, privateKey); // Setting the Cipher object to decode and then giving it my private key
        return new String(cipher.doFinal(data)); // Converts the byte array into a readable string
} 
    
    
    
    
    public static String Decrypt(String data, String base64PrivateKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, BadPaddingException, IllegalBlockSizeException{
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
        // calls my decrypt method with the data and the key encoded in base 64
        // The method calls the getPrivateKey method so it can take the key from the file and convert into a usable form
        // I also decode the data with Base64.getDecoder.decode method.
} 
    
      
       
        
}  
    
    
    
    
    
    
    
    
    










