package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tyagiabhinav.jokeandroidlib.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeReceiver{

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ((Button)root.findViewById(R.id.tellJoke)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JokeServerAsync(getActivity(),true).execute(MainActivityFragment.this);
            }
        });

        return root;
    }

    @Override
    public void onReceived(String joke) {
        Log.d("Paid: Joke received...", joke);
//        Toast.makeText(getActivity(), "Paid: "+joke, Toast.LENGTH_SHORT).show();
        Intent jokeIntent = new Intent(getActivity(), JokeActivity.class);
        jokeIntent.putExtra("joke", joke);
        startActivity(jokeIntent);
    }


}
