drop table if exists fct_places;
create table if not exists fct_places(
   id int not null auto_increment,
   src_id int not null,
   name varchar(128) not null,
   type varchar(64) not null,
   category varchar(62) null,
   addr1 varchar(128) null,
   addr2 varchar(64) null,
   city varchar(64) null,
   state varchar(3) null,
   zip varchar(5) null,
   url varchar(256) null,
   fb_url varchar(256) null,
   yelp_url varchar(256) null,
   fsquare_url varchar(256) null,
   date_added date null,
   date_updated timestamp default current_timestamp,
   primary key(id)
);
