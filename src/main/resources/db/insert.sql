SET FOREIGN_KEY_CHECKS = 0;

truncate table  pet;
truncate table  store;


INSERT into store(`id`, `name`, `location`, `contact_no`)
VALUES (1, 'our store', 'Lagos', '090890'),
(2, 'my store', 'Kano', '090891'),
(3, 'their store', 'Jigawa', '090892'),
(4, 'his store', 'Abuja', '090893'),
(5, 'her store', 'Calabar', '090894');


INSERT into pet(`id`, `name`, `color`, `breed`, `age`, `pet_sex`, `store_id`)
VALUES (31, 'jill', 'blue', 'parrot', 6, 'MALE', 21),
(32, 'jack', 'pink', 'dog', 2, 'FEMALE', 21),
(33, 'sally', 'white', 'goat', 3, 'MALE', 21),
(34, 'milly', 'brown', 'rabbit', 4, 'FEMALE', 21),
(35, 'ross', 'green', 'cow', 5, 'MALE', 21),
(36, 'john', 'black', 'parrot', 6, 'FEMALE', 21),
(37, 'john', 'rose-gold', 'sheep', 7, 'MALE', 21);

SET FOREIGN_KEY_CHECKS = 1;

