Ext.ns('KYB.QDTG');

KYB.QDTG.QdtgCvsPanel=function(){
	var fields=[{
		name : 'cvsId',
		type : 'int'
	},{
		name : 'cvsName',
		type : 'string'
	}];
	
	var store =new Ext.data.JsonStore({
		url : context+'/qdtg/cvs/queryQdtgCvs.action',
		method : 'POST',
		autoDestroy :true,
		autoLoad : true,
		root : 'qdtgCvsList',
		totalProperty: 'total',
		baseParams: {
			start : 0,
			limit : 20
		},
		fields :fields
	});
	var tbar = [{
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},'-',{
		text : '删除',
		iconCls : 'icon-delete',
		handler : doDel
	},'-',{
		text : '修改',
		iconCls : 'icon-edit',
		handler : doModify
	},'-',{
		text :'刷新',
		iconCls : 'icon-refresh',
		handler : function(){
			pageBar.doRefresh();
		}
	}];
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		defaults : {
			menuDisabled:true
		},
		columns : [sm,{
					menuDisabled:false,
					header : '<div align="center">客户名称</div>',
					//width : 120,
					dataIndex : 'cvsName'			
				}]
	});
	var pageBar = createPageBar(store);
	KYB.QDTG.QdtgCvsPanel.superclass.constructor.call(this,{
		title : '客户列表',
		id : 'qdtgCvsPanel',
		closable : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		tbar : tbar,
		cm : cm,
		sm : sm,
		bbar : pageBar,
		store : store
	});

	function addOrUpdateQdtgCvsWin(title,record){
		var items=[{
					xtype: 'textfield',
					fieldLabel:'客户名称',
					name: 'cvsName',
					allowBlank: false,
					maxLength: 30,
					anchor:'80%'
				}];
		if(!record){
			items.push([{
					xtype: 'textfield',
					fieldLabel: '登录用户名',
					name: 'userName',
					allowBlank: false,
					anchor: '80%'
				},{
					xtype: 'textfield',
					fieldLabel: '登录密码',
					inputType : 'password',
					minLength : 6 ,
					minLengthText : '密码不能少于6位！',
					name: 'password',
					anchor: '50%'
				},{
					xtype: 'textfield',
					name : 'phone',
					fieldLabel : '联系方式',
					regex : /[0-9]{11}/,
					regexText : "格式只能由0-9的数字组成11位字符！",
					anchor: '80%'
				}, {
					xtype: 'textfield',
					name : 'email',
					vtype : 'email',
					fieldLabel : '邮箱地址',
					blankText:'邮箱将作为你忘记密码时取回密码的唯一工具，请正确填写！',
					anchor: '80%'
				},{
					xtype : 'combo',
					fieldLabel : '所属角色',
					id : 'role',
					hiddenName:'roleId',
					mode:'local',
					width : 100,
					valueField : 'id',
					allowBlank: false,
					editable : false,
					lazyRender : true,
					triggerAction : 'all',
					displayField : 'roleName',
					store : new Ext.data.ArrayStore({
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
					})
				}]);
		}
		new Ext.Window({
			id:'qdtgCvsWin',
			title:title,
			width:350,
			resizable: false,
			modal: true,
			constrain: true,
			autoHeight:true,
			items:[{
				id: 'qdtgCvsForm',
				xtype:'form',
				padding:10,
				labelWidth :70,
				border: false,
				monitorValid : true,
				items:items,
				buttons:[{
					text:'保存',
					iconCls : 'icon-save',
					formBind : true,
					handler : function(){
						var formData=Ext.getCmp('qdtgCvsForm').getForm().getValues();
						Ext.Ajax.request({
							url : context + '/qdtg/cvs/saveOrUpdateQdtgCvs.action',
							method : 'POST',
							jsonData : {
								qdtgCvs : record ? Ext.apply(record.data,formData) : formData,
								userInfo: record ? null : formData 
							},
							callback : function(opts, suc, res) {
								var ds = Ext.decode(res.responseText);
								if (ds.errorMessage) {
									showErrorMsg(ds.errorMessage);
								} else {
									showInfoMsg(title+"成功！");
									if(record){
										record.set('cvsName',formData.cvsName);
										record.commit();
									}else{
										pageBar.doRefresh();
									}
									Ext.getCmp('qdtgCvsWin').close();
								}
							}
						});
					}
				},{
					text: '重置',
					iconCls : 'icon-redo',
					handler : function(){
						Ext.getCmp('qdtgCvsForm').getForm().reset();
					}
				}],
				listeners : {
					afterrender : function() {
						if(record){
							Ext.getCmp('qdtgCvsForm').getForm().setValues(record.data);
						}
					}
				}
			}]
		}).show();
	}
	
	function doAdd(){
		addOrUpdateQdtgCvsWin('增加客户信息',null);
	}
	
	function doModify() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var re=sm.getSelected();
		addOrUpdateQdtgCvsWin('修改客户信息',re)
	}

	function doDel() {
		if(!sm.hasSelection()){
			showWarnMsg("没有选中任何记录，请选择。");
			return;
		}
		var records = sm.getSelections();
		Ext.Ajax.request({
			url : context + '/qdtg/cvs/deleteQdtgCvs.action',
			method : 'POST',
			jsonData : {
				qdtgCvsList : encodeRecordsToJsonArray(records)
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					showInfoMsg("删除成功！");
					store.remove(records);
				}
			}
		});
	}
}
Ext.extend(KYB.QDTG.QdtgCvsPanel,Ext.grid.GridPanel);
Ext.reg('qdtgCvsPanel',KYB.QDTG.QdtgCvsPanel);