# User command script
# CNT 4714 - Fall 2015 - Project 4
# This script contains the commands that the user/client will issue against the
# Project4 database (supplier/parts/jobs/shipments.  The Java servlet that is 
# Project4 incoporates server-side business logic that manipulates the status of a
# supplier when certain conditions involving shipment quantity are triggered.
#
###########################################################
# Command 1: Query: list all suppliers located in Berlin
select *
from suppliers
where city = 'Berlin';

###########################################################
# Command 2: Query: list all shipment information for shipments involving a red part
select *
from shipments
where pnum in (select pnum
               from parts
			   where color = 'red');

###########################################################
# Command 3 is a multi-part sequence that will trigger the business logic.
# The first part is a query to illustrate all supplier information before the update.
# The second part performs the update and causes the business logic to trigger.
# The third part is a query that illustrates all supplier information after the update/
# In the non-bonus version of the program, supplier numbers S1, S2, S22, S3, S5, and S6 all
# have their status value updated.
# Command 3A: Query: list all supplier information.
select *
from suppliers;

# Command 3B: add a new record to shipments table (S5, P6, J7, 400)
insert into shipments
values ('S5', 'P6', 'J7', 400);

# Command 3C: list all supplier information.
select * 
from suppliers;

###########################################################
# Command 4 is a multi-part that does not cause the business logic to trigger
# Command 4A: add a new record to the supplier table (S39, Candice Swanepoel, 10, Cardiff)
insert into suppliers
values ('S39','Candice Swanepoel',10,'Cardiff')

# Command 4B: list all supplier information.
select *
from suppliers;

# Command 4C: add a new record to shipments table (S39, P3, J1, 20)
insert into shipments
values ('S39','P3','J1', 20);

# Command 4D: list all supplier information
select *
from suppliers;

###########################################################
# Command 5: List the snum, sname, and pnum for those suppliers who ship the
# same part to every job.  This is a fairly complex SQL nested query.

select distinct suppliers.snum, suppliers.sname, shipments.pnum
from suppliers natural join shipments
where shipments.pnum in
    (select pnum
     from parts
     where not exists
        (select * 
         from jobs
         where not exists
            (select *
             from shipments
             where shipments.snum = suppliers.snum
                   and shipments.pnum = parts.pnum
                   and shipments.jnum = jobs.jnum
			)
		) 
	);

###########################################################
# Command 6: List the snum, and sname for those suppliers who ship both blue and red parts to some job.
# Output should list only supplier numbers S3 and S6

select suppliers.snum, suppliers.sname
from suppliers
where suppliers.snum in
   (select shipments.snum 
    from shipments
    where shipments.snum in
          (select shipments.snum 
           from shipments natural join parts
           where parts.color='red')
     and shipments.snum in
          (select shipments.snum
           from shipments natural join parts
           where parts.color = 'blue')
   );
###########################################################
#
# Command 7 is a multipart transaction that will cause the business logic to trigger
#
# The first part is a query to illustrate all shipment information before the update.
# The second part performs the update and causes the business logic to trigger.
# The third part is a query that illustrates all shipment information after the update/
# In the non-bonus version of the program, supplier numbers S1, S2, S3, S6, and S22 all
# have their status value updated.
# In the bonus verison of the program, only supplier numbers S1 and S3 will have their status
# value updated. 
# Command 7A: List all shipment information
select *
from shipments;
			  
# Command 7B: Update the shipments table by increasing the quantity by 10 every
#             shipment of part P3.
update shipments
set quantity = quantity + 10
where pnum = 'P3';

# Command 7C:  List all shipment information
select *
from shipments;

