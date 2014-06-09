Ext.ns('KYB.BDG');
/**
 * GDG发布前计录列表
 */
KYB.BDG.BdgBrRecordPanel=function(){
	var fields=[{
		name : 'recordId',
		type : 'int'
	},{
		name : 'recordSource',
		type : 'string'
	},{
		name : 'source',
		type : 'string'
	},{
		name : 'uid',
		type : 'string'
	},{
		name : 'id',
		type : 'string'
	},{
		name : 'screenName',
		type : 'string'
	},{
		name : 'location',
		type : 'string'
	},{
		name : 'createdAt',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'text',
		type : 'string'
	},{
		name : 'thumbnailPic',
		type : 'string'
	},{
		name : 'bmiddlePic',
		type : 'string'
	},{
		name : 'originalPic',
		type : 'string'
	},{
		name : 'recordStatus',
		type : 'int'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/bdg/brrecord/queryBdgBrRecords.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'records',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 5
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
		text : '保存',
		iconCls : 'icon-save',
		handler : doSave
	},'-',{
		text : '发布',
		iconCls : 'icon-share',
		handler : doRelease
	},'-',{
		text : '加入推送列表',
		iconCls: 'icon-push',
		handler: doAddPush,
		tooltip:'推送内容必须小于或等于67个字符，推送的记录如果没有发布则发布，已发布的记录不再进行发布。'	
	},'-',{
		text : '启动定时发布任务',
		id : 'releaseTimeBtn',
		xtype: 'splitbutton',
		enableToggle : true,
		iconCls :'icon-timer-start',
		handler : doTimeRelease,
		menu : {
			ignoreParentClicks :true,
			items : [{
				text:'发布时间间隔',
				menu:{
					items:[{
						xtype : 'combo',
						id : 'releaseTimeCombo',
						store: new Ext.data.ArrayStore({
					        autoDestroy: true,
					        fields: ['value', 'name'],
					        data : [
					            [5, '5分钟'],
					            [10, '10分钟'],
					            [15, '15分钟'],
					            [20, '20分钟'],
					            [30, '30分钟'],
					            [60, '60分钟'],
					            [120, '120分钟'],
					            [180, '180分钟']
					        ]
					    }),
					    displayField: 'name',
					    valueField: 'value',
					    typeAhead: true,
					    mode: 'local',
					    selectOnFocus: true,
					    triggerAction: 'all',
					    width:100
					}]
				}
			},{
				text: '指定开始发布时间',
				menu:{
					items:[{
						xtype: 'combo',
						id: 'sHourCombo',
						store: new Ext.data.ArrayStore({
					        autoDestroy: true,
					        fields: ['value', 'name'],
					        data : HOUR_DATA
					    }),
					    displayField: 'name',
					    valueField: 'value',
					    typeAhead: true,
					    editable :false,
					    mode: 'local',
					    triggerAction: 'all',
					    width:100
					}]
				}
			},{
				text: '指定结束发布时间',
				menu:{
					items:[{
						xtype: 'combo',
						id: 'eHourCombo',
						store: new Ext.data.ArrayStore({
					        autoDestroy: true,
					        fields: ['value', 'name'],
					        data : HOUR_DATA
					    }),
					    displayField: 'name',
					    valueField: 'value',
					    typeAhead: true,
					    editable :false,
					    mode: 'local',
					    triggerAction: 'all',
					    width:100
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
	var bdgRecordStatusCombo=new KYB.recordStatusCombo();
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled : true,
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					header : '状态',
					width : 50,
					dataIndex : 'recordStatus',
					menuDisabled:false,
					editor : bdgRecordStatusCombo,
					renderer : comboRenderer(bdgRecordStatusCombo)
				},{
					header : '来自',
					width : 80,
					dataIndex : 'recordSource'
				},{
					header : '用户名',
					width : 100,
					dataIndex : 'screenName'
				},{
					header : '用户ID',
					width : 80,
					dataIndex : 'uid'
				},{
					header : '地理位置',
					width : 80,
					dataIndex : 'location'
				},{
					header : '创建时间',
					dataIndex : 'createdAt',
					width : 130,
					editor : new Ext.form.DateField({
							format : 'Y-m-d H:i:s',
							editable : false
						}),
					renderer : formatDateTime
				},{
					header : '记录ID',
					width : 130,
					dataIndex : 'id'
				},{
					header : '内容',
					editor : new Ext.form.TextArea(),
					width : 200,
					dataIndex : 'text'
				},{
					header : '中等图',
					width : 120,
					dataIndex : 'bmiddlePic',
					renderer : function(val){
						if(val){
							return '<img title="单击查看原图" src="'+val+'" width=110px height=100px></img>'
						}
					},
					listeners : {
						mousedown:function(c, g, rowIndex, e){
							crateImageReadWin(g,rowIndex);
						}
					}
				},{
					header : '缩略图',
					width : 120,
					dataIndex : 'thumbnailPic',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}
				},{
					header : '原始图',
					width : 120,
					dataIndex : 'originalPic',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}
				},{
					header : "内容来源",
					width : 150,
					dataIndex : 'source',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}					
				}]
	});
	var pageBar = createPageBar(store,5);
	KYB.BDG.BdgBrRecordPanel.superclass.constructor.call(this,{
		title : '发布前记录',
		id : 'bdgBrRecordPanel',
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
					url : context + '/bdg/brrecord/queryTimeReleaseJobStatus.action',
					method : 'POST',
					callback : function(opts, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (ds.errorMessage) {
							showErrorMsg(ds.errorMessage);
						} else {
							var jobStatus=ds.jobStatus;
							if(jobStatus){
								var btn=Ext.getCmp('releaseTimeBtn');
								if(jobStatus.status==0){
									btn.pressed = true;
									toggerPause(btn);
								}
								if(jobStatus.interval){
									Ext.getCmp('releaseTimeCombo').setValue(jobStatus.interval);
								}
								if(jobStatus.sHour){
									Ext.getCmp('sHourCombo').setValue(jobStatus.sHour);
								}
								if(jobStatus.eHour){
									Ext.getCmp('eHourCombo').setValue(jobStatus.eHour);
								}
							}
						}
					}
				});
			}
		}
	});

	function doAdd(){
		var rowR =store.recordType;
		var r = new rowR({
			recordStatus : 0,
			createdAt : new Date()
		});
		Ext.getCmp('bdgBrRecordPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('bdgBrRecordPanel').startEditing(0, 2);
	}

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/bdg/brrecord/deleteBdgBrRecords.action',
			method : 'POST',
			jsonData : {
				records : encodeRecordsToJsonArray(records)
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
	
	function doSave() {
		var modfiyDs = store.getModifiedRecords();	
		if (!isNull(modfiyDs)) {
			Ext.Ajax.request({
				url : context
						+ '/bdg/brrecord/saveOrUpdateBdgBrRecords.action',
				method : 'POST',
				jsonData : {
					records : encodeRecordsToJsonArray(modfiyDs)
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
	};
	
	function doRelease(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		var records_temp=new Array();
		for (var i = 0; i < records.length; i++) {
			var data=records[i].data;
			if(data.recordStatus!=2){
				records_temp.push(records[i]);
			}
		}
		if(records_temp.length==0){
			showWarnMsg("已经发布的记录不能重复发布。");
			sm.clearSelections();
			return ;
		}
		Ext.Ajax.request({
			url : context + '/bdg/brrecord/releaseBdgBrRecords.action',
			method : 'POST',
			jsonData : {
				records : encodeRecordsToJsonArray(records_temp)
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					store.reload();
					sm.clearSelections();//清除先中数据
					showInfoMsg("发布成功！");
				}
			}
		});
	}
	
	function doStart(ex){
		Ext.Ajax.request({
			url : context+'/bdg/brrecord/startBdgTimeRelease.action',
			method : 'POST',
			jsonData:{cronExpression:ex},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					showInfoMsg("启动成功，系统会按指定间隔时间自动发布数据状态为\"已编辑\"的记录！");
				}
			}
		});
	};

	function doPause() {
		Ext.Ajax.request({
			url : context+'/bdg/brrecord/pauseTimeRelease.action',
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
	};
	
	function toggerStart(btn){
		btn.setText("启动定时发布");
		btn.setIconClass("icon-timer-start");
	};
	
	function toggerPause(btn){
		btn.setText("停止定时发布");
		btn.setIconClass("icon-timer-stop");
	};
	
	function doTimeRelease(btn){
		var m_interval=Ext.getCmp('releaseTimeCombo').getValue();
		var sHour=Ext.getCmp('sHourCombo').getValue();
		var eHour=Ext.getCmp('eHourCombo').getValue();
		if(btn.pressed){
			if(m_interval){
				var h_interval=m_interval/60;
				var ex;
				if(h_interval>1){//按小时递增
					var hours=new Array();
					if(sHour&&eHour&&sHour<eHour){
						for(var i=sHour;i<=eHour;i+=h_interval){
							hours.push(i);
						}
						ex="0 0 "+hours.toString()+" * * ?"
					}else{
						ex="0 0 0/"+h_interval+" * * ?"
					}
				}else{
					if(sHour&&eHour&&sHour<eHour){//按分钟递增
						ex="0 0/"+m_interval+" "+sHour+"-"+eHour+" * * ?"
					}else{
						ex="0 0/"+m_interval+" * * * ?"
					}
				}
				doStart(ex);
				toggerPause(btn);
			}else{
				btn.pressed=false;
				btn.removeClass('x-btn-pressed');
				showWarnMsg("没有选择发布时间间隔，请选择。")
			}
		}else{
			doPause();
			toggerStart(btn);
		}
	};
	
	/**
	 *将选中的记录加入推送列表，同时会发布记录 
	 */
	function doAddPush(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		for (var index = 0; index < records.length; index++) {
				var data=records[0].data;
				if(data.text.length>67){
				showWarnMsg("【"+data.text.substr(0,15)+"......】 超出规定字符长度。");
				return ;
			}
		}
		Ext.Ajax.request({
			url : context + '/bdg/brrecord/addBdgPush.action',
			method : 'POST',
			jsonData : {
				records : encodeRecordsToJsonArray(records)
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					store.reload();
					sm.clearSelections();//清除先中数据
					showInfoMsg("加入推送列表成功！");
				}
			}
		});
	};
}
Ext.extend(KYB.BDG.BdgBrRecordPanel,Ext.grid.EditorGridPanel);
Ext.reg('bdgBrRecordPanel',KYB.BDG.BdgBrRecordPanel);