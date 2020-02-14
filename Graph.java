import java.io.*;				// DataStream Input/Output & File input and output
import java.util.*; 			// Vectos and Map
import java.net.*;				// Java socket
import java.awt.* ;				// Threading
import java.nio.file.*; 		// Path like os.path in python 
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*; 

public class Graph extends UnicastRemoteObject {
    Graph() throws RemoteException { 
        super(); 
    } 
    Map<String,Integer> Graphs = new HashMap<String, Integer>(); 
    Map<String, HashMap<Integer, ArrayList<Integer[]> > > adj = new HashMap<String, HashMap<Integer, ArrayList<Integer[]> > >(); 
    // Map<String, Map<Integer, ArrayList<Integer> > > adj = new HashMap<String, Map<Integer, ArrayList<Integer> > >(); 
    public String add_graph(String graphName, Integer n){
        if(Graphs.containsKey(graphName)){
            return "A graph already with this name exist";
        }
        Graphs.put(graphName, n);
        adj.put(graphName, new HashMap<Integer, ArrayList<Integer[]> >());
        // adj.put(graphName, new HashMap<Integer, ArrayList<Integer> >());
        return "Graph Added Succesfully\n";
    }
    public String add_edge(String graphName, Integer u, Integer v, Integer w){
        if(!Graphs.containsKey(graphName)){
            return "Sorry no such graph exist";
        }
        Integer sz = Graphs.get(graphName);
        if( u > sz || u < 1 || v < 1 || v > sz){
            return "nodes invalid";
        }
        ArrayList<Integer[]> list1 = adj.get(graphName).get(u);
        ArrayList<Integer[]> list2 = adj.get(graphName).get(v);
        // HashMap<Integer, ArrayList<Integer> > adj = adj.get(graphName);
        list1.add(new Integer[]{v ,w}); 
        list2.add(new Integer[]{u ,w}); 
        return "Node added to the graph";
    }

} 
class pair{
    public Integer first;
    public Integer second;
}