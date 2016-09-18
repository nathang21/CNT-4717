# The client user execution script for Project Three - CNT 4714 - Fall 2015
# all commands assumed to be executed by the client user
# the client user has only selection  privilege on the project3 database schema
#
#Command 1:
#   Query: Which rider won Milan-San Remo in 2010?
select ridername
from racewinners
where racename = 'Milan-San Remo' and raceyear = 2010;

#Command 2:
#   Delete all the riders from Norway from the riders table.
#   * * * Do a "before" and "after" select * from riders for this command.
#   Note: the before and after select statements will execute, but the delete will not
#         thus no changes will be reflected in the before and after snapshots.
select * from riders;
delete from riders where nationality = 'Norway';
select * from riders;

#Command 3:
#    Update rider Mark Renshaw to show number of wins = 40 in the riders table.
# * * Do a "before" and "after" selection on the riders table
#   Note: the before and after select statements will execute, but the delete will not
#         thus no changes will be reflected in the before and after snapshots.
select * from riders;
update riders set num_pro_wins = 40 where ridername = "Mark Renshaw";
select * from riders;
