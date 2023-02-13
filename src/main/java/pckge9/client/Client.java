package pckge9.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
    public static void main(String[] args) throws IOException{
    
        Console cons = System.console();
        int guess;
        String msgRecv = "", keyInput ="";
        Socket socket = new Socket("localhost", 1234);
        
        try(OutputStream os = socket.getOutputStream()){      
            
            // allow server to send output
            System.out.println(socket);

            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);  
                                                                   
            // allow server to receive input
            try (InputStream is = socket.getInputStream()){                       //keyboard would be system.in or console readLine
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);                 // to convert into UTF
                            
                while(!keyInput.equalsIgnoreCase("quit")){
                    keyInput = cons.readLine("Please guess the random number.");
                    guess = Integer.parseInt(keyInput); 
                    dos.writeUTF(keyInput);
                    dos.flush();

                    msgRecv = dis.readUTF();
                    System.out.println("From server: " + msgRecv);
                }

                bos.close();
                dos.close();
                socket.close();

            }catch (EOFException e){
                e.printStackTrace();
                socket.close();
            } 
        }catch(EOFException ex){
            ex.printStackTrace();
            socket.close();
        }
    }
}
