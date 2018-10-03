DROP TABLE IF EXISTS "contacts";

CREATE TABLE "contacts" (
  id INTEGER  not null,  
  name varchar(255) default NULL,
  primary key(id)
);
