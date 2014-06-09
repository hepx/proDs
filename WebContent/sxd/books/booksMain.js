Ext.ns('KYB.SXD');

KYB.SXD.BooksPanel=function(){
	var fields=[{
		name : 'bookId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'bookTitle',
		type : 'string'
	},{
		name : 'bookImagePath',
		type : 'string'
	},{
		name : 'bookPath',
		type : 'string'
	},{
		name : 'backMusicPath',
		type : 'string'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/sxd/books/queryBooks.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'buddhaBooks',
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
		text : '上传附件',
		iconCls : 'icon-folder',
		menu : {
			items: [{
				text : '上传一张图片',
				iconCls : 'icon-img',
				handler : doUploadImage
			},{
				text : '上传一个TXT文件',
				iconCls : 'icon-txt',
				handler : doUploadTxt
			},{
				text : '上传一个音乐文件',
				iconCls : 'icon-music',
				handler : doUploadMusic
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
					header : '经书图片',
					width : 80,
					dataIndex : 'bookImagePath',
					renderer : imageRenderer
				},{
					header : "经书标题",
					width : 300,
					id:'bookTitle',
					dataIndex : 'bookTitle'
				},{
					header : '经书文件',
					width : 200,
					dataIndex : 'bookPath',
					renderer : linkRenderer
				},{
					header : '背景音乐',
					width : 200,
					dataIndex : 'backMusicPath',
					renderer : linkRenderer
				}]
	});
	var pageBar = createPageBar(store);
	KYB.SXD.BooksPanel.superclass.constructor.call(this,{
		title : '经书列表',
		id : 'booksPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		autoExpandColumn : 'bookTitle',
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
		Ext.MessageBox.show({
          	title: '创建经书',
           	msg: '请输入经书标题:',
           	width:300,
           	buttons: Ext.MessageBox.OKCANCEL,
           	multiline: true,
           	fn: function(btn,text){
				if(btn==="ok"&&text){
					Ext.Ajax.request({
						url : context+'/sxd/books/createBook.action',
						method : 'POST',
						jsonData : {
							buddhaBook : {
								bookTitle : text,
								createUser : USER_NAME
							}
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showErrorMsg(ds.errorMessage);
							} else {
								//showInfoMsg("增加成功！");
								refresh();
							}
						}
					});
				}
           	}
       	});
	};
	
	function doDel() {
		var records = sm.getSelections();
		if (records && records.length != 0) {
			Ext.Ajax.request({
				url : context + '/sxd/books/deleteBooks.action',
				method : 'POST',
				jsonData : {
					buddhaBooks : encodeRecordsToJsonArray(records)
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
		var ds = sm.getSelections()[0];
		if (ds) {
			var url_=context+'/sxd/books/uploadImage.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'bookId',
					value : ds.data.bookId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-image-book',
		            emptyText: '选择一张小于100KB的JPG、PNG、GIF格式的图片',
		            fieldLabel: '图片',
		            name: 'bookFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传经文图片",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
	
	function doUploadTxt(){
		var ds = sm.getSelections()[0];
		if (ds) {
			var url_=context+'/sxd/books/uploadTxt.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'bookId',
					value : ds.data.bookId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-txt-book',
		            emptyText: '选择一个小于100KB的TXT格式的内容文件',
		            fieldLabel: '文件',
		            name: 'bookFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传经文内容",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
	
	function doUploadMusic(){
		var ds = sm.getSelected();
		if (ds) {
			var url_=context+'/sxd/books/uploadMusic.action';
			new KYB.SingleFileUploadWin({
				items: [{
					xtype : 'textfield',
					hidden : true,
					name : 'bookId',
					value : ds.data.bookId
				},{
		            xtype: 'fileuploadfield',
		            id: 'form-music-book',
		            emptyText: '选择一首小于10M的MP3格式的音乐',
		            fieldLabel: '音乐',
		            name: 'bookFile',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'icon-addFile'
		            }
        		}]
			},"上传背景音乐",ds.data,url_, refresh).show();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	}
}
Ext.extend(KYB.SXD.BooksPanel,Ext.grid.GridPanel);
Ext.reg('booksPanel',KYB.SXD.BooksPanel);