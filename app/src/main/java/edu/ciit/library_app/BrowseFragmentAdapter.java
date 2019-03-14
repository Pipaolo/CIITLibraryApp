package edu.ciit.library_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowseFragmentAdapter extends RecyclerView.Adapter<BrowseFragmentAdapter.BrowseViewHolder> {

    private ArrayList<books> mBooks;

    public BrowseFragmentAdapter(ArrayList<books> booksList) {
        mBooks = booksList;
    }

    @NonNull
    @Override
    public BrowseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.books, viewGroup, false);
        BrowseViewHolder browseViewHolder = new BrowseViewHolder(v);
        return browseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseViewHolder browseViewHolder, int i) {

        books currentBook = mBooks.get(i);
        browseViewHolder.mBookTitle.setText(currentBook.getBookTitle());
        browseViewHolder.mBookGenre.setText(currentBook.getBookGenre());

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public static class BrowseViewHolder extends RecyclerView.ViewHolder {

        public TextView mBookTitle;
        public TextView mBookGenre;

        public BrowseViewHolder(@NonNull View itemView) {
            super(itemView);
            mBookTitle = itemView.findViewById(R.id.title);
            mBookGenre = itemView.findViewById(R.id.genre);

        }
    }
}
