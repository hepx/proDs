Ext.BLANK_IMAGE_URL='extjs/resources/images/default/s.gif'
Ext.onReady(function(){
	Ext.QuickTips.init();
	setTimeout(function() {
		Ext.get('loading').fadeOut({
					remove : true
				});
	}, 500);
	new Ext.Viewport({
		layout: 'border',
	    items: [{//头部
			xtype : 'mainheader'
	    }, {//左则导航
	        xtype:'mainmenu'
	    }, {//下方
	        region: 'south',
	        html: '版权所有2012',
	        height: 30,
	        minHeight: 30
	    }, {//中间
	        xtype:'maintab'
	    }]
	});
});