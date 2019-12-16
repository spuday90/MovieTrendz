package com.parishod.movietrendz;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.parishod.movietrendz.model.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.parishod.movietrendz.utils.Constants.INTENT_PARCEL;
import static com.parishod.movietrendz.utils.Constants.poster_path_prefix;

public class MovieDetailsActivity extends AppCompatActivity {
    private Movie movie;
    ImageView posterImage;
    TextView title, description, releaseDate, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        movie = this.getIntent().getExtras().getParcelable(INTENT_PARCEL);

        posterImage = findViewById(R.id.poster_image);
        Picasso.get()
                .load(poster_path_prefix + movie.getPoster_path())
                .placeholder(R.drawable.movie_placeholder)
                .into(posterImage);

        title = findViewById(R.id.title);
        title.setText(movie.getTitle());

        description = findViewById(R.id.description);
        description.setText(movie.getOverview());

        releaseDate = findViewById(R.id.date);
        releaseDate.setText(movie.getRelease_date());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
