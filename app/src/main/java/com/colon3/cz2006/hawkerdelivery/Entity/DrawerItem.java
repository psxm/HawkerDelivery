package com.colon3.cz2006.hawkerdelivery.Entity;

/**
 * Created by Hippo on 28/03/2016.
 */
public class DrawerItem {

    private String title;
    private String icon_source;

    public DrawerItem( String title, String icon_source) {

        this.title = title;
        this.icon_source = icon_source;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon_source() {
        return icon_source;
    }

    public void setIcon_source(String icon_source) {
        this.icon_source = icon_source;
    }
}
