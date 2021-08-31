package com.amol.bharatagri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amol.bharatagri.R;
import com.amol.bharatagri.data.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomMovieAdapter extends RecyclerView.Adapter<CustomMovieAdapter.MovieViewHolder> {
    private ArrayList<MovieModel> movies;
    private final Context context;

    public CustomMovieAdapter(Context context, ArrayList<MovieModel> movies) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_movie_thumbnail, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {

        Picasso.with(context).load("http://image.tmdb.org/t/p/" + "w185" + movies.get(i).getPoster_path()).into(movieViewHolder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvOriginalTitle;
        private final ImageView ivMovie;

        MovieViewHolder(View view) {
            super(view);

            tvOriginalTitle = view.findViewById(R.id.tv_original_title);
            ivMovie = view.findViewById(R.id.iv_movie);

        }
    }

}
