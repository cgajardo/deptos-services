CREATE TABLE `deptos`.`votings` (
  `votingId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NOT NULL,
  `description` VARCHAR(500) NULL,
  `buildingId` INT UNSIGNED NOT NULL,
  `userId` INT UNSIGNED NOT NULL,
  `dateCreated` DATETIME NOT NULL,
  `deleted` INT UNSIGNED NOT NULL DEFAULT 0,
  `posibleAnswers` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`votingId`),
  INDEX `fk_votings_buildings_idx` (`buildingId` ASC),
  INDEX `fk_votings_users_idx` (`userId` ASC),
  CONSTRAINT `fk_votings_buildings`
    FOREIGN KEY (`buildingId`)
    REFERENCES `deptos`.`buildings` (`buildingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_votings_users`
    FOREIGN KEY (`userId`)
    REFERENCES `deptos`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);