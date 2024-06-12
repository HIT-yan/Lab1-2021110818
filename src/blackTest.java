import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class blackTest {
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
    public void testQueryBridgeWords_BothWordsInGraph_WithBridgeWords() {
        String result = lab.queryBridgeWords("over", "lazy");
        assertEquals("The bridge words from over to lazy are: the.", result);
    }

    @Test
    public void testQueryBridgeWords_BothWordsInGraph_WithTwoBridgeWords() {
        String result = lab.queryBridgeWords("jumps", "the");
        assertEquals("The bridge words from jumps to the are: over, again.", result);
    }

    @Test
    public void testQueryBridgeWords_BothWordsInGraph_NoBridgeWords() {
        String result = lab.queryBridgeWords("over", "very");
        assertEquals("No bridge words from over to very!", result);
    }

    @Test
    public void testQueryBridgeWords_OneWordNotInGraph() {
        String result = lab.queryBridgeWords("hello", "again");
        assertEquals("No hello or again in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_BothWordsNotInGraph() {
        String result = lab.queryBridgeWords("python", "java");
        assertEquals("No python or java in the graph!", result);
    }

    @Test
    public void testQueryBridgeWords_Word1EqualsWord2() {
        String result = lab.queryBridgeWords("smart", "smart");
        assertEquals("No bridge words from smart to smart!", result);
    }

    @Test
    public void testQueryBridgeWords_FirstWordInGraph() {
        String result = lab.queryBridgeWords("the", "clever");
        assertEquals("No bridge words from the to clever!", result);
    }

    @Test
    public void testQueryBridgeWords_LastWordInGraph() {
        String result = lab.queryBridgeWords("jumps", "hi");
        assertEquals("The bridge words from jumps to hi are: again.", result);
    }
}
