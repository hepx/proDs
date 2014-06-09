Ext.ns('KYB.SXD');

KYB.SXD.SxdVersionMainPanel=function(){
	var url_='/sxd/version/saveOrUpdateVersion.action';
	var fields=[{
		name : 'versionId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'internalVersion',
		type : 'int'
	},{
		name : 'version',
		type : 'string'
	},{
		name : 'versionDesc',
		type : 'string'
	},{
		name : 'apkPath',
		type : 'string'
	},{
		name : 'downLoads',
		type : 'int'
	}];
	
	var store = new Ext.data.JsonStore({
		url : context + '/sxd/version/queryVersion.action',
		method : 'POST',
		root : 'sxdVersions',
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
		text : '创建新版本',
		iconCls : 'icon-add',
		handler : doAdd
	}, '-', {
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	}, '-', {
		text : '修改',
		iconCls : 'icon-edit',
		handler : doModify
	}, '-',{
		text : '上传APK包',
		iconCls : 'icon-up',
		handler : uploadApk
	}, '-',{
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var ver_expander = new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>版本号:</b> {version}</p><br>',
            '<p><b>更新说明:</b> {versionDesc}</p>'
        )
    });
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaultWidth : 150,
		columns : [ver_expander,{
			header : '<div align=center>创建人</div>',
			dataIndex : 'createUser'
		}, {
			header : '<div align=center>创建时间</div>',
			align : 'right',
			dataIndex : 'createTime',
			renderer : formatDateTime
		}, {
			header : '<div align=center>内部版本号</div>',
			align : 'center',
			dataIndex : 'internalVersion'
		}, {
			header : '<div align=center>外部版本号</div>',
			align : 'center',
			dataIndex : 'version'
		}, {
			header : '<div align=center>APK</div>',
			align : 'right',
			width : 200,
			dataIndex : 'apkPath',
			renderer : linkRenderer
		}, {
			header : '<div align=center>下载次数</div>',
			align : 'center',
			dataIndex : 'downLoads'
		}]
	});
	var sm = new Ext.grid.RowSelectionModel();
	var pageBar = createPageBar(store);
	KYB.SXD.SxdVersionMainPanel.superclass.constructor.call(this, {
		title : 'SXD版本管理',
		id : 'sxdVersionMainPanel',
		autoScroll : true,
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		//viewConfig : {autoFill:true},
		tbar : tbar,
		bbar : pageBar,
		plugins: ver_expander,
		cm : cm,
		sm : sm,
		store : store
	});

	function refresh() {
		pageBar.doRefresh();
	}

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/version/deleteVersion.action',
				method : 'POST',
				jsonData : {
					sxdVersions : encodeRecordsToJsonArray(records)
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
		}else{
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};

	function doAdd() {
		new KYB.SXD.SxdVersionAddorUpWin(
			{title : '创建版本信息'}, 
			null,
			url_, 
			refresh
		).show();
	};

	function doModify() {
		var ds = sm.getSelected();
		if (ds) {
			new KYB.SXD.SxdVersionAddorUpWin(
				{title : '修改版本信息'}, 
				ds.data,
				url_, 
				refresh
			).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};
	
	function uploadApk(){
		var ds = sm.getSelected();
		if (ds) {
			var url=context+'/sxd/version/uploadApk.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'versionId',
					value : ds.data.versionId
				},{
					xtype : 'textfield',
					hidden : true,
					name : 'version',
					value : ds.data.version
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-image-apk',
		            emptyText: '选择一个小于10M的APK安装文件',
		            fieldLabel: '安装文件',
		            name: 'apkFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传APK",ds.data,url, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}	
	}
}
Ext.extend(KYB.SXD.SxdVersionMainPanel,Ext.grid.GridPanel);
Ext.reg('sxdVersionMainPanel',KYB.SXD.SxdVersionMainPanel);