#!/bin/bash
# Создание файла настроек systems.properties

ROOT_UID=0      # Только пользователь с $UID 0 имеет привилегии root.
E_NOTROOT=67    # Признак отсутствия root-привилегий.

#DIRECTORY=/home/nosov/Public/Test/
DIRECTORY=/etc/zulu/
FILENAME=${DIRECTORY}systems.properties

if [ "$UID" -ne "$ROOT_UID" ]
then
    echo "Для работы сценария требуются права root."
    exit $E_NOTROOT
fi

if ! [ -d "$DIRECTORY" ]; then
    mkdir $DIRECTORY
fi

cp /dev/null $FILENAME

echo "hostName=" >> $FILENAME                   # Имя
echo "productID=ZU-010814-A" >> $FILENAME       # Идентификатор продукта
echo "serialNumber=1122334455" >> $FILENAME     # Серийный номер
echo "software=0.0.0.1" >> $FILENAME            # Версия ПО
echo "contact=" >> $FILENAME                    # Контакт
echo "location=" >> $FILENAME                   # Местонахждение

exit 0
#  Возвращаемое значение 0 указывает на успешное завершение работы сценария.