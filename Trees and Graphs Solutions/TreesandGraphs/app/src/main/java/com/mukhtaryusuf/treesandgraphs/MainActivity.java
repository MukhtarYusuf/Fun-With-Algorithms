package com.mukhtaryusuf.treesandgraphs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    1. Write a method to check if a route exists between two nodes
     */
    public boolean existsRoute(GraphNode a, GraphNode b){
        if( a == null || b == null)
            return false;

        LinkedList<GraphNode> q = new LinkedList<>();
        q.addLast(a);
        while(!q.isEmpty()){
            GraphNode cur = q.removeFirst();
            if(cur == b)
                return true;
            for(GraphNode graphNode : cur.children)
                q.addLast(graphNode);
        }
        return false;
    }
}
