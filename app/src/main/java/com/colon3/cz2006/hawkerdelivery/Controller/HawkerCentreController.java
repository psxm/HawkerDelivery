package com.colon3.cz2006.hawkerdelivery.Controller;

import com.colon3.cz2006.hawkerdelivery.DAO.HawkerCentreDAO;
import com.colon3.cz2006.hawkerdelivery.DAO.HawkerCentreDAOImpl;
import com.colon3.cz2006.hawkerdelivery.Entity.HawkerCentre;

import java.util.ArrayList;

/**
 * Created by Michelle on 4/7/2016.
 */
public class HawkerCentreController {
    HawkerCentreDAO hcDAO = new HawkerCentreDAOImpl();

    public HawkerCentreController() {    }

    // hawker centre controller nothing to do haha
    // haha simple control

    public ArrayList<HawkerCentre> allHawkerCentres() {
        ArrayList<HawkerCentre> result = hcDAO.getAllHawkerCentres();
        // no logic to sort
        return result;
    }
}
