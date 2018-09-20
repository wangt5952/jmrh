import request from '@/utils/request'


export function getAllrole(data) {
  return request({
    //url: '/user/login',
    url: '/sysManager/role/page?pageNum='+data.page+'&pageSize='+data.limit,
    method: 'post',
    data
  });
}

export function addRole(data) {
  return request({
    url: '/sysManager/role',
    method: 'post',
    data
  });
}
export function saveRoleMenus(data) {
  return request({
    url: '/sysManager/role',
    method: 'post',
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
    url: '/sysManager/role?id='+id,
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
