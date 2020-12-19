#NoEnv  ; Recommended for performance and compatibility with future AutoHotkey releases.
; #Warn  ; Enable warnings to assist with detecting common errors.
SendMode Input  ; Recommended for new scripts due to its superior speed and reliability.
SetWorkingDir %A_ScriptDir%  ; Ensures a consistent starting directory.

Kotlin Related Code

::kt_show::
MsgBox, Running Command Line: `nkt-cmd+jar : Create java bytecode; create jar file `nkt-cmd-runkt : Run Kotlin file using Kotlin tool `nkt-cmd-runjv : Run Kotlin file using Java tool `nkt-cmd-runjar : Run Kotlin file using jar
return

::kt-cmd+jar::kotlinc-jvm kotlinFileName.kt -d javaFileName.jar

::kt-cmd-runkt::kotlin -classpath javaFileName.jar kotlinFileNameKt

::kt-cmd-runjar::java -jar javaFileName.jar

::kt-cmd-runjv::java -classpath javaFileName.jar kotlinFileNameKt