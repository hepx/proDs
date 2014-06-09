Ext.ns('KYB');

KYB.JobPanel=function(){
	var tbar=[{
		text : '全部停止',
		iconCls : 'icon-stop',
		handler : shutdown
	},'-',{
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	},'-',{
		text :'启动定时刷新',
		iconCls : 'icon-timer-start',
		enableToggle : true,
		handler : timeRefresh
	}];
	
	var sina_datacaptrueForm=new Ext.FormPanel({
		id:"sina_datacaptrueForm",
		border:false,
		labelWidth: 150, 
        //frame:true,
        bodyStyle:'padding:20px 20px 0',
		items:[{
            xtype:'fieldset',
            title: '抓取新浪微博数据任务',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 200},
            defaultType: 'textfield',
            items :[{
            		xtype:'combo',
                    fieldLabel: '执行时间占频率(分)',
                    hiddenName : 'sina_interval',
					typeAhead : true,
					emptyText : '选择或者手工输入',
					triggerAction : 'all',
					lazyRender : true,
					mode : 'local',
					//editable : false,
					valueField : 'value',
					displayField : 'name',
					store : {
						xtype : 'arraystore',
						fields : ['value', 'name'],
						data : [[1, '1分钟执行一次'],[5, '5分钟执行一次'], [10, '10分钟执行一次'],[20, '20分钟执行一次'],[30, '30分钟执行一次'],
						[45, '45分钟执行一次'],[60, '1小时执行一次'],[120, '2小时执行一次']]
					}
                }
            ]
        }],
        buttons: [{
	        text : '启动',
			listeners  : {
				click : function(){
					doStart('startSinaJob',sina_datacaptrueForm.getForm().getValues().sina_interval);
				}
			}
        },{
            text : '暂停',
			listeners  : {
				click : function(){
					doPause('pauseSinaJob');
				}
			}
        },{
        	text : '恢复',
			listeners  : {
				click : function(){
					doResume('resumeSinaJob');
				}
			}
        },{
        	text : '更新',
			listeners  : {
				click : function(){
					doUpdate('startSinaJob',sina_datacaptrueForm.getForm().getValues().sina_interval);
				}
			}
        }]
	});
	var tx_datacaptrueForm=new Ext.FormPanel({
		id : 'tx_datacaptrueForm',
		border:false,
		labelWidth: 150, 
        //frame:true,
        bodyStyle:'padding:20px 20px 0',
		items:[{
            xtype:'fieldset',
            title: '抓取腾讯微博数据任务',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 200},
            defaultType: 'textfield',
            items :[{
            		xtype:'combo',
                    fieldLabel: '执行时间占频率(分)',
                    hiddenName : 'tx_interval',
					typeAhead : true,
					emptyText : '选择或者手工输入',
					triggerAction : 'all',
					lazyRender : true,
					mode : 'local',
					//editable : false,
					valueField : 'value',
					displayField : 'name',
					store : {
						xtype : 'arraystore',
						fields : ['value', 'name'],
						data : [[1, '1分钟执行一次'],[5, '5分钟执行一次'], [10, '10分钟执行一次'],[20, '20分钟执行一次'],[30, '30分钟执行一次'],
						[45, '45分钟执行一次'],[60, '1小时执行一次'],[120, '2小时执行一次']]
					}
                }
            ]
        }],
        buttons: [{
	        text : '启动',
			listeners  : {
				click : function(t,e){
					doStart('startTxJob',tx_datacaptrueForm.getForm().getValues().tx_interval);
				}
			}
        },{
            text : '暂停',
			listeners  : {
				click : function(){
					doPause('pauseTxJob');
				}
			}
        },{
        	text : '恢复',
			listeners  : {
				click : function(){
					doResume('resumeTxJob');
				}
			}
        },{
        	text : '更新',
			listeners  : {
				click : function(){
					doUpdate('startTxJob',tx_datacaptrueForm.getForm().getValues().tx_interval);
				}
			}
        }]
	});
	var statusCombo=new Ext.form.ComboBox({
		name : 'status',
		mode:'local',
		valueField : 'value',
		displayField : 'name',
		typeAhead : true,
		lazyRender : true,
		triggerAction:'all',
		store : {
			xtype : 'arraystore',
			fields : ['value','name'],
			data : [[-1,'不存在'],[0,'正常'],[1,'已暂停'],[2,'已完成'],[3,'错误'],[4,'已锁定']]
		}
	});
	var jobGrid=new Ext.grid.GridPanel({
		title : '当前活动的任务',
		id : 'jobGrid',
		border : false,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoHeight : true,
		tbar :tbar,
		//bodyStyle:'padding:20px 20px 0',
		cm : new Ext.grid.ColumnModel({
			defaultSortable : true,
			defaultWidth : 150,
			columns:[{
				header : '任务名',
				dataIndex : 'jobName'
			},{
				header : '任务组',
				dataIndex : 'jobGroup'
			},{
				header : '下次执行时间',
				dataIndex : 'nextFireTime',
				width : 200,
				renderer : formatDateTime
			},{
				header : '状态',
				dataIndex : 'status',
				renderer : comboRenderer(statusCombo)
			}]
		}),
		store : new Ext.data.JsonStore({
			url : context+'/captrue/jobs/queryJobs.action',
			method : 'POST',
			autoLoad : true,
			autoDestroy : true,
			root : 'jobs',
			fields :[ {
				name : 'jobName',
				type : 'string'
			}, {
				name : 'jobGroup',
				type : 'string'
			}, {
				name : 'nextFireTime',
				type : 'date',
				dateFormat : 'Y-m-d\\TH:i:s'
			}, {
				name : 'status',
				type : 'int'
			}]
		})
	});
	KYB.JobPanel.superclass.constructor.call(this,{
		title : '任务调度管理',
		id : 'jobPanel',
		//frame:true,
		closable : true,
		autoScroll : true,
		items:[sina_datacaptrueForm,tx_datacaptrueForm,jobGrid]
	});

	function doStart(param,val){
		Ext.Ajax.request({
			url : context+'/captrue/jobs/'+param+'.action',
			method : 'POST',
			jsonData:{interval:val==""?null:val},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					refresh();
					showInfoMsg("启动成功！");
				}
			}
		});
	};

	function doPause(param) {
		Ext.Ajax.request({
			url : context+'/captrue/jobs/'+param+'.action',
			method : 'POST',
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					refresh();
					showInfoMsg("暂停成功！");
				}
			}
		});
	}
	
	function doResume(param) {
		Ext.Ajax.request({
			url : context+'/captrue/jobs/'+param+'.action',
			method : 'POST',
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					refresh();
					showInfoMsg("恢复成功！");
				}
			}
		});
	}
	
	function doUpdate(param,val){
		Ext.Ajax.request({
			url : context+'/captrue/jobs/'+param+'.action',
			method : 'POST',
			jsonData : {interval:val==""?null:val},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					refresh();
					showInfoMsg("更新成功！");
				}
			}
		});		
	}
	
	function shutdown(){
		Ext.Ajax.request({
			url : context+'/captrue/jobs/shutdown.action',
			method : 'POST',
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					refresh();
				}
			}
		});	
	}
	
	function refresh(){
		doRefresh(jobGrid.getStore());
	}
	var timeRefreshTask={
		run : refresh,
		interval : 30*1000	//刷新间隔
	};
	function timeRefresh(btn){
		if(btn.pressed){
			btn.setText("停止定时刷新");
			btn.setIconClass('icon-timer-stop');
			showInfoMsg("启动定时刷新后，每隔30秒刷新一次活动任务列表。");
			Ext.TaskMgr.start(timeRefreshTask);
		}else{
			btn.setText("启动定时刷新");
			btn.setIconClass('icon-timer-start');
			Ext.TaskMgr.stop(timeRefreshTask);
		}
	}
}
Ext.extend(KYB.JobPanel,Ext.Panel);
Ext.reg('jobPanel',KYB.JobPanel);