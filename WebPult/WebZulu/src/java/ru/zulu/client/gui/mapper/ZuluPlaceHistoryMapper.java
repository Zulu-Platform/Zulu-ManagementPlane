/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.zulu.client.gui.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import ru.zulu.client.gui.places.*;

/**
 * Регистрация обработчиков хэш-URL’ов.
 * @author Носов А.В.
 */
@WithTokenizers({LoginPlace.Tokenizer.class,
                 SystemsPlace.Tokenizer.class,
                 SystemsSettingsPlace.Tokenizer.class,
                 EthernetListPlace.Tokenizer.class,
                 EthernetEditPlace.Tokenizer.class,
                 UsersGroupsPlace.Tokenizer.class,
                 UsersListPlace.Tokenizer.class,
                 UserEditPlace.Tokenizer.class,
                 ToolsPlace.Tokenizer.class,
                 PageDevPlace.Tokenizer.class})
public interface ZuluPlaceHistoryMapper extends PlaceHistoryMapper {
    
}
