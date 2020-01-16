package tv.kinochi.kinochi.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tv.kinochi.kinochi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorrentsListFragment extends Fragment {


    public TorrentsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_torrents, container, false);
    }

}
