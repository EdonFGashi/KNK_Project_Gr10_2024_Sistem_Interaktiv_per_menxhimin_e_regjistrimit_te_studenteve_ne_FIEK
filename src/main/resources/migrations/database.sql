CREATE TABLE tblUser (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50),
    Mbiemri VARCHAR(50),
    Gjinia VARCHAR(10),
    DataLindjes DATE,
    Username VARCHAR(50),
    Salt VARCHAR(50),
    PasswordHash VARCHAR(100)
);

CREATE TABLE tblAdmin (
    aid INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50),
    Salt VARCHAR(50),
    PasswordHash VARCHAR(100)
);

CREATE TABLE tblMbikqyresi (
    mid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50),
    Mbiemri VARCHAR(50),
    Username VARCHAR(50),
    Salt VARCHAR(50),
    PasswordHash VARCHAR(100),
    Email VARCHAR(100),
    Afati DATE,
    Salla VARCHAR(50)
);

CREATE TABLE tblAdresa (
    adid INT AUTO_INCREMENT PRIMARY KEY,
    Qyteti VARCHAR(50),
    Shteti VARCHAR(50)
);

CREATE TABLE tblStudentApplicant (
    sid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50),
    Mbiemri VARCHAR(50),
    NrPersonal VARCHAR(20),
    Email VARCHAR(100),
    NrTel VARCHAR(20),
    Nacionaliteti VARCHAR(50),
    uid INT,
    adid INT,
    mid INT,
    FOREIGN KEY (uid) REFERENCES tblUser(uid),
    FOREIGN KEY (adid) REFERENCES tblAdresa(adid),
    FOREIGN KEY (mid) REFERENCES tblMbikqyresi(mid)
);



CREATE TABLE tblAfati (
    afid INT AUTO_INCREMENT PRIMARY KEY,
    Niveli VARCHAR(50),
    Viti INT,
    DataHapjes DATE,
    DataMbylljes DATE
);


CREATE TABLE tblDepartamenti (
    did INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(100),
    NumriPranimeveAfat1 INT,
    NumriPranimeveAfat2 INT,
    NumriMinoritetAfat1 INT,
    NumriMinoritetAfat2 INT
);

CREATE TABLE tblDrejtimi (
    sid INT PRIMARY KEY,
    Niveli VARCHAR(255),
    did INT,
    FOREIGN KEY (did) REFERENCES tblDepartamenti(did),
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid) 
);
CREATE TABLE tblDocument (
    sid INT PRIMARY KEY,
    CertifikataNotave LONGBLOB,
    Leternjoftimi LONGBLOB,
    Diploma LONGBLOB,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid)
);

CREATE TABLE tblShkollimiMesem (
    sid INT PRIMARY KEY,
    piketMat INT,
    piketAnglisht INT,
    piketShqip INT,
    piketLendaZgjedhore INT,
    lendaZgjedhore VARCHAR(255),
    viti INT,
    piketMatureTotal INT,
    NotaMesatare INT,
 FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid)
);

CREATE TABLE tblShkolla (
    shid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(255),
    adid INT PRIMARY KEY,
    FOREIGN KEY (adid) REFERENCES tblAdresa(adid)
);


CREATE TABLE tblProvimiPranues (
    sid INT PRIMARY KEY,
    student_applicant_id INT,
    aid INT,
    mid INT,
    Salla INT,
    Piket INT,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
    FOREIGN KEY (aid) REFERENCES tblAfati(afid),
    FOREIGN KEY (mid) REFERENCES tblMbikqyresi(mid)

);


CREATE TABLE tblKonkurrimet (
    kid INT AUTO_INCREMENT PRIMARY KEY,
    sid INT,
    afid INT,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
    FOREIGN KEY (afid) REFERENCES tblAfati(afid)
);

CREATE TABLE tblAcceptedStudents (
    id INT PRIMARY KEY,
    sid INT,
    did INT,
    aid INT,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
    FOREIGN KEY (did) REFERENCES tblDepartamenti(did),
    FOREIGN KEY (aid) REFERENCES tblAfati(aid)
);

CREATE TABLE tblRegisteredStudents (
    StudentId INT PRIMARY KEY,
    sid INT,
    did INT,
    Email VARCHAR(255),
    EmailGenerated VARCHAR(255),
    viti INT,
FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
 FOREIGN KEY (did) REFERENCES tblDepartamenti(did),
);

CREATE TABLE tblNjoftimet (
    id INT PRIMARY KEY,
    aid INT,
    Njoftimi VARCHAR(255),
 FOREIGN KEY (aid) REFERENCES tblAdmin(id)
);
