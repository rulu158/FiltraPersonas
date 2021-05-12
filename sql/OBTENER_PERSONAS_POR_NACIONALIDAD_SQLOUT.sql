CREATE OR REPLACE PROCEDURE OBTENER_PERSONAS_POR_NACIONALIDAD_SQLOUT 
(
  NAC IN VARCHAR2,      -- nacionalidad
  RES OUT SYS_REFCURSOR -- resultado del query (salida)
) AS
BEGIN
  OPEN RES FOR
  SELECT *
  FROM registro
  WHERE registro.nacionalidad = NAC;


-- Ejemplo llamada:
--		variable cur refcursor;
--		call OBTENER_PERSONAS_POR_NACIONALIDAD_SQLOUT('ar', :cur);
--		print cur;

END OBTENER_PERSONAS_POR_NACIONALIDAD_SQLOUT;