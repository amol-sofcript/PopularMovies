package com.amol.bharatagri.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("movie/popular?")
    Call<MoviesJSONResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated?")
    Call<MoviesJSONResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos?}")
    Call<VideosJSONResponse> getMovieVideos(@Path("movie_id") Integer id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews?")
    Call<ReviewsJSONResponse> getMovieReviews(@Path("movie_id") Integer id, @Query("api_key") String apiKey);
}