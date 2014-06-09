Ext.ns('KYB.QDTG');

KYB.QDTG.QdtgProductPanel=function(){
	var fields=[{
		name : 'productId',
		type : 'int'
	},{
		name : 'productName',
		type : 'string'
	},{
		name : 'company',
		type : 'string'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/qdtg/product/queryQdtgProduct.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'qdtgProducts',
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
					header : '<div align="center">产品名称</div>',
					width : 120,
					dataIndex : 'productName'			
				},{
					header : '<div align="center">所属公司</div>',
					width : 200,
					dataIndex : 'company'
				}]
	});
	var pageBar = createPageBar(store);
	KYB.QDTG.QdtgProductPanel.superclass.constructor.call(this,{
		title : '产品列表',
		id : 'qdtgProductPanel',
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

	function addOrUpdateQdtgProductWin(title,record){
		new Ext.Window({
			id:'qdtgProductWin',
			title:title,
			width:350,
			resizable: false,
			modal: true,
			constrain: true,
			autoHeight:true,
			items:[{
				id: 'qdtgProductForm',
				xtype:'form',
				padding:10,
				labelWidth :70,
				border: false,
				monitorValid : true,
				items:[{
					xtype: 'textfield',
					fieldLabel:'产品名称',
					name: 'productName',
					allowBlank: false,
					maxLength: 30,
					anchor:'80%'
				},{
					xtype: 'textfield',
					fieldLabel: '所属公司',
					name: 'company',
					anchor:'80%',
					maxLength: 100
				}],
				buttons:[{
					text:'保存',
					iconCls : 'icon-save',
					formBind : true,
					handler : function(){
						var formData=Ext.getCmp('qdtgProductForm').getForm().getValues();
						Ext.Ajax.request({
							url : context + '/qdtg/product/saveOrUpdateQdtgProduct.action',
							method : 'POST',
							jsonData : {
								qdtgProduct : record ? Ext.apply(record.data,formData) : formData
							},
							callback : function(opts, suc, res) {
								var ds = Ext.decode(res.responseText);
								if (ds.errorMessage) {
									showErrorMsg(ds.errorMessage);
								} else {
									showInfoMsg(title+"成功！");
									if(record){
										record.set('productName',formData.productName);
										record.set('company',formData.company);
										record.commit();
									}else{
										pageBar.doRefresh();
									}
									Ext.getCmp('qdtgProductWin').close();
								}
							}
						});
					}
				},{
					text: '重置',
					iconCls : 'icon-redo',
					handler : function(){
						Ext.getCmp('qdtgProductForm').getForm().reset();
					}
				}],
				listeners : {
					afterrender : function() {
						if(record){
							Ext.getCmp('qdtgProductForm').getForm().setValues(record.data);
						}
					}
				}
			}]
		}).show();
	}
	
	function doAdd(){
		addOrUpdateQdtgProductWin('增加产品信息',null);
	}
	
	function doModify() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var re=sm.getSelected();
		addOrUpdateQdtgProductWin('修改产品信息',re)
	}

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/qdtg/product/deleteQdtgProduct.action',
			method : 'POST',
			jsonData : {
				qdtgProducts : encodeRecordsToJsonArray(records)
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
}
Ext.extend(KYB.QDTG.QdtgProductPanel,Ext.grid.GridPanel);
Ext.reg('qdtgProductPanel',KYB.QDTG.QdtgProductPanel);