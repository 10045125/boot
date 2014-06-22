INSERT INTO Account(id, employee_id, name, age, employment_start_date, employment_end_date) VALUES(NULL, 1, 'shinji kagawa', 24, CURRENT_DATE, NULL);
INSERT INTO Account(id, employee_id, name, age, employment_start_date, employment_end_date) VALUES(NULL, 2, 'keisuke honda', 25, CURRENT_DATE, NULL);
INSERT INTO Account(id, employee_id, name, age, employment_start_date, employment_end_date) VALUES(NULL, 3, 'yuto nagatomo', 26, CURRENT_DATE, NULL);
INSERT INTO Account(id, employee_id, name, age, employment_start_date, employment_end_date) VALUES(NULL, 4, 'keigo kawashima', 27, CURRENT_DATE, NULL);

INSERT INTO Team(id, name, description) VALUES(NULL, 'nationals', 'Japan national football team');

INSERT INTO Team_Role(id, team_id, role) VALUES(NULL, 1, 'LEADER');
INSERT INTO Team_Role(id, team_id, role) VALUES(NULL, 1, 'STAFF');

INSERT INTO Team_Member(id, team_id, account_id, role_id, valid_start, valid_end) VALUES(NULL, 1, 1, 1, CURRENT_DATE, NULL);
INSERT INTO Team_Member(id, team_id, account_id, role_id, valid_start, valid_end) VALUES(NULL, 1, 2, 2, CURRENT_DATE, NULL);

INSERT INTO TIME_CARD(id, account_id, on_work, leave) VALUES(NULL, 1, '2014-06-01 09:00:00', '2014-06-01 18:00:00');
INSERT INTO TIME_CARD(id, account_id, on_work, leave) VALUES(NULL, 1, '2014-06-02 09:00:00', '2014-06-02 18:00:00');
INSERT INTO TIME_CARD(id, account_id, on_work, leave) VALUES(NULL, 1, '2014-06-03 09:00:00', '2014-06-03 18:00:00');
INSERT INTO TIME_CARD(id, account_id, on_work, leave) VALUES(NULL, 1, '2014-06-04 09:00:00', '2014-06-04 18:00:00');
INSERT INTO TIME_CARD(id, account_id, on_work, leave) VALUES(NULL, 1, '2014-06-05 09:00:00', '2014-06-05 18:00:00');

INSERT INTO PROJECT (id, customer, description, end, method, name, phase, start) VALUES (NULL, 'ブラックサービス興業', 'きついプロジェクト', null, 'えせagile', '炎上PJ', 'RP', '2011-01-07 00:00:00');