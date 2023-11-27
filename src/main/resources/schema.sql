CREATE TABLE IF NOT EXISTS folder (
                                      id VARCHAR(60) PRIMARY KEY,
                                      name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS todo (
                      id VARCHAR(60) PRIMARY KEY,
                      name VARCHAR(255),
                      desc TEXT,
                      due_date DATE,
                      completion_date DATE,
                      folder VARCHAR(60),
                      priority VARCHAR(20),
                      FOREIGN KEY (folder) REFERENCES folder(id)
);