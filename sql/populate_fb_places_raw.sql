load data local infile "/home/mikhail/work/proj/time/places_fb/fb_places_latest.txt"
into table fb_places_raw
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
(city,state,search_term,place,type,about,place_url,n_like,n_talk)
