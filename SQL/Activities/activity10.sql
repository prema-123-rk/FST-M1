SELECT * FROM orders 
WHERE salesman_id = (
    SELECT DISTINCT salesman_id FROM orders 
    WHERE customer_id = 3000
);
SELECT * FROM orders 
WHERE salesman_id IN (
    SELECT salesman_id FROM salesman 
    WHERE salesman_city = 'New York'
);
SELECT grade,COUNT(customer_id) FROM customers
GROUP BY grade 
HAVING grade > (
    SELECT AVG(grade) FROM customers);
SELECT order_no, purchase_amount, order_date, salesman_id FROM orders
WHERE salesman_id IN (
    SELECT salesman_id FROM salesman 
    WHERE comission = (
        SELECT MAX(comission) FROM salesman 
    )
);
