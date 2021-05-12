create or replace PROCEDURE OBTENER_PERSONAS_POR_NACIONALIDAD_ARCHIVO 
(
  NAC IN VARCHAR2,          -- nacionalidad
  FILE_DIR IN VARCHAR2,     -- directorio en el que guardar los datos
  FILE_NAME IN VARCHAR2     -- nombre del archivo en el que guardar los datos
)
AS
  fHandle UTL_FILE.FILE_TYPE;
BEGIN

  -- especificamos el directorio en sql
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY PERSONASNACIONALIDADDIRECTORIO AS ''' || FILE_DIR || '''';

  -- abrimos el archivo
  fHandle := UTL_FILE.FOPEN('PERSONASNACIONALIDADDIRECTORIO', FILE_NAME, 'w');

  -- buscamos las personas con determinada nacionalidad
  FOR PERSONA IN (SELECT * FROM registro WHERE registro.nacionalidad = NAC)
  LOOP
    -- ponemos los datos en el archivo
  	UTL_FILE.PUT_LINE(fHandle, PERSONA.NOMBRE || ' : ' || PERSONA.NACIONALIDAD || ' : ' || PERSONA.EDAD);
  END LOOP;

  -- cerramos el archivo
  UTL_FILE.FCLOSE(fHandle);
      
END OBTENER_PERSONAS_POR_NACIONALIDAD_ARCHIVO;