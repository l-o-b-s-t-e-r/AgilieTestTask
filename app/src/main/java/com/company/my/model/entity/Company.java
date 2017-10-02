package com.company.my.model.entity;

import io.realm.RealmObject;

/**
 * Created by Lobster on 30.09.17.
 */

public class Company extends RealmObject {

    private String name;

    private String catchPhrase;

    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
