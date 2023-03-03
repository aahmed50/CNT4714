# SQL commands to create and populate the operations log database for Project Three
# CNT 4714 - Spring 2023
#
# delete the database if it already exists
drop database if exists operationslog;

# create a new database named operationslog
create database operationslog;

# switch to the new database
use operationslog;

# create the schemas for the four relations in this database
create table operationscount (
    num_queries integer,
	num_updates integer,
    primary key (num_queries, num_updates)
);

# populate the initial table with 0 counts

insert into operationscount values (0,0);

# uncomment the following line if you want to see the results of creating and populating the database
select * from operationscount;
