Ext.ns('KYB.QDTG');

KYB.QDTG.StatQdtgRecordPanel=function(){
	var date=new Date();
	var fields=[{
		name: 'productName',
		type: 'string'
	},{
		name : 'channelNo',
		type : 'string'
	},{
		name : 'fileName',
		type : 'string'
	},{
		name: 'cvsName',
		type: 'string'
	},{
		name : 'unitPrice',
		type : 'float'
	},{
		name: 'activateQt_Net',
		type: 'int'
	},{
		name: 'activateQt',
		type: 'int'
	},{
		name: 'totalPrice_Net',
		type: 'float'
	},{
		name: 'totalPrice',
		type: 'float'
	},{
		name: 'recordTime',
		type: 'string'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/qdtg/record/statQdtgRecord.action',
		method : 'POST',
		autoDestroy :true,
		//autoLoad : true,
		root : 'statRecords',
//		totalProperty: 'total',
//		baseParams: {
//			start : 0,
//			limit : 20
//		},
		fields :fields
	});
	var tbar = [{
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : function(){
			statQdtgRecordSerchForm.getForm().reset();
			doSearch();
		}
	},'-',{
		text: '导出对帐单',
		iconCls: 'icon-export-excel',
		handler: doExprot
	}];
	var sm = new Ext.grid.RowSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled:true,
			width: 110
		},
		columns : [new Ext.grid.RowNumberer({width:28}),{
			menuDisabled:false,
			header: '<div align="center">日期</div>',
			dataIndex: 'recordTime',
			align:'center'
		},{
			header : '<div align="center">产品</div>',
			dataIndex: 'productName'
		},{
			header : '<div align="center">渠道号</div>',
			dataIndex : 'channelNo'			
		},{
			header: '<div align="center">文件名</div>',
			width : 200,
			dataIndex : 'fileName'
		},{
			header : '<div align="center">客户</div>',
			align: 'right',
			dataIndex : 'cvsName'			
		},{
			header : '<div align="center">单价</div>',
			align: 'right',
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
		}]
	});
	//var pageBar = createPageBar(store);
	var day_field=[{
		columnWidth: .25,
		layout: 'form',
		id:'stat_startTime',
		border: false,
		items:[{
			xtype: 'datefield',
			format: 'Y-m-d',
			width: 120,
			name:'startTime',
			fieldLabel: '开始日期',
			value: date.getFirstDateOfMonth()
		}]
	},{
		columnWidth: .25,
		layout: 'form',
		id:'stat_endTime',
		border: false,
		items:[{
			xtype: 'datefield',
			format: 'Y-m-d',
			width: 120,
			name: 'endTime',
			fieldLabel: '结束日期',
			value: date.getLastDateOfMonth()
		}]
	}];
	var month_field={
		columnWidth: .25,
		layout: 'form',
		id: 'stat_month',
		border:false,
		items:[{
		 	xtype: 'datefield',
			name: 'month',
            fieldLabel: '日期',
            plugins: 'monthPickerPlugin',
            format: 'Y-m',
            editable: false,
            value:date
        }]
	};
	var statQdtgRecordSerchForm=new Ext.form.FormPanel({
		id: 'statQdtgRecordSerchForm',
		padding: '10px 10px 5px 10px',
		labelAlign: 'right',
		autoDestroy: true,
		region:'north',
		height:103,
		layout:'column',
		defaults:{
			padding:'0px 0px 5px 0px'
		},
		tbar : tbar,
		items:[{
			columnWidth: .25,
			layout: 'form',
			border: false,
			items:[{
				xtype: 'combo',
				fieldLabel:'统计类型',
				hiddenName : 'statType',
				width: 120,
				mode:'local',
				valueField : 'value',
				displayField : 'name',
				typeAhead : true,
				lazyRender : true,
				triggerAction:'all',
				value: 1,
				store : {
					xtype : 'arraystore',
					fields : ['value','name'],
					data : [[1,'按日统计'],[2,'按月统计']]
				},
				listeners:{
					select: function( combo, record, index){
						var statType=record.data.value;
						var fm=Ext.getCmp('statQdtgRecordSerchForm');
						if(statType==1&&fm.getForm().items.length!=7){//按日
							fm.remove('stat_month');
							fm.add(day_field);
							fm.doLayout();
						}else if(statType==2&&fm.getForm().items.length!=6){//按月
							fm.remove('stat_startTime');
							fm.remove('stat_endTime');
							fm.add(month_field);
							fm.doLayout();
						}
					}
				}
			}]
		},{
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
							var channelCombox=Ext.getCmp('qdtg_stat_channelNo');
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
				id: 'qdtg_stat_channelNo',
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
			items: [{
				xtype: 'combo',
				fieldLabel:'状态',
				hiddenName : 'status',
				width: 120,
				mode:'local',
				valueField : 'value',
				displayField : 'name',
				typeAhead : true,
				lazyRender : true,
				triggerAction:'all',
				value: true,
				store : {
					xtype : 'arraystore',
					fields : ['value','name'],
					data : [[true,'审核通过'],[false,'审核未通过']]
				}
			}]
		},day_field]
	});
	
	var statQdtgRecordGrid=new Ext.grid.GridPanel({
		region:'center',
		stripeRows : true,
		columnLines : true,
		border: false,
		loadMask : true,
		viewConfig: {autoFill:true},
		cm : cm,
		sm : sm,
		//bbar : pageBar,
		store : store,
		listeners:{
			viewready:function(){
				doSearch();
			}
		}
	});
	
	KYB.QDTG.StatQdtgRecordPanel.superclass.constructor.call(this,{
		title: '记录统计',
		border: false,
		id : 'statQdtgRecordPanel',
		closable : true,
		layout:'border',
		items:[statQdtgRecordSerchForm,statQdtgRecordGrid]
	});

	function doSearch(){
		var formData=util_urlDecode(Ext.getCmp('statQdtgRecordSerchForm').getForm().getValues(true),true);	
		store.baseParams=Ext.apply({},formData);
		store.load({
			callback:function(r,options,success){
				if(!success){
					showErrorMsg("数据加载失败！");
					return;
				}
				gridSum();
			}
		});
	}
	
	function gridSum(){
	    var n = store.getCount();// 获得总行数
	    var sum_activateQt_Net=store.sum('activateQt_Net')||0;
	    var sum_activateQt=store.sum('activateQt')||0;
	    var sum_totalPrice_Net=store.sum('totalPrice_Net')||0;
	    var sum_totalPrice=store.sum('totalPrice')||0;
	    var p = new Ext.data.Record({
	        productName: '<b>总计</b>',
	        activateQt_Net:sum_activateQt_Net,
	        activateQt:sum_activateQt,
	        totalPrice_Net:sum_totalPrice_Net,
	        totalPrice:sum_totalPrice
	    });
	   	store.insert(n, p);// 插入到最后一行 
	}
	
	function doExprot(){
		var formData=util_urlDecode(Ext.getCmp('statQdtgRecordSerchForm').getForm().getValues(true),true);
		var params=Ext.urlEncode(formData);
		window.location.href= context + '/qdtg/record/exportQdtgXLS.action?'+params;
	}
}
Ext.extend(KYB.QDTG.StatQdtgRecordPanel,Ext.Panel);
Ext.reg('statQdtgRecordPanel',KYB.QDTG.StatQdtgRecordPanel);