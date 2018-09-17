DELETE FROM status;
DELETE FROM orders;
DELETE FROM employees;
DELETE FROM typeoffurnitures;
DELETE FROM departments;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO departments (name) VALUES
  ('мягкая мебель'),('системы хранения'),('офисная мебель');

INSERT INTO typeoffurnitures (name, number_of_seconds_performed_by_one_person, department__id)
VALUES ('кровать', 864000, 100000),('диван', 1036800, 100000), ('кресло', 1728000, 100000),  /*10, 12, */
       ('шкаф', 864000, 100001),('тумба', 1468800, 100001), ('полка', 864000, 100001),       /*10, 17, 10*/
       ('стол', 1123200, 100002),('стул', 1036800, 100002), ('кресло-качалка', 1296000, 100002);   /*13, 12, 15*/

INSERT INTO employees (name, department_id, status_work) VALUES
  ('Набойщиков Сергей Прокофиевич', 100000, false),('Ярков Поликарп Феликсович',100000, false),('Горелова Марина Юлиевна',100000, false),
  ('Созонова Алиса Германовна', 100000, false),
  ('Маслюк Мир Никанорович', 100001, false),('Полухин Назар Демьянович',100001, false),('Маркова Клара Кузьмевна',100001, false),
  ('Ясногородский Валерьян Олегович', 100001, false),
  ('Сюсин Бронислав Серафимович', 100002, false),('Желвакова Елена Иларионовна',100002, false),('Пузакова Клара Якововна',100002, false),
  ('Ткаченко Моисей Назарович', 100002, false),('Вихорева Лилия Александровна',100002, false),('Янушко Агафья Елизаровна',100002, false),
  ('Якубова Стела Михеевна', 100002, false),('Янишин Родион Геннадиевич',100002, false),('Кусков Антип Савелиевич',100002, false);

INSERT INTO status (name) VALUES
  ('STATUS_PENDING'),
  ('STATUS_INTHEWORK'),
  ('STATUS_FINISHED');

INSERT INTO orders (name, type_of_furniture_id, create_order, status, end_order, actual_deadline) VALUES
  ('ордер№100010', 100004, '2018-09-16 15:04:00', 'STATUS_PENDING', null, null),
  ('ордер№100011', 100008, '2018-09-16 15:04:00', 'STATUS_PENDING', null, null),
  ('ордер№100012', 100003, '2018-09-16 15:04:00', 'STATUS_PENDING', null, null),
  ('ордер№100013', 100007, '2018-09-16 15:04:00', 'STATUS_PENDING', null, null);

