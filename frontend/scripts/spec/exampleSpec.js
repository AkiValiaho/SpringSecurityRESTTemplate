var request = require('request');

// timeout after 250 ms
describe('jasmine test suite', function () {
  it("api should respond correctly", function (done) {
    request("http://localhost:8080/login", function (error, response, body) {
      expect(error).toBeNull();
      done();
    });
  }, 250);
  it("this method should return a proper value if invoked", function (done) {
    request("http://localhost:3000/login", function (error, response, body) {
      console.log(error);
    })
  }, 379);
  it("should return failed login attempt", function (done) {
    request("http://localhost:8080/login", function (error, response, body) {
    })
  }, 250);
});

