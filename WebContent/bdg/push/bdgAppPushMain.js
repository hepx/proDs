Ext.ns('KYB.BDG');
/**
 * GDG APP推送记录
 */
KYB.BDG.BdgAppPushPanel=function(){
	var fields=[{
		name : 'pushId',
		type : 'int'
	},{
		name : 'msgTitle',
		type : 'string'
	},{
		name : 'msgContent',
		type : 'string'
	},{
		name : 'appName',
		type : 'string'
	},{
		name : 'packageName',
		type : 'string'
	},{
		name : 'appPath',
		type : 'string'
	},{
		name : 'errmsg',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'pushTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/bdg/pushapp/queryBdgPushApps.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'bdgPushApps',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 20
		},
		fields :fields
	});
	var tbar = [{
		text : '从市场添加APP',
		iconCls: 'icon-add',
		handler: doAddFromMarket
		
	},{
		text : '自定义APP推送',
		iconCls : 'icon-add',
		handler : doAdd
	},'-',{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	},'-',{
		text: '修改',
		iconCls: 'icon-edit',
		handler: doModify
	},'-',{
		text : '推送',
		iconCls : 'icon-push',
		handler : doPush
	},'-',{
		text : '启动定时推送任务',
		id : 'pushAppBtn',
		xtype: 'splitbutton',
		enableToggle : true,
		iconCls :'icon-timer-start',
		handler : doTimePush,
		menu : {
			ignoreParentClicks :true,
			items : [{
				text:'执行任务时间表达式',
				menu:{
					items:[{
						xtype : 'textfield',
						id : 'pushAppText',
						name: 'cronExpression',
						emptyText:'0 0 10 */3 * ?',
					    width:120
					}]
				}
			}]
		}
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled : true
		},
		columns : [sm,{
					header : '<div align="center">推送序号</div>',
					width : 70,
					dataIndex : 'pushId'
				},{
					header : '<div align="center">创建时间</div>',
					dataIndex : 'createTime',
					width : 130,
					renderer : formatDateTime
				},{
					header : '<div align="center">推送标题描述</div>',
					width : 130,
					dataIndex : 'msgTitle'
				},{
					header : '<div align="center">推送内容描述</div>',
					width : 180,
					dataIndex : 'msgContent'
				},{
					header: '<div align="center">应用名</div>',
					width: 100,
					dataIndex: 'appName'
				},{
					header : '<div align="center">包名</div>',
					width : 120,
					dataIndex: 'packageName'
				},{
					header: '<div align="center">APP下载地址</div>',
					width:120,
					dataIndex: 'appPath',
					renderer : linkRenderer
				},{
					header : '<div align="center">推送时间</div>',
					dataIndex : 'pushTime',
					width : 130,
					renderer : formatDateTime
				},{
					header : '<div align="center">错误信息</div>',
					width : 120,
					dataIndex : 'errmsg'
				}]
	});
	var pageBar = createPageBar(store);
	KYB.BDG.BdgAppPushPanel.superclass.constructor.call(this,{
		title : '应用推送',
		id : 'bdgAppPushPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store,
		listeners:{
			render:function(g){
				Ext.Ajax.request({
					url : context + '/bdg/pushapp/queryBdgPushAppJobStatus.action',
					method : 'POST',
					callback : function(opts, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (ds.errorMessage) {
							showErrorMsg(ds.errorMessage);
						} else {
							var jobStatus=ds.jobStatus;
							if(jobStatus){
								var btn=Ext.getCmp('pushAppBtn');
								if(jobStatus.status==0){
									btn.pressed = true;
									toggerPause(btn);
								}
								if(jobStatus.cronExpression){
									Ext.getCmp('pushAppText').setValue(jobStatus.cronExpression);
								}
							}
						}
					}
				});
			},
			rowdblclick:function(g,i,e){
				addOrUpdateBdgPushAppWin('修改APP推送',store.getAt(i));
			}
		}
	});

	/**
	 * 从市场添加APP
	 */	
	function doAddFromMarket(){
		var url_='/bdg/pushapp/addBdgPushAppFromMarket.action';
		new KYB.MARKET.AppMainWindow(url_,refresh).show();
	}
	/**
	 * 增加和修改WIN
	 */
	function addOrUpdateBdgPushAppWin(title,record){
		new Ext.Window({
			id:'pushAppWin',
			title:title,
			width:350,
			resizable: false,
			modal: true,
			constrain: true,
			autoHeight:true,
			items:[{
				id: 'pushAppForm',
				xtype:'form',
				padding:10,
				labelWidth :60,
				border: false,
				monitorValid : true,
				items:[{
					xtype: 'textfield',
					fieldLabel:'通知标题',
					name: 'msgTitle',
					anchor:'95%'
				},{
					xtype: 'textarea',
					fieldLabel: '通知内容',
					name: 'msgContent',
					anchor:'95%',
					allowBlank: false,
					maxLength: 67,
					anchor:'95%'
				},{
					xtype: 'textfield',
					fieldLabel: '应用名',
					name: 'appName',
					anchor:'95%'
				},{
					xtype: 'textfield',
					fieldLabel: '包名',
					name: 'packageName',
					anchor:'95%'
				},{
					xtype: 'textarea',
					fieldLabel: 'APP下载地址',
					name: 'appPath',
					anchor:'95%',
					allowBlank: false
				}],
				buttons:[{
					text:'保存',
					iconCls : 'icon-save',
					formBind : true,
					handler : function(){
						var formData=Ext.getCmp('pushAppForm').getForm().getValues();
						Ext.Ajax.request({
							url : context + '/bdg/pushapp/saveOrUpdateBdgPushApp.action',
							method : 'POST',
							jsonData : {
								bdgPushApp : record ? Ext.apply(record.data,formData) : formData
							},
							callback : function(opts, suc, res) {
								var ds = Ext.decode(res.responseText);
								if (ds.errorMessage) {
									showErrorMsg(ds.errorMessage);
								} else {
									showInfoMsg(title+"成功！");
									if(record){
										record.set('msgTitle',formData.msgTitle);
										record.set('msgContent',formData.msgContent);
										record.set('appName',formData.appName);
										record.set('packageName',formData.packageName);
										record.set('appPath',formData.appPath);
										record.commit();
									}else{
										pageBar.doRefresh();
									}
									Ext.getCmp('pushAppWin').close();
								}
							}
						});
					}
				},{
					text: '重置',
					iconCls : 'icon-redo',
					handler : function(){
						Ext.getCmp('pushAppForm').getForm().reset();
					}
				}],
				listeners:{
					afterrender:function(){
						if(record){
							Ext.getCmp('pushAppForm').getForm().setValues(record.data);
						}
					}
				}
			}]
		}).show();		
	}
	
	/**
	 * 增加自定义推送APP
	 */
	function doAdd(){
		addOrUpdateBdgPushAppWin('自定义APP推送',null);
	}
	/**
	 * 修改
	 */
	function doModify(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var re=sm.getSelected();
		addOrUpdateBdgPushAppWin('修改APP推送',re);
	}
	/**
	 * 删除
	 */
	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。")
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/bdg/pushapp/deleteBdgPushApps.action',
			method : 'POST',
			jsonData : {
				bdgPushApps : encodeRecordsToJsonArray(records)
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					store.remove(records);
					showInfoMsg("删除成功！");
				}
			}
		});
	}
	/**
	 * 推送
	 */
	function doPush(){
		var records = sm.getSelections();
		if (!records || records.length == 0) {
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		if(records.length!=1){
			showWarnMsg("一次只能选择一条记录推送。");
			return;
		}
		Ext.Ajax.request({
			url : context + '/bdg/pushapp/pushBdgApp.action',
			method : 'POST',
			jsonData : {
				bdgPushApp : records[0].data
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					store.reload();
					sm.clearSelections();//清除先中数据
					showInfoMsg("推送成功！");
				}
			}
		});
	}
	
	function doStart(ex){
		Ext.Ajax.request({
			url : context+'/bdg/pushapp/startBdgPushAppJob.action',
			method : 'POST',
			jsonData:{cronExpression:ex},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					showInfoMsg("启动成功，系统会按指定间隔时间自动推送数据！");
				}
			}
		});
	};

	function doPause() {
		Ext.Ajax.request({
			url : context+'/bdg/pushapp/pauseBdgPushAppJob.action',
			method : 'POST',
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					showInfoMsg("暂停成功！");
				}
			}
		});
	}
	
	function toggerStart(btn){
		btn.setText("启动定时推送");
		btn.setIconClass("icon-timer-start");
	}
	
	function toggerPause(btn){
		btn.setText("停止定时推送");
		btn.setIconClass("icon-timer-stop");
	}
	
	function doTimePush(btn){
		var cronExpression=Ext.getCmp('pushAppText').getValue();
		if(cronExpression){
			if(btn.pressed){
				doStart(cronExpression);
				toggerPause(btn);
			}else{
				doPause();
				toggerStart(btn);
			}
		}else{
			showWarnMsg("没有选择发布时间间隔，请选择。")
		}
	}
	
	function refresh(){
		pageBar.doRefresh();
	}
}
Ext.extend(KYB.BDG.BdgAppPushPanel,Ext.grid.GridPanel);
Ext.reg('bdgAppPushPanel',KYB.BDG.BdgAppPushPanel);