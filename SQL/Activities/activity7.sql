SELECT SUM(purchase_amount) AS total_amount FROM orders;
SELECT AVG(purchase_amount) AS average_amount FROM orders;
SELECT MAX(purchase_amount) AS Maximum_amount FROM orders;
SELECT Min(purchase_amount) AS Minimum_amount FROM orders;
SELECT COUNT(DISTINCT salesman_id) AS total_salesman FROM orders;
