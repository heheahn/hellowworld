var express = require('express');
var path = require('path');

var app = express();

// configure app

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "views"));

// user middleware

/*
define routes
app.get()
app.post()
app.del()

app.get("/api/user/:id", function(req, res) {
    var userId = req.params.id;
    // Loca user and return JSON.
});
*/

app.get('/', function (req, res) {
    // res.send("hello, express");
    res.render("index", {
        title: "App",
        items: [
            {id: 1, desc: 'foo'},
            {id: 2, desc: 'bar'},
            {id: 3, desc: 'baz'}
        ]
    });
});

app.listen(1337, function() {
    console.log("ready on port 1337");
});

