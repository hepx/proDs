Ext.ns('KYB.SXD');

KYB.SXD.MusicPanel=function(){
	var url_='/sxd/music/saveOrUpdateBuddhaMusics.action';
	var fields=[{
		name : 'musicId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'musicTitle',
		type : 'string'
	},{
		name : 'author',
		type : 'string'
	},{
		name : 'musicPath',
		type : 'string'
	},{
		name : 'lyricPath',
		type : 'string'
	},{
		name : 'musicType',
		type : 'string'
	}];
	
	var musicTypeCombox =new KYB.SXD.MusicTypeCombox();
	
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/music/queryBuddhaMusics.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'buddhaMusics',
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
		text : '上传附件',
		iconCls : 'icon-folder',
		menu : {
			items: [{
				text : '上传一个佛音文件',
				iconCls : 'icon-music',
				handler : doUploadMusic
			},{
				text : '上传一个歌词文件',
				iconCls : 'icon-txt',
				handler : doUploadLyric
			}
		]}
		
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
					header : "佛音标题",
					width : 300,
					id:'musicTitle',
					dataIndex : 'musicTitle'
				},{
					header : '作者',
					width : 150,
					dataIndex : 'author'
				},{
					header : '所属类型',
					width : 150,
					dataIndex : 'musicType',
					renderer : comboRenderer(musicTypeCombox)
				},{
					header : '佛音文件',
					width : 200,
					dataIndex : 'musicPath',
					renderer : linkRenderer
				},{
					header : '歌词文件',
					width : 200,
					dataIndex : 'lyricPath',
					renderer : linkRenderer
				}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.MusicPanel.superclass.constructor.call(this,{
		title : '佛音列表',
		id : 'musicPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoExpandColumn : 'musicTitle',
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
		new KYB.SXD.MusicAddorUpWin(
		{title : '增加佛音'},
		null,
		url_,
		refresh
		).show();
	};
	
	function doModify(){
		var ds = sm.getSelected();
		if (ds) {
			new KYB.SXD.MusicAddorUpWin(
			{title : '修改佛音'}, 
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
				url : context + '/sxd/music/deleteBuddhaMusics.action',
				method : 'POST',
				jsonData : {
					buddhaMusics : encodeRecordsToJsonArray(records)
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
	
	function doUploadLyric(){
		var ds = sm.getSelections()[0];
		if (ds) {
			var url_=context+'/sxd/music/uploadLyric.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'musicId',
					value : ds.data.musicId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-txt-music',
		            emptyText: '选择一个小于100KB的LRC格式的歌词文件',
		            fieldLabel: '文件',
		            name: 'musicFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传佛音歌词",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
	
	function doUploadMusic(){
		var ds = sm.getSelected();
		if (ds) {
			var url_=context+'/sxd/music/uploadMusic.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'musicId',
					value : ds.data.musicId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-music-music',
		            emptyText: '选择一首小于10M的MP3格式的音乐',
		            fieldLabel: '音乐',
		            name: 'musicFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传佛音",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
}
Ext.extend(KYB.SXD.MusicPanel,Ext.grid.GridPanel);
Ext.reg('musicPanel',KYB.SXD.MusicPanel);