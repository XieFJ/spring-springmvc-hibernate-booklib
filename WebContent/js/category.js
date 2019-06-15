$(document).ready(function(){
	$.get("loadCategory",function(data){
		var html = "";
		for (var i=0; i<data.length; i++) {
			html += '<div class="col-md-3"><a title="' + data[i].booktypeName + '" class="category-link" href="books/'
			+ data[i].booktypeName + '">' + '<div class="category-info"><div class="cagetory-cover"><img src="' + data[i].picture 
			+ '" /> </div> <div class="category-about"> <p class="category-name h4">' + data[i].booktypeName 
			+ '</p> </div> </div> </a> </div>';
		}
		$("#categoryContent").append(html);		
	},"json");	
});