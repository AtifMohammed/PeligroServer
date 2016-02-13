# PeligroServer
Backend server for my app peligro
## Database
```
CREATE TABLE userData (
    name VARCHAR2(40) CONSTRAINT NN_userData_name,
    Phone NUMBER(10) CONSTRAINT NN_userData_Phone NOT NULL,
    email VARCHAR2(40) CONSTRAINT NN_userData_email,
    password VARCHAR2(40) CONSTRAINT NN_userData_password,
    gender VARCHAR2(40),
    disease VARCHAR2(40),
    dob varchar2(20),
    lat_home DOUBLE PRECISION,
    lat_cur DOUBLE PRECISION,
    long_home DOUBLE PRECISION,
    long_cur DOUBLE PRECISION,
    Phone1 NUMBER(10),
    REGKEY VARCHAR2(160),
    victim number
    PRIMARY KEY (Phone)
);
```
