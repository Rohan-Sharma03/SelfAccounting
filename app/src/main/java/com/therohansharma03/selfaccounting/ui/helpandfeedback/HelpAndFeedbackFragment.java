package com.therohansharma03.selfaccounting.ui.helpandfeedback;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.therohansharma03.selfaccounting.Billing;
import com.therohansharma03.selfaccounting.Help;
import com.therohansharma03.selfaccounting.R;

public class HelpAndFeedbackFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help_and_feedback,container,false);

        Button button = root.findViewById(R.id.test4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Help.class);
                startActivity(i);
            }
        });
        return root;


    }
}