CREATE TABLE Todo (
  Id VARCHAR(60) PRIMARY KEY,
  Name VARCHAR(255),
  Due_Date DATE,
  Completion_Date DATE,
  Folder_Id VARCHAR(60),
  Priority VARCHAR(20),
  Description TEXT,
  FOREIGN KEY (Folder_Id) REFERENCES Folder(Id)
);

CREATE TABLE Folder (
    Id VARCHAR(60) PRIMARY KEY,
    Name VARCHAR(255),
);
