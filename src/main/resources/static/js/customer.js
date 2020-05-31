var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and destroy
        if ($.fn.DataTable.isDataTable('#tableBiodata')) {
            // table yg sudah dibentuk menjadi datatable harus di-rebuild lagi
			// untuk di-instansiasi-ulang
            $('#tableBiodata').DataTable().clear();
            $('#tableBiodata').DataTable().destroy();
        }

        $.ajax({
            url: '/api/customer/get-all',
            method: 'get',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#tableBiodata').DataTable({
                        data: res,
                        columns: [
                               {
                                   title: "Nama Lengkap",
                                   data: "fullName"
                               },
							   {
								   title: "Email",
								   data: "email"
							   },
							   {
								   title: "Alamat",
								   data: "address"
							   },
							   {
								   title: "Kota/Kabupaten",
								   data: "kotaKabupaten"
							   },
                               {
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='btn-success' onclick=formBiodata.setEditData('" + data.id + "')><i class='fa fa-fw fa-edit'></i></button>" +
                                    	   "<button class='btn-danger' onclick=formBiodata.deleteData('" + data.id + "')><i class ='nav-icon fas fa-trash'></i></button>"
                               }

                            }
                        ]
                    });

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });


    }
};

var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
    },
    saveForm: function () {
    	console.log('true');
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

           console.log(dataResult);
           
            $.ajax({
                url: '/api/customer',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    if (xhr.status == 200 || xhr.status == 201) {
                        tableBiodata.create();
                        $('#modal-biodata').modal('hide')
            			$('#form-biodata').parsley().reset()

                    } else {

                    }
                },
                erorrr: function (err) {
                    console.log(err);
                }
            });
        }
    },
    setEditData: function (idCabang) {
    	formBiodata.resetForm();

        $.ajax({
            url: '/api/customer/' + idCabang,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-biodata').modal('show')
        			$('#form-biodata').parsley().reset()

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });


    },
    deleteData: function (idCabang) {

        $.ajax({
            url: '/api/customer/' + idCabang,
            method: 'delete',
//            contentType: 'application/json',
//            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                	tableBiodata.create();
                
//                    $('#form-biodata').fromJSON(JSON.stringify(res));
//                    $('#modal-biodata').modal('show')

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });
	}

};

	$.ajax({
	    url: '/api/refdati2/get-all-kota',
	    method: 'get',
	    contentType: 'application/json',
	    success: function (res, status, xhr) {
	        if (xhr.status == 200 || xhr.status == 201) {
	          // Get select
	            var select = document.getElementById('city');
	          
	            // Add options
	          for (var i in res) {
	            $(select).append('<option value=' + res[i].kdDati2 + '>' + res[i].kotaKabupaten + '</option>');
	          }
	        } else {
	
	        }
	    },
	    erorrr: function (err) {
	        console.log(err);
	    }
	});