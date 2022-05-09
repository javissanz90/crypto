# crypto
Aplicación para guardar, eliminar y listar criptomonedas.
La aplicación consta de 3 end points que se consumiran de la siguiente forma: 

- Añadir cryto: 
URL: localhost:8080/crypto

Para poder ejecutor el end point es necesario incorporar un JSON con los siguientes atributos:


{
 "name": -> nombre de la criptomoneda, 
 "acronym": -> acrónimo de la criptomoneda, 
 "value": -> valor, 
 "enabled": -> inidica si la criptooneda está activa 
 }
 
 Ejemplo: {"name": "Ethereum", "acronym": "ETH", "value": 2, "enabled": "true" }


- Listar crypto: 
URL: localhost:8080/crypto/{acronym} -> Acrónimo de la criptomoneda a listar.


- Elimiar crypto
URL: localhost:8080/crypto/{acronym} -> Acrónimo de la criptomoneda a eliminar.
