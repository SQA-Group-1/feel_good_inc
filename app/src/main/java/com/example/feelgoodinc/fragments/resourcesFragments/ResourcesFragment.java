package com.example.feelgoodinc.fragments.resourcesFragments;

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

    public ResourcesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Button crisisHotline = view.findViewById(R.id.emergencyContactsPage);
        Button selfHelp = view.findViewById(R.id.selfHelp);
        Button educationMaterial = view.findViewById(R.id.educationMaterial);
        Button supportGroup = view.findViewById(R.id.SupportGroup);

        ResourcesFragment resourcesFragment = new ResourcesFragment();
        crisisHotline.setOnClickListener(view1 -> {
            emergencyHotline emergencyHotline = new emergencyHotline();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, emergencyHotline).commit();
        });

        selfHelp.setOnClickListener(view1 -> {
            SelfHelpStrat selfHelpStrat = new SelfHelpStrat();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, selfHelpStrat).commit();
        });

        educationMaterial.setOnClickListener(view1 -> {
            EducationResources educationResources = new EducationResources();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, educationResources).commit();
        });

        supportGroup.setOnClickListener(view1 -> {
            SupportGroup supportGroups = new SupportGroup();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frameLayout2, supportGroups).commit();
        });
        return view;
    }
}