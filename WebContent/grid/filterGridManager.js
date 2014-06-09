/**
 * 过滤器网格
 */
Ext.ns("YSB.PROJS.GRID");

YSB.PROJS.GRID.FilterGridPanel = function() {

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

	var filters = new Ext.ux.grid.GridFilters({
				local : true,
				menuFilterText : '筛选',
				filters : [{
							type : 'string',
							dataIndex : 'mobile'
						}, {
							type : 'string',
							dataIndex : 'imsi'
						}, {
							type : 'date',
							dataIndex : 'createTime',
							afterText :'在...之后',
							beforeText:'在...之前',
							onText:'等于',
							dateFormat:'Y-m-d'
						}, {
							type : 'list',
							dataIndex : 'service',
							options : serviceList
						}, {
							type : 'string',
							dataIndex : 'cardType'
						}, {
							type : 'list',
							dataIndex : 'province',
							options : provinceList
						}, {
							type : 'string',
							dataIndex : 'city'
						}]
			})
	var cm = new Ext.grid.ColumnModel({
				defaultSortable : true,
				defaultWidth : 150,
				defaults : {
					align : 'right'
				},
				columns : cms
			});
	var sm = new Ext.grid.RowSelectionModel({
				singleSelect : true
			});
	var pageBar = createPageBar(store, 25);
	Ext.apply(pageBar, {
				plugins : [filters]
			});
	var tbar =[{
		text :'清除筛选记录',
		iconCls :'icon-clear',
		handler : clearFilters
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : function() {
			doRefresh(store);
		}
	}]
	var filterGrid = new Ext.grid.GridPanel({
				id : 'filterGrid',
				autoScroll : true,
				border : false,
				stripeRows : true,
				loadMask : true,
				columnLines : true,
				sm : sm,
				cm : cm,
				bbar : pageBar,
				tbar : tbar,
				store : store,
				plugins : [filters]
			});
	/*
	 * 清除筛选记录
	 */
	function clearFilters(){
		filters.clearFilters();
	}
	YSB.PROJS.GRID.FilterGridPanel.superclass.constructor.call(this, {
				id : 'filterGridPanel',
				title : '手机用户信息（过滤数据）',
				border : false,
				closable:true,
				layout : 'fit',
				items : [filterGrid]
			});
}
Ext.extend(YSB.PROJS.GRID.FilterGridPanel, Ext.Panel);
Ext.reg("filterGridPanel",YSB.PROJS.GRID.FilterGridPanel)