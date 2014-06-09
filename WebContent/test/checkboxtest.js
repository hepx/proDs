Ext.ns('KYB');
KYB.RoleCheckbox=Ext.extend(Ext.form.Checkbox,{
	    setValue: function (vals) {
    	if(typeof vals === 'boolean'){
    		return ;
    	}
        var a = [];
        if (Ext.isArray(vals)) {
            a = vals;
        } else {
            a = vals.split(',');
        }
        var items=Ext.query('input[name='+this.name+']');
        for (var i = 0; i < items.length; i++) {
            items[i].checked = false;
            for (var j = 0; j < a.length; j++) {
                var val = a[j];
                if (val == items[i].value) {
                    items[i].checked = true;
                }
            }
        }
    }
});
Ext.reg('rolecheckbox', KYB.RoleCheckbox);
//Ext.override(Ext.form.Checkbox,{
//    setValue: function (vals) {
//    	if(typeof vals === 'boolean'){
//    		return ;
//    	}
//        var a = [];
//        if (Ext.isArray(vals)) {
//            a = vals;
//        } else {
//            a = vals.split(',');
//        }
//        var items=Ext.query('input[name='+this.name+']');
//        for (var i = 0; i < items.length; i++) {
//            items[i].checked = false;
//            for (var j = 0; j < a.length; j++) {
//                var val = a[j];
//                if (val == items[i].value) {
//                    items[i].checked = true;
//                }
//            }
//        }
//    }
//});
Ext.onReady(function() {
	var myForm = new Ext.form.FormPanel({
		title : 'checkboxTest',
		width : 700,
		padding : 5,
		frame : true,
		renderTo : 'mybody',
		layout : 'table',
		collapsible : false,
		labelWidth : 100,
		layoutConfig : {
			labelWidth : 100,
			columns : 2
		},
		items : [{
					layout : 'table',
					colspan : 2,
					items : [{
								layout : 'form',
								items : [{
											xtype : 'textfield',
											fieldLabel : '用户',
											name : 'user'
										}]
							}, {
								layout : 'form',
								items : [{
											xtype : 'textfield',
											fieldLabel : '角色',
											name : 'roleName'

										}]
							}]

				}, {
					layout : 'table',
					xtype : 'panel',
					items : [{
								xtype : 'rolecheckbox',
								boxLabel : '用户模块',
								name : 'moduleIds',
								style : 'top: 4px; position: relative; left: 0px;',
								inputValue : 1
							}, {
								xtype : 'rolecheckbox',
								boxLabel : '录入',
								name : 'functionIds',
								style : 'top: 4px; position: relative; left: 0px;',
								inputValue : 2
							}, {
								xtype : 'rolecheckbox',
								boxLabel : '查询',
								name : 'functionIds',
								style : 'top: 4px; position: relative; left: 0px;',
								inputValue : 3
							}]
				}, {
					layout : 'table',
					xtype : 'panel',
					items : [{
								xtype : 'rolecheckbox',
								boxLabel : '权限模块',
								name : 'moduleIds',
								inputValue : 4
							}, {
								xtype : 'rolecheckbox',
								boxLabel : '查询',
								name : 'functionIds',
								inputValue : 5
							}, {
								xtype : 'rolecheckbox',
								boxLabel : '录入',
								name : 'functionIds',
								inputValue : 6
							}]
				}],
		tbar : [{
					text : '设值',
					handler : setV
				}, '-', {
					text : '取值',
					handler : showV
				}]
	});

	function showV() {
		alert(Ext.encode(myForm.getForm().getValues()));
	}
	function setV() {
		myForm.getForm().reset();
		var a = {
			"user" : "xixi",
			"roleName" : '管理员',
			"moduleIds" : [1],
			"functionIds" : [2,3]
		};

		myForm.getForm().setValues(a);
	}
});