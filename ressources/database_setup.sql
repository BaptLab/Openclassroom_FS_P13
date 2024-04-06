CREATE TABLE USER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lastName VARCHAR(255),
    firstName VARCHAR(255),
    birthDate DATE,
    status VARCHAR(255),
    address VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE AGENCY (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    adress VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE RENTAL_OFFER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    departCity VARCHAR(255),
    backCity VARCHAR(255),
    debutDate DATE,
    backDate DATE,
    vehicaleccategory VARCHAR(255),
    price DECIMAL(10, 2),
    agencyId INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (agencyId) REFERENCES AGENCY(id)
);

CREATE TABLE RENTAL (
    id INT AUTO_INCREMENT PRIMARY KEY,
    offerId INT,
    userId INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (offerId) REFERENCES RENTAL_OFFER(id),
    FOREIGN KEY (userId) REFERENCES USER(id)
);

CREATE TABLE BILL (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    rentalId INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES USER(id),
    FOREIGN KEY (rentalId) REFERENCES RENTAL(id)
);

CREATE TABLE SUPPORT_MESSAGE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contentmsg TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    userId INT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    object VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES USER(id)
);

CREATE TABLE CHAT_CONVERSATION (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE CHAT_MESSAGE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chatId VARCHAR(255),
    senderId VARCHAR(255),
    recipientId VARCHAR(255),
    conversation_id INT,
    user_id INT,
    userFirstName VARCHAR(255),
    contentChatMsg TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (conversation_id) REFERENCES CHAT_CONVERSATION(id),
    FOREIGN KEY (user_id) REFERENCES USER(id)
);