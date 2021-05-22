CREATE TABLE IF NOT EXISTS `user` (
                        `id` SERIAL NOT NULL,
                        `username` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                        `email` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `task` (
                        `id` SERIAL NOT NULL,
                        `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                        `desc` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                        `user_id` BIGINT NOT NULL,
                        FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB;