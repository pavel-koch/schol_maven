-- liquibase formatted sql

-- changeset pkochetkov:1
CREATE TABLE studentMig (
    id   SERIAL,
    name TEXT)

-- changeset pkochetkov:2
CREATE TABLE facultyMig (
    id   SERIAL,
    name TEXT,
    color TEXT)