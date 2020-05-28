insert into car_brand (name, removed) value ('Tesla', false);
insert into car_brand (name, removed) value ('BMW', false);
insert into car_brand (name, removed) value('Audi', false);
insert into car_brand (name, removed) value('Mercedes-Benz', false);
insert into car_brand (name, removed) value('Kia', false);
insert into car_brand (name, removed) value('Alfa Romeo', false);
insert into car_brand (name, removed) value('Chevrolet', false);
insert into car_brand (name, removed) value('Volkswagen', false);
insert into car_brand (name, removed) value('FIAT', false);
insert into car_brand (name, removed) value('Ford', false);
insert into car_brand (name, removed) value('Hyundai', false);
insert into car_brand (name, removed) value('Porsche', false);
insert into car_brand (name, removed) value ('Toyota', false);

insert into car_model (name, car_brand_id, removed) value ('Model S', 1, false);
insert into car_model (name, car_brand_id, removed) value ('Model X', 1, false);
insert into car_model (name, car_brand_id, removed) value ('Model Y', 1, false);
insert into car_model (name, car_brand_id, removed) value ('Model 3', 1, false);
insert into car_model (name, car_brand_id, removed) value ('Cybertruck', 1, false);

insert into car_model (name, car_brand_id, removed) value ('x5', 2, false);
insert into car_model (name, car_brand_id, removed) value ('i3', 2, false);
insert into car_model (name, car_brand_id, removed) value ('i8', 2, false);
insert into car_model (name, car_brand_id, removed) value ('M5', 2, false);
insert into car_model (name, car_brand_id, removed) value ('X1', 2, false);

insert into car_model (name, car_brand_id, removed) value('A8', 3, false);
insert into car_model (name, car_brand_id, removed) value ('A3', 3, false);
insert into car_model (name, car_brand_id, removed) value ('A4', 3, false);
insert into car_model (name, car_brand_id, removed) value ('A5', 3, false);
insert into car_model (name, car_brand_id, removed) value ('A6', 3, false);

insert into car_model (name, car_brand_id, removed) value('SLR McLaren', 4, false);
insert into car_model (name, car_brand_id, removed) value ('A-Class', 4, false);
insert into car_model (name, car_brand_id, removed) value ('AMG GT', 4, false);
insert into car_model (name, car_brand_id, removed) value ('C-Class', 4, false);
insert into car_model (name, car_brand_id, removed) value ('CLS', 4, false);

insert into car_model (name, car_brand_id, removed) value ('Rio', 5, false);
insert into car_model (name, car_brand_id, removed) value ('Sportage', 5, false);
insert into car_model (name, car_brand_id, removed) value ('Stinger', 5, false);
insert into car_model (name, car_brand_id, removed) value ('Sedona', 5, false);

insert into car_model (name, car_brand_id, removed) value ('4C', 6, false);
insert into car_model (name, car_brand_id, removed) value ('Giulia', 6, false);
insert into car_model (name, car_brand_id, removed) value ('Stelvio', 6, false);
insert into car_model (name, car_brand_id, removed) value ('Mito', 6, false);

insert into car_model (name, car_brand_id, removed) value ('Colorado', 7, false);
insert into car_model (name, car_brand_id, removed) value ('Camaro', 7, false);
insert into car_model (name, car_brand_id, removed) value ('Malibu', 7, false);
insert into car_model (name, car_brand_id, removed) value ('Cruze', 7, false);

insert into car_model (name, car_brand_id, removed) value ('Passat', 8, false);
insert into car_model (name, car_brand_id, removed) value ('Jetta', 8, false);
insert into car_model (name, car_brand_id, removed) value ('Tiguan', 8, false);
insert into car_model (name, car_brand_id, removed) value ('Golf', 8, false);

insert into car_model (name, car_brand_id, removed) value ('500X', 9, false);
insert into car_model (name, car_brand_id, removed) value ('500L', 9, false);
insert into car_model (name, car_brand_id, removed) value ('500e', 9, false);
insert into car_model (name, car_brand_id, removed) value ('500c', 9, false);

insert into car_model (name, car_brand_id, removed) value ('Escape', 10, false);
insert into car_model (name, car_brand_id, removed) value ('Fiesta', 10, false);
insert into car_model (name, car_brand_id, removed) value ('Taurus', 10, false);
insert into car_model (name, car_brand_id, removed) value ('Flex', 10, false);

insert into car_model (name, car_brand_id, removed) value ('Kona', 11, false);
insert into car_model (name, car_brand_id, removed) value ('Nexo', 11, false);
insert into car_model (name, car_brand_id, removed) value ('Sonata', 11, false);
insert into car_model (name, car_brand_id, removed) value ('Venue', 11, false);

insert into car_model (name, car_brand_id, removed) value ('Cayman', 12, false);
insert into car_model (name, car_brand_id, removed) value ('Macan', 12, false);
insert into car_model (name, car_brand_id, removed) value ('Panamera', 12, false);
insert into car_model (name, car_brand_id, removed) value ('Boxster', 12, false);

insert into car_model (name, car_brand_id, removed) value ('Prius', 13, false);
insert into car_model (name, car_brand_id, removed) value ('Sienna', 13, false);
insert into car_model (name, car_brand_id, removed) value ('Yaris', 13, false);
insert into car_model (name, car_brand_id, removed) value ('Corolla', 13, false);

insert into car_class (name, removed) value ('SUV', false);
insert into car_class (name, removed) value ('Old timer', false);
insert into car_class (name, removed) value ('Saloon', false);
insert into car_class (name, removed) value ('Station vagon', false);
insert into car_class (name, removed) value ('Sport car', false);
insert into car_class (name, removed) value ('VAN', false);
insert into car_class (name, removed) value ('Coupe', false);

insert into transmission_type (name, removed) value ('Manuel', false);
insert into transmission_type (name, removed) value ('Automatic', false);
insert into transmission_type (name, removed) value ('Semi automatic', false);

insert into fuel_type (name, removed) value ('Petrol', false);
insert into fuel_type (name, removed) value ('Gas', false);
insert into fuel_type (name, removed) value ('Diesel', false);
insert into fuel_type (name, removed) value ('Hybrid', false);
insert into fuel_type (name, removed) value ('Electric', false);

insert into car (children_seats, rate ,mileage, car_model_id, car_class_id, fuel_type_id, transmission_type_id, has_android_app) value (1, 3.5, 5000, 1,1,1,2, true);
insert into car (children_seats, rate ,mileage, car_model_id, car_class_id, fuel_type_id, transmission_type_id, has_android_app) value (0, 3.8, 2000, 2, 2,1,3, false);
insert into car (children_seats, rate ,mileage, car_model_id, car_class_id, fuel_type_id, transmission_type_id, has_android_app) value (2, 4, 10000, 3, 5,2,2, true);
insert into car (car_class_id, car_model_id, transmission_type_id, fuel_type_id, children_seats, rate, mileage, has_android_app) value (1, 1, 3, 2, 1, 3.5, 5000, true);

