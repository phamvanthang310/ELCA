/**
 * 
 */

var app = angular.module('project-list', []);

app.controller('view-project',['$scope','$http', function($scope, $http) {
	
	$scope.selected = [];
	
	$scope.search = function() {
		$http.get("/search?query=" + $scope.query + "&status="+ $scope.status).success(function(data) {
			$scope.projects = data;
		});
	};

	$scope.isNew = function(project) {
		return project.status === 'NEW';
	};

	$scope.reset = function() {
		$scope.query = '';
		$scope.status='';
		$http.get("/search?query=&status=").success(function(data) {
			$scope.projects = data;
		});
	};
	
	$scope.remove = function(project) {
		if (project === undefined) {
			var isProjectChecked = false;
			var size =  $scope.projects.length;
			for (var int = 0; int < size; int++) {
				var project = $scope.projects[int];
				if (project.checked === true) {
					isProjectChecked = true;
				}
			}
			if(!isProjectChecked){
				window.alert("You have to choose at least 1 project to delete.")
			}else{
				$('#deleteModal').modal('show');
				$('#delete-accepted').click(function(ev) {
					var size =  $scope.projects.length;
					for (var int = 0; int < size; int++) {
						var project = $scope.projects[int];
						if (project.checked === true) {
							isProjectChecked = true;
//							var idx = $scope.projects.indexOf(project);
//							$scope.projects.splice(idx, 1);
							$http.get("/delete?id=" + project.number);
							$http.get("/search?query=" + $scope.query + "&status="+ $scope.status).success(function(data) {
								$scope.projects = data;
							});
						}
					}
					$('#deleteModal').modal('hide');
				});
			}
		} else {
			$('#deleteModal').modal('show');
			$('#delete-accepted').click(function(ev) {
				var idx = $scope.projects.indexOf(project);
				$scope.projects.splice(idx, 1);
				$http.get("/delete?id=" + project.number);
				$('#deleteModal').modal('hide');
			});
		}
	};
	
	$scope.isEmpty = function(){
		return ($scope.projects.length === 0);
	};
	
}]);

$(document).ready(function() {
    $(window).load(function() {
        $('#project-search').click();
    });
});
