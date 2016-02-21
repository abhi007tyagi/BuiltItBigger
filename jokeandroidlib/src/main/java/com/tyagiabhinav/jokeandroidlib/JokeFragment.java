package com.tyagiabhinav.jokeandroidlib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by abhinavtyagi on 19/02/16.
 */
public class JokeFragment extends Fragment{

    public JokeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        ((TextView)root.findViewById(R.id.jokeView)).setText(getArguments().getString("joke"));
        return root;
    }
}
