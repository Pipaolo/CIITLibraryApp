package edu.ciit.library_app.Fragments.FragmentsAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.ciit.library_app.Models.HorizontalModel;
import edu.ciit.library_app.R;

public class HorizontalModelAdapter extends RecyclerView.Adapter<HorizontalModelAdapter.HorizontalRVViewHolder>
{
    ArrayList<HorizontalModel> arrayList;
    Context context;

    public HorizontalModelAdapter(Context context, ArrayList<HorizontalModel> bookTitleList)
    {
        this.context = context;
        arrayList = bookTitleList;
    }

    @NonNull
    @Override
    public HorizontalModelAdapter.HorizontalRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_horizontal, viewGroup, false);
        HorizontalRVViewHolder horizontalRVViewHolder = new HorizontalRVViewHolder(v);
        return horizontalRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalModelAdapter.HorizontalRVViewHolder browseViewHolder, int i)
    {
        final HorizontalModel horizontalModel = arrayList.get(i);
        browseViewHolder.mBookTitle.setText(horizontalModel.getBookTitle());
        browseViewHolder.mBookTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, horizontalModel.getBookTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class HorizontalRVViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mBookTitle;

        public HorizontalRVViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mBookTitle = itemView.findViewById(R.id.tvBookTitle);
        }

    }
}
