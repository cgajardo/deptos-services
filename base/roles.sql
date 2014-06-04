CREATE  TABLE `deptos`.`roles` (
  `roleId` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(500) NULL ,
  PRIMARY KEY (`roleId`) )
ENGINE = InnoDB;