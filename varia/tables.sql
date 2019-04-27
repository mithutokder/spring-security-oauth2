create table oauth_client_details (
  client_id character varying(255) PRIMARY KEY,
  resource_ids character varying(255),
  client_secret character varying(255),
  scope character varying(255),
  authorized_grant_types character varying(255),
  web_server_redirect_uri character varying(255),
  authorities character varying(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information character varying(4096),
  autoapprove character varying(255)
);

create table  oauth_client_token (
  token_id character varying(255),
  token BYTEA,
  authentication_id character varying(255) PRIMARY KEY,
  user_name character varying(255),
  client_id character varying(255)
);

create table  oauth_access_token (
  token_id character varying(255),
  token BYTEA,
  authentication_id character varying(255) PRIMARY KEY,
  user_name character varying(255),
  client_id character varying(255),
  authentication BYTEA,
  refresh_token character varying(255)
);

create table  oauth_refresh_token (
  token_id character varying(255),
  token BYTEA,
  authentication BYTEA
);

create table oauth_code (
  code character varying(255), authentication BYTEA
);

create table  oauth_approvals (
	userId character varying(255),
	clientId character varying(255),
	scope character varying(255),
	status character varying(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

create table  ClientDetails (
  appId character varying(255) PRIMARY KEY,
  resourceIds character varying(255),
  appSecret character varying(255),
  scope character varying(255),
  grantTypes character varying(255),
  redirectUrl character varying(255),
  authorities character varying(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation character varying(4096),
  autoApproveScopes character varying(255)
);

create table application_role (
  app_role_pk numeric(20) PRIMARY KEY,
  ldap_role character varying(50) not null,
  app_role character varying(50) not null
);

create table actions(
	actions_pk numeric(20) not null primary key,
	action_url_pattern character varying(512) not null
);

create table actions_role_participant(
	action_role_ptcp_pk numeric(20) primary key,
	app_role_pk numeric(20) references application_role(app_role_pk) not null,
	actions_pk numeric(20) references actions(actions_pk) not null,
	status character varying(6) not null
);