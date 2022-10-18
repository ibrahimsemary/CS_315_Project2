--CREATE OR REPLACE VIEW view11 AS SELECT indexid, transactionitems.transactionid, id FROM transactionitems LEFT JOIN transactions on transactionitems.transactionid = transactions.transactionid WHERE transactiondate > '10-10-22';

--SELECT name, count(*) amountOrdered from view11 NATURAL JOIN items GROUP BY (id, name) ORDER BY amountOrdered DESC;

--CREATE OR REPLACE VIEW view22 AS SELECT indexid, transactionitems.transactionid, id FROM transactionitems LEFT JOIN transactions on transactionitems.transactionid = transactions.transactionid WHERE transactiondate > '10-10-22';
--CREATE OR REPLACE VIEW view33 AS SELECT name, count(*) amountOrdered from view22 NATURAL JOIN items GROUP BY (id, name) ORDER BY amountOrdered DESC;
SELECT itemname FROM items NATURAL JOIN ingredientslist NATURAL JOIN inventory WHERE name = 'fried rice';
