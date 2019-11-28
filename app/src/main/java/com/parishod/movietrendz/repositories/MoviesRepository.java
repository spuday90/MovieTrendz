package com.parishod.movietrendz.repositories;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.parishod.movietrendz.MainActivity;
import com.parishod.movietrendz.model.Movie;
import com.parishod.movietrendz.model.MoviePageResults;
import com.parishod.movietrendz.network.GetMoviesDataService;
import com.parishod.movietrendz.network.RetroFitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.parishod.movietrendz.utils.Constants.API_KEY;

public class MoviesRepository {
        private static MoviesRepository instance;
        private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();;

        public static MoviesRepository getInstance(){
            if(instance == null){
                instance = new MoviesRepository();
            }
            return instance;
        }

        public MutableLiveData<List<Movie>> getMovies(){
            GetMoviesDataService service = RetroFitInstance.getRetrofitInstance().create(GetMoviesDataService.class);
            Call<MoviePageResults> call = service.getMoviesList(1, API_KEY);
            call.enqueue(new Callback<MoviePageResults>() {
                @Override
                public void onResponse(Call<MoviePageResults> call, Response<MoviePageResults> response) {
                    if(!response.isSuccessful()){
                        Log.i("MovieRepository", "Code: " + response.code());
                        return;
                    }
                    movies.setValue(response.body().getMovieResult());
                }

                @Override
                public void onFailure(Call<MoviePageResults> call, Throwable t) {
                    movies.setValue(null);
                    Log.i("MovieRepository", "Code: " + t.getMessage());
                }
            });
            return movies;
        }
}
