import request from '@/utils/request'


// export function getTree(data) {
//   return request({
//     //url: '/user/login',
//     url: '/mdm/menu/getTree',
//     method: 'get',
//     data
//   });
// }
export function getUserMenus(data) {
  return request({
    url: '/mdm/user/getUserMenus',
    method: 'get',
    data
  });
}
export function menuEdit(data) {
  return request({
    url: '/mdm/menu?level=0',
    method: 'get',
    data
  });
}
export function loadmenu1(data) {
  return request({
    url: '/mdm/menu?level=1',
    method: 'get',
    data
  });
}
export function addCreate(data) {
  return request({
    url: '/mdm/menu',
    method: 'post',
    data
  });
}
export function saveEdit(data) {
  return request({
    url: '/mdm/menu',
    method: 'put',
    data
  });
}
export function delMenu(id) {
  return request({
    url: '/mdm/menu?id='+id,
    method: 'delete',

  });
}
