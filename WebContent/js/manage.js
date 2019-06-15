$(document).ready(function(){
	
	$.get("loadCategory",function(data){
		var html = "";
		if(data.length == 0) {
			html = '<p class="h3">暂无记录</p>';
			$("#resultContent").empty();
			$("#resultContent").append(html);
		}
		else {
			for(var i=0; i<data.length; i++) {
				html += '<tr><td>' + data[i].booktypeId + '</td><td>' + data[i].booktypeName
				+ '</td><td><img src="' + data[i].picture + '"/></td>'
				+ '<td><a class="edit" data-toggle="modal" data-target="#editBookType" href="#"'
				+ 'id="' + data[i].booktypeId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
				+ 'id="' + data[i].booktypeId + '">删除</a></td></tr>'
			}
			$("#bookTypeContent").empty();
			$("#bookTypeContent").append(html);
		}
	});
	
	$('#searchBookType').submit(function(){
		var booktypeName = $("#booktypeName").val();
		var url = "";
		if (booktypeName == "") {
			url = "findBookType";
		}
		else {
			url = "findBookType/" + booktypeName;
		}
		$.post(url,function(data){
			var html = "";
			if(data.length == 0) {
				html = '<p class="h3">共检索到0条关于"'+ booktypeName +'"的结果</p>';
				$("#resultContent").empty();
				$("#resultContent").append(html);
			}
			else {
				for(var i=0; i<data.length; i++) {
					html += '<tr><td>' + data[i].booktypeId + '</td><td>' + data[i].booktypeName
					+ '</td><td><img src="' + data[i].picture + '"/></td>'
					+ '<td><a class="edit" data-toggle="modal" data-target="#editBookType" href="#"'
					+ 'id="' + data[i].booktypeId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
					+ 'id="' + data[i].booktypeId + '">删除</a></td></tr>'
				}
				$("#bookTypeContent").empty();
				$("#bookTypeContent").append(html);
			}
		});
		return false;		//阻止表单提交，防止刷新页面
	});
	
	$('#queding').click(function(){
		var booktypeName = $("#booktypeName_add").val();
		var picture = $("#picture").val();
		$.ajax({
			url: "addBookType",
			type: "post",
			data: JSON.stringify({
				"booktypeName": booktypeName,
				"picture": picture
			}),
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			success: function(){
				alert('添加成功');
			}
		});
	});
	
	$(document).delegate(".delete","click", function(){
		var booktypeId = $(this).attr("id");
		var url = "deleteBookType/"+booktypeId;
		$.get(url,function(data){
			alert("已成功删除");
			window.location.reload();
		});
	});
	
	$(document).delegate(".edit","click", function(){
		var booktypeId = $(this).attr("id");
		$(document).delegate("#bianji","click", function(){
			var booktypeName = $("#booktypeName_edit").val();
			var picture = $("#picture_edit").val();
//				alert(booktypeId);
//				alert(booktypeName);
//				alert(picture);
			$.ajax({
				url: "modifyBookType",
				type: "post",
				data: JSON.stringify({
					"booktypeId": booktypeId,
					"booktypeName": booktypeName,
					"picture": picture
				}),
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				success: function(){
					alert('修改成功');
				}
			});
		});
	});

	
	$.get("loadAllBooks",function(data){
		var html = "";
		if(data.length == 0) {
			html = '<p class="h3">暂无记录</p>';
			$("#resultContent2").empty();
			$("#resultContent2").append(html);
		}
		else {
			for(var i=0; i<data.length; i++) {
				html += '<tr><td>' + data[i].bookId 
				+ '</td><td>' + data[i].bookName
				+ '</td><td>' + data[i].bookType.booktypeName
				+ '</td><td>' + data[i].author
				+ '</td><td>' + data[i].publisher
				+ '</td><td>' + data[i].stockNumber
				+ '</td><td>' + data[i].introduction
				+ '</td><td><img src="' + data[i].picture + '"/></td>'
				+ '<td><a class="edit" data-toggle="modal" data-target="#editBook" href="#"'
				+ 'id="' + data[i].bookId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
				+ 'id="' + data[i].bookId + '">删除</a></td></tr>'
			}
			$("#bookContent").empty();
			$("#bookContent").append(html);
		}
	});
	
	$('#searchBook').submit(function(){
		var bookName = $("#bookName").val();
		var author = $("#author").val();
		var booktypeName = $("#bookTypeName").val();
		var url = "";
		if (bookName == "") {
			if (author == "") {
				if (booktypeName == "") {
					url = "findBooks8";
				}
				else {
					url = "findBooks4/" + booktypeName;
				}
			}
			else {
				if (booktypeName == "") {
					url = "findBooks3/" + author;
				}
				else {
					url = "findBooks7/" + author + "/" + booktypeName;
				}
			}
		}
		else {
			if (author == "") {
				if (booktypeName == "") {
					url = "findBooks2/" + bookName;
				}
				else {
					url = "findBooks6/" + bookName + "/" + booktypeName;
				}
			}
			else {
				if (booktypeName == "") {
					url = "findBooks5/" + bookName + "/" + author;
				}
				else {
					url = "findBooks/" + bookName + "/" + author + "/" +booktypeName;
				}
			}
		}
		$.post(url,function(data){
			var html = "";
			if(data.length == 0) {
				html = '<p class="h3">共检索到0条关于"'+ bookName +'"的结果</p>';
				$("#resultContent2").empty();
				$("#resultContent2").append(html);
			}
			else {
				for(var i=0; i<data.length; i++) {
					html += '<tr><td>' + data[i].bookId 
					+ '</td><td>' + data[i].bookName
					+ '</td><td>' + data[i].bookType.booktypeName
					+ '</td><td>' + data[i].author
					+ '</td><td>' + data[i].publisher
					+ '</td><td>' + data[i].stockNumber
					+ '</td><td>' + data[i].introduction
					+ '</td><td><img src="' + data[i].picture + '"/></td>'
					+ '<td><a class="edit" data-toggle="modal" data-target="#editBook" href="#"'
					+ 'id="' + data[i].bookId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
					+ 'id="' + data[i].bookId + '">删除</a></td></tr>'
				}
				$("#bookContent").empty();
				$("#bookContent").append(html);
			}
		});
		return false;		//阻止表单提交，防止刷新页面
	});
	
	$('#queding2').click(function(){
		var bookName = $("#bookname_add").val();
		var booktypeName = $("#typename_add").val();
		var author = $("#authorname_add").val();
		var publisher = $("#publishername_add").val();
		var stockNumber = $("#stocknumner_add").val();
		var picture = $("#booklink_add").val();
		var introduction = $("#bookintro_add").val();
		var url = "";
		if (bookName=="" || booktypeName=="" || author=="" || publisher=="" || stockNumber=="" || picture=="" || introduction=="") {
			alert("请将信息填写完整");
		}
		else {
			url = "addBook/" + bookName + "/" + booktypeName + "/" + author + "/" + publisher + "/"
			+ stockNumber + "/" + picture + "/" + introduction;
		}
		$.post(url,function(){
			alert("添加成功");
		});
	});
	
	$(document).delegate(".delete","click", function(){
		var bookId = $(this).attr("id");
		var url = "deleteBook/"+bookId;
		$.get(url,function(data){
			alert("已成功删除");
//				window.location.reload();
		});
	});
	
	$(document).delegate(".edit","click", function(){
		var bookId = $(this).attr("id");
		$(document).delegate("#bianji2","click", function(){
			var bookName = $("#bookname_edit").val();
//			var booktypeName = $("#typename_edit").val();
			var author = $("#authorname_edit").val();
			var publisher = $("#publishername_edit").val();
			var stockNumber = $("#stocknumner_edit").val();
			var picture = $("#booklink_edit").val();
			var introduction = $("#bookintro_edit").val();
			$.ajax({
				url: "modifyBook",
				type: "post",
				data: JSON.stringify({
					"bookId": bookId,
					"bookName": bookName,
//					"bookType.booktypeName": booktypeName,			//不确定
					"author": author,
					"publisher": publisher,
					"stockNumber": stockNumber,
					"picture": picture,
					"introduction": introduction
				}),
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				success: function(){
					alert('修改成功');
				}
			});
		});
	});
	
	$.get("loadAllDetails",function(data){
		var html = "";
		if(data.length == 0) {
			html = '<p class="h3">暂无记录</p>';
			$("#resultContent3").empty();
			$("#resultContent3").append(html);
		}
		else {
			for(var i=0; i<data.length; i++) {
				html += '<tr><td>' + data[i].detailId 
				+ '</td><td>' + data[i].book.bookId
				+ '</td><td>' + data[i].book.bookName
				+ '</td><td>' + data[i].reader.readerId
				+ '</td><td>' + data[i].reader.readerName
				+ '</td><td>' + data[i].lendDate
				+ '</td><td>' + data[i].dueDate
				+ '</td><td>' + data[i].penalty
				+ '</td></tr>'
			}
			$("#detailContent").empty();
			$("#detailContent").append(html);
		}
	});
	
	$('#searchDetail').submit(function(){
		var bookId = $("#bookId").val();
		var readerId = $("#readerId").val();
		var url = "";
		if (bookId == "") {
			if (readerId == "") {
				url = "findDetails4";
			}
			else {
				url = "findDetails3/" + readerId;
			}
		}
		else {
			if (readerId == "") {
				url = "findDetails2/" + bookId;
			}
			else {
				url = "findDetails/" + bookId + "/" + readerId;
			}
		}
		$.post(url,function(data){
			var html = "";
			if(data.length == 0) {
				html = '<p class="h3">共检索到0条符合条件的结果</p>';
				$("#resultContent3").empty();
				$("#resultContent3").append(html);
			}
			else {
				for(var i=0; i<data.length; i++) {
					html += '<tr><td>' + data[i].detailId 
					+ '</td><td>' + data[i].book.bookId
					+ '</td><td>' + data[i].book.bookName
					+ '</td><td>' + data[i].reader.readerId
					+ '</td><td>' + data[i].reader.readerName
					+ '</td><td>' + data[i].lendDate
					+ '</td><td>' + data[i].dueDate
					+ '</td><td>' + data[i].penalty
					+ '</td></tr>'
				}
				$("#detailContent").empty();
				$("#detailContent").append(html);
			}
		});
		return false;		//阻止表单提交，防止刷新页面
	});

	
	$.get("loadReaderTypes",function(data){
		var html = "";
		if(data.length == 0) {
			html = '<p class="h3">暂无记录</p>';
			$("#resultContent4").empty();
			$("#resultContent4").append(html);
		}
		else {
			for(var i=0; i<data.length; i++) {
				html += '<tr><td>' + data[i].readertypeId 
				+ '</td><td>' + data[i].readertypeName
				+ '</td><td>' + data[i].lendLimitted
				+ '<span>本</span></td><td>' + data[i].dueTime
				+ '<span>天</span></td>' 
				+ '<td><a class="edit" data-toggle="modal" data-target="#editReaderType" href="#"'
				+ 'id="' + data[i].readertypeId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
				+ 'id="' + data[i].readertypeId + '">删除</a></td></tr>'
			}
			$("#readertypeContent").empty();
			$("#readertypeContent").append(html);
		}
	});
	
	$('#searchReaderType').submit(function(){
		var readertypeName = $("#readertypeName").val();
		var url = "";
		if (readertypeName == "") {
			url = "findReaderType";
		}
		else {
			url = "findReaderType/" + readertypeName;
		}
		$.post(url,function(data){
			var html = "";
			if(data.length == 0) {
				html = '<p class="h3">共检索到0条关于"'+ readertypeName +'"的结果</p>';
				$("#resultContent4").empty();
				$("#resultContent4").append(html);
			}
			else {
				for(var i=0; i<data.length; i++) {
					html += '<tr><td>' + data[i].readertypeId 
					+ '</td><td>' + data[i].readertypeName
					+ '</td><td>' + data[i].lendLimitted
					+ '<span>本</span></td><td>' + data[i].dueTime
					+ '<span>天</span></td>'
					+ '<td><a class="edit" data-toggle="modal" data-target="#editReaderType" href="#"'
					+ 'id="' + data[i].readertypeId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
					+ 'id="' + data[i].readertypeId + '">删除</a></td></tr>'
				}
				$("#readertypeContent").empty();
				$("#readertypeContent").append(html);
			}
		});
		return false;		//阻止表单提交，防止刷新页面
	});
	
	$('#queding4').click(function(){
		var readertypeName = $("#readertypeName_add").val();
		var lendLimitted = $("#lendLimitted_add").val();
		var dueTime = $("#duetime_add").val();
		$.ajax({
			url: "addReaderType",
			type: "post",
			data: JSON.stringify({
				"readertypeName": readertypeName,
				"lendLimitted": lendLimitted,
				"dueTime": dueTime,
			}),
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			success: function(){
				alert('添加成功');
			}
		});
	});
	
	$(document).delegate(".delete","click", function(){
		var readertypeId = $(this).attr("id");
		var url = "deleteReaderType/"+readertypeId;
		$.get(url,function(data){
			alert("已成功删除");
		});
	});
	
	$(document).delegate(".edit","click", function(){
		var readertypeId = $(this).attr("id");
		$(document).delegate("#bianji4","click", function(){
			var readertypeName = $("#readertypeName_edit").val();
			var lendLimitted = $("#lendLimitted_edit").val();
			var dueTime = $("#dueTime_edit").val();
			$.ajax({
				url: "modifyReaderType",
				type: "post",
				data: JSON.stringify({
					"readertypeId": readertypeId,
					"readertypeName": readertypeName,
					"lendLimitted": lendLimitted,
					"dueTime": dueTime
				}),
				contentType: "application/json;charset=UTF-8",
				dataType: "json",
				success: function(){
					alert('修改成功');
				}
			});
		});
	});
	
	$.get("loadReaders",function(data){
		var html = "";
		if(data.length == 0) {
			html = '<p class="h3">暂无记录</p>';
			$("#resultContent5").empty();
			$("#resultContent5").append(html);
		}
		else {
			for(var i=0; i<data.length; i++) {
				html += '<tr><td>' + data[i].readerId 
				+ '</td><td>' + data[i].readerName
				+ '</td><td>' + data[i].readerType.readertypeName
				+ '</td><td>' + data[i].lendNumber
				+ '</td><td>' + data[i].createDate
				+ '</td><td><a class="edit" data-toggle="modal" data-target="#editReader" href="#"'
				+ 'id="' + data[i].readerId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
				+ 'id="' + data[i].readerId + '">删除</a></td></tr>'
			}
			$("#readerContent").empty();
			$("#readerContent").append(html);
		}
	});
	
	$('#searchReader').submit(function(){
		var readerName = $("#readerName").val();
		var readertypeName = $("#readertypeName").val();
		var url = "";
		if (readerName == "") {
			if (readertypeName == "") {
				url = "findReaders4";
			}
			else {
				url = "findReaders3/" + readertypeName;
			}
		}
		else {
			if (readertypeName == "") {
				url = "findReaders2/" + readerName;
			}
			else {
				url = "findReaders/" + readerName + "/" + readertypeName;
			}
		}
		$.post(url,function(data){
			var html = "";
			if(data.length == 0) {
				html = '<p class="h3">共检索到0条符合条件的结果</p>';
				$("#resultContent5").empty();
				$("#resultContent5").append(html);
			}
			else {
				for(var i=0; i<data.length; i++) {
					html += '<tr><td>' + data[i].readerId 
					+ '</td><td>' + data[i].readerName
					+ '</td><td>' + data[i].readerType.readertypeName
					+ '</td><td>' + data[i].lendNumber
					+ '</td><td>' + data[i].createDate
					+ '<td><a class="edit" data-toggle="modal" data-target="#editReader" href="#"'
					+ 'id="' + data[i].readerId + '">编辑</a><span class="fenge">|</span><a class="delete" href="#"' 
					+ 'id="' + data[i].readerId + '">删除</a></td></tr>'
				}
				$("#readerContent").empty();
				$("#readerContent").append(html);
			}
		});
		return false;		//阻止表单提交，防止刷新页面
	});

	$('#queding5').click(function(){
		var readerName = $("#readerName_add").val();
		var readertypeName = $("#readertypeName2_add").find("option:selected").text();
		var url = "addReader/" + readerName + "/" + readertypeName;
		$.post(url,function(){
			alert("添加成功");
		})
	});
	
	$(document).delegate(".delete","click", function(){
		var readerId = $(this).attr("id");
		var url = "deleteReader/"+readerId;
		$.get(url,function(data){
			alert("已成功删除");
//				window.location.reload();
		});
	});
	
	$(document).delegate(".edit","click", function(){
		var readerId = $(this).attr("id");
		$(document).delegate("#bianji5","click", function(){
			var readerName = $("#readerName_edit").val();
			var readertypeName = $("#readertypeName2_edit").find("option:selected").text();
			var url = "";
			if (readerName == "") {
				if (readertypeName == "") {
					url = "modifyReader4/" + readerId;
				}
				else {
					url = "modifyReader3/" + readerId + "/" + readertypeName;
				}
			}
			else {
				if (readertypeName == "") {
					url = "modifyReader2/" + readerId + "/" + readerName;
				}
				else {
					url = "modifyReader/" + readerId + "/" + readerName + "/" + readertypeName;
				}
			}
			$.post(url,function(){
				alert("修改成功");
			})
		});
	});
	
	$.get("loadUsers",function(data){
		var html = "";
		if(data.length == 0) {
			html = '<p class="h3">暂无记录</p>';
			$("#resultContent6").empty();
			$("#resultContent6").append(html);
		}
		else {
			for(var i=0; i<data.length; i++) {
				html += '<tr><td>' + data[i].userId 
				+ '</td><td>' + data[i].userName
				+ '</td><td>' + data[i].createDate
				+ '</td><td><a class="delete" href="#"' 
				+ 'id="' + data[i].userId + '">删除</a></td></tr>'
			}
			$("#systemUserContent").empty();
			$("#systemUserContent").append(html);
		}
	});
	
	$('#searchSystemUser').submit(function(){
		var userName = $("#userName").val();
		var url = "";
		if (userName == "") {
			url = "findSystemUsers";
		}
		else {
		    url = "findSystemUsers/" + userName;
		}
		$.post(url,function(data){
			var html = "";
			if(data.length == 0) {
				html = '<p class="h3">共检索到0条符合条件的结果</p>';
				$("#resultContent6").empty();
				$("#resultContent6").append(html);
			}
			else {
				for(var i=0; i<data.length; i++) {
					html += '<tr><td>' + data[i].userId 
					+ '</td><td>' + data[i].userName
					+ '</td><td>' + data[i].createDate
					+ '</td><td><a class="delete" href="#"' 
					+ 'id="' + data[i].userId + '">删除</a></td></tr>'
				}
				$("#systemUserContent").empty();
				$("#systemUserContent").append(html);
			}
		});
		return false;		//阻止表单提交，防止刷新页面
	});	
	
	$('#queding6').click(function(){
		var userName = $("#userName_add").val();
		var password = $("#password_add").val();
		var password2 = $("#password2_add").val();
		url = "";
		if (userName == "") {
			alert("请输入用户名");
		}
		else {
			if (password == "") {
				alert("请输入密码");
			}
			else {
				if (password != password2) {
					alert("密码不一致");
				}
				else {
					url = "addSystemUser/" + userName + "/" + password + "/" + password2;
				}
			}
		}
		if(url != "") {
			$.post(url,function(){
				alert("添加成功");
			});
		}	
	});
	
	$(document).delegate(".delete","click", function(){
		var userId = $(this).attr("id");
		var url = "deleteSystemUser/"+userId;
		$.get(url,function(data){
			alert("已成功删除");
//				window.location.reload();
		});
	});
	
});