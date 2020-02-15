// Imports
import java.io.*;				// DataStream Input/Output & File input and output
import java.util.*;				// Vectos and Map
import java.net.*;				// Java socket 
import java.awt.*;				// Threads
import java.rmi.*; 

public class User{
    public static void main(String args[]){
        try{
            if(args.length != 2){
                System.out.println("Wrong Syntax\nUsage java User <server-ip> <server-port>");
            }
            String server = args[0];
            Integer server_port = Integer.parseInt(args[1]);
            GraphInterface serverObj = (GraphInterface)Naming.lookup("rmi://" + server + ":" + server_port + "/graph_mst");
            BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Welcome to the server\n");
        
            while(true){
                System.out.print("Enter your query:-\n>>> ");
                String inputLine = buffRead.readLine();
                if(inputLine.length() == 0) continue;

                StringTokenizer tokens = new StringTokenizer(inputLine);
                if(!(tokens.countTokens() == 5 || tokens.countTokens() == 1 || tokens.countTokens() == 3 || tokens.countTokens() == 2)){
                    System.out.println("Wrong Command\n Commands available :-\n1. exit\n");
                    System.out.println("2. add_graph <graph_identifier> n\n3. add_edge <graph_identifire> u v w");
                    System.out.println("\n4. get_mst <graph_identifier>");
                    // System.exit(0);
                    continue;
                }
                String command = tokens.nextToken();
                if(tokens.countTokens() == 0){
                    if(command.equals("exit")){
                        break;
                    }
                    else{
                        System.out.println("Wrong Command");
                    }
                }
                else if(tokens.countTokens()==1){
                    if(!command.equals("get_mst")){
                        System.out.println("Wrong Command");
                        continue;
                    }
                    String graphName = tokens.nextToken();
                    Integer result = serverObj.get_mst(graphName);
                    System.out.print(result+"\n");
                }
                else if(tokens.countTokens()==2){
                    if(!command.equals("add_graph")){
                        System.out.println("Wrong Command");
                        continue;
                    }
                    String graphName = tokens.nextToken();
                    Integer n = 0;
                    try{
                        n = Integer.parseInt(tokens.nextToken());
                    }catch(Exception e){
                        System.out.println("last Argumnent must be integer\n"+e);
                    }
                    String result = serverObj.add_graph(graphName, n);
                    System.out.println(result);
                    
                }
                else if(tokens.countTokens()==4){
                    if(!command.equals("add_edge")){
                        System.out.println("Wrong Command\n");
                        continue;
                    }
                    String graphName = tokens.nextToken();
                    Integer u = 0, v= 0, w = 0;
                    try{
                        u = Integer.parseInt(tokens.nextToken());
                        v = Integer.parseInt(tokens.nextToken());
                        w = Integer.parseInt(tokens.nextToken());
                    }catch(Exception e){
                        System.out.println("Nodes and weights must be integer\n");
                    }
                    String result = serverObj.add_edge(graphName, u, v, w);
                    System.out.println(result);
                }

            }
        }catch(Exception e){
            System.out.println(e);
        }
    } 
}