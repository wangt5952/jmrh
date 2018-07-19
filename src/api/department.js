import request from '@/utils/request'


export function depgetAll() {
  return request({
    //url: '/user/login',
    url: '/mdm/department/getAll',
    method: 'get'
  });
}
export function addDepartment(data) {
  return request({
    url: '/mdm/department',
    method: 'post',
    data
  });
}
export function saveDepartment(data) {
  return request({
    url: '/mdm/department',
    method: 'put',
    data
  });
}
export function delDepartment(id) {
  return request({
    url: '/mdm/department?id='+id,
    method: 'delete',

  });
}
