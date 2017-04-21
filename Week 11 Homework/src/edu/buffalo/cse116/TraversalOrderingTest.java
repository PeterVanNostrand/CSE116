package edu.buffalo.cse116;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class TraversalOrderingTest {

  private OutputStream bytesPrinted;
  private BinaryTree<Character> bt;

  @SuppressWarnings("unchecked")
  @Before
  public void setUp() {
    bytesPrinted = new ByteArrayOutputStream();
    PrintStream sysOut = new PrintStream(bytesPrinted);
    System.setOut(sysOut);

    Entry<Character>[] nodes = new Entry[11];
    for (int i = 0; i < nodes.length; i++ ) {
      nodes[i] = new Entry<>();
      nodes[i].setElement((char) ('K' + i));
    }

    // Setup the left and right children in the tree
    nodes[5].setLeft(nodes[3]);
    nodes[5].setRight(nodes[4]);

    nodes[4].setLeft(nodes[10]);

    nodes[2].setLeft(nodes[0]);
    nodes[2].setRight(nodes[1]);

    nodes[6].setLeft(nodes[2]);
    nodes[6].setRight(nodes[5]);

    nodes[8].setRight(nodes[9]);

    nodes[7].setLeft(nodes[6]);
    nodes[7].setRight(nodes[8]);

    // Setup the parents in the tree
    nodes[6].setParent(nodes[7]);
    nodes[8].setParent(nodes[7]);
    nodes[9].setParent(nodes[8]);
    nodes[5].setParent(nodes[6]);
    nodes[2].setParent(nodes[6]);
    nodes[0].setParent(nodes[2]);
    nodes[1].setParent(nodes[2]);
    nodes[10].setParent(nodes[4]);
    nodes[3].setParent(nodes[5]);
    nodes[4].setParent(nodes[5]);

    bt = new BinaryTree<>();
    bt.root = nodes[7];
    bt.size = 11;
  }

  @Test
  public void testPreOrder() {
    bt.preOrderPrint();
    String str = bytesPrinted.toString();
    String actual = "RQMKLPNOUST";
    //assertEquals("Pre-ordering incorrect", actual, str);
    assertEquals("Pre-ordering incorrect", actual, str);
  }

  @Test
  public void testPostOrder() {
    bt.postOrderPrint();
    String str = bytesPrinted.toString();
    String actual = "KLMNUOPQTSR";
    //assertTrue("Post-ordering incorrect", str.equals(actual));
    assertEquals("Post-ordering incorrect", actual, str);
  }

  @Test
  public void testInOrder() {
    bt.inOrderPrint();
    String str = bytesPrinted.toString();
    String actual = "KMLQNPUORST";
    assertEquals("In-ordering incorrect", actual, str);
  }
}
