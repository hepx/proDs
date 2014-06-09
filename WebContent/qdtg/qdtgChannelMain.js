Ext.ns('KYB.QDTG');

KYB.QDTG.QdtgChannelPanel=function(){
	var fields=[{
		name : 'channelId',
		type : 'int'
	},{
		name : 'channelNo',
		type : 'string'
	},{
		name : 'fileName',
		type : 'string'
	},{
		name: 'cvsId',
		type: 'int',
		useNull: true
	},{
		name: 'cvsName',
		type: 'string'
	},{
		name: 'productId',
		type: 'int'
	},{
		name: 'productName',
		type: 'string'
	},{
		name : 'unitPrice',
		type : 'float'
	},{
		name: 'proportion',
		type: 'floay'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/qdtg/channel/queryQdtgChannel.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'qdtgChannels',
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
			width:120,
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					menuDisabled:false,
					header: '<div align="center">产品名</div>',
					dataIndex: 'productName'
				},{
					header : '<div align="center">渠道号</div>',
					dataIndex : 'channelNo'			
				},{
					header : '<div align="center">文件名</div>',
					width : 200,
					dataIndex : 'fileName'
				},{
					header : '<div align="center">单价</div>',
					dataIndex : 'unitPrice'
				},{
					header: '<div align="center">扣量比例</div>',
					align: 'center',
					dataIndex: 'proportion',
					renderer: function(val){
            			return '<span style="color:green;">' + (val*100).toFixed(0) + '%</span>';
					}
				},{
					header: '<div align="center">归属客户</div>',
					dataIndex: 'cvsName',
					align: 'center'
				}]
	});
	var pageBar = createPageBar(store);
	KYB.QDTG.QdtgChannelPanel.superclass.constructor.call(this,{
		title : '渠道列表',
		id : 'qdtgChannelPanel',
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

	function addOrUpdateQdtgChannelWin(title,record){
		var cvsCombox=new KYB.CvsCombox({anchor: '60%',editable:false});
		var productCombox=new KYB.ProductCombox({anchor: '60%'});
		var items=[productCombox,{
			xtype: 'textfield',
			fieldLabel:'渠道号',
			name: 'channelNo',
			allowBlank: false,
			maxLength: 50,
			anchor:'80%'
		},{
			xtype: 'textfield',
			fieldLabel: '文件名',
			name: 'fileName',
			anchor:'80%',
			allowBlank: false,
			maxLength: 100
		},{
			xtype: 'numberfield',
			fieldLabel: '单价',
			name: 'unitPrice',
			anchor:'50%',
			allowBlank: false,
			maxLength: 5
		},{
			xtype: 'numberfield',
			fieldLabel: '扣量比例',
			name: 'proportion',
			anchor: '50%',
			value:0
		},cvsCombox];
		new Ext.Window({
			id:'qdtgChannelWin',
			title:title,
			width:350,
			resizable: false,
			modal: true,
			constrain: true,
			autoHeight:true,
			items:[{
				id: 'qdtgChannelForm',
				xtype:'form',
				padding:10,
				labelWidth :70,
				border: false,
				monitorValid : true,
				items:items,
				buttons:[{
					text:'保存',
					iconCls : 'icon-save',
					formBind : true,
					handler : function(){
						var formData=Ext.getCmp('qdtgChannelForm').getForm().getValues();
						if(formData.cvsId===''){
							formData.cvsId=null;
						}
						Ext.Ajax.request({
							url : context + '/qdtg/channel/saveOrUpdateQdtgChannel.action',
							method : 'POST',
							jsonData : {
								qdtgChannel : record ? Ext.apply(record.data,formData) : formData
							},
							callback : function(opts, suc, res) {
								var ds = Ext.decode(res.responseText);
								if (ds.errorMessage) {
									showErrorMsg(ds.errorMessage);
								} else {
									showInfoMsg(title+"成功！");
									if(record){
										record.set('channelNo',formData.channelNo);
										record.set('fileName',formData.fileName);
										record.set('cvsName',cvsCombox.getRawValue());
										record.set('productName',productCombox.getRawValue());
										record.set('unitPrice',formData.unitPrice);
										record.set('proportion',formData.proportion);
										record.commit();
									}else{
										pageBar.doRefresh();
									}
									Ext.getCmp('qdtgChannelWin').close();
								}
							}
						});
					}
				},{
					text: '重置',
					iconCls : 'icon-redo',
					handler : function(){
						Ext.getCmp('qdtgChannelForm').getForm().reset();
					}
				}],
				listeners : {
					afterrender : function() {
						if(record){
							Ext.getCmp('qdtgChannelForm').getForm().setValues(record.data);
						}
					}
				}
			}]
		}).show();
	}
	
	function doAdd(){
		addOrUpdateQdtgChannelWin('增加渠道信息',null);
	}
	
	function doModify(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var re=sm.getSelected();
		addOrUpdateQdtgChannelWin("修改渠道信息",re)
	}

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/qdtg/channel/deleteQdtgChannel.action',
			method : 'POST',
			jsonData : {
				qdtgChannels : encodeRecordsToJsonArray(records)
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
};
Ext.extend(KYB.QDTG.QdtgChannelPanel,Ext.grid.GridPanel);
Ext.reg('qdtgChannelPanel',KYB.QDTG.QdtgChannelPanel);