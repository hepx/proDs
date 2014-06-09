Ext.ns("KYB");

KYB.UserManagerPanel = function() {
	var url_='/userInfo/saveOrUpdateUserInfo.action';
	var fields = [{
				name : 'id',
				type : 'int'
			}, {
				name : 'userName',
				type : 'string'
			}, {
				name : 'createTime',
				type : 'date',
				dateFormat : 'Y-m-d\\TH:i:s'
			}, {
				name : 'updateTime',
				type : 'date',
				dateFormat : 'Y-m-d\\TH:i:s'
			}, {
				name : 'phone',
				type : 'string'
			}, {
				name : 'email',
				type : 'string'
			}, {
				name : 'roleName',
				type : 'string'
			}, {
				name : 'state',
				type : 'string'
			}];
	var store = new Ext.data.JsonStore({
				url : context + '/userInfo/queryUserInfoList.action',
				root : 'userInfoList',
				method : 'POST',
				totalProperty : 'total',
				autoLoad : true,
				sortInfo : {
					field : 'id',
					direction : 'ASC'
				},
				fields : fields
			});
			new Ext.data.Connection()
	var tbar = [{
				text : '注册',
				iconCls : 'icon-add',
				handler : add
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : modify
			}, '-', {
				text : '删除',

				iconCls : 'icon-delete',
				handler : del
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : refresh
			}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
				defaultSortable : true,
				defaultWidth : 150,
				columns : [sm,{
							header : '<div align=center>用户名</div>',
							menuDisabled : false,
							width : 100,
							align : 'right',
							dataIndex : 'userName'
						}, {
							header : '<div align=center>联系方式</div>',
							align : 'right',
							dataIndex : 'phone'
						}, {
							header : '<div align=center>邮箱地址</div>',
							align : 'right',
							width : 200,
							dataIndex : 'email'
						}, {
							header : '<div align=center>所属角色</div>',
							align : 'center',
							width : 100,
							dataIndex : 'roleName'
						}, {
							header : '<div align=center>创建时间</div>',
							align : 'right',
							dataIndex : 'createTime',
							renderer : formatDateTime
						}, {
							header : '<div align=center>更新时间</div>',
							align : 'right',
							dataIndex : 'updateTime',
							renderer : formatDateTime
						}, {
							header : '<div align=center>用户状态</div>',
							width : 80,
							align : 'center',
							dataIndex : 'state'
						}]
			});
	KYB.UserManagerPanel.superclass.constructor.call(this, {
				title : '用户管理',
				id : 'userManagerPanel',
				autoScroll : true,
				closable : true,
				stripeRows : true,
				columnLines : true,
				loadMask : true,
				viewConfig : {autoFill:true},
				tbar : tbar,
				cm : cm,
				sm : sm,
				store : store
			});
	/**
	 * 刷新数据
	 */
	function refresh() {
		doRefresh(store);
	}
	/**
	 * 删除用户
	 */
	function del() {
		var re = sm.getSelected();
		if (!re) {
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		Ext.Msg.confirm("提示：", "确定要删除选 中的用户吗？", function(btn) {
					if (btn == "yes") {

						Ext.Ajax.request({
									url : context + '/userInfo/deleteUserInfo.action',
									method : 'POST',
									jsonData : {
										userInfo : re.json
									},
									callback : function(opts, suc, res) {
										var ds = Ext.decode(res.responseText);
										if (ds.errorMessages) {
											showErrorMsg(ds.errorMessage);
										} else {
											Ext.getCmp('userManagerPanel')
													.getStore().remove(re);
											showInfoMsg("删除成功！");
										}
									}
								});
					}
				});
	};
	/**
	 * 注册用户
	 */
	function add() {
		var userReWin = new KYB.UserRegistWin({
					title : '新用户注册'
				}, null,true, url_, refresh).show().center();
	};
	/**
	 * 更新用户
	 */
	function modify() {
		var ds = sm.getSelected();
		if (ds) {
			new KYB.UserRegistWin({
						title : '修改用户资料'
					}, ds.data,true,url_, refresh).show().center();
		} else {
			showWarnMsg("没有选中任何记录，请选择。");
		}
	};
}
Ext.extend(KYB.UserManagerPanel, Ext.grid.GridPanel);
Ext.reg('userManagerPanel', KYB.UserManagerPanel)
