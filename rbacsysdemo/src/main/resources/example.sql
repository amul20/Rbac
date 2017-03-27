insert into user(userId,name,password) values('amulyam','gdqwdq','287567874727237697473913646891586575524');
insert into user(userId,name,password) values('anmol','gejhdeiuw','165801617470889927508345088304196498006');

insert into actions(aName) values('read');
insert into actions(aName) values('write');
insert into actions(aName) values('delete');
insert into actions(aName) values('update');

insert into resource(resName) values('server');
insert into resource(resName) values('db');
insert into resource(resName) values('cache');


insert into roles(roleName) values('admin');
insert into roles(roleName) values('dev');
insert into roles(roleName) values('qa');


insert into user_role(uId,rId) values('amulyam','admin');
insert into user_role(uId,rId) values('amulyam','qa');
insert into user_role(uId,rId) values('anmol','dev');
insert into user_role(uId,rId) values('anmol','qa');

insert into rra(roleId,resId,aId) values('admin',1,1);
insert into rra(roleId,resId,aId) values('admin',1,2);
insert into rra(roleId,resId,aId) values('admin',1,3);
insert into rra(roleId,resId,aId) values('admin',1,4);
insert into rra(roleId,resId,aId) values('admin',3,1);
insert into rra(roleId,resId,aId) values('admin',3,2);
insert into rra(roleId,resId,aId) values('dev',1,1);
insert into rra(roleId,resId,aId) values('dev',2,1);
insert into rra(roleId,resId,aId) values('dev',3,1);
insert into rra(roleId,resId,aId) values('qa',2,1);
insert into rra(roleId,resId,aId) values('qa',2,2);