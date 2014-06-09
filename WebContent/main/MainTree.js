/**
 * 树
 */
Ext.ns("KYB");

KYB.MainTree = function(config) {
	config=Ext.apply({
		border : false,
		rootVisible: false,
		autoScroll : true,
		collapseFirst : false,
		listeners: {//监听树的点击事件。
            click: function(node) {
            	//当为叶子节点时
                if(node.attributes.leaf){
                	var moduleId=node.attributes.moduleId;
                	//根据ID查找此Component
                	var cmp=Ext.getCmp(moduleId);
                	//根据ID得到tab对象,ID在MainTab中指定
                	var tabs=Ext.getCmp("mainTab");
                	if(!cmp){
                		//先加载所需的JS文件。然后再创建出来。避免在开始页里加载一次把所有的JS都加载
                		var file=node.attributes.file;
                		var fileArray=getLoadJsFile(file)
                		Ext.Loader.load(fileArray,function(){
                			tabs.add({xtype:moduleId}).show();
                		});
                	}else{
                		tabs.setActiveTab(cmp);
                	}
                }
            }
		}
	},config);
	KYB.MainTree.superclass.constructor.call(this,config);
}
Ext.extend(KYB.MainTree,Ext.tree.TreePanel);
Ext.reg('maintree', KYB.MainTree);