
INSERT INTO astronauta (nombre, pais, edad) 
VALUES 
('Neil Armstrong', 'Estados Unidos', 39),
('Buzz Aldrin', 'Estados Unidos', 39),
('Yuri Gagarin', 'Unión Soviética', 27),
('Valentina Tereshkova', 'Unión Soviética', 26),
('Chris Hadfield', 'Canadá', 54),
('Sally Ride', 'Estados Unidos', 32),
('Pedro Duque', 'España', 35),
('Rakesh Sharma', 'India', 35),
('Luca Parmitano', 'Italia', 43),
('Mae Jemison', 'Estados Unidos', 36);

INSERT INTO nave (nombre, capacidad) 
VALUES 
  ('Enterprise', 1000),  
  ('Millennium Falcon', 6),  
  ('Discovery One', 5),    
  ('Nostromo', 7),          
  ('Rocinante', 4),           
  ('Serenity', 5),            
  ('Galactica', 50000),       
  ('Event Horizon', 26);      

  
  INSERT INTO mision (nombre, fecha_inicio, fecha_fin,id_nave) 
VALUES 
  ('Apollo 11', '1969-07-16', '1969-07-24',1),     
  ('Voyager 1', '1977-09-05', '2030-01-01',1),      
  ('Sputnik 1', '1957-10-04', '1958-01-04',1),      
  ('Challenger STS-51-L', '1986-01-28', '1986-01-28',1), 
  ('Hubble Space Telescope', '1990-04-24', '2030-12-31',null), 
  ('Curiosity', '2011-11-26', '2035-01-01',null),      
  ('Mars Pathfinder', '1996-12-04', '1997-09-27',null), 
  ('New Horizons', '2006-01-19', '2030-12-31',null),    
  ('International Space Station (ISS)', '1998-11-20', '2035-12-31',null), 
  ('James Webb Space Telescope', '2021-12-25', '2045-12-31',null); 
  
  

INSERT INTO participa (astronauta_id, mision_id, rol, descripcion_tarea)
VALUES 
(1, 1, 'Comandante', 'Dirigió el módulo lunar y realizó el primer paseo en la Luna'),
(2, 1, 'Piloto', 'Ayudó a operar el módulo lunar y realizó experimentos en la superficie lunar'),
(1, 2, 'Asesor', 'Proporcionó recomendaciones para mejorar la misión');
  
 