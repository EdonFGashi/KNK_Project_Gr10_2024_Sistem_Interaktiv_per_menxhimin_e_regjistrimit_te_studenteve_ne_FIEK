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
