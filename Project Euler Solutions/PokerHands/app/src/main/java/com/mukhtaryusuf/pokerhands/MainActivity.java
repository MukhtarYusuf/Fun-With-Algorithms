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
        calcPlayer1Wins();

        /*
        The code below lets you test for individual game sessions. Uncomment the code, and edit the values
        of p1Hand and p2Hand to see the results.
         */
        String[] p1Hand = {"4D","6S","9H","QH","QC"};
        String[] p2Hand = {"3D","6D","7H","QD","QS"};
        testOneGameSession(p1Hand,p2Hand);
    }

    public void calcPlayer1Wins(){
        hashMap = new HashMap<>();
        cardValues = new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14};
        cardCharacters = new char[]{'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
        royalCharacters = new char[]{'J','Q','K','A'};
        for(int i = 0; i < cardCharacters.length; i++){
            hashMap.put(cardCharacters[i], cardValues[i]);
        }
        String stringFromFile = readAllFromFile();
        String[] totalGames = stringFromFile.split("\n");
        int player1WinCount = 0;
        int player2WinCount = 0;
        for(String s : totalGames){//Iterate through each game
            String[] p1Andp2Hand = s.split(" ");
            String[] player1Hand = new String[HAND_SIZE];
            String[] player2Hand = new String[HAND_SIZE];

            for(int i = 0; i < HAND_SIZE; i++){//Get the hands for player 1 and player 2
                player1Hand[i] = p1Andp2Hand[i];
                player2Hand[i] = p1Andp2Hand[i + HAND_SIZE];
            }
            int player1Score = scorePlayerWithHand(player1Hand);
            int player2Score = scorePlayerWithHand(player2Hand);
            if(player1Score > player2Score)
                player1WinCount++;
            else if(player2Score > player1Score)
                player2WinCount++;
            else {//Scores are the same and need the tie breaker
                int level = 1;
                while(player1Score == player2Score) {//Continue to perform tie breaker until there's a winner
                    player1Score = scoreForHighCard(player1Hand, level);
                    player2Score = scoreForHighCard(player2Hand, level);
                    level++;
                }
                if(player1Score > player2Score)
                    player1WinCount++;
                else
                    player2WinCount++;
            }
        }
        System.out.println("----------Player Win Counts----------");
        System.out.println("Player 1 Win Count: " + player1WinCount);
        System.out.println("Player 2 Win Count: " + player2WinCount);
    }

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

    public int scorePlayerWithHand(String[] hand){
        int totalScore = 0;
        int tempScore;
        if((tempScore = scoreForRoyalFlush(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForStraightFlush(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForFourOfAKind(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForFullHouse(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForFlush(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForStraight(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForThreeOfAKind(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForTwoPairs(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForOnePair(hand)) > 0)
            totalScore = tempScore;
        else if((tempScore = scoreForHighCard(hand,1)) > 0)
            totalScore = tempScore;

        return totalScore;
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


    public boolean isSameSuit(String[] playerHand){
        char firstSuit = playerHand[0].charAt(1);
        for(int i = 1; i < playerHand.length; i++){
            char currentSuit = playerHand[i].charAt(1);
            if(firstSuit != currentSuit)
                return false;
        }
        return true;
    }
    public boolean isRoyalRanks(String[] playerHand){
        HashSet<Character> playerHandHS = new HashSet<>();
        for(int i = 0; i < playerHand.length; i++){
            playerHandHS.add(playerHand[i].charAt(0));
        }
        for(int i = 0; i < royalCharacters.length; i++){
            if(!playerHandHS.contains(royalCharacters[i]));
                return false;
        }
        return true;
    }
    public int calcConsecutiveScore(String[] playerHand){
        int[] sortedCardHandValues = getSortedCardValues(playerHand);
        for(int i = 0; i < sortedCardHandValues.length-1; i++){
            int difference = sortedCardHandValues[i+1] - sortedCardHandValues[i];
            if(difference != 1)
                return 0;
        }
        return sortedCardHandValues[HAND_SIZE-1];
    }
    //Where n can be 4, 3, or 2 (for pair)
    public int calcNOfAKindScore(String[] playerHand, int n, int nCount){
        int uniqueCount = 0;
        HashMap<Character, Integer> tracker = getTracker(playerHand);
        Iterator iterator = tracker.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Character,Integer> pair = (Map.Entry)iterator.next();
            if(pair.getValue() == n) {
                uniqueCount++;
                if(uniqueCount == nCount)
                    return hashMap.get(pair.getKey());
            }
        }
        return 0;
    }

    //Create and return a HashMap that contains character card values and how many times they occur
    public HashMap<Character, Integer> getTracker(String[] pHand){
        HashMap<Character, Integer> hmTracker = new HashMap<>();
        for(int i = 0; i < pHand.length; i++){
            char charCardValue = pHand[i].charAt(0);
            if(!hmTracker.containsKey(charCardValue))
                hmTracker.put(charCardValue,1);
            else{
                int charCount = hmTracker.get(charCardValue);
                charCount++;
                hmTracker.put(charCardValue,charCount);
            }
        }
        return hmTracker;
    }

    public int[] getSortedCardValues(String[] pHand){
        int[] cardHandValues = new int[HAND_SIZE];
        for(int i = 0; i < cardHandValues.length; i++){
            char charCardValue = pHand[i].charAt(0);
            int cardValue = hashMap.get(charCardValue);
            cardHandValues[i] = cardValue;
        }
        Arrays.sort(cardHandValues);
        return cardHandValues;
    }
        System.out.println("----------Testing for One Game Session----------");
        System.out.println("player 1 score: " + p1Score);
        System.out.println("player 2 score: " + p2Score);
        System.out.println((p1Score > p2Score)? playerOneWins : playerTwoWins);
    }
}
