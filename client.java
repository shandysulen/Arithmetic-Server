import java.io.*; //used for input and output streams
import java.net.*; //gives us our sockets

public class client {
    
    public static void main(String[] args) {              			
		
		try {
			client CLIENT = new client();	
			String serverURL = (args[0]);
			int port = Integer.parseInt(args[1]);
			CLIENT.run(serverURL, port);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java client <serverURL> <portNumber>");
			System.exit(0);
		} catch (IllegalArgumentException e) {			
			System.out.println("Please try again with a valid port number (up to 65535)");
			System.exit(0);
		}
		        
    }
    
    public void run(String serverURL, int port) { 
        try {        		
            /* Create new client socket */
            Socket SOCK = new Socket(serverURL, port);
            
            /* Create data stream */
            PrintStream out = new PrintStream(SOCK.getOutputStream());                 
            BufferedReader in = new BufferedReader(new InputStreamReader(SOCK.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in)); 
            
            /* Declarations */
            String userInput, message;

            while (true) {            
                /* Receive stream from server */
                message = in.readLine();

                /* Print error code */
                if (message.equals("-1")){
                    System.out.println("incorrect operation command.");
                }
                else if (message.equals("-2")){
                    System.out.println("number of inputs is less than two.");
                }
                else if (message.equals("-3")){
                    System.out.println("number of inputs is more than four.");
                }
                else if (message.equals("-4")){
                    System.out.println("one or more of the inputs contain(s) non-number(s).");
                }
                else if (message.equals("-5")){
                    System.out.println("exit");
                    shutdown(in, out, SOCK);
                    break;
                }
                else {
                    System.out.println(message);
                }
                /* Get user input */
                out.println(stdIn.readLine());                     
            }
        } catch (IOException e){
            System.out.println("\nAn error occurred with handling the client/server connection.");
        }
    }

    public void shutdown(BufferedReader in, PrintStream out, Socket SOCK) {
        
		/* Close socket and streams */  
		try {
			in.close();        
			out.close();        
			SOCK.close(); 
		} catch (IOException e) {
			System.out.println("An error occurred with closing the client/server connection.");
		}
    }
}
