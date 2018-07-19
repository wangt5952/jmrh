import request from '@/utils/request'


export function getAllrole(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/role/getAll',
    method: 'get',
    data
  });
}

export function addRole(data) {
  return request({
    url: '/mdm/role',
    method: 'post',
    data
  });
}
export function saveRoleMenus(data) {
  return request({
    url: '/mdm/role/addRoleMenus',
    method: 'post',
    data
  });
}
export function saveRole(data) {
  return request({
    url: '/mdm/role',
    method: 'put',
    data
  });
}
export function getRoleId(id) {//用户详情
  return request({
    url: '/mdm/role?id='+id,
    method: 'get',
  });
}
export function delRole(id) {
  return request({
    url: '/mdm/role?id='+id,
    method: 'delete',

  });
}
export function dataPermissionAll(id) {
  return request({
    url: '/mdm/dataPermission/getAll',
    method: 'get',
  });
}
export function dataTreeAll(id) {
  return request({
    url: '/mdm/dataPermission/getAll',
    method: 'get',
  });
}
