Ext.ns('KYB.SXD');
/**
 * 佛音类型下拉框
 * @class MusicTypeCombox
 * @extends Ext.form.ComboBox
 */
KYB.SXD.MusicTypeCombox = Ext.extend(Ext.form.ComboBox,{
	typeAhead : true,
	lazyRender:true,
	editable : false,
	mode: 'local',
	allowBlank: false,
	triggerAction : 'all',
	store : new Ext.data.JsonStore({
	url:context+"/sxd/dict/queryDicts.action",
		method:'POST',
		autoLoad:true,
		baseParams : {
			dictGroup : 'musicType'
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
	hiddenName: 'musicType',
	valueField : 'dictValue',
	displayField : 'dictName'
});
/**
 * 佛音增加和修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.MusicAddorUpWin = function(config,ds,url_,fn) {	
	var musicAddOrUpForm = new Ext.FormPanel({
        frame:true,
        monitorValid : true,
        id:'musicAddOrUpForm',
        bodyStyle:'padding:5px 5px 0',
        labelWidth :60,
        defaultType : 'textfield',
        items: [{
            fieldLabel: '佛音名',
            name: 'musicTitle',
            allowBlank : false,
            anchor:'95%'
        },{
            fieldLabel: '作者',
            name: 'author',
            anchor:'95%'
        },new KYB.SXD.MusicTypeCombox({fieldLabel:'佛音类别',anchor:'95%'})
     	,{
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
				var formDs = musicAddOrUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						buddhaMusics : (ds==null?null:[ds]) || [formDs]
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								musicAddOrUpForm.getForm().reset();
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
					musicAddOrUpForm.getForm().setValues(ds);
				} else {
					musicAddOrUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				musicAddOrUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'musicAddOrUpWin',
				width : 350,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [musicAddOrUpForm]
			}, config);
	KYB.SXD.MusicAddorUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.MusicAddorUpWin, Ext.Window);