CREATE SCHEMA `messages_service_db` ;

CREATE TABLE `messages_service_db`.`chat_media_files` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `filename` VARCHAR(65) NOT NULL,
  `extention` VARCHAR(11) NOT NULL,
  `media` LONGBLOB NULL DEFAULT NULL,
  `mimetype` VARCHAR(15) NULL DEFAULT NULL,
  `thumbnail` LONGBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `messages_service_db`.`chats` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `icon` INT NULL DEFAULT NULL,
  `additional_info` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `messages_service_db`.`chat_users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `chat_id` INT NOT NULL DEFAULT -1,
  `user_id` INT NOT NULL,
  `private_user` INT NOT NULL DEFAULT -1,
  `is_new` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `chatid_idx` (`chat_id` ASC),
  CONSTRAINT `chatid`
    FOREIGN KEY (`chat_id`)
    REFERENCES `messages_service_db`.`chats` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE `messages_service_db`.`chat_messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author_id` INT NOT NULL,
  `reciever_id` INT NOT NULL,
  `chat_id` INT NOT NULL,
  `text` TEXT NULL DEFAULT NULL,
  `mediafile_id` INT NULL,
  `stamp` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edit_stamp` DATETIME NULL DEFAULT NULL,
  `deleted` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `chat_idx` (`chat_id` ASC),
  INDEX `media_idx` (`mediafile_id` ASC),
  CONSTRAINT `chat`
    FOREIGN KEY (`chat_id`)
    REFERENCES `messages_service_db`.`chats` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `media`
    FOREIGN KEY (`mediafile_id`)
    REFERENCES `messages_service_db`.`chat_media_files` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE `messages_service_db`.`chat_messages_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message_id` INT NOT NULL,
  `author_id` INT NOT NULL,
  `reciever_id` INT NOT NULL,
  `chat_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `chat_idx` (`chat_id` ASC),
  INDEX `msg_idx` (`message_id` ASC),
  CONSTRAINT `chat_id`
    FOREIGN KEY (`chat_id`)
    REFERENCES `messages_service_db`.`chats` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `msg`
    FOREIGN KEY (`message_id`)
    REFERENCES `messages_service_db`.`chat_messages` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);