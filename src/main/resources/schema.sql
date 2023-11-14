CREATE TABLE Todo (
  Id VARCHAR(60) PRIMARY KEY,
  Name VARCHAR(255),
  Due_Date DATE,
  Completion_Date DATE,
  Folder_Id INT,
  Priority ENUM ('High', 'Med', 'Low'),
  Description TEXT,
  FOREIGN KEY (Folder_Id) REFERENCES Folder(Id)
);

CREATE TABLE Folder (
    Id VARCHAR(60) PRIMARY KEY,
    Name VARCHAR(255),
);
