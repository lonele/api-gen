function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r != null) return decodeURI(r[2]); return null; //返回参数值
}

var CODE_SUCCESS = 200

var datatable_language = {  
	'emptyTable': '没有数据',  
	'loadingRecords': '加载中...',  
	'processing': '查询中...',  
	'search': '检索:',  
	'lengthMenu': '每页 _MENU_ 条',  
	'zeroRecords': '没有数据',  
	'paginate': {  
		'first':      '第一页',  
		'last':       '最后一页',  
		'next':       '',  
		'previous':   ''  
	},  
	'info': '第 _PAGE_ 页 / 总 _PAGES_ 页 共 _TOTAL_ 条',  
	'infoEmpty': '没有数据',  
	'infoFiltered': '(过滤总件数 _MAX_ 条)'  
} 

//给form表单赋值json对象
function initFromValue(form, data)
{
    for (var p in data)
    {
        form.find(":input[name='" + p + "']").val(data[p]);
    } 
}