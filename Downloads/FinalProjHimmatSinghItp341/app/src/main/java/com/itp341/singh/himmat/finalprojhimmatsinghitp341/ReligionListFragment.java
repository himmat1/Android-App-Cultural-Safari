package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.Model.Religion;
import com.itp341.singh.himmat.finalprojhimmatsinghitp341.Model.ReligionSingleton;

import java.util.ArrayList;



public class ReligionListFragment extends Fragment {
    private static final String TAG = ReligionListFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

   private ReligionAdapter religionAdapter;
    private Button button;
    //private OnFragmentInteractionListener mListener;

    ListView listView;

    public ReligionListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ReligionListFragment newInstance() {
        ReligionListFragment fragment = new ReligionListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_religion_list, container, false);
        listView = (ListView) v.findViewById(R.id.listView);

        ArrayList<Religion> r = ReligionSingleton.get(getActivity()).getReligions();
        religionAdapter = new ReligionAdapter(getContext(), 0, r);
        listView.setAdapter(religionAdapter);


        //TODO create listview item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "listView: onListItemClick");
                Intent i = new Intent(getActivity(), ReligionInfoActivity.class);
                i.putExtra("index", position);
                startActivityForResult(i, 0);

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    //TODO finish onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);

        if (resultCode == Activity.RESULT_OK) {
            //refresh the screen
            religionAdapter.notifyDataSetChanged();
        }

    }

    private class ReligionAdapter extends ArrayAdapter<Religion> {
        ArrayList<Religion> religions; //#1 data source

        //#2 constructor
        public ReligionAdapter(Context c, int resId, ArrayList<Religion>objects){

            super(c, 0, objects);
            religions = objects;//storing the custom data

        }
        //#3 generate a single row of our list
        public View getView(int position, View convertView, ViewGroup parent){
            //check if a row has already been instantiated
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(
                        R.layout.religion_cardview,null
                );
            }
            //load the data into the row
            //TextView textName = (TextView)convertView.findViewById(R.id.text_name);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewMovie);
            TextView textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            TextView textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
            Button button = (Button) convertView.findViewById(R.id.buttonFindOutMore);

            Religion cs = religions.get(position);

            int resID = getContext().getResources().getIdentifier(cs.getUrl(), "drawable",
                    getContext().getPackageName());
            imageView.setImageResource(resID);
            textViewTitle.setText(cs.getName());
            textViewDescription.setText(cs.getOrigin());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Find out more button");
//                    Intent intent = new Intent(getContext(), DetailActivity.class);
//                    intent.putExtra("index", position);
//                    getContext().startActivity(intent);
                }
            });
            //textName.setText(cs.getName());

            return convertView;
        }
    }

}
