drop table if exists issue;
drop table if exists issue_voter;
drop table if exists score;
drop table if exists user;

create table issue (id integer not null auto_increment, description varchar(255), title varchar(255), primary key (id));
create table issue_voter (user_id integer not null, issue_id integer not null);
create table score (id integer not null auto_increment, value integer not null, issue_id integer, user_id integer, primary key (id));
create table user (id integer not null auto_increment, password varchar(255), username varchar(255), primary key (id));

alter table issue_voter add constraint FKap03g5o2id32g1tn3vurwp7vl foreign key (issue_id) references issue (id);
alter table issue_voter add constraint FKkkr94o27xroeym1vphp1b3q3q foreign key (user_id) references user (id);
alter table score add constraint FKj4rqh1xf7c4ahj14jdt1qmvk0 foreign key (issue_id) references issue (id);
alter table score add constraint FKq0fd09h1462r0pfum9249o3o7 foreign key (user_id) references user (id);

insert into user (password, username) values ('password','username');
insert into user (password, username) values ('yelsaw','usher');

insert into issue (description, title) values ('feed the cat', 'important stuff');
insert into issue (description, title) values ('heed the cat', 'heed dem cats');
insert into issue (description, title) values ('flyway', 'migrating thy db');
insert into issue (description, title) values ('flyaway', 'fly away');
insert into issue (description, title) values ('array', 'create an array');
