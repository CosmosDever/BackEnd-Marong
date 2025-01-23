-- Create Users Table
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       gmail VARCHAR(255) NOT NULL UNIQUE,
                       full_name VARCHAR(255) NOT NULL,
                       birthday DATETIME,
                       telephone INT,
                       gender VARCHAR(50),
                       password VARCHAR(255) NOT NULL,
                       picture VARCHAR(255),
                       INDEX (gmail)
);

-- Insert Mock Data for Users
INSERT INTO users (gmail, full_name, birthday, telephone, gender, password, picture)
VALUES
    ('admin@example.com', 'John Doe', '1990-01-01 00:00:00', '0849874867', 'Male', '$2a$10$WWIIXVeEp84wH8RqbM0Z1.jycfkQOaEnkA/U1SUIVZDJ0258lHa2e', 'https://th.bing.com/th/id/OIP.GKAbRpYzDlJa139WC8xPtwHaIC?rs=1&pid=ImgDetMain'),
    ('User@example.com', 'Jane Smith', '1992-02-02 00:00:00', '0984783758', 'Female', '$2a$10$acpqGHt9lC1C.zvmohlyo.ZAjLHerzte0zcpowUHDtpwKBDHzNU8y', 'https://th.bing.com/th/id/OIP.GKAbRpYzDlJa139WC8xPtwHaIC?rs=1&pid=ImgDetMain'),
    ('msaidmin@gmail.com', 'Master Admin', '1992-02-02 00:00:00', '0984783758','Male', '$2a$10$ZRCWxcMW1egzmMq66ggkwe7UwTVApaql9AkuL3GdaUp8ZBykzWoLi', 'https://th.bing.com/th/id/OIP.GKAbRpYzDlJa139WC8xPtwHaIC?rs=1&pid=ImgDetMain');

-- Create Cases Table
CREATE TABLE cases (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       category VARCHAR(255) NOT NULL,
                       detail VARCHAR(255) NOT NULL,
                       picture VARCHAR(255),
                       picture_done VARCHAR(255),
                       location_description VARCHAR(255),
                       latitude VARCHAR(255),
                       longitude VARCHAR(255),
                       date_opened DATETIME DEFAULT NOW(),
                       date_closed DATETIME,
                       INDEX (category)
);

-- Insert Mock Data for Cases
INSERT INTO cases (category, detail, picture, location_description, latitude, longitude)
VALUES
    ('Road Damage', 'Cracked road at Main Street', 'https://th.bing.com/th/id/OIP.6eGGNKkQ0OP7QYyXQ7uvYgHaE7?w=296&h=197&c=7&r=0&o=5&dpr=1.5&pid=1.7', 'Main Street', '13.720211', '100.558915'),
    ('Damaged Sidewalk', 'Sunken sidewalk near the park', 'https://th.bing.com/th/id/OIP.KOdf5mbVQk0bYgKUfKlB_QHaFW?rs=1&pid=ImgDetMain', 'Park Avenue', '13.714444', '100.571352'),
    ('Overpass Damage', 'Structural damage to the overpass near the highway', 'https://thethaiger.com/wp-content/uploads/2023/07/S__13640050.jpeg', 'Highway Overpass', '13.721234', '100.560987'),
    ('Wire Damage', 'Electric wires hanging low at Elm Street', 'https://thumbs.dreamstime.com/b/chaos-cables-wires-electric-pole-bangkok-thail-thailand-92304698.jpg', 'Elm Street', '13.722345', '100.562345');

-- Create Reports Table
CREATE TABLE reports (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         status VARCHAR(50) NOT NULL DEFAULT 'pending',
                         damage_value DECIMAL(10,2),
                         category VARCHAR(255) NOT NULL,
                         detail_detect TEXT NOT NULL,
                         case_id INT NOT NULL,
                         user_id INT NOT NULL,
                         created_at DATETIME DEFAULT NOW(),
                         updated_at DATETIME DEFAULT NOW(),
                         INDEX (status),
                         INDEX (damage_value),
                         INDEX (category),
                         FOREIGN KEY (case_id) REFERENCES cases(id),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Mock Data for Reports
INSERT INTO reports (status, damage_value, category, detail_detect, case_id, user_id)
VALUES
    ('InProgress', 1000.00, 'Road Damage', 'The road is cracked and needs repair.', 1, 1),
    ('Waiting', 500.00, 'Damaged Sidewalk', 'The sidewalk has a large hole in it.', 2, 2),
    ('Done', 2000.00, 'Overpass Damage', 'The overpass has visible cracks and requires immediate structural assessment.', 3, 2),
    ('Cancel', 150.00, 'Wire Damage', 'Electric wires are hanging low and pose a safety hazard.', 4, 2);


-- Create News Table
CREATE TABLE news (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      type VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      picture VARCHAR(255),
                      location_description VARCHAR(255),
                      latitude VARCHAR(255),
                      longitude VARCHAR(255),
                      published_at DATETIME DEFAULT NOW(),
                      INDEX (title)
);

-- Insert Mock Data for News
INSERT INTO news (title,type, content, picture, location_description,latitude,longitude)
VALUES
    ('Road Repairs Starting Soon','Road Damage', 'The city will begin repairing Main Street next week.', 'https://th.bing.com/th/id/OIP.6eGGNKkQ0OP7QYyXQ7uvYgHaE7?w=296&h=197&c=7&r=0&o=5&dpr=1.5&pid=1.7', 'Main Street','13.715118','100.579940'),
    ('Park Sidewalk Renovation','Damaged Sidewalk', 'The park sidewalk will be renovated starting this month.', 'https://th.bing.com/th/id/OIP.KOdf5mbVQk0bYgKUfKlB_QHaFW?rs=1&pid=ImgDetMain', 'City Park','13.706424','100.574480');

-- Create Email Verifications Table
CREATE TABLE email_verifications (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     user_id INT NOT NULL,
                                     token VARCHAR(255) NOT NULL UNIQUE,
                                     created_at DATETIME DEFAULT NOW(),
                                     expires_at DATETIME NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES users(id)
);


-- Create Password Resets Table
CREATE TABLE password_resets (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT NOT NULL ,
                                 email VARCHAR(255) NOT NULL,
                                 token VARCHAR(255) NOT NULL UNIQUE,
                                 created_at DATETIME DEFAULT NOW(),
                                 expires_at DATETIME NOT NULL,
                                 FOREIGN KEY (user_id) REFERENCES users(id)
);


-- Create Roles Table
CREATE TABLE roles (
                       id INT AUTO_INCREMENT  PRIMARY KEY,
                       role_name VARCHAR(255) NOT NULL UNIQUE
                      
);

-- Insert Mock Data for Roles
INSERT INTO roles (role_name)
VALUES
    ('ROLE_Admin'),
    ('ROLE_User'),
    ('ROLE_master Admin');


-- Create User Roles Table
CREATE TABLE user_roles (
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Insert Mock Data for User Roles
INSERT INTO user_roles (user_id, role_id)
VALUES
    (1, 1),  -- John Doe as Admin
    (2, 2),  -- Jane Smith as User
    (3, 3);  -- Master Admin

CREATE TABLE notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    case_id INT NOT NULL,
    user_id INT NOT NULL,
    detail_detect TEXT,
    date_closed DATETIME,
    status VARCHAR(50),
    FOREIGN KEY (case_id) REFERENCES cases(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (detail_detect) REFERENCES reports(detail_detect),
    FOREIGN KEY (date_closed) REFERENCES cases(date_closed),
    FOREIGN KEY (status) REFERENCES reports(status)
);

