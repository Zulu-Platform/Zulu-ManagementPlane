#!/bin/bash
# Создание файла настроек systems.properties

ROOT_UID=0      # Только пользователь с $UID 0 имеет привилегии root.
E_NOTROOT=67    # Признак отсутствия root-привилегий.

#DIRECTORY=/home/nosov/Public/Test/
DIRECTORY=/etc/zulu/
FILENAME=${DIRECTORY}usergroup.properties

if [ "$UID" -ne "$ROOT_UID" ]
then
    echo "Для работы сценария требуются права root."
    exit $E_NOTROOT
fi

if ! [ -d "$DIRECTORY" ]; then
    mkdir $DIRECTORY
fi

cp /dev/null $FILENAME

echo "group.default=monitoring" >> $FILENAME    # Имя
echo "groups=systems,interface,mirroring,firewall,nat,security,usergroup,tools" >> $FILENAME    # 

exit 0
#  Возвращаемое значение 0 указывает на успешное завершение работы сценария.