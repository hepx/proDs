Ext.ns("YSB.PROJS.GRID");

YSB.PROJS.GRID.BufferGridPanel = function() {

	var store = new Ext.data.JsonStore({
				url : context + "/mobile/queryOnePageMobileInfos.action",
				method : 'POST',
				root : 'mobileInfos',
				autoLoad : true,
				totalProperty:'total',
				baseParams:{
					start : 0,
					limit :1000
				},
				sortInfo : {
					field : 'id',
					direction : 'ASC'
				},
				fields : fields
			});
	var tbar = [{
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					doRefresh(store);
				}
			}];

	var cm = new Ext.grid.ColumnModel({
				defaultWidth : 150,
				defaultSortable : true,
				defaults : {
					align : 'right'
				},
				columns : cms
			});
	var bbar=createPageBar(store,1000);

	var config = ({
		title : '手机用户信息（缓冲网络）',
		id : 'bufferGridPanel',
		closable : true,
		loadMask : true,
		autoScroll : true,
		border : false,
		columnLines : true,
		stripeRows : true,
		cm : cm,
		tbar : tbar,
		bbar : bbar,
		store : store,
		view: new Ext.ux.grid.BufferView({
		    scrollDelay: 200
	    })
	});
	YSB.PROJS.GRID.BufferGridPanel.superclass.constructor.call(this, config);
}
Ext.extend(YSB.PROJS.GRID.BufferGridPanel, Ext.grid.GridPanel);
Ext.reg('bufferGridPanel', YSB.PROJS.GRID.BufferGridPanel);