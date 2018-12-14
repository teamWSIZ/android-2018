package com.android7.projektstartowy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment1 extends Fragment {

    public Fragment1() {
    }

    Button mButton0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        mButton0 = view.findViewById(R.id.button2);

        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"tekst",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
