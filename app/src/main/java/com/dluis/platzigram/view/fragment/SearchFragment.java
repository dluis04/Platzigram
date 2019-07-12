package com.dluis.platzigram.view.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dluis.platzigram.R;
import com.dluis.platzigram.adapter.PictureAdapterRecyclerView;
import com.dluis.platzigram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView picturesRecycler = view.findViewById(R.id.search_recyclerview_photos);


        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2);
        picturesRecycler.setHasFixedSize(true);
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
        pictures.add(new Picture("http://celetours.com/wp-content/uploads/2015/02/oslo5-400x200-300x150.jpg", "Santiago Lenis", "5 días", "4 Me Gusta"));
        pictures.add(new Picture("http://www.tikbok.com/rahalat/wp-content/uploads/2011/08/1-400x200.jpg", "Alejandra Ramirez", "2 días", "9 Me Gusta"));
        pictures.add(new Picture("https://concoursmondial.com/wp-content/uploads/paris-400x200.jpg", "Angelina Mejia", "2 días", "9 Me Gusta"));
        pictures.add(new Picture("http://s3-eu-central-1.amazonaws.com/wp-urbanhub-upload/wp-content/uploads/2018/04/06150654/Lasecity_1920X864-400x200.png", "Rubencha", "2 días", "9 Me Gusta"));
        pictures.add(new Picture("https://www.cocosisland.org/wp-content/uploads/2018/09/isla-del-coco-40-aniversario-foto-aerea-400x200.jpg", "Pepito Perez", "2 días", "9 Me Gusta"));
        return pictures;
    }

}
