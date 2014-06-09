Ext.ns('KYB.SXD');
/**
 * 供品类型下拉框
 * @class LoreTypeCombox
 * @extends Ext.form.ComboBox
 */
KYB.SXD.ArticleTypeCombox = Ext.extend(Ext.form.ComboBox,{
	typeAhead : true,
	lazyRender:true,
	editable : false,
	allowBlank: false,
	mode: 'local',
	triggerAction : 'all',
	emptyText : '请选择',
	store : new Ext.data.JsonStore({
		url:context+"/sxd/dict/queryDicts.action",
		method:'POST',
		//autoLoad:true,
		baseParams : {
			dictGroup : 'articleType'
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
	hiddenName: 'articleType',
	valueField : 'dictValue',
	displayField : 'dictName'
});
/**
 * 供品信息增加和修改窗口
 * @param {} config
 * @param {} ds
 * @param {} url_
 * @param {} fn
 */
KYB.SXD.WorshipArticleAddorUpWin = function(config,ds,url_,fn) {	
	var articleAddOrUpForm = new Ext.FormPanel({
        frame:true,
        monitorValid : true,
        id:'articleAddOrUpForm',
        bodyStyle:'padding:5px 5px 0',
        labelWidth :60,
        defaultType : 'textfield',
        items: [{
            fieldLabel: '供品名',
            name: 'articleName',
            allowBlank : false,
            anchor:'95%'
        },new KYB.SXD.ArticleTypeCombox({
        	fieldLabel : '供品分类',
        	anchor : '95%'
        }),{
        	xtype : 'textarea',
            fieldLabel: '描述',
            name: 'articleDesc',
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
				var formDs = articleAddOrUpForm.getForm().getValues();
				if (ds) {
					Ext.apply(ds, formDs);
				}
				Ext.Ajax.request({
					url : context+url_,
					method : 'POST',
					jsonData : {
						
						articles : (ds==null?null:[ds]) || [formDs]
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg(config.title+"成功！");
							if(!ds){
								articleAddOrUpForm.getForm().reset();
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
					articleAddOrUpForm.getForm().setValues(ds);
				} else {
					articleAddOrUpForm.getForm().reset();
				}
			}
		}],
		listeners : {
			afterrender : function() {
				articleAddOrUpForm.getForm().setValues(ds || {});
			}
		}
    });
    
	config = Ext.apply({
				id : 'worshipArticleAddorUpWin',
				width : 350,
				border : false,
				resizable : false,
				modal : true,
				constrain : true,
				items : [articleAddOrUpForm]
			}, config);
	KYB.SXD.WorshipArticleAddorUpWin.superclass.constructor.call(this, config);
}
Ext.extend(KYB.SXD.WorshipArticleAddorUpWin, Ext.Window);