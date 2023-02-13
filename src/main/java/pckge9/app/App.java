package pckge9.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
// import java.io.Console;
import java.io.DataInputStream;
// import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public final class App {
    private App() {
    }
    // writing everything in main program, server application
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        
        // generate random number between 0 and 100
        Integer randomNumber = rand.nextInt(100);

        // store my guess
        Integer guess = 0;

        //Console cons = System.console();
        message("Server is running");

        ServerSocket ss = new ServerSocket(1234);
        Socket s = ss.accept();                                // or ss.accept();    

        // Preparing input coming through socket from client (receiving)
        InputStream is = s.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // Preparing output using socket to client (sending out)
        OutputStream os = s.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);        
        
        String msg = "";
        
        while(!msg.equalsIgnoreCase("quit")){
            // guess XX
            msg = dis.readUTF();
            if(msg.contains("guess")){
                String[] spMsg = msg.split(" ");
                guess = Integer.parseInt(spMsg[2]);
            }


             // String strGuess = cons.readLine("Please guess the random number."); 
             //guess = Integer.parseInt(strGuess);
             if (guess < randomNumber){
                dos.writeUTF("^^^ Higher");
             }else if (guess > randomNumber) {
                dos.writeUTF("vvvv Lower");
             } else{
                dos.writeUTF("You got the right answer!"); // needs dos to send back data to client
             }
             // ensure records are written and sent across socket
             
             dos.flush();
        }
        // close all input output stream
        dos.close();
        bos.close();
        os.close();
        
        dis.close();
        bis.close();
        is.close();
    }
    private static void message(String s) {
        System.out.println(s);
    }
}
