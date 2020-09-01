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
    cusPhoneNumber varchar(50) not null ,
    cusAddress varchar(500) not null ,
    cusEmail varchar(50)
);

