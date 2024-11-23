INSERT INTO product (name, price)
VALUES 
    ('Gaming Laptop', 5200.00),
    ('Bluetooth Speaker', 599.99),
    ('Smartwatch', 1199.99),
    ('4K TV', 7750.00),
    ('Wireless Mouse', 699.99),
    ('Smartphone Case', 50.50),
    ('Tablet', 3999.99),
    ('USB Flash Drive', 89.99),
    ('External Hard Drive', 299.00),
    ('Fitness Tracker', 399.99);


ALTER TABLE product ADD COLUMN description VARCHAR(255);

UPDATE product
SET description = 'High-performance laptop'
WHERE name = 'Gaming Laptop';

UPDATE product
SET description = 'Portable Bluetooth speaker'
WHERE name = 'Bluetooth Speaker';

UPDATE product
SET description = 'Smartwatch with health tracking'
WHERE name = 'Smartwatch';

UPDATE product
SET description = 'Large 4K Ultra HD TV'
WHERE name = '4K TV';

UPDATE product
SET description = 'Compact wireless mouse'
WHERE name = 'Wireless Mouse';

UPDATE product
SET description = 'Protective case for smartphones'
WHERE name = 'Smartphone Case';

UPDATE product
SET description = 'Lightweight tablet'
WHERE name = 'Tablet';

UPDATE product
SET description = 'USB drive with 16GB capacity'
WHERE name = 'USB Flash Drive';

UPDATE product
SET description = 'Portable external storage'
WHERE name = 'External Hard Drive';

UPDATE product
SET description = 'Activity tracker for fitness'
WHERE name = 'Fitness Tracker';

ALTER TABLE product ADD COLUMN tax_rate DOUBLE PRECISION;

UPDATE product
SET tax_rate = CASE
    WHEN price > 1000 THEN 0.15  -- Products with price > 1000 will get 15% tax
    ELSE 0.10                    -- Products with price <= 1000 will get 10% tax
END;

--update the id for all items
WITH updated AS (
    SELECT id, row_number() OVER (ORDER BY id) AS new_id
    FROM product
)
UPDATE product
SET id = updated.new_id
FROM updated
WHERE product.id = updated.id;
CREATE TABLE amin_alsuhaibani_product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    description TEXT,
    tax_rate DOUBLE PRECISION NOT NULL
);
