use DBmodule3;

create procedure create_new_customer(
in name varchar(50),
in phone varchar(15),
in address varchar(500),
in email varchar(50))
begin
    INSERT INTO Customer(cusNumber, cusName, cusPhoneNumber, cusAddress, cusEmail)
    values (name,phone,address,email);
end;

update User set password =  'huy8895' where userName = 'huy';
