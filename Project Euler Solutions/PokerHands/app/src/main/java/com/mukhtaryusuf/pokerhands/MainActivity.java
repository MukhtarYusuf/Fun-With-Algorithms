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
    //2. Scoring for Straight Flush
    public int scoreForStraightFlush(String[] playerHand){
        int straightFlushScore = 800;
        int weightedScore = 0;
        boolean isStraightFlush = false;
        isStraightFlush = isSameSuit(playerHand) && ((weightedScore = calcConsecutiveScore(playerHand)) > 0);
        straightFlushScore += weightedScore;
        return isStraightFlush ? straightFlushScore : 0;
    }
    //3. Scoring for Four of a Kind
    public int scoreForFourOfAKind(String[] playerHand){
        int fourOfAKindScore = 700;
        int weightedScore = calcNOfAKindScore(playerHand,4,1);
        fourOfAKindScore += weightedScore;
        return (weightedScore > 0) ? fourOfAKindScore : 0;
    }
    //4. Scoring for Full House
    public int scoreForFullHouse(String[] playerHand){
        int fullHouseScore = 600;
        int weightedScore1 = calcNOfAKindScore(playerHand,3,1);
        int weightedScore2 = calcNOfAKindScore(playerHand,2,1);
        fullHouseScore += weightedScore1;

        return (weightedScore1 > 0 && weightedScore2 > 0) ? fullHouseScore : 0;
    }
    //5. Scoring for Flush
    public int scoreForFlush(String[] playerHand){
        final int flushScore = 500;
        boolean isSameSuit = isSameSuit(playerHand);
        return isSameSuit ? flushScore : 0;
    }
    //6. Scoring for Straight
    public int scoreForStraight(String[] playerHand){
        int straightScore = 400;
        int weightedScore = calcConsecutiveScore(playerHand);
        straightScore += weightedScore;
        return (weightedScore > 0) ? straightScore : 0;
    }
    //7. Scoring for 3 of a Kind
    public int scoreForThreeOfAKind(String[] playerHand){
        int threeOfAKindScore = 300;
        int weightedScore = calcNOfAKindScore(playerHand,3,1);
        threeOfAKindScore += weightedScore;
        return (weightedScore > 0) ? threeOfAKindScore : 0;
    }
    //8. Scoring for 2 pairs
    public int scoreForTwoPairs(String[] playerHand){
        int twoPairScore = 200;
        int weightedScore = calcNOfAKindScore(playerHand,2,2);
        twoPairScore += weightedScore;
        return (weightedScore > 0) ? twoPairScore : 0;
    }
    //9. Scoring for 1 pair
    public int scoreForOnePair(String[] playerHand){
        int onePairScore = 100;
        int weightedScore = calcNOfAKindScore(playerHand,2,1);
        onePairScore += weightedScore;
        return (weightedScore > 0) ? onePairScore : 0;
    }
    //10. Scoring for High Card
    public int scoreForHighCard(String[] playerHand, int level){
        int highCardScore = 0;
        if(level >= 1 && level <=5) {
            int[] sortedCardHandValues = getSortedCardValues(playerHand);
            highCardScore = sortedCardHandValues[HAND_SIZE - level];
        }
        return highCardScore;
    }
    }
}
