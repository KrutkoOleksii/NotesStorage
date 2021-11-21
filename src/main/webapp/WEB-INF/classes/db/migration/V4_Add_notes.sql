-- insert into users (id, username, password, active)
-- values ('a81bc81b-dead-4e5d-abcd-90865d1e13b1', 'user2', '656565', true),
--        ('a81bc81b-dead-4e5d-abde-90865d1e13b1', 'user3', '787878', true),
--        ('a81bc81b-dead-4e5d-abes-90865d1e13b1', 'user4', '     5', true),
--        ('a81bc81b-dead-4e5d-abui-90865d1e13b1', 'user5', '---888', true),
--        ('a81bc81b-dead-4e5d-abop-90865d1e13b1', 'User6', '999u88', true),
--        ('a81bc81b-dead-4e5d-abpo-90865d1e13b1', 'useR7', '77777', true);

insert into user_role (user_id, roles)
values ('a81bc81b-dead-4e5d-abcd-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abde-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abc2-90865d1e13b1', 'USER');
--        ('a81bc81b-dead-4e5d-abde-90865d1e13b6', 'USER'),
--        ('a81bc81b-dead-4e5d-abde-90865d7e13b1', 'USER');

insert into notes (id, name, access_type, message, user_id)
values ('a81bc81b-dead-4e5d-abc2-90865d1e13b1', 'note1', 'PRIVATE', 'onononceocneone',
        'a81bc81b-dead-4e5d-abff-90865d1e13b1'),
       ('70c527b8-e483-435f-b256-38654a350a47', 'note2', 'PRIVATE', 'aaaaaaaaaaaaaa',
        'a81bc81b-dead-4e5d-abff-90865d1e13b1'),
       ('f7a38913-019a-4f82-8b3b-23bdcefbfd30', 'note3', 'PUBLIC', 'onononceocneone',
        'a81bc81b-dead-4e5d-abff-90865d1e13b1'),
       ('68c47b38-85fd-4071-bbc7-c68ce5cf2cf2', 'note4', 'PRIVATE', 'o.s.web.servlet.DispatcherServlet',
        'a81bc81b-dead-4e5d-abde-90865d1e13b1'),
       ('ce8c25b9-87b4-488c-b2e0-bcd2f2e372ec', 'note5', 'PUBLIC', '2021-11-16 15:26:17.019  INFO 5716',
        'a81bc81b-dead-4e5d-abde-90865d1e13b1'),
       ('07bc68f9-b964-4dc6-9015-a29190922a8d', 'note6', 'PRIVATE', '2021-11-16 15:26:14.786  INFO 5716',
        'a81bc81b-dead-4e5d-abde-90865d1e13b1'),
       ('53e60c10-d94c-400b-bc9c-f34cfceb5e3e', 'note7', 'PRIVATE', '2021-11-16 15:26:10.433  INFO 5716',
        'a81bc81b-dead-4e5d-abde-90865d1e13b1');

