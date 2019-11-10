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


delete from query4;

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
);


drop view allChamps;
