package com.example.recyclerview;

import android.os.Bundle;

import com.example.recyclerview.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    private RecyclerView mRecyclerView;
    private  WordListAdapter mAdapter;

    private LinkedList<String> mWordList = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        createInitialData();
        setupAdapter();
        setupFAB();
    }

    private void setupAdapter() {
        //Get a handle to the Recycler View
        mRecyclerView = findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed
        mAdapter = new WordListAdapter(this , mWordList);
        // Connect the adapter with the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createInitialData() {
        // Creating a dataset to use in this app
        for (int i=0 ; i<20 ; i++){
            mWordList.addLast("Word "+i);
        }
    }

    private void setupFAB() {
        activityMainBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                mWordList.addLast("Word "+wordListSize+"     New");        //Add a new word to the wordList
                mAdapter.notifyItemInserted(wordListSize);  // Notify the adapter that data has changed
                mRecyclerView.smoothScrollToPosition(wordListSize);           // Scroll to the bottom
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}