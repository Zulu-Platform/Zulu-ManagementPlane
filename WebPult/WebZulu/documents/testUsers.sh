#!/bin/bash
# Добавление тестовых пользователей

ROOT_UID=0      # Только пользователь с $UID 0 имеет привилегии root.
E_NOTROOT=67    # Признак отсутствия root-привилегий.

if [ "$UID" -ne "$ROOT_UID" ]
then
    echo "Для работы сценария требуются права root."
    exit $E_NOTROOT
fi

useradd -G monitoring,systems,interface -M -c "user 01" -p $(openssl passwd -1 user01) user01
useradd -G monitoring,mirroring,firewall,nat,security -M -c "user 02" -p $(openssl passwd -1 user02) user02
useradd -G monitoring,security,usergroup,tools -M -c "user 03" -p $(openssl passwd -1 user03) user03
useradd -G monitoring,systems,interface,usergroup,tools -M -c "user 04" -p $(openssl passwd -1 user04) user04
useradd -G monitoring,interface,mirroring,security,usergroup -M -c "user 05" -p $(openssl passwd -1 user05) user05

exit 0
#  Возвращаемое значение 0 указывает на успешное завершение работы сценария.