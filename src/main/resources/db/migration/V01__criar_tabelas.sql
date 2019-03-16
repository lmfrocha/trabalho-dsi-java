-- -----------------------------------------------------
-- Table `bdpsi`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE  `bdpsi`.`Pessoa` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(50) NOT NULL,
  `Sobrenome` VARCHAR(50) NOT NULL,
  `DataNasc` DATETIME NOT NULL,
  `Sexo` VARCHAR(10) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE INDEX `idPessoa_UNIQUE` (`Id` ASC))
ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `bdpsi`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE  `bdpsi`.`Paciente` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Peso` VARCHAR(45) NOT NULL,
  `Altura` VARCHAR(45) NOT NULL,
  `TipoDiabetes` VARCHAR(45) NOT NULL,
  `Terapia` VARCHAR(45) NOT NULL,
  `Diagnostico` DATETIME NOT NULL,
  `ObjetivoPreRefeicao` INT NOT NULL,
  `ObjetivoPosRefeicao` INT NOT NULL,
  `ObjetivoAntesDormir` INT NOT NULL,
  `IdPessoa` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `IdPessoa_idx` (`IdPessoa` ASC),
  CONSTRAINT `IdPessoa`
    FOREIGN KEY (`IdPessoa`)
    REFERENCES `bdpsi`.`Pessoa` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `bdpsi`.`Medico`
-- -----------------------------------------------------
CREATE TABLE `bdpsi`.`Medico` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Cpf` VARCHAR(45) NOT NULL,
  `Crm` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `EnderecoConsultorio` VARCHAR(45) NOT NULL,
  `IdPessoa` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `IdPessoa_idx` (`IdPessoa` ASC)
  )
ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `bdpsi`.`Glicemia`
-- -----------------------------------------------------
CREATE TABLE `bdpsi`.`Glicemia` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `DataMedicao` DATE NOT NULL,
  `HoraMedicao` VARCHAR(30) NOT NULL,
  `NivelGlicemico` INT NOT NULL,
  `TipoGlicemia` VARCHAR(45) NOT NULL,
  `IdPaciente` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `IdPaciente_idx` (`IdPaciente` ASC),
  CONSTRAINT `IdPaciente`
    FOREIGN KEY (`IdPaciente`)
    REFERENCES `bdpsi`.`Paciente` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8;