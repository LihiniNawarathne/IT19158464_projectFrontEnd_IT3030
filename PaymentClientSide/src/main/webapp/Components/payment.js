$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
	
	/*if($("#userID1").text().trim() == ""){
		$("#divItemsGrid").hide();
	}*/
});


	
	
//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
		
	// Form validation-------------------
	var status = validatePaymentForm();
	if (status != true){
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
		
	// If valid------------------------
	var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		url : "paymentAPI",
		type : type,
		data : $("#formCon").serialize(),
		dataType : "text",
		complete : function(response, status){
			PaymentSaveComplete(response.responseText, status);
		}
		});
});

function PaymentDetailsComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#hidpaymentIDSave").val("");
		$("#formCon")[0].reset();
}


function PaymentSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#hidpaymentIDSave").val("");
		$("#formCon")[0].reset();
}



//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidpaymentIDSave").val($(this).data("userid"));
	$("#userID").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#billID").val($(this).closest("tr").find('td:eq(2)').text()); 
 	$("#paid_amount").val($(this).closest("tr").find('td:eq(4)').text()); 
 	$("#payment_type").val($(this).closest("tr").find('td:eq(7)').text());
 	$("#card_no").val($(this).closest("tr").find('td:eq(8)').text()); 


});


//DELETE=================================================
$(document).on("click", ".btnRemove", function(event)
		{
			$.ajax(
			{
				url : "paymentAPI",
				type : "DELETE",
				data : "billID=" + $(this).data("userid"),
				dataType : "text",
				complete : function(response, status)
				{
					onpaymentDeleteComplete(response.responseText, status);
				}
			});
});




function onpaymentDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
	}
}


//========== VALIDATION ================================================
function validatePaymentForm()
{
	
		if ($("#userID").val().trim() == "")
		{
			return "Insert User ID";
		}
		
		
		if ($("#billID").val().trim() == "")
		{
			return "Insert Bill ID.";
		}
		
		
		if ($("#paid_amount").val().trim() == "")
		{
			return "Insert Paid Amount.";
		}
	
		if ($("#payment_type").val().trim() == "")
		{
			return "Insert Payment Type.";
		}
		
		if ($("#card_no").val().trim() == "")
		{
			return "Insert Card Number.";
		}
		
		// is numerical value
		var amount = $("#paid_amount").val().trim();
		if (!$.isNumeric(amount))
		{
			return "Please enter a valid amount.";
		}
		
		var billid = $("#billID").val().trim();
		if (!$.isNumeric(billid))
		{
			return "Please enter a valid Bill ID.";
		}
		
		return true;
}
