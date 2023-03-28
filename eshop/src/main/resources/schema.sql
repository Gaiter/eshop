DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS orders;

create table orders
(
    id           bigint not null
        primary key,
    order_status int    not null
);

create table item
(
    id       bigint       not null
        primary key,
    name     varchar(100) not null,
    price    mediumtext   not null,
    order_id bigint       not null,
    constraint item_orders_id_fk
        foreign key (order_id) references db.orders (id)
);

create table payment
(
    id        bigint    not null
        primary key,
    sum       bigint    not null,
    order_id  bigint    not null,
    date_time timestamp not null,
    constraint payments_orders_id_fk
        foreign key (order_id) references db.orders (id)
);

