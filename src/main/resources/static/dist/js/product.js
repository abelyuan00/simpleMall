
$("#jqGrid").jqGrid({
    url: 'product/list',
    datatype: "json",
    dataId: 'id',
    mtype:'GET',
    colModel: [
        {label: 'ID', name: 'id', index: 'id', width: 50, hidden: false, key: true},
        {label: 'Code', name: 'code', index: 'code', sortable: false, width: 80},
        {label: 'Update time', name: 'updateTime', index: 'updateTime', sortable: false, width: 80},
        {label: 'Status', name: 'status', index: 'status',sortable: false, width: 40},
        {label: 'Name', name: 'productName', index: 'productName', sortable: false, width: 80},
        {label: 'Description', name: 'description', index: 'description', sortable: false, width: 80}
    ],
    height: 500,
    rowNum: 10,
    rowList: [10, 30, 50],
    styleUI: 'Bootstrap4',
    iconSet: "fontAwesome",
    loadtext: 'loading',
    rownumbers: false,
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
