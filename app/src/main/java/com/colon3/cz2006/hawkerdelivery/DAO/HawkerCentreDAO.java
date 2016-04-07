package com.colon3.cz2006.hawkerdelivery.DAO;

import com.colon3.cz2006.hawkerdelivery.Entity.HawkerCentre;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michelle on 4/7/2016.
 */
public interface HawkerCentreDAO {
    ArrayList<HawkerCentre> getHawkerCentresByDistance();
    ArrayList<HawkerCentre> getAllHawkerCentres(); // if distance one works, delete this
    HawkerCentre getHawkerCentreByName(String name);
}
