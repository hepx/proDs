Ext.ns("KYB");

/**
 * 重写Ext.form.ComboBox的setValue方法
 * 解决combobox延迟加载在form中需要先设置data时不显示displayFile的问题
 */
 Ext.override(Ext.form.ComboBox, {
	setValue : function(v) {
		if (this.store.totalLength === undefined
				&& ((this.mode == 'local' && this.getStore().url != null) || (this.mode == 'remote'))) {
			this.store.on('load',
					this.setValue.createDelegate(this, arguments), null, {
						single : true
					});
			if (this.store.lastOptions === null) {
				this.store.load();
			}
			return;
		}
		var text = v;
		if (this.valueField) {
			var r = this.findRecord(this.valueField, v);
			if (r) {
				text = r.data[this.displayField];
			} else if (this.valueNotFoundText !== undefined) {
				text = this.valueNotFoundText;
			}
		}
		this.lastSelectionText = text;
		if (this.hiddenField) {
			this.hiddenField.value = v;
		}
		Ext.form.ComboBox.superclass.setValue.call(this, text);
		this.value = v;
	},
	doQuery : function(q, forceAll){
        q = Ext.isEmpty(q) ? '' : q;
        var qe = {
            query: q,
            forceAll: forceAll,
            combo: this,
            cancel:false
        };
        if(this.fireEvent('beforequery', qe)===false || qe.cancel){
            return false;
        }
        q = qe.query;
        forceAll = qe.forceAll;
        if(forceAll === true || (q.length >= this.minChars)){
            if(this.lastQuery !== q){
                this.lastQuery = q;
                if(this.mode == 'local'&&!(this.store.totalLength === undefined)){
                    this.selectedIndex = -1;
                    if(forceAll){
                        this.store.clearFilter();
                    }else{
                        this.store.filter(this.displayField, q);
                    }
                    this.onLoad();
                }else{
                    this.store.baseParams[this.queryParam] = q;
                    this.store.load({
                        params: this.getParams(q)
                    });
                    this.expand();
                }
            }else{
                this.selectedIndex = -1;
                this.onLoad();
            }
        }
    }
});

/**
 * 定义URL转码 去掉空字段
 * @param {} string
 * @param {} isFilter
 * @return {}
 */
function util_urlDecode(string, isFilter){
	var isFilter=isFilter||false;
    if(Ext.isEmpty(string)){
        return {};
    }
    var obj = {},
        pairs = string.split('&'),
        d = decodeURIComponent,
        name,
        value;
    Ext.each(pairs, function(pair) {
        pair = pair.split('=');
        name = d(pair[0]);
        value = d(pair[1]);
        if(isFilter){
        	if(value!=undefined&&value!=''){
        		obj[name] = !obj[name] ? value :[].concat(obj[name]).concat(value);
        	}
        }else{
        	obj[name] = !obj[name] ? value :[].concat(obj[name]).concat(value);
        }
    });
    return obj;
}

/**
 * 根据type来获得type的文本
 * @param {} type
 * @return {String}
 */
function getTypeText(type) {
	switch (type) {
		case 1 :
			return '查询';
		case 2 :
			return '录入';
	}
}

/**
 * 动态时间
 * @type 
 */
var task = {
	run : function() {
		Ext.fly('txt').update(new Date().format('Y年m月d日 H:i:s A'));
	},
	interval : 1000
	//1 second
}

/**
 * records 转换为JSON数据
 * @param {} records
 * @return {}
 */
function encodeRecordsToJsonArray(records) {
	var jsonArray = new Array();
	var len = records.length;
	for (var i = 0; i < len; i++) {
		jsonArray.push(records[i].data);
	}
	return jsonArray;
}
/**
 * 日期转换
 */
function formatDate(value) {
	return value ? value.dateFormat('Y-m-d') : '';
}

/**
 * 日期+时间转换
 * @param {} value
 * @return {}
 */
function formatDateTime(value){
	return value ? value.dateFormat('Y-m-d H:i:s') : '';
}

function formatNumber(value){
	return value ? Ext.util.Format.number(value,'0.00') : '0.00';
}

/**
 * 分页控件
 */
function createPageBar(store, ps) {
	var pageBar = new Ext.PagingToolbar({
        plugins:new Ext.ux.Andrie.pPageSize(),
        pageSize: ps||20,
        store: store,
        displayInfo: true
    });
	return pageBar;
}

/**
 * 页面刷新，重新组织数据
 */
function doRefresh(store) {
	if (store) {
		store.load();
	}
}
/**
 * 当下拉框在网格中显示时的渲染方法
 * @param {} combo
 * @return {}
 */
comboRenderer = function(combo) {
	return function(value) {
		var record = combo.findRecord(combo.valueField, value);
		return record
				? record.get(combo.displayField)
				: combo.valueNotFoundText;
	}
}
/**
 * 网格图片渲染方法
 * @param {} val
 * @return {}
 */
imageRenderer = function(val) {
	return '<img src="'+(val)+'" width="70" height="70">';
}
/**
 * 网格中链接渲染方法
 * @param {} val
 * @return {}
 */
linkRenderer = function(val){
	var title=decodeURI(val.substring(val.lastIndexOf('/')+1,val.length));
　　 return '<a target="_blank" href="'+(val)+'">'+title+'</a>'
}

/**
 * JS深度复制对象和数组
 * @param {} o
 * @return {}
 */
clone = function(o) {
	if (!o || 'object' !== typeof o) {
		return o;
	}
	if ('function' === typeof o.clone) {
		return o.clone();
	}
	var c = '[object Array]' === Object.prototype.toString.call(o) ? [] : {};
	var p, v;
	for (p in o) {
		if (o.hasOwnProperty(p)) {
			v = o[p];
			if (v && 'object' === typeof v) {
				c[p] = clone(v);
			} else {
				c[p] = v;
			}
		}
	}
	return c;
};

/**
 * 检查是否为空
 * @param {} o
 * @return {Boolean}
 */
isNull=function(o){
	if(o==null||o=='undefined'){
		return true;
	}else if(typeof(o)=='object'&&o.length==0){
		return true;
	}
	return false;
};

/**
 * 提取加载JS的路径为一个数组
 * @param {} file
 * @return {}
 */
function getLoadJsFile(file){
	var fileArray=[];
	if(file){
		var files=file.split(',');
		for(var i=0;i<files.length;i++){
			fileArray.push(context+files[i]);
		}
	}
	return fileArray;
};
/**
 * 记录状态下拉
 */
KYB.recordStatusCombo = Ext.extend(Ext.form.ComboBox, {
	constructor: function(config) {
		var store = {
			xtype : 'arraystore',
			fields : ['value','name'],
			data : [[0,'<span style="color:red;">末发布</span>'],
					[1,'<span style="color:blue;">已编辑</span>'],
					[2,'<span style="color:green;">已发布</span>']]
		};
		config = Ext.apply({
			triggerAction : 'all',
			typeAhead : true,
			lazyRender:true,
			mode : 'local',
			editable : false,
			allowBlank: false,
			hiddenName: 'recordStatus',
			valueField : 'value',
			displayField : 'name',
			store: store
		}, config);
		KYB.recordStatusCombo.superclass.constructor.call(this, config);
	}
});
/**
 * 权限checkBox
 * @class KYB.PermissionCheckbox
 * @extends Ext.form.Checkbox
 */
KYB.PermissionCheckbox=Ext.extend(Ext.form.Checkbox,{
	    setValue: function (vals) {
    	if(typeof vals === 'boolean'){
	    	var checked = this.checked,
	            inputVal = this.inputValue;
	            
	        if (vals === false) {
	            this.checked = false;
	        } else {
	            this.checked = (vals === true || vals === 'true' || vals == '1' || (inputVal ? vals == inputVal : String(vals).toLowerCase() == 'on'));
	        }
	        
	        if(this.rendered){
	            this.el.dom.checked = this.checked;
	            this.el.dom.defaultChecked = this.checked;
	        }
	        if(checked != this.checked){
	            this.fireEvent('check', this, this.checked);
	            if(this.handler){
	                this.handler.call(this.scope || this, this, this.checked);
	            }
	        }
	        return this;
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
Ext.reg('permissioncheckbox',KYB.PermissionCheckbox);

/**
 * 创建一个图片查看WINDOW
 * @param {} text
 * @param {} pic
 * @return {}
 */
function createImageHtml(text,pic){
	return '<div style="padding:10px 10px 0px 10px">图片尺寸：<span id="imgSize" style="color:red"></span></div>' +
		   '<div style="padding:10px">'+text+'</div>' +
		   '<img id="picer" src="'+pic+'">';
}
function crateImageReadWin(g,rowIndex){
	var count=g.getStore().getCount();
	var text=g.getStore().getAt(rowIndex).data.text;
	var pic=g.getStore().getAt(rowIndex).data.bmiddlePic;
	if(pic){
		var html=
		new Ext.Window({
			title : '图片浏览【窗口尺寸480px*450px】',
			layout : 'fit',
			width : 480,
			height : 450,
			modal : true,
			constrain : true,
			autoDestroy : true,
			resizable : false,
			items : [{
				xtype : 'panel',
				id : 'bdgimgPanel',
				autoScroll : true,
				border : false,
				html: createImageHtml(text,pic),
				listeners :{
					afterlayout : function(p){
						var img=Ext.getDom('picer');
						//Ext.getDom('imgSize').innerHTML=img.width+'*'+img.height;
						Ext.getDom('imgSize').innerHTML=img.naturalWidth+'*'+img.naturalHeight;
					}
				}
			}],
			buttonAlign: 'center',
			buttons:[{
				text : '上一张',
				handler : function(){
					if(rowIndex>0){
						rowIndex-=1;
						text=g.getStore().getAt(rowIndex).data.text;
						pic=g.getStore().getAt(rowIndex).data.bmiddlePic;
						Ext.getCmp('bdgimgPanel').update(createImageHtml(text,pic));
						Ext.getCmp('bdgimgPanel').doLayout(true);
					}
				}										
			},{
				text : '下一张',
				handler : function(){
					if(rowIndex<(count-1)){
						rowIndex+=1;
						text=g.getStore().getAt(rowIndex).data.text;
						pic=g.getStore().getAt(rowIndex).data.bmiddlePic;
						Ext.getCmp('bdgimgPanel').update(createImageHtml(text,pic));
						Ext.getCmp('bdgimgPanel').doLayout(true);
					}
				}
			}]
		}).show();
	}
}

/**
 * APP分类下拉菜单
 * @class KYB.MARKET.AppCategoryCombox
 * @extends Ext.form.ComboBox
 */
KYB.AppCategoryCombox=Ext.extend(Ext.form.ComboBox,{
	triggerAction : 'all',
	mode: 'local',
	typeAhead : true,
	lazyRender:true,
	editable : false,
	store: new Ext.data.JsonStore({
		url:context+"/market/category/queryAllAppCategory.action",
		method:'POST',
		//autoLoad:true,
		root : 'appCategorys',
		fields : [{
				name: 'categoryName',
				type: 'string'
			},{
				name: 'categoryValue',
				type: 'string'
			}]
	}),
	hiddenName: 'appCategory',
	valueField : 'categoryValue',
	displayField : 'categoryName'
});
Ext.reg('appCategoryCombox',KYB.AppCategoryCombox);

/**
 * 时间数组
 * @type 
 */
var HOUR_DATA=[[0, '0:00'],[1, '1:00'],[2, '2:00'],[3, '3:00'],[4, '4:00'],[5, '5:00'],[6, '6:00'],[7, '7:00'],
				   [8, '8:00'],[9, '09:00'],[10, '10:00'],[11, '11:00'],[12, '12:00'],[13, '13:00'],[14, '14:00'],[15, '15:00'],
				   [16, '16:00'],[17, '17:00'],[18, '18:00'],[19, '19:00'],[20,'20:00'],[21, '21:00'],[22, '22:00'],[23, '23:00']];

/**
 * 服务商下拉框
 * @class ServiceCombox
 * @extends Ext.form.ComboBox
 */
ServiceCombox =Ext.extend(Ext.form.ComboBox,{
	typeAhead : true,
	lazyRender:true,
	editable : false,
	allowBlank: false,
	triggerAction : 'all',
	mode : 'local',
	store : new Ext.data.ArrayStore({
				fields : ['ser'],
				data : [['移动'], ['联通'], ['电信']]
			}),
	hiddenName: 'service',
	valueField : 'ser',
	displayField : 'ser'
});
/**
 * 省份下拉框
 * @class ProvinceCombox
 * @extends Ext.form.ComboBox
 */
ProvinceCombox = Ext.extend(Ext.form.ComboBox,{
	typeAhead : true,
	triggerAction : 'all',
	editable : false,
	allowBlank: false,
	lazyRender:true,
	mode : 'local',
	store : new Ext.data.ArrayStore({
				fields : ['pro'],
				data : [['北京'], ['上海'], ['天津'], ['重庆'], ['湖南'],
						['黑龙江'], ['吉林'], ['辽宁'], ['内蒙古'], ['河北'],
						['河南'], ['广东'], ['湖北'], ['山东'], ['浙江'], ['安徽'],
						['江苏'], ['江西'], ['云南'], ['宁夏'], ['青海'], ['山西'],
						['陕西'], ['福建'], ['甘肃'], ['四川'], ['广西'], ['贵州'],
						['海南'], ['西藏'], ['新疆']]
			}),
	hiddenName : 'province',
	valueField : 'pro',
	displayField : 'pro'
});

/****************************************************************
 * QDTG NS begin                                            *
 * **************************************************************/

/**
 * 客户下拉框
 * @class KYB.CvsCombox
 * @extends Ext.form.ComboBox
 */
KYB.CvsCombox = Ext.extend(Ext.form.ComboBox,{
	fieldLabel: '客户',
	typeAhead : true,
	lazyRender:true,
	mode:'local',
	triggerAction : 'all',
	store : new Ext.data.JsonStore({
		url : context + '/qdtg/cvs/queryAllQdtgCvs.action',
		root : 'qdtgCvsList',
		fields : [{
			name: 'cvsId',
			type: 'int'
		}, {
			name : 'cvsName',
			type: 'string'
		}]
	}),
	hiddenName:'cvsId',
	valueField : 'cvsId',
	displayField : 'cvsName'
});
Ext.reg('cvsCombox',KYB.CvsCombox)
/**
 * 产品下拉框
 * @class KYB.ProductCombox
 * @extends Ext.form.ComboBox
 */
KYB.ProductCombox = Ext.extend(Ext.form.ComboBox,{
	fieldLabel: '产品',
	typeAhead : true,
	lazyRender:true,
	mode:'local',
	triggerAction : 'all',
	store : new Ext.data.JsonStore({
		url : context + '/qdtg/product/queryAllQdtgProduct.action',
		root : 'qdtgProducts',
		fields : [{
			name : 'productId',
			type : 'int'
		}, {
			name : 'productName',
			type : 'string'
		}]
	}),
	hiddenName:'productId',
	valueField : 'productId',
	displayField : 'productName'
});
Ext.reg('productCombox',KYB.ProductCombox);

/**
 * 渠道下拉选择框
 * @class Ext.ChannelCombox
 * @extends Ext.form.ComboBox
 */
KYB.ChannelCombox=Ext.extend(Ext.form.ComboBox,{
	fieldLabel: '渠道号',
	mode: 'local',
	lazyRender: true,
	typeAhead: true,
	triggerAction: 'all',
	hiddenName: 'channelId',
	valueField: 'channelId',
	displayField: 'channelNo',
	store: new Ext.data.JsonStore({
		url: context+'/qdtg/channel/queryAllQdtgChannel.action',
		root: 'qdtgChannels',
		method: 'POST',
		fields: [{
			name: 'channelId',
			type: 'int'
		},{
			name: 'channelNo',
			type: 'string'
		},{
			name: 'fileName',
			type: 'string'
		}]
	})
});
Ext.reg('channelCombox',KYB.ChannelCombox);
/****************************************************************
 * QDTG NS end                                            *
 * **************************************************************/