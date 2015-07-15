--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `t_mvcdemo_coursetree` DROP PRIMARY KEY;

ALTER TABLE `t_mvcdemo_coursetree` DROP INDEX `ID`;

DROP TABLE `t_mvcdemo_coursetree`;

CREATE TABLE `t_mvcdemo_coursetree` (
	`ID` INT NOT NULL,
	`PID` INT,
	`name` VARCHAR(100),
	`rootId` INT DEFAULT -1,
	`url` VARCHAR(255),
	`No` VARCHAR(50),
	`Type` VARCHAR(50),
	`bz` VARCHAR(50),
	`eduId` VARCHAR(50),
	`imgSrc` VARCHAR(50),
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX `ID` ON `t_mvcdemo_coursetree` (`ID` ASC);

