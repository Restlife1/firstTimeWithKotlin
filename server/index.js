var express = require("express");  //esto es lo mismo que el import en java, importar una librería
var app = express();
var bodyParser = require("body-parser");
app.use(bodyParser.urlencoded({extended: true}));   //decodificar el estado que creé en el body hay que poner bodyParser a true

const showHelloMessage = (req, res) => {    // => es arrow function

    console.log(req.body);
    res.send({
        message: 'Hola buenas tardes',
        author: 'Pepeitor'
    });

};

var cors = require("cors");

app.use(cors()); //cors es un mecanismo que permite que se puedan solicitar recursos restringidos en una página web desde un dominio fuera del dominio que sirvió el primer recurso.

const PossibleTaxisScreen = (req, res) => {     //hago el mensaje de inicio para la pantalla index

    console.log(req.body);
        res.send({
            taxi: 'Press to find'
        });

}

var arrayPosibleTaxis = [   //creo el array de taxis (datos) posibles
    {
        taxi: '120',
        time: '3 min'
    },
    {
        taxi: '077',
        time: '2 min'
    },
    {
        taxi: '202',
        time: '3 min'
    },
    {
        taxi: '003',
        time: '5 min'
    },
    {
        taxi: '111',
        time: '1 min'
    },
];

var nearTaxi = {

    taxi: '111',
    time: '1 min'

};

var showPosibleTaxis = (req,res) => {

    console.log(req.body)
    res.send(arrayPosibleTaxis);

};

var betterTaxi = (req,res) => {

    console.log(req.body)
    res.send(nearTaxi);

};

app.get("/near", betterTaxi);
app.get("/hello", showHelloMessage);
app.get("/index", PossibleTaxisScreen);
app.get("/find", showPosibleTaxis);
app.get("/goodbye", (req, res) => {

    res.send({
        something: 'byez'
    });

});

const port = 40000;

app.listen(port, () => {
    console.log("Running on http://localhost:" + port);     //arriba definí el puerto como final en el 40000 y aquí le digo donde se va a conectar el server
});