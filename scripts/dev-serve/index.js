// @TODO: This should be a simple server-static
// https://github.com/expressjs/serve-static

const express = require('express');
const path = require("path");
const app = express();

[
  '../../core/target/scala-2.12/scalajs-bundler/main',
  '../../../scalajs-code-splitting/o/o',
].forEach(target => {
  app.use(express.static(path.join(__dirname, target)));
})

app.get('/*', function (req, res) {
    res.sendFile(path.join(__dirname, './index.html'));
});

app.listen(3000, () => console.log('Port 3000!'));
