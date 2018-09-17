package com.mukhtaryusuf.recursionanddynamicprogramming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int result = tripleStep(3);
        System.out.println("Triple Step Result: " + result);

        result = tripleStep1(2);
        System.out.println("Triple Step 1 Result: " + result);

        boolean[][] grid = new boolean[][]{{true, true, true, true},
                                            {false, false, true, true},
                                            {true, false, true, true},
                                            {true, true, true, true}
                                        };

        ArrayList<Point> robotPathResult = robotPath(grid);
        System.out.println("----------Robot Path Result----------");
        printList(robotPathResult);

        robotPathResult = robotPath1(grid);
        System.out.println("----------Robot Path 1 Result----------");
        printList(robotPathResult);

        Stack<Integer> sourceStack = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();
        Stack<Integer> destStack = new Stack<>();

        sourceStack.push(5);
        sourceStack.push(4);
        sourceStack.push(3);
        sourceStack.push(2);
        sourceStack.push(1);

        System.out.println("----------Towers of Hanoi Result----------");
        System.out.println("Before Hanoi...");
        System.out.println(sourceStack);
        solveHanoi(sourceStack, tempStack, destStack);

        System.out.println("After Hanoi...");
        System.out.println(destStack);

        System.out.println("----------Place Queens Result----------");
        placeQueens();
    }

    public void printList(ArrayList<Point> pList){
        for(Point p : pList)
            System.out.println(p);
    }

    /*
    Problem 1: Triple Step
     */

    //Solution 1: Brute Force. Time:O(3^n), Space: O(n)
    public int tripleStep(int n){
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;

        int nWays = 0;
        nWays += tripleStep(n-1);
        nWays += tripleStep(n-2);
        nWays += tripleStep(n-3);

        return nWays;
    }

    //Solution 2: Dynamic Programming. Time:O(n), Space: O(n)
    public int tripleStep1(int n){
        int[] cache = new int[n+1];
        Arrays.fill(cache, -1);
        return tripleStep1(n, cache);
    }

    public int tripleStep1(int n, int[] cache){
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;
        if(cache[n] > -1)//Already Computed
            return cache[n];
        else{
            cache[n] = tripleStep1(n-1, cache) + tripleStep1(n-2, cache) + tripleStep1(n-3, cache);
            return cache[n];
        }
    }

    /*
    Problem 2: Robot Path
     */

    //Solution 1
    public ArrayList<Point> robotPath(boolean[][] grid){
        if(grid == null || grid.length == 0)
            return null;

        ArrayList<Point> result = new ArrayList<>();
        robotPath(grid, 0, 0, result);

        return result;
    }

    public boolean robotPath(boolean[][] grid, int row, int col, ArrayList<Point> path){
        if(row >= grid.length || col >= grid[0].length || !grid[row][col])
            return false;

        boolean isAtEnd = (row == grid.length-1 && col == grid[0].length-1);

        if(isAtEnd || robotPath(grid, row+1, col, path) || robotPath(grid, row, col+1, path)){
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }

        return false;
    }

    //Solution 2: Dynamic Programming
    public ArrayList<Point> robotPath1(boolean[][] grid){
        if(grid == null || grid.length == 0)
            return null;

        ArrayList<Point> result = new ArrayList<>();
        HashSet<Point> cache = new HashSet<>();
        robotPath1(grid, 0, 0, result, cache);
        return result;
    }

    public boolean robotPath1(boolean[][] grid, int row, int col, ArrayList<Point> points, HashSet<Point> cache){
        if(row >= grid.length || col >= grid[0].length || !grid[row][col])
            return false;

        Point p = new Point(row, col);
        if(cache.contains(p))
            return false;

        boolean isAtEnd = (row == grid.length-1 && col == grid[0].length-1);
        if(isAtEnd || robotPath1(grid, row+1, col, points, cache) || robotPath1(grid, row, col+1, points, cache)){
            points.add(p);
            return true;
        }

        cache.add(p);
        return false;
    }

    /*
    Problem 3: Magic Index
     */

    //Solution: Divide and Conquer. Time: O(logn), Space: O(logn)
    public int magicIndex(int[] array){
        if(array == null || array.length == 0)
            return -1;

        return recMagicIndex(array, 0, array.length-1);
    }

    public int recMagicIndex(int[] array, int lower, int upper){
        if(lower > upper)
            return -1;

        int mid = (lower + upper)/2;

        if(mid > array[mid])//Search Right Half
            return recMagicIndex(array, mid+1, upper);
        else if(mid < array[mid])//Search Left Half
            return recMagicIndex(array, lower, mid-1);
        else
            return mid;
    }

    /*
    Problem 3 Follow Up (Array contains duplicates)
     */

    public int magicIndex1(int[] array){
        if(array == null || array.length == 0)
            return -1;
        return recMagicIndex1(array, 0, array.length-1);
    }

    public int recMagicIndex1(int[] array, int lower, int upper){
        if(lower > upper)
            return -1;

        int midIndex = (lower + upper)/2;
        int midValue = array[midIndex];

        if(midIndex == midValue)
            return midIndex;

        int leftIndex = Math.min(midIndex-1, midValue);
        int leftResult = recMagicIndex1(array, lower, leftIndex);
        if(leftResult != -1)
            return leftResult;

        int rightIndex = Math.max(midIndex+1, midValue);
        int rightResult = recMagicIndex1(array, rightIndex, upper);

        return rightResult;
    }

    /*
    Problem 4: Subsets
     */

    //Solution 1 (Recursion). Time: (2^n), Space: O(n)
    public ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
        ArrayList<ArrayList<Integer>> allSubsets = null;
        if(index == set.size()){
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<Integer>());
        }else{
            allSubsets = getSubsets(set, index+1);

            ArrayList<ArrayList<Integer>> extraSubsets = new ArrayList<>();
            int current = set.get(index);

            for(ArrayList<Integer> subset : allSubsets){
                ArrayList<Integer> newSubset = new ArrayList<>();
                newSubset.addAll(subset);
                newSubset.add(current);
                extraSubsets.add(newSubset);
            }
            allSubsets.addAll(extraSubsets);
        }

        return allSubsets;
    }

    /*
    Problem 5: Multiply two numbers without '*' and '/' Operator
     */

    //Solution 1. Time: O(logs), Space: O(s), where s is the size of the smaller integer
    public int multiply(int a, int b){
        int smaller = a < b ? a : b;
        int bigger = a < b ? b : a;
        int[] cache = new int[smaller+1];

        return recMultiply(bigger, smaller, cache);
    }

    public int recMultiply(int bigger, int smaller, int[] cache){
        if(smaller == 0)
            return 0;
        if(smaller == 1)
            return bigger;
        if(cache[smaller] > 0)
            return cache[smaller];

        int halfSmaller = smaller >> 1;
        int leftResult = recMultiply(bigger, halfSmaller, cache);
        int rightResult = leftResult;

        if(smaller % 2 == 1)
            rightResult = recMultiply(bigger, smaller - halfSmaller, cache);

        cache[smaller] = leftResult + rightResult;
        return cache[smaller];
    }

    //Solution 2. Time: O(logs), Space: O(logs)
    public int multiply1(int a, int b){
        int smaller = a < b ? a : b;
        int bigger = a < b ? b : a;

        return recMultiply1(bigger, smaller);
    }

    public int recMultiply1(int bigger, int smaller){
        if(smaller == 0)
            return 0;
        if(smaller == 1)
            return bigger;

        int halfSmaller = smaller >> 1;
        int halfResult = recMultiply1(bigger, halfSmaller);

        if(smaller % 2 == 0)
            return halfResult + halfResult;
        else
            return halfResult + halfResult + bigger;
    }

    /*
    Problem 6: Towers of Hanoi.
     */

    public void solveHanoi(Stack<Integer> source, Stack<Integer> temp, Stack<Integer> dest){
        int n = source.size();
        if(n == 0)
            return;

        recSolveHanoi(source, temp, dest, n);
    }

    public void recSolveHanoi(Stack<Integer> source, Stack<Integer> temp, Stack<Integer> dest, int n){
        if(n == 0)
            return;

        recSolveHanoi(source, dest, temp, n-1);
        dest.push(source.pop());
        recSolveHanoi(temp, source, dest, n-1);
    }

    /*
    Problem 7: Permutations Without Duplicates
     */

    //Solution 1: Using char[] array and rotation
    public void doPermut(char[] chars){
        if(chars == null || chars.length == 0)
            return;
        recDoPermut(chars, 0);
    }

    public void recDoPermut(char[] chars, int index){
        if(index == chars.length - 1) {
            System.out.println(chars);
            return;
        }
        for(int i = index; i < chars.length; i++){
            recDoPermut(chars, index+1);
            rotate(chars, index);
        }
    }

    public void rotate(char[] cArray, int startIndex){
        int length = cArray.length;
        char lastChar = cArray[length-1];
        for(int i = length - 1; i > startIndex; i--){
            cArray[i] = cArray[i-1];
        }
        cArray[startIndex] = lastChar;
    }

    //Solution 2: Using Immutable String
    public ArrayList<String> getPermutations(String word){
        ArrayList<String> result = new ArrayList<>();
        int sLength = word.length();
        if(sLength == 0){
            result.add("");
            return result;
        }
        for(int i = 0; i < sLength; i++){
            String left = word.substring(0,i);
            String right = word.substring(i+1);
            char cur = word.charAt(i);

            ArrayList<String> subResult = getPermutations(left+right);
            for(String s : subResult){
                String permutation = cur + s;
                result.add(permutation);
            }
        }
        return result;
    }

    /*
    Problem 8: Permutations With Duplicates
     */

    //Solution 1: Using HashSet
    public ArrayList<String> getPermutations1(String word){
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> hashSet = new HashSet<>();
        int sLength = word.length();

        if(sLength == 0){
            result.add("");
            return result;
        }
        for(int i = 0; i < sLength; i++){
            String left = word.substring(0, i);
            String right = word.substring(i+1);
            char c = word.charAt(i);
            ArrayList<String> subResult = getPermutations1(left+right);
            for(String s : subResult){
                String permutation = c + s;
                if(!hashSet.contains(permutation)){
                    result.add(permutation);
                    hashSet.add(permutation);
                }
            }
        }
        return result;
    }

    //Solution 2: Using HashMap and Passing Prefix Down Recursive Stack
    public ArrayList<String> getPermutations2(String word){
        ArrayList<String> result = new ArrayList<>();
        HashMap<Character, Integer> map = buildMap(word);
        getPermutations2(map, "", word.length(), result);
        return result;
    }

    public void getPermutations2(HashMap<Character, Integer> map, String prefix, int length, ArrayList<String> result){
        if(length == 0){
            result.add(prefix);
            return;
        }
        for(char c : map.keySet()){
            int occurence = map.get(c);
            if(occurence > 0){
                map.put(c, occurence-1);
                getPermutations2(map, prefix+c, length-1, result);
                map.put(c, occurence);
            }
        }
    }

    public HashMap<Character, Integer> buildMap(String s){
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!hashMap.containsKey(c))
                hashMap.put(c, 1);
            else
                hashMap.put(c, hashMap.get(c) + 1);
        }
        return hashMap;
    }

    /*
    Problem 9: Valid Parenthesis
     */
    public ArrayList<String> validPar(int n){
        if(n <= 0)
            return null;
        char[] cArray = new char[n*2];
        ArrayList<String> result = new ArrayList<>();
        recValidPar(cArray, 0, n, n, result);
        return result;
    }

    public void recValidPar(char[] cArray, int index, int left, int right, ArrayList<String> result){
        if(left < 0 || right > left)//Invalid State
            return;

        if(left == 0 && right == 0){
            result.add(String.copyValueOf(cArray));
            return;
        }
        cArray[index] = '(';
        recValidPar(cArray, index+1, left-1, right, result);

        cArray[index] = ')';
        recValidPar(cArray, index+1, left, right-1, result);
    }

    /*
    Problem 10: Paint Fill
     */

    //Solution: Recursion (DFS)
    public void paintFill(Color[][] screen, int row, int col, Color newColor){
        Color curColor = screen[row][col];
        paintFill(screen, row, col, newColor, curColor);
    }

    public void paintFill(Color[][] screen, int row, int col, Color newColor, Color oldColor){
        if(row < 0 || row >= screen.length || col < 0 || col >= screen[0].length)
            return;

        Color currentColor = screen[row][col];
        if(currentColor.equals(oldColor)){
            screen[row][col] = newColor;
            paintFill(screen, row-1, col, newColor, oldColor);//Go Up
            paintFill(screen, row, col+1, newColor, oldColor);//Go Right
            paintFill(screen, row+1, col, newColor, oldColor);//Go Down
            paintFill(screen, row, col-1, newColor, oldColor);//Go Left
        }
    }


    /*
    Problem 12: Place Queens
     */
    public void placeQueens(){
        int BOARD_SIZE = 8;
        boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];
        placeQueens(board, 0);
    }

    public void placeQueens(boolean[][] board, int row){
        if(row == board.length){
//            printBoard(board);
            return;
        }
        for(int j = 0; j < board[0].length; j++){
            if(isValid(board, row, j)){
                board[row][j] = true;
                placeQueens(board, row+1);
                board[row][j] = false;
            }
        }
    }

    public boolean isValid(boolean[][] board, int row, int col){
        //Check column
        int i; int j;
        for(i = 0; i < row; i++){
            if(board[i][col])
                return false;
        }
        //Check diagonal
        i = row; j = col;
        while(i >= 0 && j >= 0){
            if(board[i][j])
                return false;
            i--; j--;
        }
        i = row; j = col;
        while(i >= 0 && j < board[0].length){
            if(board[i][j])
                return false;
            i--; j++;
        }
        return true;
    }

    public void printBoard(boolean[][] board){
        System.out.println("----------Printing Board----------");
        System.out.println();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
