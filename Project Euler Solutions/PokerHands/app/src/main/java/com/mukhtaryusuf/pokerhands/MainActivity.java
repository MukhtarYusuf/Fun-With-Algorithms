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
    }
}
