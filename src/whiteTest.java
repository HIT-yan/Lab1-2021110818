import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class whiteTest {
    private lab1 lab;

    @Before
    public void setUp() {
        lab = new lab1();
        // 初始化图结构
        String textContent = lab.readFile("test.txt");
        String[] words = lab.preprocessText(textContent);
        lab.graph = lab.buildGraph(words);
    }

    @Test
    public void testQueryBridgeWords_Word1NotInGraph() {
        String result = lab.queryBridgeWords("hello", "cat");
        assertEquals("No hello or cat in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_Word2NotInGraph() {
        String result = lab.queryBridgeWords("jumps", "java");
        assertEquals("No jumps or java in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_Word1InGraph_AdjListEmpty() {
        String result = lab.queryBridgeWords("hi", "very");
        assertEquals("No bridge words from hi to very!", result);
    }

    @Test
    public void testQueryBridgeWords_Word1InGraph_AdjListNotEmpty_Word3AdjListEmpty() {
        lab.graph.get("hi").put("next", 1);
        lab.graph.put("next", new HashMap<>());
        String result = lab.queryBridgeWords("hi", "lazy");
        assertEquals("No bridge words from hi to lazy!", result);
    }

    @Test
    public void testQueryBridgeWords_Word1InGraph_AdjListNotEmpty_Word3AdjListNotEmpty_NotContainingWord2() {
        String result = lab.queryBridgeWords("very", "over");
        assertEquals("No bridge words from very to over!", result);
    }

    @Test
    public void testQueryBridgeWords_BothWordsInGraph_WithOneBridgeWord() {
        String result = lab.queryBridgeWords("a", "cat");
        assertEquals("The bridge words from a to cat are: smart.", result);
    }

    @Test
    public void testQueryBridgeWords_BothWordsInGraph_WithMultipleBridgeWords() {
        String result = lab.queryBridgeWords("jumps", "hi");
        assertEquals("The bridge words from jumps to hi are: again.", result);
    }
}
