insert into user (id, email, nickname, password, status, role) VALUES(1,'qwe@qwe.com', '박영배', '$2a$04$w0CnLUwqOsqhJNbiTqEoDOBSeLNNmaSYA3B.rf4Hbla1snpdAvCeK', null , 'USER');
insert into user (id, email, nickname, password, status, role) VALUES(2,'asd@asd.com', '김창배', '$2a$04$cBRD.UQx5DNrWj9BGpsWeuZquqQZr09It3PupF8vOjENzLg/BBAS.', null , 'ADMIN');

insert into item (id, manager_id, owner_id, description, name, status) VALUES(1, 2, 1, '단단함', '야구방망이', 'PENDING');

insert into reservation (id, item_id, user_id, status) VALUES (1, 1, 1, 'PENDING');