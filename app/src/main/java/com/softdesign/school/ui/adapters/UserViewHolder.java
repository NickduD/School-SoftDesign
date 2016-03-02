package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.user_fullname) protected TextView fullName;
    @Bind(R.id.user_image) protected ImageView avatar;
    @Bind(R.id.user_team) protected TextView team;

    public UserViewHolder(View itemView) {
        super(itemView);
//        fullName = (TextView) itemView.findViewById(R.id.user_fullname);
//        avatar = (ImageView) itemView.findViewById(R.id.user_image);
        ButterKnife.bind(this, itemView);
    }
}
