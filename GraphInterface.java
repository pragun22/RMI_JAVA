import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*; 

public interface GraphInterface extends Remote { 
    // Declaring the method prototypes 
    String add_graph(String graph_name, Integer n) throws RemoteException;
    String add_edge(String graph_name,Integer u, Integer v, Integer w) throws RemoteException;
    Integer get_mst(String graph_name) throws RemoteException;
}