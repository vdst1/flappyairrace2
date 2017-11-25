var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);

server.listen(8080, function(){
              console.log("Server está a decorrer...")
              });
io.on('connection', function(socket){
    console.log("Jogador ligou-se");
    socket.on('disconnect', function(){
        console.log("Jogador desligou-se");
    });
});