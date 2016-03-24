var request = require('request');

// timeout after 250 ms
describe('jasmine test suite', function () {
  it("api should respond correctly", function (done) {
    request("http://localhost:8080/login", function (error, response, body) {
      expect(error).toBeNull();
      done();
    });
  }, 250);
  it("api should responed correctly with json deny", function (done) {
    request("http://localhost:8080/login", function (error, response, body) {
      console.log(error);
    });
  })
});

