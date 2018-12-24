CREATE SEQUENCE seq_id_store START 1 ;
CREATE	TABLE store (
	id bigint CONSTRAINT pk_id_store PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	address  VARCHAR(200)
);

CREATE SEQUENCE seq_id_order START 1 ;
CREATE	TABLE orders (
	id bigint CONSTRAINT pk_id_order PRIMARY KEY,
	confirmation_date timestamp NOT NULL,
	address  VARCHAR(200),
	status VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_id_order_item START 1 ;
CREATE	TABLE order_item (
	id bigint CONSTRAINT pk_id_order_item PRIMARY KEY,
	description VARCHAR(150) NOT NULL,
	unit_price integer NOT NULL,
	quantity integer NOT NULL,
	status VARCHAR(50) NOT NULL,
	order_id bigint NOT NULL,
	FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE SEQUENCE seq_id_payment START 1 ;
CREATE	TABLE payment (
	id bigint CONSTRAINT pk_id_payment PRIMARY KEY,
	credit_card_number VARCHAR(50) NOT NULL,
	payment_date timestamp NOT NULL,
	status VARCHAR(50) NOT NULL,
	order_id bigint NOT NULL
);






