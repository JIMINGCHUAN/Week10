package com.example.week9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final ArrayList<Movie> movieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewGenre.setText("Genre: " + movie.getGenre());

        if (movie.getYear() <= 0) {
            holder.textViewYear.setText("Year: Unknown Year");
        } else {
            holder.textViewYear.setText("Year: " + movie.getYear());
        }

        int imageResId = context.getResources().getIdentifier(
                movie.getPoster(),
                "drawable",
                context.getPackageName()
        );

        if (imageResId == 0) {
            imageResId = R.drawable.default_poster;
        }

        holder.imageViewPoster.setImageResource(imageResId);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewYear;
        TextView textViewGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
        }
    }
}