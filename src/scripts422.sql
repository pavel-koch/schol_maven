В этом задании по описанию необходимо спроектировать таблицы, связи между ними и корректно определить типы данных.
Здесь не важно, какой тип вы выберете, например, для данных, представленных в виде строки (varchar или text).
Важно, что вы выберете один из строковых типов, а не числовых, например.

Описание структуры:
У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
create table people(
id serial primary key,
name varchar(100) not null,
age integer not null,
license BOOLEAN not null
);

У каждой машины есть марка, модель и стоимость.
create table cars(
id serial primary key,
brand varchar(100) not null,
model varchar(100) not null,
price decimal(10,2) not null
);

Также не забудьте добавить таблицам первичные ключи и связать их.
у каждого человека есть машина. Причем несколько человек могут пользоваться одной машиной.
create table people_cars(
person_id primary key,
cars_id primary key,
primary key(people_id, cars_id),
foreign key(person_id) references people(id) on delete cascade,
foreign key(cars_id) references cars(id) on delete cascade
);