Ext.ns('KYB.BDG');
/**
 * GDG内容推送记录
 */
KYB.BDG.BdgContentPushPanel=function(){
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
		name : 'errcode',
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
		url : context+'/bdg/pushcontent/queryBdgPushRecords.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'bdgPushRecords',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 20
		},
		fields :fields
	});
	var tbar = [{
		text : '自定义推送内容',
		iconCls : 'icon-add',
		handler : doAdd
	},'-',{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	},'-',{
		text : '修改',
		iconCls: 'icon-edit',
		handler : doModify
	},'-',{
		text : '推送',
		iconCls : 'icon-push',
		handler : doPush
	},'-',{
		text : '启动定时推送任务',
		id : 'pushContentBtn',
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
						id : 'pushContentText',
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
		handler : function(){
			pageBar.doRefresh();
		}
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled : true
		},
		columns : [sm,{
					header : '<div align="center">推送序号</div>',
					width : 80,
					dataIndex : 'pushId'
				},{
					header : '<div align="center">创建时间</div>',
					dataIndex : 'createTime',
					width : 130,
					renderer : formatDateTime
				},{
					header : '<div align="center">推送标题描述</div>',
					width : 150,
					dataIndex : 'msgTitle'
				},{
					header : '<div align="center">推送内容描述</div>',
					width : 300,
					dataIndex : 'msgContent'
				},{
					header : '<div align="center">推送时间</div>',
					dataIndex : 'pushTime',
					width : 130,
					renderer : formatDateTime
				},{
					header : '<div align="center">错误代码</div>',
					width : 80,
					dataIndex : 'errcode'
				},{
					header : '<div align="center">错误信息</div>',
					width : 180,
					dataIndex : 'errmsg'
				}]
	});
	var pageBar = createPageBar(store);
	KYB.BDG.BdgContentPushPanel.superclass.constructor.call(this,{
		title : '内容推送',
		id : 'bdgContentPushPanel',
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
					url : context + '/bdg/pushcontent/queryBdgPushJobStatus.action',
					method : 'POST',
					callback : function(opts, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (ds.errorMessage) {
							showErrorMsg(ds.errorMessage);
						} else {
							var jobStatus=ds.jobStatus;
							if(jobStatus){
								var btn=Ext.getCmp('pushContentBtn');
								if(jobStatus.status==0){
									btn.pressed = true;
									toggerPause(btn);
								}
								if(jobStatus.cronExpression){
									Ext.getCmp('pushContentText').setValue(jobStatus.cronExpression);
								}
							}
						}
					}
				});
			},
			rowdblclick:function(g,i,e){
				addOrUpdatePushContentWin('修改推送内容',store.getAt(i));
			}
		}
	});
	function addOrUpdatePushContentWin(title,record){
		new Ext.Window({
			id:'pushContentWin',
			title:title,
			width:350,
			resizable: false,
			modal: true,
			constrain: true,
			autoHeight:true,
			items:[{
				id: 'pushContentForm',
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
					maxLength: 67
				}],
				buttons:[{
					text:'保存',
					iconCls : 'icon-save',
					formBind : true,
					handler : function(){
						var formData=Ext.getCmp('pushContentForm').getForm().getValues()
						Ext.Ajax.request({
							url : context + '/bdg/pushcontent/saveOrUpdateBdgPushRecord.action',
							method : 'POST',
							jsonData : {
								bdgPushRecord : record ? Ext.apply(record.data,formData) : formData
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
										record.commit();
									}else{
										pageBar.doRefresh();
									}
									Ext.getCmp('pushContentWin').close();
								}
							}
						});
					}
				},{
					text: '重置',
					iconCls : 'icon-redo',
					handler : function(){
						Ext.getCmp('pushContentForm').getForm().reset();
					}
				}],
				listeners : {
					afterrender : function() {
						if(record){
							Ext.getCmp('pushContentForm').getForm().setValues(record.data);
						}
					}
				}
			}]
		}).show();
	};
	
	/**
	 * 增加
	 */
	function doAdd(){
		addOrUpdatePushContentWin('自定义推送内容',null);
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
		addOrUpdatePushContentWin('修改推送内容',re);
	
	}
	/**
	 * 删除
	 */
	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/bdg/pushcontent/delBdgPushRecords.action',
			method : 'POST',
			jsonData : {
				bdgPushRecords : encodeRecordsToJsonArray(records)
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
	};
	function doPush(){
		if (!sm.hasSelection()) {
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		if(records.length!=1){
			showWarnMsg("一次只能选择一条记录推送。");
			return;
		}
		Ext.Ajax.request({
			url : context + '/bdg/pushcontent/pushBdgRecord.action',
			method : 'POST',
			jsonData : {
				bdgPushRecord : records[0].data
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
	};
	function doStart(ex){
		Ext.Ajax.request({
			url : context+'/bdg/pushcontent/startBdgPushContentJob.action',
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
			url : context+'/bdg/pushcontent/pauseBdgPushContentJob.action',
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
		var cronExpression=Ext.getCmp('pushContentText').getValue();
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
}
Ext.extend(KYB.BDG.BdgContentPushPanel,Ext.grid.GridPanel);
Ext.reg('bdgContentPushPanel',KYB.BDG.BdgContentPushPanel);