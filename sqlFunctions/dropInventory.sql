DROP VIEW view1;
CREATE VIEW view1 AS
SELECT transactionid, id, name
FROM transactionitems
NATURAL JOIN items
WHERE transactionid = 11;

select id, name, itemid, itemname from items NATURAL JOIN ingredientslist NATURAL JOIN inventory NATURAL JOIN view1;