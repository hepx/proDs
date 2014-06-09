Ext.ns('KYB.SXD');

KYB.SXD.LorePanel=function(){
	var url_='/sxd/lore/saveOrUpdateBuddhismLores.action';
	var fields=[{
		name : 'loreId',
		type : 'int'
	},{
		name : 'loreType',
		type : 'string'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'loreTitle',
		type : 'string'
	},{
		name : 'loreContent',
		type : 'string'
	}];
	
	var loreTypeCombox=new KYB.SXD.LoreTypeCombox();
	
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/lore/queryBuddhismLores.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'buddhismLores',
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
		text : '修改',
		iconCls : 'icon-edit',
		handler : doModify
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	
	var expander = new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>标题:</b> {loreTitle}</p><br>',
            '<p><b>详细内容:</b> {loreContent}</p>'
        )
    });
	
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		columns : [expander,{
					header : '创建人',
					width : 100,
					dataIndex : 'createUser'
				}, {
					header : '创建时间',
					dataIndex : 'createTime',
					width : 150,
					renderer : formatDateTime
				}, {
					header : '佛学常识类型',
					width : 150,
					dataIndex : 'loreType',
					renderer : comboRenderer(loreTypeCombox)
				},{
					header : "佛学常识标题",
					width : 300,
					id:'loreTitle',
					dataIndex : 'loreTitle'
				}]
	});
	var sm = new Ext.grid.RowSelectionModel();
	var pageBar = createPageBar(store);
	KYB.SXD.LorePanel.superclass.constructor.call(this,{
		title : '佛学常识列表',
		id : 'lorePanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoExpandColumn : 'loreTitle',
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		plugins: expander,
		store : store
	});
	
	function refresh(){
		pageBar.doRefresh();
	}
	
	function doAdd(){
		new KYB.SXD.LoreAddOrUpWin(
			{title : '创建佛学常识'}, 
			null,
			url_, 
			refresh
		).show();
	};
	
	function doModify(){
		var ds = sm.getSelections()[0];
		if (ds) {
			new KYB.SXD.LoreAddOrUpWin({
						title : '修改佛学常识'
					}, ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/lore/deleteBuddhismLores.action',
				method : 'POST',
				jsonData : {
					buddhismLores : encodeRecordsToJsonArray(records)
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
						+ '/sxd/lore/saveOrUpdateBuddhismLores.action',
				method : 'POST',
				jsonData : {
					buddhismLores : encodeRecordsToJsonArray(modfiyDs)
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
Ext.extend(KYB.SXD.LorePanel,Ext.grid.GridPanel);
Ext.reg('lorePanel',KYB.SXD.LorePanel);