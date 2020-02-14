// Imports
import java.io.*;				// DataStream Input/Output & File input and output
import java.util.*;				// Vectos and Map
import java.net.*;				// Java socket 
import java.awt.*;				// Threads
import java.rmi.*; 

public class User{
    public static void main(String args[]){
        if(args.length != 2){
            System.println("Wrong Syntax\nUsage java User <server-ip> <server-port>")
        }
        String server = args[0];
        Integer server_port = Integer.parseInt(args[1]);
        Graph serverObj = (Graph)Naming.lookup("rmi://" + server_ip + ":" + server_port + "/graph");
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
        System.println("Welcome to the server\n");
        while(true){
            String inputLine = buffRead.readLine();
            if(inputLine.length == 0) continue;

            StringTokenizer tokens = new StringTokenizer(inputLine);
            if(!(tokens.countTokens() == 5 || tokens.countTokens() == 1 || tokens.countTokens() == 3 || tokens.countTokens() == 2)){
                System.println("Wrong Command\n Commands available :-\n1. exit\n");
                System.println("2. add_graph <graph_identifier> n\n3. add_edge <graph_identifire> u v w");
                System.println("\n4. get_mst <graph_identifier>");
                System.exit(0);
            }
            String command = tokens.nextToken();
            if(tokens.countTokens() == 1){
                if(command.equals("exit")){
                    break;
                }
                else{
                    System.println("Wrong Command");
                }
            }
            else if(tokens.countTokens()==2){
                if(!command.equals("get_mst")){
                    System.println("Wrong Command");
                }
                String graphName = tokens.nextToken();
                String result = serverObj.get_mst(graphName);
            }
            else if(tokens.countTokens()==3){
                if(!command.equals("add_graph")){
                    System.println("Wrong Command");
                }
                String graphName = tokens.nextToken();
                try{
                    Integer n = Integer.parseInt(tokens.nextToken());
                }catch(Exception e){
                    System.println("last Argumnent must be integer\n"+e);
                }
                String result = serverObj.add_graph(graphName, n);
                System.println(result);
                
            }
            else if(tokens.countTokens()==5){
                if(!command.equals("add_edge")){
                    System.println("Wrong Command");
                }
                String graphName = tokens.nextToken();
                try{
                    Integer u = Integer.parseInt(token.nextToken());
                    Integer v = Integer.parseInt(token.nextToken());
                    Integer w = Integer.parseInt(token.nextToken());
                }catch(Exception e){
                    System.println("Nodes and weights must be integer");
                }
                String result = serverObj.add_edge(graphName, u, v, w);
            }

        }
    } 
}