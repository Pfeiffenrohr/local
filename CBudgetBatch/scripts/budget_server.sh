#!/bin/bash
cd /home/budget/budget
/usr/bin/java -classpath /home/budget/budget/server.jar  cbudgetbatch/Server  budget budget budget >>/home/budget/budget/log.txt 2>&1
