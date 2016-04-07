package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.HawkerCentre;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michelle on 4/7/2016.
 */

public class HawkerCentreDAOImpl implements HawkerCentreDAO {
    ArrayList<HawkerCentre> hawkerCentres;

    public HawkerCentreDAOImpl() {
        hawkerCentres = new ArrayList<HawkerCentre>();
        hawkerCentres.add(new HawkerCentre(
                "Adam Food Centre",
                "2 Adam Road, Singapore 289876",
                2.0, "adam_food_centre"));
    }

    @Override
    public ArrayList<HawkerCentre> getHawkerCentresByDistance() {
        return null;
    }

    @Override
    public ArrayList<HawkerCentre> getAllHawkerCentres() {
        return hawkerCentres;
    }

    @Override
    public HawkerCentre getHawkerCentreByName(String name) {
        for (HawkerCentre hc : hawkerCentres) {
            if (hc.getName().equals(name))
                return hc;
        }
        return null;
    }
}