# The root user execution script for Project Three - CNT 4714 - Fall 2015
# all commands assumed to be executed by the root user
#
#Command 1:
#   Query: Which rider won Milan-San Remo in 2010?
select ridername
from racewinners
where racename = 'Milan-San Remo' and raceyear = 2010;

#Command 2:
#   Query: List the teams that ride bikes manufactured in Italy.
select teamname
from teams
where bikename in (select bikename
                   from bikes
                   where country_of_origin = 'Italy');
                   
#Command 3:
#   Query: List the name of every race won by a rider who has more than
#          50 professional wins.
select distinct racename
from racewinners
where ridername in (select ridername 
                    from riders
                    where num_pro_wins > 50);

#Command 4:
#   Query: List the names of all the riders on the same team as the winner of the 
#          2010 Paris-Roubaix race.
select ridername 
from riders 
where teamname = (select teamname
                  from riders
                  where ridername = (select ridername
                                     from racewinners
                                     where racename = 'Paris-Roubaix'
                                         and raceyear = 2010
                                    )
                );
                
#Command 5:
#    Insert the rider Mark Renshaw into the riders table.
# * * Do a "before" and "after" selection on the riders table
select * from riders;
insert into riders values ('Mark Renshaw','HTC-Columbia','Australia',26);
select * from riders;


#Command 6:
#   List the names of those riders who have won Paris-Roubaix at least two times.
select ridername 
from racewinners
where racename = 'Paris-Roubaix'
group by ridername
having count(ridername) >= 2;

#Command 7:
#   Delete all the riders from Norway from the riders table.
#   * * * Do a "before" and "after" select * from riders for this command.
select * from riders;
delete from riders where nationality = 'Norway';
select * from riders;

#Command 8:
#    Update rider Mark Renshaw to show number of wins = 30 in the riders table.
# * * Do a "before" and "after" selection on the riders table
select * from riders;
update riders set num_pro_wins = 30 where ridername = "Mark Renshaw";
select * from riders;
