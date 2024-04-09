create table if not exists users (
	user_id bigserial,
	password varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	PRIMARY KEY (user_id)
);

create table if not exists roles (
	user_id int8 NOT NULL,
	role varchar(255) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table if not exists customers (
	id bigserial,
	name varchar(255) NOT NULL,
	phone varchar(255) NOT NULL,
	user_id int8,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table if not exists entrepreneurs (
	id bigserial,
	name varchar(255) NOT NULL,
	phone varchar(255) NOT NULL,
	tax_number varchar(255) NOT NULL,
	user_id int8,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table if not exists appointments (
	id bigserial,
	date_time timestamp(6),
	finish_time time(6),
	status varchar(255),
	customer_id int8 NULL,
	entrepreneur_id int8 NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (customer_id) REFERENCES customers(id),
	FOREIGN KEY (entrepreneur_id) REFERENCES entrepreneurs(id)
);

create table if not exists notifications (
	id bigserial,
	date_time timestamp(6),
	status varchar(255),
	appointment_id int8,
	PRIMARY KEY (id),
	FOREIGN KEY (appointment_id) REFERENCES appointments(id)
);