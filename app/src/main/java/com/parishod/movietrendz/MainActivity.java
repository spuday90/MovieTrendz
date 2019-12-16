package com.parishod.movietrendz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parishod.movietrendz.adapters.MyRecyclerViewAdapter;
import com.parishod.movietrendz.model.Movie;
import com.parishod.movietrendz.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.parishod.movietrendz.utils.Constants.INTENT_PARCEL;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    List<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mViewModel.init();
        mViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mMovies.clear();
                mMovies.addAll(movies);
                mAdapter.setData(mMovies);
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(mMovies, mOnClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = mRecyclerView.getChildLayoutPosition(v);
            Movie movie = mMovies.get(position);
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra(INTENT_PARCEL, movie);
            startActivity(intent);
        }
    };
}
