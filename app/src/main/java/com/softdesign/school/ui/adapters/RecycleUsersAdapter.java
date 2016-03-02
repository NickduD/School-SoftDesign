package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;

public class RecycleUsersAdapter extends RecyclerView.Adapter<UserViewHolder>{
    ArrayList<User> users;

    public RecycleUsersAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View converView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        return new UserViewHolder(converView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.fullName.setText(user.getmFirstName() + " " + user.getmLastName());
//        holder.avatar.setImageDrawable(user.getmImage());
        Team team = user.getmTeam();
        holder.team.setText(team.getName());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
