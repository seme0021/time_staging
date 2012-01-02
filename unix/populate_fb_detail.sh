#/bin/bash
dir=/home/mikhail/work/proj/time/places_fb
sql=/home/mikhail/work/proj/time/sql
infile=$1
outfile=fb_detail_latest.txt
mysql_pwd=$2

cd $dir
sed 's/""/\\N/g' $infile > $outfile

mysql -u root -p"$mysql_pwd" -D time_raw < $sql/populate_fb_detail_raw.sql
