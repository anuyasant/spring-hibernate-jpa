DROP SCHEMA IF EXISTS `Spring-RestSpringSecurityHibernate`;

CREATE SCHEMA `Spring-RestSpringSecurityHibernate`;

use `Spring-RestSpringSecurityHibernate`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `instructor_detail`;

CREATE TABLE `instructor_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `youtube_channel` varchar(128) DEFAULT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
  KEY `FK_DETAIL_idx` (`instructor_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `instructor`;

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `instructor_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`title`),
  
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `instructor` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(256) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_COURSE_ID_idx` (`course_id`),

  CONSTRAINT `FK_COURSE` 
  FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `course_student`;

CREATE TABLE `course_student` (
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  
  PRIMARY KEY (`course_id`,`student_id`),
  
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) 
  REFERENCES `student` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--Composite primary key
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `company_id` VARCHAR(20) NOT NULL,
  `employee_id` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100),
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  
  PRIMARY KEY (`company_id`,`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

Insert into employee values("A-1","1", "abc@a1.com", "abc","abccba");
Insert into employee values("B-1","2", "xyz@c1.com", "xyz","xyzzyx");
Insert into employee values("A-1","3", "lmn@a1.com", "lmn","lmnnml");
Insert into employee values("C-1","4", "pqr@c1.com", "pqr","pqrrqp");
Insert into employee values("A-1","5", "jkl@a1.com", "jkl","jkllkj");
commit;

SET FOREIGN_KEY_CHECKS = 1;
