drop table if exists fb_places_raw;
create table if not exists fb_places_raw(
id int not null auto_increment,
city varchar(26) null,
state varchar(3) null,
search_term varchar(18) null,
page smallint null,
place varchar(36) null,
type varchar(46) null,
about varchar(128) null,
place_url varchar(256) null,
n_like varchar(18) null,
n_talk varchar(18) null,
date_added timestamp default CURRENT_TIMESTAMP,
primary key(id)
);
