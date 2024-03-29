package com.parishod.movietrendz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parishod.movietrendz.R;
import com.parishod.movietrendz.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.parishod.movietrendz.utils.Constants.poster_path_prefix;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    List<Movie> mMovies;
    View.OnClickListener mOnClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public LinearLayout parentLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.iv_movie_poster);
            this.parentLayout = itemView.findViewById(R.id.linear_layout);
        }
    }

    public MyRecyclerViewAdapter(List<Movie> movies, View.OnClickListener onClickListener) {
        this.mMovies = movies;
        this.mOnClickListener = onClickListener;
    }

    public void setData(List<Movie> movies){
        this.mMovies = movies;
        notifyDataSetChanged();;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        RecyclerView.ViewHolder viewHolder = new MyViewHolder(view);

        return (MyViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Movie movie = this.mMovies.get(position);
        Picasso.get()
                .load(poster_path_prefix + movie.getPoster_path())
                .placeholder(R.drawable.movie_placeholder)
                .into(holder.imageView);
        holder.parentLayout.setOnClickListener(mOnClickListener);
        //holder.imageView.setOnClickListener(mOnClickListener);
        //holder.itemView.setOnClickListener(mOnClickListener);
        //holder.imageView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
