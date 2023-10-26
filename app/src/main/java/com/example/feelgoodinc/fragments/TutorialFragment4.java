package com.example.feelgoodinc.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.feelgoodinc.R;

public class TutorialFragment4 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tutorial4, container, false);
        TextView tv = (v.findViewById(R.id.eulaText));
        tv.setMovementMethod(new ScrollingMovementMethod());

        String eula = "Feel Good Inc. should not be viewed as a substitute for therapy or other psychological or " +
                "psychiatric treatment, nor should they be considered a substitute for a diagnosis or" +
                "medical advice.\n\n" +
                "Notwithstanding the scientific research on journaling, Feel Good Inc. makes no representation" +
                "or warranty that the User’s mental health will improve. Reliance on the Apps and the" +
                "information they contain is strictly at the User’s risk. If the App licensee has been" +
                "diagnosed with a psychiatric condition they may wish to speak to their medical" +
                "practitioner before use.\n\n" +
                "Feel Good Inc. makes no representation or warranty that the Apps will prevent, cure or treat" +
                "diseases, disorders or conditions, including those related to cognition and the brain, but" +
                "not limited to such.\n\n" +
                "Feel Good Inc. has designed the Apps for general information purposes only. We make no" +
                "representation or warranty regarding accuracy, completeness, suitability and safety. The" +
                "User assumes full responsibility and Feel Good Inc. is not liable for any consequences resulting" +
                "from reliance.\n\n" +
                "The Apps should not be viewed as a substitute for addiction or rehabilitation programmes." +
                "Some of the Apps are designed to increase productivity and help manage habits. Although" +
                "the User may find them helpful in overcoming addictions, Feel Good Inc. makes no" +
                "representation or warranty that they are fit for this purpose. The App licensee should not" +
                "delay in obtaining such intervention as a result of usage.\n\n";

        tv.setText(eula);
        return v;
    }
}