drop table if exists tmp_exist;
create table if not exists tmp_exist(
   src_id int not null
);


insert into tmp_exist(src_id)
   select distinct src_id 
     from fct_places
;

insert into fct_places(src_id,name,type,category,addr1,addr2,city,state,zip,url,fb_url,yelp_url,fsquare_url,date_added)
   select 
      a.id as src_id
     ,a.place as name
     ,a.type as type
     ,'Restaurant' as category
     ,c.addr1
     ,'' as addr2
     ,c.addr2 as city
     ,c.addr3 as state
     ,c.addr4 as zip
     ,'' as url
     ,b.src_url as fb_url
     ,case when locate('yelp',b.url1)>0 
        then b.url1
        when locate('yelp',b.url2)>0 
        then b.url2
        when locate('yelp',b.url3)>0
        then b.url3
        else null 
     end as yelp_url
     ,'' as fsquare_url
     ,curdate() as date_added 
   from time_raw.fb_places_raw as a join 
        time_raw.fb_detail_raw as b  
     on a.id = b.src_id join 
        time_raw.yelp_detail_raw as c 
     on a.id = c.src_id
   where a.id not in(select src_id from tmp_exist)
;

drop table if exists tmp_exist;
