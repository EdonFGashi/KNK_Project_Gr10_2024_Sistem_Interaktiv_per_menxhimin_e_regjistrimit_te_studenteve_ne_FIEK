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
    s.email,
    s.emri,
    s.mbiemri,
    s.nacionaliteti,
    s.qyteti,
    s.shteti,
    s.gjinia,
    s.dataLindjes,
    s.salt,
    s.passwordHash,
    r.generatedEmail,
    r.generatedId,
    r.emriDepartamentit,
    r.niveli
FROM tblUserStudent s
JOIN tblRegisteredStudents r ON s.userId = r.userId;





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



