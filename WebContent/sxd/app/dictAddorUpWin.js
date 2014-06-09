Ext.ns('KYB.SXD');
/**
 * 字典增加和修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.DictAddorUpWin = function(config,ds,url_,fn) {	
	var dictAddOrUpForm = new Ext.FormPanel({
        frame:true,
        monitorValid : true,
        id:'dictAddOrUpForm',
        bodyStyle:'padding:5px 5px 0',
        labelWidth :60,
        defaultType : 'textfield',
        items: [{
            fieldLabel: '字典组',
            name: 'dictGroup',
            allowBlank : false,
            anchor:'95%'
        },{
            fieldLabel: '字典名',
            name: 'dictName',
            allowBlank : false,
            anchor:'95%'
        },{
            fieldLabel: '字典值',
            name: 'dictValue',
            allowBlank : false,
            anchor:'95%'
        },{
            xtype:'numberfield',
            fieldLabel: '排序',
            name: 'sort',
            anchor:'95%'
        },{
        	xtype:'textarea',
            fieldLabel: '描述',
            name: 'dictDesc',
            anchor:'95%'
        }],

		buttons : [{
			text : '保存',
			iconCls : 'icon-save',
			formBind : true,
			handler : function() {
				var formDs = dictAddOrUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						dicts : (ds==null?null:[ds]) || [formDs]
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								dictAddOrUpForm.getForm().reset();
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
					dictAddOrUpForm.getForm().setValues(ds);
				} else {
					dictAddOrUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				dictAddOrUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'dictAddOrUpWin',
				width : 350,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [dictAddOrUpForm]
			}, config);
	KYB.SXD.DictAddorUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.DictAddorUpWin, Ext.Window);