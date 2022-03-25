create table roles
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);

create table users
(
    id       bigserial    not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

create table user_role
(
    userid bigserial not null,
    roleid bigserial not null
);

alter table if exists roles
    add constraint UK_unique_rolename unique (name);

alter table if exists user_role
    add constraint UK_unique_pair unique (userid, roleid);

alter table if exists users
    add constraint UK_unique_username unique (username);

alter table if exists user_role
    add constraint FK_userrole_role foreign key (roleid) references roles;

alter table if exists user_role
    add constraint FK_userrole_user foreign key (userid) references users;

insert into roles(id, name)
values (1, 'USER');

insert into roles(id, name)
values (2, 'ADMIN');

insert into users(id, username, password)
values (1, 'user', '$2y$10$hvFxDDzbba4zbyI7UuEDm.ZZtyWeq7V3MFaxUJF707Av.DvwwV91y');

insert into user_role(userid, roleid)
values (1, 1);

insert into users(id, username, password)
values (2, 'admin', '$2y$10$FtrYAIs6RD89NWFfgXa9e.L3PpSYjXp.IE4RZrjPlagDUsRgiFEQO');

insert into user_role(userid, roleid)
values (2, 2);

insert into users(id, username, password)
values (3, 'superuser', '$2y$10$u/v6amJm5ZV5ZOY4i0EH7ONkOcD80bkSNmCcmZk3yqOTSRR26R/32');

insert into user_role(userid, roleid)
values (3, 1);

insert into user_role(userid, roleid)
values (3, 2);

create or replace function set_def_role()
    returns trigger
    language PLPGSQL
as
$$
begin
    insert into user_role(userid, roleid)
    values (NEW.id, 1);
    return NEW;
end;
$$;

create trigger set_def_role_trigger
    after insert
    on users
    for each row
execute procedure set_def_role();
