CREATE OR REPLACE PROCEDURE OBTENER_PERSONAS_POR_NACIONALIDAD_SQLOUT2 
(
  NAC IN VARCHAR2 -- nacionalidad
) AS
RES SYS_REFCURSOR;
BEGIN
  OPEN RES FOR
  SELECT *
  FROM registro
  WHERE registro.nacionalidad = NAC;

  DBMS_SQL.RETURN_RESULT(RES);

-- Ejemplo llamada:
--		exec OBTENER_PERSONAS_POR_NACIONALIDAD_SQLOUT2('ar');

END OBTENER_PERSONAS_POR_NACIONALIDAD_SQLOUT2;