Ext.onReady(function() {
	Ext.QuickTips.init();
	var text = '此项不能为空！';
	// 截取URL参数。
	// var url_params=window.location.search;
	var url_params = '?uid=c81e728d9d4c2f636f067f89cc14862c&ein=4c9408f6e7b9c4da427960d1f8b23614';
	var uid = url_params.substring(url_params.indexOf('=') + 1, url_params
					.indexOf('&'));
	var ein = url_params.substring(url_params.lastIndexOf('=') + 1);

	var pwdForm = new Ext.FormPanel({
				id : 'pwdForm',
				frame : true,
				monitorValid : true,
				border : false,
				padding : '20px',
				defaultType : 'textfield',
				defaults : {
					allowBlank : false
				},
				items : [{
							xtype : 'hidden',
							name : 'uid',
							value : uid
						}, {
							xtype : 'hidden',
							name : 'ein',
							value : ein
						}, {
							fieldLabel : '新密码',
							id : 'pwd',
							name : 'pwd',
							blankText : text,
							minLength : 6,
							minLengthText : '密码不能少于6位！',
							inputType : 'password'
						}, {
							fieldLabel : '确认密码',
							name : 'dpwd',
							blankText : text,
							inputType : 'password',
							initialPassField : 'pwd',
							vtype : 'password'
						}],
				buttons : [{
							text : '修改',
							formBind : true,
							handler : modify
						}, {
							text : '重置',
							handler : reset
						}]
			});
	var pwdWin = new Ext.Window({
				title : '修改密码',
				id : 'pwdWin',
				applyTo : 'div_pwd',
				width : 400,
				border : false,
				frame : true,
				closable : false,
				draggable : false,
				resizable : false,
				items : [pwdForm]
			});
	pwdWin.show().center();
	function modify() {
		var formDs = pwdForm.getForm().getValues();
		Ext.Ajax.request({
					url : context + '/main/modifyInitPwd.action',
					method : 'POST',
					jsonData : formDs,
					callback : function(opt, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (!ds.errorMessage) {
							Ext.Msg.confirm('提示', '密码修改成功，点击确认按纽返回登录界面。',
									function(btn) {
										if (btn == 'yes') {
											window.location.href = context
													+ "/index.html";
										}
									});
						} else {
							showErrorMsg(ds.errorMessage);
						}
					}
				});
	}
	function reset() {
		pwdForm.getForm().reset();
	}

});
