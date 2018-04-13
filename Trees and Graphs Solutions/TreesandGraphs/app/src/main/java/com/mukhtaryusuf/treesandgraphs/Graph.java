package com.mukhtaryusuf.treesandgraphs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mukhtaryusuf on 4/4/18.
 */

/**
*
*/

public class Graph {
    private ArrayList<GraphNode1> nodes;
    private HashMap<Character, GraphNode1> map;

    public Graph(){
        nodes = new ArrayList<>();
        map = new HashMap<>();
    }

    public Graph(Character[] projects, Character[][] dependencies){
        nodes = new ArrayList<>();
        map = new HashMap<>();
        for(Character c : projects)
            addNode(c);
        for(Character[] characters : dependencies)
            addEdge(characters[0], characters[1]);
    }

    public void addNode(char c){
        GraphNode1 node = new GraphNode1(c);
        nodes.add(node);
        map.put(c, node);
    }

    public void addEdge(char first, char second){
        GraphNode1 firstNode = map.get(first);
        GraphNode1 secondNode = map.get(second);
        if(firstNode != null && secondNode != null){
            firstNode.addChild(secondNode);
        }
    }

    public ArrayList<GraphNode1> getNodes(){
        return nodes;
    }
}
