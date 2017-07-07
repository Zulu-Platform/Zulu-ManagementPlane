#!/bin/bash
# Добавление групп пользователей для системы Zulu

ROOT_UID=0      # Только пользователь с $UID 0 имеет привилегии root.
E_NOTROOT=67    # Признак отсутствия root-привилегий.

USERNAME=admin  # Главный администратор
PASS=admin      # Пароль главного администратора

if [ "$UID" -ne "$ROOT_UID" ]
then
    echo "Для работы сценария требуются права root."
    exit $E_NOTROOT
fi

groupadd -g 511 monitoring
groupadd -g 512 systems
groupadd -g 513 interface
groupadd -g 514 mirroring
groupadd -g 515 firewall
groupadd -g 516 nat
groupadd -g 517 security
groupadd -g 518 usergroup
groupadd -g 519 tools

# -u Заданый UID
# -g Задает GID
# -G список групп
# -N без групп
# -M без домашней директории
# -c комментарий
# -s /sbin/nologin (Командная оболочка) (В данном случае ее отсутствие)
# -e 2015-03-27 Срок действия аккаунта
useradd -u 1005 -g 511 -G monitoring,systems,interface,mirroring,firewall,nat,security,usergroup,tools -M -c "Administrator" -p $(openssl passwd -1 $PASS) $USERNAME

exit 0
#  Возвращаемое значение 0 указывает на успешное завершение работы сценария.