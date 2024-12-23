-- Create Users Table
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       gmail VARCHAR(255) NOT NULL UNIQUE,
                       full_name VARCHAR(255) NOT NULL,
                       birthday DATETIME,
                       telephone DATETIME,
                       gender VARCHAR(50),
                       password VARCHAR(255) NOT NULL,
                       picture VARCHAR(255),
                       INDEX (gmail)
);

-- Insert Mock Data for Users
INSERT INTO users (gmail, full_name, birthday, telephone, gender, password, picture)
VALUES
    ('john.doe@example.com', 'John Doe', '1990-01-01 00:00:00', '2024-01-01 12:00:00', 'Male', 'hashed_password_1', 'http://example.com/john.jpg'),
    ('jane.smith@example.com', 'Jane Smith', '1992-02-02 00:00:00', '2024-02-02 12:00:00', 'Female', 'hashed_password_2', 'http://example.com/jane.jpg');

-- Create Cases Table
CREATE TABLE cases (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       category VARCHAR(255) NOT NULL,
                       detail VARCHAR(255) NOT NULL,
                       picture VARCHAR(255),
                       location VARCHAR(255),
                       date_opened DATETIME DEFAULT NOW(),
                       date_closed DATETIME,
                       INDEX (category)
);

-- Insert Mock Data for Cases
INSERT INTO cases (category, detail, picture, location)
VALUES
    ('Road Damage', 'Cracked road at Main Street', 'http://example.com/road.jpg', 'Main Street'),
    ('Damaged Sidewalk', 'Sunken sidewalk near the park', 'http://example.com/sidewalk.jpg', 'Park Avenue');

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
    ('pending', 1000.00, 'Road Damage', 'The road is cracked and needs repair.', 1, 1),
    ('closed', 500.00, 'Damaged Sidewalk', 'The sidewalk has a large hole in it.', 2, 2);

-- Create News Table
CREATE TABLE news (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      content TEXT NOT NULL,
                      picture VARCHAR(255),
                      location VARCHAR(255),
                      published_at DATETIME DEFAULT NOW(),
                      INDEX (title)
);

-- Insert Mock Data for News
INSERT INTO news (title, content, picture, location)
VALUES
    ('Road Repairs Starting Soon', 'The city will begin repairing Main Street next week.', 'http://example.com/road-repair.jpg', 'Main Street'),
    ('Park Sidewalk Renovation', 'The park sidewalk will be renovated starting this month.', 'http://example.com/sidewalk-renovation.jpg', 'City Park');

-- Create Email Verifications Table
CREATE TABLE email_verifications (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     user_id INT NOT NULL,
                                     token VARCHAR(255) NOT NULL UNIQUE,
                                     created_at DATETIME DEFAULT NOW(),
                                     expires_at DATETIME NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Mock Data for Email Verifications
INSERT INTO email_verifications (user_id, token, expires_at)
VALUES
    (1, 'verification_token_1', '2024-01-01 23:59:59'),
    (2, 'verification_token_2', '2024-02-01 23:59:59');

-- Create Password Resets Table
CREATE TABLE password_resets (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT NOT NULL,
                                 token VARCHAR(255) NOT NULL UNIQUE,
                                 created_at DATETIME DEFAULT NOW(),
                                 expires_at DATETIME NOT NULL,
                                 FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Mock Data for Password Resets
INSERT INTO password_resets (user_id, token, expires_at)
VALUES
    (1, 'password_reset_token_1', '2024-01-01 23:59:59'),
    (2, 'password_reset_token_2', '2024-02-01 23:59:59');

-- Create Roles Table
CREATE TABLE roles (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       role_name VARCHAR(255) NOT NULL UNIQUE,
                       description VARCHAR(255)
);

-- Insert Mock Data for Roles
INSERT INTO roles (role_name, description)
VALUES
    ('Admin', 'Administrator with full access'),
    ('User', 'Regular user with limited access');

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
    (2, 2);  -- Jane Smith as User

-- Create Permissions Table
CREATE TABLE permissions (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             permission_name VARCHAR(255) NOT NULL UNIQUE,
                             description VARCHAR(255)
);

-- Insert Mock Data for Permissions
INSERT INTO permissions (permission_name, description)
VALUES
    ('view_reports', 'Permission to view reports'),
    ('edit_reports', 'Permission to edit reports');

-- Create Role Permissions Table
CREATE TABLE role_permissions (
                                  role_id INT NOT NULL,
                                  permission_id INT NOT NULL,
                                  PRIMARY KEY (role_id, permission_id),
                                  FOREIGN KEY (role_id) REFERENCES roles(id),
                                  FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- Insert Mock Data for Role Permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES
    (1, 1),  -- Admin can view reports
    (1, 2);  -- Admin can edit reports
