/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentcommunication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener {

    private Button button;
    private boolean isFragmentOpen = false;

    private static final String STATE_FRAGMENT = "state_of_fragment";
    private static final int NONE = 2;
    private static final int YES = 1;
    private static final int NO = 0;
    private int radioButtonChoice = NONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        if (savedInstanceState != null) {
            isFragmentOpen =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentOpen) {
                // If the fragment is displayed, change button to "close".
                button.setText(R.string.close);
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFragmentOpen)
                    closeFragment();
                else
                    displayFragment();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the state of the fragment (true=open, false=closed).
        outState.putBoolean(STATE_FRAGMENT, isFragmentOpen);
        super.onSaveInstanceState(outState);
    }

    private void initUI() {
        button = findViewById(R.id.open_button);
    }

    private void displayFragment(){
        // Create a new instance. Get the fragment manager. Start the transaction.
        SimpleFragment simpleFragment = SimpleFragment.newInstance(radioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).disallowAddToBackStack().commit();

        //Update the text on the button
        button.setText(getResources().getString(R.string.close));
        //set the boolean variable to true
        isFragmentOpen = true;
    }

    private void closeFragment(){
        //Get fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        if (simpleFragment != null){
            //Create and commit the transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        button.setText(getResources().getString(R.string.open));
        isFragmentOpen = false;
    }

    @Override
    public void onRadioButtonClick(int choice) {
        // Keep the radio button choice to pass to the fragment
        radioButtonChoice = choice;
        Toast.makeText(MainActivity.this, radioButtonChoice + "", Toast.LENGTH_SHORT).show();
    }
}
