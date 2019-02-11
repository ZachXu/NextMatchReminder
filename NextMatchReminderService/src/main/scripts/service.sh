#!/bin/sh
SERVICE_NAME=NextMatchService
SERVICE_HOME=$(dirname $(pwd))
PID_PATH_NAME=/tmp/$SERVICE_NAME-pid
CLASSPATH=$CLASSPATH:$SERVICE_HOME/lib/*
LOG_DIR=$SERVICE_HOME/logs

echo $CLASSPATH
echo $PID_PATH_NAME

START_CLASS=de.zachxu.nextmatchreminder.webservice.StartNextMatchReminderWebService
START_ARGS="-dburl=jdbc:derby:$SERVICE_HOME/database/NMR -port=8080"

case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java $START_CLASS $START_ARGS >> $SERVICE_NAME.out 2>&1&
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
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
            nohup java -classpath $SERVICE_HOME/lib/*.jar $START_CLASS $START_ARG /tmp 2>> /dev/null >> /dev/null &
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac 
