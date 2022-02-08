# CALCULADORA SANITAS

###### EJECUCION ######
Para ejecutar la aplicación, hay que actualizar los links de los jars customs que se encuentran en la carpeta resources
El maven install no funciona, ya que no encuentra la librería externa del 'tracer'
Para ejecutar la aplicacion correctamente basta con, reimportar los jars con la nueva url (están en la carpeta resources dentro del codigo) y ejecutar
CalculadoraSanApplication.java como SpringbootApplication

###### PRUEBAS POSTMAN ######
La única URL del micro sería '/sanitas-calculadora/api/v1/operar' junto con el localhost que levante.

El body que hay que pasarle es el siguiente:
{
    "tipo":"suma",
    "numeros":["10","5","2"]
}

###### EXPLICACION BODY ######
El "tipo" puede ser 'suma', 'resta', 'multiplicacion' o 'division', si se sale de estos, dará un mensaje de error (con un 400 Bad Request)

Los "numeros", se pueden enviar cuantos se quieran, están en Double, por lo que tambien se pueden enviar decimales. En caso de que solo se envíe un numero
dará un mensaje error notificandolo (junto con un 400 Bad Request) 

###### NOTAS ######
El programa realiza correctamente las operaciones de suma y multiplicacion (para ver mejor la sintaxis preferí utilizar las 4 operaciones de aritmetica)
como el metodo que utilizo es el stream().reduce() la resta y a division tienen funcionamientos erraticos, pero lo envío así, para que veáis el codigo.