create database DBmodule3;

use DBmodule3;

create table Product (
    productCode int auto_increment primary key,
    productName varchar(500) not null ,
    productBrand varchar(50) not null ,
    productPrice double not null ,
    productImage nvarchar(500) ,
    productDetail text,
    productLine varchar(50)
);

create table Customer(
    cusNumber int auto_increment primary key ,
    cusName varchar(50) not null ,
    cusPhoneNumber varchar(50) not null unique ,
    cusAddress varchar(500) not null ,
    cusEmail varchar(50) unique
);

create table User(
    userName varchar(50) primary key ,
    password varchar(50) not null
);

alter table Customer
add column userName varchar(50) unique ,
    add constraint foreign key (userName) references User(userName);

create table Role(
    id bit primary key ,
    roleName varchar(10) not null
);

create table Orders(
    orderNumber int auto_increment primary key ,
    cusNumber int not null,
    orderDate date not null,
    constraint foreign key (cusNumber) references Customer(cusNumber)
);

create table OrderDetail(
    productCode int not null ,
    orderNumber int not null ,
    quantityOrdered int not null ,
    priceEach double not null ,
    constraint foreign key (productCode) references Product(productCode),
    constraint foreign key (orderNumber) references Orders(orderNumber),
    status varchar(50)
);


alter table User
add column roleID bit not null,
    add constraint foreign key (roleID) references Role(id);

insert into Customer (cusName, cusPhoneNumber, cusAddress, cusEmail) VALUES ('huy','012345','hanoi','huy88956');

insert into Role values (1,'admin');
insert into Role values (0,'customer');

alter table User
modify column roleID bit not null default 0;

INSERT INTO User (userName, password) values ('huy','1234');






