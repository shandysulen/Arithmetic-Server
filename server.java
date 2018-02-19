import java.io.*; //used for input and output streams
import java.net.*; //gives us our sockets

public class server {
    
    public static void main(String[] args) {  
	
		try {
			server SERVER = new server();				
			int port = Integer.parseInt(args[0]);
			SERVER.run(port);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java server <portNumber>");
			System.exit(0);
		} catch (IllegalArgumentException e) {			
			System.out.println("Please try again with a valid port number (up to 65535)");
			System.exit(0);
		}		
                       
    }
    
    public void run(int port) {
        try {
            /* Create new Server Socket */
            ServerSocket SRVSOCK = new ServerSocket(port);                                   

            listen:
            while (true) {                    
                /* Instantiate socket connection */
                Socket SOCK = SRVSOCK.accept();  

                /* Send connection acknowledgement */
                PrintStream out = new PrintStream(SOCK.getOutputStream());
                out.println("Hello!");                                    

                /* Instantiate input stream */                    
                BufferedReader in = new BufferedReader(new InputStreamReader(SOCK.getInputStream()));
                String message;

                while (true) {
					
                    /* Display message from client */  
                    try {
                        message = in.readLine();
                        message = message.trim();
                        System.out.println(message);
                    } catch (SocketException e) {
                        System.out.println("Client suddenly ended its connection...");
                        break;
                    }

                    /* Parse tokens & check type */
                    String[] tokens = message.split("\\s+");                                
                    boolean allNumbers = true;
                    for (int i = 1; i < tokens.length; i++){
                        if (tokens[i].matches("[^0-9]")){                    
                            allNumbers = false;
                            break;
                        }
                    }                       

                    /* Error Handling */
                    if (message.equals("bye")){                
                        out.println(-5);
                        SOCK.close();                
                        break;
                    }
                    else if (message.equals("terminate")){                
                        out.println(-5);
                        shutdown(in, out, SOCK, SRVSOCK);
                        break listen;              
                    }  
                    else if (!tokens[0].equals("add") && !tokens[0].equals("subtract") && !tokens[0].equals("multiply")){                
                        out.println(-1);
                    }
                    else if (tokens.length < 3){                
                        out.println(-2);
                    }
                    else if (tokens.length > 5){                
                        out.println(-3);
                    }
                    else if (!allNumbers) {                
                        out.println(-4);
                    }
                    else {                                      
                        /* Convert string operands to ints */
                        int[] operands = new int[tokens.length - 1];
                        for (int i = 1; i < tokens.length; i++){
                            operands[i-1] = Integer.parseInt(tokens[i]);
                        }

                        /* Perform arithmetic operation */
                        if (tokens[0].equals("add")) { 
                            int sum = 0;
                            for (int i = 0; i < operands.length; i++){
                                sum += operands[i];
                            }
                            out.println(sum);
                        }
                        else if (tokens[0].equals("subtract")) {
                            int difference = operands[0];
                            for (int i = 1; i < operands.length; i++){
                                difference -= operands[i];
                            }
                            out.println(difference);
                        }
                        else if (tokens[0].equals("multiply")) {
                            int product = 1;
                            for (int i = 0; i < operands.length; i++){
                                product *= operands[i];
                            }
                            out.println(product);
                        }                
                    }
                }  
            }
        } catch (IOException e) {
            System.out.println("\nAn error occurred with handling the client/server connection.");
        }
    }      
    
    public void shutdown(BufferedReader in, PrintStream out, Socket SOCK, ServerSocket SRVSOCK) {
		
        /* Close socket and streams */
		try {
			in.close();        
			out.close();        
			SOCK.close();        
			SRVSOCK.close();     
		} catch (IOException e) {
			System.out.println("An error occurred with closing the client/server connection.");
		}
    }
}                     