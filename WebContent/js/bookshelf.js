$(document).ready(function(){	
	$.get("showItem",function(data){
		var html = "";
		for (var i=0; i<data.length; i++) {
			html += '<tr><td><img src="' + data[i].item.picture + '"/></td>' 
			+ '<td class="bookItemId">' + data[i].item.bookId + '</td><td>' + data[i].item.bookName + '</td>'
			+ '<td>' + data[i].quantity + '</td><td class="shanchu"><button type="button" class="btn btn-danger removeBookshelf" id="'+data[i].item.bookId+'">删除</button></td></tr>';
		}
		$('#itemContent').append(html);
	},"json");
	
	$(document).delegate(".removeBookshelf","click", function(){
		var bookId = $(this).attr("id");
		var url = "removeItem/"+bookId;
		$.get(url,function(data){
			alert("已成功删除");
			window.location.reload();
		});
	});
	
	$('#confirmBorrow').click(function(){
		$.get("borrow",function(data){
			alert("已成功借阅");
			window.location.reload();
		});
	});
});
