INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES ('ROLE_MODERATOR');

--ADMIN
INSERT INTO users(id, first_name, last_name, username, email, password)
VALUES(30, 'admin', 'admin', 'admin', 'admin@admin.nl', '$2a$10$pjYTOZyDXr6mVw5bapgCv.eejcnRMe6GRBsdScPeoJmbKpXPu895u');
INSERT INTO user_role(user_id, role_id) VALUES(30, 1);

--USER
INSERT INTO users(id, first_name, last_name, username, email, password)
VALUES(1, 'Jeffrey', 'Leeuw', 'Jeff', 'jeff@app.nl', '$2a$10$pjYTOZyDXr6mVw5bapgCv.eejcnRMe6GRBsdScPeoJmbKpXPu895u');
INSERT INTO user_role(user_id, role_id) VALUES(1, 2);

INSERT INTO users(id, first_name, last_name, username, email, password)
VALUES(2, 'Piet', 'Janssen', 'Piet', 'piet@app.nl', '$2a$10$pjYTOZyDXr6mVw5bapgCv.eejcnRMe6GRBsdScPeoJmbKpXPu895u');
INSERT INTO user_role(user_id, role_id) VALUES(2, 2);
