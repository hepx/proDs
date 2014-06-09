/**
 * 登录JS
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	var logoPanel = new Ext.Panel({
				baseCls : 'x-plain',
				id : 'login-logo',
				region : 'center'
			})
	var loginForm = new Ext.FormPanel({
		labelWidth : 120,
		height : 70,
		region : 'south',
		bodyStyle : 'padding:10px 0px 0px 50px',
		defaults : {
			width : 200
		},
		defaultType : 'textfield',
		items : [{
					fieldLabel : '用 户 名',
					name : 'userName',
					id : 'userName',
					style : 'icon-user',
					cls : 'icon-user',
					allowBlank : false,
					frame : true,
					enableKeyEvents : true,
					listeners : {
						keyup : function(tf, e) {
							if (e.keyCode == 13) {
								Ext.get("password").focus();
							}
						}
					}
				}, {
					fieldLabel : '密码',
					name : 'password',
					id : 'password',
					cls : 'icon-lock',
					allowBlank : false,
					inputType : 'password',
					enableKeyEvents : true,
				 	listeners : {
						keyup : function(tf, e) {
							if (e.keyCode == 13) {
								login();
							}
						}
					}
				}]/**,{
					xtype:'panel',
					layout: 'table',
					border : false,
					width : '100%',
					items:[{
						layout:'form',
						border :false,
						items:[{
						 	fieldLabel : '验证码',
						 	xtype : 'textfield',
						 	id : 'randCode',
						 	name : 'randCode',
						 	cls: 'icon-barcode',
						 	allowBlank : false,
						 	width:80,
						 	enableKeyEvents : true,
						 	listeners : {
								keyup : function(tf, e) {
									if (e.keyCode == 13) {
										login();
									}
								}
							}
						}]
					},{
						xtype : 'panel',
						padding :'0 0 0 5',
						id : 'captchaPanel',
						border :false,
						html : '<img id="captcha" src="'+context+'/Kaptcha.jpg">'
					}]
				}]**/
			});
	var loginWindow = new Ext.Window({
				title : '用户登陆',
				applyTo : 'login',
				width : 530,
				height : 300,
				id : 'loginWindow',
				draggable : false,
				closable : false,
				resizable : false,
				layout : 'border',
				items : [logoPanel, loginForm],
				buttons : [{
					xtype : 'tbsplit',
					text : '登录',
					iconCls : 'icon-login',
					handler : login,
					menu : {
						items : [{
							text : '忘记密码',
							iconCls : 'icon-help',
							handler : findPwd
						}]
					}
				},/* {
					text : '注册',
					iconCls : 'icon-guestinfo',
					handler : regist
				}, */{
					text : '重置',
					iconCls : 'icon-redo',
					handler : reset
				}]
			});
	loginWindow.show().center();

	/**
	 * 刷新验证码
	 */
	/**
	Ext.get("captchaPanel").on('click',function(){
		Ext.getCmp('captchaPanel').update('<img id="captcha" src="'+context+'/Kaptcha.jpg">');
	});**/
	
	/**
	 * 用户登录
	 */
	function login() {
		var para = loginForm.getForm().getValues();
		if (!para.userName) {
			showWarnMsg("未录入用户名，请重新录入！");
			return;
		}
		if (!para.password) {
			showWarnMsg("未录入密码，请重新录入！")
			return;
		}
//		if (!para.randCode) {
//			showWarnMsg("未录入验证码，请重新录入！")
//			return;
//		}
		showMask(Ext.getBody(),'正在验证用户信息，请稍候......');
		Ext.Ajax.request({
					url : context + '/main/loginIn.action',
					jsonData : {
						userInfo : para
					},
					callback : function(options, success, response) {
						var ds = Ext.decode(response.responseText);
						hideMask();
						if (ds.errorMessage) {
							showErrorMsg(ds.errorMessage);
						} else {
							window.location = context + "/main/main.action?rid="+Math.random();
						}
					}
				});
	}
	var registWin;
	/**
	 * 注册用户
	function regist() {
		var url = "/main/registUser.action";
		registWin = new KYB.UserRegistWin({
					title : "新用户注册"
				}, null, false, url, registAfterDo).show().center();
	}*/
	
	/**
	 * 注册成功后。将注册的信息填充到登录页面。以便登录
	function registAfterDo(ds) {
		if (registWin) {
			registWin.close();
		}
		loginForm.getForm().setValues(ds);
	}*/
	function reset() {
		loginForm.getForm().reset();
		Ext.get("userName").focus();
	}
	/**
	 * 找回密码
	 */
	function findPwd(btn) {
		var findPwdWin = new Ext.Window({
			title : '找回密码',
			id : 'findPwdWin',
			modal : true,
			width:400,
			resizable : false,
			border : false,
			constrain : true,
			items : [{
				xtype : 'form',
				id : 'findPwdForm',
				border : false,
				frame : true,
				defaultType : 'textfield',
				padding : '20px',
				monitorValid : true,
				defaults : {
					allowBlank : false
				},
				items : [{
							fieldLabel : '用户名',
							name : 'userName'
						}, {
							fieldLabel : '邮箱地址',
							vtype : 'email',
							name : 'email'
						}],
				buttons : [{
					text : '下一步',
					iconCls : 'icon-next',
					handler : function() {
						showMask(Ext.getBody(),'正在验证信息......');
						var formDs= Ext.getCmp('findPwdForm').getForm().getValues();
						Ext.Ajax.request({
							url : context + '/main/findPwd.action',
							method : 'POST',
							jsonData : {
								userInfo :formDs
							},
							callback : function(opt, suc, res) {
								hideMask();
								var ds = Ext.decode(res.responseText);
								if (!ds.errorMessage) {
									showInfoMsg("系统已发送了一封找回密码的邮件给你，请登录你的邮箱操作！");
									Ext.getCmp('findPwdWin').close();
								} else {
									showErrorMsg(ds.errorMessage);
								}
							}
						});
					}
				}, {
					text : '重置',
					iconCls : 'icon-redo',
					handler : function() {
						Ext.getCmp('findPwdForm').getForm().reset();
					}
				}]
			}]
		});
		findPwdWin.show().center();
	}
});
