Ext.ns("KYB");
KYB.PermissionManagerPanel = function() {
	var RoleTree = new Ext.tree.TreePanel({
		id : 'RoleTree',
		title : '角色列表',
		region : 'west',
		minSize : 100,
		maxSize : 300,
		width : 180,
		border : false,
		animate : true,
		rootVisible : false,
		autoScroll : true,
		containerScroll : true,
		loader : new Ext.tree.TreeLoader({
					dataUrl : context + '/role/queryRole.action',
					handleResponse : function(response) {
						var rolePojos = Ext.decode(response.responseText).rolePojos;
						var treeData = new Array();
						Ext.each(rolePojos, function(po) {
									treeData.push({
												id : po.id,
												text : po.roleName,
												leaf : true,
												iconCls : 'icon-group'
											})
								})
						response.responseData = treeData;
						this.constructor.prototype.handleResponse.call(this,
								response);
					}
				}),
		root : new Ext.tree.AsyncTreeNode({
					id : 'root',
					draggable : false,
					text : '角色列表'
				}),
		listeners : {
			click : function(node) {
				Ext.Ajax.request({
							url : context + '/role/queryRoleById.action',
							method : 'POST',
							jsonData : {
								rolePojo : {
									id : node.attributes.id
								}
							},
							callback : function(opts, suc, res) {
								var ds = Ext.decode(res.responseText);
								if (!ds.errorMessage) {
									moduleForm.getForm().reset();
									var rolePojo = ds.rolePojo;
									moduleForm.getForm().setValues(rolePojo);
								} else {
									showErrorMsg(ds.errorMessage);
								}
							}
						});
			}
		}
	});
	var its = [];

	var moduleForm = new Ext.FormPanel({
				id : 'moduleForm',
				region : 'center',
				padding : 10,
				autoScroll : true,
				autoWidth : false,
				autoHeight : false,
				frame : true,
				border : false,
				layout : 'table',
				defaults : {
					//width : '60%',
					labelAlign : 'right',
					labelWidth : 80
					//bodyStyle:'padding:10px'
				},
				layoutConfig : {
					columns : 3
				},
				listeners : {
					afterrender : beload
				},
				items : its
			});
	var tbar = [{
				text : '增加角色',
				iconCls : 'icon-add',
				handler : addRole
			}, '-', {
				text : '删除角色',
				iconCls : 'icon-delete',
				handler : deleteRole
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : saveRoles
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh'
			}];
	KYB.PermissionManagerPanel.superclass.constructor.call(this, {
				id : 'permissionManagerPanel',
				title : '权限管理',
				closable : true,
				layout : 'border',
				border : false,
				tbar : tbar,
				items : [RoleTree, moduleForm]
			});

	function beload() {
		Ext.Ajax.request({
					url : context + '/role/queryModules.action',
					method : 'POST',
					callback : function(ops, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (!ds.errorMessage) {
							var modules = ds.modulePojos;
							buileModules(modules);
						} else {
							showErrorMsg(ds.errorMessage);
							return;
						}
					}
				});
	}
	function buileModules(modules) {
		its.push({
			colspan : 3,
			layout : 'form',
			items : [{
				xtype : 'textfield',
				name : 'roleName',
				fieldLabel : '角色名称'
			},{
				xtype : 'textfield',
				name : 'roleCode',
				readOnly : true,
				fieldLabel : '角色类型'
			},{
				xtype : 'hidden',
				name : 'roleType'
			},{
				xtype : 'textfield',
				fieldLabel : '描述',
				width : 500,
				name : 'roleDesc'
			},{
				xtype : 'hidden',
				name : 'id'
			}]
		});			
		Ext.each(modules, function(module) {
			its.push({
				layout:'form',
				items:[{ 
					xtype : 'permissioncheckbox',
					boxLabel : '<b>'+module.text+'</b>',
					name : 'moduleIds',
					width:100,
					inputValue : module.id
				}]
			});
			its.push({});
			its.push({});
			eachPrint(module.modulePojos,2);
		});
		moduleForm.add(its);
		moduleForm.doLayout();
	}
	function eachPrint(modulePojos,n){
		Ext.each(modulePojos, function(module2) {
			if(n==2){
				its.push({});
				its.push({
					layout : 'form',
					items : [{
						boxLabel : module2.text,
						name : 'moduleIds',
						xtype : 'permissioncheckbox',
						width:100,
						inputValue : module2.id
					}]
				});
				its.push({});
			}else{
				its.push({});
				its.push({});
				its.push({
					layout : 'form',
					items : [{
						boxLabel : module2.text,
						name : 'moduleIds',
						xtype : 'permissioncheckbox',
						width:100,
						inputValue : module2.id
					}]
				});
			}
			eachPrint(module2.modulePojos,3);
		});
	}
	function afterAddRole() {
		RoleTree.getLoader().load(RoleTree.root, function(node) {
					node.expand(false, false)
				}, this);
	}
	function addRole() {
		var roleTypeStore = new Ext.data.ArrayStore({
			url : context + '/dic/queryDicInfo.action',
			baseParams : {
				dicGroup : 'roleType'
			},
			root : 'dicInfos',
			fields : [{
						name : 'dicValue',
						mapping : 'dicValue'
					}, {
						name : 'dicCode',
						mapping : 'dicCode'
					}]
		});
		var addRoleForm = new Ext.FormPanel({
			id : 'addRoleForm',
			monitorValid : true,
			border : false,
			defaultType : 'textfield',
			padding : 10,
			frame : true,
			items : [{
						fieldLabel : '角色名称',
						allowBlank : false,
						width : 100,
						name : 'roleName'
					}, {
						xtype : 'combo',
						fieldLabel : '角色类型',
						allowBlank : false,
						editable : false,
						hiddenName : 'roleType',
						lazyRender : true,
						width : 100,
						mode : 'remote',
						valueField : 'dicValue',
						displayField : 'dicCode',
						triggerAction : 'all',
						store : roleTypeStore
					}, {
						fieldLabel : '描述',
						anchor : '90%',
						name : 'roleDesc'
					}],
			buttons : [{
						text : '保存',
						iconCls : 'icon-save',
						formBind : true,
						handler : doSave
					}, {
						text : '重置',
						iconCls : 'icon-redo',
						handler : resetRole
					}]
		});
		function doSave() {
			var formData = addRoleForm.getForm().getValues();
			Ext.Ajax.request({
				url : context + '/role/saveOrUpdateRole.action',
				method : 'POST',
				jsonData : {
					rolePojo : formData
				},
				callback : function(opts, suc, res) {
					var ds = Ext.decode(res.responseText);
					if (!ds.errorMessage) {
						resetRole();
						afterAddRole();
						showInfoMsg("保存成功！");
					} else {
						showErrorMsg(ds.errorMessage);
					}
				}
			});
		}
		function resetRole() {
			addRoleForm.getForm().reset();
		}
		var roleWin = new Ext.Window({
				title : '增加角色',
				id : 'addRoleWin',
				modal : true,
				border : false,
				constrain : true,
				width : 400,
				resizable : false,
				items : [addRoleForm]
			});
		roleWin.show().center();
	}
	/**
	 * 删除ROLE
	 */
	function deleteRole() {
		var selRe = RoleTree.getSelectionModel().getSelectedNode();
		if (selRe) {
			var temp = true;
			Ext.Ajax.request({
						url : context + '/role/deleteRole.action',
						method : 'POST',
						jsonData : {
							rolePojo : {
								id : selRe.attributes.id
							}
						},
						callback : function(opts, suc, res) {
							var ds = Ext.decode(res.responseText);
							if (!ds.errorMessage) {
								showInfoMsg("册除成功！");
							} else {
								temp = false;
								showErrorMsg(ds.errorMessage);
							}
						}
					});
			if (temp) {
				selRe.remove();
			}
		} else {
			showWarnMsg("没有选中任何角色，请选择。");
		}
	}
	/**
	 * 保存
	 */
	function saveRoles() {
		var role = moduleForm.getForm().getValues();
		if (!role.id && !role.roleName) {
			return;
		}
		if (role.moduleIds) {
			if (typeof role.moduleIds == 'string') {
				role.moduleIds = [role.moduleIds];
			}
		}

		if (role.functionIds) {
			if (typeof role.functionIds == 'string') {
				role.functionIds = [role.functionIds];
			}
		}
		Ext.Ajax.request({
			url : context + '/role/saveOrUpdateRole.action',
			method : 'POST',
			jsonData : {
				rolePojo : role
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (!ds.errorMessage) {
					showInfoMsg("保存成功！");
				} else {
					showErrorMsg(ds.errorMessage);
				}
			}
		});
	}
}
Ext.extend(KYB.PermissionManagerPanel, Ext.Panel);
Ext.reg("permissionManagerPanel", KYB.PermissionManagerPanel);
