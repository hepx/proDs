Ext.ns('YSB.PROJS.GRID');

YSB.PROJS.GRID.GeneralGridPanel = function() {

	var store = new Ext.data.JsonStore({
				url : context + '/mobile/queryOnePageMobileInfos.action',
				method : 'POST',
				root : 'mobileInfos',
				autoLoad : true,
				totalProperty : 'total',
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
	var mobile_s = new Ext.form.TextField({
				name : 'mobile',
				fieldLabel : '手机号码',
				width : 120,
				value : null,
				regex : /[0-9]{11}/,
				regexText : '手机号码只能是11位的有效数字组成！'
			});

	var imsi_s = new Ext.form.TextField({
				name : 'imsi',
				fieldLabel : '识别码',
				width : 150,
				value : null,
				regex : /[0-9]{16}/,
				regexText : '识别码只能是16位的有效数字 组成！'
			});
	var searchForm = new Ext.FormPanel({
				id : 'generalGridSearchForm',
				layout : 'table',
				padding : '10px',
				region : 'north',
				height : 45,
				labelAlign : 'right',
				layoutConfig : {
					columns : 2
				},
				items : [{
							layout : 'table',
							border : false,
							colspan : 2,
							items : [{
										layout : 'form',
										border : false,
										items : [mobile_s]
									}, {
										layout : 'form',
										border : false,
										items : [imsi_s]
									}]
						}]
			});
	/*
	 * 列模型
	 */
	var cm = new Ext.grid.ColumnModel({
				defaultWidth : 150,
				defaultSortable : true,
				defaults : {
					align : 'right'
				},
				columns : cms
			});
	/*
	 * 行选择模型
	 */
	var sm = new Ext.grid.RowSelectionModel({
				singleSelect : true
			});
	var bbar = createPageBar(store, 25);

	var tbar = [{
				text : '查询',
				iconCls : 'icon-search',
				handler : doSearch
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					doRefresh(store);
				}
			}];

	var generalGrid = new Ext.grid.GridPanel({
				id : 'generalGrid',
				region : 'center',
				autoScroll : true,
				closable : true,
				border : false,
				stripeRows : true,
				columnLines : true,
				loadMask : true,
				cm : cm,
				sm : sm,
				bbar : bbar,
				store : store
			});

	YSB.PROJS.GRID.GeneralGridPanel.superclass.constructor.call(this, {
				id : 'generalGridPanel',
				title : '手机用户信息（普通网格）',
				closable : true,
				layout : 'border',
				tbar : tbar,
				border : false,
				items : [searchForm, generalGrid]
			});
	/**
	 * 查询用户
	 */
	function doSearch() {
		var formDs = searchForm.getForm().getValues();
		Ext.Ajax.request({
					url : context + '/mobile/queryOnePageMobileInfos.action',
					method : 'POST',
					jsonData : {
						mobileInfo : formDs,
						start : 0,
						limit : 25
					},
					callback : function(opt, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (ds.errorMessage) {
							showMessage(ds.errorMessage);
						} else {
							if (ds.mobileInfos) {
								store.loadData(ds, false);
							}
						}
					}
				});
	}
}
Ext.extend(YSB.PROJS.GRID.GeneralGridPanel, Ext.Panel);
Ext.reg('generalGridPanel', YSB.PROJS.GRID.GeneralGridPanel);