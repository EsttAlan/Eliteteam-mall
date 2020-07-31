  /* 点击idall 改变状态为全选 */
    $(function(){
	 doGetObjects();
	 $("#all").change(checkAll);
	 $("#tbody").on("change","input:checkbox",doCheckBox)
//	 $(".input-group-btn")
//	  .on("click",".btn-search",doFindByname)
	  //console.log("2");
	 
});
/*function doFindByname(){
	console.log("1");
	 var name=$("#errorCode").val();
	   var params={"name":name};
	   //获取查询时用户输入的名字
	   var url="/basket/doFindByname";
	   //3.ajax request
	   //console.log(params);
	   $.getJSON(url,params,function(result){
		   doResult(result);
	   });
}*/
document.onkeydown = function(event) {
    var target, code, tag;
    if (!event) {
        event = window.event; //针对ie浏览器
        target = event.srcElement;
        code = event.keyCode;
        if (code == 13) {
            tag = target.tagName;
            if (tag == "TEXTAREA") { return true; }
            else { return false; }
        }
    }
    else {
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox
        code = event.keyCode;
        if (code == 13) {
            tag = target.tagName;
            if (tag == "INPUT") { return false; }
            else { return true; }
        }
    }
};
function submitTest(){
	if(req.getParameter("smit")== null)
    {
		return  true; 
		console.log("1");
 }
	console.log("2");
	return  false;
}
function login() {
    $.ajax({
    //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/basket/doFinds" ,//url
        data: $('#form1').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
            ;
        },
        error : function() {
            alert("异常！");
        }
    });
}


function _b(){
	if(event.keyCode ==13)
			_a();
	}
function _a(){
	//alert("按钮被点击");
	doFindByname();
}
function doFindByname(){
	
	 var name=$("#keyWords").val();
	   var params={"name":name};
	   //获取查询时用户输入的名字
	   var url="/basket/doFindByname";
	   //3.ajax request
	   //console.log(params);
	   $.getJSON(url,params,function(result){
		   doResult(result);
		   $("#danjia").html("0");
			$("#count").html("0"); 
			$("#all").prop("checked",false);
			var name=$("#keyWords").val("");
	   });
}
function _c(id)
{
	if(event.keyCode ==13)
	updateNum(id);
}
function updateNum(id) {
	var trList=$("#tbody").children("tr");
	for(var i=0;i<trList.length;i++){
		var tdArr = trList.eq(i).find("td");
		if(tdArr.eq(0).find("input").val()==id){
		
		var number = tdArr.eq(4).find("input[type='text']").val();
			update(id,number);
		}
	}
}
function update(id,num) {
	var url="/basket/Update";
	var params={"id":id,"number":num};
	$.post(url,params,function(result){
		if(result.state==1){
			//alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}

function doCheckBox(){
	var flag=true;
	$("#tbody input:checkbox").each(function(){
		flag=flag&&$(this).prop("checked");
	});
	$("#all").prop("checked",flag);
}
function doGetObjects(){
	 $("#all").prop("checked",false);
	var params="";
	var url="/basket/FindAll";
	$.getJSON(url,params,function(result){
		doResult(result);
	});
}
function doResult(Baskets){
	var tBody=$("#tbody");
		tBody.empty();
		//2.遍历将records并将结果更新到页面上
	for(var i=0;i<Baskets.length;i++){
		tBody.append(doCreateRow(Baskets[i]));
	}
	calcRow();
}
function doCreateRow(result){
	return`
	<tr class="STYLE1" style="text-align:center; vertical-align:middel;">
		<td><input type="checkbox" class="ckitem pull-left"  style="vertical-align:middle;" name="id" value="${result.id}" onclick="calcTotal()" ></td>
		
		<td
            <a href="http://localhost/detail/GoodsDetail?skuId=${result.skuId}">
				<textarea name="description" cols="20" rows="5" style="border:none;resize:none;vertical-align:middle;" disabled>${result.skuName}</textarea>
			</a>
		</td>
		
		<td style="vertical-align:middle" disabled>${result.model}</td>
		
		<td style="vertical-align:middle"><input type="text" id="goodsPrice${result.id}" style="border:none;text-align:center"value="${result.skuPrice}" disabled/></td>
		
		<td style="vertical-align:middle">
			
		<input type="text" class="btn-default btn-lg" style="border:none;"   size="1"  value="${result.number}" onKeyDown = "_c(${result.id})" />
		    
		</td>
		
		<td style="vertical-align:middle">
			<input type="text" id="goodsCast${result.id}" class="danjiasums"  style="border:none;text-align:center" disabled></input>
		</td>
		
		<td style="vertical-align:middle"><button type="button" class="layui-btn layui-btn-primary" 
			onclick="doDeleteById(${result.id})">确认收货</button></td>
	</tr>`
}
function calcTotal(){
	
	var sum=0;
	var numm=0;
	var trList=$("#tbody").children("tr");
	for(var i=0;i<trList.length;i++){
		var tdArr = trList.eq(i).find("td");
		if(tdArr.eq(0).find("input").prop("checked")){
		var danjiacount=tdArr.eq(5).find("input").val();
		var number = tdArr.eq(4).find("input[type='text']").val();
		sum+=parseFloat(danjiacount);
		numm+=parseFloat(number);
		}
	}
		$("#danjia").html(numm);
		$("#count").html(sum);
} 
function calcRow() {	
	//getSum();
	var trList=$("#tbody").children("tr");
	for(var i=0;i<trList.length;i++){
	var tdArr = trList.eq(i).find("td");
	var money = tdArr.eq(3).find("input").val();//收入金额
    var number = tdArr.eq(4).find("input[type='text']").val();
	//console.log(money);
	//console.log(number);
	var danjiacount=money*1*number*1
	tdArr.eq(5).find("input").val(danjiacount)
	}
} 
function checkAll() {
	var isCheck = $("#all").prop("checked");
	$("input[type='checkbox']").prop("checked", isCheck);
	calcTotal();
}

function addNum(id,number) {
	var num=number*1+1;
	var url="/basket/Update";
	var params={"id":id,"number":num};
	$.post(url,params,function(result){
		if(result.state==1){
			//alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
		
	});
}
function reduceNum(id,number) {
	var num=number*1-1;
	if(num<1){
		alert("亲亲该宝贝不能再减少了呦~")
		return;
	}
	//location.href = "Update?id="+ id+"&number="+num;
	var url="/basket/Update";
	var params={"id":id,"number":num};
	$.post(url,params,function(result){
		if(result.state==1){
			//alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
/*function check(){
	var num=$("#tsinput").val()
	console.log(num);
	if(event.KeyCode==13)
	var url="/basket/Update";
	var params={"id":id,"number":num};
	$.post(url,params,function(){
		doGetObjects()
	
	});
}*/
function doDeletess(){
	//获取勾选的
	for (var i = $(".ckitem").length - 1; i >= 0; i--) {
	//如果选中
	if ($(".ckitem")[i].checked) {
		//删除
		$($(".ckitem")[i]).parents("tr").remove();
	}
}
	//获取id值
}
function doDeleteById(id) {
	//location.href = "deleteById?id=" + id; 
	var url="/basket/deleteById"
	var params={"id":id};
	if(!confirm("确认收货吗"))return;
	$.post(url,params,function(result){
		if(result.state==1){
			//alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
function doDeletes(){
	var ids=[];
	$("#tbody input:checkbox:checked").each(function(){
		ids.push($(this).val());
	});
	if(ids.length==0){
		alert("至少选择一个");
        return;
	}
	var url="/basket/delete";
	var params={"ids":ids.toString()};
	$.post(url,params,function(result){
		if(result.state==1){
			//alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	});

}

	/* 搜索 */
	document.onkeydown = function(event) {
    var target, code, tag;
    if (!event) {
        event = window.event; //针对ie浏览器
        target = event.srcElement;
        code = event.keyCode;
        if (code == 13) {
            tag = target.tagName;
            if (tag == "TEXTAREA") { return true; }
            else { return false; }
        }
    }
    else {
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox
        code = event.keyCode;
        if (code == 13) {
            tag = target.tagName;
            if (tag == "INPUT") { return false; }
            else { return true; }
        }
    }
};
function submitTest(){
	if(req.getParameter("smit")== null)
    {
		return  true; 
		console.log("1");
 }
	console.log("2");
	return  false;
}
function login() {
    $.ajax({
    //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/basket/doFinds" ,//url
        data: $('#form1').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.resultCode == 200) {
                alert("SUCCESS");
            }
            ;
        },
        error : function() {
            alert("异常！");
        }
    });
}


function _b(){
	if(event.keyCode ==13)
			_a();
	}
function _a(){
	//alert("按钮被点击");
	doFindByname();
}
function doFindByname(){
	
	 var name=$("#keyWords").val();
	   var params={"name":name};
	   //获取查询时用户输入的名字
	   var url="/basket/doFindByname";
	   //3.ajax request
	   //console.log(params);
	   $.getJSON(url,params,function(result){
		   doResult(result);
		   $("#danjia").html("0");
			$("#count").html("0"); 
			$("#all").prop("checked",false);
			var name=$("#keyWords").val("");
	   });
}
function _c(id)
{
	if(event.keyCode ==13)
	updateNum(id);
}
function updateNum(id) {
	var trList=$("#tbody").children("tr");
	for(var i=0;i<trList.length;i++){
		var tdArr = trList.eq(i).find("td");
		if(tdArr.eq(0).find("input").val()==id){
		
		var number = tdArr.eq(4).find("input[type='text']").val();
			update(id,number);
		}
	}
}
function update(id,num) {
	var url="/basket/Update";
	var params={"id":id,"number":num};
	$.post(url,params,function(result){
		if(result.state==1){
			//alert(result.message);
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
	/* 地址模块 */
	
	
	
	
	
	