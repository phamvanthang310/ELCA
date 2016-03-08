<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha256-MfvZlkHCEqatNoGiOXveE8FIwMzZg4W85qfrfIFBfYc= sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
  crossorigin="anonymous">
<link href="/static/css/mystyle.css" rel="stylesheet">
<link href="/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<title><@spring.message "application.title"/></title>
</head>
<body class="container-fluid" >
  <div class="container">
    <div id="header" class="row">
      <div class="col-md-8">
        <div class="col-md-2">
         <a href="/"><img alt="ELCA Logo" src="/static/Images/logo_elca.png" /></a>
        </div>
        <div class="col-md-10">
          <h3><@spring.message "text.pim"/></h3>
        </div>
      </div>
      <div class="col-md-2 content">
        <p>
          <a class="active" href="#">EN</a> | <a class="" href="#">FR</a>
        </p>
      </div>
      <div class="col-md-1 content">
        <a class="active" href="#"><@spring.message "text.help"/></a>
      </div>
      <div class="col-md-1 content">
        <p>
          <a href="#"><@spring.message "text.logout"/></a>
        </p>
      </div>
    </div>
  </div>
  <div class="divider"></div>
  <div class="container error">
    <div class="row">
      <div class="col-md-2 col-md-offset-3">
        <img alt="error" src="/static/Images/error.png">
      </div>
      <div class="col-md-6">
        <p>
          <#if Error??>[${Error}]</#if></br> <font color="red"> <@spring.message "message.contact.admin"/></font>
        </p>
        <p>
           <a href="/"><font color="blue"><@spring.message "message.back.to.search"/></font></a>
        </p>
      </div>
    </div>
  </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
  integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
  crossorigin="anonymous"></script>
</html>