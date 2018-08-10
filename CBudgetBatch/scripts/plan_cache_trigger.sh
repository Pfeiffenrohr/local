#!/bin/bash
date >>/home/budget/budget/plan_cache_trigger.log
cd /home/budget/budget
/usr/bin/java -Xms512m  -Xmx1024m /usr/bin/java -classpath /home/budget/budget/server.jar:/home/budget/budget/lib/CbudgetBase-0.0.2-SNAPSHOT.jar:/home/budget/budget/lib/postgresql-42.2.4.jar  cbudgetbatch.BerechnePlanungBatch budget budget budget trigger>> /home/budget/budget/plan_cache_trigger.log
