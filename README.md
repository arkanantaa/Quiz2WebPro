<h1> SQL Configuration </h1>


```
CREATE DATABASE Storify;

USE Storify;

CREATE TABLE Users (
    UserId INT PRIMARY KEY AUTO_INCREMENT,
    UserEmail VARCHAR(50) UNIQUE NOT NULL,
    UserName VARCHAR(50) UNIQUE NOT NULL,
    UserPassword VARCHAR(255) NOT NULL,
    UserDOB DATE NOT NULL,
    UserFullName VARCHAR(100) NOT NULL,
    UserAddress VARCHAR(255) NOT NULL
);

/2 products

CREATE TABLE Products (
    ProductId INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100) NOT NULL,
    ProductQuantity INT NOT NULL,
    ProductPrice DECIMAL(10, 2) NOT NULL
);


/3 catat siapa yang update terakhir

ALTER TABLE Products ADD last_updated_by INT;
ALTER TABLE Products ADD FOREIGN KEY (last_updated_by) REFERENCES Users(UserID);
```

