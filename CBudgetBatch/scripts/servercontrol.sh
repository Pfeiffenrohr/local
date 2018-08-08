#!/bin/bash
ps aux |grep budget_server|grep -v grep >/dev/null
if [ $? -ne 0 ]; then
host=`hostname`
echo "start server on $host ..." >> /storage-pool/budget/log.txt
/home/richard/budget/budget_server.sh &
fi
