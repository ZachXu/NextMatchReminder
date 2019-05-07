set SCRIPT_DIR=%~dp0
for %%I in ("%SCRIPT_DIR%..") do set SERVICE_HOME=%%~dpfI
cd %SERVICE_HOME%

set classpath=%classpath%;%SERVICE_HOME%/lib/*

set START_CLASS=de.zachxu.nextmatchreminder.webservice.StartNextMatchReminderWebService

java -Dlog4j.configurationFile=%SERVICE_HOME%/bin/log4j2.xml %START_CLASS% -dburl=jdbc:derby://localhost:1527/%SERVICE_HOME%/database/NMR -port=8080

pause

