Ext.ns('KYB.SXD');

KYB.SXD.UserMainPanel = function() {
	var url_='/sxd/user/saveOrUpdateUsers.action';
	var fields = [{
		name : 'userId',
		type : 'int'
	}, {
		name : 'xyNo',
		type : 'string'
	}, {
		name : 'realName',
		type : 'string'
	}, {
		name : 'sex',
		type : 'string'
	}, {
		name : 'age',
		type : 'int'
	}, {
		name : 'mobile',
		type : 'string'
	}, {
		name : 'qq',
		type : 'string'
	}, {
		name : 'city',
		type : 'string'
	}, {
		name : 'email',
		type : 'string'
	}, {
		name : 'avatar',
		type : 'string'
	}, {
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	}];
	var store = new Ext.data.JsonStore({
		url : context + '/sxd/user/queryUsers.action',
		method : 'POST',
		root : 'users',
		totalProperty : 'total',
		autoLoad : true,
		autoDestroy : true,
		baseParams: {
			start : 0,
			limit : 20
		},
		fields : fields
	});
	var tbar = [{
		text : '注册',
		iconCls : 'icon-add',
		handler : doRegister
	}, '-', {
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	}, '-', {
		text : '修改',
		iconCls : 'icon-edit',
		handler : doModify
	}, '-',{
		text : '上传头像',
		iconCls : 'icon-img',
		handler : uploadAuatar
	}, '-',{
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaultWidth : 150,
		columns : [sm,{
			header : '<div align=center>香缘号</div>',
			width : 100,
			dataIndex : 'xyNo'
		}, {
			header : '<div align=center>真实姓名</div>',
			dataIndex : 'realName'
		}, {
			header : '<div align=center>姓别</div>',
			align : 'center',
			width : 50,
			dataIndex : 'sex'
		}, {
			header : '<div align=center>年龄</div>',
			align : 'center',
			width : 50,
			dataIndex : 'age'
		}, {
			header : '<div align=center>联系方式</div>',
			dataIndex : 'mobile'
		}, {
			header : '<div align=center>QQ号码</div>',
			dataIndex : 'qq'
		}, {
			header : '<div align=center>所在城市</div>',
			dataIndex : 'city'
		}, {
			header : '<div align=center>邮箱地址</div>',
			dataIndex : 'email'
		}, {
			header : '<div align=center>头像</div>',
			dataIndex : 'avatar',
			width : 80,
			renderer : imageRenderer
		}, {
			header : '<div align=center>创建时间</div>',
			align : 'right',
			dataIndex : 'createTime',
			renderer : formatDateTime
		}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.UserMainPanel.superclass.constructor.call(this, {
		title : 'SXD用户管理',
		id : 'sxdUserMainPanel',
		autoScroll : true,
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		viewConfig : {autoFill:true},
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store
	});

	function refresh() {
		pageBar.doRefresh();
	}

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Msg.confirm("提示：", "确定要删除选 中的用户吗？", function(btn) {
				if (btn == "yes") {
					Ext.Ajax.request({
						url : context + '/sxd/user/deleteUsers.action',
						method : 'POST',
						jsonData : {
							users : encodeRecordsToJsonArray(records)
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showErrorMsg(ds.errorMessage);
							} else {
								showInfoMsg("删除成功！");
								store.remove(records);
							}
						}
					});
				}
			});
		}else{
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};

	function doRegister() {
		Ext.Ajax.request({
			url : context + '/sxd/phone/user/register.action',
			method : 'POST',
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					showInfoMsg("注册成功！");
					refresh();
				}
			}			
		});
	};

	function doModify() {
		var ds = sm.getSelected();
		if (ds) {
			new KYB.SXD.UserUpWin(
				{title : '修改用户信息'}, 
				ds.data,
				url_, 
				refresh
			).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};
	
	function uploadAuatar(){
		var ds = sm.getSelected();
		if (ds) {
			var url=context+'/sxd/phone/user/uploadAuatar.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'userId',
					value : ds.data.userId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-image-auatar',
		            emptyText: '选择一张小于20KB的JPG、PNG、GIF格式的图片',
		            fieldLabel: '图片',
		            name: 'auatarFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传头像",ds.data,url, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}	
	}
}
Ext.extend(KYB.SXD.UserMainPanel, Ext.grid.GridPanel);
Ext.reg('sxdUserMainPanel', KYB.SXD.UserMainPanel)
