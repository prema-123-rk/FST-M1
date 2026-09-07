SELECT customer_id, customer_name FROM customers c
WHERE 1<(SELECT COUNT(*) FROM orders o WHERE c.customer_id = o.customer_id)
UNION
SELECT salesman_id, salesman_name FROM salesman s
WHERE 1<(SELECT COUNT(*) FROM orders o WHERE s.salesman_id = o.salesman_id)
ORDER BY customer_name;
SELECT s.salesman_id, s.salesman_name, o.order_no, 'highest_on', o.order_date, o.purchase_amount FROM salesman s
JOIN orders o
ON s.salesman_id=o.salesman_id
WHERE o.purchase_amount=(SELECT MAX(purchase_amount) FROM orders c WHERE c.order_date = o.order_date)
UNION
SELECT s.salesman_id, s.salesman_name, o.order_no, 'lowest_on', o.order_date, o.purchase_amount FROM salesman s
JOIN orders o
ON s.salesman_id=o.salesman_id
WHERE o.purchase_amount=(SELECT MIN(purchase_amount) FROM orders c WHERE c.order_date = o.order_date)
ORDER BY order_date;
