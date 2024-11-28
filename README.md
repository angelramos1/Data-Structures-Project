# Data-Structures-Project

Integrantes del grupo: Angel L. Ramos(841-21-5463)(angel.ramos48@upr.edu), Abimael Torres(444-168-964)(abimael.torres@upr.edu), Karina Ojeda(802-215-177)(karina.ojeda1@upr.edu)

Sistema de Reservación de Asientos para un Estadio

Este es un programa hecho para manejar las reservaciones de asientos en un estadio de béisbol. Sirve para:

Reservar asientos.
Cancelar reservaciones.
Ver la disponibilidad de asientos.
Manejar una lista de espera para los clientes cuando no hay asientos disponibles.
Reasignar asientos automáticamente a los que están en la lista de espera si se liberan.

Reservar asientos:
Entras el nombre, email y teléfono del cliente.
Seleccionas la sección (Field, Main o Grandstand) y la cantidad de asientos.
Si hay espacio, se reservan. Si no, el cliente se pone en la lista de espera.

Cancelar reservaciones:
Buscas al cliente por nombre y cancelas su reservación.
Si hay alguien esperando por esa misma sección, los asientos liberados se asignan automáticamente.

Lista de espera:
Los clientes que no consiguen asientos se ponen en la lista de espera y se les asignan asientos cuando estén disponibles.

Ver asientos disponibles:
Muestra cuántos asientos quedan en cada sección del estadio.

Secciones y precios
Field Level: $300 por asiento.
Main Level: $120 por asiento.
Grandstand Level: $45 por asiento.

Clases
Asiento: Representa cada asiento en el estadio.
Cliente: Guarda los datos del cliente.
Estadio: Se encarga de las reservaciones, cancelaciones, lista de espera y reasignaciones.
Action: Guarda las acciones realizadas para poder deshacerlas si es necesario.
Main: El menú principal donde el operador interactúa con el programa.