package ru.zulu.client.gui.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.zulu.client.gui.activitys.AAAActivity;
import ru.zulu.client.gui.places.AAAPlace;
import ru.zulu.client.gui.places.MainMenuPlace;

/**
 * Построение карты рабочей области.
 * @author Носов А.В.
 */
public class MainContentActivityMapper implements ActivityMapper {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private final MainActivityMapper mainActivityMapper;
    // End of variables declaration
    
    public MainContentActivityMapper() {
        mainActivityMapper = new MainActivityMapper();
    }

    @Override
    public Activity getActivity(Place place) {
        
        if (place instanceof AAAPlace)
            return new AAAActivity( (AAAPlace) place );
        else if (place instanceof MainMenuPlace) {
            return mainActivityMapper.getActivity(place);
        }
//        else if (place instanceof LoginPlace)
//            return new LoginActivity( (LoginPlace) place );
//        

        return null;
    }
}
