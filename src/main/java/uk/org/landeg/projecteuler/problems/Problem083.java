package uk.org.landeg.projecteuler.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.org.landeg.projecteuler.FileLoader;
import uk.org.landeg.projecteuler.ProblemDescription;

@Order(82)
@Component
public class Problem083 implements ProblemDescription<Integer> {
  private static final Logger LOG = LoggerFactory.getLogger(Problem083.class);
  private int[][] grid = {
      {131,673,234,103,18},
      {201,96,342,965,150},
      {630,803,746,422,111},
      {537,699,497,121,956},
      {805,732,524,37,331}
  };
  private Node[][] nodes;
  private List<Node> unvisitedNodes = new ArrayList<>();
  private List<Node> allNodes = new ArrayList<>();
  
  @Override
  public String getDescribtion() {
    return "The minimal path sum in the 5 by 5 matrix below, by starting in any cell in the left column and finishing in any cell in the right column, and only moving up, down, and right, is indicated in red and bold; the sum is equal to 994.";
  }

  @Override
  public String getTask() {
    return "Find the minimal path sum, in matrix.txt (right click and \"Save Link/Target As...\"), a 31K text file containing a 80 by 80 matrix, from the left column to the right column.\n";
  }

  @Override
  public Integer solve() {
    grid = Problem081.loadGrid(FileLoader.readLines("p083_matrix.txt"));
    nodes = new Node[grid.length][grid.length];
      initNodes();
      showGrid(nodes);
      Node currentNode = nodes[0][0];
      currentNode.cost = currentNode.initialCost;
      unvisitedNodes.remove(currentNode);
      do {
        LOG.trace("current node : {} ", currentNode);
        currentNode.visited = true;
        updateNeighbourCosts(currentNode, nodes);
        if (LOG.isTraceEnabled()) {
          showGridDebug(nodes);
        }
        unvisitedNodes.sort(NodeCostComparator.INSTANCE);
        currentNode = unvisitedNodes.remove(0);
        LOG.trace("Moving to node {} ", currentNode);
      } while (!unvisitedNodes.isEmpty());
      AtomicInteger currentResult = new AtomicInteger();
      Arrays.stream(nodes[nodes.length - 1]).min(NodeCostComparator.INSTANCE).ifPresent(x ->  currentResult.set(x.cost));
    showGrid(nodes);
    return nodes[nodes.length - 1][nodes.length - 1].cost;
  }
  
  
  private void showGrid (final Node[][] nodes) {
    for (int x = 0 ; x < nodes.length ; x++) {
      final StringBuilder builder = new StringBuilder ();
      for (int y = 0 ; y < nodes[x].length ; y++) {
        final Node node = nodes[x][y];
        if (node.cost < Integer.MAX_VALUE) {
          builder.append(node.cost).append(",");
        } else {
          builder.append("*").append(",");
        }
      }
      LOG.debug("{}", builder.toString());
    }
    
  }
  
  private void showGridDebug(Node[][] nodes2) {
    if (LOG.isDebugEnabled()) {
      showGrid(nodes2);
    }
  }

  private void initNodes() {
    if (allNodes.isEmpty()) {
      for (int x = 0 ; x < grid.length ; x++) {
        for (int y = 0 ; y < grid.length ; y++) {
          nodes[x][y] = new Node (x,y,grid[y][x]);
          allNodes.add(nodes[x][y]);
        }
      }
    }
    else {
      allNodes.stream().forEach(Node::reset);
    }
    unvisitedNodes.clear();
    unvisitedNodes.addAll(allNodes);
  }

  private void updateNeighbourCosts (final Node node, final Node[][] allNodes) {
    for (int[] t : getAllowedTransforms(node.x)) {
      int x = node.x + t[0];
      int y = node.y + t[1];
      if (x >= 0 && x < grid.length && y >= 0 && y < grid.length) {
        Node neighbour = allNodes[x][y];
        int neighbourInitialCost = neighbour.initialCost;
        int tentativeCost = node.cost + neighbourInitialCost;
        if (tentativeCost < neighbour.cost) {
          neighbour.cost = tentativeCost;
        }
      }
    }
  }

  final int[][] transforms = new int[][] {
      {0,1},
      {1,0},
      {0,-1},
      {-1,0},
  };
  
  final int[][] transformsRestricted = new int[][] {{1,0}};

  protected int[][] getAllowedTransforms (int column) {
    return transforms;
  }

  private class Node {
    final int x;
    final int y;
    final int initialCost;
    Integer cost;
    boolean visited = false;
    
    public Node(final int x, final int y, final int initialCost) {
      this.initialCost = initialCost;
      this.cost = Integer.MAX_VALUE;
      this.x = x;
      this.y = y;
    }

    public void reset () {
      visited = false;
      cost = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Node [x=").append(x).append(", y=").append(y).append(", initialCost=")
          .append(initialCost).append(", cost=").append(cost).append(", visited=").append(visited)
          .append("]");
      return builder.toString();
    }
  }
  
  private static class NodeCostComparator implements Comparator<Node> {
    public static final NodeCostComparator INSTANCE = new NodeCostComparator();
    
    @Override
    public int compare(Node o1, Node o2) {
      return (o1.cost == null ? 0 : o1.cost) - (o2.cost == null ? 0 : o2.cost);
    }
  }
}
