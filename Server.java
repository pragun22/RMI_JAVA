// Imports
import java.io.*;				// DataStream Input/Output & File input and output
import java.util.*; 			// Vectos and Map
import java.net.*;				// Java socket
import java.awt.* ;				// Threading
import java.nio.file.*; 		// Path like os.path in python 
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*; 

public class Server{
    public static void main(String args[]){
        try{
            if(args.length==0){
            System.out.println("Specify Port number\n Usage javac Server [:PORT NUM:]");
            System.exit(0);
            }
            if(args.length>=2){
                System.out.println("Wrong Syntax\n Usage javac Server [:PORT NUM:]");
                System.exit(0);
            }
            Integer port = Integer.parseInt(args[0]);
            Graph obj = new Graph();
            LocateRegistry.createRegistry(port);
            Naming.rebind("rmi://0.0.0.0:"+ port.toString() + "/graph_mst", obj);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}