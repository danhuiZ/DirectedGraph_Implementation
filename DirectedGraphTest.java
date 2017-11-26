import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DirectedGraphTest which unit tests the DirectedGraph class.
 *
 * @author  (Danhui Zhang)
 * @version (Nov 25, 2017)
 */
public class DirectedGraphTest
{
    /**
     * Default constructor for test class DirectedGraphTest
     */
    public DirectedGraphTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void TestAddNode()
    {
        DirectedGraph<String> diGraph = new DirectedGraph<String>();
        assertEquals(true, diGraph.AddNode("A"));
        assertEquals(true, diGraph.AddNode("B"));
        assertEquals(true, diGraph.AddNode("Z"));
        // key should be unique, return false if duplicated
        assertEquals(false, diGraph.AddNode("Z"));        
    }
    
    @Test
    public void TestAddEdge()
    {
        DirectedGraph<String> diGraph = new DirectedGraph<String>();
        assertEquals(true, diGraph.AddNode("A"));
        assertEquals(true, diGraph.AddNode("B"));
        assertEquals(true, diGraph.AddNode("Z"));
        // both nodes exist, return true
        assertEquals(true, diGraph.AddEdge("A", "B", 3));
        // 1st node doesn't exist, return false
        assertEquals(false, diGraph.AddEdge("C", "B", 3));
        // 2nd node doesn't exist, return false
        assertEquals(false, diGraph.AddEdge("A", "D", 3));
        // neither node exists, return false
        assertEquals(false, diGraph.AddEdge("E", "D", 3));   
        // edge already exist, update weight & return true
        assertEquals(true, diGraph.AddEdge("A", "B", 5));        
    }

    @Test
    public void TestGetNeighbors()
    {
        DirectedGraph<String> diGraph = new DirectedGraph<String>();
        assertEquals(true, diGraph.AddNode("A"));
        assertEquals(true, diGraph.AddNode("B"));
        assertEquals(true, diGraph.AddNode("Z"));
        assertEquals(true, diGraph.AddEdge("A", "B", 3));
        assertEquals(true, diGraph.AddEdge("A", "Z", 6));
        String[] neightbors = {"B", "Z"};
        assertArrayEquals(neightbors, diGraph.getNeighbors("A").toArray());
    }    
    
    @Test
    public void TestClosestNodeKey()
    {
        DirectedGraph<String> diGraph = new DirectedGraph<String>();
        assertEquals(true, diGraph.AddNode("A"));
        assertEquals(true, diGraph.AddNode("B"));
        assertEquals(true, diGraph.AddNode("Z"));
        assertEquals(true, diGraph.AddEdge("A", "B", 3));
        assertEquals(true, diGraph.AddEdge("A", "Z", 6));
        assertEquals("B", diGraph.closestNodeKey("A"));
    }     
}

