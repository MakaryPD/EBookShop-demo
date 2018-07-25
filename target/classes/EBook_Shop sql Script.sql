DROP DATABASE  IF EXISTS `EBookShop_database`;

CREATE DATABASE  IF NOT EXISTS `EBookShop_database`;
USE `EBookShop_database`;

SET FOREIGN_KEY_CHECKS = 0; 
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `carts`;
DROP TABLE IF EXISTS `cart_book`;
DROP TABLE IF EXISTS `personal_data`;
DROP TABLE IF EXISTS `credit_card`;
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `transactions_books`;
SET FOREIGN_KEY_CHECKS = 1;

-- PERSONAL DATA
create TABLE `personal_data`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`first_name` VARCHAR(45) DEFAULT NULL, 
`last_name` VARCHAR(45) DEFAULT NULL, 
`email` VARCHAR(45) DEFAULT NULL, 
`reg_postal` VARCHAR(7) DEFAULT NULL, 
`reg_city` VARCHAR(45) DEFAULT NULL, 
`reg_street` VARCHAR(45) DEFAULT NULL,
`reg_house_nr` VARCHAR(5) DEFAULT NULL,
`reg_apartment_nr` VARCHAR(5) DEFAULT NULL,
`contact_postal` VARCHAR(7) DEFAULT NULL, 
`contact_city` VARCHAR(45) DEFAULT NULL, 
`contact_street` VARCHAR(45) DEFAULT NULL,
`contact_house_nr` VARCHAR(5) DEFAULT NULL,
`contact_apartment_nr` VARCHAR(5) DEFAULT NULL,
PRIMARY KEY(`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;  

INSERT INTO `personal_data`(`first_name`,`last_name`,`email`,`reg_postal`,`reg_city`,`reg_street`,`reg_house_nr`,`reg_apartment_nr`,
`contact_postal`,`contact_city`,`contact_street`,`contact_house_nr`,`contact_apartment_nr`) VALUES 
('Makary','Detko','detkopiotr@gmail.com','64100','Leszno','Rejtana',14,8,'64100','Leszno','Rejtana',14,8),
('test','test','test@gmail.com','64100','Leszno','Rejtana',14,8,'64100','Leszno','Rejtana',14,8);

-- CREDIT CARDS 
CREATE TABLE `credit_card` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`card_type` VARCHAR(45) DEFAULT NULL, 
`number` VARCHAR(45) DEFAULT NULL, 
`owner_first_name` VARCHAR(45) DEFAULT NULL, 
`owner_last_name` VARCHAR(45) DEFAULT NULL,
`expire_date` DATE DEFAULT NULL, 
`password`  INT(11), 
`personal_data_id` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `FK_PERSONAL_CREDIT` FOREIGN KEY(`personal_data_id`) REFERENCES `personal_data` (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;

INSERT INTO `credit_card` (`card_type`, `number`,`owner_first_name`,`owner_last_name`, `expire_date`, `password`,`personal_data_id`) VALUES
('MasterCard','5565483927362917','Piotr','Detko','2021-11-11','170',1),
('MasterCard','1111111111111111','test','Test','2023-09-27','111',2);


-- CREATE USERS AND AUTHORITIES
create TABLE `users`(
`username` VARCHAR(50) NOT NULL, 
`password` CHAR(68) NOT NULL, 
`enabled` TINYINT(1) NOT NULL,
`personal_data_id` INT(11) DEFAULT NULL,
PRIMARY KEY(`username`),
CONSTRAINT `FK_PERSONAL` FOREIGN KEY(`personal_data_id`) REFERENCES `personal_data` (`id`)
)Engine = InnoDB  DEFAULT CHARSET=latin1; 

INSERT INTO `users` (username, password, enabled,personal_data_id) VALUES
('Admin','{bcrypt}$2a$04$xYBePDXsH3FfjAOr4P3UwutYbeImnBj/wL2/IiT9KgESacNMhYz8K',1,1),
('test','{bcrypt}$2a$04$xYBePDXsH3FfjAOr4P3UwutYbeImnBj/wL2/IiT9KgESacNMhYz8K',1,2);

CREATE TABLE `authorities` (
	`username`  VARCHAR(50) NOT NULL,
	`authority` VARCHAR(50) NOT NULL,
	UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
	CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`)REFERENCES `users` (`username`)
)Engine=InnoDB DEFAULT CHARSET = latin1; 

INSERT INTO `authorities` VALUES
('Admin','ROLE_ADMIN'),
('Admin','ROLE_USER'),
('test','ROLE_USER');

-- CART
CREATE TABLE `carts`(
`id` INT(11) NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) DEFAULT NULL,
`size` INT(50) DEFAULT 0,
`total_cost` FLOAT(11) DEFAULT 0, 
PRIMARY KEY (`id`), 
CONSTRAINT `USER_FK` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1; 
INSERT INTO `carts` (`username`) VALUES
('test'),
('Admin');
-- CREATING BOOKS TABLE
CREATE TABLE `books` (
`id` INT(11) NOT NULL AUTO_INCREMENT, 
`title` VARCHAR(45) DEFAULT NULL, 
`author` VARCHAR(45) DEFAULT NULL, 
`publisher` VARCHAR(45) DEFAULT NULL, 
`year` VARCHAR(4) DEFAULT NULL, 
`type` VARCHAR(45) DEFAULT NULL, 
`price` FLOAT(10) DEFAULT NULL,
`vat` INT(2) DEFAULT NULL,
`number` INT(100) DEFAULT NULL,
`enabled` BOOLEAN DEFAULT TRUE,
PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;  

-- ADDING DEFAULT BOOKS: 
INSERT INTO `books`(`title`,`author`,`publisher`,`year`,`type`,`price`,`vat`,`number`) VALUES
('Harry Potter & a philosopher stone','J.K. Rowling','Media Rodzina','2000','Fantasy','46.99','8',10),
('Harry Potter & a chamber of secrets','J.K. Rowling','Media Rodzina','2002','Fantasy','46.99','8',10),
('Lord Of The Rings: Two Towers','R.R.Tolkien','ZYSK I S-KA','2017','Fantasy','31.99','8',10),
('Game Of Thrones','George R.R. Martin','ZYSK I S-KA','2011','Fantasy','40.49','8',10),
('Snowman','Jo Nesbo','Wydawnictwo Dolnoslaskie','2017','Action','44.99','8',10),
('Disaster Artist','Greg Sestero','ZYSK I S-KA','2018','Biography','31.49','8',10),
('Macbeth','Jo Nesbo','Wydawnictwo Dolnoslaskie','2018','Action','22.15','8',10),
('Player One','Ernest Cline','Wydawnictwo Feeria','2018','Sci-fi','55.99','8',0);

-- ManyToMany CART AND BOOKS 
CREATE TABLE `cart_book` (
`id` INT(11) NOT NULL auto_increment,
`cart_id` INT(11) NOT NULL, 
`book_id` INT(11) NOT NULL,     
PRIMARY KEY (`id`), 
constraint `FK_BOOK` foreign key (`book_id`) REFERENCES `books` (`id`), 
constraint `FK_CART` foreign key (`cart_id`) REFERENCES `carts` (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1; 

CREATE TABLE `transactions`( 
`id` INT(11) NOT NULL AUTO_INCREMENT, 
`username` VARCHAR(45) DEFAULT NULL, 
`total_cost` FLOAT(11) DEFAULT 0, 
`size` INT(50) DEFAULT 0,
`adress` VARCHAR(45) DEFAULT NULL,
`credit_card_id` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`), 
CONSTRAINT `FK_USER` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
CONSTRAINT `FK_CARD` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = latin1; 

CREATE TABLE `transactions_books` (
`id` INT(11) NOT NULL auto_increment,
`transaction_id` INT(11) NOT NULL, 
`book_id` INT(11) NOT NULL,     
PRIMARY KEY (`id`), 
constraint `FK_BOOK_TRANSACTION` foreign key (`book_id`) REFERENCES `books` (`id`), 
constraint `FK_TRANSACTION_BOOK` foreign key (`transaction_id`) REFERENCES `transactions` (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1; 