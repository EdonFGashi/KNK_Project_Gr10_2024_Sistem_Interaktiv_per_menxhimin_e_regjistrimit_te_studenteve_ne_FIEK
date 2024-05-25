insert into tblAdmin (email,emri,mbiemri,salt,passwordHash)
values ("edon@admin.uni-pr.edu","Edon","Gashi","jmsiV++dDMDe7FXCx0Qn/S8M7IUcuht8L1YeU95V5Tg=","6a6d7369562b2b64444d4465374658437830516e2f53384d37495563756874384c315965553935563554673dd75688ba54e10e904f243faa0d486f4a7bebbb19cdcdcb78bf2b158c6058cbe5");


-- Shtim i kolones per gjenerume
ALTER TABLE tblRegisteredStudents
CHANGE registeredEmail generatedId VARCHAR(50);

ALTER TABLE tblShkollaMesme
ADD COLUMN approved BOOLEAN DEFAULT TRUE;


CREATE VIEW UserStudentRegisteredView AS
SELECT
    s.userId AS userStudentUserId,
    s.numriPersonal,
    us.email,
    s.emri,
    s.mbiemri,
    s.nacionaliteti,
    s.qyteti,
    s.shteti,
    s.gjinia,
    s.dataLindjes,
    us.salt,
    us.passwordHash,
    r.generatedEmail,
    r.generatedId,
    r.emriDepartamentit,
    r.niveli
FROM tblUserStudent s
JOIN tblRegisteredStudents r ON s.userId = r.userId
JOIN tblUser us ON s.userId = us.userId;




-- Dummy Values

INSERT INTO tblUserStudent (numriPersonal, email, emri, mbiemri, nacionaliteti, qyteti, shteti, gjinia, dataLindjes, salt, passwordHash)
VALUES
    ('1234567890', 'john@example.com', 'John', 'Doe', 'American', 'New York', 'USA', 'M', '1990-05-15', 'salt123', 'hash123'),
    ('0987654321', 'jane@example.com', 'Jane', 'Smith', 'British', 'London', 'UK', 'F', '1992-08-20', 'salt456', 'hash456'),
    ('1111111111', 'michael@example.com', 'Michael', 'Johnson', 'Canadian', 'Toronto', 'Canada', 'M', '1995-03-10', 'salt789', 'hash789'),
    ('2222222222', 'emily@example.com', 'Emily', 'Brown', 'Australian', 'Sydney', 'Australia', 'F', '1994-11-28', 'saltabc', 'hashabc'),
    ('3333333333', 'david@example.com', 'David', 'Taylor', 'German', 'Berlin', 'Germany', 'M', '1991-07-03', 'saltxyz', 'hashxyz'),
    ('4444444444', 'sophia@example.com', 'Sophia', 'Wilson', 'French', 'Paris', 'France', 'F', '1993-09-17', 'saltuvw', 'hashuvw'),
    ('5555555555', 'alex@example.com', 'Alex', 'Anderson', 'Italian', 'Rome', 'Italy', 'M', '1996-02-25', 'salt123', 'hash123'),
    ('6666666666', 'olivia@example.com', 'Olivia', 'Martinez', 'Spanish', 'Madrid', 'Spain', 'F', '1990-12-08', 'salt456', 'hash456'),
    ('7777777777', 'william@example.com', 'William', 'Garcia', 'Chinese', 'Beijing', 'China', 'M', '1992-06-14', 'salt789', 'hash789'),
    ('8888888888', 'ava@example.com', 'Ava', 'Rodriguez', 'Indian', 'Mumbai', 'India', 'F', '1995-04-30', 'saltabc', 'hashabc');

-- Insert dummy values into tblRegisteredStudents
INSERT INTO tblRegisteredStudents (userId, generatedEmail, generatedId, emriDepartamentit, niveli)
VALUES
    (1, 'john123@example.com', 'john@example.com', 'Computer Science', 'Bachelor'),
    (2, 'jane456@example.com', 'jane@example.com', 'Engineering', 'Master'),
    (3, 'michael789@example.com', 'michael@example.com', 'Business Administration', 'Bachelor'),
    (4, 'emilyabc@example.com', 'emily@example.com', 'Psychology', 'Master'),
    (5, 'davidxyz@example.com', 'david@example.com', 'Medicine', 'Doctorate');

-- Insert dummy values into tblShkollaMesme
INSERT INTO tblShkollaMesme (userId, emriShkolles, piketMat, piketGjSh, piketAng, piketZgjedhore, lendaZgjedhore, suksesiKl10, suksesiKl11, suksesiKl12, approved)
VALUES
    (1, 'High School 1', 80, 75, 85, 70, 'Physics', 80, 75, 85, TRUE),
    (2, 'High School 2', 75, 80, 70, 85, 'Chemistry', 75, 80, 70, TRUE),
    (3, 'High School 3', 85, 70, 80, 75, 'Biology', 85, 70, 80, TRUE),
    (4, 'High School 4', 70, 85, 75, 80, 'Mathematics', 70, 85, 75, TRUE),
    (5, 'High School 5', 80, 75, 85, 70, 'English', 80, 75, 85, TRUE);



-- Shkruani edhe daten para se me e qit ni query

-- 15/05/2024 - Joni
ALTER TABLE tblKonkurimet
ADD COLUMN piketPranues INT NOT NULL;

ALTER TABLE tblKonkurimet
ADD COLUMN mbikqyresiId INT,
ADD FOREIGN KEY (mbikqyresiId) REFERENCES tblMbikqyresi(mbikqyresiId) ON DELETE SET NULL;

DROP table tblProvimiPranues;


--15/05/2024 - Edoni
CREATE TABLE tblArkiva (
     documentId INT AUTO_INCREMENT PRIMARY KEY,
     nrSerik VARCHAR(50) NOT NULL,
     idStudentit VARCHAR(50) NOT NULL,
     data DATE NOT NULL
);

-- 15/05/2024 - Joni
ALTER TABLE tblNjoftimet
MODIFY COLUMN text VARCHAR(2000);

INSERT INTO tblAdmin (email,emri,mbiemri,salt,passwordHash)
VALUES ("jon@admin.uni-pr.edu","Jon","Kuçi","OCjHhzKXTym9xYJtqCQZWr/3rS2RCs5Hs22qp8UrXtk=","4f436a48687a4b5854796d3978594a747143515a57722f337253325243733548733232717038557258746b3d42120edb685b822fc9dc878664c8d2b8b0d005ad2ffe94926efbb7b5bf55adf9");

--Passi i admin Jonit eshte Isaku1234

-- 16/05/2024 - Dreni

ALTER TABLE tblkonkurimet
DROP COLUMN provimiId;


-- 16/05/2024 - Joni
-- Veq qe me i ba unique qe mos mu kan dy me email t njejt, s lejon as me editu per me ba qat email
ALTER TABLE tblMbikqyresi
MODIFY column email VARCHAR(50) UNIQUE;

ALTER TABLE tblAdmin
MODIFY column email VARCHAR(50) UNIQUE;



--18/05/2024 - Edoni
--Ekzekutoni qita constrains
ALTER TABLE tblSemsStaf
    ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE tblSemsStaf MODIFY COLUMN passwordHash VARCHAR(300);
INSERT INTO tblSemsStaf (email, emri, mbiemri, salt, passwordHash, facultyId) VALUES
                                                                                  ('blerim.rexha@uni-pr.edu', 'Blerim', 'Rexha', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220939),
                                                                                  ('valon.raça@uni-pr.edu', 'Valon', 'Raça', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220109),
                                                                                  ('qamil.kabashi@uni-pr.edu', 'Qamil', 'Kabashi', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220589),
                                                                                  ('valon.veliu@uni-pr.edu', 'Valon', 'Veliu', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 210548),
                                                                                  ('milaim.zabeli@uni-pr.edu', 'Milaim', 'Zabeli', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220325),
                                                                                  ('isak.shabani@uni-pr.edu', 'Isak', 'Shabani', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220547),
                                                                                  ('kadri.sylejmani@uni-pr.edu', 'Kadri', 'Sylejmani', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220841),
                                                                                  ('vjosa.shatri@uni-pr.edu', 'Vjosa', 'Shatri', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220874),
                                                                                  ('valdete.rexhbeqajhamiti@uni-pr.edu', 'Valdete', 'Rexhbeqaj-Hamiti', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220125),
                                                                                  ('blend.arifaj@uni-pr.edu', 'Blend', 'Arifaj', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220458),
                                                                                  ('labeat.arbneshi@uni-pr.edu', 'Labeat', 'Arbneshi', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220458),
                                                                                  ('synim.selimi@uni-pr.edu', 'Synim', 'Selimi', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220879),
                                                                                  ('artan.mazrekaj@uni-pr.edu', 'Artan', 'Mazrekaj', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220412),
                                                                                  ('dalina.vranoci@uni-pr.edu', 'Dalina', 'Vranoci', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220852),
                                                                                  ('arbnor.halili@uni-pr.edu', 'Arbnor', 'Halili', 'j3h2Cx8TuSOpYHGky0b0MO2M8LgUssjV0HrIh1swJ+U=',
                                                                                   '6a3368324378385475534f705948476b793062304d4f324d384c675573736a5630487249683173774a2b553d394652fc5ab4fa9557a3fae5306ac417a3d356f883c2c85a7f329b79c29cba71', 220659);

-- 18/05/2024 - Joni
    CREATE VIEW vwKonkurrimetData AS
SELECT
    tblUserStudent.userId,
    tblUserStudent.emri,
    tblUserStudent.mbiemri,
    tblShkollaMesme.suksesiKl10,
    tblShkollaMesme.suksesiKl11,
    tblShkollaMesme.suksesiKl12,
    tblShkollaMesme.piketMat,
    tblShkollaMesme.piketGjSh,
    tblShkollaMesme.piketAng,
    tblShkollaMesme.piketZgjedhore,
    tblKonkurimet.piketPranues,
    d1.emri as Prioriteti1,
    d2.emri as Prioriteti2,
    d3.emri as Prioriteti3,
    d4.emri as Prioriteti4,
    tblAfati.afatId,
    tblKonkurimet.mbikqyresiId,
    tblAfati.niveli,
    CASE WHEN tblUserStudent.nacionaliteti = 'Kosovar' THEN FALSE ELSE TRUE END AS minoritet
FROM
    tblKonkurimet
JOIN
    tblAplikimi ON tblKonkurimet.aplikimiId = tblAplikimi.aplikimiId
JOIN
    tblShkollaMesme ON tblAplikimi.shkollaId = tblShkollaMesme.shkollaId
JOIN
    tblUserStudent ON tblShkollaMesme.userId = tblUserStudent.userId
JOIN
    tblAfati ON tblAplikimi.afatId = tblAfati.afatId
LEFT JOIN
    tblDepartamenti d1 ON tblAplikimi.deptIdPrioritet1 = d1.deptId
LEFT JOIN
    tblDepartamenti d2 ON tblAplikimi.deptIdPrioritet2 = d2.deptId
LEFT JOIN
    tblDepartamenti d3 ON tblAplikimi.deptIdPrioritet3 = d3.deptId
LEFT JOIN
    tblDepartamenti d4 ON tblAplikimi.deptIdPrioritet4 = d4.deptId
WHERE
    tblShkollaMesme.approved = TRUE;




INSERT INTO tblDepartamenti (emri, niveli, nrStudentaveAfat1, nrStudentaveAfat2, nrStudentaveMinoritetAfat1, nrStudentaveMinoritetAfat2)
VALUES
    ('IKS', 'BSC', 125, 25, 20, 5),
    ('EAR', 'BSC', 50, 20, 5, 5),
    ('EE', 'BSC', 40, 15, 5, 5),
    ('TIK', 'BSC', 40, 15, 5, 5),
    ('IKS', 'MSC', 50, 10, 0, 0),
    ('EAR', 'MSC', 20, 10, 0, 0),
    ('EE', 'MSC',  20, 10, 0, 0),
    ('TIK', 'MSC', 20, 10, 0, 0),
    ('IKS', 'PHD', 20, 10, 0, 0),
    ('EAR', 'PHD', 5, 5, 0, 0),
    ('EE', 'PHD', 5, 5, 0, 0),
    ('TIK', 'PHD', 5, 5, 0, 0);


ALTER TABLE tblKonkurimet
DROP column provimiId;

--24-05-2024 Edoni
CREATE TABLE tblArkiva (
                           documentId INT AUTO_INCREMENT PRIMARY KEY,
                           nrSerik VARCHAR(50) NOT NULL,
                           idStudentit VARCHAR(50) NOT NULL,
                           data DATE NOT NULL
);



--25-05-2024 Erza
  CREATE TABLE tblApplicationStatus (
    UserID INT PRIMARY KEY,
    SubmissionStatus VARCHAR(255),
    EditTime DATETIME,
    ApplicationName VARCHAR(255),
    FOREIGN KEY (UserID) REFERENCES tblUser(UserID)
);

