package com.parishod.movietrendz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parishod.movietrendz.adapters.MyRecyclerViewAdapter;
import com.parishod.movietrendz.model.Movie;
import com.parishod.movietrendz.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mViewModel.init();
        mViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.i("MainActivity", "Observer listener");
                if (mAdapter == null){
                    mAdapter = new MyRecyclerViewAdapter(movies);
                    mRecyclerView.setAdapter(mAdapter);
                }
                else
                    mAdapter.notifyDataSetChanged();
            }
        });

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
    }

}
