package libMessage.client.types;

/**
 * Типы сообщений внутреннего протокола.
 * @author Носов А.В.
 */
public enum TypeMessage {
    
//    /** 
//     * Запрос информации о пользователе.
//     */
//    RqUserById,
//    
//    /** Информации о пользователе. */
//    RxUserById,
    
    /** 
     * Запрос информации о пользователе.
     */
    RqUserByName,
    
    /** Информации о пользователе. */
    RxUserByName,
    
    /** Запрос информации о группе. */
    RqGroupById,
    
    /** Информации о группе. */
    RxGroupById,
    
    /** Запрос групп пользователя. */
    RqGroupByUser,
    
    /** Разрешения групп. */
    RxGroupByUser,
    
    /** Запрос списка групп. */
    RqGroups,
    
    /** Список групп. */
    RxGroups,
    
    /** Добавить новую группу. */
    GroupAdd,
    
    /** Редактировать группу. */
    GroupEdit,
    
    /** Удалить список групп. */
    GroupsRemove,
    
    /** Удалить группу. */
    GroupRemove,
    
    /** Запрос списка пользователей. */
    RqUsers,
    
    /** Список пользоватлей. */
    RxUsers,
    
    /** Добавить нового пользователя. */
    UserAdd,
    
    /** Редактировать пользователя. */
    UserEdit,
    
    /** Удалить список пользователей. */
    UsersRemove,
    
    /** Удалить пользователя. */
    UserRemove,
    
    /** Сохранить и применить все исправления. */
    SaveActivate,
    
    /** Отменить все исправления. */
    DiscardChanges,
    
    /** Сообщение ошибки. */
    Error,
    
    /** Сообщение успеха. */
    Success,
    
    /** Информационное сообщение. */
    Info,
    
    /** Список системных сообщений. */
    Systems,
    
    /** Вход в систему (авторизация). */
    Login,
    
    /** Выход из системы. */
    Logout,
    
    /** Информация о пользователе. */
    LoginInfo,
    
    /** Новая сессия. */
    NewSession,
    
    /** Проверка логина. */
    isLoginName,
    
    /** Запрос системных настроек. */
    RqSystemsSettings,
    
    /** Информация о системных настройках. */
    RxSystemsSettings,
    
    /** Редактировать системные настройки. */
    SystemsSettingsEdit,
    
    /** Запрос списка ethernet портов. */
    RqEthernets,
    
    /** Список ethernet портов. */
    RxEthernets,
    
    /** Добавить новsq ethernet порт. */
    EthernetAdd,
    
    /** Редактировать ethernet порт. */
    EthernetEdit,
    
    /** Редактировать список ethernet портов. */
    EthernetsEdit,
    
    /** Удалить список ethernet портов. */
    EthernetsRemove,
    
    /** Удалить ethernet порт. */
    EthernetRemove
}
