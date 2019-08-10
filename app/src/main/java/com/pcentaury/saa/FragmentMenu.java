//  MainActivity.java
//  Project:    Saa
//  Created by SDE. Alfredo Jiménez Miguel on 08/07/19.
//  Copyright © 2019 com.pcentaury All rights reserved.
//
package com.pcentaury.saa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment {


    public FragmentMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_menu, container, false);
    }

}
