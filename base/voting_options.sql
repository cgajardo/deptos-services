CREATE TABLE `deptos`.`voting_options` (
  `optionId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(250) NOT NULL,
  `votingId` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`optionId`),
  INDEX `fg_voting_options_votings_idx` (`votingId` ASC),
  CONSTRAINT `fg_voting_options_votings`
    FOREIGN KEY (`votingId`)
    REFERENCES `deptos`.`votings` (`votingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);