package com.shaide.interviewpreparraysandstrings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int[][] matrix = {
//                {1,2,3,4,5},
//                {6,7,8,9,10},
//                {11,12,13,14,15},
//                {16,17,18,19,20},
//                {21,22,23,24,25}
//        };
//        displayMatrix(matrix);
//        rotate(matrix);
//        myRotate(matrix);
//        System.out.println("After Rotation");
//        displayMatrix(matrix);

        String first = "Mukhtar";
        String second = "";
        String third = "adrR";
        String fourth = "abcd";

        //System.out.println(oneAway(first, second) ? "True" : "False");
        System.out.println(pPermut(third) ? "True" : "False");
        System.out.println(compressString(fourth));

        char[] sentence = {'P','r','a','c',' ','m','a','k','e','s',' ','p','e','r','f'};
        System.out.println("Before word reversal: ");
        System.out.println(sentence);
        reverseWords(sentence);
        System.out.println("After word reversal: ");
        System.out.println(sentence);
    }

    //Time: O(n), Space: O(1)
    public boolean isUnique(String s){
        if(s == null)
            return false;
        int sLength = s.length();
        if(sLength == 0)
            return false;

        boolean[] flags = new boolean[256];
        for(int i = 0; i < sLength; i++){
            char c = s.charAt(i);
            if(flags[c]) //Found duplicate
                return false;
            else
                flags[c] = true;
        }
        return true;
    }

    /*
    1. Sorting. Time: O(nlogn), Space: O(n)
    2. HashMap. Time: O(n), Space: O(n)
    3. Array of constant size. Time: O(n), Space: O(1)
    4. Bit Manipulation. Time: O(n), Space: O(1)
     */
    //Array of Constant Size Solution. Time: O(n), Space: O(1)
    public boolean isPermut(String a, String b){
        if(a == null || b == null)
            return false;

        int aLen = a.length(); int bLen = b.length(); int uniqueChars = 0;

        if(aLen != bLen)
            return false;

        int[] tracker = new int[256];
        for(int i = 0; i < aLen; i++){
            char c = a.charAt(i);
            if(tracker[c] == 0)
                uniqueChars++;
            tracker[c]++;
        }
        for(int i = 0; i < bLen; i++){
            char c = b.charAt(i);
            if(tracker[c] == 0) //More characters in b than a
                return false;
            tracker[c]--;
            if(tracker[c] == 0)
                uniqueChars--;
        }
        return (uniqueChars == 0);
    }

    public void URLify(char[] s, int tLength){
        if(s == null)
            return;
        if(s.length == 0)
            return;

        int nLength = tLength;
        for(int i = 0; i < tLength; i++){
            if(s[i] == ' ')//Space
                nLength +=2;
        }
        int ptr1; int ptr2;
        for(ptr1 = tLength - 1, ptr2 = nLength -1; ptr1 >= 0; ptr1--, ptr2--){
            if(s[ptr1] == ' '){
                s[ptr2] = '0';
                s[ptr2 - 1] = '2';
                s[ptr2 - 2] = '%';
                ptr2 -= 2;
            }else
                s[ptr2] = s[ptr1];
        }
    }

    public void rotate(int[][] mat){
        if(mat == null)
            return;
        int rows = mat.length;
        int cols = mat[0].length;
        if(rows == 0 && cols == 0)
            return;

        int n = rows;
        for(int layer = 0; layer < n/2; layer++){
            int first = layer; //0
            int last = n-1-layer; //n-1
            for(int i = first; i < last; i++){
                int offset = i-first;

                int top = mat[first][i];
                mat[first][i] = mat[last-offset][first];
                mat[last-offset][first] = mat[last][last-offset];
                mat[last][last-offset] = mat[i][last];
                mat[i][last] = top;
            }
        }
    }

    public void myRotate(int[][] mat){//Assumes square matrix
        if(mat == null)
            return;
        int rows = mat.length;
        int cols = mat[0].length;
        if(rows == 0 && cols == 0)
            return;
        int layer = rows; int n = rows;
        int offset = 0;
        for(int i = layer; layer >= 1; layer-=2, offset++){
            int first = 0 + offset;
            int last = n-1-offset;
            for(int j = first; j < last; j++){
                int diff = j-first;

                int buffer = mat[first][first+diff];//Copy top to buffer
                mat[first][first+diff] = mat[last-diff][first];//Copy left to top
                mat[last-diff][first] = mat[last][last-diff];//Copy bottom to left
                mat[last][last-diff] = mat[first+diff][last];//Copy right to bottom
                mat[first+diff][last] = buffer;//Copy buffer/top to right
            }
        }
    }

    public void myRotate1(int[][] mat){
        if(mat == null)
            return;
        int layer = mat.length; int offset = 0;
        for(int i = layer; i >= 1; i-=2, offset++){
            int first = 0 + offset;
            int last = mat.length - 1 - offset;
            int subSize = last - first + 1;
            int[] buffer = new int[subSize];

            //Copy top to buffer
            for(int j = 0; j < subSize; j++)
                buffer[j] = mat[first][first+j];
            //Copy left to top
            for(int j = 0; j < subSize; j++)
                mat[first][first+j] = mat[last-j][first];
            //Copy bottom to left
            for(int j = 0; j < subSize; j++)
                mat[last-j][first] = mat[last][last-j];
            //Copy right to bottom
            for(int j = 0; j < subSize; j++)
                mat[last][last-j] = mat[first+j][last];
            //Copy buffer to right
            for(int j = 0; j < subSize; j++)
                mat[first+j][last] = buffer[j];
        }
    }

    public void displayMatrix(int[][] mat){
        for(int i = 0; i<mat.length;i++){
            for(int j = 0; j<mat[0].length;j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean oneAway(String a, String b){
        if(a == null || b == null)
            return false;

        int aLen = a.length();
        int bLen = b.length();
        int editCount = 0;
        int minLen = Math.min(aLen, bLen);
        int aPtr = 0; int bPtr = 0;
        int lenDiff = Math.abs(aLen-bLen);
        if(lenDiff > 1)
            return false;
        int i = 0;
        while(i < minLen){
            if(a.charAt(aPtr) != b.charAt(bPtr)){
                editCount++;
                if(aLen < bLen)
                    aPtr--;
                else if(bLen < aLen)
                    bPtr--;
                if(editCount > 1)
                    return false;
            }
            aPtr++; bPtr++; i++;
        }
        if(editCount == 0 && aLen != bLen)
            return true;
        return (editCount == 1);
    }

    public boolean pPermut(String s){
        int ASCI_COUNT = 256;
        if(s == null)
            return false;
        int sLen = s.length();
        if(sLen == 0)
            return false;
        int uCount = 0;
        boolean[] flags = new boolean[ASCI_COUNT];

        for(int i = 0; i < sLen; i++){
            char c = Character.toLowerCase(s.charAt(i));
            if(c != ' '){
                flags[c] = !flags[c];
                if (flags[c] == true)
                    uCount++;
                else
                    uCount--;
            }
        }
        return (uCount <= 1);
    }

    public String compressString(String s){
        if(s == null)
            return null;
        int sLength = s.length();
        if(sLength <= 1)
            return s;
        int curCount = 0;
        StringBuilder sb = new StringBuilder();
        boolean wasCompressed = false;
        char curChar = 0;
        for(int i = 0; i < sLength; i++){
            char c = s.charAt(i);
            if(c != curChar){
                if(curCount != 0)
                    sb.append(Integer.toString(curCount));
                sb.append(c);
                curCount = 1;
                curChar = c;
            }else{
                curCount++;
                wasCompressed = true;
            }
        }
        if(wasCompressed)
            return sb.toString();
        else
            return s;
    }

    public void reverseWords(char[] arr){
        if(arr == null)
            return;
        int length = arr.length;
        if(length == 0)
            return;
        reverseCharacters(arr);
        int tail = 0; int i = 0;
        for(i = 0; i < length; i++){
            if(arr[i] == ' '){
                reverseWord(arr, tail, i-1);
                tail = i+1;
            }
            if(i== length-1){
                reverseWord(arr, tail, i);
            }
        }
    }
    public void reverseCharacters(char[] charArray){
        int lower = 0;
        int upper = charArray.length - 1;
        while(lower < upper){
            char temp;
            temp = charArray[lower];
            charArray[lower] = charArray[upper];
            charArray[upper] = temp;
            lower++; upper--;
        }
    }
    public void reverseWord(char[] cArray, int start, int end){
        while(start < end){
            char temp;
            temp = cArray[start];
            cArray[start] = cArray[end];
            cArray[end] = temp;
            start++;
            end--;
        }
    }
}
