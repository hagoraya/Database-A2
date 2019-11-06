SET search_path TO A2;
-- Add below your SQL statements. 
-- For each of the queries below, your final statement should populate the respective answer table (queryX) with the correct tuples. It should look something like:
-- INSERT INTO queryX (SELECT … <complete your SQL query here> …)
-- where X is the correct index [1, …,10].
-- You can create intermediate views (as needed). Remember to drop these views after you have populated the result tables query1, query2, ...
-- You can use the "\i a2.sql" command in psql to execute the SQL commands in this file.
-- Good Luck!


-- Creating samaple data
insert into country (cid, cname)
values (1, 'Canada'),(2,'USA');

insert into a2.player (pid, pname, globalrank, cid)
values (32,'Bob',1,1),(12,'Jerry',2,2);

insert into a2.record (pid,year,wins,losses)
values (32,2018,12,5),(12,2018,8,38);

insert into tournament (tid, tname, cid)
values (543, 'NA International', 1), (322, 'US Open', 2);

insert into court (courtid, courtname, capacity, tid)
values (92, 'Rogers arena', 5000, 322), (99, 'MSG', 10000, 543);

insert into champion (pid, year, tid)
values (32,2018,322);

insert into event (eid, year, courtid, winid, lossid, duration)
values (1022, 2018, 92, 32,12,60),
(1033, 2019,99,12,32,120);

--Query 1 statements
--INSERT INTO query1

--Query 2 statements
--INSERT INTO query2

--Query 3 statements
--INSERT INTO query3

--Query 4 statements
--INSERT INTO query4

--Query 5 statements
--INSERT INTO query5

--Query 6 statements
--INSERT INTO query6

--Query 7 statements
--INSERT INTO query7

--Query 8 statements
--INSERT INTO query8

--Query 9 statements
--INSERT INTO query9

--Query 10 statements
--INSERT INTO query10
