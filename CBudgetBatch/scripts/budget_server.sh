#!/bin/bash
#/usr/bin/java -jar /share/daten/austausch/budget_batch.jar budget budget budget_test /share/daten/austausch/budgetTXT.txt
/usr/bin/java -jar /home/richard/budget/server.jar budget budget budget localhost >>/storage-pool/budget/log.txt 2>&1
