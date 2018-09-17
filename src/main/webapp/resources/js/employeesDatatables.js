var ajaxUrl = 'ajax/employees/';
var datatableApi;
var editTitleKey ="users.edit";

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "department.name"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
});

function getMounth(str) {
    var mounth = ['янв','фев','март','апр','май','июнь','июль','авг','сен','окт','нояб','дек'];
    var number = parseInt(str.substring(5,7));
    return str.substring(8,10) +'-'+ mounth[number] + '-'+ str.substring(0,4);
}

function renderEditBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-xs edit" onclick="updateRow(' + row.id + ');">' +
            '<span class="material-icons" aria-hidden="true">&#xE254;</span></a>';
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === 'display') {
        return '<a class="btn btn-xs delete" onclick="deleteRow(' + row.id + ');">' +
            '<span class="material-icons" aria-hidden="true">&#xE872;</span></a>';
    }
}