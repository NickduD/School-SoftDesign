package com.softdesign.school.ui.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.softdesign.school.data.storage.models.User;

import java.util.List;

public class UserLoader extends AsyncTaskLoader<List<User>> {

    public UserLoader(Context context) {
        super(context);
    }

    @Override
    public List<User> loadInBackground() {
        return User.getAll();
    }
}
