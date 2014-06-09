Ext.ns('KYB.BDG');

KYB.BDG.BdgArRecordPanel=function(){
	var fields=[{
		name : 'recordId',
		type : 'int'
	},{
		name : 'recordSource',
		type : 'string'
	},{
		name : 'source',
		type : 'string'
	},{
		name : 'uid',
		type : 'string'
	},{
		name : 'id',
		type : 'string'
	},{
		name : 'screenName',
		type : 'string'
	},{
		name : 'location',
		type : 'string'
	},{
		name : 'releaseTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'text',
		type : 'string'
	},{
		name : 'pic',
		type : 'string'
	},{
		name : 'tops',
		type : 'int'
	},{
		name : 'treads',
		type : 'int'
	},{
		name : 'collects',
		type : 'int'
	},{
		name : 'shares',
		type : 'int'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/bdg/arrecord/queryBdgArRecords.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'records',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 20
		},
		fields :fields
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
		text : '保存',
		iconCls : 'icon-save',
		handler : doSave
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : function(){
			pageBar.doRefresh();
		}
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled : true,
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					menuDisabled : false,
					header : '来自',
					width : 80,
					dataIndex : 'recordSource'
				},{
					header : '用户名',
					width : 100,
					dataIndex : 'screenName'
				},{
					header : '用户ID',
					width : 80,
					dataIndex : 'uid'
				},{
					header : '地理位置',
					width : 80,
					dataIndex : 'location'
				},{
					header : '发布时间',
					dataIndex : 'releaseTime',
					width : 130,
					editor : new Ext.form.DateField({
							format : 'Y-m-d H:i:s',
							editable : false
						}),
					renderer : formatDateTime
				},{
					header : '记录ID',
					width : 130,
					dataIndex : 'id'
				},{
					header : '内容',
					width : 200,
					dataIndex : 'text'
				},{
					header : '图片',
					width : 120,
					dataIndex : 'pic',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}
				},{
					header : "内容来源",
					width : 150,
					dataIndex : 'source',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}					
				},{
					header : '顶',
					width : 50,
					dataIndex : 'tops'
				},{
					header : '踩',
					width : 50,
					dataIndex : 'treads'
				},{
					header : '收藏',
					width : 50,
					dataIndex : 'collects'
				},{
					header : '分享',
					width : 50,
					dataIndex : 'shares'
				}]
	});
	var pageBar = createPageBar(store);
	KYB.BDG.BdgArRecordPanel.superclass.constructor.call(this,{
		title : '发布后记录',
		id : 'bdgArRecordPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store
	});

	function doAdd(){
		var rowR =store.recordType;
		var r = new rowR({
			createdAt : new Date()
		});
		Ext.getCmp('bdgArRecordPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('bdgArRecordPanel').startEditing(0, 1);
	};

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/bdg/arrecord/deleteBdgArRecords.action',
			method : 'POST',
			jsonData : {
				records : encodeRecordsToJsonArray(records)
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
	
	function doSave() {
		var modfiyDs = store.getModifiedRecords();	
		if (!isNull(modfiyDs)) {
			Ext.Ajax.request({
				url : context
						+ '/bdg/arrecord/saveOrUpdateBdgArRecords.action',
				method : 'POST',
				jsonData : {
					records : encodeRecordsToJsonArray(modfiyDs)
				},
				callback : function(opts, suc, res) {
					var ds = Ext.decode(res.responseText);
					if (ds.errorMessage) {
						showErrorMsg(ds.errorMessage);
					} else {
						store.commitChanges();
						store.reload();
						showInfoMsg("保存成功！");
					}
				}
			});
		}
	}
}
Ext.extend(KYB.BDG.BdgArRecordPanel,Ext.grid.EditorGridPanel);
Ext.reg('bdgArRecordPanel',KYB.BDG.BdgArRecordPanel);