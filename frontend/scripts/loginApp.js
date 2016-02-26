$(document).ready(function () {
  var xauthtoken = "";
  var tulo = null;
  $("#messagesForErrors").hide();
  $("#kysymysPeli").hide();

  var ircClient = (function () {
    console.log("This is an irc client");
  });
  var alreadySubmitted = false;
  $("#submitbutton").click(function (event) {
    event.preventDefault();
    var val = $('#vastaus').val();
    if (val == "") {

    } else {
      if (tulo != null) {
        if ($("#vastaus").val() == tulo) {
          $('#comment').append("Great job! It is indeed " + tulo + "\n");
          alreadySubmitted = false;
          getNewQuestion();
        } else  if (alreadySubmitted == false) {
          $("#comment").append("Err, your wrong!\n");
          alreadySubmitted= true;
        }
      }

    }
  });
  $("#loginButton").click(function (event) {
    event.preventDefault();
    var username = $("#username").val();
    var password = $("#password").val();

    var jsonString = {
      "username": username,
      "password": password
    };
    $('comment').autoresize({animate: true});
    $.ajax({
      //Template ajax-query to connect JQuery with Spring using
      //JSON
      contentType: "application/json",
      url: "https://localhost:8443/login",
      data: JSON.stringify(jsonString),
      type: "POST",
      dataType: "html",
      success: function asdf(data) {
        var json = JSON.parse(data);
        xauthtoken = json.x_AUTH_HEADER;
        if (json.loginStatus == true) {
          $("#loginForm").fadeOut('slow', function () {
            $("#kysymysPeli").show();
            getNewQuestion();
          });
        } else {
          $("#messagesForErrors").show();
        }
        console.log("Its working");
      },
      error: function (xhr, textStatus, error) {
        console.log(xhr.statusText);
        console.log(textStatus);
        console.log(error);
      }
    });
  });
  function getNewQuestion() {
    $.ajax({
      //Template ajax-query to connect JQuery with Spring using
      //JSON
      contentType: "application/json",
      url: "https://localhost:8443/quizapp",
      type: "GET",
      dataType: "html",
      beforeSend: function (xhr) {
        xhr.setRequestHeader('X-Auth-Header', xauthtoken);
      },
      success: function asdf(data) {
        var json = JSON.parse(data);
        $('#comment').append("What is the product of number: "
        + json.number1 + " and number: " + json.number2 + "?" + "\n").slideDown();
        tulo = json.tulo;
        console.log("Its working");
      },
      error: function (xhr, textStatus, error) {
        console.log(xhr.statusText);
        console.log(textStatus);
        console.log(error);
      }
    });
  }
});