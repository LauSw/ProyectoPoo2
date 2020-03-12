# Aplicación web PetCenter
## Grupo: 
1.	Lopez Schwegler, Alejandro Nahuel
2.	Seewald, Ana Laura
## Visión
Una pagina web que ayudará a seguir el crecimiento de tus mascotas, también permitirá visualizar veterinarios y sus guardias para emergencias. Se registrarán las mascotas indicando su raza y fecha de nacimiento, y en base a la información de las mismas se visualizarán consejos con respecto a vacunas, problemas específicos de la raza, etc. 

## Lista de características
  1.	Registro de mascotas (grupo familiar)
  2.	Visualización de veterinarios
  3.	Visualización de guardias de veterinarios
  4.	Consejos respecto a las mascotas del usuario
  
## Análisis de Dominio

![Image description](https://scontent.fcnq2-2.fna.fbcdn.net/v/t1.0-9/89313300_2648306678732423_5158492267819827200_o.jpg?_nc_cat=110&_nc_sid=e007fa&_nc_ohc=jaThJOdunbUAX_a2e3s&_nc_ht=scontent.fcnq2-2.fna&oh=234f1e0d9851d38c254d9d22c356d37a&oe=5E9580AD)
 
## Casos de Uso

__Registrar Mascota__

__Actores:__ 
Administrador

__Objetivo:__ 
Permite registrar una mascota a un grupo familiar.

__Precondición:__ 
El usuario debe haber seleccionado un grupo familiar previamente.

__Flujo Principal:__
1.	El actor solicita registrar una mascota.
2.	El sistema solicita los datos de la mascota: nombre, fecha de nacimiento, raza.
3.	El actor ingresa los datos y solicita registrar.
4.	El sistema registra la mascota.
__Flujos alternativos__:
Si no ingresa los datos solicitados no puede finalizar el caso de uso.

__Generar Guardia__
__Actores:__ Administrador
__Objetivo:__ Permitir crear una guardia en un día y horario especifico
__Flujo Principal:__
1.	El actor solicita crear una guardia
2.	El sistema le solicita que ingrese la fecha de la misma.
3.	El actor ingresa los datos y se genera una nueva guardia.
__Flujos alternativos:__
Si no ingresa los datos solicitados no puede finalizar el caso de uso.

__Buscar Consejos__
__Actores__: Administrador
__Objetivo__: Permite visualizar una lista de consejos referidos a una mascota.
__Flujo Principal:__
1.	El actor solicita visualizar consejos sobre una mascota.
2.	El sistema muestra todos los consejos referidos a esa mascota teniendo en cuenta la especie, raza y fecha de nacimiento de la misma.
3.	El actor selecciona el consejo que desea visualizar.
4.	El sistema le muestra toda la información referida.
__Flujos alternativos:__
-.
## Arquitectura
•	Lenguaje: Java.
•	SGBD: PostgreSQL.
•	Tecnologías relacionadas: GIT, NetBeans, Star UML, Power Architect.
• Framework: React JS
