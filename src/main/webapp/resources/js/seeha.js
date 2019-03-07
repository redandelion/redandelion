!(function ($) {

    seeha = {
        version: '2.0'
    };
    seeha.orderStatus =  [['已申请','已审批','已完成','已入库','已驳回'],['已申请','已审批','已完成','已出库','已退货']];
    seeha.orderType =  ['采购订单','销售订单'];
    seeha.errorMaseege =  ['请选择入库明细','请选择出库库明细'];
    seeha.errorMaseegeOfInv =  ['该产品已入库完毕','该产品已出库完毕'];
    seeha.invOperaterType = ['入库','出库'];
    seeha.orderStatusChain =  [['采购申请','采购审批','采购确认','采购入库','采购驳回'],['销售申请','销售审批','销售确认','销售出库','销售退货']];
seeha.prepareSubmitParameter = function (options, type) {
    var datas = options.models;
    $.each(datas, function (i, r) {
        if (type == 'create')
            r['__status'] = 'add';
        else if (type == 'update')
            r['__status'] = 'update';
        else if (type == 'destroy')
            r['__status'] = 'delete';
    });
    return datas;
};
    seeha.prepareQueryParameter = function (obj, options) {
    obj = obj || {};
    if (options) {
        obj.page = options.page;
        obj.pagesize = options.pageSize;
        if (options.sort && options.sort.length > 0) {
            obj.sortname = options.sort[0].field;
            obj.sortorder = options.sort[0].dir;
        }
    }
    for (var k in obj) {
        if (obj[k] === '' || obj[k] === null || obj[k] === undefined)
            delete obj[k]
        if (obj[k] instanceof Date) {
            obj[k] = obj[k].toJSON()
        }
    }
    return obj;
};

})(jQuery);