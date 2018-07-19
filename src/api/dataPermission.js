import request from '@/utils/request'


export function pergetAll(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/dataPermission/getAll',
    method: 'get',
    data
  });
}

export function addPermissione(data) {
  return request({
    url: '/mdm/dataPermission',
    method: 'post',
    data
  });
}
export function savePermissione(data) {
  return request({
    url: '/mdm/dataPermission',
    method: 'put',
    data
  });
}
export function deldataPermission(id) {
  return request({
    url: '/mdm/dataPermission/delDataPermissionDefined?id='+id,
    method: 'delete',

  });
}
