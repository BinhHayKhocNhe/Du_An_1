create database Du_An_1;
use Du_An_1;

CREATE TABLE Administrators(
ID_Administrator NVARCHAR(50) NOT NULL,
Password_Administrator NVARCHAR(50) NOT NULL,
First_Name NVARCHAR(20) NOT NULL,
Middle_Name NVARCHAR(20),
Last_Name NVARCHAR(20) NOT NULL,
Email NVARCHAR(50) NOT NULL,
Phone_Number NVARCHAR(20) NOT NULL,
Gender BIT NOT NULL,
Address NVARCHAR(255) NOT NULL,
Note NVARCHAR(255),
CONSTRAINT PK_ID_Admin PRIMARY KEY(ID_Administrator)
);

INSERT INTO Administrators (ID_Administrator, Password_Administrator, First_Name, Middle_Name, Last_Name, Email, Phone_Number, Gender, Address, Note)
VALUES 
('admin1', 'securepass1', 'John', '', 'Doe', 'john.d@example.com', '123456789', 1, '123 Main Street, Cityville', 'Administrator 1'),
('admin2', 'pass1234', 'Jane', '', 'Smith', 'jane.s@example.com', '987654321', 0, '456 Oak Avenue, Townsville', 'Administrator 2'),
('admin3', 'adminadmin', 'Michael', '', 'Johnson', 'michael.j@example.com', '111223344', 1, '789 Pine Road, Villageton', 'Administrator 3'),
('admin4', 'admin123', 'Emily', '', 'Brown', 'emily.b@example.com', '9988776655', 0, '101 Cedar Lane, Hamletville', 'Administrator 4'),
('admin5', 'adminpass123', 'Daniel', '', 'Lee', 'daniel.lee@example.com', '1122334455', 1, '202 Maple Drive, Township', 'Administrator 5');


CREATE TABLE Staff (
ID_Staff NVARCHAR(50) NOT NULL,
Password_Staff NVARCHAR(50) NOT NULL,
First_Name NVARCHAR(20) NOT NULL,
Middle_Name NVARCHAR(20),
Last_Name NVARCHAR(20) NOT NULL,
Email NVARCHAR(50) NOT NULL,
Phone_Number NVARCHAR(20) NOT NULL,
Gender BIT NOT NULL,
Status_Staff BIT NOT NULL,
Position NVARCHAR(50) NOT NULL,
Address_Staff  NVARCHAR(255) NOT NULL,
Avatar NVARCHAR(50) NOT NULL,
Date_Of_Birth INT NOT NULL,
Month_Of_Birth INT NOT NULL,
Year_Of_Birth INT NOT NULL,
Start_Date DATETIME DEFAULT GETDATE(),
Note NVARCHAR(255),
CONSTRAINT PK_ID_Staff PRIMARY KEY(ID_Staff)
);
INSERT INTO Staff (ID_Staff, Password_Staff, First_Name, Middle_Name, Last_Name, Email, Phone_Number, Gender, Status_Staff, Position, Address_Staff, Avatar, Date_Of_Birth, Month_Of_Birth, Year_Of_Birth, Note)
VALUES 
('EMP001', 'password123', 'John', 'A.', 'Doe', 'john.doe@email.com', '123456789', 1, 1, 'Doctor', '123 Main Street, City', 'avatar1.jpg', 1, 1, 1980, 'Excellent doctor with years of experience in healthcare.'),
('EMP002', 'pass456', 'Jane', '', 'Smith', 'jane.smith@email.com', '987654321', 0, 1, 'Office', '456 Oak Avenue, Town', 'avatar2.jpg', 5, 12, 1995, 'Detail-oriented and efficient in office tasks.'),
('EMP003', 'secure789', 'Michael', 'J.', 'Williams', 'michael.w@email.com', '111222333', 1, 1, 'Chef', '789 Pine Street, Village', 'avatar3.jpg', 8, 7, 1988, 'Experienced chef specializing in various cuisines.'),
('EMP004', 'p@ssw0rd', 'Sarah', 'L.', 'Johnson', 'sarah.j@email.com', '555666777', 0, 1, 'Office', '101 Security Lane, City', 'avatar4.jpg', 3, 4, 1990, 'Dedicated to maintaining a safe and secure environment.'),
('EMP005', 'pwd123', 'David', '', 'Miller', 'david.m@email.com', '999888777', 1, 1, 'Chef', '200 Cleaning Street, Town', 'avatar5.jpg', 10, 9, 1985, 'Hardworking and committed to cleanliness.'),
('EMP006', 'ituser1', 'Emily', '', 'Jones', 'emily.j@email.com', '777888999', 0, 1, 'IT', '456 Tech Avenue, City', 'avatar6.jpg', 12, 6, 1982, 'Skilled in troubleshooting and IT support.'),
('EMP007', 'chef123', 'Daniel', 'R.', 'White', 'daniel.w@email.com', '333444555', 1, 1, 'Chef', '789 Kitchen Lane, Town', 'avatar7.jpg', 7, 3, 1993, 'Assisting in food preparation and kitchen tasks.'),
('EMP008', 'guard456', 'Megan', '', 'Taylor', 'megan.t@email.com', '222333444', 0, 1, 'Officer', '101 Secure Lane, Village', 'avatar8.jpg', 4, 11, 1987, 'Ensuring safety and security in the workplace.'),
('EMP009', 'cleaner1', 'Christopher', 'M.', 'Brown', 'chris.b@email.com', '888777666', 1, 1, 'Doctor', '222 Maintenance Street, City', 'avatar9.jpg', 9, 2, 1991, 'Diligent in maintenance tasks and hardworking.'),
('EMP010', 'itadmin', 'Sophia', '', 'Garcia', 'sophia.g@email.com', '111444777', 0, 1, 'IT', '333 Admin Lane, Town', 'avatar10.jpg', 6, 8, 1984, 'Managing IT systems and providing technical support.'),
('EMP011', 'security1', 'Kevin', 'J.', 'Martinez', 'kevin.m@email.com', '555444333', 1, 1, 'Security Guard', '444 Security Street, Village', 'avatar11.jpg', 2, 5, 1998, 'Dedicated to maintaining a safe environment.'),
('EMP012', 'cleaner1', 'Olivia', '', 'Hernandez', 'olivia.h@email.com', '999111444', 0, 1, 'Laborer', '555 Clean Street, City', 'avatar12.jpg', 11, 10, 1989, 'Ensuring cleanliness and hygiene in the workplace.'),
('EMP013', 'ittech', 'William', 'S.', 'Davis', 'william.d@email.com', '666555444', 1, 1, 'Laborer', '666 Tech Lane, Town', 'avatar13.jpg', 8, 1, 1983, 'Providing technical support and troubleshooting IT issues.'),
('EMP014', 'office1', 'Ella', '', 'Lopez', 'ella.l@email.com', '333222111', 0, 1, 'Laborer', '777 Office Lane, Village', 'avatar14.jpg', 5, 7, 1990, 'Assisting in various office tasks and administrative support.'),
('EMP015', 'waiter1', 'Alexander', 'R.', 'Perez', 'alex.p@email.com', '888999000', 1, 1, 'Chef', '888 Restaurant Street, City', 'avatar15.jpg', 12, 4, 1986, 'Providing excellent service in a restaurant setting.'),
('EMP016', 'csr1', 'Grace', '', 'Scott', 'grace.s@email.com', '111999888', 0, 1, 'Doctor', '999 Service Lane, Town', 'avatar16.jpg', 3, 9, 1992, 'Assisting customers with inquiries and providing support.'),
('EMP017', 'maintenance1', 'Nathan', 'C.', 'Ward', 'nathan.w@email.com', '777000111', 1, 1, 'Doctor', '000 Maintenance Street, Village', 'avatar17.jpg', 6, 12, 1981, 'Handling maintenance tasks with precision and care.'),
('EMP018', 'execassist', 'Ava', '', 'Baker', 'ava.b@email.com', '444555666', 0, 1, 'Office', '111 Executive Lane, City', 'avatar18.jpg', 9, 5, 1988, 'Providing administrative support to executives.'),
('EMP019', 'waitstaff', 'Ethan', 'D.', 'Gomez', 'ethan.g@email.com', '222111000', 1, 1, 'Office', '222 Restaurant Street, Town', 'avatar19.jpg', 2, 8, 1997, 'Ensuring a pleasant dining experience for customers.'),
('EMP020', 'itsupport1', 'Chloe', '', 'Russell', 'chloe.r@email.com', '666000999', 0, 1, 'IT', '333 Tech Lane, Village', 'avatar20.jpg', 7, 10, 1985, 'Assisting with IT issues and technical support.');

CREATE TABLE Teacher (
ID_Teacher NVARCHAR(50) NOT NULL,
Password_Teacher NVARCHAR(50) NOT NULL,
First_Name NVARCHAR(20) NOT NULL,
Middle_Name NVARCHAR(20),
Last_Name NVARCHAR(20) NOT NULL,
Email NVARCHAR(50) NOT NULL,
Phone_Number NVARCHAR(20) NOT NULL,
Gender BIT NOT NULL,
Status_Teacher BIT NOT NULL,
Level_Teacher NVARCHAR(50) NOT NULL,
Address_Teacher  NVARCHAR(255) NOT NULL,
Avatar NVARCHAR(50) NOT NULL,
Date_Of_Birth INT NOT NULL,
Month_Of_Birth INT NOT NULL,
Year_Of_Birth INT NOT NULL,
Start_Date DATETIME DEFAULT GETDATE(),
Note NVARCHAR(255),
CONSTRAINT PK_ID_Teacher PRIMARY KEY(ID_Teacher)
);
INSERT INTO Teacher (ID_Teacher, Password_Teacher, First_Name, Middle_Name, Last_Name, Email, Phone_Number, Gender, Status_Teacher, Level_Teacher, Address_Teacher, Avatar, Date_Of_Birth, Month_Of_Birth, Year_Of_Birth, Note)
VALUES 
('TCH001', 'teacher123', 'John', 'A.', 'Doe', 'john.doe@email.com', '123456789', 1, 1, 'High School', '123 Main Street, City', 'avatar1.jpg', 1, 1, 1980, 'Experienced teacher with a focus on high school education.'),
('TCH002', 'pass456', 'Jane', '', 'Smith', 'jane.smith@email.com', '987654321', 0, 1, 'Primary School', '456 Oak Avenue, Town', 'avatar2.jpg', 5, 12, 1995, 'Passionate about teaching young students in primary school.'),
('TCH003', 'secure789', 'Michael', 'J.', 'Williams', 'michael.w@email.com', '111222333', 1, 1, 'College', '789 Pine Street, Village', 'avatar3.jpg', 8, 7, 1988, 'College-level educator specializing in a specific subject.'),
('TCH004', 'p@ssw0rd', 'Sarah', 'L.', 'Johnson', 'sarah.j@email.com', '555666777', 0, 1, 'High School', '101 Education Lane, City', 'avatar4.jpg', 3, 4, 1990, 'Dedicated to inspiring high school students through education.'),
('TCH005', 'pwd123', 'David', '', 'Miller', 'david.m@email.com', '999888777', 1, 1, 'Middle School', '200 Teaching Street, Town', 'avatar5.jpg', 10, 9, 1985, 'Passionate about educating students in middle school.'),
('TCH006', 'ituser1', 'Emily', '', 'Jones', 'emily.j@email.com', '777888999', 0, 1, 'High School', '456 Educators Avenue, City', 'avatar6.jpg', 12, 6, 1982, 'Experienced high school teacher with a focus on student success.'),
('TCH007', 'chef123', 'Daniel', 'R.', 'White', 'daniel.w@email.com', '333444555', 1, 1, 'Primary School', '789 Learning Lane, Town', 'avatar7.jpg', 7, 3, 1993, 'Passionate about shaping the education of young learners in primary school.'),
('TCH008', 'guard456', 'Megan', '', 'Taylor', 'megan.t@email.com', '222333444', 0, 1, 'College', '101 University Street, Village', 'avatar8.jpg', 4, 11, 1987, 'College-level instructor with a commitment to academic excellence.'),
('TCH009', 'laocong1', 'Christopher', 'M.', 'Brown', 'chris.b@email.com', '888777666', 1, 1, 'Intermediate', '222 Teach Lane, City', 'avatar9.jpg', 9, 2, 1991, 'Experienced high school educator with a focus on student development.'),
('TCH010', 'itadmin', 'Sophia', '', 'Garcia', 'sophia.g@email.com', '111444777', 0, 1, 'Master', '333 School Lane, Town', 'avatar10.jpg', 6, 8, 1984, 'Dedicated to fostering a positive learning environment for middle school students.'),
('TCH011', 'officer1', 'Kevin', 'J.', 'Martinez', 'kevin.m@email.com', '555444333', 1, 1, 'College', '444 College Street, Village', 'avatar11.jpg', 2, 5, 1998, 'College-level instructor with a passion for knowledge transfer.'),
('TCH012', 'cleaner1', 'Olivia', '', 'Hernandez', 'olivia.h@email.com', '999111444', 0, 1, 'Intermediate', '555 Teaching Street, City', 'avatar12.jpg', 11, 10, 1989, 'Dedicated high school teacher focused on student engagement.'),
('TCH013', 'ittech', 'William', 'S.', 'Davis', 'william.d@email.com', '666555444', 1, 1, 'University', '666 Educate Lane, Town', 'avatar13.jpg', 8, 1, 1983, 'Middle school educator providing technical education.'),
('TCH014', 'office1', 'Ella', '', 'Lopez', 'ella.l@email.com', '333222111', 0, 1, 'Master', '777 Office Lane, Village', 'avatar14.jpg', 5, 7, 1990, 'Master in a specialized field, committed to advanced education.'),
('TCH015', 'waiter1', 'Alexander', 'R.', 'Perez', 'alex.p@email.com', '888999000', 1, 1, 'University', '888 Teaching Street, City', 'avatar15.jpg', 12, 4, 1986, 'University-level educator with a focus on academic excellence.'),
('TCH016', 'csr1', 'Grace', '', 'Scott', 'grace.s@email.com', '111999888', 0, 1, 'Intermediate', '999 Service Lane, Town', 'avatar16.jpg', 3, 9, 1992, 'College-level instructor providing support and guidance.'),
('TCH017', 'maintenance1', 'Nathan', 'C.', 'Ward', 'nathan.w@email.com', '777000111', 1, 1, 'Intermediate', '000 Maintenance Street, Village', 'avatar17.jpg', 6, 12, 1981, 'Intermediate educator specializing in practical skills and knowledge.'),
('TCH018', 'execassist', 'Ava', '', 'Baker', 'ava.b@email.com', '444555666', 0, 1, 'University', '111 Executive Lane, City', 'avatar18.jpg', 9, 5, 1988, 'University-level instructor providing administrative support.'),
('TCH019', 'waitstaff', 'Ethan', 'D.', 'Gomez', 'ethan.g@email.com', '222111000', 1, 1, 'Master', '222 Restaurant Street, Town', 'avatar19.jpg', 2, 8, 1997, 'Master in a specialized field, dedicated to advanced education.'),
('TCH020', 'itsupport1', 'Chloe', '', 'Russell', 'chloe.r@email.com', '666000999', 0, 1, 'Intermediate', '333 Tech Lane, Village', 'avatar20.jpg', 7, 10, 1985, 'College-level instructor providing technical support and guidance.');


CREATE TABLE Guardians(
ID_Guardians NVARCHAR(50) NOT NULL,
Password_Guardians NVARCHAR(50) NOT NULL,
First_Name NVARCHAR(20) NOT NULL,
Middle_Name NVARCHAR(20),
Last_Name NVARCHAR(20) NOT NULL,
Email NVARCHAR(50) NOT NULL,
Phone_Number NVARCHAR(20) NOT NULL,
Gender BIT NOT NULL,
Address_Guardians NVARCHAR(255) NOT NULL,
Job NVARCHAR(50) NOT NULL,
Note NVARCHAR(255),
CONSTRAINT PK_ID_Guardians PRIMARY KEY(ID_Guardians)
);
INSERT INTO Guardians (ID_Guardians, Password_Guardians, First_Name, Middle_Name, Last_Name, Email, Phone_Number, Gender, Address_Guardians, Job, Note)
VALUES 
('GRD001', 'guardian123', 'Michael', 'A.', 'Johnson', 'michael.j@email.com', '123456789', 1, '456 Guardian Street, City', 'Engineer', 'Active supporter of the child''s education.'),
('GRD002', 'pass456', 'Emma', '', 'Smith', 'emma.s@email.com', '987654321', 0, '789 Care Avenue, Town', 'Doctor', 'Dedicated to ensuring the well-being and success of the child.'),
('GRD003', 'secure789', 'Christopher', 'J.', 'Williams', 'chris.w@email.com', '111222333', 1, '101 Safety Lane, Village', 'Police Officer', 'Committed to providing a safe environment for the child.'),
('GRD004', 'p@ssw0rd', 'Olivia', 'L.', 'Davis', 'olivia.d@email.com', '555666777', 0, '200 Support Street, City', 'Nurse', 'Ensuring the child''s health and well-being is a top priority.'),
('GRD005', 'pwd123', 'Daniel', '', 'Brown', 'daniel.b@email.com', '999888777', 1, '789 Guardian Lane, Town', 'Architect', 'Supportive of the child''s academic and extracurricular activities.'),
('GRD006', 'ituser1', 'Sophia', '', 'Jones', 'sophia.j@email.com', '777888999', 0, '456 Caregiver Street, City', 'Psychologist', 'Understanding and supporting the child''s emotional well-being.'),
('GRD007', 'chef123', 'William', 'R.', 'White', 'william.w@email.com', '333444555', 1, '789 Guidance Lane, Town', 'Counselor', 'Providing guidance and support for the child''s personal development.'),
('GRD008', 'guard456', 'Ava', '', 'Taylor', 'ava.t@email.com', '222333444', 0, '101 Guardian Street, Village', 'Social Worker', 'Dedicated to the child''s overall welfare and happiness.'),
('GRD009', 'guardian1', 'Ethan', 'M.', 'Brown', 'ethan.b@email.com', '888777666', 1, '222 Support Avenue, City', 'Teacher', 'Supportive of the child''s educational journey and growth.'),
('GRD010', 'itadmin', 'Isabella', '', 'Garcia', 'isabella.g@email.com', '111444777', 0, '333 Care Lane, Town', 'Artist', 'Encouraging the child''s creativity and artistic expression.'),
('GRD011', 'security1', 'Mia', 'J.', 'Martinez', 'mia.m@email.com', '555444333', 1, '444 Safe Lane, Village', 'Lawyer', 'Advocating for the child''s rights and legal matters.'),
('GRD012', 'cleaner1', 'Noah', '', 'Hernandez', 'noah.h@email.com', '999111444', 0, '555 Support Street, City', 'Entrepreneur', 'Promoting an entrepreneurial mindset and business understanding.'),
('GRD013', 'ittech', 'James', 'S.', 'Taylor', 'james.t@email.com', '666555444', 1, '666 Guardian Lane, Town', 'Scientist', 'Fostering an interest in science and exploration for the child.'),
('GRD014', 'office1', 'Emily', '', 'Lopez', 'emily.l@email.com', '333222111', 0, '777 Office Street, Village', 'Writer', 'Encouraging a love for literature and writing in the child.'),
('GRD015', 'waiter1', 'Aiden', 'R.', 'Perez', 'aiden.p@email.com', '888999000', 1, '888 Care Lane, City', 'Chef', 'Inspiring a passion for culinary arts and cooking.'),
('GRD016', 'csr1', 'Scarlett', '', 'Scott', 'scarlett.s@email.com', '111999888', 0, '999 Support Avenue, Town', 'Athlete', 'Encouraging physical fitness and a love for sports.'),
('GRD017', 'maintenance1', 'Lucas', 'C.', 'Ward', 'lucas.w@email.com', '777000111', 1, '000 Safety Lane, Village', 'Engineer', 'Supporting the child''s interest in engineering and innovation.'),
('GRD018', 'execassist', 'Liam', '', 'Baker', 'liam.b@email.com', '444555666', 0, '111 Caregiver Street, City', 'Executive', 'Providing a supportive and goal-oriented environment for the child.'),
('GRD019', 'waitstaff', 'Madison', 'D.', 'Gomez', 'madison.g@email.com', '222111000', 1, '222 Support Lane, Town', 'Fashion Designer', 'Nurturing the child''s creativity in the field of fashion.'),
('GRD020', 'itsupport1', 'Evelyn', '', 'Russell', 'evelyn.r@email.com', '666000999', 0, '333 Guardian Avenue, Village', 'Scientist', 'Encouraging scientific curiosity and experimentation in the child.');

CREATE TABLE Student(
ID_Student NVARCHAR(50) NOT NULL,
First_Name NVARCHAR(20) NOT NULL,
Middle_Name NVARCHAR(20),
Last_Name NVARCHAR(20) NOT NULL,
Gender BIT NOT NULL,
Address_Student NVARCHAR(255) NOT NULL,
Status_Student BIT NOT NULL,
Avatar NVARCHAR(50) NOT NULL,
Date_Of_Birth INT NOT NULL,
Month_Of_Birth INT NOT NULL,
Year_Of_Birth INT NOT NULL,
Note NVARCHAR(255),
CONSTRAINT PK_ID_Student PRIMARY KEY(ID_Student)
);

INSERT INTO Student (ID_Student, First_Name, Middle_Name, Last_Name, Gender, Address_Student, Status_Student, Avatar, Date_Of_Birth, Month_Of_Birth, Year_Of_Birth, Note)
VALUES
('STU001', 'Emma', '', 'Johnson', 0, '123 Main Street, City', 1, 'avatar1.jpg', 5, 3, 2019, 'Active and engaged learner.'),
('STU002', 'Liam', 'A.', 'Smith', 1, '456 Oak Avenue, Town', 1, 'avatar2.jpg', 8, 6, 2020, 'Enthusiastic about mathematics.'),
('STU003', 'Olivia', '', 'Williams', 0, '789 Pine Street, Village', 1, 'avatar3.jpg', 2, 9, 2018, 'Loves reading and storytelling.'),
('STU004', 'Noah', 'R.', 'Davis', 1, '101 Education Lane, City', 1, 'avatar4.jpg', 10, 12, 2019, 'Active in sports and physical activities.'),
('STU005', 'Sophia', '', 'Brown', 0, '200 Teaching Street, Town', 1, 'avatar5.jpg', 7, 5, 2021, 'Passionate about arts and crafts.'),
('STU006', 'Jackson', '', 'Jones', 1, '456 Educators Avenue, City', 1, 'avatar6.jpg', 3, 8, 2022, 'Interested in science experiments.'),
('STU007', 'Ava', 'R.', 'White', 0, '789 Learning Lane, Town', 1, 'avatar7.jpg', 12, 11, 2020, 'Active participant in school events.'),
('STU008', 'Liam', 'J.', 'Taylor', 1, '101 University Street, Village', 1, 'avatar8.jpg', 6, 2, 2018, 'Enjoys collaborative learning.'),
('STU009', 'Emma', '', 'Miller', 0, '222 Teach Lane, City', 1, 'avatar9.jpg', 9, 4, 2021, 'Keen interest in history and social studies.'),
('STU010', 'Mia', 'R.', 'Garcia', 0, '333 School Lane, Town', 1, 'avatar10.jpg', 11, 7, 2019, 'Enthusiastic about environmental issues.'),
('STU011', 'Ethan', 'S.', 'Martinez', 1, '444 College Street, Village', 1, 'avatar11.jpg', 4, 10, 2022, 'Actively participates in school clubs.'),
('STU012', 'Isabella', '', 'Hernandez', 0, '555 Teaching Street, City', 1, 'avatar12.jpg', 1, 1, 2018, 'Creative writing enthusiast.'),
('STU013', 'Lucas', 'M.', 'Davis', 1, '666 Educate Lane, Town', 1, 'avatar13.jpg', 7, 3, 2020, 'Passionate about music and instruments.'),
('STU014', 'Aiden', 'J.', 'Lopez', 1, '777 Office Lane, Village', 1, 'avatar14.jpg', 5, 6, 2021, 'Active in school theater productions.'),
('STU015', 'Scarlett', '', 'Perez', 0, '888 Teaching Street, City', 1, 'avatar15.jpg', 10, 9, 2019, 'Interested in mathematics competitions.'),
('STU016', 'Noah', 'D.', 'Scott', 1, '999 Service Lane, Town', 1, 'avatar16.jpg', 2, 12, 2020, 'Enjoys coding and computer science.'),
('STU017', 'Ava', 'C.', 'Ward', 0, '000 Maintenance Street, Village', 1, 'avatar17.jpg', 8, 1, 2022, 'Active in community service projects.'),
('STU018', 'Ethan', 'J.', 'Baker', 1, '111 Executive Lane, City', 1, 'avatar18.jpg', 3, 4, 2021, 'Keen interest in astronomy.'),
('STU019', 'Madison', 'D.', 'Gomez', 0, '222 Restaurant Street, Town', 1, 'avatar19.jpg', 6, 7, 2018, 'Participates in school sports teams.'),
('STU020', 'Chloe', '', 'Russell', 0, '333 Tech Lane, Village', 1, 'avatar20.jpg', 9, 10, 2023, 'Actively involved in student government.'),
-- Tiếp tục thêm dữ liệu cho 10 học sinh khác tương tự...
('STU021', 'Evelyn', '', 'Cooper', 0, '444 Science Avenue, City', 1, 'avatar21.jpg', 5, 2, 2018, 'Enjoys science experiments and discoveries.'),
('STU022', 'Owen', 'M.', 'Hill', 1, '555 Math Lane, Town', 1, 'avatar22.jpg', 8, 5, 2020, 'Actively participates in math competitions.'),
('STU023', 'Hazel', '', 'Fisher', 0, '666 Arts Street, Village', 1, 'avatar23.jpg', 2, 8, 2019, 'Passionate about visual arts and painting.'),
('STU024', 'Henry', 'J.', 'Morgan', 1, '777 Music Lane, City', 1, 'avatar24.jpg', 11, 11, 2022, 'Talented musician and member of the school band.'),
('STU025', 'Mila', 'A.', 'Wells', 0, '888 Literature Avenue, Town', 1, 'avatar25.jpg', 7, 3, 2021, 'Frequent participant in literature and book clubs.'),
('STU026', 'Mason', 'S.', 'Cook', 1, '999 Drama Lane, Village', 1, 'avatar26.jpg', 12, 6, 2018, 'Actively involved in drama and theatrical performances.'),
('STU027', 'Ella', '', 'Chapman', 0, '000 History Street, City', 1, 'avatar27.jpg', 1, 9, 2019, 'Fascinated by historical events and research.'),
('STU028', 'Carter', 'D.', 'Snyder', 1, '111 Technology Lane, Town', 1, 'avatar28.jpg', 4, 12, 2020, 'Passionate about technology and innovation.'),
('STU029', 'Aria', 'L.', 'Elliott', 0, '222 Innovation Avenue, Village', 1, 'avatar29.jpg', 9, 1, 2022, 'Enthusiastic about STEM projects and experiments.'),
('STU030', 'Leo', 'B.', 'Fleming', 1, '333 Engineering Lane, City', 1, 'avatar30.jpg', 6, 4, 2023, 'Aspiring engineer with a love for building and design.');

CREATE TABLE Subject(
ID_Subject NVARCHAR(50) NOT NULL,
Subject_Name NVARCHAR(50) NOT NULL,
Note NVARCHAR(255),
CONSTRAINT PK_ID_Subject PRIMARY KEY(ID_Subject)
);
INSERT INTO Subject (ID_Subject, Subject_Name, Note) VALUES 
('MAT001', 'Mathematics', 'Subject covering arithmetic and theory.'),
('WR001', 'Writing', 'Subject focusing on writing and expression.'),
('DR001', 'Drawing', 'Subject covering the art of drawing and image creation.'),
('PE001', 'Physical Education', 'Subject focusing on physical fitness and health.');

CREATE TABLE Course(
ID_Course NVARCHAR(50) NOT NULL,
Course_Name NVARCHAR(50) NOT NULL,
ID_Student NVARCHAR(50) NOT NULL,
ID_Teacher NVARCHAR(50) NOT NULL,
ID_Class NVARCHAR(50) NOT NULL,
Year int NOT NULL,
Note NVARCHAR(255),
CONSTRAINT PK_ID_Course PRIMARY KEY(ID_Course)
);
INSERT INTO Course (ID_Course, Course_Name, ID_Student, ID_Teacher, ID_Class, Year, Note)
VALUES 
    ('C001', 'Spring', 'STU001', 'TCH001', 'C001', 2023, 'Note for Spring'),
    ('C002', 'Summer', 'STU002', 'TCH002', 'C002', 2023, 'Note for Summer'),
    ('C003', 'Fall', 'STU003', 'TCH003', 'C003', 2023, 'Note for Fall'),
    ('C004', 'Spring', 'STU004', 'TCH004', 'C004', 2022, 'Note for Spring'),
    ('C005', 'Summer', 'STU005', 'TCH005', 'C005', 2022, 'Note for Summer'),
    ('C006', 'Fall', 'STU006', 'TCH006', 'C006', 2022, 'Note for Fall'),
    ('C007', 'Spring', 'STU007', 'TCH007', 'C007', 2021, 'Note for Spring'),
    ('C008', 'Summer', 'STU008', 'TCH008', 'C008', 2021, 'Note for Summer'),
    ('C009', 'Fall', 'STU009', 'TCH009', 'C009', 2021, 'Note for Fall'),
    ('C010', 'Spring', 'STU010', 'TCH010', 'C010', 2024, 'Note for Spring');

CREATE TABLE Class(
ID_Class NVARCHAR(50) NOT NULL,
Class_Name NVARCHAR(50) NOT NULL,
ID_Teacher NVARCHAR(50) NOT NULL,
ID_Student NVARCHAR(50) NOT NULL,
Quantity int NOT NULL,
Note NVARCHAR(255),
CONSTRAINT PK_ID_Class PRIMARY KEY(ID_Class),
CONSTRAINT FK_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Teacher FOREIGN KEY (ID_Teacher) REFERENCES Teacher(ID_Teacher)
);
-- Thêm dữ liệu vào bảng Class
INSERT INTO Class (ID_Class, Class_Name, ID_Teacher, ID_Student, Quantity, Note) VALUES
    ('C001', 'Class 1', 'TCH001', 'STU001', 25, 'Note for Class 1'),
    ('C002', 'Class 2', 'TCH002', 'STU002', 30, 'Note for Class 2'),
    ('C003', 'Class 3', 'TCH003', 'STU003', 20, 'Note for Class 3'),
    ('C004', 'Class 4', 'TCH004', 'STU004', 25, 'Note for Class 4'),
    ('C005', 'Class 5', 'TCH005', 'STU005', 30, 'Note for Class 5'),
    ('C006', 'Class 6', 'TCH006', 'STU006', 20, 'Note for Class 6'),
    ('C007', 'Class 7', 'TCH007', 'STU007', 25, 'Note for Class 7'),
    ('C008', 'Class 8', 'TCH008', 'STU008', 30, 'Note for Class 8'),
    ('C009', 'Class 9', 'TCH009', 'STU009', 20, 'Note for Class 9'),
    ('C010', 'Class 10', 'TCH010', 'STU010', 25, 'Note for Class 10');

CREATE TABLE Schedule(
ID_Course NVARCHAR(50) NOT NULL,
ID_Teacher NVARCHAR(50) NOT NULL,
ID_Student NVARCHAR(50) NOT NULL,
ID_Class NVARCHAR(50) NOT NULL,
ID_Subject NVARCHAR(50) NOT NULL,
School_Day DATE NOT NULL,
Schedule_Date DATETIME DEFAULT GETDATE(),
Course_Name NVARCHAR(50) NOT NULL,
Note NVARCHAR(255),
CONSTRAINT FK_Schedule_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Schedule_Teacher FOREIGN KEY (ID_Teacher) REFERENCES Teacher(ID_Teacher),
CONSTRAINT FK_Schedule_Class FOREIGN KEY (ID_Class) REFERENCES Class(ID_Class),
CONSTRAINT FK_Schedule_Course FOREIGN KEY (ID_Course) REFERENCES Course(ID_Course),
CONSTRAINT FK_Schedule_Subject FOREIGN KEY (ID_Subject) REFERENCES Subject(ID_Subject)
);
-- Thêm dữ liệu vào bảng Schedule
INSERT INTO Schedule (ID_Course, ID_Teacher, ID_Student, ID_Class, ID_Subject, School_Day, Course_Name, Note)
VALUES
    ('C001', 'TCH001', 'STU001', 'C001', 'MAT001', '2023-11-15', 'Fall', 'Note for Schedule 1'),
    ('C002', 'TCH002', 'STU002', 'C002', 'WR001', '2023-11-16', 'Fall', 'Note for Schedule 2'),
    ('C003', 'TCH003', 'STU003', 'C003', 'DR001', '2023-11-17', 'Fall', 'Note for Schedule 3'),
    ('C004', 'TCH004', 'STU004', 'C004', 'PE001', '2023-11-18', 'Fall', 'Note for Schedule 4'),
    ('C005', 'TCH005', 'STU005', 'C005', 'MAT001', '2023-11-19', 'Fall', 'Note for Schedule 5'),
    ('C006', 'TCH006', 'STU006', 'C006', 'WR001', '2023-11-20', 'Fall', 'Note for Schedule 6'),
    ('C007', 'TCH007', 'STU007', 'C007', 'DR001', '2023-11-21', 'Fall', 'Note for Schedule 7'),
    ('C008', 'TCH008', 'STU008', 'C008', 'PE001', '2023-11-22', 'Fall', 'Note for Schedule 8'),
    ('C009', 'TCH009', 'STU009', 'C009', 'MAT001', '2023-11-23', 'Fall', 'Note for Schedule 9'),
    ('C010', 'TCH010', 'STU010', 'C010', 'WR001', '2023-11-24', 'Fall', 'Note for Schedule 10');

CREATE TABLE Point(
ID_Student NVARCHAR(50) NOT NULL,
ID_Class NVARCHAR(50) NOT NULL,
ID_Subject NVARCHAR(50) NOT NULL,
ID_Teacher NVARCHAR(50) NOT NULL,
Year INT NOT NULL,
Point FLOAT NOT NULL,
Course_Name NVARCHAR(50) NOT NULL,
Note NVARCHAR(255) NOT NULL,
CONSTRAINT FK_Point_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Point_Teacher FOREIGN KEY (ID_Teacher) REFERENCES Teacher(ID_Teacher),
CONSTRAINT FK_Point_Class FOREIGN KEY (ID_Class) REFERENCES Class(ID_Class),
CONSTRAINT FK_Point_Subject FOREIGN KEY (ID_Subject) REFERENCES Subject(ID_Subject)
);


-- Thêm dữ liệu mẫu vào bảng Point
INSERT INTO Point (ID_Student, ID_Class, ID_Subject, ID_Teacher, Year, Point, Course_Name, Note)
VALUES
    ('STU001', 'C001', 'MAT001', 'TCH001', 2023, 10, 'Fall', 'Note for Point 1'),
    ('STU002', 'C002', 'WR001', 'TCH002', 2023, 9, 'Fall', 'Note for Point 2'),
    ('STU003', 'C003', 'DR001', 'TCH003', 2023, 9.5, 'Fall', 'Note for Point 3'),
    ('STU004', 'C004', 'PE001', 'TCH004', 2023, 8, 'Fall', 'Note for Point 4'),
    ('STU005', 'C005', 'PE001', 'TCH005', 2023, 10, 'Fall', 'Note for Point 5'),
    ('STU006', 'C006', 'DR001', 'TCH006', 2023, 9, 'Fall', 'Note for Point 6'),
    ('STU007', 'C007', 'WR001', 'TCH007', 2023, 9.5, 'Fall', 'Note for Point 7'),
    ('STU008', 'C008', 'MAT001', 'TCH008', 2023, 8.5, 'Fall', 'Note for Point 8'),
    ('STU009', 'C009', 'MAT001', 'TCH009', 2023, 9, 'Fall', 'Note for Point 9'),
    ('STU010', 'C010', 'DR001', 'TCH010', 2023, 10, 'Fall', 'Note for Point 10');

CREATE TABLE Attendance_Student(
ID_Attendance NVARCHAR(50) NOT NULL,
ID_Student_Attendance NVARCHAR(50) NOT NULL,
ID_Class_Attendance NVARCHAR(50) NOT NULL,
ID_Subject_Attendance NVARCHAR(50) NOT NULL,
ID_Teacher_Attendance NVARCHAR(50) NOT NULL,
Status_Attendance BIT NOT NULL,
Attendance_Date DATETIME DEFAULT GETDATE(),
Note NVARCHAR(50),
CONSTRAINT PK_ID_Attendance PRIMARY KEY(ID_Attendance),
CONSTRAINT FK_Attendance_Student FOREIGN KEY (ID_Student_Attendance) REFERENCES Student(ID_Student),
CONSTRAINT FK_Attendance_Class FOREIGN KEY (ID_Class_Attendance) REFERENCES Class(ID_Class),
CONSTRAINT FK_Attendance_Subject FOREIGN KEY (ID_Subject_Attendance) REFERENCES Subject(ID_Subject),
CONSTRAINT FK_Attendance_Teacher FOREIGN KEY (ID_Teacher_Attendance) REFERENCES Teacher(ID_Teacher)
);

-- Insert sample data into Attendance table
INSERT INTO Attendance_Student (ID_Attendance, ID_Student_Attendance, ID_Class_Attendance, ID_Subject_Attendance, ID_Teacher_Attendance, Status_Attendance, Note)
VALUES
('A1', 'STU001', 'C001', 'MAT001', 'TCH001', 1, 'Present'),
('A2', 'STU002', 'C001', 'WR001', 'TCH002', 0, 'Absent'),
('A3', 'STU003', 'C002', 'DR001', 'TCH003', 1, 'Present'),
('A4', 'STU004', 'C002', 'PE001', 'TCH004', 1, 'Present'),
('A5', 'STU005', 'C003', 'MAT001', 'TCH005', 0, 'Absent'),
('A6', 'STU006', 'C003', 'WR001', 'TCH006', 1, 'Present'),
('A7', 'STU007', 'C004', 'DR001', 'TCH007', 1, 'Present'),
('A8', 'STU008', 'C004', 'PE001', 'TCH008', 0, 'Absent'),
('A9', 'STU015', 'C004', 'PE001', 'TCH010', 1, 'Present'),
('A10', 'STU010', 'C005', 'PE001', 'TCH010', 1, 'Present');

CREATE TABLE Health(
ID_Course NVARCHAR(50) NOT NULL,
Course_Name NVARCHAR(50) NOT NULL,
ID_Staff NVARCHAR(50) NOT NULL,
ID_Student NVARCHAR(50) NOT NULL,
Status BIT NOT NULL,
Height INT NOT NULL,
Weight INT NOT NULL,
Note NVARCHAR(255),
CONSTRAINT FK_Health_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Health_Staff FOREIGN KEY (ID_Staff) REFERENCES Staff(ID_Staff),
CONSTRAINT FK_Health_Course FOREIGN KEY (ID_Course) REFERENCES Course(ID_Course)
);
INSERT INTO Health (ID_Course, Course_Name, ID_Staff, ID_Student, Status, Height, Weight, Note)
VALUES
('C003', 'Fall', 'EMP001', 'STU001', 1, 170, 65, 'Normal health condition'),
('C003', 'Fall', 'EMP009', 'STU002', 0, 160, 55, 'Requires follow-up'),
('C003', 'Fall', 'EMP016', 'STU003', 1, 175, 70, 'No specific notes'),
('C003', 'Fall', 'EMP017', 'STU004', 0, 180, 80, 'Needs dietary adjustments'),
('C003', 'Fall', 'EMP001', 'STU005', 1, 170, 65, 'Normal health condition'),
('C003', 'Fall', 'EMP009', 'STU006', 0, 160, 55, 'Requires follow-up'),
('C003', 'Fall', 'EMP016', 'STU007', 1, 175, 70, 'No specific notes'),
('C003', 'Fall', 'EMP017', 'STU008', 0, 180, 80, 'Needs dietary adjustments');

CREATE TABLE Bonus(
ID_Bonus NVARCHAR(50) NOT NULL,
Year INT NOT NULL,
Course_Name NVARCHAR(50) NOT NULL,
Level NVARCHAR(50) NOT NULL,
ID_Student NVARCHAR(50) NOT NULL,
GPA FLOAT NOT NULL,
Note NVARCHAR(255),
CONSTRAINT FK_Bonus_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT PK_ID_Bonus PRIMARY KEY(ID_Bonus)
);
INSERT INTO Bonus (ID_Bonus, Year, Course_Name, Level, ID_Student, GPA, Note)
VALUES
('B001', 2020, 'Fall', 'Good', 'STU021', 8.5, 'High performance in coursework'),
('B002', 2019, 'Summer', 'Excellent', 'STU022', 9, 'Above-average grades'),
('B003', 2021, 'Spring', 'Medium', 'STU023', 6.5, 'Top scorer in the class'),
('B004', 2018, 'Spring', 'Good', 'STU024', 8, 'Consistent academic performance'),
('B005', 2020, 'Summer', 'Good', 'STU025', 8.3, 'Received special recognition'),
('B006', 2019, 'Fall', 'Good', 'STU026', 8.7, 'Active participant in research projects'),
('B007', 2021, 'Spring', 'Good', 'STU027', 8.5, 'Exceptional performance in mathematics'),
('B008', 2018, 'Fall', 'Good', 'STU028', 8.6, 'Completed additional coursework'),
('B009', 2019, 'Summer', 'Good', 'STU029', 8, 'Received scholarship for academic achievements'),
('B010', 2022, 'Summer', 'Good', 'STU030', 8, 'Consistently improving grades');


CREATE TABLE Former_Student(
ID_Bonus NVARCHAR(50) NOT NULL,
ID_Student NVARCHAR(50) NOT NULL,
Year INT NOT NULL,
Course_Name NVARCHAR(50) NOT NULL,
Note NVARCHAR(255),
CONSTRAINT FK_Former_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Former_Bonus FOREIGN KEY (ID_Bonus) REFERENCES Bonus(ID_Bonus)
);
INSERT INTO Former_Student (ID_Bonus, ID_Student, Year, Course_Name, Note)
VALUES
('B001', 'STU021', 2020, 'Fall', 'Good performance in the course'),
('B002', 'STU022', 2019, 'Summer', 'Completed the course successfully'),
('B003', 'STU023', 2021, 'Summer', 'Excellent academic record'),
('B004', 'STU018', 2018, 'Spring', 'Received special recognition'),
('B005', 'STU025', 2020, 'Fall', 'Completed with honors'),
('B006', 'STU026', 2019, 'Summer', 'Research project published'),
('B007', 'STU027', 2021, 'Fall', 'Top scorer in the class'),
('B008', 'STU028', 2018, 'Spring', 'Participated in science fair'),
('B009', 'STU029', 2019, 'Summer', 'Received scholarship'),
('B010', 'STU030', 2022, 'Spring', 'Internship at research institute');

CREATE TABLE Guardian_Student_Relationship(
ID_Student NVARCHAR(50) NOT NULL,
ID_Guardians NVARCHAR(50) NOT NULL,
CONSTRAINT FK_Relationship_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Relationship_Guardians FOREIGN KEY (ID_Guardians) REFERENCES Guardians(ID_Guardians)
);
INSERT INTO Guardian_Student_Relationship (ID_Student, ID_Guardians)
VALUES
('STU001', 'GRD001'),
('STU002', 'GRD002'),
('STU003', 'GRD003'),
('STU004', 'GRD001'),
('STU005', 'GRD004'),
('STU006', 'GRD002'),
('STU007', 'GRD005'),
('STU008', 'GRD003'),
('STU009', 'GRD006'),
('STU010', 'GRD004'),
('STU011', 'GRD007'),
('STU012', 'GRD005'),
('STU013', 'GRD008'),
('STU014', 'GRD006'),
('STU015', 'GRD009'),
('STU016', 'GRD007'),
('STU017', 'GRD010'),
('STU018', 'GRD008'),
('STU019', 'GRD009'),
('STU020', 'GRD010');

CREATE TABLE Tuition(
ID_Bill NVARCHAR(50) NOT NULL,
Money FLOAT NOT NULL,
Status BIT NOT NULL,
ID_Staff NVARCHAR(50) NOT NULL,
ID_Guardians NVARCHAR(50) NOT NULL,
Year INT NOT NULL,
ID_Course NVARCHAR(50) NOT NULL,
Course_Name NVARCHAR(50) NOT NULL,
Bill_Date DATETIME DEFAULT GETDATE(),
Note NVARCHAR(255),
CONSTRAINT PK_ID_Bill PRIMARY KEY(ID_Bill),
CONSTRAINT FK_Tuition_Guardians FOREIGN KEY (ID_Guardians) REFERENCES Guardians(ID_Guardians),
CONSTRAINT FK_Tuition_Staff FOREIGN KEY (ID_Staff) REFERENCES Staff(ID_Staff)
);
INSERT INTO Tuition (ID_Bill, Money, Status, ID_Staff, ID_Guardians, Year, ID_Course, Course_Name, Note)
VALUES
('B001', 500, 1, 'EMP001', 'GRD001', 2023, 'C001', 'Spring', 'Tuition 1'),
('B002', 500, 1, 'EMP002', 'GRD002', 2023, 'C002', 'Summer', 'Tuition 1'),
('B003', 500, 1, 'EMP003', 'GRD003', 2023, 'C003', 'Fall', 'Tuition 1'),
('B004', 500, 1, 'EMP004', 'GRD004', 2023, 'C001', 'Summer', 'Tuition 2'),
('B005', 500, 1, 'EMP005', 'GRD005', 2023, 'C002', 'Spring', 'Tuition 2'),
('B006', 650, 1, 'EMP006', 'GRD006', 2023, 'C003', 'Fall', 'Tuition 2'),
('B007', 700, 1, 'EMP007', 'GRD007', 2023, 'C001', 'Fall', 'Tuition 3'),
('B008', 4500, 1, 'EMP008', 'GRD008', 2023, 'C002', 'Spring', 'Tuition 3'),
('B009', 550, 1, 'EMP009', 'GRD009', 2023, 'C003', 'Summer', 'Tuition 3'),
('B010', 600, 1, 'EMP010', 'GRD010', 2023, 'C001', 'Spring', 'Tuition 4'),
('B011', 500, 1, 'EMP011', 'GRD011', 2023, 'C002', 'Spring', 'Tuition 4'),
('B012', 700, 1, 'EMP012', 'GRD012', 2023, 'C003', 'Fall', 'Tuition 4'),
('B013', 450, 1, 'EMP013', 'GRD013', 2023, 'C001', 'Summer', 'Tuition 5'),
('B014', 550, 1, 'EMP014', 'GRD014', 2023, 'C002', 'Summer', 'Tuition 5'),
('B015', 600, 1, 'EMP015', 'GRD015', 2023, 'C003', 'Fall', 'Tuition 5');

CREATE TABLE Poor_Households(
ID_Poor_Households NVARCHAR(50) NOT NULL,
ID_Guardians NVARCHAR(50) NOT NULL,
Status BIT NOT NULL,
Note NVARCHAR(50),
CONSTRAINT PK_Poor_Households PRIMARY KEY(ID_Poor_Households),
CONSTRAINT FK_Poor_Households_Guardians FOREIGN KEY (ID_Guardians) REFERENCES Guardians(ID_Guardians)
);
INSERT INTO Poor_Households (ID_Poor_Households, ID_Guardians, Status, Note)
VALUES
('PH001', 'GRD001', 1, 'Family struggling financially'),
('PH002', 'GRD003', 1, 'Low income household'),
('PH003', 'GRD013', 1, 'Unemployed parents'),
('PH004', 'GRD015', 1, 'Single parent'),
('PH005', 'GRD011', 1, 'Limited access to education and resources');
CREATE TABLE Teacher_Salary(
ID_Teacher_Salary NVARCHAR(50) NOT NULL,
ID_Teacher NVARCHAR(50) NOT NULL,
Number_Of_Working_Days INT NOT NULL,
Daily_Wage FLOAT NOT NULL,
Month INT NOT NULL,
Year INT NOT NULL,
Note NVARCHAR(50),
CONSTRAINT PK_Teacher_Salary PRIMARY KEY(ID_Teacher_Salary),
CONSTRAINT FK_Teacher_Salary FOREIGN KEY (ID_Teacher) REFERENCES Teacher(ID_Teacher)
);
INSERT INTO Teacher_Salary (ID_Teacher_Salary, ID_Teacher, Number_Of_Working_Days, Daily_Wage, Month, Year, Note)
VALUES
('TS001', 'TCH001', 20, 100, 11, 2023, 'Salary for November 2023'),
('TS002', 'TCH002', 22, 150, 11, 2023, 'Salary for November 2023'),
('TS003', 'TCH003', 18, 200, 11, 2023, 'Salary for November 2023'),
('TS004', 'TCH004', 20, 120, 11, 2023, 'Salary for November 2023'),
('TS005', 'TCH005', 21, 130, 11, 2023, 'Salary for November 2023');

CREATE TABLE Staff_Salary(
ID_Staff_Salary NVARCHAR(50) NOT NULL,
ID_Staff NVARCHAR(50) NOT NULL,
Number_Of_Working_Days INT NOT NULL,
Daily_Wage FLOAT NOT NULL,
Month INT NOT NULL,
Year INT NOT NULL,
Note NVARCHAR(50),
CONSTRAINT PK_Staff_Salary PRIMARY KEY(ID_Staff_Salary),
CONSTRAINT FK_Staff_Salary FOREIGN KEY (ID_Staff) REFERENCES Staff(ID_Staff)
);
-- Thêm dữ liệu mẫu vào bảng Staff_Salary
INSERT INTO Staff_Salary (ID_Staff_Salary, ID_Staff, Number_Of_Working_Days, Daily_Wage, Month, Year, Note)
VALUES
('SS001', 'EMP001', 22, 120, 11, 2023, 'Salary for November 2023'),
('SS002', 'EMP002', 20, 100, 11, 2023, 'Salary for November 2023'),
('SS003', 'EMP003', 21, 130, 11, 2023, 'Salary for November 2023'),
('SS004', 'EMP004', 23, 80, 11, 2023, 'Salary for November 2023'),
('SS005', 'EMP005', 22, 95, 11, 2023, 'Salary for November 2023');

CREATE TABLE Finance(
ID_Finance NVARCHAR(50) NOT NULL,
Total_Money FLOAT NOT NULL,
Month INT NOT NULL,
Year INT NOT NULL,
Total_Salary FLOAT NOT NULL,
Remaining FLOAT NOT NULL,
Note NVARCHAR(50),
);
CREATE TABLE Personal_Profile(
ID_Student NVARCHAR(50) NOT NULL,
ID_Staff NVARCHAR(50) NOT NULL,
Date_Of_Profile_Creation DATETIME DEFAULT GETDATE(),
Note NVARCHAR(255),
ID_Profile NVARCHAR(50) NOT NULL,
CONSTRAINT PK_ID_Profile PRIMARY KEY(ID_Profile),
CONSTRAINT FK_Profile_Student FOREIGN KEY (ID_Student) REFERENCES Student(ID_Student),
CONSTRAINT FK_Profile_Salary FOREIGN KEY (ID_Staff) REFERENCES Staff(ID_Staff)
);
CREATE TABLE Admission(
ID_Profile NVARCHAR(50) NOT NULL,
Admission_Date DATETIME NOT NULL,
Note NVARCHAR(255),
CONSTRAINT FK_ID_Profile FOREIGN KEY (ID_Profile) REFERENCES Personal_Profile(ID_Profile)
);

CREATE TABLE Staff_Working_Day(
ID_Staff NVARCHAR(50) NOT NULL,
Day INT NOT NULL,
Month INT NOT NULL,
Year INT NOT NULL,
Note NVARCHAR(255),
CONSTRAINT FK_Staff_Working_Day FOREIGN KEY (ID_Staff) REFERENCES Staff(ID_Staff)
);
-- Insert sample data into the Staff_Working_Day table

INSERT INTO Staff_Working_Day (ID_Staff, Day, Month, Year, Note)
VALUES
    ('EMP001', 1, 11, 2023, 'Normal working day'),
    ('EMP002', 5, 11, 2023, NULL),
    ('EMP003', 2, 11, 2023, 'Need to work overtime'),
    ('EMP004', 3, 11, 2023, 'Normal working day'),
    ('EMP005', 8, 11, 2023, NULL),
    ('EMP006', 4, 11, 2023, 'Need a day off');
