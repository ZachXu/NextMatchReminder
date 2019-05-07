#!/bin/sh

### BEGIN INIT INFO
# Provides:          scriptname
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Start daemon at boot time
# Description:       Enable service provided by daemon.
### END INIT INFO


SERVICE_NAME=NextMatchService
SERVICE_HOME=$(dirname $(dirname $(readlink -f $0)))
PID_PATH_NAME=/tmp/$SERVICE_NAME-pid
CLASSPATH=$CLASSPATH:$SERVICE_HOME/lib/*

export SERVICE_LOG_PATH=$SERVICE_HOME/logs

echo $SERVICE_HOME
echo $CLASSPATH
echo $PID_PATH_NAME

START_CLASS=de.zachxu.nextmatchreminder.webservice.StartNextMatchReminderWebService
START_ARGS="-dburl=jdbc:derby://127.0.0.1:1527/$SERVICE_HOME/database/NMR -port=8080"

case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -Dlog4j.configurationFile=$SERVICE_HOME/bin/log4j2.xml $START_CLASS $START_ARGS >> /dev/null 2>&1&
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
	    echo $PID
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            rm -f $LOG_DIR/$SERVICE_NAME.out;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -Dlog4j.configurationFile=$SERVICE_HOME/bin/log4j2.xml $START_CLASS $START_ARGS >> /dev/null &
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac 
