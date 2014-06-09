Ext.ns('KYB.QDTG');

KYB.QDTG.QdtgRecordPanel=function(){
	var fields=[{
		name : 'recordId',
		type : 'int'
	},{
		name: 'productName',
		type: 'string'
	},{
		name : 'channelNo',
		type : 'string'
	},{
		name : 'fileName',
		type : 'string'
	},{
		name : 'cvsName',
		type : 'string'
	},{
		name : 'unitPrice',
		type : 'float'
	},{
		name: 'activateQt',
		type: 'int'
	},{
		name: 'activateQt_Net',
		type: 'int'
	},{
		name: 'totalPrice',
		type: 'float'
	},{
		name: 'totalPrice_Net',
		type: 'float'
	},{
		name: 'recordTime',
		type: 'date',
		dateFormat: 'Y-m-d\\TH:i:s'
	},{
		name: 'createTime',
		type: 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name: 'status',
		type: 'boolean'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/qdtg/record/queryQdtgRecord.action',
		method : 'POST',
		autoDestroy :true,
		//autoLoad : true,
		root : 'qdtgRecords',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 20
		},
		fields :fields
	});
	var tbar = [{
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	},'-',{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	},'-',{
		text: '审核',
		iconCls: 'icon-accept',
		handler: doEnter
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
			width: 110
		},
		columns : [new Ext.grid.RowNumberer(),sm,{
			menuDisabled: false,
			header: '<div align="center">日期</div>',
			dataIndex: 'recordTime',
			align: 'center',
			renderer : formatDate
		},{
			header : '<div align="center">产品</div>',
			dataIndex: 'productName'
		},{
			header : '<div align="center">渠道号</div>',
			dataIndex : 'channelNo'			
		},{
			header: '<div align="center">文件名</div>',
			width : 180,
			dataIndex : 'fileName'
		},{
			header : '<div align="center">客户</div>',
			align: 'right',
			dataIndex : 'cvsName'			
		},{
			header : '<div align="center">单价</div>',
			align: 'right',
			width:80,
			dataIndex : 'unitPrice'
		},{
			header: '<div align="center">激活</div>',
			align: 'right',
			dataIndex: 'activateQt',
			renderer: function(val){
				return '<span style="color:green;">'+val+'</span>'
			}
		},{
			header: '<div align="center">激活(扣量后)</div>',
			align: 'right',
			dataIndex: 'activateQt_Net',
			renderer: function(val){
				return '<span style="color:red;">'+val+'</span>'
			}
		},{
			header: '<div align="center">小计</div>',
			align: 'right',
			dataIndex: 'totalPrice',
			renderer: function(val){
				return '<span style="color:green;">'+formatNumber(val)+'</span>'
			}
		},{
			header: '<div align="center">小计(扣量后)</div>',
			align: 'right',
			dataIndex: 'totalPrice_Net',
			renderer: function(val){
				return '<span style="color:red;">'+formatNumber(val)+'</span>'
			}
		},{
			header: '<div align="center">状态</div>',
			align: 'center',
			width:80,
			dataIndex: 'status',
			renderer:function(v){
				return v ? '<span style="color:green;">已审核</span>' : 
				'<span style="color:red;">待审核</span>';
			}
		},{
			header: '<div align="center">录入时间</div>',
			dataIndex: 'createTime',
			//align: 'center',
			width:180,
			renderer : formatDateTime
		}]
	});
	var pageBar = createPageBar(store);
	
	var qdtgRecordSerchForm=new Ext.form.FormPanel({
		id: 'qdtgRecordSerchForm',
		padding: '10px 10px 5px 10px',
		region: 'north',
		height: 70,
		labelAlign: 'right',
		autoDestroy: true,
		layout:'column',
		tbar: tbar,
		items:[{
			columnWidth: .25,
			layout: 'form',
			border:false,
			items:[{
				xtype: 'productCombox',
				width: 120,
				listeners:{
					select:function(combo, record, index){
						var productId=record.data.productId;
						if(productId){
							var channelCombox=Ext.getCmp('qdtg_channelNo');
							channelCombox.clearValue();
							var c_store=channelCombox.getStore();
							c_store.proxy=new Ext.data.HttpProxy({
								url: context+'/qdtg/channel/queryAllQdtgChannel.action'
							});
							c_store.setBaseParam('productId',productId);
							c_store.load();
						}
					}
				}
			}]
		},{
			columnWidth: .25,
			layout: 'form',
			border:false,
			items:[{
				xtype: 'channelCombox',
				id: 'qdtg_channelNo',
				width: 120
			}]
		},{
			columnWidth: .25,
			layout: 'form',
			border:false,
			items:[{
				xtype: 'cvsCombox',
				width: 120
			}]
		},{
			columnWidth: .25,
			layout: 'form',
			border: false,
			items:[{
				xtype: 'datefield',
				format: 'Y-m-d',
				width: 120,
				name: 'recordTime',
				fieldLabel: '日期'
			}]
		}]
	});
	var qdtgRecordGrid=new Ext.grid.GridPanel({
		region: 'center',
		border: false,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		viewConfig: {
			autoFill:true
		},
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store,
		listeners:{
			viewready:function(){
				doSearch();
			}
		}
	});
	KYB.QDTG.QdtgRecordPanel.superclass.constructor.call(this,{
		title : '记录列表',
		id : 'qdtgRecordPanel',
		layout: 'border',
		border: false,
		closable : true,
		items: [qdtgRecordSerchForm,qdtgRecordGrid]
	});
	function doSearch(){
		var formData=util_urlDecode(Ext.getCmp('qdtgRecordSerchForm').getForm().getValues(true),true);
		store.baseParams=Ext.apply({start:0,limit:20},formData);
		store.load();
	}

	function doDel() {
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
	
	function doEnter(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return; 
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/qdtg/record/enterQdtgRecord.action',
			method : 'POST',
			jsonData : {
				qdtgRecords : encodeRecordsToJsonArray(records)
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					pageBar.doRefresh();
					showInfoMsg("审核成功！");
				}
			}
		});
	}
}
Ext.extend(KYB.QDTG.QdtgRecordPanel,Ext.Panel);
Ext.reg('qdtgRecordPanel',KYB.QDTG.QdtgRecordPanel);