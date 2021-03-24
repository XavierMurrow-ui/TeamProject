
// individual performance report
SELECT Staff.Name, Task.Task_ID, Task.Location, Task.Duration,
(SELECT SUM(Task.Duration) FROM Task WHERE Task.StaffStaff_ID = Staff.Staff_ID)
AS Total FROM Task
INNER JOIN Staff ON Task.StaffStaff_ID = Staff.Staff_ID
ORDER BY Staff.Name

//individual report
SELECT Date, Job_No, Customer.Customer_Name FROM Job 
INNER JOIN Customer ON Customer.Customer_ID = Job.CustomerCustomer_No
WHERE Customer.Customer_Name = 'AirVia' AND
Date BETWEEN '16/03/21' AND '22/03/21'

//summary performance report
