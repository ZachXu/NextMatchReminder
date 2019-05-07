SERVICE_HOME=$(dirname $(dirname $(readlink -f $0)))

export SERVICE_LOG_PATH=$SERVICE_HOME/logs

CLASSPATH=$CLASSPATH:$SERVICE_HOME/lib/*

START_CLASS=de.zachxu.nextmatchreminder.webservice.StartNextMatchReminderWebService
START_ARGS="-dburl=jdbc:derby://localhost:1527/$SERVICE_HOME/database/NMR -port=8080"

java -Dlog4j.configurationFile=$SERVICE_HOME/bin/log4j2.xml $START_CLASS $START_ARGS
