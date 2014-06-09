KYB.MainHeader = function() {
	KYB.MainHeader.superclass.constructor.call(this);
}
KYB.MainHeader = Ext.extend(Ext.Panel, {
	id : 'headerPanel',
	height : 75,
	region : 'north',
	layout : 'column',
	margins : '0 5 5 5',
	initComponent : function() {
		Ext.apply(this, {
			items : [{
						columnWidth : .55,
						border : false,
						layout : 'fit',
						items : [{
									xtype : 'panel',
									id : 'logo-header',
									border : false,
									html : '<img src="images/header.png">'
								}]
					}, {
						columnWidth : .45,
						border : false,
						layout : 'fit',
						items : [{
							xtype : 'panel',
							id : 'control-header',
							border : false,
							tbar : [{
								text : 'Welcome :  ' + '<b>' + USER_NAME
										+ '</b>'
							}, '-', {
								text : '当前时间 : <span id=txt></span>',
								iconCls : 'icon-system'
							}, '->', {
								text : '<b>注销</b>',
								iconCls : 'icon-logout',
								handler : function() {
									Ext.Msg.show({
												title : '注销系统:',
												msg : '提示:注销系统前请注意保存数据,确定要注销吗?',
												buttons : Ext.Msg.YESNO,
												fn : function(btn) {
													if (btn == 'yes') {
														window.location = context
																+ "/main/login.action";
														// Ext.Ajax.request({
														// url:context+'/main/login.action',
														// method:'POST'
														// });
													}
												},
												animEl : 'elId',
												icon : Ext.MessageBox.QUESTION
											});
								}
							}]
						}]
					}]
		});

		Ext.TaskMgr.start(task);
		KYB.MainHeader.superclass.initComponent.apply(this, arguments);
	}
});
Ext.reg('mainheader', KYB.MainHeader)