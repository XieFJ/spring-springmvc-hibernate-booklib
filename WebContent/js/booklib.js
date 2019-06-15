$(document).ready(function(){
	$('#denglu').click(function(){
		var userId = $("#userId").val();
		var userPwd = $("#userPwd").val();
		var userType = $("#userType").find("option:selected").text();
		$.ajax({
			url: "login",
			type: "post",
			data: JSON.stringify({
				"userId": userId,
				"userPwd": userPwd,
				"userType": userType
			}),
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			success: function(data){
				if(data.resultInfo != null) {
					alert(data.resultInfo);
					window.location.reload();
				}
				else {
					alert(data.userType);
					if(data.userType == "读者") { 
//						alert(data.userType);
						window.location.href = "/spring-springmvc-hibernate-booklib/index";
					}
					else {
//						alert(data.userType);
						window.location.href = "/spring-springmvc-hibernate-booklib/manage";
					}
				}
			}
		});
	});
	
	$('#chazhao').click(function(){
		var content = $("#searchContent").val();
		$.ajax({
			url: "search",
			type: "post",
			data: content,
			contentType: "application/text;charset=UTF-8",
			dataType: "json",
			success: function(data){
				html = "";
				for (var i=0; i<data.length; i++) {
					html += '<a href="detail/' + data[i].bookName + '" class="search-link"><div class="search-info"> '
					+ '<div class="search-cover"><img src="' + data[i].picture + '" />' + '</div><div class="search-about">'
					+ '<div class="search-name"><p class="h4">' + data[i].bookName + '</p></div><div class="search-author-publisher">'
					+ '<p><span class="glyphicon glyphicon-user">&nbsp;' + data[i].author + '</span></p>'
					+ '<p><span class="glyphicon glyphicon-book">&nbsp;' + data[i].publisher + '</span></p></div></div></div></a>';
				}
				$("#searchResult").empty();
				$("#searchResult").append(html);
			}
		});
	});
	
	$.get("checkUser",function(data){
		if(data.userName != null) {
			$("#login").hide();
			$("#operatorName").append(data.userName);
		}
		else {
			$("#logout").hide();
		}
	});
	
});