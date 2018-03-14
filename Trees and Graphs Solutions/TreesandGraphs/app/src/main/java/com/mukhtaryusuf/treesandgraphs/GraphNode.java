package com.mukhtaryusuf.treesandgraphs;

import java.util.ArrayList;

/**
 * Created by mukhtaryusuf on 3/13/18.
 */

public class GraphNode {
    String label;
    ArrayList<GraphNode> children;

    public GraphNode(String label){
        this.label = label;
    }
}
