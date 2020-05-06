$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateHospitalForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "HospitalsAPI",
		type : type,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onHospitalSaveComplete(response.responseText, status);
		}
	});
});

//UPDATE==========================================
function onHospitalSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "HospitalsAPI",
		type : "DELETE",
		data : "hospital_id=" + $(this).data("hospital_id"),
		dataType : "text",
		complete : function(response, status) {
			onHospitalDeleteComplete(response.responseText, status);
		}
	});
});

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "HospitalsAPI",
		type : "DELETE",
		data : "hospital_id=" + $(this).data("hospitalid"),
		dataType : "text",
		complete : function(response, status) {
			onHospitalDeleteComplete(response.responseText, status);
		}
	});
});


function onHospitalDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divUsersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting hospital..");
		$("#alertError").show();
	}
}

//UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidHospitalIDSave").val(
					$(this).closest("tr").find('#hidHospitalIDUpdate').val());
			$("#hospital_name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#hospital_address_no").val($(this).closest("tr").find('td:eq(1)').text());
			$("#hospital_address_lane1").val($(this).closest("tr").find('td:eq(2)').text());
			$("#hospital_address_lane2").val($(this).closest("tr").find('td:eq(3)').text());
			$("#hospital_address_lane3").val($(this).closest("tr").find('td:eq(4)').text());
			$("#hospital_city").val($(this).closest("tr").find('td:eq(5)').text());
			$("#tel").val($(this).closest("tr").find('td:eq(6)').text());
			$("#email").val($(this).closest("tr").find('td:eq(7)').text());
		});

// CLIENTMODEL=========================================================================
function validateHospitalForm() {
	
	// NAME
	if ($("#hospital_name").val().trim() == "") {
		return "Insert Hospital Name.";
	}
	// ADDRESS NO
	if ($("#hospital_address_no").val().trim() == "") {
		return "Insert Hospital Address No.";
	}
	// ADDRESS LINE 1
	if ($("#hospital_address_lane1").val().trim() == "") {
		return "Insert Address Lane 1.";
	}
	// ADDRESS LINE 2
	if ($("#hospital_address_lane2").val().trim() == "") {
		return "Insert Address Lane 2.";
	}
	// ADDRESS LINE 3
	if ($("#hospital_address_lane3").val().trim() == "") {
		return "Insert Address Lane 3.";
	}
	// HOS CITY
	if ($("#hospital_city").val().trim() == "") {
		return "Insert Hospital City.";
	}
	// TELPHONE
	if ($("#tel").val().trim() == "") {
		return "Insert Hospital Telephone Number.";
	}
	// Email
	if ($("#tel").val().trim() == "") {
		return "Insert Hospital Email.";
	}
	return true;
}
