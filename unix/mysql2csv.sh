#/bin/bash
dir=/home/mikhail/work/proj/time/raw
outfile=$1
mysql_pwd=$2

mysql -u root -p"$mysql_pwd" -D time_raw -e"select id,substring(place_url,locate('.com/',place_url)+4) as url,place from fb_places_raw where city='Santa Monica';" | sed 's/\t/,/g' | sed '1d' > $dir/$outfile
