create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user binary(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
create table game (id bigint generated by default as identity, score double, player_id bigint, primary key (id))
create table player (id bigint generated by default as identity, name varchar(255) not null, user_id varchar(255), primary key (id))
create table user (uid varchar(255) not null, email varchar(255), is_email_verified boolean not null, issuer varchar(255), name varchar(255), picture varchar(255), primary key (uid))
create index game_index on game (score desc)
alter table game add constraint FK69kxn13hw2qili6x6em4ur6kd foreign key (player_id) references player
