# MySQL script for the root user queries of project #5 - CNT 4714 - Fall 2015
#
# this script assumes that you have already run the script
# titled "prog5dbscript.sql" which creates and populates the database.
#


# select correct database.
use project5;


#ROOT user queries
#
#query #1: List the snum and sname for every supplier who ships a part whose color is black to any job located in Orlando. 

#SCREENSHOT: Root #1
select suppliers.snum, suppliers.sname
from suppliers
where suppliers.snum in
    (select shipments.snum
     from shipments
     where shipments.pnum in (select pnum from parts where color = 'black')
       and shipments.jnum in (select jnum from jobs where city = 'Orlando')
    );

#this also works for query #1
#select snum, sname
#from suppliers
#where snum in 
#    (select snum
#     from shipments natural join parts
#     where color='black'
#       and jnum in 
#           (select jnum
#            from jobs
#            where city = 'Orlando')
#    );
#
#
#note that the following expression also answers query #1, but not as efficiently.
#select suppliers.snum, suppliers.sname
#from suppliers,shipments,parts,jobs
#where suppliers.snum = shipments.snum
#  and shipments.pnum = parts.pnum
#  and shipments.jnum = jobs.jnum
#  and parts.color = 'black'
#  and jobs.city = 'Orlando';


#query #2: List the pnum and pname for all of the parts which are not shipped to any job.

#SCREENSHOT: Root #2
select parts.pnum, parts.pname
from parts
where not exists (select * from shipments where shipments.pnum = parts.pnum);


#query #3: List the jnum and jname for those jobs which receive shipments of parts from only supplier with snum = ‘S1’.
#Note: this query can also be handled with an excepts clause which is not
#available in all SQL implementations, so I only show it here using a
#nested query using a tuple calclus style operations which is not exists.

#SCREENSHOT: Root #3
select jobs.jnum
from jobs
where not exists
    (select *
     from shipments
     where shipments.jnum = jobs.jnum
       and not (shipments.snum = 'S1')
    );

    

#query #4: List the snum, sname, and pnum for those suppliers who ship the same part to every job.

#SCREENSHOT: Root #4
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
     ) ) );


#query #5: List the snum, and sname for those suppliers who ship both blue and red parts to some job.

#SCREENSHOT: Root #5
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


#update #6:  Update the suppliers table by adding 3 to the status of every supplier who has
#            a shipment of any part in a quantity greater than 200.
# note: first selection is just to show status of suppliers relation before the update occurs.

#SCREENSHOT: Root #6
select * from suppliers;

#SCREENSHOT: Root #7
update suppliers
set status = status + 3
where snum in (select snum
               from shipments
               where quantity > 200);


# see results of previous update
#SCREENSHOT: Root #8
select * from suppliers;


# Command 7 is a multipart transaction that will cause the business logic to trigger
#
# The first part is a query to illustrate all shipment information before the update.
# The second part performs the update and causes the business logic to trigger.
# The third part is a query that illustrates all shipment information after the update/
# In the non-bonus version of the program, supplier numbers S1, S2, S3, S6, and S22 all
# have their status value updated.
# In the bonus verison of the program, only supplier numbers S1 and S3 will have their status
# value updated. 
# SCREENSHOT: Root #9
select * from shipments;
			  
# SCREENSHOT: Root #10: Update the shipments table by increasing the quantity by 10 every
#             shipment of part P3.
update shipments
set quantity = quantity + 10
where pnum = 'P3';

# SCREENSHOT: Root #11
select *
from shipments;
