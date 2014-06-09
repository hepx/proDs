Ext.ns('KYB.SXD');

KYB.SXD.ChantingRecordPanel = function(){
	var fields=[{
		name : 'chantRecordId',
		type : 'int'
	},{
		name : 'xyNo',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'chantingBook',
		type : 'string'
	},{
		name : 'meritContent',
		type : 'string'
	},{
		name : 'restoreStatus',
		type : 'string'
	},{
		name : 'restoreContent',
		type : 'string'
	}];
	var store = new Ext.data.JsonStore({
		url : context+'/sxd/chantrecord/queryChantRecords.action',
		method : 'POST',
		autoDestroy : true,
		autoLoad : true,
		root : 'chantingRecords',
		totalProperty : 'total',
		baseParams : {
			start : 0,
			limit : 20
		},
		fields:fields
	});
	var tbar = [{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
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
		columns : [sm,{
			header : '香缘号',
			width : 100,
			dataIndex : 'xyNo'
		}, {
			header : "创建时间",
			width : 130,
			dataIndex : 'createTime',
			renderer : formatDateTime
		},{
			header : '诵经',
			width : 200,
			dataIndex : 'chantingBook'
		},{
			header : '回向功德',
			width : 250,
			id : 'meritContent',
			dataIndex : 'meritContent'
		},{
			header : '状态',
			width : 70,
			dataIndex : 'restoreStatus',
			renderer : function(val){
				return val==='0'?'未还愿':'已还愿';
			}
		},{
			header : '还愿内容',
			width : 250,
			dataIndex : 'restoreContent'
		}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.ChantingRecordPanel.superclass.constructor.call(this,{
		title : '诵经记录',
		id : 'chantingRecordPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoExpandColumn : 'meritContent',
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store
	});

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/chantrecord/deleteChantRecords.action',
				method : 'POST',
				jsonData : {
					chantingRecords : encodeRecordsToJsonArray(records)
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
		} else {
			showWarnMsg("没有选中任何记录，请选择。")
		}
	}
}
Ext.extend(KYB.SXD.ChantingRecordPanel,Ext.grid.GridPanel);
Ext.reg("chantingRecordPanel",KYB.SXD.ChantingRecordPanel);