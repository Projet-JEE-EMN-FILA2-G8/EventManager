delete from events where id is not null;

alter table events drop column DATEFIN;
alter table events drop column DATEDEB;

alter table events add column DATEDEB TIMESTAMP;
alter table events add column DATEFIN TIMESTAMP;
alter table events alter column DATEDEB NOT NULL;
alter table events alter column DATEFIN NOT NULL;