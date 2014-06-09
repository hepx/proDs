Ext.ns("KYB");

/**
 * 获得相对路径
 * 
 * @return 相对路径
 */
function getContext() {
	var pathname = window.location.pathname;
	var isModalDialog = (pathname.indexOf("/") != 0);
	if (!isModalDialog)
		pathname = pathname.substring(1, pathname.length);
	pathname = pathname.substring(0, pathname.indexOf("/"));
	return "/" + pathname;
}
var context = getContext(); // 相对路径

/**
 * TAP的宽
 * @type 
 */
var tab_width=null;
function getTableWidth(){
	if(!tab_width){
		tab_width=Ext.get('mainTab').getWidth();
	}
	return tab_width;
}

/**
 * alert框
 * 
 * @param {}
 *            msg 提示的信息
 */
function showInfoMsg(msg) {
//	Ext.MessageBox.show({
//		title : '提示：',
//		msg : msg,
//		minWidth : 300,
//		buttons : Ext.MessageBox.OK,
//		icon : Ext.MessageBox.INFO
//	});
	KYB.example.infoMsg('提示',msg);
}
/**
 * 错误
 * @param {} msg
 */
function showErrorMsg(msg){
//	Ext.MessageBox.show({
//		title : '错误：',
//		msg : msg,
//		minWidth : 300,
//		buttons : Ext.MessageBox.OK,
//		icon : Ext.MessageBox.ERROR
//	});
	KYB.example.errorMsg('出错了',msg);
}
/**
 * 警告
 * @param {} msg
 */
function showWarnMsg(msg){
//	Ext.MessageBox.show({
//		title : "警告：",
//		msg : msg,
//		minWidth : 300,
//		buttons :Ext.MessageBox.OK,
//		icon : Ext.MessageBox.WARNING
//	});
	KYB.example.warnMsg('警告',msg);
}

KYB.example = function(){
    var msgCt;
    function createBox(t, s){
        return ['<div class="msg">',
                '<div class="x-box-tl"><div class="x-box-tr"><div class="x-box-tc"></div></div></div>',
                '<div class="x-box-ml"><div class="x-box-mr">',
                '<div class="x-box-mc"><h3>', t, '</h3>', s, '</div></div></div>',
                '<div class="x-box-bl"><div class="x-box-br"><div class="x-box-bc"></div></div></div>',
                '</div>'].join('');
    }
    return {
        infoMsg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            m.slideIn('t').pause(1).ghost("t", {remove:true});
        },
        warnMsg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            Ext.select('[class=x-box-mc]').setStyle('color','#FF6600');
            m.slideIn('t').pause(1).ghost("t", {remove:true});
        },
        errorMsg : function(title, format){
            if(!msgCt){
                msgCt = Ext.DomHelper.insertFirst(document.body, {id:'msg-div'}, true);
            }
            msgCt.alignTo(document, 't-t');
            var s = String.format.apply(String, Array.prototype.slice.call(arguments, 1));
            var m = Ext.DomHelper.append(msgCt, {html:createBox(title, s)}, true);
            Ext.select('[class=x-box-mc]').setStyle('color','#CC0000');
            m.slideIn('t').pause(1).ghost("t", {remove:true});
        },
        init : function(){
            var lb = Ext.get('lib-bar');
            if(lb){
                lb.show();
            }
        }
    };
}();

/**
 * 定义一个加载的进度
 * 
 * @type
 */
var mask;
function showMask(el, msg) {
	mask = new Ext.LoadMask(el, {
				msg : msg || '请稍后......',
				removeMask : true
			});
	mask.show();
}
function hideMask() {
	if (mask) {
		mask.hide();
		mask.destroy();
	}
}
/**
 * 确认密码验证 Vtype
 */
Ext.apply(Ext.form.VTypes, {
	password : function(val, field) {
		if (field.initialPassField) {
			var pwd = Ext.getCmp(field.initialPassField);
			return (val == pwd.getValue());
		}
		return true;
	},
	passwordText : '两次密码不一致！'
});