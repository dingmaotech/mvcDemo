$(document).ready(function() {
	var usertable = $('#data-table-user').DataTable({
		"paging": true,
		"ordering": true,
		"info": false,
		"searching": true,
		"columnDefs": [{
			// 定义操作列
			"targets": 2,
			"data": null,
			"render": function(data, type, row) {
				var html = '<a href="javascript:void(0);" class="delete btn btn-default btn-xs"><i class="fa fa-times"></i> 删除</a>'
				html += ' <a href="javascript:void(0);" class="edit btn btn-default btn-xs"><i class="fa fa-arrow-up"></i> 修改</a>'
				html += ' <a href="javascript:void(0);" class="detail btn btn-default btn-xs"><i class="fa fa-arrow-down"></i>详细</a>'
				return html;
			}
		}],
		language: {
			"processing": "处理中...",
			"lengthMenu": "显示 _MENU_ 项结果",
			"zeroRecords": "没有匹配结果",
			"info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"infoEmpty": "显示第 0 至 0 项结果，共 0 项",
			"infoFiltered": "(由 _MAX_ 项结果过滤)",
			"infoPostFix": "",
			"search": "搜索:",
			"url": "",
			"emptyTable": "表中数据为空",
			"loadingRecords": "载入中...",
			"infoThousands": ",",
			"paginate": {
				"first": "首页",
				"previous": "上页",
				"next": "下页",
				"last": "末页"
			},
			"aria": {
				"sortAscending": ": 以升序排列此列",
				"sortDescending": ": 以降序排列此列"
			}
		}
	});



/*	// 初始化上升按钮
	$('#data-table-user tbody').on('click', 'a.up', function(e) {
		e.preventDefault();
		var table = $('#data-table-user').DataTable();
		var index = table.row($(this).parents('tr')).index();
		if ((index - 1) >= 0) {
			var data = table.data();
			table.clear();
			data.splice((index - 1), 0, data.splice(index, 1)[0]);
			table.rows.add(data).draw();
		} else {
			alert("亲，已经到顶了");
		}

	});

	// 初始化下降按钮
	$('#data-table-user tbody').on('click', 'a.down', function(e) {
		e.preventDefault();

		var table = $('#data-table-user').DataTable();
		var index = table.row($(this).parents('tr')).index();
		var max = table.rows().data().length;
		if ((index + 1) < max) {
			var data = table.data();
			table.clear();
			data.splice((index + 1), 0, data.splice(index, 1)[0]);
			table.rows.add(data).draw();
		} else {
			alert("亲，已经到底了");
		}
	});*/
});