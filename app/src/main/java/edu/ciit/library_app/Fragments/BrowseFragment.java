package edu.ciit.library_app.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.ciit.library_app.Fragments.FragmentsAdapter.BrowseFragmentAdapter;
import edu.ciit.library_app.Fragments.FragmentsAdapter.VerticalModelAdapter;
import edu.ciit.library_app.Models.HorizontalModel;
import edu.ciit.library_app.Models.VerticalModel;
import edu.ciit.library_app.R;
import edu.ciit.library_app.Models.books;

public class BrowseFragment extends Fragment {
    ArrayList<VerticalModel> bookList = new ArrayList<>();
    ArrayList<HorizontalModel> horizontalModelArrayList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new VerticalModelAdapter(getActivity(),bookList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        updateui();
        return rootView;

    }

    private void updateui()
    {
        for(int i = 0; i < 10; i++)
        {
            horizontalModelArrayList.add(new HorizontalModel("Title " + i));
        }
        for (int i = 0; i < 8; i++)
        {
            bookList.add(new VerticalModel("Genre " + i, horizontalModelArrayList));
        }

        mAdapter.notifyDataSetChanged();
    }
}
