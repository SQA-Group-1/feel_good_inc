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
 This is the fragment representing the resources page, it is a simple ui that lays out the
 resources a user can access when need be
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

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     *  This method directs all the buttons in the resources page to the placeholder resources page.
     * @return
     */
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
            emergencyHotline emergencyHotline = new emergencyHotline();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, emergencyHotline).commit();
        });

        selfHelp.setOnClickListener(view1 -> {
            SelfHelpStrat selfHelpStrat = new SelfHelpStrat();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, selfHelpStrat).commit();
        });

        educationMaterial.setOnClickListener(view1 -> {
            EducationResources educationResources = new EducationResources();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, educationResources).commit();
        });

        supportGroup.setOnClickListener(view1 -> {
            SupportGroup supportGroups = new SupportGroup();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, supportGroups).commit();
        });
        return view;
    }
}