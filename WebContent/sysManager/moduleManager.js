Ext.ns("KYB");
var imgComboTpl = '<tpl for="."><div x-combo-list-item :qtip="{url}" ' +
					'class="x-combo-list-item"><img src="{url}" width="16" height="16">&nbsp;{name}</div></tpl>';
KYB.IconCombobox = Ext.extend(Ext.form.ComboBox,{
	hiddenName : 'iconCls',
	fieldLabel : '菜单图标',
	anchor:'50%',
	typeAhead : true,
	triggerAction : 'all',
	lazyRender : true,
	mode : 'local',
	editable : false,
	valueField : 'icon',
	displayField : 'name',
	tpl : imgComboTpl,
	store : {
		xtype : 'arraystore',
		//autoLoad : true,
		fields : ['icon','name','url'],
		url : 'css/icons.json'
	}
});
KYB.ModuleManagerPanel = function() {
	var moduleTree = new Ext.tree.TreePanel({
		id : 'moduleTree',
		title : '模块列表',
		region : 'west',
		minSize : 100,
		maxSize : 300,
		width : 180,
		border : false,
		animate : true,
		rootVisible : false,
		autoScroll : true,
		containerScroll : true,
		loader : new Ext.tree.TreeLoader({
			dataUrl : context + '/module/queryModuleTreePojos.action',
			handleResponse : function(response) {
				response.responseData = Ext.decode(response.responseText).moduleTreePojos;
				this.constructor.prototype.handleResponse.call(this, response);
			}
		}),
		root : new Ext.tree.AsyncTreeNode({
					id : 'root',
					draggable : false,
					text : '模块列表'
				})
	});
	
	var fields = [{
		name : 'id',
		type : 'int'
	}, {
		name : 'functionName',
		type : 'string'
	}, {
		name : 'type',
		type : 'int'
	}];
	var store = new Ext.data.JsonStore({
		sortInfo : {
			field : 'id',
			direction : 'ASC'
		},
		fields : fields
	});
	var combo = new Ext.form.ComboBox({
		name : 'type',
		width : 100,
		typeAhead : true,
		triggerAction : 'all',
		lazyRender : true,
		mode : 'local',
		editable : false,
		valueField : 'value',
		displayField : 'name',
		store : {
			xtype : 'arraystore',
			fields : ['value', 'name'],
			data : [[1, '查询'], [2, '编辑']]
		}
	});
	var cm = new Ext.grid.ColumnModel({
		defaultSortable : true,
		columns : [{
			header : '<div align=center>方法</div>',
			dataIndex : 'functionName',
			width : 500,
			editor : new Ext.form.TextField({})
		}, {
			header : '<div align=center>操作权限</div>',
			dataIndex : 'type',
			editor : combo,
			renderer : comboRenderer(combo)
		}]
	})

	var moduleGrid = new Ext.grid.EditorGridPanel({
		id : 'moduleGrid',
		region : 'center',
		trackMouseOver : true,
		autoScroll : true,
		stripeRows : true,
		columnLines : true,
		loadMask : true,
		store : store,
		height : 520,
		cm : cm
	});

	var moduleId = new Ext.form.Hidden({
		fieldLabel : 'id',
		name : 'id',
		anchor:'60%'
	});
	var moduleCode = new Ext.form.TextField({
		fieldLabel : '模块代码(唯一)',
		name : 'moduleId',
		anchor:'60%'
	});
	var moduleName = new Ext.form.TextField({
		fieldLabel : '模块名字',
		name : 'text',
		anchor:'60%'
	});
	var jsFile = new Ext.form.TextField({
		fieldLabel : '对应JS的相对路径',
		width : 400,
		name : 'file',
		anchor:'95%'
	});
	var expand = new Ext.form.RadioGroup({
		fieldLabel : '是否默认展开',
		items :[{
			boxLabel : '是',
			name : 'expanded',
			inputValue : 1
		},{
			boxLabel : '否',
			name : 'expanded',
			inputValue : 0
		}],
		anchor:'60%'
	});
	var leaf = new Ext.form.Hidden({
		fieldLabel : 'leaf',
		name : 'leaf',
		anchor:'60%'
	});
	
	var items = [{
		layout : 'column',
		border : false,
		items : [{
			columnWidth : .5,
			layout : 'form',
			border : false,
			items : [moduleName, moduleCode]
		},{
			columnWidth : .5,
			layout : 'form',
			border : false,
			items : [expand, new KYB.IconCombobox()]
		},{
			columnWidth : .77,
			layout : 'form',
			border : false,
			items : [jsFile, moduleId,leaf]
		}]
	}, moduleGrid];
	var moduleContent = new Ext.FormPanel({
		id : 'moduleContext',
		region : 'center',
		border : false,
		frame : true,
		items : items
	});

	var tbar = [{
		text : '增加根模块',
		iconCls : 'icon-add',
		handler : addOneNode
	}, '-', {
		text : '增加子模块',
		iconCls : 'icon-add',
		handler : addTwoNode
	}, '-', {
		text : '删除模块',
		iconCls : 'icon-delete',
		handler : deleteModule
	}, '-', {
		text : '增加操作方法',
		iconCls : 'icon-add',
		handler : addFuns
	}, '-', {
		text : '保存',
		iconCls : 'icon-save',
		handler : saveModule
	}, '-', {
		text : '刷新',
		iconCls : 'icon-refresh',
		handler : afterAddNode
	}];

	KYB.ModuleManagerPanel.superclass.constructor.call(this, {
		title : '模块管理',
		id : 'moduleManagerPanel',
		layout : 'border',
		border : false,
		closable : true,
		items : [moduleTree, moduleContent],
		tbar : tbar
	});

	/**
	 * 增加方法
	 */
	function addFuns() {
		var rowObj = moduleGrid.getStore().recordType;
		var rowStore = new rowObj({});
		moduleGrid.stopEditing();
		store.insert(0, rowStore);
		moduleGrid.startEditing(0, 0);
	};
	/**
	 * 增加一级模块
	 */
	function addOneNode() {
		var oneModuleWin = new KYB.ModuleAddWin({
			id : 'oneModuleWin',
			title : '增加根模块',
			level : 1
		}, afterAddNode);
		oneModuleWin.show().center();
	}
	/**
	 * 增加二级模块
	 */
	var selNode;
	function addTwoNode() {
		selNode = moduleTree.getSelectionModel().getSelectedNode();
		if (selNode) {
//			if (selNode.leaf) {
//				selNode = selNode.parentNode;
//			}
			twoModuleWin = new KYB.ModuleAddWin({
				id : 'twoModuleWin',
				title : '增加子模块',
				level : 2,
				parentId : selNode.id
			}, afterAddNode);
			twoModuleWin.show().center();
		} else {
			showWarnMsg("增加二级模块必须选择一个一级模块，请选择！");
		}
	}
	/**
	 * 执行完增加操作后调用的法
	 */
	function afterAddNode() {
		moduleTree.getLoader().load(selNode || moduleTree.root, function(node) {
			node.expand(false, false)
		}, this);
	}
	/**
	 * 删除模块
	 */
	function deleteModule() {
		selNode = moduleTree.getSelectionModel().getSelectedNode();
		if (!selNode) {
			showWarnMsg("请选择要删除的模块！");
			return;
		}
		Ext.MessageBox.confirm("提示", "删除模块会将模块本身和模块下的子模块一同删除。确定要删除吗？",
				function(btn) {
					if (btn === 'yes') {
						Ext.Ajax.request({
									url : context
											+ '/module/deleteModule.action',
									method : 'POST',
									jsonData : {
										modulePojo : {
											id : selNode.id
										}
									},
									callback : function(opts, suc, res) {
										var ds = Ext.decode(res.responseText);
										if (ds.errorMessage) {
											showErrorMsg(ds.errorMessage);
										} else {
											selNode.remove(true);
											showInfoMsg("删除成功！");
										}
									}
								});
					}
				});
	}
	/**
	 * 点击节点事件
	 */
	moduleTree.on("click", function(node, e) {
		var config = {
			id : node.attributes.id,
			moduleId : node.attributes.moduleId,
			text : node.attributes.text,
			file : node.attributes.file,
			expanded : node.attributes.expanded?1:0,
			iconCls : node.attributes.iconCls,
			leaf : node.attributes.leaf?true:false
		}
		moduleContent.getForm().setValues(config);
		moduleGrid.getStore().loadData(node.attributes.functionPojos||{})
	});

	/**
	 * 保存和更新模块
	 */
	function saveModule() {
		selNode = moduleTree.getSelectionModel().getSelectedNode();
		if (selNode.leaf) {
			selNode = selNode.parentNode;
		}
		var formV = moduleContent.getForm().getValues();
		if (!formV.text) {
			showWarnMsg("模块名称不能为空！");
			return;
		}
		if (!formV.moduleId) {
			showWarnMsg("模块代码不能为空！");
			return;
		}
//		if (!formV.file) {
//			showWarnMsg("对应Action路径不能为空！");
//			return;
//		}
		var girdV = encodeRecordsToJsonArray(moduleGrid.getStore()
				.getModifiedRecords());
		formV.functionPojos = girdV;
		formV.expanded=(formV.expanded==1?true:false);
		Ext.Ajax.request({
			url : context + '/module/saveModule.action',
			method : 'POST',
			jsonData : {
				modulePojo : formV
			},
			callback : function(opts, suc, res) {
				var ds = Ext.decode(res.responseText);
				if (ds.errorMessage) {
					showErrorMsg(ds.errorMessage);
				} else {
					moduleGrid.getStore().commitChanges();
					afterAddNode();
					showInfoMsg("保存成功！");
				}
			}
		});
	}
}
Ext.extend(KYB.ModuleManagerPanel, Ext.Panel);
Ext.reg("moduleManagerPanel", KYB.ModuleManagerPanel);