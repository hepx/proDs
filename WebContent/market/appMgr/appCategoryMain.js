Ext.ns('KYB.MARKET');

KYB.MARKET.AppCategoryPanel=function(){
	var fields=[{
		name : 'categoryId',
		type : 'int'
	},{
		name : 'categoryName',
		type : 'string'
	},{
		name : 'categoryValue',
		type : 'string'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/market/category/queryAppCategorys.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'appCategorys',
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
			menuDisabled:true,
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					menuDisabled:false,
					header : '<div align="center">分类名称</div>',
					width : 120,
					dataIndex : 'categoryName'			
				},{
					header : '<div align="center">分类赋值</div>',
					width : 120,
					dataIndex : 'categoryValue'
				}]
	});
	var pageBar = createPageBar(store);
	KYB.MARKET.AppCategoryPanel.superclass.constructor.call(this,{
		title : '应用分类管理',
		id : 'appCategoryPanel',
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
		var r = new rowR({});
		Ext.getCmp('appCategoryPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('appCategoryPanel').startEditing(0, 1);
	};

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return ;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/market/category/deleteAppCategorys.action',
			method : 'POST',
			jsonData : {
				appCategorys : encodeRecordsToJsonArray(records)
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
						+ '/market/category/saveOrUpdateAppCategorys.action',
				method : 'POST',
				jsonData : {
					appCategorys : encodeRecordsToJsonArray(modfiyDs)
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
Ext.extend(KYB.MARKET.AppCategoryPanel,Ext.grid.EditorGridPanel);
Ext.reg('appCategoryPanel',KYB.MARKET.AppCategoryPanel);