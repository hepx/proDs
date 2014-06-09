Ext.ns('KYB.SXD');
/**
 * 佛像信息增加和修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.BuddhaImageAddorUpWin = function(config,ds,url_,fn) {	
	var buddhaImageAddOrUpForm = new Ext.FormPanel({
        frame:true,
        monitorValid : true,
        id:'buddhaImageAddOrUpForm',
        bodyStyle:'padding:5px 5px 0',
        labelWidth :60,
        defaultType : 'textfield',
        items: [{
            fieldLabel: '佛像名',
            name: 'buddhaImageName',
            allowBlank : false,
            anchor:'95%'
        },{
        	xtype : 'textarea',
            fieldLabel: '描述',
            name: 'buddhaImageDesc',
            anchor:'95%'
        },{
            fieldLabel: '创建人',
            name: 'createUser',
           	readOnly:true,
            allowBlank : false,
            value : USER_NAME,
            anchor:'95%'
        }],

		buttons : [{
			text : '保存',
			iconCls : 'icon-save',
			formBind : true,
			handler : function() {
				var formDs = buddhaImageAddOrUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						
						buddhaImages : (ds==null?null:[ds]) || [formDs]
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								buddhaImageAddOrUpForm.getForm().reset();
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
					buddhaImageAddOrUpForm.getForm().setValues(ds);
				} else {
					buddhaImageAddOrUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				buddhaImageAddOrUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'buddhaImageAddorUpWin',
				width : 350,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [buddhaImageAddOrUpForm]
			}, config);
	KYB.SXD.BuddhaImageAddorUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.BuddhaImageAddorUpWin, Ext.Window);