import request from '@/utils/request'




export function getUserMenus(data) {
  return request({
    url: '/xtcx/sysManager/menu/tree',
    method: 'get',
    data
  });
}
export function getUserMenusone(data) {
  return request({
    url: '/xtcx/user/getMenus',
    method: 'get',
    data
  });
}
export function getRoleData(id) {
  return request({
    url: '/xtcx/sysManager/role?id='+id,
    method: 'get'
  });
}


export function addCreate(data) {
  return request({
    url: '/xtcx/sysManager/menu',
    method: 'post',
    data
  });
}
export function saveEdit(data) {
  return request({
    url: '/xtcx/sysManager/menu',
    method: 'post',
    data
  });
}
export function delMenu(id) {
  return request({
    url: '/xtcx/sysManager/menu?id='+id,
    method: 'delete',

  });
}
