Ext.ns("KYB");

KYB.RoleManagerPanel = function() {
	var fields = [{
				name : 'id',
				type : 'int'
			}, {
				name : 'roleName',
				type : 'string'
			}, {
				name : 'roleType',
				type : 'string'
			}, {
				name : 'roleDesc',
				type : 'string'
			}];
	var store = new Ext.data.JsonStore({
				url : context + '/role/queryRole.action',
				method : 'POST',
				root : 'rolePojos',
				autoLoad : true,
				sortInfo : {
					field : 'id',
					direction : 'ASC'
				},
				fields : fields
			});
	var tbar = [{
				text : '增加',
				iconCls : 'icon-add',
				handler : add
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : save
			}, '-', {
				text : '删除',
				iconCls : 'icon-delete',
				handler : del
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					doRefresh(store);
				}
			}];
	var cm = new Ext.grid.ColumnModel({
				defaultSortable : true,
				defaultWidth : 150,
				columns : [{
							header : '<div align=center>角色名称</div>',
							dataIndex : 'roleName',
							editor : {
								xtype : 'textfield'
							}
						}, {
							header : '<div align=center>角色类型</div>',
							dataIndex : 'roleType',
							editor : {
								xtype : 'textfield'
							}
						}, {
							header : '<div align=center>角色描述</div>',
							dataIndex : 'roleDesc',
							width : 300,
							editor : {
								xytpe : 'textfield'
							}
						}]
			});
	var sm = new Ext.grid.RowSelectionModel({
				singleSelect : true
			})
	KYB.RoleManagerPanel.superclass.constructor.call(this, {
				title : '角色管理',
				id : 'roleManagerPanel',
				autoScroll : true,
				closable : true,
				stripeRows : true,
				columnLines : true,
				trackMouseOver : true,
				loadMask : true,
				tbar : tbar,
				cm : cm,
				sm : sm,
				store : store
			});
	/**
	 * 增加行
	 */
	function add() {
		var grid = Ext.getCmp('roleManager');
		var plant = grid.getStore().recordType;
		var rows = new plant({});
		grid.stopEditing();
		store.insert(0, rows);
		grid.startEditing(0, 0);
	};
	/**
	 * 保存
	 */
	function save() {
		// 获得修改的记录
		var records = Ext.getCmp('roleManager').getStore().getModifiedRecords();
		if (records) {
			Ext.Ajax.request({
						url : context + '/role/saveOrUpdateRole.action',
						method : 'POST',
						jsonData : {
							rolePojos : encodeRecordsToJsonArray(records)
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showErrorMsg(ds.errorMessage);
							} else {
								showInfoMsg("保存成功！");
								Ext.getCmp('roleManager').getStore().reload();
							}
						}
					});
		}
	}
	/**
	 * 删除
	 */
	function del() {
		var selRe = sm.getSelected();
		if (selRe) {
			var temp = true;
			if (selRe.data.id) {
				Ext.Ajax.request({
							url : context + '/role/deleteRole.action',
							method : 'POST',
							jsonData : {
								rolePojo : selRe.data
							},
							callback : function(opts, suc, res) {
								var ds = Ext.decode(res.responseText);
								if (ds.errorMessage) {
									temp = false;
									showErrorMsg(ds.errorMessage);
								} else {
									showInfoMsg("册除成功！");
								}
							}
						});
			}
			if (temp) {
				store.remove(selRe);
			}
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
}
Ext.extend(KYB.RoleManagerPanel, Ext.grid.EditorGridPanel);
Ext.reg("roleManagerPanel", KYB.RoleManagerPanel);