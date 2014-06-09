Ext.ns('KYB.SXD');

KYB.SXD.WeiboPanel=function(){
	var fields=[{
		name : 'wbId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'wbName',
		type : 'string'
	},{
		name : 'wbDesc',
		type : 'string'
	},{
		name : 'wbUrl',
		type : 'string'
	}];
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/wb/queryBuddhaWeibos.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'buddhaWeibos',
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
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
			header : '创建人',
			width : 100,
			dataIndex : 'createUser',
			editor : new Ext.form.TextField({
				readOnly:true
			})
		},{
			header : '创建时间',
			dataIndex : 'createTime',
			width : 150,
			editor : new Ext.form.DateField({
					format : 'Y-m-d H:i:s',
					editable : false
				}),
			renderer : formatDateTime
		},{
			header : "用户名",
			width : 200,
			dataIndex : 'wbName'
		},{
			header : '大师简介',
			width : 400,
			dataIndex : 'wbDesc'
		},{
			header : '微地址',
			width : 300,
			dataIndex : 'wbUrl',
			renderer : function(val){
				if(val){
					return '<a href="'+val+'" target="_blank">'+val+'</a>'
				}
			}
		}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.WeiboPanel.superclass.constructor.call(this,{
		title : '大师微博',
		id : 'weiboPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		tbar : tbar,
		cm : cm,
		sm : sm,
		viewConfig : {
			autoFill :true
		},
		bbar : pageBar,
		store : store
	});

	function doAdd(){
		var rowR =store.recordType;
		var r = new rowR({
			createUser:USER_NAME,
			createTime : new Date()
		});
		Ext.getCmp('weiboPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('weiboPanel').startEditing(0, 3);
	};

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/wb/deleteBuddhaWeibos.action',
				method : 'POST',
				jsonData : {
					buddhaWeibos : encodeRecordsToJsonArray(records)
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
						+ '/sxd/wb/saveOrUpdateBuddhaWeibos.action',
				method : 'POST',
				jsonData : {
					buddhaWeibos : encodeRecordsToJsonArray(modfiyDs)
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
Ext.extend(KYB.SXD.WeiboPanel,Ext.grid.EditorGridPanel);
Ext.reg('weiboPanel',KYB.SXD.WeiboPanel);