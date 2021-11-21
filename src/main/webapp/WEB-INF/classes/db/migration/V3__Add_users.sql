insert into users (id, username, password, active)
values ('a81bc81b-dead-4e5d-abcd-90865d1e13b1', 'user2', '656565', true),
       ('a81bc81b-dead-4e5d-abde-90865d1e13b1', 'user3', '787878', true),
       ('a81bc81b-dead-4e5d-abc1-90865d1e13b1', 'user4', '     5', true),
       ('a81bc81b-dead-4e5d-abc2-90865d1e13b1', 'user5', '---888', true),
       ('a81bc81b-dead-4e5d-abc3-90865d1e13b1', 'User6', '999u88', true),
       ('a81bc81b-dead-4e5d-abc4-90865d1e13b1', 'useR7', '77777', true);

insert into user_role (user_id, roles)
values ('a81bc81b-dead-4e5d-abcd-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abde-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abc1-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abc2-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abc3-90865d1e13b1', 'USER'),
       ('a81bc81b-dead-4e5d-abc4-90865d1e13b1', 'USER');

-- insert into notes (id, name, access_type, message, user_id)
-- values ('84e9ffb1-5c21-4258-a070-0c28abc5cc78','note1', 'PRIVATE', 'onononceocneone','a81bc81b-dead-4e5d-abde-90865d1e13b1'),
--        ('51f2da5c-ada1-45df-9b3c-33d5401af24d', 'note2', 'PRIVATE', 'aaaaaaaaaaaaaa','b9251a6b-0278-410c-a22e-02d0a2594f3c'),
--        ('d737524a-b1ed-4c8a-bc45-806041572574', 'note3', 'PUBLIC', 'onononceocneone','71db4f85-f95c-409f-8a79-2190de509eb8'),
--        ('7fbac221-6904-4f27-adf8-ec5f10a0f3ae', 'note4', 'PRIVATE', 'o.s.web.servlet.DispatcherServlet','3f04bbd8-e5cc-4968-9495-c2b5230dbb3b'),
--        ('6f45ec48-7529-46bc-ac28-add0eb39e9ff', 'note5', 'PUBLIC', '2021-11-16 15:26:17.019  INFO 5716.521','aac15f04-7576-42f4-abb6-46eb1b56e970'),
--        ('7202e2bc-ee29-474a-9250-34d7632726f5', 'note6', 'PRIVATE', '2021-11-16 15:26:14.786  INFO 5716','1e3a44ae-c8ea-4e7d-9517-dffff830e0dc'),
--        ('234ddac2-a58f-46c1-8bd1-37e2127cfb1b', 'note7', 'PRIVATE', '2021-11-16 15:26:10.433  INFO 5716','1d171b8a-c1d1-4583-a84c-955349333c17');

