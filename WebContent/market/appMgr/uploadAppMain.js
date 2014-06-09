Ext.ns('KYB.MARKET');

KYB.MARKET.UploadAppPanel=function(){
	
	var tbar = [{
		text : '上传APK包',
		iconCls : 'icon-up',
		handler : uploadApk
	}];

	var uploadAppForm = new Ext.FormPanel({
        frame:true,
        monitorValid : true,
        id:'uploadAppForm',
        bodyStyle:'padding:5px 5px 0',
        labelWidth :100,
        defaults:{
        	xtype:'textfield'
        },
        items: [{
            fieldLabel: '应用名',
            name: 'appName',
            allowBlank : false,
            anchor:'90%'
        },new KYB.AppCategoryCombox({
        	fieldLabel : '分类',
            allowBlank : false,
            anchor:'50%'
        }),{
        	xtype:'numberfield',
        	fieldLabel:'资费',
        	name: 'fee',
        	value : 0,
        	anchor:'50%'
        },{
            xtype : 'box',   
            fieldLabel : "APP图标",  
            autoEl : {  
                width : 72,  
                height : 72,  
                tag : 'img',
                src : Ext.BLANK_IMAGE_URL,  
                //style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);',  
                complete : 'off',  
                id : 'imageBrowse'  
            }  
  		},{
        	fieldLabel: '包名',
        	name: 'packageName',
        	readOnly:true,
        	cls:'x-item-disabled',
        	emptyText: 'APP的唯一包名',
        	anchor:'90%'
        },{
        	fieldLabel: '大小',
        	name: 'appSize',
        	readOnly:true,
        	cls:'x-item-disabled',
        	anchor: '90%'
        },{
            fieldLabel: '版本号(升级)',
            name: 'appVersionCode',
            readOnly:true,
        	cls:'x-item-disabled',
            emptyText : '(升级)版本号只能由数字组成，便于升级更新',
            anchor:'90%'
        },{
            fieldLabel: '版本号(展示)',
            name: 'appVersionName',
        	readOnly:true,
        	cls:'x-item-disabled',            
            emptyText : '(展示)版本号可由字符、数字、特殊字符组合',
            anchor:'90%'
        },{
        	fieldLabel: '支持SDK',
        	name: 'sdkSupport',
        	readOnly:true,
        	cls:'x-item-disabled',        	
        	anchor:'90%'
        },{
        	xtype :'textarea',
            fieldLabel : '应用说明',
            name : 'appDesc',
            height : 80,
            anchor:'90%'
        },{
        	name: 'appPic',
        	hidden: true
        },{
        	name : 'appPath',
        	hidden:true
        },{
        	name : 'createUser',
        	hidden:true,
        	value: USER_NAME
        }],

		buttons : [{
			text : '提交',
			iconCls : 'icon-save',
			formBind : true,
			handler : function() {
				var formDs = uploadAppForm.getForm().getValues();
				Ext.Ajax.request({
					url : context+"/market/appmgr/saveAppInfo.action",
					method : 'POST',
					jsonData : {
						appInfo : formDs
					},
					callback : function(opts, sus, res) {
						var das = Ext.decode(res.responseText);
						if (das.errorMessage) {
							showErrorMsg(das.errorMessage);
						} else {
							showInfoMsg("APP提交成功，请到APP列表中查看！");
							uploadAppForm.getForm().reset();
						}
					}
				});
			}
		}]
    });
	
    var uploadAppWin=new Ext.Window({
    	id:'uploadAppWin',
    	x:(getTableWidth()-500)/2,
    	y:30,
		width : 500,
		shim:false,
		border:false,
		draggable : false,
		closable : false,
		resizable : false,
		initHidden:false,
		maximizable : true,
		shadow : false,
		tbar:tbar,
		items: [uploadAppForm]
    });
	KYB.MARKET.UploadAppPanel.superclass.constructor.call(this, {
		title : '上传APP',
		id : 'uploadAppPanel',
		autoScroll : true,
		closable : true,
		items: [uploadAppWin]
	});
	
	function uploadCallBack(data){
		uploadAppForm.getForm().setValues(data);
		Ext.get('imageBrowse').dom.src=data.appPic;
	}
	
	function uploadApk(){
		var url=context+'/market/appmgr/uploadApp.action';
		new KYB.SingleFileUploadWin({
			items: [{
	            xtype: 'fileuploadfield',
	            id: 'form-market-app',
	            emptyText: '选择一个小于10M的APK安装文件',
	            fieldLabel: '安装文件',
	            name: 'app',
	            buttonText: '',
	            buttonCfg: {
	                iconCls: 'icon-addFile'
	            }
    		}]
		},"上传APK",null,url, uploadCallBack).show();
	}
}
Ext.extend(KYB.MARKET.UploadAppPanel,Ext.Panel);
Ext.reg('uploadAppPanel',KYB.MARKET.UploadAppPanel);