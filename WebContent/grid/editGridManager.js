Ext.ns('YSB.PROJS.GRID');

YSB.PROJS.GRID.EditGridPanel = function() {
	var store = new Ext.data.JsonStore({
				url : context + '/mobile/queryOnePageMobileInfos.action',
				method : 'POST',
				root : 'mobileInfos',
				totalProperty : 'total',
				autoLoad : true,
				baseParams : {
					start : 0,
					limit : 25
				},
				sortInfo : {
					field : 'id',
					direction : 'DESC'
				},
				fields : fields
			});
	var sercombox = new ServiceCombox();
	var procombox = new ProvinceCombox();

	var editCms = [{
				header : '<div align=center>手机号码</div>',
				dataIndex : 'mobile'
			}, {
				header : '<div align=center>识别码</div>',
				dataIndex : 'imsi'
			}, {
				header : '<div align=center>创建时间</div>',
				dataIndex : 'createTime',
				editor : new Ext.form.DateField({
							format : 'Y-m-d H:i:s',
							editable : false
						}),
				renderer : formatDateTime
			}, {
				header : '<div align=center>服务商</div>',
				width : 100,
				align : 'center',
				dataIndex : 'service',
				editor : sercombox,
				renderer : comboRenderer(sercombox)
			}, {
				header : '<div align=center>卡类型</div>',
				dataIndex : 'cardType'
			}, {
				header : '<div align=center>省份</div>',
				width : 100,
				dataIndex : 'province',
				editor : procombox,
				renderer : comboRenderer(procombox)
			}, {
				header : '<div align=center>城市</div>',
				width : 100,
				dataIndex : 'city'
			}];

	var cm = new Ext.grid.ColumnModel({
				defaultWidth : 150,
				defaultSortable : true,
				defaults : {
					align : 'right',
					editor : new Ext.form.TextField({})
				},
				columns : editCms
			});
	var tbar = [{
				text : '增加',
				iconCls : 'icon-add',
				handler : doAdd
			}, '-', {
				text : '删除',
				iconCls : 'icon-delete',
				handler : doDelete
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : doSave
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					doRefresh(store);
				}
			}];
	var bbar = createPageBar(store, 25);
	var sm = new Ext.grid.RowSelectionModel({
				singleSelect : true
			});

	var editGridPanel = new Ext.grid.EditorGridPanel({
				border : false,
				stripeRows : true,
				autoScroll : true,
				loadMask : true,
				columnLines : true,
				trackMouseOver : true,
				tbar : tbar,
				bbar : bbar,
				cm : cm,
				sm : sm,
				store : store
			});
	var config = {
		id : 'editGridPanel',
		title : '手机用户信息(可编辑)',
		border : false,
		layout : 'fit',
		closable : true,
		items : [editGridPanel]
	};
	/*
	 * 增加行
	 */
	function doAdd() {
		var rowR = editGridPanel.getStore().recordType;
		var r = new rowR({});
		editGridPanel.stopEditing();
		store.insert(0, r);
		editGridPanel.startEditing(0, 0);
	};
	/*
	 * 删除
	 */
	function doDelete() {
		var selR = sm.getSelected();
		if (selR.data.id) {
			Ext.Ajax.request({
						url : context + '/mobile/deleteMobileInfo.action',
						method : 'POST',
						jsonData : {
							mobileInfo : selR.data
						},
						callback : function(opt, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showMessage(ds.errorMessage);
							} else {
								store.reload();
								showMessage("删除成功！");
							}
						}
					});
		} else {
			store.remove(selR);
		}
	}
	/*
	 * 保存或更新
	 */
	function doSave() {
		var modfiyDs = store.getModifiedRecords();
		if (!isNull(modfiyDs)) {
			Ext.Ajax.request({
						url : context
								+ '/mobile/saveOrUpdateMobileInfos.action',
						method : 'POST',
						jsonData : {
							mobileInfos : encodeRecordsToJsonArray(modfiyDs)
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showMessage(ds.errorMessage);
							} else {
								store.commitChanges();
								store.reload();
								showMessage("保存成功！");
							}
						}
					});
		}
	}
	YSB.PROJS.GRID.EditGridPanel.superclass.constructor.call(this, config);
}
Ext.extend(YSB.PROJS.GRID.EditGridPanel, Ext.Panel);
Ext.reg('editGridPanel', YSB.PROJS.GRID.EditGridPanel);