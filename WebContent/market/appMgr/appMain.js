Ext.ns('KYB.MARKET');

KYB.MARKET.AppMainPanel=function(){
	var url_='/market/appmgr/saveOrUpdateAppInfo.action';
	var fields=[{
		name : 'appId',
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
		name : 'appCategory',
		type : 'string'
	},{
		name : 'appDesc',
		type : 'string'
	},{
		name : 'appVersionCode',
		type : 'string'
	},{
		name : 'appVersionName',
		type : 'string'
	},{
		name : 'appPic',
		type : 'string'
	},{
		name : 'appPath',
		type : 'string'
	},{
		name : 'downloads',
		type : 'int'
	},{
		name : 'packageName',
		type : 'string'
	},{
		name : 'sdkSupport',
		type : 'string'
	},{
		name : 'appSize',
		type : 'string'
	},{
		name : 'fee',
		type : 'float'
	}];
	
	var appCategoryCombox=new KYB.AppCategoryCombox();
	if(appCategoryCombox.store.totalLength === undefined){
		appCategoryCombox.store.load();
	}
	
	var store = new Ext.data.JsonStore({
		url : context + '/market/appmgr/queryAppInfos.action',
		method : 'POST',
		root : 'appInfos',
		totalProperty : 'total',
		autoLoad : true,
		autoDestroy : true,
		baseParams: {
			start : 0,
			limit : 20
		},
		fields : fields
	});
	var tbar = [ {
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	}, '-',{
		text : '更新APK包',
		iconCls : 'icon-up',
		handler : updateApk
	}, '-',{
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	}];
	var ver_expander = new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
            '<p><b>版本号(升级用):</b> {appVersionCode}</p><br>'+
            '<p><b>版本号(展示用):</b> {appVersionName}</p><br>'+
            '<p><b>应用说明:</b> {appDesc}</p>'
        )
    });
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			width : 100,
			menuDisabled : true
		},
		columns : [ver_expander,{
			header : '<div align=center>创建人</div>',
			menuDisabled : false,
			dataIndex : 'createUser'
		}, {
			header : '<div align=center>创建时间</div>',
			align : 'right',
			width : 130,
			dataIndex : 'createTime',
			renderer : formatDateTime
		}, {
			header : '<div align=center>应用名</div>',
			dataIndex : 'appName'
		}, {
			header : '<div align=center>包名</div>',
			width:150,
			dataIndex : 'packageName'
		},{
			header : '<div align=center>分类</div>',
			align : 'center',
			width:80,
			dataIndex : 'appCategory',
			renderer : comboRenderer(appCategoryCombox)
		},{
			header : '<div align=center>版本号(升级)</div>',
			align : 'center',
			width: 80,
			dataIndex : 'appVersionCode'
		},{
			header : '<div align=center>版本号(展示)</div>',
			align : 'center',
			width: 80,
			dataIndex : 'appVersionName'
		},{
			header : '<div align=center>图标</div>',
			align:'center',
			width : 80,
			dataIndex : 'appPic',
			renderer : imageRenderer
		},{
			header : '<div align=center>APK</div>',
			align : 'right',
			width : 120,
			dataIndex : 'appPath',
			renderer : linkRenderer
		},{
			header : '<div align=center>大小</div>',
			width: 80,
			align: 'center',
			dataIndex: 'appSize'
		},{
			header: '<div align=center>资费</div>',
			dataIndex : 'fee',
			align: 'center',
			width: 80,
			renderer: function(fee){
				if(!fee){
					return '免费';
				}else{
					return fee;
				}
			}
		},{
			header: '<div align=center>平台要求</div>',
			width: 80,
			dataIndex: 'sdkSupport',
			renderer: function(v){
				return v+"+";
			}
		},{
			header : '<div align=center>下载次数</div>',
			align : 'right',
			width: 80,
			dataIndex : 'downloads'
		}]
	});
	var sm = new Ext.grid.RowSelectionModel();
	var pageBar = createPageBar(store);
	KYB.MARKET.AppMainPanel.superclass.constructor.call(this, {
		title : '用应列表',
		id : 'appMainPanel',
		autoScroll : true,
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		//viewConfig : {autoFill:true},
		tbar : tbar,
		bbar : pageBar,
		plugins: ver_expander,
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
		Ext.Ajax.request({
			url : context + '/market/appmgr/deleteAppInfos.action',
			method : 'POST',
			jsonData : {
				appInfos : encodeRecordsToJsonArray(records)
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
	
	function updateApk(){
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var ds = sm.getSelected();
		var url=context+'/market/appmgr/updateApp.action';
		new KYB.SingleFileUploadWin({
			items: [{
				xtype : 'textfield',
				hidden : true,
				name : 'appId',
				value : ds.data.appId
			},{
	            xtype: 'fileuploadfield',
	            id: 'form-market-app',
	            emptyText: '选择一个小于10M的APK安装文件',
	            fieldLabel: '更新文件',
	            name: 'app',
	            buttonText: '',
	            buttonCfg: {
	                iconCls: 'icon-addFile'
	            }
    		}]
		},"更新APK",ds.data,url,function(apk){
			ds.set('appVersionCode',apk.appVersionCode);
			ds.set('appVersionName',apk.appVersionName);
			ds.set('appPath',apk.appPath);
			ds.set('sdkSupport',apk.sdkSupport);
			ds.set('appSize',apk.appSize);
			ds.commit();
		}).show();
	}
}
Ext.extend(KYB.MARKET.AppMainPanel,Ext.grid.GridPanel);
Ext.reg('appMainPanel',KYB.MARKET.AppMainPanel);