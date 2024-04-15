-- Use this script to set up your Planetarium database

-- needed for referential integrity enforcement
PRAGMA foreign_keys = 1;

create table users(
	id INTEGER primary key,
	username TEXT unique,
	password TEXT
);

create table planets(
	id INTEGER primary key,
	name TEXT,
	ownerId INTEGER references users(id)
);

create table moons(
	id INTEGER primary key,
	name TEXT,
	myPlanetId INTEGER references planets(id)
);