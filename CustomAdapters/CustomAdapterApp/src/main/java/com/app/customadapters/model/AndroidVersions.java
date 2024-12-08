package com.app.customadapters.model;

public class AndroidVersions {

    public AndroidVersions(String vnum, String vname){
        this.version = vnum;
        this.name = vname;
    }

    private String version;

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    private String name;
}
