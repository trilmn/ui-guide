// @TODO: This should be a simple server-static
// https://github.com/expressjs/serve-static

const express = require('express');
const path = require("path");
const app = express();

const resources = '../../core/src/main/resources';
const target = '../../core/target/scala-2.12';

app.use(express.static(path.join(__dirname, resources)));
app.use(express.static(path.join(__dirname, target)));

app.get('/*', function (req, res) {
   res.sendFile(path.join(__dirname, resources, 'index-fastopt.html'));
 });

app.listen(3000, () => console.log('Port 3000!'));