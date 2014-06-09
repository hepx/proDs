/**
 * 左则菜单
 */
Ext.ns("KYB");
KYB.MainMeau=function(){
	KYB.MainMeau.superclass.constructor.call(this, {
	    region:'west',
	    margins:'5 5 5 5',
		title:'导航菜单',
	    id : 'mainMenu',
	    collapsible: true,
	    width: 180,
	    layout:'accordion',
	    listeners:{
	    	render:function(t){
	    		Ext.Ajax.request({
	    			url : context+'/role/queryRoleModules.action',
	    			method : 'POST',
	    			callback : function(opts, suc, res) {
						var ds = Ext.decode(res.responseText);
						if (ds.errorMessage) {
							showErrorMsg(ds.errorMessage);
						} else {
							var data=ds.modulePojos;
							if(data){
								data=Ext.decode(Ext.encode(data).replace(/modulePojos/g,'children'));
				    			for (var index = 0; index < data.length; index++) {
				    				var re=data[index];
					                var meauPanel={
				    					xtype:'panel',
				    					iconCls : re.iconCls,
				    					border:false
				    				}
				    				var id=re.moduleId;
				    				meauPanel.title=re.text;
				    				var treeData=re.children;
				    				if(treeData){
			    						meauPanel.items=[{
			    							xtype:'maintree',
			    							id:id,
			    							root:new Ext.tree.AsyncTreeNode({
												text : 'mainMenu',
												children : treeData
											}) 
			    						}];
				    				} 
				    				t.add(meauPanel)
				    				t.doLayout();
				    			}
				    		}
						}
					}
	    		});
	    	}
	    }
	});
};
Ext.extend(KYB.MainMeau,Ext.Panel);
Ext.reg('mainmenu', KYB.MainMeau);