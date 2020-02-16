# Aplicación web PetCenter
## Grupo: 
1.	Lopez Schwegler, Alejandro Nahuel
2.	Seewald, Ana Laura
## Visión
Una pagina web que ayudará a seguir el crecimiento de tus mascotas, también permitirá visualizar veterinarios y sus guardias para emergencias. Se registrarán las mascotas indicando su raza, y en base a la información de las mismas se dará avisos con respecto a vacunas, problemas específicos de la raza, etc. 

## Lista de características
  1.	Registro de mascotas (grupo familiar)
  2.	Visualización de veterinarios
  3.	Visualización de guardias de veterinarios
  4.	Consejos respecto a las mascotas del usuario
  
## Análisis de Dominio
 
Aca hay un analisis de dominio
 
## Casos de Uso

__Registrar Mascota__

__Actores:__ 
Usuario

__Objetivo:__ 
Permite registrar una mascota a un grupo familiar.

__Precondición:__ 
El usuario debe haber seleccionado un grupo familiar previamente.

__Flujo Principal:__
1.	El actor solicita registrar una mascota.
2.	El sistema solicita los datos de la mascota: nombre, fecha de nacimiento, raza, foto de perfil, y si quiere indicar algún/os veterinario/s al que esa mascota suele concurrir.
3.	El actor ingresa los datos y solicita registrar.
4.	El sistema registra la mascota.
__Flujos alternativos__:
Si no ingresa los datos solicitados no puede finalizar el caso de uso.

__Generar Guardia__
__Actores:__ Veterinario
__Objetivo:__ Permitir crear una guardia en un día y horario especifico
__Flujo Principal:__
1.	El actor solicita crear una guardia
2.	El sistema le solicita que ingrese la fecha de la misma y la ciudad.
3.	El actor ingresa los datos y se genera una nueva guardia.
__Flujos alternativos:__
Si la ciudad que desea ingresar no se encuentra disponible se le informara al usuario.
Si no ingresa los datos solicitados no puede finalizar el caso de uso.

 
__Solicitar Emergencia__
__Actores__: Usuario
__Objetivo__: Permite solicitar una emergencia de una mascota a una guardia especifica.
__Precondición__: El usuario debe haber seleccionado una guardia previamente.
__Flujo Principal__:
1.	El actor solicita ingresar una emergencia.
2.	El sistema le solicita que ingrese un título, descripción y foto opcionales.
3.	El actor ingresa los datos y envía la emergencia.
4.	El sistema informa de que la emergencia se creó con éxito.
__Flujos alternativos__:
Si no ingresa los datos solicitados no puede finalizar el caso de uso.

__Buscar Consejos__
__Actores__: Usuario
__Objetivo__: Permite visualizar una lista de consejos referidos a una mascota.
__Flujo Principal:__
1.	El actor solicita visualizar consejos sobre una mascota.
2.	El sistema muestra todos los consejos referidos a esa mascota teniendo en cuenta la especie y raza de la misma.
3.	El actor selecciona el consejo que desea visualizar.
4.	El sistema le muestra toda la información referida.
__Flujos alternativos:__
-.
## Arquitectura
•	Lenguaje: Java.
•	SGBD: PostgreSQL.
•	Tecnologías relacionadas: GIT, NetBeans, Star UML, Power Architect.
