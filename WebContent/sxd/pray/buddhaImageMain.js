Ext.ns('KYB.SXD');

KYB.SXD.BuddhaImagePanel=function(){
	var url_='/sxd/pray/saveOrUpdateBuddhaImages.action';
	var fields=[{
		name : 'imageId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'buddhaImageName',
		type : 'string'
	},{
		name : 'buddhaImageDesc',
		type : 'string'
	},{
		name : 'buddhaImagePath',
		type : 'string'
	}];
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/pray/queryBuddhaImages.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'buddhaImages',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 20
		},
		fields :fields
	});
	var tbar = [{
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},'-',{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	},'-',{
		text : '修改',
		iconCls : 'icon-edit',
		handler : doModify
	},'-',{
		text : '上传佛像',
		iconCls : 'icon-img',
		handler : doUploadImage
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		columns : [sm,{
					header : '创建人',
					width : 100,
					dataIndex : 'createUser'
				}, {
					header : '创建时间',
					dataIndex : 'createTime',
					width : 130,
					renderer : formatDateTime
				}, {
					header : "佛像名称",
					width : 300,
					dataIndex : 'buddhaImageName'
				},{
					header : '描述',
					width : 350,
					id : 'buddhaImageDesc',
					dataIndex : 'buddhaImageDesc'
				},{
					header : '佛像',
					width : 100,
					align : 'center',
					dataIndex : 'buddhaImagePath',
					renderer : function(val){
						return val?'<img src="'+(val)+'" width="80" height="80">':'';
					}
				}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.BuddhaImagePanel.superclass.constructor.call(this,{
		title : '佛像列表',
		id : 'buddhaImagePanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		//autoExpandColumn : 'buddhaImageDesc',
		tbar : tbar,
		viewConfig : {
			autoFill :true
		},
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store
	});
	
	function refresh(){
		pageBar.doRefresh();
	}
	
	function doAdd(){
		new KYB.SXD.BuddhaImageAddorUpWin(
		{title : '增加佛像'},
		null,
		url_,
		refresh
		).show();
	};
	
	function doModify(){
		var ds = sm.getSelected();
		if (ds) {
			new KYB.SXD.BuddhaImageAddorUpWin(
			{title : '修改佛像'}, 
			ds.data,
			url_,
			refresh
			).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};
	
	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/pray/deleteBuddhaImages.action',
				method : 'POST',
				jsonData : {
					buddhaImages : encodeRecordsToJsonArray(records)
				},
				callback : function(opts, suc, res) {
					var ds = Ext.decode(res.responseText);
					if (ds.errorMessage) {
						showErrorMsg(ds.errorMessage);
					} else {
						showInfoMsg("删除成功！");
						store.remove(records);
					}
				}
			});
		} else {
			showWarnMsg("没有选中任何记录，请选择。")
		}
	}
	
	function doUploadImage(){
		var ds = sm.getSelected();
		if (ds) {
			var url_=context+'/sxd/pray/uploadBuddhaImage.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'imageId',
					value : ds.data.imageId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-txt-image',
		            emptyText: '选择一个小于100KB的jpg、png、gif格式的图像文件',
		            fieldLabel: '文件',
		            name: 'imageFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传佛像",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
}
Ext.extend(KYB.SXD.BuddhaImagePanel,Ext.grid.GridPanel);
Ext.reg('buddhaImagePanel',KYB.SXD.BuddhaImagePanel);