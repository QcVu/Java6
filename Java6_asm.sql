CREATE DATABASE ASSIGNMENT_JAVA6
GO

USE ASSIGNMENT_JAVA6
GO

CREATE TABLE JAVA6_FINAL
(
	ID NVARCHAR(20) PRIMARY KEY,
	PASSWORD NVARCHAR(50),
	FULLNAME NVARCHAR(50),
	EMAIL NVARCHAR(50),
	ADMIN NVARCHAR(20)
)

INSERT INTO JAVA6_FINAL VALUES 
('admin', 'adminpass', 'A Đê Min', 'adminmail1@gmail.com', 'Admin'),
('user', 'userpass', 'Du Se', 'usermail1@gmail.com', 'User'),
('khachhang', 'khachhangpass', 'Khách hàng', 'khachhangmail1@gmail.com', 'Khach')