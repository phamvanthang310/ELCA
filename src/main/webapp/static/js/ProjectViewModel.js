function ProjectViewModel(query) {
	
	var self = this;

	self.prjName = ko.observable(query);

	self.projects = ko.observableArray([]);

	self.query = function() {
		$.ajax({
			method : "POST",
			url : "/query",
			data : {
				name : self.prjName
			}
		}).done(function(data) {
			self.projects(data);
		});
	}
}

$( document ).ready(function() {
	var query = $('#PreviousQuery').val();	
	ko.applyBindings(new ProjectViewModel(query));
	if(query != ""){
		$('#query').click();
	}
	
});