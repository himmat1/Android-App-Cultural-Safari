package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.Model.Religion;
import com.itp341.singh.himmat.finalprojhimmatsinghitp341.Model.ReligionSingleton;


public class ReligionInfoFragment extends Fragment {
    private TextView religionName;
    private TextView description;
    private TextView prophets;
    private ListView listViewFriends;
    private TextView beliefGod;
    private TextView holyBook;
    private static int index;
    private Religion religion;
    private ArrayAdapter<String> list;
    private FrameLayout frame;

    public ReligionInfoFragment() {
        // Required empty public constructor
    }

    public static ReligionInfoFragment newInstance(int i) {
        ReligionInfoFragment fragment = new ReligionInfoFragment();
        Bundle args = new Bundle();
        index =i;
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
        View v = inflater.inflate(R.layout.fragment_religion_info, container, false);

        if (index >= 0) {
            religion = ReligionSingleton.get(getContext()).getReligion(index);
        }

        religionName = (TextView) v.findViewById(R.id.religionName);
        description = (TextView)v.findViewById(R.id.textAbout);
        prophets = (TextView) v.findViewById(R.id.text_prophets);
        beliefGod =(TextView)v.findViewById(R.id.text_god);
        holyBook = (TextView)v.findViewById(R.id.text_book);
        listViewFriends = (ListView) v.findViewById(R.id.listFriends);
        frame = (FrameLayout)v.findViewById(R.id.frameReligionInfo);
        if(index ==0){
            frame.setBackground(getActivity().getResources().getDrawable(R.drawable.cross));
        }else if(index==1){
            frame.setBackground(getActivity().getResources().getDrawable(R.drawable.quran));
        }else if(index==2){
            frame.setBackground(getActivity().getResources().getDrawable(R.drawable.hindu));
        }else if(index==3){
            frame.setBackground(getActivity().getResources().getDrawable(R.drawable.buddha));
        }else if(index==4){
            frame.setBackground(getActivity().getResources().getDrawable(R.drawable.khandha));
        }

        religionName.setText(religion.getName());
        description.setText(religion.getOrigin());
        prophets.setText(religion.getProphets());
        beliefGod.setText(religion.getBeliefInGod());
        holyBook.setText(religion.getHolyBook());
        list = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, religion.getFriends());
        listViewFriends.setAdapter(list);

        return v;
    }

}
