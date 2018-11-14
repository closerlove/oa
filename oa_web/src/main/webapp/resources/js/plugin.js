/**
 * 打开弹出框
 */
function openDialog(div, title, w, h){
    if (w == null){
        w = 700;
    }

    if(h == null){
        h = 600;
    }

    $("#" + div).dialog({
        title:title,
        width:w,
        height:h,
        modal:true
    });
}

//关闭弹出框
function closeDialog(div){
    $("#" + div).dialog("close");
}


/**
 * 创建一个Ztree
 */
function createZtree(divid, data, param,did){
    //生成ztree
    var setting = {
        data: {
            key: {
                name: param.name
            },
            simpleData: {
                enable: true,
                pIdKey: param.pid
            }
        },
        view: {
            showIcon: param.icon
        },
        callback: {
            onClick: param.onclick
        },
        check:{
            enable: param.check == null ? false : param.check,
            chkboxType:param.checkType ==null ? {"Y":"ps","N":"ps"} :param.checkType
        }
    };
    var zNodes = data;

    //初始化zTree
    var ztreeObject = $.fn.zTree.init($("#" + divid), setting, zNodes);
    ztreeObject.expandAll(param.expand);//全部展开

    if(did!=null){

        var nodes=ztreeObject.getNodeByParam("id",did,null);
        ztreeObject.selectNode(nodes);
    }

    return ztreeObject;
}