import java.io.*;				// DataStream Input/Output & File input and output
import java.util.*; 			// Vectos and Map
import java.net.*;				// Java socket
import java.awt.* ;				// Threading
import java.nio.file.*; 		// Path like os.path in python 
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*; 

public class Graph extends UnicastRemoteObject implements GraphInterface{
    Graph() throws RemoteException { 
        super(); 
    } 
    Map<String,Integer> Graphs = new HashMap<String, Integer>(); 
    Map<String, ArrayList<Integer[]>> edges = new HashMap< String, ArrayList<Integer[]> >();
    private int[] par = new int[100005];
    @Override
    public String add_graph(String graphName, Integer n) throws RemoteException{
        if(Graphs.containsKey(graphName)){
            return "A graph already with this name exist";
        }
        Graphs.put(graphName, n);
        edges.put(graphName, new ArrayList<Integer[]>());
        return "Graph Added Succesfully\n";
    }
    @Override
    public String add_edge(String graphName, Integer u, Integer v, Integer w) throws RemoteException{
        if(!Graphs.containsKey(graphName)){
            return "Sorry no such graph exist";
        }
        Integer sz = Graphs.get(graphName);
        if( u > sz || u < 1 || v < 1 || v > sz){
            return "nodes invalid";
        }
        ArrayList<Integer[]> ed = edges.get(graphName);
        if(ed==null){
            ed = new ArrayList<Integer[]>();
        }
        ed.add(new Integer[]{u, v, w});
        System.out.print("adding edge to graph "+graphName+"\n");
        return "Node added to the graph";
    }
    @Override
    public int get_mst(String graphName){
        if(!Graphs.containsKey(graphName)){
            return -1;
        }
        ArrayList<Integer[]> ed = edges.get(graphName);
        int sz = Graphs.get(graphName);
        int ans = 0;
        for(int i = 0 ; i <= sz ; i++){
            par[i] = i;
        }
        if(ed==null || ed.size()==0){
            return -1;
        }
        Collections.sort(ed, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b){
                return a[2].compareTo(b[2]);
            }
        });
        for (int i = 0 ; i < ed.size() && sz>1; i++){
            Integer[] e = ed.get(i);
            if(unions(e[0], e[1]) == 1){
                ans += e[2];
                sz--;
            }
        }
        if(sz!=1){
            return -1;
        } 
        return ans;
    }
    private int find(int a){
        while(par[a]!=a) a = par[a];
        return a;
    }
    private int unions(int a, int b){
        int p1 = find(a);
        int p2 = find(b);
        if(p1 == p2){
            return 0;
        } 
        if(p1<p2){
            par[p2] = p1;
            return 1;
        }
        par[p1] = p2;
        return 1;
    }

}