package ru.zulu.client.gui.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import ru.zulu.client.gui.activity.LoginActivity;
import ru.zulu.client.gui.activity.PageDevActivity;
import ru.zulu.client.gui.activity.ToolsActivity;
import ru.zulu.client.gui.places.InterfacePlace;
import ru.zulu.client.gui.places.LoginPlace;
import ru.zulu.client.gui.places.PageDevPlace;
import ru.zulu.client.gui.places.SystemPlace;
import ru.zulu.client.gui.places.ToolPlace;
import ru.zulu.client.gui.places.ToolsPlace;
import ru.zulu.client.gui.places.UserGroupPlace;

/**
 * Построение карты рабочей области.
 * @author Носов А.В.
 */
public class MainContentActivityMapper implements ActivityMapper {

    // Variables declaration
    private final String CLASS_NAME = this.getClass().getName();
    
    private final SystemActivityMapper systemActivityMapper;
    private final InterfaceActivityMapper interfaceActivityMapper;
    private final UserGroupActivityMapper userGroupActivityMapper;
    // End of variables declaration
    
    public MainContentActivityMapper() {
        systemActivityMapper = new SystemActivityMapper();
        interfaceActivityMapper = new InterfaceActivityMapper();
        userGroupActivityMapper = new UserGroupActivityMapper();
    }

    @Override
    public Activity getActivity(Place place) {

        if (place instanceof LoginPlace) {
            return new LoginActivity( (LoginPlace)place );
        } else if (place instanceof SystemPlace) {
            return systemActivityMapper.getActivity(place);
        } else if (place instanceof InterfacePlace) {
            return interfaceActivityMapper.getActivity(place);
        } else if (place instanceof UserGroupPlace) {
            return userGroupActivityMapper.getActivity(place);
        } else if (place instanceof ToolsPlace) {
            return new ToolsActivity( (ToolsPlace) place);
        } else if (place instanceof PageDevPlace) {
            return new PageDevActivity((PageDevPlace)place);
        }
        
        return null;
    }
}
