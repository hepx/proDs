Ext.ns('KYB.SXD');
/**
 * SXD用户修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.UserUpWin = function(config,ds,url_,fn) {	
	var userUpForm = new Ext.FormPanel({
        frame:true,
        id:'userUpForm',
        monitorValid:true,
        bodyStyle:'padding:5px 5px 0',
        labelWidth :60,
        defaultType : 'textfield',
        items: [{
            fieldLabel: '真实姓名',
            name: 'realName',
            anchor:'95%'
        },{
        	xtype: 'radiogroup',
            fieldLabel: '姓别',
            anchor:'95%',
            items: [
                {boxLabel:'男',name:'sex',inputValue:'男'},
                {boxLabel:'女',name:'sex',inputValue:'女'}
            ]
        },{
        	xtype : 'numberfield',
            fieldLabel: '年龄',
            maxValue : 100,
            name: 'age',
            anchor:'95%'
        },{
            fieldLabel: '联系方式',
            name: 'mobile',
            anchor:'95%'
        },{
            fieldLabel: 'QQ号码',
            name: 'qq',
            anchor:'95%'
        },{
            fieldLabel: '所在城市',
            name: 'city',
            anchor:'95%'
        },{
            fieldLabel: '邮箱地址',
            name: 'email',
            vtype : 'email',
            anchor:'95%'
        }],
		buttons : [{
			text : '保存',
			iconCls : 'icon-save',
			formBind : true,
			handler : function() {
				var formDs = userUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						users : (ds==null?null:[ds]) || [formDs]
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								userUpForm.getForm().reset();
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
					userUpForm.getForm().setValues(ds);
				} else {
					userUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				userUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'sxdUserUpWin',
				width : 350,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [userUpForm]
			}, config);
	KYB.SXD.UserUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.UserUpWin, Ext.Window);