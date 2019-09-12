/*
 * Created by Sujoy Datta. Copyright (c) 2019. All rights reserved.
 *
 * To the person who is reading this..
 * When you finally understand how this works, please do explain it to me too at sujoydatta26@gmail.com
 * P.S.: In case you are planning to use this without mentioning me, you will be met with mean judgemental looks and sarcastic comments.
 */

package com.example.android.fragmentcommunication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    private RadioGroup radioGroup;
    private RadioButton yesButton;
    private RadioButton noButton;
    private RatingBar ratingBar;
    private View view;

    private static final int YES = 0;
    private static final int NO = 1;

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance(){
        return new SimpleFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_simple, container, false);

        initUI();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);

                TextView textView = view.findViewById(R.id.fragment_header);
                switch (index) {
                    case YES:       //User chooses yes
                        textView.setText(R.string.yes_message);
                        break;
                    case NO:        //User chooses no
                        textView.setText(R.string.no_message);
                        break;
                    default:
                        break;
                }
            }
        });

        // Set rating bar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                // Get rating and show Toast with rating.
                String myRating = (getString(R.string.my_rating) + ratingBar.getRating());
                Toast.makeText(getContext(), myRating, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initUI() {
        radioGroup = view.findViewById(R.id.radio_group);
        yesButton = view.findViewById(R.id.radio_button_yes);
        noButton = view.findViewById(R.id.radio_button_no);
        ratingBar = view.findViewById(R.id.ratingBar);
    }

}
