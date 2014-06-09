Ext.ns("KYB");

KYB.LogsManagerPanel = function() {
	var fields = [{
				name : 'id',
				type : 'int'
			}, {
				name : 'user',
				type : 'string'
			}, {
				name : 'ip',
				type : 'string'
			}, {
				name : 'createTime',
				type : 'date',
				dateFormat : 'Y-m-d\\TH:i:s'
			}, {
				name : 'logs',
				type : 'string'
			}];
	var store = new Ext.data.JsonStore({
				url : context + '/logs/queryLogs.action',
				method : 'POST',
				root : 'logsPojos',
				totalProperty : 'total',
				autoLoad : true,
				baseParams : {
					start : 0,
					limit : 20
				},
				sortInfo : {
					field : 'id',
					direction : 'DESC'
				},
				fields : fields
			});
	var tbar = [{
				text : '删除',
				iconCls : 'icon-delete',
				handler : del
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					pageBar.doRefresh();
				}
			}];
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
				defaultSortable : true,
				defaultWidth : 150,
				columns : [sm,{
							header : '<div align=center>用户名</div>',
							dataIndex : 'user'
						}, {
							header : '<div align=center>IP</div>',
							dataIndex : 'ip'
						}, {
							header : '<div align=center>时间</div>',
							align : 'right',
							dataIndex : 'createTime',
							renderer : formatDateTime
						}, {
							header : '<div align=center>日志描述</div>',
							width : 400,
							id : 'logs',
							dataIndex : 'logs'
						}]
			});

	var pageBar = createPageBar(store);
	KYB.LogsManagerPanel.superclass.constructor.call(this, {
				title : '日志管理',
				id : 'logsManagerPanel',
				closable : true,
				stripeRows : true,
				columnLines : true,
				loadMask : true,
				autoExpandColumn : 'logs',
				tbar : tbar,
				cm : cm,
				sm : sm,
				bbar : pageBar,
				store : store
			});

	function del() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
						url : context + '/logs/deleteLogs.action',
						method : 'POST',
						jsonData : {
							logsPojos : encodeRecordsToJsonArray(records)
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showErrorMsg(ds.errorMessage);
							} else {
								showInfoMsg("删除成功！");
							}
							store.remove(records);
						}
					});
		} else {
			showWarnMsg("没有选中任何记录，请选择。")
		}
	}
}

Ext.extend(KYB.LogsManagerPanel, Ext.grid.GridPanel);
Ext.reg('logsManagerPanel', KYB.LogsManagerPanel);