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
	name TEXT unique,
	ownerId INTEGER references users(id) ON DELETE CASCADE
);

create table moons(
	id INTEGER primary key,
	name TEXT unique,
	myPlanetId INTEGER references planets(id) ON DELETE CASCADE
);