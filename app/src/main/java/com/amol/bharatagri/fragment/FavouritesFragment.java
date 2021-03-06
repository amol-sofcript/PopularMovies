package com.amol.bharatagri.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.amol.bharatagri.MovieDetailsActivity;
import com.amol.bharatagri.R;
import com.amol.bharatagri.adapter.CustomMovieAdapter;
import com.amol.bharatagri.adapter.ViewPagerAdapter;
import com.amol.bharatagri.data.FavouriteMoviesContract;
import com.amol.bharatagri.data.MovieModel;

import java.util.ArrayList;


public class FavouritesFragment extends Fragment {

    public static final String TITLE = "Favourites";

    private RecyclerView recyclerView;
    private ArrayList<MovieModel> movies;
    private CustomMovieAdapter movieAdapter;
    private View favouritesView;

    public static FavouritesFragment newInstance() {

        return new FavouritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        favouritesView = inflater.inflate(R.layout.fragment_favourites, container, false);

        loadViews();

        return favouritesView;

    }

    private void loadViews() {
        recyclerView = favouritesView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        loadMovies(getActivity().getApplicationContext());

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            final GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    MovieModel movie = movies.get(position);

                    Intent intentToStartDetailActivity = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
                    intentToStartDetailActivity.putExtra("MOVIE", movie);

                    startActivity(intentToStartDetailActivity);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            final GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    MovieModel movie = movies.get(position);

                    Intent intentToStartDetailActivity = new Intent(getActivity().getApplicationContext(), MovieDetailsActivity.class);
                    intentToStartDetailActivity.putExtra("MOVIE", movie);

                    startActivity(intentToStartDetailActivity);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                // do nothing
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                // do nothing
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int position = viewHolder.getAdapterPosition();

                MovieModel movie = movies.get(position);

                Uri movieUri = FavouriteMoviesContract.FavouriteMovieEntry.CONTENT_URI.buildUpon().appendEncodedPath("/" + movie.getId()).build();

                int rowsDeleted = getActivity().getContentResolver().delete(movieUri, null, null);

                if (rowsDeleted > 0) {
                    Toast.makeText(getActivity().getBaseContext(), "Removed from favourites successfully", Toast.LENGTH_LONG).show();
                    ViewPagerAdapter.reloadFavouriteMovies(getContext());
                }

            }
        }).attachToRecyclerView(recyclerView);
    }

    public void loadMovies(Context context) {

        movies = new ArrayList<>();
        try (Cursor cursor = getAllFavouriteMovies()) {
            while (cursor.moveToNext()) {

                MovieModel movie = createMovie(cursor);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (movieAdapter == null) {
            movieAdapter = new CustomMovieAdapter(context, movies);
            recyclerView.setAdapter(movieAdapter);
        } else {
            movieAdapter.setMovies(movies);
            movieAdapter.notifyDataSetChanged();
        }
    }

    private MovieModel createMovie(Cursor cursor) {

        int idIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_MOVIE_ID);
        int id = cursor.getInt(idIdx);

        int originalTitleIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_ORIGINAL_TITLE);
        String originalTitle = cursor.getString(originalTitleIdx);

        int posterPathIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_POSTER_PATH);
        String posterPath = cursor.getString(posterPathIdx);

        int backdropPathIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_BACKDROP_PATH);
        String backdropPath = cursor.getString(backdropPathIdx);

        int releaseDateIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_RELEASE_DATE);
        String releaseDate = cursor.getString(releaseDateIdx);

        int overviewIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_OVERVIEW);
        String overview = cursor.getString(overviewIdx);

        int voteAverageIdx = cursor.getColumnIndex(FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_VOTE_AVERAGE);
        double voteAverage = cursor.getDouble(voteAverageIdx);

        return new MovieModel(id, originalTitle, posterPath, backdropPath, releaseDate, overview, voteAverage);
    }

    private Cursor getAllFavouriteMovies() {

        return getActivity().getContentResolver().query(FavouriteMoviesContract.FavouriteMovieEntry.CONTENT_URI,
                null,
                null,
                null,
                FavouriteMoviesContract.FavouriteMovieEntry.COLUMN_VOTE_AVERAGE);
    }

}