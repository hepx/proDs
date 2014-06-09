Ext.ns('YSB.PROJS.GRID');

/*
 * 字段
 */
var fields = [{
			name : 'id',
			type : 'int'
		}, {
			name : 'mobile',
			type : 'string'
		}, {
			name : 'imsi',
			type : 'string'
		}, {
			name : 'createTime',
			type : 'date',
			dateFormat : 'Y-m-d\\TH:i:s'
		}, {
			name : 'service',
			type : 'string'
		}, {
			name : 'cardType',
			type : 'string'
		}, {
			name : 'province',
			type : 'string'
		}, {
			name : 'city',
			type : 'string'
		}];
/*
 * 列
 */
var cms = [{
			header : '<div align=center>手机号码</div>',
			dataIndex : 'mobile'
		}, {
			header : '<div align=center>识别码</div>',
			dataIndex : 'imsi'
		}, {
			header : '<div align=center>创建时间</div>',
			id : 'createTime',
			dataIndex : 'createTime',
			renderer : formatDateTime
		}, {
			header : '<div align=center>服务商</div>',
			width : 100,
			id : 'service',
			align : 'center',
			dataIndex : 'service'
		}, {
			header : '<div align=center>卡类型</div>',
			dataIndex : 'cardType'
		}, {
			header : '<div align=center>省份</div>',
			id : 'province',
			width : 100,
			dataIndex : 'province'
		}, {
			header : '<div align=center>城市</div>',
			width : 100,
			dataIndex : 'city'
		}];
var serviceList = ['移动', '联通', '电信'];
var provinceList = ['北京', '上海', '天津', '重庆', '湖南', '黑龙江', '吉林', '辽宁', '内蒙古',
		'河北', '河南', '广东', '湖北', '山东', '浙江', '安徽', '江苏', '江西', '云南', '宁夏', '青海',
		'山西', '陕西', '福建', '甘肃', '四川', '广西', '贵州', '海南', '西藏', '新疆'];
