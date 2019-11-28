package com.parishod.movietrendz.network;

import com.parishod.movietrendz.model.MoviePageResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMoviesDataService {

    @GET("movie/popular")
    Call<MoviePageResults> getMoviesList(@Query("page") int page, @Query("api_key") String userkey);
}
