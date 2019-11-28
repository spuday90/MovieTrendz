package com.parishod.movietrendz.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.parishod.movietrendz.model.Movie;
import com.parishod.movietrendz.repositories.MoviesRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movies;
    private MoviesRepository mRepo;

    public void init(){
        if(movies != null){
            return;
        }
        //mRepo = NicePlaceRepository.getInstance();
        //mNicePlaces = mRepo.getNicePlaces();
        loadMovies();
    }

    public LiveData<List<Movie>> getMovies(){
        return movies;
    }

    private void loadMovies() {
        //Call Repository to fetch data from server
        mRepo = MoviesRepository.getInstance();
        movies = mRepo.getMovies();
    }
}
