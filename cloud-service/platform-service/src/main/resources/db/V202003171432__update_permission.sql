alter table permission
    add column permission_desc varchar(100) comment '权限描述';
alter table permission
    add column pid varchar(100) comment '父权限id';
alter table permission
    add column create_by varchar(36) comment '创建人';