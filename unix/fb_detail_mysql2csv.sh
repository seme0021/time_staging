#/bin/bash

dir=/home/mikhail/work/proj/time/raw
outfile=$1
mysql_pwd=$2

mysql -u root -p"$mysql_pwd" -D time_raw -e"select distinct src_id,src_place,case when instr(url1,'yelp')>1 then url1 when instr(url2,'yelp')>0 then url2 when instr(url3,'yelp')>0 then url3 else null end as url from fb_detail_raw;" | sed 's/\t/,/g' | sed '1d' > $dir/$outfile
