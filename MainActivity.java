package com.example.week9;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        try {
            movieList = JsonUtils.loadMoviesFromJson(this);
            movieAdapter = new MovieAdapter(this, movieList);
            recyclerViewMovies.setAdapter(movieAdapter);

            Toast.makeText(this, "Movies loaded successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            movieList = new ArrayList<>();
            movieAdapter = new MovieAdapter(this, movieList);
            recyclerViewMovies.setAdapter(movieAdapter);

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}