import java.util.*;
import java.io.*;

/**
 * The GraphExperiment class reads input.txt file to construct a directed graph 
 * and outputs to output.txt
 *
 * @author (Danhui Zhang)
 * @version (Nov 25, 2017)
 */
public class GraphExperiment
{
    public static void main (String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        Scanner input;
        String[] nodes;
        String[] edge;
        PrintStream output;
        try {
            input = new Scanner(new FileReader(inputFile));
            output = new PrintStream(new File(outputFile));
            
            DirectedGraph<String> dGraph = new DirectedGraph<String>();
            // Add nodes
            nodes = input.nextLine().split(" ");            
            for (String key: nodes) {
                dGraph.AddNode(key);
            }
            // Add edges
            while(input.hasNextLine()){
                edge = input.nextLine().split(" ");
                int weight = Integer.parseInt(edge[2]);
                dGraph.AddEdge(edge[0], edge[1], weight);           
            }
            // write to output file
            dGraph.breadthFirstClosest(nodes[0]);
        } catch (IOException ex) {
            System.out.println("Error in GraphExperiment: " + ex);
            ex.printStackTrace();
        }
    }
}
