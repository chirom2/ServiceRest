$(function() {

	var rootURL = "http://localhost:8080/mri.jersey/rest";

	var lastID = 0;
	var fullUpdate;

	function updateMessages() {
		var url;
		if(fullUpdate){
			fullUpdate = false;
			lastID = 0;
			$('#chatContent').html("");
		}
		$.ajax({
			type : 'GET',
			url : rootURL + "/messages/after/" + lastID,
			dataType : "json",
			success : function(data) {
				if (null != data) {
					if ($.isArray(data.message)) {
						console.log("append each message");
						$.each(data.message, function(key, message) {
							appendMessage(message);
						});
					} else {
						console.log("append one message");
						appendMessage(data.message);
					}

				}
				console.log("call update again in 1s");
				setTimeout(updateMessages, 1000);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('updateMessages error: ' + textStatus);
			}
		});
	}
	
	
	function appendMessage(message) {
		console.log(message);

		$('#chatContent').append("<p>").append(message.id).append(":").append(
				message.date).append(">").append(message.content)
				.append("</p>");

		lastID = parseInt(message.id) + 1;
	}

	function sendMessage(messageContent) {
		$.ajax({
			type : 'POST',
			url : rootURL + "/messages",
			contentType : 'application/json',
			data : JSON.stringify({
				"content" : messageContent
			}),
			dataType : "json",
			success : function(data) {
				console.log("create message " + data.id);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('sendMessage error: ' + textStatus);
			}
		});
	}

	function deleteMessage(id) {
		$.ajax({
			type : 'DELETE',
			url : rootURL + "/messages/"+id,
			contentType : 'application/json',
			dataType : "json",
			success : function(data) {
				fullUpdate = true;
				console.log("message deleted");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('sendMessage error: ' + textStatus);
			}
		});
	}
	
	function sendContent() {
		var ligne = $("#ligne").val();
		$("#ligne").val("");
		sendMessage(ligne);
	}

	$("#submitButton").click(function() {
		sendContent();
	});

	$("#submitDelete").click(function() {
		console.log("test "+$("#idMessage").val());
		deleteMessage($("#idMessage").val());
		$("#idMessage").val("");
	});
	
	$(window).keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			sendContent();
			return false;
		}
	});

	updateMessages();

});