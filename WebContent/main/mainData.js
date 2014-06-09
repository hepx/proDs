Ext.ns("KYB");

KYB.meauData = [{
			"iconCls" : "icon-hotel",
			"id" : 1,
			"moduleId" : "platformMgr",
			"modulePojos" : [{
						"expanded" : true,
						"file" : "",
						"iconCls" : "icon-catalog",
						"id" : 2,
						"leaf" : false,
						"moduleId" : "sysManager",
						"modulePojos" : [{
									"expanded" : false,
									"file" : "\/sysManager\/userManager.js",
									"iconCls" : "details",
									"id" : 3,
									"leaf" : true,
									"moduleId" : "userManagerPanel",
									"text" : "用户管理"
								}, {
									"expanded" : false,
									"file" : "\/sysManager\/moduleManager.js",
									"iconCls" : "details",
									"id" : 4,
									"leaf" : true,
									"moduleId" : "moduleManagerPanel",
									"text" : "模块管理"
								}],
						"text" : "系统管理"
			}],
			"text" : "平台管理"
}]