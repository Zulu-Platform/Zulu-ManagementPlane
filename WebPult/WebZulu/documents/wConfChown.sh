#!/bin/bash
# Добавление тестовых пользователей

ROOT_UID=0      # Только пользователь с $UID 0 имеет привилегии root.
E_NOTROOT=67    # Признак отсутствия root-привилегий.

DIRECTORY=/etc/zulu/
SYSTEMS=${DIRECTORY}systems.properties
USERGROUP=${DIRECTORY}usergroup.properties

if [ "$UID" -ne "$ROOT_UID" ]
then
    echo "Для работы сценария требуются права root."
    exit $E_NOTROOT
fi

chown -R admin /etc/zulu
chown :systems $SYSTEMS
chown :usergroup $USERGROUP

exit 0
#  Возвращаемое значение 0 указывает на успешное завершение работы сценария.