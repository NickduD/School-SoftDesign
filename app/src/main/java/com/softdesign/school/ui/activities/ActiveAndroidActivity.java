package com.softdesign.school.ui.activities;

import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.adapters.RecycleUsersAdapter;
import com.softdesign.school.ui.loaders.UserLoader;
import com.softdesign.school.utils.Lg;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActiveAndroidActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<User>> {
    private final Lg log = Lg.getLogger(ActiveAndroidActivity.class, true);
    @Bind(R.id.add_user_button)
    Button mAddUserButton;
    @Bind(R.id.add_team_button)
    Button mAddTeamButton;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<User> mUsers = new ArrayList<User>();


    private Spinner mSpinner; // should be declared, else it doesn't show

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.e("onCreate");

        setContentView(R.layout.activity_activeandroid);
        ButterKnife.bind(this); // should be called only after setContentView
        setTitle("Homework #6");
//        setSupportActionBar(mToolbar);
        setupToolbar();

        mUsers.addAll(User.getAll()); // load saved data

        mLayoutManager = new LinearLayoutManager(this); // create LayoutManager
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleUsersAdapter(mUsers); // create new adapter
        mRecyclerView.setAdapter(mAdapter); // put data into adapter

        // Prepare the loader.  Either re-connect with an existing one,
// or start a new one.
        getLoaderManager().initLoader(0, null, this);
        // http://jmcdale.com/201505567/android-recyclerviews-102-swipe-to-dismiss
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                User user = mUsers.get(position);
                user.delete();
                getLoaderManager().getLoader(0).forceLoad();
                mRecyclerView.getAdapter().notifyItemRemoved(position);

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    /**
     * Setup custom toolbar
     */
    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        log.e("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.e("onDestoy");
    }

    @OnClick(R.id.add_user_button)
    public void addUser() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.dialog_add_user, null, false);
//        List<Team> teams = Team.getAll();
        List<String> l = Team.getAllNames();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Team.getAllNames());


        AlertDialog.Builder builder = new AlertDialog.Builder(ActiveAndroidActivity.this);
        builder.setTitle("Add new user?")
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        final EditText name = (EditText) ((Dialog) dialog).findViewById(R.id.dialog_adduser_firstname);
                        final EditText lastName = (EditText) ((Dialog) dialog).findViewById(R.id.dialog_adduser_lastname);
                        try {
                            String teamName = mSpinner.getSelectedItem().toString();
                            User addUser = new User(checkIsNotEmpty(name), checkIsNotEmpty(lastName), Team.getByName(teamName));
                            addUser.save();
                            getLoaderManager().getLoader(0).forceLoad();
                        } catch (NullPointerException e) {
                            Toast.makeText(ActiveAndroidActivity.this, "You should fill all fields", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setView(R.layout.dialog_add_user);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        mSpinner = (Spinner) alertDialog.findViewById(R.id.dialog_adduser_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Team.getAllNames());
        mSpinner.setAdapter(adapter);
        mSpinner.setPrompt("New User");


    }

    /**
     * @param myeditText
     * @return text of myeditText
     * @throws NullPointerException if the EditText is empty
     */
    private String checkIsNotEmpty(EditText myeditText) throws NullPointerException {
        if (myeditText.getText().toString().trim().length() == 0)
            throw new NullPointerException("EditText is empty");
        return myeditText.getText().toString();
    }

    @OnClick(R.id.add_team_button)
    public void addTeam() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.dialog_add_team, null, false);
//        List<Team> teams = Team.getAll();


//        ButterKnife.bind(this, dialogView);
        AlertDialog.Builder builder = new AlertDialog.Builder(ActiveAndroidActivity.this);
        builder.setTitle("Add new user?")
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText name = (EditText) ((Dialog) dialog).findViewById(R.id.dialog_addteam_teamname);
                        try {
                            String newTeamName = checkIsNotEmpty(name);
                            Team t = Team.getByName(newTeamName);
                            boolean checkNotExistsTeam = Team.getByName(newTeamName) == null;
                            if (checkNotExistsTeam) {
                                Team addTeam = new Team(newTeamName);
                                addTeam.save();
                            } else {
                                Toast.makeText(ActiveAndroidActivity.this, "The team already exists", Toast.LENGTH_SHORT).show();

                            }
                        } catch (NullPointerException e) {
                            Toast.makeText(ActiveAndroidActivity.this, "You should fill a name", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setView(R.layout.dialog_add_team);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    /**
     * Creates new instance of loader
     *
     * @param id   of loader
     * @param args
     * @return new loader
     */
    @Override
    public Loader<List<User>> onCreateLoader(int id, Bundle args) {
        log.e("onCreateLoader");
        return new UserLoader(this);
    }

    /**
     * Calls, when the data had been already load
     *
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
        log.e("onLoadFinished");
        mAdapter = new RecycleUsersAdapter((ArrayList) data); // create new adapter
        mRecyclerView.setAdapter(mAdapter);
        mUsers.clear();
        mUsers.addAll(data);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {

    }

}
