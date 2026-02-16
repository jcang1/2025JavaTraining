CREATE TABLE books (
	id SERIAL PRIMARY KEY NOT NULL,
	title VARCHAR(50),
	author VARCHAR(50),
	is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE users (
   id SERIAL PRIMARY KEY NOT NULL,
   name VARCHAR(50)
);

CREATE TABLE loans (
   id SERIAL PRIMARY KEY NOT NULL,
   user_id INT,
   book_id INT,
   FOREIGN KEY (user_id) REFERENCES users(id),
   FOREIGN KEY (book_id) REFERENCES books(id)
);

INSERT INTO users (name)
VALUES
('John'),
('Paul'),
('Mark'),
('Matthew'),
('Luke');

INSERT INTO books (title, author)
VALUES ('Java Crash Course', 'Eric'),
       ('Eloquent Oracle', 'Bert'),
       ('Basic PostgreSQL', 'Steve'),
	   ('To Kill a Mockingbird','Harper Lee'),
	   ('1984','George Orwell');
	   
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE books TO postgres;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE users TO postgres;
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE loans TO postgres;

GRANT ALL PRIVILEGES ON SEQUENCE books_id_seq TO postgres;
GRANT ALL PRIVILEGES ON SEQUENCE users_id_seq TO postgres;	   
GRANT ALL PRIVILEGES ON SEQUENCE loans_id_seq TO postgres;	  