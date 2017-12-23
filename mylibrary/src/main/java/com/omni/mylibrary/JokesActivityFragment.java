package com.omni.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Omni on 23/12/2017.
 */

public class JokesActivityFragment extends Fragment {

    private TextView displayJokeTv ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.jokes_activity_fragment , container , false);
        displayJokeTv = rootView.findViewById(R.id.display_text_view);
        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("Joke"))
            displayJokeTv.setText(intent.getStringExtra("Joke"));
        return  rootView ;
    }
}
