#!/bin/bash
# Чистка пользователей и групп

ROOT_UID=0      # Только пользователь с $UID 0 имеет привилегии root.
E_NOTROOT=67    # Признак отсутствия root-привилегий.

if [ "$UID" -ne "$ROOT_UID" ]
then
    echo "Для работы сценария требуются права root."
    exit $E_NOTROOT
fi

userdel -r admin
userdel -r user01
userdel -r user02
userdel -r user03
userdel -r user04
userdel -r user05

groupdel monitoring
groupdel systems
groupdel interface
groupdel mirroring
groupdel firewall
groupdel nat
groupdel security
groupdel usergroup
groupdel tools

exit 0
#  Возвращаемое значение 0 указывает на успешное завершение работы сценария.