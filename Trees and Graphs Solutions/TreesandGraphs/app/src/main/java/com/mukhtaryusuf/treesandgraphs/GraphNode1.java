package com.mukhtaryusuf.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mukhtaryusuf on 4/4/18.
 */

public class GraphNode1 {
    public char label;
    public boolean visited;
    public int incoming;
    public ArrayList<GraphNode1> children;
    public HashMap<Character, GraphNode1> map;

    public GraphNode1(char c){
        label = c;
        children = new ArrayList<>();
        map = new HashMap<>();
    }

    public void addChild(GraphNode1 child){
        if(child != null){
            children.add(child);
            map.put(child.label, child);
        }
    }
}
