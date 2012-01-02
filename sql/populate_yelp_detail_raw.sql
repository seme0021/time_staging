load data local infile "/home/mikhail/work/proj/time/places_fb/yelp_detail_latest.txt"
into table yelp_detail_raw
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
(src_id,src_place,src_url,category,place,addr1,addr2,addr3,addr4,addr5,phone,price,nrate,url,c_array1,d_array1,c_array2,d_array2,c_array3,d_array3)
