package com.softdesign.school.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;

/**
 * Created by Nik on 02.02.16.
 */
public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_settings, null, false); // create View from layout
        getActivity().setTitle(R.string.navigation_menu_settings);
        Activity activity=getActivity();
        ((MainActivity) activity).setMenuItemChecked(R.id.navigation_settings);
        return myView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
