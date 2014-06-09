Ext.ns("KYB");

KYB.Mainview=function(){	
	KYB.Mainview.superclass.constructor.call(this,{
		title:'主界面',
		region : 'center',
		id:'mainview',
		autoscroll : true,
		layout:'fit',
		items:[]
	});
}
Ext.extend(KYB.Mainview,Ext.Panel);
Ext.reg('mainview', KYB.Mainview);
