INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('client-1', 'secret', 'read,write',
	'password,authorization_code,refresh_token', 'http://localhost:8080/client/', 'ROLE_ADMIN,ROLE_USER', 36000, 36000, '{"name":"Client 1"}', false);
	
	
insert into application_role values(1,'ROLE_ADMIN', 'ADMIN');
insert into application_role values(2,'ROLE_USER', 'USER');

insert into actions values(1,'/employee/fetch/.*/');
insert into actions values(2,'/employee/create');

insert into actions_role_participant values(1,1,1,'NORMAL');
insert into actions_role_participant values(2,1,2,'NORMAL');
insert into actions_role_participant values(3,2,1,'NORMAL');