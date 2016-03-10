<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<html >
<head>
<meta charset="ISO-8859-1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha256-MfvZlkHCEqatNoGiOXveE8FIwMzZg4W85qfrfIFBfYc= sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
  crossorigin="anonymous">
<link href="/static/css/mystyle.css" rel="stylesheet">
<link href="/static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.7/angular.js"></script>

<title><@spring.message "application.title"/></title>
</head>
<body class="container-fluid" ng-controller='view-project' ng-app="project-list" >
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
        <a class="active" href="#">Project of ThangPham</a>
     </div>
      <div class="col-md-1 content">
        <p>
          <a href="#"><@spring.message "text.logout"/></a>
        </p>
      </div>
    </div>
  </div>
  <div class="divider"></div>
  <div class="container" >
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
            <h4 class="semibold"><@spring.message "text.project.list"/></h4>
            <div class="divider"></div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="row">
              <form class="form-inline" ng-init="query='${query}';status='${status}'" >
                <div class="col-md-5">
                  <input type="text" class="fullwidth form-control" id="exampleInputName2" autofocus ng-model="query"
                    placeholder="<@spring.message "search.placeholder"/>" >
                    
                </div>
                <div class="col-md-3">
                  <select id="status" class="fullwidth form-control" ng-model="status">
                    <option value="" disabled selected><@spring.message "search.status"/></option>
                    <option value="NEW"><@spring.message "text.status.new"/></option>
                    <option value="PLA"><@spring.message "text.status.planed"/></option>
                    <option value="INP"><@spring.message "text.status.inprogress"/></option>
                    <option value="FIN"><@spring.message "text.status.finished"/></option>
                  </select>
                </div>
                <div class="col-md-4">
                  <button id="project-search"  type="submit" class="btn btn-primary" ng-click="search()"><@spring.message "search.project"/></button>
                  <a href="#" id="reset-search" class="active" ng-click="reset()"><@spring.message "search.reset"/></a>
                </div>
              </form>
            </div>
          </div>
          <div id="project-list" class="col-md-12" >
          <!-- <div class="panel panel-primary"><div class="panel-body"><strong>No projects available</strong></div></div> -->
          <div  ng-show="isEmpty()" class="alert alert-info" role="alert"><strong>No projects available</strong></div>
           <div  ng-hide="isEmpty()">
             <table class="table table-bordered"  >
                  <tr>
                    <th></th>
                    <th><@spring.message "table.project.number"/></th>
                    <th><@spring.message "label.project.name"/></th>
                    <th><@spring.message "label.project.status"/></th>
                    <th><@spring.message "label.project.customer"/></th>
                    <th><@spring.message "label.project.start.date"/></th>
                    <th><@spring.message "label.project.delete"/></th>
                  </tr>
                    <tr ng-repeat="project in projects">
                      <td><input type="checkbox" ng-model="project.checked" ng-disabled="!isNew(project)" ></td>
                      <td><a href="/detail/{{project.number}}" > {{project.number}} </a></td>
                      <td>{{project.name}}</td>
                      <td>{{project.status}}</td>
                      <td>{{project.customer}}</td>
                      <td>{{project.startDate | date : 'dd.MM.yyyy'}}</td>
                      <td><a href="#" ng-click="remove(project)"><i class="glyphicon glyphicon-trash" ng-show="isNew(project)"></i></a></td>
                    </tr>
                    <tr id="project-control">
                      <td colspan="7">
                        <div class="col-md-6 align-left"> <font color="#2F85FA" > {{(projects | filter:{checked: true }).length}} <@spring.message "label.item.selected"/> </font> </div>
                        <div class="col-md-6 align-right">
                          <a href="#" ng-click="remove()"><font style="text-align: right" color="#DC143C" ><@spring.message "label.delete.selected.items"/> <i class="glyphicon glyphicon-trash"></i></font></a>
                        </div>
                      </td>
                    </tr>
                  </table>
                  
                  <div class="col-md-12">
                  <nav id="project-paging">
                    <ul class="pagination">
                      <li class="disabled"><a href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                      </a></li>
                      <li class="active"><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li><a href="#">4</a></li>
                      <li><a href="#">5</a></li>
                      <li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                      </a></li>
                    </ul>
                  </nav>
                  </div>
            </div>
           </div>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="deleteModal">
    <div class="modal-dialog modal-md">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Delete Comfirmation</h4>
        </div>
        <div class="modal-body">
          <p>Do you want to permanent delete this Project?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" id="delete-accepted">Delete</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
  integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
  crossorigin="anonymous"></script>
<script src="/static/js/project-list.js"></script>
</html>