package com.example.feelgoodinc.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.feelgoodinc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResourcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourcesFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResourcesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResourcesPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ResourcesFragment newInstance(String param1, String param2) {
        ResourcesFragment fragment = new ResourcesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resources_page, container, false);

        Button crisisHotline = view.findViewById(R.id.button7);
        Button selfHelp = view.findViewById(R.id.button8);
        Button educationMaterial = view.findViewById(R.id.button9);
        Button supportGroup = view.findViewById(R.id.button10);

        ResourcesFragment resourcesFragment = new ResourcesFragment();
        crisisHotline.setOnClickListener(view1 -> {
            PlaceHolderResources placeHolderResources = new PlaceHolderResources();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, placeHolderResources).commit();
        });

        selfHelp.setOnClickListener(view1 -> {
            PlaceHolderResources placeHolderResources = new PlaceHolderResources();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, placeHolderResources).commit();
        });

        educationMaterial.setOnClickListener(view1 -> {
            PlaceHolderResources placeHolderResources = new PlaceHolderResources();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, placeHolderResources).commit();
        });

        supportGroup.setOnClickListener(view1 -> {
            PlaceHolderResources placeHolderResources = new PlaceHolderResources();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, placeHolderResources).commit();
        });
        return view;
    }
}