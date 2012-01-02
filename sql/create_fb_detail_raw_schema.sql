drop table if exists fb_detail_raw;
create table if not exists fb_detail_raw(
   id int not null auto_increment,
   src_id int null,
   src_place varchar(128) null,
   n_like int null,
   n_talk int null,
   n_here int null,
   about varchar(256) null,
   locale varchar(128) null,
   type varchar(164) null,
   url1 varchar(664) null,
   url2 varchar(664) null,
   url3 varchar(664) null,
   place varchar(128) null,
   addr varchar(128) null,
   phone varchar(18) null,
   hours varchar(256) null,
   website_url varchar(664) null,
   src_url varchar(664) null,
   date_added timestamp default CURRENT_TIMESTAMP,
   primary key(id)
);
