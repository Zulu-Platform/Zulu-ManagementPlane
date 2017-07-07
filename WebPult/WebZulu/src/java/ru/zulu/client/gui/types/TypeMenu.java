package ru.zulu.client.gui.types;

/**
 * Типы меню.
 * @author Носов А.В.
 */
public enum TypeMenu {

    // Variables declaration
    Monitoring(1000, "monitoring", "Телеметрия"),
        MonitoringStatusPorts(1100, "statusports", "Статусы портов"),
        MonitoringStatisticsPorts(1200, "statisticsports", "Статистика портов"),
        MonitoringUsePorts(1300, "useports", "Использование портов"),
        MonitoringSwitchHealth(1400, "switchhealth", "Железо"),
        MonitoringLogs(1500, "logs", "Системный журнал"),
    /** Системные. */
    Systems(2000, "systems", "Система"),
        SystemsSettings(2100, "settings", "Системные настройки"),
        SystemsInstallation(2200, "installation", "Экспресс установка"),
        SystemsLogs(2300, "logs", "Системный журнал"),
        SystemsSNTP(2400, "sntp", "Настройка времени"),
    Interface(3000, "interface", "Интерфейсы"),
        InterfaceEthernet(3100, "ethernet", "Ethernet"),
            InterfaceEthernetList(3110, "ethernet-list", "Таблица Ethernet интерфейсов"),
            InterfaceEthernetAdd(3120, "ethernet-add", "Добавить Ethernet интерфейс"),
            InterfaceEthernetEdit(3130, "ethernet-edit", "Редактировать Ethernet интерфейс"),
        InterfaceTCP(3200, "tcp", "TCP"),
        InterfaceIPv4(3300, "ipv4", "IPv4"),
        InterfaceToS(3400, "tos", "ToS"),
        InterfaceMPLS(3500, "mpls", "MPLS"),
        InterfaceBGP4(3600, "bgp4", "BGP-4"),
        InterfaceVLAN(3700, "vlan", "VLAN"),
            InterfaceVLANList(3710, "vlan-list", "Таблица VLAN"),
            InterfaceVLANAdd(3720, "vlan-add", "Добавить VLAN"),
            InterfaceVLANEdit(3730, "vlan-edit", "Редактированть VLAN"),
    Mirroring(4000, "mirroring", "Зеркалирование"),
    Firewall(5000, "firewall", "Межсетевой экран"),
        FirewallICMP(5100, "icmp", "ICMP"),
    NAT(6000, "nat", "NAT"),
    Security(7000, "security", "Безопастность"),
        SecurityTrustedHost(7100, "trustedhost", "Доверенный хост"),
        SecurityPorts(7200, "ports", "Безопастность портов"),
        SecurityTelnet(7300, "telnet", "Telnet"),
        SecurityARP(7400, "arp", "ARP"),
            SecurityARPList(7410, "arp-list", "Таблица ARP"),
            SecurityARPAdd(7420, "arp-add", "Добавить ARP"),
            SecurityARPEdit(7430, "arp-edit", "Редактировать ARP"),
        SecurityMAC(7500, "mac", "Таблица MAC"),
        SecurityIP(7600, "ip", "IP группы"),
    /** Пользователи. */
    UserGroup(8000, "usergroup", "Пользователи"),
        /** Пользователи. */
        Users(8100, "users", "Пользователи"),
            UserList(8110, "user-list", "Таблица пользователей"),
            UserAdd(8120, "user-add", "Добавление пользователя"),
            UserEdit(8130, "user-edit", "Редактирование пользователя"),
        /** Группы и разрешения. Policies. */
        Groups(8200, "groups", "Группы и разрешения"),
            GroupList(8210, "group-list", "Таблица групп"),
            GroupAdd(8220, "group-add", "Добавление группы"),
            GroupEdit(8230, "group-edit", "Редактирование группы"),
    Tools(9000, "tools", "Инструменты"),
        ToolsReboot(9100, "reboot", "Перезапуск"),
        ToolsReset(9200, "reset", "Сброс"),
        ToolsBackup(9300, "backup", "Восстановление и резервное копирование настроек"),
        ToolsUpdate(9400, "update", "Обновление и резервное копирование ПО"),
    Help(10000, "help", "Сетевой помошник"),


    // Верхнее меню
    /** Выход. */
    Exit(100, "exit", "Выход"),

    /** Маршрутизация. */
    Routing(1, "routing", "Маршрутизация"),
    /** Правила. */
    Rules(2, "rules", "Правила");

    /** Код. */
    private final int code;
    /** Имя. */
    private final String name;
    /** Описание. */
    private final String description;
    // End of variables declaration

    /**
     * Инициализация ошибки.
     * @param description описание ошибки
     */
    TypeMenu(int code, String name, String description) {
        this.code = code;
        this.name = name;
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
     * Возвращает имя меню.
     * @return имя меню
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание меню.
     * @return описание меню
     */
    public String getDescription() {
        return description;
    }
}
