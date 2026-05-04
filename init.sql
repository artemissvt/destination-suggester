USE defaultdb;

CREATE TABLE IF NOT EXISTS users (
        user_id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS recommendations (
        rec_id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT NOT NULL,
        recommendation_data TEXT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
    );