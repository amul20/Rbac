create table user(userId varchar(20) not null, name varchar(30) not null, password varchar(80) not null, PRIMARY KEY(userId));

create table roles(roleName varchar(50) not null, roleDesc varchar(50), PRIMARY KEY(roleName));

create table resource(resId int not null AUTO_INCREMENT, resName varchar(30) not null, resDesc varchar(50), PRIMARY KEY(resId));

create table actions(aId int not null AUTO_INCREMENT, aName varchar(30) not null, aDesc varchar(50), PRIMARY KEY(aId));

create table rra(roleId varchar(50) not null,resId int not null,aId int not null, FOREIGN KEY rolfk(roleId) REFERENCES roles(roleName) ON DELETE CASCADE, FOREIGN KEY resfk(resId) REFERENCES resource(resId) ON DELETE CASCADE, FOREIGN KEY afk(aId) REFERENCES actions(aId) ON DELETE CASCADE, PRIMARY KEY(roleId,resId,aId));

create table user_role(uId varchar(20) not null, rId varchar(50) not null, FOREIGN KEY user_fk(uId) REFERENCES user(userId) ON DELETE CASCADE, FOREIGN KEY role_fk(rId) REFERENCES roles(roleName) ON DELETE CASCADE, PRIMARY KEY(uId,rId));