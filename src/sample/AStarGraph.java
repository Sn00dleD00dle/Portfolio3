package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarGraph {
    ArrayList<Vertex> vertices;

    public AStarGraph() {
        vertices = new ArrayList<Vertex>();
    }

    public void addvertex(Vertex v) {
        vertices.add(v);
    }

    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2, dist);
        v2.addOutEdge(v1, dist);
    }

    public boolean A_Star(Vertex start, Vertex destination, String heuristics) {
        if (start == null || destination == null)
            return false;
        PriorityQueue<Vertex> Openlist = new PriorityQueue<Vertex>();
        ArrayList<Vertex> Closedlist = new ArrayList<Vertex>();
        Openlist.offer(start);
        Vertex Current;
        ArrayList<Vertex> CurrentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        if (heuristics.equals("Manhattan")){
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).seth(Manhattan(vertices.get(i), destination));
                //System.out.println(vertices.get(i).getid() + " h: " + vertices.get(i).geth());
            }

        }
        if (heuristics.equals("Euclidean")){
            for (int i = 0; i < vertices.size(); i++) {
                vertices.get(i).seth(Euclidean(vertices.get(i), destination));
                //System.out.println(vertices.get(i).getid() + " h: " + vertices.get(i).geth());
            }
        }
        Openlist.remove(start);
        start.setg(0.0);
        start.calculatef();
        Openlist.offer(start);
        //Start algorithm
        //System.out.println("Start Algorithm");
        while (!Openlist.isEmpty()) {
            Current = Openlist.remove();
            if (Current == destination) {
                return true;
            }
            Closedlist.add(Current);
            CurrentNeighbors = Current.getNeighbours();
            for (int v = 0; v < CurrentNeighbors.size(); v++) {
                Neighbor = CurrentNeighbors.get(v);
                double tempgofv = Current.getg() + Current.getNeighbourDistance().get(v);
                if (tempgofv < Neighbor.getg()) {
                    Neighbor.setPrev(Current);
                    Neighbor.setg(tempgofv);
                    Neighbor.calculatef();
                    if (!Openlist.contains(Neighbor) && !Closedlist.contains(Neighbor)) {
                        Openlist.offer(Neighbor);
                    } else if (Openlist.contains(Neighbor)){
                        Openlist.remove(Neighbor);
                        Openlist.offer(Neighbor);
                    }
                }
            }
        }
        return false;
    }

    public Double Manhattan(Vertex from,Vertex goal){
        double distance = Math.abs(goal.getx() -from.getx()) + Math.abs(goal.gety() - from.gety());
        return distance;
    }

    public Double Euclidean( Vertex from,Vertex to){
        double squaredX = (to.getx() - from.getx());
        double squaredY = (to.gety() - from.gety());
        double distance = Math.sqrt((squaredX * squaredX) + (squaredY * squaredY));
        return distance;
    }
}

class Vertex implements Comparable<Vertex>{
    private String id;
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>();
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>();
    private Double f;
    private Double g;
    private Double h;
    private Integer x;
    private Integer y;
    private Vertex prev =null;
    public Vertex(String id, int x_cor,int y_cor){
        this.id=id;
        this.x=x_cor;
        this.y = y_cor;
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        h=0.0;
    }

    public void addOutEdge(Vertex toV, Double dist){
        Neighbours.add(toV);
        NeighbourDistance.add(dist);
    }

    public ArrayList<Vertex> getNeighbours(){
        return Neighbours;
    }
    public ArrayList<Double> getNeighbourDistance(){
        return NeighbourDistance;
    }
    public String getid(){ return id;};
    public Integer getx(){ return x; }
    public Integer gety(){return y; }
    public Double getf() { return f; }
    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }
    public Double geth(){ return h; }
    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}

    public void printVertex(){
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex o) {
        if (this.getf() > o.getf())
            return 1;
        if (this.getf() < o.getf())
            return -1;
        return 0;
    }
}

