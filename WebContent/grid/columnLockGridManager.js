Ext.ns("YSB.PROJS.GRID");

YSB.PROJS.GRID.ColumnLockGridPanel = function() {
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
	Ext.apply(cms[0], {
				locked : true,
				id : 'mobile'
			});
	var cm = new Ext.ux.grid.LockingColumnModel({
				defaultWidth : 150,
				defaultSortable : true,
				defaults : {
					align : 'right'
				},
				columns : cms
			});
	var sm = new Ext.grid.RowSelectionModel({
				singleSelect : true
			});

	var pageBar = createPageBar(store, 25);

	var columnLockGrid = new Ext.grid.GridPanel({
				id : 'columnLockGrid',
				border : false,
				width : 800,
				height : 600,
				stripeRows : true,
				loadMask : true,
				columnLines : true,
				autoScroll : true,
				sm : sm,
				cm : cm,
				bbar : pageBar,
				store : store,
				view : new Ext.ux.grid.LockingGridView()
			});
	YSB.PROJS.GRID.ColumnLockGridPanel.superclass.constructor.call(this, {
				id : 'columnLockGridPanel',
				title : '手机用户信息（列锁定）',
				closable : true,
				border : false,
				// layout : 'fit',
				items : [columnLockGrid]
			});
}
Ext.extend(YSB.PROJS.GRID.ColumnLockGridPanel, Ext.Panel);
Ext.reg("columnLockGridPanel",YSB.PROJS.GRID.ColumnLockGridPanel);