#!/usr/bin/env bash

today=`date +%Y-%m-%d`
echo ${today}

log_file_path=/data/logs/starfish-notify-server.${today}.log
echo ${log_file_path}

line_number=${1}
if [[ ${line_number} != '' ]]; then
   tail -${line_number} ${log_file_path}
else
   tail -f ${log_file_path}
fi