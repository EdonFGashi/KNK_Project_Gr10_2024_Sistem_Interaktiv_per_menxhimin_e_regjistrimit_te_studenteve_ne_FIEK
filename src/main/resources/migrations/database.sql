CREATE TABLE tblUser (
    uid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Gjinia VARCHAR(10),
    DataLindjes DATE,
    Username VARCHAR(50) NOT NULL,
    Salt VARCHAR(50) NOT NULL,
    PasswordHash VARCHAR(400)NOT NULL
);

CREATE TABLE tblAdmin (
    aid INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Salt VARCHAR(50) NOT NULL,
    PasswordHash VARCHAR(400) NOT NULL
);

CREATE TABLE tblMbikqyresi (
    mid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    Username VARCHAR(50) NOT NULL,
    Salt VARCHAR(50) NOT NULL,
    PasswordHash VARCHAR(400) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Afati DATE,
    Salla VARCHAR(50)
);

CREATE TABLE tblAdresa (
    adid INT AUTO_INCREMENT PRIMARY KEY,
    Qyteti VARCHAR(50) NOT NULL,
    Shteti VARCHAR(50) NOT NULL
);

CREATE TABLE tblStudentApplicant (
    sid INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(50) NOT NULL,
    Mbiemri VARCHAR(50) NOT NULL,
    NrPersonal VARCHAR(20) NOT NULL,
    Email VARCHAR(100),
    NrTel VARCHAR(20),
    Nacionaliteti VARCHAR(50),
    uid INT,
    adid INT,
    mid INT,
    FOREIGN KEY (uid) REFERENCES tblUser(uid) ON DELETE SET NULL,
    FOREIGN KEY (adid) REFERENCES tblAdresa(adid) ON DELETE SET NULL,
    FOREIGN KEY (mid) REFERENCES tblMbikqyresi(mid) ON DELETE SET NULL
);



CREATE TABLE tblAfati (
    afid INT AUTO_INCREMENT PRIMARY KEY,
    Niveli VARCHAR(50),
    Viti INT,
    DataHapjes DATE NOT NULL,
    DataMbylljes DATE NOT NULL
);


CREATE TABLE tblDepartamenti (
    did INT AUTO_INCREMENT PRIMARY KEY,
    Emri VARCHAR(100) NOT NULL,
    NumriPranimeveAfat1 INT,
    NumriPranimeveAfat2 INT,
    NumriMinoritetAfat1 INT,
    NumriMinoritetAfat2 INT
);

CREATE TABLE tblDrejtimi (
    sid INT PRIMARY KEY,
    Niveli VARCHAR(255),
    did INT,
    FOREIGN KEY (did) REFERENCES tblDepartamenti(did) ON DELETE SET NULL,
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
    NotaMesatare DOUBLE,
 FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid)
);

CREATE TABLE tblShkolla (
    shid INT AUTO_INCREMENT,
    Emri VARCHAR(255),
    adid INT,
    PRIMARY KEY (shid,adid),
    FOREIGN KEY (adid) REFERENCES tblAdresa(adid)
);


CREATE TABLE tblProvimiPranues (
    sid INT PRIMARY KEY,
    aid INT,
    mid INT,
    Salla INT,
    Piket INT,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
    FOREIGN KEY (aid) REFERENCES tblAfati(afid) ON DELETE SET NULL,
    FOREIGN KEY (mid) REFERENCES tblMbikqyresi(mid) ON DELETE SET NULL

);


CREATE TABLE tblKonkurrimet (
    kid INT AUTO_INCREMENT PRIMARY KEY,
    sid INT,
    afid INT,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
    FOREIGN KEY (afid) REFERENCES tblAfati(afid) ON DELETE SET NULL
);

CREATE TABLE tblAcceptedStudents (
    id INT PRIMARY KEY,
    sid INT,
    did INT,
    aid INT,
    FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
    FOREIGN KEY (did) REFERENCES tblDepartamenti(did) ON DELETE SET NULL,
    FOREIGN KEY (aid) REFERENCES tblAfati(afid) ON DELETE SET NULL
);

CREATE TABLE tblRegisteredStudents (
    StudentId INT PRIMARY KEY,
    sid INT,
    did INT,
    Email VARCHAR(255),
    EmailGenerated VARCHAR(255),
    viti INT,
FOREIGN KEY (sid) REFERENCES tblStudentApplicant(sid),
 FOREIGN KEY (did) REFERENCES tblDepartamenti(did) ON DELETE SET NULL
);

CREATE TABLE tblNjoftimet (
    id INT PRIMARY KEY,
    aid INT,
    Njoftimi VARCHAR(255),
 FOREIGN KEY (aid) REFERENCES tblAdmin(aid) ON DELETE SET NULL
);

ALTER TABLE tblAfati
ADD CONSTRAINT Check_Opening_Date_Before_Closing_Date CHECK (DataHapjes < DataMbylljes);



ALTER TABLE tblProvimiPranues
ADD CONSTRAINT Check_Exam_Score_Range CHECK (Piket BETWEEN 0 AND 100);


ALTER TABLE tblDocument
ADD CONSTRAINT Check_Not_Empty_Document CHECK (COALESCE(LENGTH(CertifikataNotave), 0) + COALESCE(LENGTH(Leternjoftimi), 0) + COALESCE(LENGTH(Diploma), 0) > 0);



ALTER TABLE tblStudentApplicant
ADD CONSTRAINT Check_Valid_Age CHECK (YEAR(CURRENT_DATE) - YEAR(DataLindjes) > 17);
