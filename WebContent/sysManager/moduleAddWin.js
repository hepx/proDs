Ext.ns("KYB");
/**
 * 增加模块窗口类
 * @param {} config
 * @param {} fn
 */
KYB.ModuleAddWin=function(config,fn){
	var items=[{
			fieldLabel:'模块名字',
			name:'text'
		},{
			fieldLabel:'模块代码',
			name : 'moduleId'
		},new KYB.IconCombobox()];
	if(config.level==2){
		items.push({
			xtype : 'radiogroup',
			fieldLabel : '节点类型',
			items :[{
				boxLabel : '子节点',
				name : 'leaf',
				inputValue : false
			},{
				boxLabel : '叶节点',
				name : 'leaf',
				inputValue : true,
				checked : true
			}]
		},{
			xtype : 'radiogroup',
			fieldLabel : '是否默认展开',
			items :[{
				boxLabel : '是',
				name : 'expanded',
				inputValue : true
			},{
				boxLabel : '否',
				name : 'expanded',
				inputValue : false,
				checked : true
			}]
		},{
			fieldLabel:'对应JS类路径',
			allowBlank:true,
			anchor:'98%',
			name: 'file'
		},{
			xtype:'hidden',
			name:'parentId',
			value:config.parentId
		});
	}
	var moduleForm=new Ext.FormPanel({
		id:'moduleForm',
		frame:true,
		padding:10,
		monitorValid:true,
		border:false,
		defaultType:'textfield',
		defaults:{
			anchor : '70%',
			allowBlank:false
		},
		items:items,
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			formBind:true,
			handler:saveModule
		},{
			text :'重置',
			iconCls : 'icon-redo',
			handler : resetModule
		}]
	});
	function resetModule(){
		moduleForm.getForm().reset()
	}
	function saveModule(){
		var module=moduleForm.getForm().getValues();
		Ext.Ajax.request({
			url:context+"/module/saveModule.action",
			mdthod:'POST',
			jsonData:{modulePojo:module},
			callback:function(opts,suc,res){
				var ds=Ext.decode(res.responseText);
				if(ds.errorMessage){
					showErrorMsg(ds.errorMessage);
				}else{
					showInfoMsg("保存成功！");
					resetModule();
					fn.call(this);
				}
			}
		})
	}
	config=Ext.apply({
		id:'moduleAddWin',
		title :'增加模块',
		width:400,
		border:false,
		items:[moduleForm],
		resizable : false,
		modal : true,
		constrain : true
	},config);
	KYB.ModuleAddWin.superclass.constructor.call(this,config);
}
Ext.extend(KYB.ModuleAddWin,Ext.Window);