### **Práctica**

En un Sistema de Telefonía fija, dado el Caso de Uso de creación de una Factura correspondiente a un número y un período determinados, con la siguiente definición:

* La factura está compuesta por los siguientes ítems:
    * Un abono mensual básico.
    * La suma del costo del consumo por llamadas Locales.
    * La suma del costo del consumo por llamadas Nacionales más la suma del costo de las llamadas Internacionales.
* La entrada al software de facturación viene en una lista de strings con la siguiente información: **Número de Origen, Número de Destino, Fecha y Hora de inicio de la llamada y Duración.**
* Las llamadas locales tienen distintos valores según la franja horaria en la que se realizan y el día. Para los días hábiles, de 8 a 20 hrs. el costo es de 20 centavos el minuto, mientras en el resto de las horas es de 10 centavos el minuto. Los sábados y domingos cuesta 5 centavos el minuto.
* Las llamadas Internacionales tienen un costo distinto según el país al que se llame.
* Las llamadas Nacionales tienen un costo distinto según la localidad a la que se llame.

Se pide:
* Modelar la creación de la Factura con diagramas de clases, de instancias, pseudocódigo o lo que sea más cómodo para transmitir las idea del diseño.
* Escribir, en pseudocódigo, el/los algoritmos necesarios para calcular el costo de las llamadas según el punto A de la definición del caso de uso.
