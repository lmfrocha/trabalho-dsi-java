-- -----------------------------------------------------
-- Table `bdpsi`.`tipo_pessoa`
-- -----------------------------------------------------
CREATE TABLE `bdpsi`.`tipo_pessoa`(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `medico` int(11),
  `paciente` INT(11),
  `pessoa` INT(11),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- -----------------------------------------------------
-- Table `bdpsi`.`diabete`
-- -----------------------------------------------------
CREATE TABLE `bdpsi`.`diabete`(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `tipo` INT(11),
  `descricao` VARCHAR(300),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `bdpsi`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE  `bdpsi`.`pessoa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(50) NOT NULL,
  `data_nasc` DATETIME NOT NULL,
  `sexo` VARCHAR(10),
  `telefone` VARCHAR(45),
  `email` VARCHAR(80) NOT NULL,
  `login` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `paciente` INT(11),
  `medico` INT(11),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id` (`id` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `bdpsi`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE  `bdpsi`.`paciente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `peso` VARCHAR(45) NOT NULL,
  `altura` VARCHAR(45) NOT NULL,
  `diabete` INT(11) NOT NULL, 
  PRIMARY KEY (`Id`),
  INDEX `id` (`id` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `bdpsi`.`Medico`
-- -----------------------------------------------------
CREATE TABLE `bdpsi`.`medico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(45) NOT NULL,
  `crm` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `endereco_consultorio` VARCHAR(45) NOT NULL,
  `paciente` INT(11),
  PRIMARY KEY (`Id`),
  INDEX `id` (`id` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `bdpsi`.`Glicemia`
-- -----------------------------------------------------
CREATE TABLE `bdpsi`.`glicemia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_medicao` DATE NOT NULL,
  `hora_medicao` DATE NOT NULL,
  `nivel_glicemico` INT NOT NULL,
  `tipo_glicemia` VARCHAR(45) NOT NULL,
  `paciente` INT(11),
  PRIMARY KEY (`id`),
  INDEX `id` (`id` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8