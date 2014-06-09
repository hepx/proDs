Ext.ns('KYB.SXD');

KYB.SXD.DictMainPanel=function(){
	var url_='/sxd/dict/saveOrUpdateDicts.action';
	var fields=[{
		name : 'dictId',
		type : 'int'
	},{
		name : 'dictGroup',
		type : 'string'
	},{
		name : 'dictName',
		type : 'string'
	},{
		name : 'dictValue',
		type : 'string'
	},{
		name : 'iconPath',
		type : 'string'
	},{
		name : 'dictDesc',
		type : 'string'
	},{
		name : 'sort',
		type : 'int'
	}];
	
	var reader = new Ext.data.JsonReader({
		root : 'dicts',
		fields: fields
	})
	var store = new Ext.data.GroupingStore({
		reader: reader,
		url : context+'/sxd/dict/queryAllDict.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		groupField : 'dictGroup'
	});
	var tbar = [{
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},'-',{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	},'-',{
		text : '修改',
		iconCls : 'icon-edit',
		handler : doModify
	},'-',{
		text : '上传图标',
		iconCls : 'icon-img',
		tooltip : '用图标来表示分类',
		handler : doUploadIcon
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : function(){
			doRefresh(store);
		}
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults :{
			width:200
		},
		columns : [sm,{
					header : '字典组',
					dataIndex : 'dictGroup'
				}, {
					header : '字典名',
					dataIndex : 'dictName'
				}, {
					header : '字典值',
					dataIndex : 'dictValue'
				},{
					header : '图标',
					width : 70,
					dataIndex : 'iconPath',
					renderer : imageRenderer
				},{
					header : "描述",
					width : 300,
					id : 'dictDesc',
					dataIndex : 'dictDesc'
				},{
					header : '排序',
					width : 50,
					dataIndex : 'sort'
				}]
	});
	KYB.SXD.DictMainPanel.superclass.constructor.call(this,{
		title : 'APP字典管理',
		id : 'sxdDictPanel',
		//collapsible: true,
        animCollapse: false,
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoExpandColumn : 'dictDesc',
		tbar : tbar,
		cm : cm,
		sm : sm,
		store : store,
		view: new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {["项"]})'
        })
	});

	function refresh(){
		doRefresh(store);
	};
	
	function doAdd(){
		new KYB.SXD.DictAddorUpWin(
		{title : '增加字典'},
		null,
		url_,
		refresh
		).show();
	};
	
	function doModify(){
		var ds = sm.getSelected();
		if (ds) {
			new KYB.SXD.DictAddorUpWin(
			{title : '修改字典'}, 
			ds.data,
			url_, 
			refresh
			).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/dict/deleteDicts.action',
				method : 'POST',
				jsonData : {
					dicts : encodeRecordsToJsonArray(records)
				},
				callback : function(opts, suc, res) {
					var ds = Ext.decode(res.responseText);
					if (ds.errorMessage) {
						showErrorMsg(ds.errorMessage);
					} else {
						store.remove(records);
						showInfoMsg("删除成功！");
					}
				}
			});
		} else {
			showWarnMsg("没有选中任何记录，请选择。")
		}
	}
	
	function doUploadIcon(){
		var ds = sm.getSelected();
		if (ds) {
			var url=context+'/sxd/dict/uploadIcon.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'dictId',
					value : ds.data.dictId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-image-dict',
		            emptyText: '选择一张小于100KB的PNG格式的图片',
		            fieldLabel: '图片',
		            name: 'dictFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传图片",ds.data,url, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
}
Ext.extend(KYB.SXD.DictMainPanel,Ext.grid.GridPanel);
Ext.reg('sxdDictPanel',KYB.SXD.DictMainPanel);