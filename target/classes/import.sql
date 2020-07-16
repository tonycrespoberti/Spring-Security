INSERT INTO USER (user_id, name, password, email, enabled) VALUES (1, 'Vicky Andersen', '$2a$10$vCovGPel/M3R9WknY7BLk.p0pxC34PFrWZA8xf..4o931g8PZw06O', 'vicky@yahoo.com', TRUE);
INSERT INTO USER (user_id, name, password, email, enabled) VALUES (2, 'Wolfand Heur', '$2a$10$.FWSGOtGTUG6rUXEZXk9W.rD9aA/.Q93crnVGsM4Bm1DrBD.i08dy', 'wheur@outlook.com', TRUE);
INSERT INTO USER (user_id, name, password, email, enabled) VALUES (3, 'Stefhan Heinz',	'$2a$10$Fm8l7UKWc.zOqXBVIY64duWhuBfSkvUa8QBpRvCB/bNsR5Asy9lIW', 'jhondoe@facebook.com', TRUE);

INSERT INTO ROLE (role_id, rol_name) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE (role_id, rol_name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_ROLES (users_user_id, roles_role_id) VALUES (1,1);
INSERT INTO USER_ROLES (users_user_id, roles_role_id) VALUES (2,1);
INSERT INTO USER_ROLES (users_user_id, roles_role_id) VALUES (2,2);