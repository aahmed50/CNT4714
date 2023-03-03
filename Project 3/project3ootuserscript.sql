# The root user execution script for Project Three- CNT 4714 - Spring 2023
# all commands assumed to be executed by the root user
#
# Command 1:
#   Query: Which rider won World Cyclocross Championships - Elite Women in 2020?
select ridername
from racewinners
where racename = 'World Cyclocross Championships - Elite Women' and raceyear = 2020;

# Command 2:
#   Query: List the teams that ride Colnago bikes.
select teamname
from teams
where bikename = "Colnago";
                   
# Command 3:
#   Query: List the name of every race won by a rider who has more than 50 professional wins.
select distinct racename
from racewinners
where ridername in (select ridername 
                    from riders
                    where num_pro_wins > 50);

# Command 4:
#   Query: List the names of all the riders on the same team as the winner of the 2010 Paris-Roubaix race.
select ridername 
from riders 
where teamname = (select teamname
                  from riders
                  where ridername = (select ridername
                                     from racewinners
                                     where racename = 'Paris-Roubaix' and raceyear = 2010
                                    )
                );
                
# Commanda 5A, 5B, and 5C:
#    Insert the rider Mark Renshaw into the riders table.
# * * Do a "before" and "after" selection on the riders table
select * from riders;
insert into riders values ('Mark Renshaw','HTC-Columbia','Australia',26, 'M');
select * from riders;


# Command 6:
#   List the names of those riders who have won Paris-Roubaix at least two times.
select ridername 
from racewinners
where racename = 'Paris-Roubaix'
group by ridername
having count(ridername) >= 2;

# Commands 7A, 7B, and 7C:
#   Delete all the riders from Belgium from the riders table.
#   * * * Do a "before" and "after" select * from riders for this command.
select * from riders;
delete from riders where nationality = 'Belgium';
select * from riders;

# Commands 8A, 8B, and 8C:
#    Update rider Mark Renshaw to show number of wins = 30 in the riders table.
# * * Do a "before" and "after" selection on the riders table
select * from riders;
update riders set num_pro_wins = 30 where ridername = "Mark Renshaw";
select * from riders;

# Command 9:
#   This command is malformed and will not execute
select * 
from racewinners
where length >= 200;

# Commands 10A and 10B:
#   update rider Ceylin del Carmen Alvarado to show number of wins = 89 in the riders table.
#   then query the riders table to list all riders with exactyl 89 wins.
update riders set num_pro_wins = 89 where ridername = 'Ceylin del Carmen Alvarado';
select (ridername, num_pro_wins) from riders where num_pro_wins = 89;
