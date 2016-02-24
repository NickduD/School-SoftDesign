package com.softdesign.school.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUsersAdapter;

import java.util.ArrayList;

/**
 * Created by Nik on 02.02.16.
 */
public class ContactsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<User> mUsers = new ArrayList<User>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_contacts, null, false); // create View from layout
        getActivity().setTitle(R.string.navigation_menu_contacts);
        Activity activity=getActivity();
        ((MainActivity) activity).setMenuItemChecked(R.id.navigation_contacts);
        ((MainActivity) activity).lockBar(true);
        generateData(); // preparing data
        mRecyclerView = (RecyclerView) myView.findViewById(R.id.recycler_view); // initialize RecyclerView
        mLayoutManager = new LinearLayoutManager(getActivity()); // create LayoutManager
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleUsersAdapter(mUsers); // create new adapter
        mRecyclerView.setAdapter(mAdapter); // put data into adapter


        return myView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.float_action_button);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFloatingActionButton.getLayoutParams();
        params.setAnchorId(R.id.coordinator_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.END;
        mFloatingActionButton.setLayoutParams(params);
        mFloatingActionButton.setImageResource(R.drawable.ic_add);
    }

    private void generateData(){
        mUsers.add(new User("Ivan", "Ivanov", getResources().getDrawable(R.drawable.ic_account_circle_24dp)));
        mUsers.add(new User("Petr", "Ivanov", getResources().getDrawable(R.drawable.ic_account_circle_24dp)));
        mUsers.add(new User("Elena", "Ivanova", getResources().getDrawable(R.drawable.ic_account_circle_24dp)));
        mUsers.add(new User("Tatiana", "Fedorova", getResources().getDrawable(R.drawable.ic_account_circle_24dp)));
        mUsers.add(new User("Polina", "Sidorova", getResources().getDrawable(R.drawable.ic_account_circle_24dp)));
        mUsers.add(new User("Sergei", "Gagarin", getResources().getDrawable(R.drawable.ic_account_circle_24dp)));
    }
}
