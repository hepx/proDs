Ext.ns('KYB.BDG');

KYB.BDG.BdgAppWallMainPanel=function(){
	var fields=[{
		name : 'appWallId',
		type : 'int'
	},{
		name : 'createUser',
		type : 'string'
	},{
		name : 'createTime',
		type : 'date',
		dateFormat : 'Y-m-d\\TH:i:s'
	},{
		name : 'appName',
		type : 'string'
	},{
		name : 'appVersionCode',
		type : 'int'
	},{
		name : 'appVersionName',
		type : 'string'
	},{
		name : 'appPic',
		type : 'string'
	},{
		name : 'downloads',
		type : 'int'
	},{
		name : 'sort',
		type : 'int'
	},{
		name : 'isPush',
		type : 'boolean'
	}];
	
	var store = new Ext.data.JsonStore({
		url : context + '/bdg/appwall/queryBdgAppWalls.action',
		method : 'POST',
		root : 'bdgAppWalls',
		totalProperty : 'total',
		autoLoad : true,
		autoDestroy : true,
		baseParams: {
			start : 0,
			limit : 20
		},
		fields : fields
	});
	var tbar = [{
		text : '从市场添加APP',
		iconCls : 'icon-add',
		handler : doAdd
	}, '-', {
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	}, {
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			width : 150,
			menuDisabled : true
		},
		columns : [{
			header : '<div align=center>添加人</div>',
			width : 120,
			menuDisabled : false,
			dataIndex : 'createUser'
		}, {
			header : '<div align=center>添加时间</div>',
			align : 'right',
			dataIndex : 'createTime',
			renderer : formatDateTime
		}, {
			header : '<div align=center>应用名</div>',
			dataIndex : 'appName'
		}, {
			header : '<div align=center>版本号(升级)</div>',
			align : 'center',
			dataIndex : 'appVersionCode'
		}, {
			header : '<div align=center>版本号(展示)</div>',
			align : 'center',
			dataIndex : 'appVersionName'
		}, {
			header : '<div align=center>图标</div>',
			align:'center',
			width : 85,
			dataIndex : 'appPic',
			renderer : imageRenderer
		}, {
			header : '<div align=center>序号(可双击修改)</div>',
			align : 'center',
			width : 100,
			dataIndex : 'sort',
			editor : new Ext.form.TextField({
				listeners : {
					change:function(t, newValue, oldValue ){
						updateSort(sm.getSelected().data.appWallId,newValue);
					}
				}
			})
		}, {
			header : '<div align=center>下载次数</div>',
			align : 'right',
			width : 100,
			dataIndex : 'downloads'
		}/*, {
			header: '<div align=center>是否推送</div>',
			align: 'center',
			width: 100,
			dataIndex: 'isPush',
			renderer:function(val){
				if(val==0){
					return '<span style="color:red;">NO</span>';
				}else{
					return '<span style="color:green;">YES</span>';
				}
			}
		},  {
            header:'<div align=center>操作</div>',
            xtype: 'actioncolumn',
            width: 50,
            align: 'center',
            items: [{
            		iconCls: 'push-col',
                    tooltip: '启动推送',
                    handler: function(grid, rowIndex, colIndex) {
                    	var rec = store.getAt(rowIndex);
                    	if(!rec.data.isPush){
                       		updatePush(rec,true);
                    	}
                    }
            	},{
            		iconCls: 'unpush-col',
                    tooltip: '取消推送',
                    handler: function(grid, rowIndex, colIndex) {
                        var rec = store.getAt(rowIndex);
                        if(rec.data.isPush){
                        	updatePush(rec,false);
                        }
                    }
            	}
            ]
        }*/]
	});
	var sm = new Ext.grid.RowSelectionModel();
	var pageBar = createPageBar(store);
	KYB.BDG.BdgAppWallMainPanel.superclass.constructor.call(this, {
		title : '吊丝不寂寞软件墙',
		id : 'bdgAppWallPanel',
		autoScroll : true,
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		viewConfig : {autoFill:true},
		tbar : tbar,
		bbar : pageBar,
		cm : cm,
		sm : sm,
		store : store
	});

	function refresh() {
		pageBar.doRefresh();
	}

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/bdg/appwall/deleteBdgAppWalls.action',
			method : 'POST',
			jsonData : {
				bdgAppWallsTemp : encodeRecordsToJsonArray(records)
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
	};

	function updateSort(appWallId,sort){
		if(appWallId&&sort){
			Ext.Ajax.request({
				url: context+'/bdg/appwall/updateBdgAppWallSort.action',
				method: 'POST',
				jsonData: {
					appWallId:appWallId,
					sort:sort
				},
				callback:function(opts,suc,res){
					var ds=Ext.decode(res.responseText);
					if(ds.errorMessage){
						showErrorMsg(ds.errorMessage);
					}else{
						store.commitChanges();
					}
				}
			});
		}
	}
	/**
	function updatePush(re,isPush){
		var appWallId=re.data.appWallId;
		if(appWallId){
			Ext.Ajax.request({
				url: context+'/bdg/appwall/updateBdgAppWallPush.action',
				method: 'POST',
				jsonData:{
					appWallId: appWallId,
					isPush: isPush
				},
				callback:function(opts,suc,res){
					var ds=Ext.decode(res.responseText);
					if(ds.errorMessage){
						showErrorMsg(ds.errorMessage);
					}else{
						re.set('isPush',isPush);
						re.commit();
					}
				}
			});
		}
	}**/
	
	function doAdd() {
		var url_='/bdg/appwall/addBdgAppWalls.action';
		new KYB.MARKET.AppMainWindow(url_,refresh).show();
	};
}
Ext.extend(KYB.BDG.BdgAppWallMainPanel,Ext.grid.EditorGridPanel);
Ext.reg('bdgAppWallPanel',KYB.BDG.BdgAppWallMainPanel);