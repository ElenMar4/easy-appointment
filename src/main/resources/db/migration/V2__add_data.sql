insert into users (username, password)
values ('easy_appointment_admin@example.com', '$2y$10$mtrnJeGs6n9WCDDSIG.UuOQFzzq5aIoVkIINMFco/3xO300y5AzD.'),
        ('e1@example.com', '$2y$10$FQ0sNoPzHBy9b16BVDCjx.NvcbvwVXvWsKZMhc6Ac8FDleDQ.TMGy'),
        ('e2@example.com', '$2y$10$FQ0sNoPzHBy9b16BVDCjx.NvcbvwVXvWsKZMhc6Ac8FDleDQ.TMGy'),
        ('e3@example.com', '$2y$10$FQ0sNoPzHBy9b16BVDCjx.NvcbvwVXvWsKZMhc6Ac8FDleDQ.TMGy'),
        ('c1@example.com', '$2y$10$aD9oZQ2LOPQzZCveXlHsMOHV4Jtp7BNOFPkwX2jIKY/UcIZez65kW'),
        ('c2@example.com', '$2y$10$aD9oZQ2LOPQzZCveXlHsMOHV4Jtp7BNOFPkwX2jIKY/UcIZez65kW'),
        ('c3@example.com', '$2y$10$aD9oZQ2LOPQzZCveXlHsMOHV4Jtp7BNOFPkwX2jIKY/UcIZez65kW'),
        ('c4@example.com', '$2y$10$aD9oZQ2LOPQzZCveXlHsMOHV4Jtp7BNOFPkwX2jIKY/UcIZez65kW');

insert into roles (user_id, role)
values (1, 'ADMIN'),
        (2, 'ENTREPRENEUR'), (3, 'ENTREPRENEUR'), (4, 'ENTREPRENEUR'),
        (5, 'CUSTOMER'), (6, 'CUSTOMER'), (7, 'CUSTOMER'), (8, 'CUSTOMER');

insert into entrepreneurs (name, phone, tax_number, user_id)
values ('Пушистые прогулки', '+71112223344', '123456789123', 2),
        ('Кучерявая голова', '+72223334455', '456789123456', 3),
        ('Фотографиня', '+73334445566', '789123456789', 4);


insert into customers (name, phone, user_id)
values ('Лев Толстой', '+79996663322', 5),
        ('Александр Пушкин', '+76663332211', 6),
        ('Марина Цветаева', '+73332221144', 7),
        ('Анна Ахматова', '+72221114477', 8);



