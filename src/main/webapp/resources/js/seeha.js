!(function ($) {

    seeha = {
        version: '2.0'
    };
    seeha.orderStatus =  ['已申请','已审批','已完成','已入库','已驳回'];
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