package ru.zulu.client.gui.types;

/**
 * Типы меню.
 * @author Носов А.В.
 */
public enum TypeMenu {
    
    // Variables declaration
    Main(1000, "Zulu Project"),
        MainWho(1100, "Who's who"),
        MainThanks(1200, "Thanks"),
        MainTeam(1300, "Team"),
    Resources(2000, "Resources"),
        ResourcesAbout(2100, "Wiki"),
        ResourcesScreenshots(2200, "Screenshots"),
    Download(3000, "Download"),
    News(4000, "News");
    
    /** Код. */
    private final int code;
    /** Описание. */
    private final String description;
    // End of variables declaration
    
    /**
     * Инициализация ошибки.
     * @param description описание ошибки
     */
    TypeMenu(int code, String description) {
        this.code = code;
        this.description = description;
    }
    
    /**
     * Возвращает код меню.
     * @return код меню
     */
    public int getCode() {
        return code;
    }

    /**
     * Возвращает описание меню.
     * @return описание меню
     */
    public String getDescription() {
        return description;
    }
}
