$(document).ready(function(){	
	$.get("loadBook",function(data){
		$("#categoryName").append(data[0].bookType.booktypeName);	
		var html = "";
		for (var i=0; i<data.length; i++) {
			html += '<div class="col-md-3"><a title="' + data[i].bookName + '" class="book-link" href="detail/'
			+ data[i].bookName + '">' + '<div class="book-info"><div class="book-cover"><img src="' + data[i].picture 
			+ '" /> </div> <div class="book-about"> <p class="book-name h4">' + data[i].bookName 
			+ '</p> </div> </div> </a> </div>';
		}
		$("#booksContent").append(html);		
	},"json");	

});