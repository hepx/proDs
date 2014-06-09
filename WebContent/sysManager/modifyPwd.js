Ext.ns("KYB");

KYB.ModifyPwdPanel = function() {
	var modifyPwdForm = new Ext.FormPanel({
				id : 'modifyPwdForm',
				defaultType : 'textfield',
				autoHeight : true,
				autoWidth : true,
				frame : true,
				border:false,
				padding : 20,
				closable : true,
				monitorValid : true,
				defaults : {
					allowBlank : false
				},
				items : [{
							inputType : 'password',
							name : 'oldPwd',
							minLength:6,
							fieldLabel : '旧密码'
						}, {
							id : 'pwd',
							name : 'newPwd',
							inputType : 'password',
							fieldLabel : '新密码'
						}, {
							inputType : 'password',
							fieldLabel : '确认密码',
							vtype : 'password',
							initialPassField : 'pwd'
						}],
				buttons : [{
							text : '保存',
							iconCls : 'icon-save',
							formBind : true,
							handler : modify
						}]
			});
	var winPanel = new Ext.Panel({
				border : false,
				items : {
					xtype : 'window',
					title : '修改密码',
					x:(getTableWidth()-400)/2,
					y:150,
					width : 400,
					border : false,
					collapsible : true,
					closable : false,
					resizable : false,
					constrain : true,
					maximizable : true,
					initHidden : false,
					shadow : false,
					items : [modifyPwdForm]

				}
			});
	KYB.ModifyPwdPanel.superclass.constructor.call(this, {
				title : '修改密码',
				id : 'modifyPwdPanel',
				closable : true,
				layout : 'fit',
				autoscroll : true,
				items : [winPanel]
			});
	function modify() {
		var formData = modifyPwdForm.getForm().getValues();
		Ext.Ajax.request({
					url : context + "/userInfo/modifyPwd.action",
					method : 'POST',
					jsonData : formData,
					callback : function(options, success, response) {
						var ds = Ext.decode(response.responseText);
						if (ds.errorMessage) {
							showErrorMsg(ds.errorMessage);
						} else {
							modifyPwdForm.getForm().reset();
							showInfoMsg("密码修改成功！");
						}
					}
				})
	}
}
Ext.extend(KYB.ModifyPwdPanel, Ext.Panel);
Ext.reg("modifyPwdPanel", KYB.ModifyPwdPanel);