Ext.ns('KYB.SXD');

KYB.SXD.PrayRecordPanel = function(){
	var fields=[{
		name : 'prayId',
		type : 'int'
	},{
		name : 'xyNo',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'prayType',
		type : 'string'
	},{
		name : 'buddhaImagePath',
		type : 'string'
	},{
		name : 'articlePath',
		type : 'string'
	},{
		name : 'meritContent',
		type : 'string'
	},{
		name : 'restoreStatus',
		type : 'string'
	}];
	var store = new Ext.data.JsonStore({
		url : context+'/sxd/pray/queryPrayRecords.action',
		method : 'POST',
		autoDestroy : true,
		autoLoad : true,
		root : 'prayRecords',
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
			header : '祈福类型',
			width : 100,
			dataIndex : 'prayType'
		},{
			header : '供奉佛像',
			width : 100,
			align : 'center',
			dataIndex : 'buddhaImagePath',
			renderer : function(val){
				return val?'<img src="'+(val)+'" width="80" height="80">':'';
			}
		},{
			header : '供奉物品',
			width : 80,
			align : 'center',
			dataIndex : 'articlePath',
			renderer : function(val){
				return val?'<img src="'+(val)+'" width="40" height="60">':'';
			}
		},{
			header : '回向功德',
			width : 200,
			id : 'meritContent',
			dataIndex : 'meritContent'
		},{
			header : '状态',
			width : 70,
			dataIndex : 'restoreStatus',
			renderer : function(val){
				return val==='0'?'未还愿':'已还愿';
			}
		}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.PrayRecordPanel.superclass.constructor.call(this,{
		title : '祈福记录',
		id : 'prayRecordPanel',
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
				url : context + '/sxd/pray/deletePrayRecords.action',
				method : 'POST',
				jsonData : {
					prayRecords : encodeRecordsToJsonArray(records)
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
Ext.extend(KYB.SXD.PrayRecordPanel,Ext.grid.GridPanel);
Ext.reg("prayRecordPanel",KYB.SXD.PrayRecordPanel);