-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clothing
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clothing
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clothing` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clothing` ;

-- -----------------------------------------------------
-- Table `clothing`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`image` (
  `id` BINARY(16) NOT NULL,
  `url` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`user` (
  `id` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `date_of_birth` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `enabled` BIT(1) NOT NULL,
  `fullname` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `role` INT NULL DEFAULT NULL,
  `image_id` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_i5xeor1kbd7mof6edpvkp3wsd` (`image_id` ASC) VISIBLE,
  CONSTRAINT `FK9hpx11qlu8cqhrkjn0yor93h`
    FOREIGN KEY (`image_id`)
    REFERENCES `clothing`.`image` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`customer` (
  `id` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKg2o3t8h0g17smtr9jgypagdtv`
    FOREIGN KEY (`id`)
    REFERENCES `clothing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`address` (
  `id` BINARY(16) NOT NULL,
  `district` VARCHAR(255) NULL DEFAULT NULL,
  `is_default` BIT(1) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `postal_code` VARCHAR(255) NULL DEFAULT NULL,
  `province` VARCHAR(255) NULL DEFAULT NULL,
  `specific_address` VARCHAR(255) NULL DEFAULT NULL,
  `ward` VARCHAR(255) NULL DEFAULT NULL,
  `customer` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7jsd9ypl6vxj3t9t62urp8ru4` (`customer` ASC) VISIBLE,
  CONSTRAINT `FK7jsd9ypl6vxj3t9t62urp8ru4`
    FOREIGN KEY (`customer`)
    REFERENCES `clothing`.`customer` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`admin` (
  `id` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK1ja8rua032fgnk9jmq7du3b3a`
    FOREIGN KEY (`id`)
    REFERENCES `clothing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`branch` (
  `id` BINARY(16) NOT NULL,
  `branch_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`cart` (
  `id` BINARY(16) NOT NULL,
  `customer_id` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKdebwvad6pp1ekiqy5jtixqbaj` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `FKdebwvad6pp1ekiqy5jtixqbaj`
    FOREIGN KEY (`customer_id`)
    REFERENCES `clothing`.`customer` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`product_gender`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`product_gender` (
  `id` BINARY(16) NOT NULL,
  `gender_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`category` (
  `id` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  `product_gender` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKm81jhv77993hkngh4sf9r5nq6` (`product_gender` ASC) VISIBLE,
  CONSTRAINT `FKm81jhv77993hkngh4sf9r5nq6`
    FOREIGN KEY (`product_gender`)
    REFERENCES `clothing`.`product_gender` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`product` (
  `id` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `price` DECIMAL(38,2) NOT NULL,
  `product_name` VARCHAR(255) NOT NULL,
  `branch_id` BINARY(16) NULL DEFAULT NULL,
  `category_id` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKikaed1un46tr8ey7jis9v4868` (`branch_id` ASC) VISIBLE,
  INDEX `FK1mtsbur82frn64de7balymq9s` (`category_id` ASC) VISIBLE,
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s`
    FOREIGN KEY (`category_id`)
    REFERENCES `clothing`.`category` (`id`),
  CONSTRAINT `FKikaed1un46tr8ey7jis9v4868`
    FOREIGN KEY (`branch_id`)
    REFERENCES `clothing`.`branch` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`color` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `color_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_7jag82tp83ne6q9lbjr0lf1q1` (`color_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`size`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`size` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `size_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`product_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`product_item` (
  `id` BINARY(16) NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `color_id` INT NULL DEFAULT NULL,
  `product_id` BINARY(16) NULL DEFAULT NULL,
  `size_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKcytt0le70yfgb3uupwn2xg0io` (`color_id` ASC) VISIBLE,
  INDEX `FKa9mjpi98ark8eovbtnnreygbb` (`product_id` ASC) VISIBLE,
  INDEX `FKfj4jsxj1t4wktcbain9kk1r8s` (`size_id` ASC) VISIBLE,
  CONSTRAINT `FKa9mjpi98ark8eovbtnnreygbb`
    FOREIGN KEY (`product_id`)
    REFERENCES `clothing`.`product` (`id`),
  CONSTRAINT `FKcytt0le70yfgb3uupwn2xg0io`
    FOREIGN KEY (`color_id`)
    REFERENCES `clothing`.`color` (`id`),
  CONSTRAINT `FKfj4jsxj1t4wktcbain9kk1r8s`
    FOREIGN KEY (`size_id`)
    REFERENCES `clothing`.`size` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`cart_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`cart_item` (
  `quantity` INT NULL DEFAULT NULL,
  `cart_id` BINARY(16) NOT NULL,
  `product_item` BINARY(16) NOT NULL,
  PRIMARY KEY (`cart_id`, `product_item`),
  INDEX `FK9r23ekl2nv2wq5lfes32nlhv6` (`product_item` ASC) VISIBLE,
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3`
    FOREIGN KEY (`cart_id`)
    REFERENCES `clothing`.`cart` (`id`),
  CONSTRAINT `FK9r23ekl2nv2wq5lfes32nlhv6`
    FOREIGN KEY (`product_item`)
    REFERENCES `clothing`.`product_item` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`coupon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`coupon` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `discount_value` DECIMAL(38,2) NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'EXPIRED', 'DISABLED') NULL DEFAULT NULL,
  `minimum_bill` DECIMAL(38,2) NULL DEFAULT NULL,
  `coupon_name` VARCHAR(255) NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`forgot_password`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`forgot_password` (
  `id` BIGINT NOT NULL,
  `expiry_date` DATETIME(6) NULL DEFAULT NULL,
  `otp` INT NULL DEFAULT NULL,
  `user_id` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_ss96nm4ed1jmllpxib14p1r7v` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK95rqabtnw8wouua80mbixrq4`
    FOREIGN KEY (`user_id`)
    REFERENCES `clothing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`forgot_password_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`forgot_password_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`import_invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`import_invoice` (
  `id` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `total` DECIMAL(38,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`import_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`import_item` (
  `quantity` INT NULL DEFAULT NULL,
  `import_id` BINARY(16) NOT NULL,
  `product_item` BINARY(16) NOT NULL,
  PRIMARY KEY (`import_id`, `product_item`),
  INDEX `FKt364bxmnlfyeare5mbcwacdyx` (`product_item` ASC) VISIBLE,
  CONSTRAINT `FK795r40a4jujkwuh6cgekoygst`
    FOREIGN KEY (`import_id`)
    REFERENCES `clothing`.`import_invoice` (`id`),
  CONSTRAINT `FKt364bxmnlfyeare5mbcwacdyx`
    FOREIGN KEY (`product_item`)
    REFERENCES `clothing`.`product_item` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`payment_method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`payment_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `image` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7k3w5avendtf80l1x8a09lqjh` (`image` ASC) VISIBLE,
  CONSTRAINT `FK7k3w5avendtf80l1x8a09lqjh`
    FOREIGN KEY (`image`)
    REFERENCES `clothing`.`image` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`orders` (
  `id` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `canceled_at` DATETIME(6) NULL DEFAULT NULL,
  `completed_at` DATETIME(6) NULL DEFAULT NULL,
  `note` VARCHAR(255) NULL DEFAULT NULL,
  `status` ENUM('PENDING', 'CANCELED', 'DELIVERED', 'COMPLETED') NULL DEFAULT NULL,
  `payment_at` DATETIME(6) NULL DEFAULT NULL,
  `shipping_at` DATETIME(6) NULL DEFAULT NULL,
  `shipping_fee` DECIMAL(38,2) NULL DEFAULT NULL,
  `total` DECIMAL(38,2) NULL DEFAULT NULL,
  `shipping_address` BINARY(16) NULL DEFAULT NULL,
  `coupon` INT NULL DEFAULT NULL,
  `customer_id` BINARY(16) NULL DEFAULT NULL,
  `payment_method` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKtd3ktxfksudg5ncck099nqn02` (`shipping_address` ASC) VISIBLE,
  INDEX `FKp66gcqrhfnwkxr9vpygtoye6m` (`coupon` ASC) VISIBLE,
  INDEX `FK624gtjin3po807j3vix093tlf` (`customer_id` ASC) VISIBLE,
  INDEX `FKcy9w3ml6rxpaxi8rw82po4cim` (`payment_method` ASC) VISIBLE,
  CONSTRAINT `FK624gtjin3po807j3vix093tlf`
    FOREIGN KEY (`customer_id`)
    REFERENCES `clothing`.`customer` (`id`),
  CONSTRAINT `FKcy9w3ml6rxpaxi8rw82po4cim`
    FOREIGN KEY (`payment_method`)
    REFERENCES `clothing`.`payment_method` (`id`),
  CONSTRAINT `FKp66gcqrhfnwkxr9vpygtoye6m`
    FOREIGN KEY (`coupon`)
    REFERENCES `clothing`.`coupon` (`id`),
  CONSTRAINT `FKtd3ktxfksudg5ncck099nqn02`
    FOREIGN KEY (`shipping_address`)
    REFERENCES `clothing`.`address` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`order_item` (
  `quantity` INT NULL DEFAULT NULL,
  `order_id` BINARY(16) NOT NULL,
  `product_item` BINARY(16) NOT NULL,
  PRIMARY KEY (`order_id`, `product_item`),
  INDEX `FK7rxsmwbol45d97bw56463eaq0` (`product_item` ASC) VISIBLE,
  CONSTRAINT `FK7rxsmwbol45d97bw56463eaq0`
    FOREIGN KEY (`product_item`)
    REFERENCES `clothing`.`product_item` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt`
    FOREIGN KEY (`order_id`)
    REFERENCES `clothing`.`orders` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`product_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`product_image` (
  `product_id` BINARY(16) NOT NULL,
  `image_id` BINARY(16) NOT NULL,
  INDEX `FK36hxrhwpcax6k2t3gbp3ykw7o` (`image_id` ASC) VISIBLE,
  INDEX `FKp8dh695dqdfcrhs57f0wa30ip` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FK36hxrhwpcax6k2t3gbp3ykw7o`
    FOREIGN KEY (`image_id`)
    REFERENCES `clothing`.`product` (`id`),
  CONSTRAINT `FKp8dh695dqdfcrhs57f0wa30ip`
    FOREIGN KEY (`product_id`)
    REFERENCES `clothing`.`image` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`promotion` (
  `id` BINARY(16) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` BINARY(16) NULL DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  `end_date` DATETIME(6) NULL DEFAULT NULL,
  `status` ENUM('ACTIVE', 'EXPIRED', 'DISABLED') NULL DEFAULT NULL,
  `promotion_name` VARCHAR(255) NULL DEFAULT NULL,
  `discount_rate` DECIMAL(38,2) NULL DEFAULT NULL,
  `start_date` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`product_promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`product_promotion` (
  `product_id` BINARY(16) NOT NULL,
  `promotion_id` BINARY(16) NOT NULL,
  INDEX `FKqpyym6dque0lhm9l2ijmpooll` (`promotion_id` ASC) VISIBLE,
  INDEX `FKqn445u9gm02n2r29lwglyekqn` (`product_id` ASC) VISIBLE,
  CONSTRAINT `FKqn445u9gm02n2r29lwglyekqn`
    FOREIGN KEY (`product_id`)
    REFERENCES `clothing`.`promotion` (`id`),
  CONSTRAINT `FKqpyym6dque0lhm9l2ijmpooll`
    FOREIGN KEY (`promotion_id`)
    REFERENCES `clothing`.`product` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`refresh_token` (
  `id` BINARY(16) NOT NULL,
  `expired` DATETIME(6) NULL DEFAULT NULL,
  `refresh_token` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` BINARY(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_f95ixxe7pa48ryn1awmh2evt7` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKfgk1klcib7i15utalmcqo7krt`
    FOREIGN KEY (`user_id`)
    REFERENCES `clothing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clothing`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clothing`.`staff` (
  `id` BINARY(16) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKbexrkamkp6kq5mi23v5dgodnw`
    FOREIGN KEY (`id`)
    REFERENCES `clothing`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
