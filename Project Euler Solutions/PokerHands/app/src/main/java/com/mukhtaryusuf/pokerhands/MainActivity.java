package com.mukhtaryusuf.pokerhands;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    HashMap<Character,Integer> hashMap; //Store Card characters and their values e.g. {A:14}
    int[] cardValues;
    char[] cardCharacters;
    char[] royalCharacters;
    final int HAND_SIZE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    public String readAllFromFile(){
        StringBuilder result = new StringBuilder();
        Context context = getApplicationContext();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream fileInputStream = assetManager.open("p054_poker.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while((line = bufferedReader.readLine()) != null){
                result.append(line + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result.toString();
    }
    //1. Scoring for Royal Flush
    public int scoreForRoyalFlush(String[] playerHand){
        final int royalFlushScore = 900;
        boolean isRoyalFlush = false;
        isRoyalFlush = isSameSuit(playerHand) && isRoyalRanks(playerHand);
        return isRoyalFlush ? royalFlushScore : 0;
    }
    }
}
