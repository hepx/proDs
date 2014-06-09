Ext.ns('KYB.QDTG');

KYB.QDTG.StatQdtgRecordByCvsPanel=function(){
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
		name : 'unitPrice',
		type : 'float'
	},{
		name: 'activateQt',
		type: 'int'
	},{
		name: 'totalPrice',
		type: 'float'
	},{
		name: 'recordTime',
		type: 'string'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/qdtg/record/statQdtgRecordByCvs.action',
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
			statQdtgRecordByCvsSerchForm.getForm().reset();
			doSearch();
		}
	}];
	var sm = new Ext.grid.RowSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled:true,
			width: 120
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
			header : '<div align="center">单价</div>',
			align: 'right',
			width: 100,
			dataIndex : 'unitPrice'
		},{
			header: '<div align="center">激活</div>',
			align: 'right',
			dataIndex: 'activateQt',
			renderer: function(val){
				return '<span style="color:red;">'+val+'</span>'
			}
		},{
			header: '<div align="center">小计</div>',
			align: 'right',
			dataIndex: 'totalPrice',
			renderer: function(val){
				return '<span style="color:red;">'+formatNumber(val)+'</span>'
			}
		}]
	});
	//var pageBar = createPageBar(store);
	var day_field=[{
		columnWidth: .25,
		layout: 'form',
		id:'cvs_stat_startTime',
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
		id:'cvs_stat_endTime',
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
		id: 'cvs_stat_month',
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
	var statQdtgRecordByCvsSerchForm=new Ext.form.FormPanel({
		id: 'statRecordByCvsSerchForm',
		padding: '10px 10px 5px 10px',
		region: 'north',
		height: 70,
		labelAlign: 'right',
		autoScroll:true,
		autoDestroy: true,
		layout:'column',
		tbar : tbar,
		items:[{
			columnWidth: .25,
			layout: 'form',
			border: false,
			items:[{
				xtype: 'combo',
				fieldLabel:'统计类型',
				hiddenName : 'statType',
				mode:'local',
				width:120,
				valueField : 'value',
				displayField : 'name',
				typeAhead : true,
				lazyRender : true,
				editable: false,
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
						var fm=Ext.getCmp('statRecordByCvsSerchForm');
						if(statType==1&&fm.getForm().items.length!=4){//按日
							fm.remove('cvs_stat_month');
							fm.add(day_field);
							fm.doLayout();
						}else if(statType==2&&fm.getForm().items.length!=3){//按月
							fm.remove('cvs_stat_startTime');
							fm.remove('cvs_stat_endTime');
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
				xtype: 'channelCombox',
				width: 120
			}]
		},day_field]
	});
	
	var statQdtgRecordByCvsGrid=new Ext.grid.GridPanel({
		region: 'center',
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		border: false,
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
	KYB.QDTG.StatQdtgRecordByCvsPanel.superclass.constructor.call(this,{
		title : '记录统计',
		id : 'statQdtgRecordByCvsPanel',
		layout: 'border',
		border: false,
		closable : true,
		items:[statQdtgRecordByCvsSerchForm,statQdtgRecordByCvsGrid]
	});
	
	function doSearch(){
		var formData=util_urlDecode(Ext.getCmp('statRecordByCvsSerchForm').getForm().getValues(true),true);
		store.baseParams=Ext.apply({},formData);
		store.load({
			callback:function(r,options,success){
				if(!success){
					showErrorMsg("加载数据失败！");
					return ;
				}
				gridSum();
			}
		});
	}
		
	function gridSum(){
	    var n = store.getCount();// 获得总行数
	    var sum_activateQt=store.sum('activateQt')||0;
	    var sum_totalPrice=store.sum('totalPrice')||0;
	    var p = new Ext.data.Record({
	        productName: '<b>总计</b>',
	        activateQt:sum_activateQt,
	        totalPrice:sum_totalPrice
	    });
	   	store.insert(n, p);// 插入到最后一行 
	}
}
Ext.extend(KYB.QDTG.StatQdtgRecordByCvsPanel,Ext.Panel);
Ext.reg('statQdtgRecordByCvsPanel',KYB.QDTG.StatQdtgRecordByCvsPanel);