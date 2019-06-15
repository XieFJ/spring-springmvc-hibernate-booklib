$(document).ready(function(){	
	$.get("loadDetail",function(data){
		var str = '<img src="' + data.picture + '" />';
		$("#bookCover").append(str);
		$("#bookName").append(data.bookName);	
		$("#bookAuthor").append(data.author);
		$("#bookPublisher").append(data.publisher);
		$("#bookContent").append(data.introduction);
		var html = "";
		for (var i=0; i<data.stockNumber; i++) {
			html += '<tr><td>浙江大学城市学院图书馆</td><td class="bookItemId">' + data.bookId+ '</td>' 
			+ '<td><span class="label label-success">在馆</span></td><td></td></tr>';
		}
		$("#record").append(html);	
	});	
	
	$.get("loadRecord",function(data){
		var html = "";
		for (var i=0; i<data.length; i++) {
			html += '<tr><td>浙江大学城市学院图书馆</td><td class="bookItemId">' + data[i].book.bookId+ '</td>' 
			+ '<td><span class="label label-danger">借出</span></td><td>'+ data[i].dueDate
			+'</td></tr>';
		}
		$("#record").append(html);	
	});

	$('#addBookshelf').click(function(){
		var bookId = $('.bookItemId:first').text();
		var url = "addItem/"+bookId;
		$.get(url,function(data){
			alert("已成功加入书架");
		});
	});
	
});