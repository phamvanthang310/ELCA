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
<body class="container-fluid">
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
          <a class="active" href="?lang=en">EN</a> | <a class="" href="?lang=fr">FR</a>
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
  <div class="container">
    <div class="row">
      <div id="left-nav" class="col-md-2">
        <h4 class="semibold"><@spring.message "text.project.list"/></h4>
        <h4 class="active"><@spring.message "text.new"/></h4>
        <ul class="list-unstyled">
          <li><a href="/new"><@spring.message "text.project"/></a></li>
          <li><a href="#"><@spring.message "text.customer"/></a></li>
          <li><a href="#"><@spring.message "text.supplier"/></a></li>
        </ul>
      </div>
      <div id="main-container" class="col-md-10">
        <div class="row" id="sub-header">
          <div class="col-md-12">
            <h4 class="semibold"><@spring.message "text.edit.project"/></h4>
            <div class="divider"></div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="alert alert-danger lert-dismissible" role="alert" hidden="true" >
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <strong><@spring.message "message.required.field"/></strong>
            </div>
            <form id="project-form" class="form-horizontal" name="detailForm" novalidate action="/submit" method="POST">

              <div class="form-group">
                <label for="projectNumber" class="col-md-2 control-label"><@spring.message "label.project.number"/> <font>*</font></label>
                <div class="col-md-3">
                  <input id="projectNumber" type="text" class="form-control" name="number" <#if project??> value="${project.number?string["0"]}" readOnly="true" </#if>>
                </div>
                <#if projectError??>
                    <p><font color="#c7342e">${projectError}</font></p>
                </#if>
              </div>
              <div class="form-group">
                <label for="projectName" class="col-md-2 control-label"><@spring.message "label.project.name"/> <font>*</font></label>
                <div class="col-md-8">
                  <input id="projectName" autofocus type="text" class="form-control" name="name" <#if project??> value="${project.name}" </#if>>
                </div>
              </div>
              <div class="form-group">
                <label for="customer" class="col-md-2 control-label"><@spring.message "label.project.customer"/> <font>*</font></label>
                <div class="col-md-8">
                  <input id="customer" type="text" class="form-control" name="customer" <#if project??> value="${project.customer}" </#if>>
                </div>
              </div>
              <div class="form-group">
                <label for="group" class="col-md-2 control-label"><@spring.message "label.project.group"/> <font>*</font></label>
                <div class="col-md-3">
                  <select id="group" class="form-control" name="group.groupLeader.visa">
       .             <#list groups as group>
                      <option value="${group.groupLeader.visa}" <#if group.groupLeader.visa == project.group.groupLeader.visa> selected </#if>>${group.groupLeader.visa}</option>
                    </#list>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="members" class="col-md-2 control-label"><@spring.message "label.project.members"/></label>
                <div class="col-md-8 <#if visaError??> has-error </#if>">
                  <input id="members" type="text" class="form-control" name="member" <#if members??> value="${members}" </#if>>
                </div>
                <#if visaError??>
                  <div class="col-md-8 col-md-offset-2"> <p style="margin-left: 38px;"><font color="#c7342e">${visaError}</font></p></div>
                </#if>
              </div>
              <div class="form-group">
                <label for="status" class="col-md-2 control-label"><@spring.message "label.project.status"/> <font>*</font></label>
                <div class="col-md-3">
                  <select id="status" class="form-control" name="status">
                    <option value="NEW" <#if project.status == 'NEW'> selected </#if>><@spring.message "text.status.new"/></option>
                    <option value="PLA" <#if project.status == 'PLA'> selected </#if>><@spring.message "text.status.planed"/></option>
                    <option value="INP" <#if project.status == 'INP'> selected </#if>><@spring.message "text.status.inprogress"/></option>
                    <option value="FIN" <#if project.status == 'FIN'> selected </#if>><@spring.message "text.status.finished"/></option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label for="status" class="col-md-2 control-label"><@spring.message "label.project.start.date"/> <font>*</font></label>
                <div class="col-md-3">
                  <div class='input-group date' id='startdate'>
                    <input id='productStartDate' type='text' class="form-control" name="startDate" <#if project.startDate??>value="${project.startDate?string('dd.MM.yyyy')}"  </#if>/> <span class="input-group-addon"> <span
                      class="glyphicon glyphicon-calendar"></span>
                    </span>
                  </div>
                </div>
                <label for="status" class="col-md-2 control-label "><@spring.message "label.project.end.date"/> </label>
                <div class="col-md-3">
                  <div class='input-group date' id='enddate'>
                    <input type='text' class="form-control" name="finishingDate" id='productEndDate' <#if project.finishingDate??>value="${project.finishingDate?string('dd.MM.yyyy')}"  </#if>  /> <span class="input-group-addon" > <span
                      class="glyphicon glyphicon-calendar"></span>
                    </span>
                  </div>
                </div>
              </div>
              </br>
              <div class="col-md-12 divider"></div>
              </br>
              <div id="button-control" class="col-md-offset-2 col-md-8 ">
                <a href="/" class="btn btn-default"><@spring.message "button.cancel"/></a>
                <button id="create-project" class="btn btn-primary" type="submit"><@spring.message "button.save.change"/></button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
  integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
  crossorigin="anonymous"></script>
<script src="/static/js/moment.js"></script>
<script src="/static/js/transition.js"></script>
<script src="/static/js/collapse.js"></script>
<script src="/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/myscript.js"></script>

</html>