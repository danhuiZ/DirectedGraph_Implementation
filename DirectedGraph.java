import java.util.*;

/**
 * The class DirectedGraph builds a simple directed graph with nodes and implements breadth first traversal.
 *
 * @author (DanhuiZ)
 * @version (Nov 24, 2017)
 */
public class DirectedGraph<K extends Comparable<K>>
{
    // instance variables - replace the example below with your own
    private List<DirectedGraphNode<K>> nodes;

    /**
     * Constructor for objects of class DirectedGraph
     */
    public DirectedGraph()
    {
        nodes = new ArrayList<DirectedGraphNode<K>>();
    }

    public boolean AddNode(K k) {
        boolean added = false;
        Iterator<DirectedGraphNode<K>> it = nodes.iterator();
        for (DirectedGraphNode<K> node: nodes) {
            if (node.getKey().compareTo(k) == 0) return false;
        }
        added = nodes.add(new DirectedGraphNode(k));
        return added;
    }
    
    public boolean AddEdge(K k1, K k2, int w) {
        Iterator<DirectedGraphNode<K>> it = nodes.iterator();
        boolean added = false;
        boolean hask1 = false;
        int k1Index = -1;
        int k2Index = -1;
        int temp = -1;
        boolean hask2 = false;
        for (DirectedGraphNode<K> node: nodes) {
            temp++;
            // System.out.println(temp + " " + node);
            if (node.getKey().compareTo(k1) == 0) {
                // if already has edge, update weight
                hask1 = true;
                k1Index = temp;
                added = node.updateWeightIfHasEdge(k2, w); 
            }
            if (node.getKey().compareTo(k2) == 0) {
                hask2 = true;
                k2Index = temp;
            }
        }
        // if both nodes exist but edge does not currently exist
        if (!added && hask1 && hask2) { 
            return nodes.get(k1Index).addEdge(nodes.get(k2Index), w);
        } 
        return added;
    }
    
    // returns an Arraylist containing all the neighbors k can reach in one hop
    public ArrayList<K> getNeighbors(K k) {
        ArrayList<K> result = new ArrayList<K>();
        Iterator<DirectedGraphNode<K>> it = nodes.iterator();
        for (DirectedGraphNode<K> node: nodes) {
            if (node.getKey().compareTo(k) == 0) {
                LinkedList<DirectedGraphEdge<K>> edges = node.getOutgoingEdges();
                ListIterator<DirectedGraphEdge<K>> iter = edges.listIterator(0);
                for (DirectedGraphEdge<K> edge: edges) {
                    result.add(edge.getEndNode().getKey());
                }
            }
        }
        return result;
    }
    
    // Given a key, return closest neighbor's key or null if nonexistent
    public K closestNodeKey(K k1) {
        Iterator<DirectedGraphNode<K>> it = nodes.iterator();
        DirectedGraphNode<K> result;
        for (DirectedGraphNode<K> node: nodes) {
            if (node.getKey().compareTo(k1) == 0) {
                result = node.getClosestNode();
                if(result != null) return result.getKey();
            }
        }
        return null;
    }
    
    // Prints out the closest neighbor (smallest edge weight) each node in the graph 
    // can reach in one hop, using breadth first order 
    public void breadthFirstClosest(K k1) {
        // k1 is the starting node key
        Set<K> visited = new HashSet<K>(nodes.size());
        LinkedList<K> queue = new LinkedList<K>();
        // mark the starting node with key k1 as visited and enqueue
        visited.add(k1);
        queue.add(k1);
        
        while (queue.size() != 0) {
            // dequeue a node key and print closest neighbor's key
            k1 = queue.poll();
            System.out.println(k1+" "+closestNodeKey(k1));
            // get all adjacent nodes of the dequeued node
            // if not visited, mark as visited and enqueue
            Iterator<K> it = getNeighbors(k1).iterator();
            while (it.hasNext()) {
                K next = it.next();
                if(!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
    }
        
    // Store the generic key, plus a list of outgoing edges
    // has a method which returns the closest neighbor it can reach; if it can't reach any neighbor, return null
    public class DirectedGraphNode<K extends Comparable<K>> 
    {
        private K key;
        private LinkedList<DirectedGraphEdge<K>> outgoingEdges;
        
        public DirectedGraphNode(K k) {
            this.key = k;
            this.outgoingEdges = new LinkedList<DirectedGraphEdge<K>>();
        }
        
        public K getKey() {
            return key;
        }

        public LinkedList<DirectedGraphEdge<K>> getOutgoingEdges() {
            return outgoingEdges;
        }        
        
        public boolean addEdge(DirectedGraphNode<K> endNode, int weight) {
            DirectedGraphEdge outEdge = new DirectedGraphEdge(this, endNode, weight);
            return outgoingEdges.add(outEdge);            
        }
        
        // returns true if the node has outgoing edge to a node with key k
        public boolean updateWeightIfHasEdge(K k, int w) {
            ListIterator<DirectedGraphEdge<K>> it = outgoingEdges.listIterator(0);
            for (DirectedGraphEdge<K> edge: outgoingEdges) {
                if (edge.getEndNode().getKey().compareTo(k) == 0) {
                    edge.setWeight(w);
                    return true;
                }
            }    
            return false;  
        }        
        
        // Get closest neighbor
        public DirectedGraphNode<K> getClosestNode() {
            int smallestWeight = Integer.MAX_VALUE;
            DirectedGraphNode<K> result = null;
            ListIterator<DirectedGraphEdge<K>> it = outgoingEdges.listIterator(0);
            for (DirectedGraphEdge<K> edge: outgoingEdges) {
                if (edge.getWeight() < smallestWeight) {
                    smallestWeight = edge.getWeight();
                    result = edge.getEndNode();
                }
            }            
            return result;
        }
        
        public String toString() {
            return key.toString();
        }
        
    }
    
    // Store the starting node, the end node and the weight of the edge (int)
    public class DirectedGraphEdge<K extends Comparable<K>> 
    {
        private DirectedGraphNode<K> startNode;
        private DirectedGraphNode<K> endNode;        
        private int weight;
        
        public DirectedGraphEdge(DirectedGraphNode<K> startNode, DirectedGraphNode<K> endNode, int w) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = w;
        }
        
        public DirectedGraphNode<K> getStartNode() {
            return startNode;
        }
        
        public DirectedGraphNode<K> getEndNode() {
            return endNode;
        }      
        
        public int getWeight() {
            return weight;
        }
        
        public void setWeight(int w) {
            this.weight = w;
        }
        
        public String toString() {
            return startNode + " " + endNode + " " + weight;
        }
    }    
}
