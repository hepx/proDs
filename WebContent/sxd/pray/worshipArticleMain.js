Ext.ns('KYB.SXD');

KYB.SXD.WorshipArticlePanel=function(){
	var url_='/sxd/pray/saveOrUpdateArticles.action';
	var fields=[{
		name : 'articleId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'articleName',
		type : 'string'
	},{
		name : 'articleDesc',
		type : 'string'
	},{
		name : 'articlePath',
		type : 'string'
	},{
		name : 'articleType',
		type : 'string'
	}];
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/pray/queryArticles.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'articles',
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
		text : '上传供品图像',
		iconCls : 'icon-img',
		handler : doUploadImage
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var articleTypeCombox=new KYB.SXD.ArticleTypeCombox();
	if(articleTypeCombox.store.totalLength === undefined){
		articleTypeCombox.store.load();
	}
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		columns : [sm,{
					header : '创建人',
					width : 100,
					dataIndex : 'createUser'
				},{
					header : '创建时间',
					dataIndex : 'createTime',
					width : 130,
					renderer : formatDateTime
				},{
					header : '供品分类',
					width : 100,
					dataIndex : 'articleType',
					renderer : comboRenderer(articleTypeCombox)
				},{
					header : "供品名称",
					width : 300,
					dataIndex : 'articleName'
				},{
					header : '描述',
					width : 350,
					id : 'articleDesc',
					dataIndex : 'articleDesc'
				},{
					header : '供品图像',
					width : 80,
					align : 'center',
					dataIndex : 'articlePath',
					renderer : function(val){
						return val?'<img src="'+(val)+'" width="40" height="60">':'';
					}
				}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.WorshipArticlePanel.superclass.constructor.call(this,{
		title : '供品列表',
		id : 'worshipArticlePanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoExpandColumn : 'articleDesc',
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store
	});
	
	function refresh(){
		pageBar.doRefresh();
	}
	
	function doAdd(){
		new KYB.SXD.WorshipArticleAddorUpWin(
		{title : '增加供品'},
		null,
		url_,
		refresh
		).show();
	};
	
	function doModify(){
		var ds = sm.getSelected();
		if (ds) {
			new KYB.SXD.WorshipArticleAddorUpWin(
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
				url : context + '/sxd/pray/deleteArticles.action',
				method : 'POST',
				jsonData : {
					articles : encodeRecordsToJsonArray(records)
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
			var url_=context+'/sxd/pray/uploadArticleImage.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'articleId',
					value : ds.data.articleId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-txt-article',
		            emptyText: '选择一个小于100KB的jpg、png、gif格式的图像文件',
		            fieldLabel: '文件',
		            name: 'articleFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传供品图像",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
}
Ext.extend(KYB.SXD.WorshipArticlePanel,Ext.grid.GridPanel);
Ext.reg('worshipArticlePanel',KYB.SXD.WorshipArticlePanel);