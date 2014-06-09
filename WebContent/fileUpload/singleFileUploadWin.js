Ext.ns('KYB');

KYB.SingleFileUploadWin = function(config,title,ds,url_,fn){ 
    config = Ext.apply({
        fileUpload: true,
        //width: 500,
        frame: true,
        autoHeight: true,
        bodyStyle: 'padding: 10px 10px 0 10px;',
        labelWidth: 50,
        defaults: {
            anchor: '95%',
            allowBlank: false,
            msgTarget: 'side'
        },
        items: [{
            xtype: 'fileuploadfield',
            id: 'form-file',
            emptyText: '选择一个文件',
            fieldLabel: '文件',
            name: 'filePath',
            buttonText: '',
           	buttonCfg: {
                iconCls: 'icon-addFile'
            }
        }],
        buttons: [{
            text: '上传',
            iconCls :'icon-up',
            handler: function(){
                if(uploadForm.getForm().isValid()){
	                uploadForm.getForm().submit({
	                    url: url_,
	                    waitTitle : '请稍候',
	                    waitMsg: '正在上传...',
	                    success: function(form, action){
	                        showInfoMsg(action.result.msg);
	                        if(action.result.apk){
	                        	fn.call(this,action.result.apk);
	                        }else{
	                        	fn.call(this,ds);
	                        }
	                    },
	                    failure: function(form, action) {
	                    	showErrorMsg(action.result.msg);
	                    }
	                });
                }
            }
        },{
            text: '重置',
            iconCls :'icon-redo',
            handler: function(){
                uploadForm.getForm().reset();
            }
        }]
    },config);

    var uploadForm =new Ext.FormPanel(config);
	KYB.SingleFileUploadWin.superclass.constructor.call(this, {
		id : 'singleFileUploadWin',
		title : title || '上传',
		width : 500,
		border : false,
		resizable : false,
		modal : true,
		constrain : true,
		items : [uploadForm]
	});
};

Ext.extend(KYB.SingleFileUploadWin,Ext.Window);