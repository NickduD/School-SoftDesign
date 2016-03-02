package com.softdesign.school.data.storage.models;

import android.graphics.drawable.Drawable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Users")
public class User extends Model{
    @Column(name = "firstName")
    private String mFirstName;
    @Column(name = "lastName")
    private String mLastName;
    private int mRate;
    private Drawable mImage;
    private String mVkLink;
    private String mGithubLink;
    private String mHometask;
    @Column(name = "team")
    private Team mTeam;

    public User(){
        super();
    }

    public User(String mFirstName, String mLastName, Drawable mImage) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mImage = mImage;
    }

    public User(String mFirstName, String mLastName, Team mTeam) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mTeam = mTeam;
    }
    public String getmFirstName() {
        return mFirstName;
    }

    public Team getmTeam() {
        return mTeam;
    }

    public String getmLastName() {
        return mLastName;
    }

    public int getmRate() {
        return mRate;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public String getmVkLink() {
        return mVkLink;
    }

    public String getmGithubLink() {
        return mGithubLink;
    }

    public String getmHometask() {
        return mHometask;
    }

    public static List<User> getAll(){
        return new Select().from(User.class).execute();
    }
    public static User getByFirstName(String name){
        return new Select().from(User.class).where("firstName =?", name).executeSingle();
    }
    public static User getByLastName(String name){
        return new Select().from(User.class).where("lastName =?",name).executeSingle();
    }
}
