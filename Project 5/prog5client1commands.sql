# This script contains the SQL commands to be executed by client #1 
# for project #5 - CNT 4714 - Fall 2015
#
# Note that the permissions for the client must already have been set and due to the requirements
# not all of the following commands will execute successfully.

#select the database to be used.
use project5;

# Command #1: Insert into parts the row: P60, flange, silver, 8, Los Angeles.
# This command will execute.

#SCREENSHOT: Client1 #1
select * from parts;
insert into parts values ('P60','flange','silver',8, 'Los Angeles');
select * from parts;

# Command #2: Insert into shipments the row S5, P8, J5, 40.
# This command will execute.

#SCREENSHOT: Client1 #2
select * from shipments;
#SCREENSHOT: Client1 #3
insert into shipments values ('S5','P8','J5', 40);
#SCREENSHOT: Client1 #4
select * from shipments;

# Command #3: Update the name of the part identified by key value P33 with the new name of gear.
# This command will not execute - client1 does not have update privileges on db. 

#SCREENSHOT: Client1 #5
update parts
set pname = 'gear'
where pnum = 'P33';
#SCREENSHOT: Client1 #6
select * from parts where pnum = 'P33';


# Command #4: List all of the parts which are shipped in a quantity greater than 125.
# This command will execute properly for client1.

#SCREENSHOT: Client1 #7
select distinct shipments.pnum
from shipments 
where quantity > 125;


# Command #5: Insert into shipments the row S87, P9, J4, 250
# This command will not execute properly for client1.
#SCREENSHOT: Client1 #8
select distinct city from jobs;

#SCREENSHOT: Client1 #9
update jobs
set city = "Rome"
where city = "Berlin";

#SCREENSHOT: Client1 #10
select distinct city from jobs;


