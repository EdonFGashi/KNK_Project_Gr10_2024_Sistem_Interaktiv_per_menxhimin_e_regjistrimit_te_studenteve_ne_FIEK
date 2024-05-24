use knk_project_gr10;


CREATE TABLE tblAdmin (
    adminId INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    emri VARCHAR(50)  NOT NULL,
    mbiemri VARCHAR(50) NOT NULL,
    salt VARCHAR(100) NOT NULL,
    passwordHash VARCHAR(400) NOT NULL
);

CREATE TABLE tblUser(
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(50)  NOT NULL,
    salt VARCHAR(100) NOT NULL,
    passwordHash VARCHAR(400) NOT NULL
);


CREATE TABLE tblPerson (
    numriPersonal VARCHAR(20) PRIMARY KEY,
    emri VARCHAR(50) NOT NULL,
    mbiemri VARCHAR(50) NOT NULL,
    nacionaliteti VARCHAR(50) NOT NULL,
    qyteti VARCHAR(50) NOT NULL,
    shteti VARCHAR(50) NOT NULL,
    gjinia VARCHAR(10) NOT NULL,
    dataLindjes DATE NOT NULL
);
CREATE TABLE tblSemsStaf (
    stafId INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    emri VARCHAR(50) NOT NULL,
    mbiemri VARCHAR(50) NOT NULL,
    salt VARCHAR(50) NOT NULL,
    passwordHash VARCHAR(50) NOT NULL,
    facultyId INT NOT NULL
);

CREATE TABLE tblMbikqyresi (
    mbikqyresiId INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    emri VARCHAR(50),
    mbiemri VARCHAR(50),
    salt VARCHAR(100) NOT NULL,
    passwordHash VARCHAR(400) NOT NULL
);

CREATE TABLE tblUserStudent (
    userId INT PRIMARY KEY,
    numriPersonal VARCHAR(20) NOT NULL,
    email VARCHAR(50),
    emri VARCHAR(50),
    mbiemri VARCHAR(50),
    nacionaliteti VARCHAR(50),
    qyteti VARCHAR(50),
    shteti VARCHAR(50),
    gjinia VARCHAR(10),
    dataLindjes DATE,
    FOREIGN KEY (userId) REFERENCES tblUser(userId) ON DELETE CASCADE
);
CREATE TABLE tblShkollaMesme (
    shkollaId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    emriShkolles VARCHAR(50),
    piketMat INT,
    piketGjSh INT,
    piketAng INT,
    piketZgjedhore INT,
    lendaZgjedhore VARCHAR(50),
    suksesiKl10 INT,
    suksesiKl11 INT,
    suksesiKl12 INT,
    certifikataNotave LONGBLOB,
    leternjoftimi LONGBLOB,
    diplomaShkolles LONGBLOB,
    FOREIGN KEY (userId) REFERENCES tblUserStudent(userId) ON DELETE CASCADE
);
CREATE TABLE tblDepartamenti (
    deptId INT AUTO_INCREMENT PRIMARY KEY,
    emri VARCHAR(50) NOT NULL,
    niveli VARCHAR(50) NOT NULL,
    nrStudentaveAfat1 INT,
    nrStudentaveAfat2 INT,
    nrStudentaveMinoritetAfat1 INT,
    nrStudentaveMinoritetAfat2 INT
);
CREATE TABLE tblAfati (
    afatId INT AUTO_INCREMENT PRIMARY KEY,
    hera VARCHAR(50) NOT NULL,
    viti INT NOT NULL,
    dataHapjes DATE,
    dataMbylljes DATE,
    niveli VARCHAR(50)
);
CREATE TABLE tblAplikimi (
    aplikimiId INT AUTO_INCREMENT PRIMARY KEY,
    shkollaId INT,
    deptIdPrioritet1 INT,
    deptIdPrioritet2 INT,
    deptIdPrioritet3 INT,
    deptIdPrioritet4 INT,
    afatId INT,
	FOREIGN KEY (shkollaId) REFERENCES tblShkollaMesme(shkollaId) ON DELETE SET NULL,
	FOREIGN KEY (deptIdPrioritet1) REFERENCES tblDepartamenti(deptId) ON DELETE SET NULL,
	FOREIGN KEY (deptIdPrioritet2) REFERENCES tblDepartamenti(deptId) ON DELETE SET NULL,
	FOREIGN KEY (deptIdPrioritet3) REFERENCES tblDepartamenti(deptId) ON DELETE SET NULL,
	FOREIGN KEY (deptIdPrioritet4) REFERENCES tblDepartamenti(deptId) ON DELETE SET NULL,
	FOREIGN KEY (afatId) REFERENCES tblAfati(afatId) ON DELETE SET NULL
);
CREATE TABLE tblProvimiPranues (
    provimiId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    mbikqyresiId INT,
    piket INT NOT NULL,
	FOREIGN KEY (userId) REFERENCES tblUserStudent(userId) ON DELETE SET NULL,
	FOREIGN KEY (mbikqyresiId) REFERENCES tblMbikqyresi(mbikqyresiId) ON DELETE SET NULL
);
CREATE TABLE tblKonkurimet (
    konkurronId INT AUTO_INCREMENT PRIMARY KEY,
    aplikimiId INT,
    provimiId INT,
	FOREIGN KEY (aplikimiId) REFERENCES tblAplikimi(aplikimiId) ON DELETE SET NULL
);
CREATE TABLE tblRegisteredStudents (
    regId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    generatedEmail VARCHAR(50),
    registeredEmail VARCHAR(50),
    emriDepartamentit VARCHAR(50),
    niveli VARCHAR(50),
	FOREIGN KEY (userId) REFERENCES tblUserStudent(userId) ON DELETE SET NULL
);
CREATE TABLE tblNjoftimet (
    njoftimId INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT,
    adminId INT,
	FOREIGN KEY (adminId) REFERENCES tblAdmin(adminId) ON DELETE SET NULL
);



