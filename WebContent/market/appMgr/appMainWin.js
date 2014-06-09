Ext.ns('KYB.MARKET');

KYB.MARKET.AppMainWindow=function(url,fn){

	var fields=[{
		name : 'appId',
		type : 'int'
	},{
		name : 'appName',
		type : 'string'
	},{
		name : 'appCategory',
		type : 'string'
	},{
		name : 'appVersionName',
		type : 'string'
	},{
		name : 'appPic',
		type : 'string'
	},{
		name : 'packageName',
		type : 'string'
	},{
		name : 'appPath',
		type : 'string'
	}];
	
	var appCategoryCombox=new KYB.AppCategoryCombox({
		width : 120
	});
	var store = new Ext.data.JsonStore({
		url : context + '/market/appmgr/queryAllAppInfo.action',
		method : 'POST',
		root : 'appInfos',
		totalProperty : 'total',
		autoLoad : true,
		autoDestroy : true,
		baseParams:{
			appCategory:null
		},
		sortInfo: {
            field    : 'appName',
            direction: 'ASC'
        },
		fields : fields,
		listeners :{
			load : function(s, r, o){
				updateCount(s.getCount());
				var dv=Ext.getCmp("phones");
				if(dv){
					dv.bindStore(s);
				}
			}
		}
	});
	
	var tpl=new Ext.XTemplate(
        '<ul>',
            '<tpl for=".">',
                '<li class="phone">',
                    '<img width="70" height="70" src="{appPic}"/>',
                    '<strong>{appName}</strong>',
                    '<span>{appVersionName}</span>',
                '</li>',
            '</tpl>',
        '</ul>'
    );
	
	var dataview = new Ext.DataView({
        store: [],
        tpl  : tpl,
        plugins : [
            new Ext.ux.DataViewTransition({
                duration  : 550,
                idProperty: 'id'
            })
        ],
        id: 'phones',
        itemSelector: 'li.phone',
        overClass   : 'phone-hover',
        simpleSelect: true,
        singleSelect: true,
        multiSelect : true,
        autoScroll  : true
    });
    
    var tbar = [{
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : refresh
	},'-',{
		xtype:'label',
		text:'分类'
	},appCategoryCombox
	,'->',{
		id : 'appCount',
		xtype :'label',
		text :"统计"
	}];
	
	KYB.MARKET.AppMainWindow.superclass.constructor.call(this, {
		title : 'APP应用',
		id : 'appMainWin',
		loadMask : true,
		resizable : false,
		modal : true,
		constrain : true,
		width : 700,
		height : 615,
		layout:'fit',
		items:dataview,
		tbar : tbar,
		buttons:[{
			text : '确定',
			handler:enterSelected
		},{
			text : '撤消',
			handler: cancelSelected
		}]
	});

	function refresh() {
		store.setBaseParam('appCategory',null);
		store.load();
	};
	appCategoryCombox.on("select",function(cb,r,i){
		store.setBaseParam('appCategory',r.data.categoryValue);
		store.load();
	});
	function updateCount(count){
		Ext.getCmp('appCount').setText("统计(数量)："+count);
	};
	function enterSelected(){
		if(dataview.getSelectionCount()>0){
			var selRecords=dataview.getSelectedRecords();
			Ext.Ajax.request({
				url : context + url,
				method : 'POST',
				jsonData : {
					appInfos : encodeRecordsToJsonArray(selRecords)
				},
				callback : function(opts, suc, res) {
					var ds = Ext.decode(res.responseText);
					if (ds.errorMessage) {
						showErrorMsg(ds.errorMessage);
					} else {
						showInfoMsg("添加成功！");
						fn.call(this, ds);
						Ext.getCmp('appMainWin').close();
					}
				}
			});
		}else{
			showWarnMsg("没有选中任务APP，请选择。");
		}
	};
	function cancelSelected(){
		dataview.clearSelections();
	}
}
Ext.extend(KYB.MARKET.AppMainWindow,Ext.Window);
Ext.reg('appMainWin',KYB.MARKET.AppMainWindow);