CREATE  TABLE `deptos`.`users` (
  `userId` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `firstname` VARCHAR(100) NOT NULL ,
  `secondname` VARCHAR(100) NULL ,
  `lastname` VARCHAR(100) NOT NULL ,
  `secondlastname` VARCHAR(100) NULL ,
  `email` VARCHAR(200) NOT NULL ,
  `password` VARCHAR(40) NOT NULL ,
  `rut` VARCHAR(50) NOT NULL ,
  `phone` VARCHAR(15) NULL ,
  `cellphone` VARCHAR(15) NULL ,
  `roleId` INT UNSIGNED NOT NULL ,
  `deptoNumber` VARCHAR(45) NOT NULL ,
  `buildingId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`userId`) ,
  INDEX `fk_users_roles_idx` (`roleId` ASC) ,
  INDEX `fk_users_buildings_idx` (`buildingId` ASC) ,
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`roleId` )
    REFERENCES `deptos`.`roles` (`roleId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_buildings`
    FOREIGN KEY (`buildingId` )
    REFERENCES `deptos`.`buildings` (`buildingId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
