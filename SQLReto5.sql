--Primer Informe 
SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia
FROM Lider 
ORDER BY Ciudad_Residencia;

-- Segundo Informe
SELECT ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad 
FROM Proyecto 
WHERE Clasificacion = 'Casa Campestre' 
AND Ciudad IN ('Santa Marta', 'Cartagena', 'Barranquilla') 
ORDER By Ciudad;

-- Tercer Informe
SELECT c.ID_Compra, p.Constructora, p.Banco_Vinculado 
FROM Compra c 
JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto 
WHERE c.Proveedor = 'Homecenter' AND Ciudad = 'Salento';

-- 