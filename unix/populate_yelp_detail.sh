#/bin/bash
dir=/home/mikhail/work/proj/time/places_fb
sql=/home/mikhail/work/proj/time/sql
infile=$1
outfile=yelp_detail_latest.txt
mysql_pwd=$2

cd $dir
sed 's/""/\\N/g' $infile > $outfile

mysql -u root -p"$mysql_pwd" -D time_raw < $sql/populate_yelp_detail_raw.sql
