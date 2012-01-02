#/bin/bash

dir=/home/mikhail/work/proj/time/raw
outfile=$1
mysql_pwd=$2

mysql -u root -p"$mysql_pwd" -D time_raw -e"
select distinct 
   a.src_id
  ,replace(a.src_place,',','-') as src_place
  ,case when instr(a.url1,'yelp')>1 
      then substring(url1,locate('.com/',url1)+4) 
      when instr(a.url2,'yelp')>0 
      then substring(a.url2,locate('.com/',url2)+4) 
      when instr(a.url3,'yelp')>0 
      then substring(a.url3,locate('.com/',url3)+4) 
   else null end as url 
from fb_detail_raw as a join
     fb_places_raw as b
   on a.src_id = b.id
  where b.city='Minneapolis'
    and (locate('yelp',a.url1)>0 or 
         locate('yelp',a.url2)>0 or
         locate('yelp',a.url3)>0);" | sed 's/\t/,/g' | sed '1d' > $dir/$outfile
