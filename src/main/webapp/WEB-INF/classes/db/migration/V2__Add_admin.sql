insert into users (id, username, password, active)
    values ('a81bc81b-dead-4e5d-abff-90865d1e13b1', 'admin', '123', true);

insert into user_role (user_id, roles)
    values ('a81bc81b-dead-4e5d-abff-90865d1e13b1', 'USER'), ('a81bc81b-dead-4e5d-abff-90865d1e13b1', 'ADMIN');