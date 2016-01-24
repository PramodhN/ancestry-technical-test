# Ancestry technical test

- Used Java and MySQL for this application
- Used Maven for getting required packages

MySQL schema
------------
Basically used 2 tables to represent the requirements

- Table Name: **Zoo**
  - zooid (integer, primary key)
  - location (varchar)
  - running_inventory (double)
  - new_inventory (double)
- Table Name: **Animal**
  - animalid (integer, primary key)
  - zooid (integer, foreign key)
  - species (varchar)
  - feedtime (integer)
  - quantity (double)
  
Assumptions
-----------

- I have made some assumptions while designing the system and the database.
- This led to having a fewer features as based my assumption, some of the stats didn't make sense
- Some of the assumptions made include the following
  - I wasn't clear about how running and new inventory works. So I just made them 2 columns of the Zoo table
  - Similarly while adding new feed shipments, I have just added it to the existing new inventory
  - I assumed that there will be only 1 feed time per species in a zoo
  - Because of this assumption, the stats for 'How much was each individual animal fed per day on average' and 'How many times per day are animals fed on average?' didn't make sense.
