# This script contains the SQL commands to be executed by client #2 
# for project #5 - CNT 4714 - Fall 2015
#
# Note that the permissions for the client must already have been set and due to the requirements
# not all of the following commands will execute successfully.

#select the database to be used.
use project5;

# Command #1: Insert into suppliers the row: S14, Eva Mendes, 10, Miami.
# This command will not execute - client2 does not have insert permission on db

#SCREENSHOT: Client2 #1
select * from suppliers;
#SCREENSHOT: Client2 #2
insert into suppliers values ('S14','Eva Mendes', 10, 'Miami');
#SCREENSHOT: Client2 #3
select * from suppliers;


# Command #2: List all the part numbers and part names for parts with color = blue.
# This command will execute.

#SCREENSHOT: Client2 #4
select pnum, pname 
from parts
where color = "blue";



# Command #3: Update the city value of all parts from London to a new value of Miami. 
# This command will execute.

#SCREENSHOT: Client2 #5
select * from parts;
#SCREENSHOT: Client2 #6
update parts
set city = "Miami"
where city = "London";
#SCREENSHOT: Client2 #7
select * from parts;


# Command #4: Update the shipments table by adding 25 to every quantity.
# This command will execute.

#SCREENSHOT: Client2 #8
select * from shipments;
#SCREENSHOT: Client2 #9
update shipments
set quantity = quantity + 25;
#SCREENSHOT: Client2 #10
select * from shipments;


