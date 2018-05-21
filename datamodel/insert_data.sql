use bshop;

insert into author values(1, 'Пушкин', 'Величайший поэт');
insert into author values(2, 'Лермонтов', 'Еще один величайший поэт');
insert into author values(3, 'Иванов', 'Самый величайший превиликий поэт современности. Неповторимый стиль, взрывоопасное возмуждение. Бла-бла-бла');
insert into author values(4, 'Петров', 'Тестовый автор');
insert into author values(5, 'Петровский', 'Тестовый автор');
insert into author values(6, 'Петропавловский', 'Тестовый автор');
insert into author values(7, 'Петрович', 'Тестовый автор');
insert into author values(8, 'Петро', 'Тестовый автор');
insert into author values(9, 'Петр', 'Тестовый автор');
insert into author values(10, 'Петрозаводск', 'Тестовый автор');
insert into author values(11, 'Петрозаводскск', 'Тестовый автор');
insert into author values(12, 'Стивен Хокинг', 'Ученый, писатель, человек');

insert into book_category values(1, 'Учебная литература');
insert into book_category values(2, 'Детям и родителям');
insert into book_category values(3, 'Бизнес-литература');
insert into book_category values(4, 'Художественная литература');
insert into book_category values(5, 'Научная литература');

insert into book values(1, 'Сборник стихов Пушкина', 'Описание: сборник стихов Пушкина', 1, 4, 123.45, 10);
insert into book values(2, 'Капитанская дочка', 'Описание №2', 1, 4, 300, 10);
insert into book values(3, 'Сборник стихов Лермонтова', 'Описание №3', 2, 4, 200, 5);
insert into book values(4, 'Герой нашего времени', 'Описание', 2, 4, 500, 5);
insert into book values(5, 'Мцыри', 'Описание', 2, 4, 234.23, 5);
insert into book values(6, 'Бородино', 'Описание', 2, 4, 213.4, 5);
insert into book values(7, 'Смерть поэта', 'Описание', 2, 4, 789.31, 5);
insert into book values(8, 'Демон', 'Описание', 2, 4, 231.23, 5);
insert into book values(9, 'Дума', 'Описание', 2, 4, 867.3, 5);
insert into book values(10, 'Песня о купце Калашникове', 'Описание', 2, 4, 2133, 5);
insert into book values(11, 'Княгиня Лиговская', 'Описание', 2, 4, 213.3, 5);
insert into book values(12, 'Краткая история времени', 
'Единственная нормальная книга в нашем магазине, а не вот это вот все', 12, 5, 213.3, 5);

insert into arrival values(1, 11, 123, '1998-09-13');
insert into arrival values(2, 1, 3, '2008-12-15');
insert into arrival values(3, 200, 20, '2009-09-13');
insert into arrival values(4, 110, 93, '2010-09-13');
insert into arrival values(5, 11, 123, '1998-09-13');
insert into arrival values(6, 11, 123, '1998-09-13');
insert into arrival values(7, 11, 123, '1998-09-13');

insert into client values (0, 'Администратор', 'admin', 'admin', null);
insert into client values (1, 'Иван Иванов', 'dimarejf', '123', null);
insert into client values (2, 'Петр Петров', 'ppp', '123', 1);

insert into bshop.order values (1, 1, 'Active', null, now());
insert into order_book values(1, 1, 1);
insert into order_book values(1, 2, 3);
insert into bshop.order values (2, 1, 'Completed', now(), now());
insert into order_book values(2, 3, 10);
insert into order_book values(2, 4, 10);
insert into bshop.order values (3, 2, 'Completed', now(), now());
insert into order_book values(3, 5, 10);
insert into order_book values(3, 6, 10);




