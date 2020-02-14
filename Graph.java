import java.io.*;				// DataStream Input/Output & File input and output
import java.util.*; 			// Vectos and Map
import java.net.*;				// Java socket
import java.awt.* ;				// Threading
import java.nio.file.*; 		// Path like os.path in python 
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*; 

public class Graph extends UnicastRemoteObject{
    Map<String,Integer> Graphs = new HashMap<String, Integer>(); 
    public String add_graph(String graphName, Integer n){
        if(Graph.containsKey(graphName)){
            return "A graph already with this name exist";
        }
        Graph.put(graphName, n);
        return "Graph Added Succesfully\n";
    }
    public String add_edge(String graphName, Integer u, Integer v, Integer w){
        if(!Graph.containsKey(graphName)){
            return "Sorry no such graph exist";
        }
        
    }
} 