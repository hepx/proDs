Ext.ns('KYB.BDG');

KYB.BDG.UserMainPanel = function() {
	var fields = [{
		name : 'userId',
		type : 'int'
	}, {
		name : 'imei',
		type : 'string'
	}, {
		name : 'mobile',
		type : 'string'
	}, {
		name : 'simOperator',
		type : 'string'
	}, {
		name : 'networkType',
		type : 'string'
	}, {
		name : 'province',
		type : 'string'
	}, {
		name : 'city',
		type : 'string'
	}, {
		name : 'sdk',
		type : 'string'
	}, {
		name : 'screenSize',
		type : 'string'
	}, {
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name: 'channel',
		type: 'string'
	}];
	var store = new Ext.data.JsonStore({
		url : context + '/bdg/user/queryUsers.action',
		method : 'POST',
		root : 'users',
		totalProperty : 'total',
		autoLoad : true,
		autoDestroy : true,
		baseParams: {
			start : 0,
			limit : 20
		},
		fields : fields
	});
	var tbar = [{
		text: '查询',
		iconCls: 'icon-search',
		handler: doSearch
	},{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	}, '-',{
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled : true,
			width : 150
		},
		columns : [sm,{
			menuDisabled : false,
			width:130,
			header : '<div align=center>渠道号</div>',
			dataIndex: 'channel'
		},{
			header : '<div align=center>IMEI号</div>',
			align:'right',
			dataIndex : 'imei'
		}, {
			header : '<div align=center>手机号</div>',
			align:'right',
			dataIndex : 'mobile'
		}, {
			header : '<div align=center>SIM运营商</div>',
			align : 'center',
			width:100,
			dataIndex : 'simOperator'
		}, {
			header : '<div align=center>网络类型</div>',
			align : 'center',
			dataIndex : 'networkType'
		}, {
			header : '<div align=center>省份</div>',
			width:100,
			dataIndex : 'province'
		}, {
			header : '<div align=center>城市</div>',
			width:100,
			dataIndex : 'city'
		}, {
			header : '<div align=center>SDK版本</div>',
			width:100,
			align:'right',
			dataIndex : 'sdk'
		}, {
			header : '<div align=center>屏幕尺寸</div>',
			align : 'right',
			width : 100,
			dataIndex : 'screenSize'
		}, {
			header : '<div align=center>创建时间</div>',
			align : 'right',
			width : 150,
			dataIndex : 'createTime',
			renderer : formatDateTime
		}]
	});
	var pageBar = createPageBar(store);
	
	var bdgUserSerchForm=new Ext.form.FormPanel({
		id: 'bdgUserSerchForm',
		padding: '10px 10px 5px 10px',
		labelAlign: 'right',
		autoScroll:true,
		layout: 'column',
		items:[{
			columnWidth: .33,
			layout: 'form',
			border:false,
			items:[{
				xtype: 'textfield',
				id: 'channel',
				name: 'channel',
				fieldLabel: '渠道号'			
			}]
		},{
			columnWidth: .33,
			layout: 'form',
			border:false,
			items:[{
				xtype: 'textfield',
				id: 'imei',
				name: 'imei',
				fieldLabel: 'IMEI号',
				regex : /[0-9]/,
				regexText : 'IMEI号只能是数字 组成！'
			}]
		},{
			columnWidth: .33,
			layout: 'form',
			border: false,
			items:[{
				xtype: 'datefield',
				format: 'Y-m-d',
				id: 'createTime',
				width: 120,
				name: 'createTime',
				fieldLabel: '创建时间'
			}]
		}]
	});
	
	KYB.BDG.UserMainPanel.superclass.constructor.call(this, {
		title : '吊丝不寂寞用户',
		id : 'bdgUserMainPanel',
		autoScroll : true,
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		viewConfig : {autoFill:true},
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store,
		items:[bdgUserSerchForm]
	});

	function refresh() {
		pageBar.doRefresh();
	}

	function doSearch(){
		store.baseParams={
			start : 0,
			limit : 20
		}
		var channel=Ext.getCmp('channel').getValue();
		var imei=Ext.getCmp('imei').getValue();
		var createTime=Ext.getCmp('createTime').getValue();
		if(channel){
			store.setBaseParam("channel",channel);
		}
		if(imei){
			store.setBaseParam("imei",imei);
		}
		if(createTime){
			store.setBaseParam("createTime",createTime);
		}
		store.load();
	}
	
	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Msg.confirm("提示：", "确定要删除选 中的用户吗？", function(btn) {
			if (btn == "yes") {
				Ext.Ajax.request({
					url : context + '/bdg/user/deleteUsers.action',
					method : 'POST',
					jsonData : {
						users : encodeRecordsToJsonArray(records)
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
		});
	}
}
Ext.extend(KYB.BDG.UserMainPanel, Ext.grid.GridPanel);
Ext.reg('bdgUserMainPanel', KYB.BDG.UserMainPanel)
