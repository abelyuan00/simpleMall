
$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/categories/list?categoryLevel=' + 1 + '&superiorId=' + 0,
        mtype: 'GET',
        dataId: 'id',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: 'Name', name: 'name', index: 'name', width: 240},
            {label: 'Level', name: 'categoryLevel', index: 'categoryLevel', width: 120},
            {label: 'Created', name: 'createTime', index: 'createTime', width: 120},
            {label: 'Updated', name: 'updateTime', index: 'updateTime', width: 120},
            {label: 'Deleted', name: 'isDeleted', index: 'isDeleted', width: 60}
        ],
        height: 500,
        rowNum: 10,
        rowList: [10, 30, 50],
        styleUI: 'Bootstrap4',
        iconSet: "fontAwesome",
        loadtext: 'loading',
        rownumbers: true,
        rownumWidth: 35,
        autowidth: true,

        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        }
    });
});

function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function categoryAdd() {
    reset();
    $('.modal-title').html('Close');
    $('#categoryModal').modal('show');
}

/**
 * 管理下级分类
 */
function categoryManage() {
    var categoryLevel = parseInt($("#categoryLevel").val());
    var parentId = $("#superiorId").val();
    var id = getSelectedRow();
    if (id == undefined || id < 0) {
        return false;
    }
    categoryLevel = categoryLevel + 1;
    window.location.href = '/admin/categories?categoryLevel=' + categoryLevel + '&parentId=' + id + '&backParentId=' + parentId;
    window.location.href = '/admin/categories?categoryLevel=' + categoryLevel + '&parentId=' + id + '&backParentId=' + parentId;
}

function getSelectedRowWithoutAlert() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        return;
    }
    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        return;
    }
    return selectedIDs[0];
}

$('#saveButton').click(function () {
    var categoryName = $("#categoryName").val();
    var superiorId = $("#superiorId").val();
    var categoryRank = $("#categoryRank").val();
    var data = {
        "categoryName": categoryName,
        "categoryTier": superiorId,
        "categoryRank": categoryRank
    };
    var url = '/admin/categories/save';
    data = {
            "categoryName": categoryName,
            "superiorId": superiorId,
            "categoryRank": categoryRank
    };

    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                $('#categoryModal').modal('hide');
                reload();
            } else {
                $('#categoryModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("Failed", {
                icon: "error",
            });
        }
    });
});

function categoryEdit() {
    reset();
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid("getRowData", id);
    $('.modal-title').html('EDIT');
    $('#categoryModal').modal('show');
    $("#categoryId").val(id);
    $("#categoryName").val(rowData.categoryName);
    $("#categoryRank").val(rowData.categoryRank);
}


function reset() {
    $("#categoryName").val('');
    $("#categoryRank").val(0);
    $('#edit-error-msg').css("display", "none");
}
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        swal("Select at least one record", {
            icon: "warning",
        });
        return;
    }
    return grid.getGridParam("selrow");
}
function deleteCategory() {
    const ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "Deletion Confirm",
        text: "Do you want to delete this record?",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/categories/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("SUCCESS!", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        });
}