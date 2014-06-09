Ext.ns('KYB.SXD');

KYB.SXD.NewsPanel=function(){
	var fields=[{
		name : 'newsId',
		type : 'int'
	},{
		name : 'newsType',
		type : 'string'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'newsTitle',
		type : 'string'
	},{
		name : 'newsDesc',
		type : 'string'
	},{
		name : 'newsUrl',
		type : 'string'
	}];
	
	var newsTypeCombox=new Ext.form.ComboBox({
		id : 'newsTypeCombox',
		triggerAction : 'all',
		typeAhead : true,
		lazyRender:true,
		mode: 'local',
		editable : false,
		allowBlank: false,
		store : new Ext.data.JsonStore({
					url:context+"/sxd/dict/queryDicts.action",
					method:'POST',
					autoLoad:true,
					root : 'dicts',
					baseParams : {
						dictGroup : 'newsType'					
					},
					fields : [{
							name : 'dictName',
							type : 'string'
						},{
							name : 'dictValue',
							type : 'string'
						}]
				}),
		hiddenName: 'newsType',
		valueField : 'dictValue',
		displayField : 'dictName'
	});
	
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/news/queryNews.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'newsList',
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
		text : '保存',
		iconCls : 'icon-save',
		handler : doSave
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
		defaults : {
			editor : new Ext.form.TextField()
		},
		columns : [sm,{
					header : '创建人',
					width : 100,
					dataIndex : 'createUser',
					editor : new Ext.form.TextField({
						readOnly:true
					})
				}, {
					header : '创建时间',
					dataIndex : 'createTime',
					width : 150,
					editor : new Ext.form.DateField({
							format : 'Y-m-d H:i:s',
							editable : false
						}),
					renderer : formatDateTime
				}, {
					header : '新闻类型',
					width : 100,
					dataIndex : 'newsType',
					editor : newsTypeCombox,
					renderer : comboRenderer(newsTypeCombox)
				},{
					header : "新闻标题",
					width : 200,
					dataIndex : 'newsTitle'
				},{
					header : '新闻概述',
					width : 250,
					dataIndex : 'newsDesc'
				},{
					header : '新闻地址',
					width : 320,
					dataIndex : 'newsUrl',
					renderer : function(val){
						if(val){
							return '<a href="'+val+'" target="_blank">'+val+'</a>'
						}
					}
				}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.NewsPanel.superclass.constructor.call(this,{
		title : '新闻列表',
		id : 'newsPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		tbar : tbar,
		cm : cm,
		sm : sm,
		viewConfig : {
			autoFill :true
		},
		bbar : pageBar,
		store : store
	});

	function doAdd(){
		var rowR =store.recordType;
		var r = new rowR({
			createUser:USER_NAME,
			createTime : new Date()
		});
		Ext.getCmp('newsPanel').stopEditing();
		store.insert(0, r);
		Ext.getCmp('newsPanel').startEditing(0, 3);
	};

	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/news/deleteNews.action',
				method : 'POST',
				jsonData : {
					newsList : encodeRecordsToJsonArray(records)
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
	
	function doSave() {
		var modfiyDs = store.getModifiedRecords();	
		if (!isNull(modfiyDs)) {
			Ext.Ajax.request({
				url : context
						+ '/sxd/news/saveOrUpdateNews.action',
				method : 'POST',
				jsonData : {
					newsList : encodeRecordsToJsonArray(modfiyDs)
				},
				callback : function(opts, suc, res) {
					var ds = Ext.decode(res.responseText);
					if (ds.errorMessage) {
						showErrorMsg(ds.errorMessage);
					} else {
						store.commitChanges();
						store.reload();
						showInfoMsg("保存成功！");
					}
				}
			});
		}
	}
}
Ext.extend(KYB.SXD.NewsPanel,Ext.grid.EditorGridPanel);
Ext.reg('newsPanel',KYB.SXD.NewsPanel);