Ext.ns('KYB');

KYB.RecordPanel=function(){
	var fields=[{
		name : 'recordId',
		type : 'int'
	},{
		name : 'recordType',
		type : 'string'
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
		name : 'createdAt',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'text',
		type : 'string'
	},{
		name : 'thumbnailPic',
		type : 'string'
	},{
		name : 'bmiddlePic',
		type : 'string'
	},{
		name : 'originalPic',
		type : 'string'
	},{
		name : 'recordStatus',
		type : 'int'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/capture/record/queryWbRecords.action',
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
	var wbRecordStatusCombo=new KYB.recordStatusCombo();
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled : true,
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					header : '状态',
					width : 50,
					dataIndex : 'recordStatus',
					menuDisabled:false,
					editor : wbRecordStatusCombo,
					renderer : comboRenderer(wbRecordStatusCombo)
				},{
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
					header : '创建时间',
					dataIndex : 'createdAt',
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
					header : '类型',
					width : 70,
					dataIndex : 'recordType'
				},{
					header : '缩略图',
					width : 120,
					dataIndex : 'thumbnailPic',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}
				},{
					header : '中等图',
					width : 120,
					dataIndex : 'bmiddlePic',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}
				},{
					header : '原始图',
					width : 120,
					dataIndex : 'originalPic',
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
				}]
	});
	var pageBar = createPageBar(store);
	KYB.RecordPanel.superclass.constructor.call(this,{
		title : '采集记录列表',
		id : 'wbRecordPanel',
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
		Ext.getCmp('recordPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('recordPanel').startEditing(0, 1);
	};

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/capture/record/deleteWbRecords.action',
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
		} else {
			showWarnMsg("没有选中任何记录，请选择。")
		}
	}
	
	function doSave() {
		var modfiyDs = store.getModifiedRecords();	
		if (!isNull(modfiyDs)) {
			Ext.Ajax.request({
				url : context
						+ '/capture/record/saveOrUpdateWbRecords.action',
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
Ext.extend(KYB.RecordPanel,Ext.grid.EditorGridPanel);
Ext.reg('wbRecordPanel',KYB.RecordPanel);