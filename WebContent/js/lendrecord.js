$(document).ready(function(){	
	$.get("showRecord",function(data){
		var html = "";
		for (var i=0; i<data.length; i++) {
			html += '<tr><td>' + data[i].detailId + '</td>' + '<td>' + data[i].book.bookName + '</td>'
			+ '<td>' + data[i].book.bookId + '</td><td>' + data[i].lendDate + '</td>'
			+ '<td>' + data[i].dueDate + '</td>'
			+ '<td>' + data[i].renewStatus + '</td><td class="xujie"><button type="button" class="btn btn-success xujie" id="'+data[i].detailId+'">续借</button></td>'
			+ '<td class="guihuan"><button type="button" class="btn btn-primary guihuan" id="'+data[i].detailId+'">归还</button> </td></tr>' ;
		}
		$('#recordContent').append(html);
	},"json");
	
	$(document).delegate(".xujie","click", function(){
		var detailId = $(this).attr("id");
		var url = "renew/"+detailId;
		$.get(url,function(data){
			alert("已成功续借");
			window.location.reload();
		});
	});
	
	$(document).delegate(".guihuan","click", function(){
		var detailId = $(this).attr("id");
		var url = "return/"+detailId;
		$.get(url,function(data){
			alert("已成功归还");
			window.location.reload();
		});
	});
});