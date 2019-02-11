SERVICE_HOME=$(dirname $(pwd))

CLASSPATH=$CLASSPATH:$SERVICE_HOME/lib/*

START_CLASS=de.zachxu.nextmatchreminder.webservice.StartNextMatchReminderWebService
START_ARGS="-dburl=jdbc:derby:$SERVICE_HOME/database/NMR -port=8080"

java $START_CLASS $START_ARGS
