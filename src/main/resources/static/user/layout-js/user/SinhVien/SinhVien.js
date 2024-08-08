class SinhVien {
    listUser = []

    loadInit = async () => {
        await this.getListSinhVien()
    }

    getListSinhVien = async () => {
        let {data : response} = await axios.get('/java05/account-api/getAllAccount')
        if (!response.status) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }

        this.listUser = response.data.map(e => ({
            id : e.id,
            password : e.password,
            fullname : e.fullname,
            email : e.email,
            role : e.role
        }))
        this.createTableSinhVien()
    }

    createTableSinhVien = () => {
        if ($.fn.dataTable.isDataTable('#tableSinhVien')) {
            $('#tableSinhVien').DataTable().destroy();
        }
        let body = ``
        this.listUser.forEach((e, index) => {
            body +=   `<tr>
                           <td class="bg-transparent text-center">${e.id}</td> 
                           <td class="bg-transparent text-center">${e.fullname}</td> 
                           <td class="bg-transparent text-center">${e.password}</td> 
                           <td class="bg-transparent text-center">${e.email}</td> 
                           <td class="bg-transparent text-center"">${e.role}</td>
                           <td class="bg-transparent text-center">
                                <button class="btn btn-light mb-3" id="btnEdit" onclick="serviceSinhVien.fillDataToForm(${index})">
                                    Edit
                                </button>
                           </td>
                       </tr>`
        })
        let footer = `</tbody></table>`
        let result = body +footer
        $('.tableSinhVien').html(result)
        let table = new DataTable('#tableSinhVien', {
            searching: false,
            info: false,
            paging: true,
            ordering: false,
            lengthMenu: [10]
        })
        $('.dt-length').hide()
    }

    filterSinhVien = async () => {
        let param = {
            id : $('#idFilterId').val(),
            fullname : $('#idFilterTen').val()
        }

        let {data: response} = await axios.get('/java05/account-api/filter',
            {params: param})
        this.listUser = response.data.map(e => ({
            id : e.id,
            password : e.password,
            fullname : e.fullname,
            email : e.email,
            role : e.role
        }))
        if (!response.data.length) {
            Swal.fire({
                title: 'Không tìm thấy sinh viên',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        await this.createTableSinhVien();
    }

    fillDataToForm = (index) => {
        let user = this.listUser[index];
        $('#id').val(user.id);
        $('#idPass').val(user.password);
        $('#idHoTen').val(user.fullname);
        $('#idEmail').val(user.email);
        $('#idRole').val(user.role);
    }

    clearForm = () => {
        $('#id').val('')
        $('#idPass').val('')
        $('#idHoTen').val('')
        $('#idEmail').val('')
        $('#idRole').val('Admin')
    }

    btnLuu_click = async () => {
        if (!this.validateForm()) {
            return
        }
        let dataApiSave = {
            id : $('#id').val(),
            fullname : $('#idHoTen').val(),
            password : $('#idPass').val(),
            email : $('#idEmail').val(),
            role : $('#idRole').val()
        }
        let {data : response} = await axios.post('/java05/account-api/save',
            dataApiSave)
        if (!response.status) {
            Swal.fire({
                title: 'Lưu không thành công',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        Swal.fire({
            title: 'Lưu thông tin sinh viên thành công',
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
        })
        await this.getListSinhVien()
    }

    btnXoa_click = async () => {
        if (!$('#id').val()) {
            Swal.fire({
                title: 'Chưa có id',
                icon: 'warning',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        Swal.fire({
            title: "Chắc chắn muốn xóa?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Xóa",
            cancelButtonText: "Vẫn giữ"
        }).then(async () => {
            let param = {
                id : $('#id').val()
            }
            let {data: response} = await axios.delete('/java05/account-api/delete', {params: param})
            if (!response.status) {
                Swal.fire({
                    title: 'Xóa không thành công',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }
            Swal.fire({
                title: 'Xóa thành công',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })
            this.clearForm()
            await this.getListSinhVien()
        });
    }

    validateForm = () => {
        if (!$('#id').val()) {
            Swal.fire({
                title: 'Id còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idHoTen').val()) {
            Swal.fire({
                title: 'Họ tên còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idPass').val()) {
            Swal.fire({
                title: 'Mật khẩu còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idEmail').val()) {
            Swal.fire({
                title: 'Email còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        return true
    }
}