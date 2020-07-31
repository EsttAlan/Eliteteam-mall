
		$(function() {//页面加载完成以后执行
			//页面加载完成以后去获取活动数据
			doGetActivityss();
		});
		//获取所有活动数据
		function doGetActivityss() {
			//1.定义url
		
			var url = "address";
			//2.定义params
			var params = "";
			//3.发送异步请求获取数据并更新到页面上
			doAjaxGets(url, params, function(result) {//List<Activity>
				doHandleQueryResults(result);
			})
		}
		function doHandleQueryResults(result) {
			//debugger
			var jsonObj = JSON.parse(result);
			var tBody = $('#ads').find('tbody');//$("del");
			tBody.empty();//清空tbody中原有内容
			for (var i = 0; i < jsonObj.length; i++) {
				tBody.append(doCreateRows(jsonObj[i]));
			}
		}
		function doInitEditFormDatas(aty) {
			$("#atyId").val(aty.id);
			$("#nameId").val(aty.name);
			$("#useraddressId").val(aty.useraddress);
			$("#createtimeId").val(aty.createtime);

			$("#remarkId").text(aty.remark);
			//显示模态框
			$('#myModal').modal('show');
		}
		//呈现添加模态框
		function doShowAddDialogs() {
			$("#atyId").val('');
			$("#nameId").val('');
			$("#useraddressId").val('');
			$("#createtimeId").val('');

			$("#remarkId").text('');
			//显示模态框
			$('#myModal').modal('show');
		}
		function doLoadByIds(id) {
			// location.href="doFindById?id="+id;
			var url = "doFindById";
			var params = "id=" + id;
			doAjaxGets(url, params, function(result) {
				var jsonObj = JSON.parse(result);
				doInitEditFormDatas(jsonObj);
			})
		}
		function doSaveActivitys() {
			//1.定义url
			debugger
			var url = "/basket/doSaveAddress";
			//2.定义请求参数
			var params = "";
			var id = $("#atyId").val();//获取绑定的数据
			var name = $("#nameId").val();
			var useraddress = $("#useraddressId").val();
			var createtime = $("#createtimeId").val();
			var remark = $("#remarkId").val();
			var params = "id="+id+"&name=" + name + "&useraddress=" + useraddress
					+ "&createtime=" + createtime + "&remark=" + remark;
			
			//3.发送异步请求
			doAjaxPosts(url, params, function(result) {//--->Activity
				debugger
				console.log("result", result);
				var jsonObj = JSON.parse(result);
				$('#myModal').modal('hide');
				//刷新页面(查询所有或者页面上局部刷新)
				//doGetActivitys();// 再次查询所有
				window.location.reload() //刷新页面
				//$("tbody").append(doCreateRow(jsonObj));
				//$("tbody").prepend(doCreateRow(jsonObj)); 
				if (id) {//id有值说明是更新
					var tr = $("#" + id).parents("tr");
					tr.before(doCreateRows(jsonObj));
					tr.remove();
				} else {
					$("tbody").find("tr:eq(0)").before(doCreateRows(jsonObj));
				}
			});
		}

	     function doCreateRows(obj){
	    	 return `<tr>
	    	           <td>${obj.id}</td>
	    	           <td>${obj.name}</td>
	    	           <td>${obj.useraddress}</td>
	    	           <td>${obj.createtime}</td>
	    	           <td>${obj.remark}</td>
	    	           <td>
	    	           <button class="btn btn-danger btn-sm" id=${obj.id} onclick=doDeleteByIds(${obj.id})>删除</button>
				<input type="checkbox"></input>		
	    	           </td>
	    	         </tr>`;
	    	         
     }
		//基于ajax方式执行删除操作
		function doDeleteByIds(id) {
			if (!confirm("确定删除吗"))
				return;
			location.href="doDeleteById?id="+id;
			var url = "doDeleteById";
			var params = "id=" + id;
			//var obj=$(this);
			doAjaxGets(url, params, function(result) {
				alert("delete ok");
				//思考刷新的方式?...
				var obj = $("#" + id);
				console.log("obj", obj);
				$("#" + id).parents("tr").remove();
			});
		}
		
		//基于此函数向服务端发起ajax Get请求
		function doAjaxGets(url, params, callback) {
			//1.创建xhr对象
			var xhr = new XMLHttpRequest();
			//2.注册Xhr对象的状态监听函数
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						callback(xhr.responseText);//xhr.responseText为服务端返回的数据
					}
				}
			}
			//3.创建一个连接
			xhr.open("GET", url + "?" + params, true);//true表示异步执行
			//4.发送请求
			xhr.send(null)
		}
		//基于此函数向服务端发起ajax Get请求
		function doAjaxPosts(url, params, callback) {
			//1.创建xhr对象
			var xhr = new XMLHttpRequest();
			//2.注册Xhr对象的状态监听函数
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						callback(xhr.responseText);
					}
				}
			}
			//3.创建一个连接
			xhr.open("POST", url, true);//true表示异步执行
			//对于post请求必须设置请求头
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			//4.发送请求
			xhr.send(params);
		}