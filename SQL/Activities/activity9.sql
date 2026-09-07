SELECT c.customer_id, c.customer_name, c.city, s.salesman_id, s.salesman_name FROM customers c
INNER JOIN salesman s
ON s.salesman_id = c.salesman_id;
SELECT c.customer_id, c.customer_name,c.grade, c.city,s.salesman_id , s.salesman_name FROM customers c
LEFT OUTER JOIN salesman s
ON s.salesman_id = c.salesman_id
WHERE c.grade < 300
ORDER BY c.customer_id;
SELECT c.customer_id, c.customer_name, c.city, s.salesman_name, s.comission FROM customers c 
INNER JOIN salesman s
ON c.salesman_id = s.salesman_id
WHERE s.comission > 12;
SELECT o.order_no,o.order_date, o.purchase_amount, 
c.customer_id,c.customer_name,c.city,
s.salesman_id,s.salesman_name,s.comission FROM orders o 
INNER JOIN customers c 
ON o.customer_id = c.customer_id 
INNER JOIN salesman s
ON o.salesman_id = s.salesman_id;
