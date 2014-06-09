Ext.ns('KYB');

KYB.WbUserPanel=function(){
	var fields=[{
		name : 'userId',
		type : 'int'
	},{
		name : 'type',
		type : 'string'
	},{
		name : 'uid',
		type : 'string'
	},{
		name : 'screenName',
		type : 'string'
	},{
		name : 'feature',
		type : 'int'
	},{
		name : 'maxRecordId',
		type : 'string'
	},{
		name : 'limits',
		type : 'string'
	},{
		name : 'appId',
		type : 'string'
	},{
		name : 'status',
		type : 'boolean'
	}];
	
	var typeCombo=new Ext.form.ComboBox({
		triggerAction : 'all',
		typeAhead : true,
		lazyRender:true,
		mode : 'local',
		editable : false,
		allowBlank: false,
		hiddenName: 'type',
		valueField : 'value',
		displayField : 'name',
		store : {
			xtype : 'arraystore',
			fields : ['value','name'],
			data : [['1','新浪'],['2','腾讯']]
		}
	});
	
	var statusCombo=new Ext.form.ComboBox({
		triggerAction : 'all',
		typeAhead : true,
		lazyRender:true,
		mode : 'local',
		editable : false,
		allowBlank: false,
		hiddenName: 'status',
		valueField : 'value',
		displayField : 'name',
		store : {
			xtype : 'arraystore',
			fields : ['value','name'],
			data : [[false,'否'],[true,'是']]
		}
	});
	
	var featureCombo=new Ext.form.ComboBox({
		triggerAction : 'all',
		typeAhead : true,
		lazyRender:true,
		mode : 'local',
		editable : false,
		allowBlank: false,
		hiddenName: 'feature',
		valueField : 'value',
		displayField : 'name',
		store : {
			xtype : 'arraystore',
			fields : ['value','name'],
			data : [[0,'全部'],[1,'原创'],[2,'图片'],[3,'视频'],[4,'音乐']]
		}
	});
	
	var appCombo=new Ext.form.ComboBox({
		triggerAction : 'all',
		typeAhead : true,
		lazyRender:true,
		mode : 'local',
		editable : false,
		allowBlank: false,
		hiddenName: 'appId',
		valueField : 'value',
		displayField : 'name',
		store : {
			xtype : 'arraystore',
			fields : ['value','name'],
			data : [[null,'无'],['1','吊丝不寂寞']]
		}
	});
	
	var store =new Ext.data.JsonStore({
		url : context+'/captrue/wbuser/queryWbUsers.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'wbUsers',
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
			width : 100,
			menuDisabled : true,
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					header : '用户ID',
					dataIndex : 'uid',
					menuDisabled : false
				}, {
					header : '用户名',
					dataIndex : 'screenName'
				}, {
					header : '来源',
					dataIndex : 'type',
					width : 80,
					editor : typeCombo,
					renderer : comboRenderer(typeCombo)
				},{
					header : "过滤",
					dataIndex : 'feature',
					width : 80,
					editor : featureCombo,
					renderer : comboRenderer(featureCombo)
				},{
					header : '最大记录ID',
					dataIndex : 'maxRecordId'
				},{
					header : '单次拉取记录条数',
					width : 80,
					dataIndex : 'limits'
				},{
					header : '记录入库APP',
					dataIndex : 'appId',
					editor : appCombo,
					renderer : comboRenderer(appCombo)
				},{
					header : '是否启动',
					dataIndex :'status',
					width : 80,
					editor : statusCombo,
					renderer : comboRenderer(statusCombo)					
				}]
	});
	var pageBar = createPageBar(store);
	KYB.WbUserPanel.superclass.constructor.call(this,{
		title : '微博列表',
		id : 'wbUserPanel',
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
			feature : 2,
			maxRecordId : 0,
			limits : 20,
			status : false
		});
		Ext.getCmp('wbUserPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('wbUserPanel').startEditing(0, 1);
	};

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/captrue/wbuser/deleteWbUsers.action',
				method : 'POST',
				jsonData : {
					wbUsers : encodeRecordsToJsonArray(records)
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
						+ '/captrue/wbuser/saveOrUpdateWbUsers.action',
				method : 'POST',
				jsonData : {
					wbUsers : encodeRecordsToJsonArray(modfiyDs)
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
Ext.extend(KYB.WbUserPanel,Ext.grid.EditorGridPanel);
Ext.reg('wbUserPanel',KYB.WbUserPanel);