@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile all java files in the vatican package hierarchy
REM Using /s to search subdirectories or listing the specific paths
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\vatican\*.java ..\src\main\java\vatican\command\*.java ..\src\main\java\vatican\data\*.java ..\src\main\java\vatican\task\*.java ..\src\main\java\vatican\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program using the fully qualified name vatican.Vatican
java -classpath ..\bin vatican.Vatican < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT