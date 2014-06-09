Ext.ns("KYB");

/**
 * 用户注册/更新窗口
 * 
 * @param {}
 *            title
 * @param {}
 *            ds
 */
KYB.UserRegistWin = function(config, ds, isRole,url_,fn) {
	var roleStore = new Ext.data.ArrayStore({
				url : context + '/role/queryRole.action',
				root : 'rolePojos',
				autoLoad:true,
				fields : [{
							name : 'id',
							mapping : 'id'
						}, {
							name : 'roleName',
							mapping : 'roleName'
						}]
			});
	var items = [{
				name : 'userName',
				fieldLabel : '用户名'
			}, {
				inputType : 'password',
				name : 'password',
				minLength : 6 ,
				minLengthText : '密码不能少于6位！',
				fieldLabel : '密码'
			}, {
				name : 'phone',
				allowBlank : true,
				fieldLabel : '联系方式',
				regex : /[0-9]{11}/,
				regexText : "格式只能由0-9的数字组成11位字符！"
			}, {
				name : 'email',
				vtype : 'email',
				allowBlank : true,
				fieldLabel : '邮箱地址',
				blankText:'邮箱将作为你忘记密码时取回密码的唯一工具，请正确填写！'
				
			}];
	// 去除密码输入框
	if (ds) {
		items.splice(1, 1);
	}
	// 增加角色选择框
	if (isRole) {
		items.push([{
					xtype : 'combo',
					fieldLabel : '所属角色',
					id : 'role',
					//name : 'roleId',
					hiddenName:'roleId',
					mode:'local',
					width : 100,
					valueField : 'id',
					editable : false,
					lazyRender : true,
					triggerAction : 'all',
					displayField : 'roleName',
					store : roleStore
				}, {
					xtype : 'combo',
					fieldLabel : '用户状态',
					id : 'state',
					name : 'state',
					width : 100,
					mode : 'local',
					valueField : 'name',
					editable : false,
					triggerAction : 'all',
					displayField : 'name',
					store : {
						xtype : 'arraystore',
						fields : ['name'],
						data : [['正常'], ['禁用']]
					}
					
				}])
	}
	var userInfoForm = new Ext.FormPanel({
				id : 'userInfoForm',
				monitorValid : true,
				frame : true,
				padding : 10,
				border : false,
				buttonAlign : 'right',
				defaultType : 'textfield',
				defaults : {
					allowBlank : false
				},
				items : items,
				buttons : [{
					text : '保存',
					iconCls : 'icon-save',
					formBind : true,
					handler : function() {
						var formDs = userInfoForm.getForm().getValues();
						if (ds) {
							Ext.apply(ds, formDs);
						}
						Ext.Ajax.request({
									url : context+url_,
									method : 'POST',
									jsonData : {
										userInfo : ds || formDs
									},
									callback : function(opts, sus, res) {
										var das = Ext.decode(res.responseText);
										if (das.errorMessage) {
											showErrorMsg(das.errorMessage);
										} else {
											showInfoMsg(config.title+"成功！");
											// userInfoForm.getForm().reset();
											fn.call(this, ds || formDs);
										}
									}
								});
					}
				}, {
					text : '重置',
					iconCls : 'icon-redo',
					handler : function() {
						if (ds) {
							userInfoForm.getForm().setValues(ds);
						} else {
							userInfoForm.getForm().reset();
						}
					}
				}],
				listeners : {
					afterrender : function() {
						userInfoForm.getForm().setValues(ds || {});
					}
				}
			});
	config = Ext.apply({
				id : 'userInfoWin',
				title : '注册',
				width : 400,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [userInfoForm]
			}, config);
	KYB.UserRegistWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.UserRegistWin, Ext.Window);