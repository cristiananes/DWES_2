INSERT INTO supermercado (nombre) VALUES ('Supermercado Central');
INSERT INTO supermercado (nombre) VALUES ('Tienda del Barrio');
INSERT INTO supermercado (nombre) VALUES ('Hipermercado Global');


INSERT INTO producto (nombre) VALUES ('Leche');
INSERT INTO producto (nombre) VALUES ('Pan');
INSERT INTO producto (nombre) VALUES ('Huevos');
INSERT INTO producto (nombre) VALUES ('Café');

INSERT INTO vende (supermercado_id, producto_id, lote, cantidad, precio) VALUES
(1, 1, 'Lote-A123', 50, 10),
(1, 2, 'Lote-B456', 30, 20),
(2, 1, 'Lote-C789', 100, 30), 
(3, 1, 'Lote-D321', 25, 40),
(2, 2, 'Lote-E654', 75, 50); 
