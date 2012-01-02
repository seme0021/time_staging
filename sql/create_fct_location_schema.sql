drop table if exists fct_location;
create table if not exists fct_location(
   id int not null auto_increment,
   zip varchar(5) null,
   city varchar(36) null,
   state varchar(3) null,
   date_updated timestamp default current_timestamp,
   primary key(id)
);
