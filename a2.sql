SET search_path TO A2;
-- Add below your SQL statements. 
-- For each of the queries below, your final statement should populate the respective answer table (queryX) with the correct tuples. It should look something like:
-- INSERT INTO queryX (SELECT … <complete your SQL query here> …)
-- where X is the correct index [1, …,10].
-- You can create intermediate views (as needed). Remember to drop these views after you have populated the result tables query1, query2, ...
-- You can use the "\i a2.sql" command in psql to execute the SQL commands in this file.
-- Good Luck!


INSERT INTO query2 (
    select tournament.tname,sum(court.capacity)
    from a2.tournament, a2.court
    where tournament.tid = court.tid
    group by tournament.tid
    LIMIT 1
);

--Query 4
delete from query4;  --TODO: delete this line later 

-- temp view that contains all the champions
create or replace view allChamps as 
select player.pid, player.pname, champion.tid
from player, champion
where (champion.pid = player.pid);

--count the number of all tournaments and number of all tournaments champs have won and see which ones have the same number
insert into query4 (
select pid , pname
from allChamps
where allChamps.tid in (select tournament.tid from tournament)
group by allChamps.pid, allChamps.pname
having count(distinct tid) = (select count(*) from tournament)
order by allChamps.pname
);

drop view allChamps;

--Query 6
delete from query6; --TODO: delete this later

--Any play that as a record from 2011 and 2014
create or replace view rangeOfYears as 
select p.pid, r.year, r.wins
from player p, record r
where (p.pid = r.pid) and (year between 2011 and 2014);

--All the players with only the 4 years 
create or replace view between11and14 as 
select *
from rangeOfYears
where pid in ( 
    select pid
    from rangeOfYears
    group by pid 
    having count(pid) > 3
)

--drop view rangeOfYear 
