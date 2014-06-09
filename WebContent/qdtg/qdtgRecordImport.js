Ext.ns('KYB.QDTG');

KYB.QDTG.QdtgChannelImportPanel=function(){
	var fields=[{
		name: 'recordId',
		type: 'int'
	},{
		name: 'productName',
		type: 'string'
	},{
		name: 'channelNo',
		type: 'string'
	},{
		name: 'fileName',
		type: 'string'
	},{
		name: 'activateQt',
		type: 'int'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name: 'recordTime',
		type: 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	}];
	var store=new Ext.data.JsonStore({
		url: context+'/qdtg/record/queryQdtgRecordByInStatus.action',
		method: 'POST',
		autoDestroy: true,
		autoLoad: true,
		root: 'qdtgRecords',
		totalProperty: 'total',
		baseParams: {
			start: 0,
			limit: 20
		},
		fields: fields
	});
	var sm = new Ext.grid.CheckboxSelectionModel({singleSelect: true});
	var cm=new Ext.grid.ColumnModel({
		defaultSortable: true,
		defaults: {
			menuDisabled: true,
			width: 110
		},
		columns: [new Ext.grid.RowNumberer(),sm,{
			menuDisabled: false,
			header: '<div align="center">记录日期</div>',
			dataIndex: 'recordTime',
			align: 'center',
			renderer : formatDate
		},{
			header: '<div align="center">产品</div>',
			dataIndex: 'productName'
		},{
			header: '<div align="center">渠道号</div>',
			dataIndex: 'channelNo'
		},{
			header: '<div align="center">文件名</div>',
			dataIndex: 'fileName',
			width: 200
		},{
			header: '<div align="center">激活量</div>',
			dataIndex: 'activateQt',
			align: 'center'
		},{
			header: '<div align="center">录入时间</div>',
			dataIndex: 'createTime',
			width: 150,
			renderer : formatDateTime
		}]
	});
	var pageBar= createPageBar(store);
	var qdtgRecordGrid=new Ext.grid.GridPanel({
		title: '记录列表',
		id: 'qdtgRecordGrid',
		region: 'center',
		border: true,
		stripeRows: true,
		columnLines: true,
		loadMask: true,
		viewConfig : {autoFill:true},
		cm: cm,
		sm: sm,
		bbar: pageBar,
		store:store,
		tbar:[{
			text : '删除',
			iconCls : 'icon-delete',
			handler : doDel
		},'-',{
			text : '刷新',
			iconCls : 'icon-refresh',
			handler : function(){
				pageBar.doRefresh();
			}
		}]
	});
	var productCombox=new KYB.ProductCombox({allowBlank: false});
	var channelCombox=new KYB.ChannelCombox({allowBlank: false});
	//产品下拉框选中事件
	productCombox.on('select',function(combo, record, index){
		var productId=record.data.productId;
		if(productId){
			channelCombox.clearValue();
			var c_store=channelCombox.getStore();
			Ext.getCmp('fileName').setValue(null);
			c_store.proxy=new Ext.data.HttpProxy({
				url: context+'/qdtg/channel/queryAllQdtgChannel.action'
			});
			c_store.setBaseParam('productId',productId);
			c_store.load();
		}
	});
	//渠道下拉选中事件
	channelCombox.on('select',function(combo,record,index){
		Ext.getCmp('fileName').setValue(record.data.fileName);
		Ext.getCmp('activateQt').focus(true,true);
	});
	
	var qdtgRecordForm=new Ext.form.FormPanel({
		id: 'qdtgRecordForm',
		//title: '录入面板',
		monitorValid: true,
		buttonAlign: 'center',
		labelWidth: 100,
		width: 400,
		padding: 20,
		region: 'west',
		border: false,
		autoHeight: true,
		defaults: {width:140},
		items:[productCombox,channelCombox,{
			fieldLabel: '文件名',
			xtype: 'textfield',
			id: 'fileName',
			name: 'fileName'
		},{
			fieldLabel: '记录日期',
			xtype: 'datefield',
			format: 'Y-m-d',
			editable:false,
			id:'recordTime',
			name: 'recordTime',
			value: new Date()
		},{
			fieldLabel: '激活量',
			xtype: 'numberfield',
			allowBlank: false,
			width: 60,
			id: 'activateQt',
			name: 'activateQt',
			enableKeyEvents: true,
			listeners : {
				keyup : function(tf, e) {
					if (e.keyCode == 13) {
						doSave();
					}
				}
			}
		}],
		buttons:[{
			text: '保存',
			iconCls: 'icon-save',
			formBind: true,
			handler: doSave
		},{
			text: '重置',
			iconCls: 'icon-redo',
			handler: function(){
				Ext.getCmp('qdtgRecordForm').getForm().reset();
			}
		}]
	});
	
	function doSave(){
		//var formData=Ext.getCmp('qdtgRecordForm').getForm().getValues();
		var formData=Ext.getCmp('qdtgRecordForm').getForm().getFieldValues();
		//alert(Ext.encode(formData));
		//return ;
		Ext.Ajax.request({
			url: context + '/qdtg/record/saveOrUpdateQdtgRecord.action',
			method: 'POST',
			jsonData: {
				qdtgRecord: formData
			},
			callback: function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					pageBar.doRefresh();
					Ext.getCmp('qdtgRecordForm').getForm().reset();
					showInfoMsg("保存成功！");
				}
			}
		});	
	}
	function doDel(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/qdtg/record/deleteQdtgRecord.action',
			method : 'POST',
			jsonData : {
				qdtgRecords : encodeRecordsToJsonArray(records)
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
	KYB.QDTG.QdtgChannelImportPanel.superclass.constructor.call(this,{
		id: 'qdtgChannelImportPanel',
		title: '记录录入',
		border: false,
        frame: true,
        closable: true,
        //bodyStyle:'padding:5px',
        layout: 'border',
        items:[qdtgRecordForm,qdtgRecordGrid]
	});
	
};
Ext.extend(KYB.QDTG.QdtgChannelImportPanel,Ext.Panel);
Ext.reg('qdtgChannelImportPanel',KYB.QDTG.QdtgChannelImportPanel);
