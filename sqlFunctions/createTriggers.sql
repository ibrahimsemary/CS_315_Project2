-- Auto add to inventory on new batch
/*
DROP FUNCTION addInventory() CASCADE;
CREATE FUNCTION addInventory() 
RETURNS TRIGGER AS $trigger1$
    BEGIN
        UPDATE teststock SET
        stock = stock + NEW.amount
        WHERE teststock.temid = NEW.itemid;
        return NEW;

    END;
$trigger1$ language plpgsql;


CREATE TRIGGER trigger1
    AFTER INSERT ON testbatch
    FOR EACH ROW
    EXECUTE PROCEDURE addInventory();
*/

-- Auto subtract on new transaction item
DROP FUNCTION dropInventory() CASCADE;
CREATE FUNCTION dropInventory() 
RETURNS TRIGGER AS $trigger2$
    BEGIN
        UPDATE teststock SET
        stock = stock - 1
        WHERE teststock.itemid = NEW.itemid;
        return NEW;

    END;
$trigger2$ language plpgsql;

CREATE TRIGGER trigger2
    AFTER INSERT ON transactionitems_test
    FOR EACH ROW
    EXECUTE PROCEDURE dropInventory();
