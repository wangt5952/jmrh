import request from '@/utils/request'




export function getUserMenus(data) {
  return request({
    url: '/sysManager/menu/tree',
    method: 'get',
    data
  });
}
export function getUserMenusone(data) {
  return request({
    url: '/user/getMenus',
    method: 'get',
    data
  });
}
export function getRoleData(id) {
  return request({
    url: '/sysManager/role?id='+id,
    method: 'get'
  });
}


export function addCreate(data) {
  return request({
    url: '/sysManager/menu',
    method: 'post',
    data
  });
}
export function saveEdit(data) {
  return request({
    url: '/sysManager/menu',
    method: 'post',
    data
  });
}
export function delMenu(id) {
  return request({
    url: '/sysManager/menu?id='+id,
    method: 'delete',

  });
}
