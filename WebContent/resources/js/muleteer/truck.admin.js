var muleNumber;
var muleURL;
var muleConnectionURLTemplate = "http://" + location.host + "/tracking/mule-";


$(document).ready(function () {

    $("[id*='conn-']").click(function () {
        muleNumber = $(this).attr("id").substring(5);
        muleURL = muleConnectionURLTemplate + muleNumber;

        $("#qr").empty();
        $("#qr").ClassyQR({
            create: true,
            size: 200,
            type: 'url',
            url: muleURL
        });

        $("#myModal").modal('show');
        updateMule(muleNumber);
    });

    function updateMule(n) {
        var name = $('#name-' + n).val();
        var status = $("#active-" + n).is(':checked') ? true : false;

        var addr = "http://" + location.host + location.pathname + "/mule-" + n;

        $.post(addr,
            {
                name: name,
                status: status
            },
            function (data, status) {
            });
    }

    function deleteMule(n) {
        var addr = "http://" + location.host + location.pathname + "/del/mule-" + n;

        $.post(addr, {}, function (data, status) {
            if (status == "success") {
                var trId = 'muleList-' + n;
                if (data == true) {
                    $("#" + trId).fadeOut();
                }
                console.log("data - " + data);
                console.log("id - " + trId);
            }
        });
    }

    $("[id*='name-']").blur(function () {
        n = $(this).attr("id").substring(5);
        updateMule(n);
    });

    $("[id*='active-']").click(function () {
        n = $(this).attr("id").substring(7);
        updateMule(n);
    });

    $("button[id*='delete-']").click(function () {
        var n = $(this).attr("id").substring(7);
        var trId = 'muleList-' + n;
        deleteMule(n);
    });

    $("#addMule").click(function () {
        var addr = "http://" + location.host + location.pathname + "/get-new-mule" ;

        $.get(addr, {}, function (data, status) {
            if (status == "success") {
                    newMule = JSON.parse(data);
                    console.log(newMule);
                    console.log(newMule.id);
                    console.log(newMule.name);
                    var str =
                        '<tr>' +
                        '<td>' +
                            '<input id=\"active-'+newMule.id+'\" type=\"checkbox\"' +
                        '</td>' +
                        '<td>' +
                            '<input id=\"name-'+newMule.id+'\" maxlength=\"30\" type=\"text\" class=\"form-control\" value='+newMule.name+'>' +
                        '</td>' +
                        '<td>' +
                            '<button id=\"conn-'+newMule.id+'\" type=\"button\" class=\"btn btn-success\">connect</button>' +
                            '<button id=\"delete-'+newMule.id+'\" type=\"button\" class=\"btn btn-danger\">remove</button>' +
                        '</td>' +
                        '</tr>';
                    //$('#tableMules > tbody:last-child').append(str);
                    $('#tableMules').find('tbody:last').append(str);
            }
        });
    });
});
