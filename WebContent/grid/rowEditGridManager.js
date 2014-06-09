Ext.ns('YSB.PROJS.GRID');

YSB.PROJS.GRID.RowEditGridPanel = function() {
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

	var sercombox = new ServiceCombox();
	var procombox = new ProvinceCombox();

	var editCms = [{
				header : '<div align=center>手机号码</div>',
				dataIndex : 'mobile',
				align : 'right',
				width : 150,
				editor : new Ext.form.TextField({allowBlank: false})
			}, {
				header : '<div align=center>识别码</div>',
				dataIndex : 'imsi',
				align : 'right',
				width : 150,
				editor : new Ext.form.TextField({allowBlank: false})
			}, {
				header : '<div align=center>创建时间</div>',
				dataIndex : 'createTime',
				align :'right',
				width : 150,
				editor : new Ext.form.DateField({
							format : 'Y-m-d H:i:s',
							editable : false,
							allowBlank: false
						}),
				renderer : formatDateTime
			}, {
				header : '<div align=center>服务商</div>',
				width : 100,
				align : 'center',
				dataIndex : 'service',
				editor : sercombox,
				renderer : comboRenderer(sercombox)
			}, {
				header : '<div align=center>卡类型</div>',
				dataIndex : 'cardType',
				align : 'right',
				width : 150,
				editor : new Ext.form.TextField({allowBlank: false})
			}, {
				header : '<div align=center>省份</div>',
				width : 100,
				dataIndex : 'province',
				align:'right',
				editor : procombox,
				renderer : comboRenderer(procombox)
			}, {
				header : '<div align=center>城市</div>',
				width : 100,
				dataIndex : 'city',
				align : 'right',
				editor : new Ext.form.TextField({allowBlank: false})
			}];

	var tbar = [{
				text : '增加',
				iconCls : 'icon-add',
				handler : doAdd
			}, '-', {
				text : '删除',
				iconCls : 'icon-delete',
				handler : doDelete
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					doRefresh(store);
				}
			}];

	var bbar = createPageBar(store, 25);

	var sm = new Ext.grid.RowSelectionModel({
				singleSelect : true
			});

	var rowEditor = new Ext.ux.grid.RowEditor({
				saveText : '保存',
				cancelText : '取消',
				commitChangesText : '你需要保存你的修改或取消！',
				errorText : '出错了！',
				errorSummary:false,
				listeners :{
					afteredit : afterEditDo
				}
			});
	
	var rowEditGrid = new Ext.grid.GridPanel({
				autoScroll : true,
				stripeRows : true,
				columnLines : true,
				border : false,
				tbar : tbar,
				bbar : bbar,
				columns : editCms,
				sm : sm,
				store : store,
				plugins : [rowEditor]
			});
	function afterEditDo(g,or,nr,i){
		Ext.Ajax.request({
			url:context +'/mobile/saveOrUpdateMobileInfos.action',
			method :'POST',
			jsonData :{mobileInfos :[nr.data]},
			callback:function(opt,suc,res){
				var ds =Ext.decode(res.responseText);
				if(ds.errorMessage){
					showMessage(ds.errorMessage);
				}else{
					if(nr.data.id){
						nr.commit();
					}else{
						store.reader.update(nr,ds.mobileInfos);
					}
				}
			}
		})
	}
	/*
	 * 增加一行
	 */
	function doAdd() {
		var rowR = store.recordType;
		var rowD = new rowR({});
		rowEditor.stopEditing();
		store.insert(0, rowD);
		sm.selectRow(0);
		rowEditor.startEditing(0);

	}
	/*
	 * 删除一行
	 */
	function doDelete() {
		var selRowR = sm.getSelected();
		if (selRowR && !isNull(selRowR.data.id)) {
			Ext.Ajax.request({
						url : context + "/mobile/deleteMobileInfo.action",
						method : 'POST',
						jsonData : {
							mobileInfo : selRowR.data
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (ds.errorMessage) {
								showMessage(ds.errorMessage);
							} else {
								store.remove(selRowR);
								showMessage("删除成功！");
							}
						}
					});
		} else {
			store.remove(selRowR);
		}
	}
	YSB.PROJS.GRID.RowEditGridPanel.superclass.constructor.call(this, {
				id : 'rowEditGridPanel',
				title : '手机用户信息(行编辑器)',
				layout : 'fit',
				closable : true,
				border : false,
				items : [rowEditGrid]
			});
}
Ext.extend(YSB.PROJS.GRID.RowEditGridPanel, Ext.Panel);
Ext.reg('rowEditGridPanel',YSB.PROJS.GRID.RowEditGridPanel);
