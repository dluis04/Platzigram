package com.dluis.platzigram.view.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dluis.platzigram.R;
import com.dluis.platzigram.adapter.PictureAdapterRecyclerView;
import com.dluis.platzigram.model.Picture;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("", false, view);


        RecyclerView picturesRecycler = view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }


    public ArrayList<Picture> buildPictures() {
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://www.educationquizzes.com/library/KS3-Geography/river-1-1.jpg", "Diego Luis", "4 días", "15 me gusta"));
        pictures.add(new Picture("https://www.educationquizzes.com/library/KS3_Categories/Links-1-web.jpg", "Uriel Prueba", "3 días", "2 me gusta"));
        pictures.add(new Picture("https://r.hswstatic.com/h_300/gif/airplane-groundspeed.jpg", "Pepito Perez", "2 días", "1 me gusta"));
        return pictures;
    }


    public void showToolbar(String tittle, boolean upButton, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar); /*soporte para verse bien en versiones anteriores al loolipop*/
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

}
