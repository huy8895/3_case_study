create database DBmodule3;

use DBmodule3;

create table Product (
    productCode int auto_increment primary key,
    productName varchar(50) not null ,
    manufacture varchar(50) not null ,
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

create table Admin(
    userName varchar(50) primary key ,
    password varchar(50) not null
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
add column cusNumber int not null,
    add constraint foreign key (cusNumber) references Customer(cusNumber);

insert into Customer (cusName, cusPhoneNumber, cusAddress, cusEmail) VALUES ('huy','012345','hanoi','huy88956');
update Customer set userName = 'huy889' where cusNumber = 4;
insert into User values ('huy8895','01423','1');
insert into User values ('huy889','01423','4');
insert into Customer (cusName, cusPhoneNumber, cusAddress, cusEmail,userName) VALUES ('huy','df','hanoi','huy88asd956@','huy88');




