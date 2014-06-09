Ext.ns("KYB");

KYB.MainTab = function() {
	KYB.MainTab.superclass.constructor.call(this, {
			id : 'mainTab',
			region: 'center',
			border : true,
			plain:true,
			margins : '5 5 5 0',
			activeTab : 0,
			//plugins : new Ext.ux.TabCloseMenu(),
			layoutOnTabChange : true,
			items : [{
						xtype : 'mainview'
					}]
			});
}
Ext.extend(KYB.MainTab,Ext.TabPanel);
Ext.reg('maintab', KYB.MainTab);