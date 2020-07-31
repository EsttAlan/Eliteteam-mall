	function check(){
		var ischeckall=$("#checkAll").prop("checked");
		$("#tbodyId input:checkbox").prop("checked", ischeckall);
   }
	function doGetCheckedIds(){
		//获取被选择的复选框获取其中的val
		var Array=[];
		$("#tbodyId input:checkbox:checked").each(function(){
			Array.push($(this).val());
		});
		return Array;
	}
	function dodeletes(){
		var ids=doGetCheckedIds();
		//校验获取的ids值
		if(ids.length==0){
			alert("至少选择一个");
            return;
		}
		//url
		var url="/log/doDeleteObjects";
		//params
		var params={"ids":ids.toString()};
		console.log(params);
		//ajax
		$.post(url,params,function(result){
			console.log(result);
			if(result.state==1){
				alert(result.message);
				//doGetObjects();
				doRefreshAfterDelete();
			}else{
				alert(result.message);
				
			}
		});
	}
	function doRefreshAfterDelete(){
		var checkAll=$("#checkAll").prop("checked");
		var pageCurrent=$("#pageId").data("pageCurrent");
		var pagecount=$("#pageId").data("pagecount");
		if(checkAll&&pageCurrent==pegCount&&pagecount>1){
			pageCurrent--;
			$("#pageId").data("pageCurrent",pageCurrent);
		}
		doGetObjects();
	}
   $(function(){
	   doQueryObject();
	   $("#pageId").load("doPageUI");
	   $(".input-group-btn")
	   .on("click",".btn-search",doQueryObject)
	   .on("click",".btn-delete",dodeletes);
	 //处理thead中checkbox事件注册实践中的函数
	   //$("#checkAll").click(check);
	   $("#checkAll").change(check);
	   //tbody中的checkbox对象事件注册
	   $("#tbodyId").on("change","input:checkbox",dochangeTHeadCheckBox)
	   //$("#tbodyId").on("change",".cItem",dochangeTHeadCheckBox)
   });
   function dochangeTHeadCheckBox(){
	   //定义一个初始状态,默认值为true.
	   var flag=true;
	   //获取所有tbody的checkbox对象,并与flag初始状态进行逻辑与操作
	   $("#tbodyId input:checkbox").each(function(){
		   flag=flag&&$(this).prop("checked");
		   
	   });
	   //修改thead中checkbox状态
	   $("#checkAll").prop("checked",flag);
	   
   }
  function doQueryObject(){
	   $("#pageId").data("pageCurrent",1)
	   //获取参数
	   doGetObjects();
   }
  function doGetObjects(){
	  //初始化全选状态
	  $("#checkAll").prop("checked",false);
	  
	   //定义请求url
	   const url="log/doFindPageObjects";
	   //定义请求参数
	   var pageCurrent=$("#pageId").data("pageCurrent");
	   //为什么要执行如下语句的判定，然后初始化pageCurrent的值为1
       //pageCurrent参数在没有赋值的情况下，默认初始值应该为1.没有赋值的话pageCurrent默认为falsh
	   if(!pageCurrent) pageCurrent=1;
	   var uname=$("#searchNameId").val();
	   var params={"pageCurrent":pageCurrent};
	   //有值再给值赋值
	   if(uname) params.username=uname;
	   //发送异步请求并处理响应
	   $.getJSON(url,params,doHandleQueryResult)
	   /* $.getJSON(url,params,function(result){
          doHandleQueryResult(result);
	   }); */
  }
  		function doHandleQueryResult(result){
  			if(result.state==1){//正常数据
  				//将当前页的职责信息更新到页面上
  				doSetTableBodyRows(result.data.records);
  				//将分页信息更新到页面上
  				doSetPagination(result.data)
  				
  			}else{
  				//alert(result.message);
  				doSetQueryErrors(message);
  			}
  		}
  		function doSetQueryErrors(message){
  			$("#tbodyId").html(`<tr><td colspan="7">${message}</td></tr>`)
  		}
  		 function doSetTableBodyRows(records){
  			//1.获取tbody对象
  			var tBody=$("tbody");
  			tBody.empty();
  			//2.遍历将records并将结果更新到页面上
			for(var i=0;i<records.length;i++){
				tBody.append(doCreateRow(records[i]));
			}
  		}
  		function doCreateRow(result){
  			return`<tr>
  					<td><input type="checkbox" class='cItem' value=${result.id}></td>
  					<td>${result.username}</td>
  					<td>${result.operation}</td>
  					<td>${result.method}</td>
  					<td>${result.params}</td>
  					<td>${result.ip}</td>
  					<td>${result.time}</td>
  					
  					</tr>`;
  			
  			}	 