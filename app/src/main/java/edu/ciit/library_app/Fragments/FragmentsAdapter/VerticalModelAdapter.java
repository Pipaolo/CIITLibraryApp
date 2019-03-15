package edu.ciit.library_app.Fragments.FragmentsAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.ciit.library_app.Models.HorizontalModel;
import edu.ciit.library_app.Models.VerticalModel;
import edu.ciit.library_app.R;

public class VerticalModelAdapter extends RecyclerView.Adapter<VerticalModelAdapter.VerticalRVViewHolder>
{
    ArrayList<VerticalModel> mVerticalModel;
    Context context;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static class VerticalRVViewHolder extends RecyclerView.ViewHolder
    {
        RecyclerView horizontalRV;
        Button btnMore;
        public TextView bookTitle;

        public VerticalRVViewHolder(@NonNull View itemView)
        {
            super(itemView);
            horizontalRV = itemView.findViewById(R.id.recyclerView_vertical);
            bookTitle = itemView.findViewById(R.id.txtTitle);
            btnMore = itemView.findViewById(R.id.btnMore);
        }

    }

    public VerticalModelAdapter(Context context, ArrayList<VerticalModel> bookCatalogue)
    {
        this.context = context;
        mVerticalModel = bookCatalogue;
    }

    @NonNull
    @Override
    public VerticalRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vertical, viewGroup,false);
        VerticalRVViewHolder verticalRVViewHolder = new VerticalRVViewHolder(v);
        return verticalRVViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull VerticalModelAdapter.VerticalRVViewHolder browseViewHolder, int i)
    {
        final VerticalModel currentBook = mVerticalModel.get(i);

        ArrayList<HorizontalModel> bookGenre = currentBook.getArrayList();
        browseViewHolder.bookTitle.setText(currentBook.getBookGenre());

        mAdapter = new HorizontalModelAdapter(context,bookGenre);
        mRecyclerView = browseViewHolder.horizontalRV;
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false);
        mAdapter = new HorizontalModelAdapter(context, bookGenre);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        browseViewHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, currentBook.getBookGenre(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mVerticalModel.size();
    }

}
