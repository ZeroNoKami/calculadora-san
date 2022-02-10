# CALCULADORA SANITAS

#### EJECUCION ####
Para ejecutar la aplicacion hay que lanzar 'spring-boot:run' o bien, realizar un mvn package e ir a
'\calculadora-san\target' y estará el jar 'calculadora-san-0.0.1-SNAPSHOT.jar'

#### PRUEBAS POSTMAN ####
La única URL del micro es:

###### URL: /sanitas-calculadora/api/v1/operar

El body que hay que pasarle es el siguiente:
###### Numero1: Con el primer numero
###### Numero2: Con el segundo numero
###### Operacion: con el tipo de operacion

#### EXPLICACION BODY ####
El "tipo" puede ser 'suma', 'resta', 'multiplicacion' o 'division', si se sale de estos, dará un mensaje de error (con un 400 Bad Request)

Los "numeros", se pueden enviar cuantos se quieran, están en Double, por lo que tambien se pueden enviar decimales. En caso de que solo se envíe un numero
dará un mensaje error notificandolo (junto con un 400 Bad Request)