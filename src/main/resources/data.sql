SELECT
    MONTH(o.completed_at) AS month,
    YEAR(o.completed_at) AS year,
    COUNT(DISTINCT o.customer_id) AS total_customers,
    COUNT(DISTINCT o.id) AS total_orders,
    SUM(oi.quantity) AS total_products_sold,
    SUM(oi.total) AS total_revenue
FROM
    orders o
JOIN
    order_item oi ON o.id = oi.order_id
WHERE
	o.status = 'COMPLETED'
    AND YEAR(o.completed_at) = 2024
GROUP BY
    YEAR(o.completed_at), MONTH(o.completed_at)
ORDER BY
    YEAR(o.completed_at), MONTH(o.completed_at);