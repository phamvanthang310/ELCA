/**
 * 
 */

$(function() {
	// datetime picker
	$('#startdate').datetimepicker({
		useCurrent : false,
		format : 'DD.MM.YYYY'
	});
	$('#enddate').datetimepicker({
		useCurrent : false,
		format : 'DD.MM.YYYY'

	});
	$("#startdate").on("dp.change", function(e) {
		$('#enddate').data("DateTimePicker").minDate(e.date);
	});
	$("#enddate").on("dp.change", function(e) {
		$('#startdate').data("DateTimePicker").maxDate(e.date);
	});

	// validate
	// $('#create-project').click(function() {
	// validate();
	// });
	
	$(".has-error:first input").focus();

	$('#project-form').submit(function(event) {
		if (validate()) {
			$('.alert.alert-danger').hide();
		} else {
			$('.alert.alert-danger').show();
			event.preventDefault();
		}

	});

	function validate() {
		var temp = true;
		if (isError('#projectNumber')) {
			temp = false;
		}
		if (isError('#projectName')) {
			temp = false;
		}
		if (isError('#customer')) {
			temp = false;
		}
		if (isError('#status')) {
			temp = false;
		}
		if (isError('#productStartDate')) {
			temp = false;
		}
		$(".has-error:first input").focus();
		return temp;
	}

	function isError(id) {
		if ($(id).val() === '') {
			$(id).parent().addClass('has-error');
			return true;
		} else {
			$(id).parent().removeClass('has-error');
		}
		return false;
	}
});