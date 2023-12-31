# CNT4714
## Enterprise Computing | Spring 2023 

Enterprise computing architectures with an emphasis on networked, distributed applications

-----------

### Project 1 | Event Driven Programming
 <p>
 Problem Description: NileDotCom <br>
    Java program that creates a standalone GUI application that simulates an e-store which allows the user to add in stock items to a shopping cart and once all items are included, total all costs (including tax), produces an invoice, and append a transaction log file. 
 </p>

![Project 1](<Project 1/Cover Photo.png>)

<br>

### Project 2 | Synchronized Multi-Threads
 <p>
 Problem Description: Banking Simulator <br>
    An Application Employing Synchronized/Cooperating Multiple Threads In Java Using Locks – A Banking Simulator.
    Develop  an  application  which  requires  cooperating,  synchronized multiple threads of execution. 
 </p>

![Project 2](<Project 2/Cover Photo.png>)

<br>

### Project 3 | Two-Tier Client-Server Application Development With MySQL and JDBC
 <p>
 Problem Description: A Two-tier Client-Server Application<br>
    Develop  a  Java-based  GUI  front-end  (client-side) application that will connect to a MySQL server via JDBC.  
 </p>

![Alt text](<project 3 v2/Cover Photo.png>)

<br>

### Project 4 | Three-Tier Distributed Web-Based Application
 <p>
 Problem Description: A Three-Tier Distributed Web-Based Application using servlets running on a Tomcat Server to access MSQL database via JDBC<br>
 
 <b>APPLICATION ARCHITECTURE: </b>
 
 Tier 1: [User-Level Front End] Consisting of a HTML landing page to authenticate end users. Handled via servlet in the webapp to validate user entered credentials.
        JSP page that allows client to enter SQL commands into a window and submit to server application. 
        Redirects users to one of three JSP pages: root-level user clients, non-root-level clients, data entry for new records.

 Tier 2: [Serverlet] Handles the SQL command interface for users 
        Root Level: Implement server-side business logic.
            This logic will increment by 5, the status of a supplier anytime that supplier is involved in the insertion/update of a shipment record in which the quantity is greater than or equal to 100.
        Client level: handle SQL command interface without business logic
        Data Entry level: provide user with four forms for each table in the database
        
 Tier 3: [Back-End] Persistent MySQL database under the control of the MySQL DBMS server. Created and maintained via population script <br>

    Schema:
        suppliers (snum, sname, status, city) //information about suppliers
        parts (pnum, pname, color, weight, city) //information about parts
        jobs (jnum, jname, numworkers, city) //information about jobs
        shipments (snum, pnum, jnum, quantity) //suppliers ship parts to jobs in specific quantities
 </p>

![Project 4](<Project 4/Cover Photo 1.png>)
![Project 4.1](<Project 4/Cover Photo 2.png>)
