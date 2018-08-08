#!/bin/bash
date >>/home/richard/budget/plan_cache_trigger.log
cd /home/richard/budget
/usr/bin/java -Xms512m  -Xmx1024m -jar plan_cache.jar budget budget budget trigger localhost >> /home/richard/budget/plan_cache_trigger.log
