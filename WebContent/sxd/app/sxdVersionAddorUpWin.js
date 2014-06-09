Ext.ns('KYB.SXD');
/**
 * 版本增加和修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.SxdVersionAddorUpWin = function(config,ds,url_,fn) {	
	var sxdVersionAddOrUpForm = new Ext.FormPanel({
        frame:true,
        monitorValid : true,
        id:'sxdVersionAddOrUpForm',
        bodyStyle:'padding:5px 5px 0',
        labelWidth :70,
        items: [{
        	xtype : 'textfield',
            fieldLabel: '创建人',
            name: 'createUser',
            allowBlank : false,
            readOnly : true,
            value : USER_NAME,
            anchor:'95%'
        },{
        	xtype : 'numberfield',
            fieldLabel: '内部版本号',
            name: 'internalVersion',
            allowBlank : false,
            emptyText : '内部版本号只能由数字组成且要大于之前的版本号',
            anchor:'95%'
        },{
        	xtype : 'textfield',
            fieldLabel: '外部版本号',
            name: 'version',
            emptyText : '外部版本号可由字符、数字、特殊字符组合',
            allowBlank : false,
            anchor:'95%'
        },{
        	xtype :'textarea',
            fieldLabel : '更新说明',
            name : 'versionDesc',
            height : 100,
            anchor :'95%'
        }],

		buttons : [{
			text : '保存',
			iconCls : 'icon-save',
			formBind : true,
			handler : function() {
				var formDs = sxdVersionAddOrUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						sxdVersion : ds || formDs
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								sxdVersionAddOrUpForm.getForm().reset();
							}
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
					sxdVersionAddOrUpForm.getForm().setValues(ds);
				} else {
					sxdVersionAddOrUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				sxdVersionAddOrUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'sxdVersionAddorUpWin',
				width : 400,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [sxdVersionAddOrUpForm]
			}, config);
	KYB.SXD.SxdVersionAddorUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.SxdVersionAddorUpWin, Ext.Window);