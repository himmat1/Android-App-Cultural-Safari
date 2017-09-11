package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AboutFragment extends Fragment {
    private static final String TAG = AboutFragment.class.getSimpleName();

    //Bundle key
    public static final String ARGS_POSITION = "args_position";


    Button mButton;

    public AboutFragment() {
        // Required empty public constructor
    }

    //TODO store newInstance input into fragment argument
    public static AboutFragment newInstance() {  //this is the factory part
        Bundle args = new Bundle();

        AboutFragment f = new AboutFragment();
        f.setArguments(args);

        return f;
    }

    //TODO read bundle argument
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_about, container, false);


        //TODO find Views
        mButton = (Button) v.findViewById(R.id.button_explore);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create intent to start Create activity
                Intent i = new Intent(getActivity(), ReligionListActivity.class);
                //there is no position
                startActivity(i);
            }
        });
        return v;
    }


}
