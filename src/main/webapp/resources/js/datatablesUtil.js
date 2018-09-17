var form;

function makeEditable() {
    form = $('#detailsForm');

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#detailsFormProduct').button(function () {
        $('#editRowProduct').modal('hide');
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    })
}

function add() {
    $('#editRow').modal();
}

function deleteRow(id) {

    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('common.deleted');
        }
    });
}

function updateRow(id) {
    $('#modalTitle').html(i18n[editTitleKey]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function arrayHaveValue(str, text) {
    return str.indexOf(text) !== -1;
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (key, item) {
            datatableApi.row.add(item);
        });
        datatableApi.draw();
    });
}

function save() {
    var form = $('#detailsForm');

    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('common.saved');
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function successNoty(key) {
    iziToast.show({
        color: 'green',
        position: 'bottomRight',
        timeout: 2000,
        message: i18n[key]
    });
}

function successButNo(key) {
    iziToast.show({
        color: 'red',
        position: 'bottomRight',
        timeout: 2000,
        message: i18n[key]
    });
}

function failNoty(event, jqXHR, options, jsExc) {

    var errorInfo = $.parseJSON(jqXHR.responseText);
    iziToast.show({
        message: i18n['common.status'] + ': ' + jqXHR.status + "<br>" + errorInfo.cause + "<br>" + errorInfo.details.join("<br>"),
        color: 'red',
        position: 'bottomRight',
        timeout: 2000
    });
}
