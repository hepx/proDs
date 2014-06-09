Ext.ns('KYB.SXD');
/**
 * 佛学常识类型下拉框
 * @class LoreTypeCombox
 * @extends Ext.form.ComboBox
 */
KYB.SXD.LoreTypeCombox = Ext.extend(Ext.form.ComboBox,{
	typeAhead : true,
	lazyRender:true,
	editable : false,
	allowBlank: false,
	mode: 'local',
	triggerAction : 'all',
	store : new Ext.data.JsonStore({
	url:context+"/sxd/dict/queryDicts.action",
		method:'POST',
		autoLoad:true,
		baseParams : {
			dictGroup : 'loreType'
		},
		root : 'dicts',
		fields : [{
				name : 'dictName',
				type : 'string'
			},{
				name : 'dictValue',
				type : 'string'
			}]
	}),
	hiddenName: 'loreType',
	valueField : 'dictValue',
	displayField : 'dictName'
});
/**
 * 佛学常识增加和修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.LoreAddOrUpWin = function(config,ds,url_,fn) {	
	var addOrUpForm = new Ext.FormPanel({
        labelAlign: 'top',
        frame:true,
        monitorValid : true,
        id:'addOrUpForm',
        bodyStyle:'padding:5px 5px 0',
        width: 600,
        items: [{
            layout:'column',
            items:[{
                columnWidth:.5,
                layout: 'form',
                items: [{
                    xtype:'textfield',
                    fieldLabel: '标题',
                    name: 'loreTitle',
                    allowBlank : false,
                    anchor:'95%'
                },{
                    xtype:'textfield',
                    fieldLabel: '创建人',
                    name: 'createUser',
                   	readOnly:true,
                    allowBlank : false,
                    value : USER_NAME,
                    anchor:'95%'
                }]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [
					new KYB.SXD.LoreTypeCombox({fieldLabel:'类型',anchor:'95%'}),{
						xtype : 'textfield',
						hidden : true,
						name : 'roleId'
					}]
            }]
        },{
            xtype:'textarea',
            id:'loreContent',
            fieldLabel:'内容',
            name :'loreContent',
            height:200,
            anchor:'98%'
        }],
		buttons : [{
			text : '保存',
			iconCls : 'icon-save',
			formBind : true,
			handler : function() {
				var formDs = addOrUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						buddhismLores : (ds==null?null:[ds]) || [formDs]
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								addOrUpForm.getForm().reset();
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
					addOrUpForm.getForm().setValues(ds);
				} else {
					addOrUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				addOrUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'addOrUpWin',
				title : '创建佛学常识',
				width : 600,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [addOrUpForm]
			}, config);
	KYB.SXD.LoreAddOrUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.LoreAddOrUpWin, Ext.Window);