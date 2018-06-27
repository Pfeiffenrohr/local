#!/bin/bash

##############################################
# Script to restart BW-Engine after a Heap Size Error is found
# Author:	Richard Lechner
# Date: 	23.22.2017
###############################################

#Debuging
debug=1

# Directory where the logfiles are
Logfilepath=/var/applications/tibco/DOMAIN/tra/ESB-TST-C20/application/logs

# Java Location
JavaCmd=/applications/tibco/tibcojre64/1.7.0/bin/java

#If Logfile older than maxTime seconds, no action takes place
maxTime=3600 
#maxTime=46000 

#Store for last restarts
restartlog=/home/tibcorun/bin/restartDir 

if [ ! -d $restartlog ]; then
	[ debug ] && [  `echo "mkdir -r $restartlog"` ]
	mkdir -p $restartlog
fi
oPWD=$PWD
IFS="
"
cd $Logfilepath
year2=`date +%Y`
month2=`date +%b`
day2=`date +%d`

hour2=`date +%H`
minute2=`date +%M`
second2=`date +%S`

for dat in `ls *.log`
do
		[ $debug ] && [ `echo "Working with $dat"` ]
		for line in `grep -i "BWENGINE-100001 Java heap" $dat`
		do
			 [ $debug ] && [ `echo $line` ]
			time=`echo $line |cut -f 1-4 -d " "`		
			[ $debug ] && [ `echo $time` ]
			year1=`echo $time |cut -f 1 -d " "`
			month1=`echo $time |cut -f 2 -d " "`
			day1=`echo $time |cut -f 3 -d " "`
			hour1=`echo $time |cut -f 4 -d " " |cut -f 1 -d ":"`
			minute1=`echo $time |cut -f 4 -d " " |cut -f 2 -d ":"`
			second1=`echo $time |cut -f 4 -d " " |cut -f 3 -d ":"`
			  [ $debug ] && [ `echo "$year1 $month1 $day1 $hour1 $minute1 $second1"` ]
 		        delta=`$JavaCmd -jar /home/tibcorun/bin/timecompare.jar $year1 $month1 $day1 $hour1 $minute1 $second1 $year2 $month2 $day2 $hour2 $minute2 $second2`
		[ $debug ] && [ `echo "Delta = $delta"` ]
		if [ $delta -lt $maxTime ]; then
			timestamp=$year2:$month2:$day2:$hour2:$minute2:$second2 
			#Look, if this Service was restartet
			HOST=`hostname |head -1`
			if [ ! -f  $restartlog/$dat ]; then
				echo restart bwengine $dat
				anz=`echo -n $dat | sed -e 's/[^-]//g' | wc -m`
				anz=`echo -e $anz |tr -d ' '`
				echo "Anzahl der - ist $anz"
				div=`expr $anz / 2` 
				service=`echo $dat | cut -f -$div  -d "-"`
				echo "Service = $service"
				MAILFROM=lechnerri@media-saturn.com
				MAILREPLYTO="lechnerri@media-saturn.com"
				MAILTO=lechnerri@media-saturn.com
				body="Found HeapSizeError and restart $service"
				subject="$HOST Restart BW-Engine"
				/usr/sbin/sendmail "$MAILTO" <<EOF
From: $MAILFROM
Reply-To: $MAILREPLYTO
To: $MAILTO
Subject: $subject
$body
EOF

  
				 [ debug ] && [  `echo restart bwengine $service` ] 
				/var/applications/tibco/scripts/bwManage.sh stop $service
				sleep 5	
				/var/applications/tibco/scripts/bwManage.sh start $service
				echo "$timestamp;1" >$restartlog/$dat
			else
				timestampLog=`cat $restartlog/$dat |cut -f 1 -d ";"`
				count=`cat $restartlog/$dat |cut -f 2 -d ";"`
				#echo count=$count
				#echo timestampLog= $timestampLog
				 #Look if Timestamp is to new
                                year1=`echo $timestampLog |cut -f 1 -d ": "`
                                month1=`echo $timestampLog |cut -f 2 -d ":"`
                                day1=`echo $timestampLog |cut -f 3 -d ":"`
                                hour1=`echo $timestampLog |cut -f 4 -d ":"`
                                minute1=`echo $timestampLog |cut -f 5 -d ":"`
                                second1=`echo $timestampLog |cut -f 4 -d ":"`
                                #echo "$year1 $month1 $day1 $hour1 $minute1 $second1         $year2 $month2 $day2 $hour2 $minute2 $second2"
				delta2=`$JavaCmd -jar /home/tibcorun/bin/timecompare.jar  $year1 $month1 $day1 $hour1 $minute1 $second1 $year2 $month2 $day2 $hour2 $minute2 $second2`
                                #echo delta2=$delta2
				if [ $delta -lt 0 ]; then
					if [ $count -gt 2 ]; then
						if [ $count -eq 3 ]; then
							echo "send mail"
						fi
					fi
				else 
					[ debug ] && [ `echo rm -f $restartlog/$dat` ]
					continue
				fi
				count=`expr $count + 1`
				echo "$timestamp;$count" >$restartlog/$dat	
			fi
		fi
		done
done
cd $oPWD
