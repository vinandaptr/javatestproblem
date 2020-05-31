var tableDati2 = {
	    create: function () {
	        // jika table tersebut datatable, maka clear and destroy
	        if ($.fn.DataTable.isDataTable('#tableDati2')) {
	            // table yg sudah dibentuk menjadi datatable harus di-rebuild
				// lagi untuk di-instansiasi-ulang
	            $('#tableDati2').DataTable().clear();
	            $('#tableDati2').DataTable().destroy();
	        }

	        $.ajax({
	            url: '/api/refdati2/get-all',
	            method: 'get',
	            contentType: 'application/json',
	            success: function (res, status, xhr) {
	                if (xhr.status == 200 || xhr.status == 201) {
	                    $('#tableDati2').DataTable({
	                        data: res,
	                        columns: [
	                               {
	                                   title: "Kode Dati 2",
	                                   data: "kdDati2"
	                               },
								   {
									   title: "Kota/Kabupaten",
									   data: "kotaKabupaten"
								   },
	                               {
	                                title: "Action",
	                                data: null,
	                                render: function (data, type, row) {
	                                    return "<button class='btn-primary' onclick=formDati2.setEditData('" + data.id + "')><i class='fa fa-fw fa-edit'></i></button>" +
                                 	   		   "<button class='btn-danger' onclick=formBiodata.deleteData('" + data.id + "')><i class='fa fa-fw fa-trash'></i></button>"
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

var formDati2 = {
	    resetForm: function () {
	        $('#form-kodedati2')[0].reset();
	    },
	    saveForm: function () {
	    	console.log('asdasd');
	        if ($('#form-kodedati2').parsley().validate()) {
	            var dataResult = getJsonForm($("#form-kodedati2").serializeArray(), true);
// document.getElementById("demo").innerHTML = JSON.stringify(dataResult);

	            $.ajax({
	                url: '/api/refdati2',
	                method: 'post',
	                contentType: 'application/json',
	                dataType: 'json',
	                data: JSON.stringify(dataResult),
	                success: function (res, status, xhr) {
	                    if (xhr.status == 200 || xhr.status == 201) {
	                        tableDati2.create();
	                        $('#modal-kodedati2').modal('hide')

	                    } else {

	                    }
	                },
	                erorrr: function (err) {
	                    console.log(err);
	                }
	            });
	        }
	    }, setEditData: function (idCabang) {
	        formDati2.resetForm();

	        $.ajax({
	            url: '/api/refdati2/' + idCabang,
	            method: 'get',
	            contentType: 'application/json',
	            dataType: 'json',
	            success: function (res, status, xhr) {
	                if (xhr.status == 200 || xhr.status == 201) {
	                    $('#form-kodedati2').fromJSON(JSON.stringify(res));
	                    $('#modal-kodedati2').modal('show')

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
//	            contentType: 'application/json',
//	            dataType: 'json',
	            success: function (res, status, xhr) {
	                if (xhr.status == 200 || xhr.status == 201) {
	                	tableDati2.create();
	                
//	                    $('#form-biodata').fromJSON(JSON.stringify(res));
//	                    $('#modal-biodata').modal('show')

	                } else {

	                }
	            },
	            erorrr: function (err) {
	                console.log(err);
	            }
	        });
		}

	};
