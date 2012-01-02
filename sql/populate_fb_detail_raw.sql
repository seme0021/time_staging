load data local infile "/home/mikhail/work/proj/time/places_fb/fb_detail_latest.txt"
into table fb_detail_raw
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
(src_id,src_place,n_like,n_talk,n_here,about,locale,type,url1,url2,url3,place,addr,phone,hours,website_url,src_url)
