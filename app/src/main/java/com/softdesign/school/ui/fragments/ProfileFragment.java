package com.softdesign.school.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.utils.Lg;

/**
 * Created by Nik on 02.02.16.
 */
public class ProfileFragment extends Fragment {
    private final Lg log = Lg.getLogger(ProfileFragment.class, true);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile, null, false); // create View from layout
        getActivity().setTitle(R.string.navigation_menu_profile);
        Activity activity=getActivity();
        ((MainActivity) activity).setMenuItemChecked(R.id.navigation_profile);
        ((MainActivity) activity).lockBar(false);
       // ((MainActivity) activity).setCollapsToolbarTitle(getResources().getString(R.string.fullname));
        log.e("onCreateView ProfileFragment");
        return myView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.float_action_button);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.app_bar_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.END;
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setImageResource(R.drawable.ic_mode_edit_24dp);
    }

}
