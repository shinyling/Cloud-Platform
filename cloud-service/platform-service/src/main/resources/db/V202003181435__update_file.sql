alter table file
    add column suffix varchar(16) comment '文件后缀';
alter table file
    add column is_delete tinyint(1) comment '是否删除';
alter table file
    modify column size bigint comment '文件大小';